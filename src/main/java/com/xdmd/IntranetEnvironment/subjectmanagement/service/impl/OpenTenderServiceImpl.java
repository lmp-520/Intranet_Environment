package com.xdmd.IntranetEnvironment.subjectmanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdmd.IntranetEnvironment.common.*;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.InsertSqlException;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.UpdateSqlException;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.UpdateTenderStatusException;
import com.xdmd.IntranetEnvironment.subjectmanagement.mapper.OpenTenderMapper;
import com.xdmd.IntranetEnvironment.subjectmanagement.mapper.UploadFileMapper;
import com.xdmd.IntranetEnvironment.subjectmanagement.pojo.OpenTender;
import com.xdmd.IntranetEnvironment.subjectmanagement.service.OpenTenderService;
import com.xdmd.IntranetEnvironment.user.service.impl.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: Kong
 * @createDate: 2019/07/26
 * @description: 招标备案业务实现
 */
@Service
public class OpenTenderServiceImpl implements OpenTenderService {
    private static Logger log = LoggerFactory.getLogger(OpenTenderServiceImpl.class);
    @Autowired
    OpenTenderMapper openTenderMapper;
    @Autowired
    UploadFileMapper uploadFileMapper;
    @Autowired
    TokenService tokenService;


    /**
     * 状态码
     */
    ResultMap resultMap = new ResultMap();


    /**
     * 新增
     *
     *
     * @param token
     * @param response
     * @param openTender
     * @return
     */
    @Override
    public ResultMap insertTender(String token, HttpServletResponse response, OpenTender openTender) {
        try {
//      User user = new User();
//        try {
//            user = tokenService.compare(response, token);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (UserNameNotExistentException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (ClaimsNullException e){
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        }catch (Exception e) {
//            e.printStackTrace();
//            log.error("MenuServiceImpl 中 TokenService 出现问题");
//            return resultMap.message("系统异常");
//        }
//        //当前登录者
//        Integer uid = user.getId();
//        String username = user.getUsername();

            String username = "单位员工";
            //获取课题编号
            openTender.setProjectNo(setProjectNo());
            //执行新增操作
            int insertNo = openTenderMapper.insertTender(openTender);
            //获取当前系统时间
            String nowtime = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(System.currentTimeMillis());
            //新增员工提交信息
            String auditStep = "单位员工提交，等待单位管理员审核";
            String newState = "等待处理";
            int num = 0;
            num = openTenderMapper.insertNewOpenTenderStateRecord(openTender.getId(),username,auditStep,nowtime,newState);
            System.out.println(num);
            if (num == 0) {
                throw new InsertSqlException("审核通过时，在新增审核状态时，新增下一条数据时出错");
            }

            if (insertNo > 0) {
                resultMap.success().message("成功新增" + insertNo + "条数据");
            } else if (insertNo == 0) {
                resultMap.success().message("新增失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 根據单位id查詢相应单位的招标公告
     *
     * @param uid
     * @param projectName
     * @param subjectName
     * @param subjectLeader
     * @param leaderContact
     * @return
     */
    @Override
    public ResultMap getTenderByUid(int uid, String projectName, String subjectName, String subjectLeader, String leaderContact, int pagenNum, int pageSize) {
        try {
            PageHelper.startPage(pagenNum, pageSize, true);
            List<Map> getTenderByUidMap = openTenderMapper.getTenderByUid(uid, projectName, subjectName, subjectLeader, leaderContact);
            PageInfo pageInfo = new PageInfo(getTenderByUidMap);
            if (getTenderByUidMap != null) {
                resultMap.success().message(pageInfo);
            } else if (getTenderByUidMap == null) {
                resultMap.success().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 根據id获取招标公告详情
     *
     * @param id
     * @return
     */
    @Override
    public ResultMap getTenderById(int id) {
        try {
            Map getTenderByIdMap = openTenderMapper.getTenderById(id);
            if (getTenderByIdMap != null) {
                resultMap.success().message(getTenderByIdMap);
            } else if (getTenderByIdMap == null) {
                resultMap.success().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 课题编号自增设置
     *
     * @param
     */
    public String setProjectNo() {
        getNewData();
        String subString = new String(openTenderMapper.getNewData().getProjectNo());
        /**
         * 分离出数字并转换成int类型
         */
        int num = Integer.parseInt(subString.substring(4));
        if (num <= 20190000) {
            /**
             * 获取当前年份
             */
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date date = new Date();
            String sDate = date.toString();
            sDate = sdf.format(date);
            /**
             * 拼接课题编号和年份
             */
            StringBuilder number = new StringBuilder(sDate);
            number.append("0000");
            num = Integer.parseInt(number.toString());
        }
        num += 1;

        StringBuilder sBuilder = new StringBuilder("xdmd");
        String finalResult = sBuilder.insert(sBuilder.length(), num).toString();
        return finalResult;
    }

    /**
     * 分页筛选查询招标信息
     *
     * @param projectName
     * @param subjectName
     * @param subjectLeader
     * @param leaderContact
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultMap getTenderPageList(String projectName, String subjectName, String subjectLeader, String leaderContact, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<Map> openTenderList = openTenderMapper.getTenderPageList(projectName, subjectName, subjectLeader, leaderContact);
            PageInfo pageInfo = new PageInfo(openTenderList);
            if (openTenderList != null) {
                resultMap.success().message(pageInfo);
            } else if (openTenderList == null) {
                resultMap.success().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 根据招标备案id更新相应的附件id【外网上传附件】
     *
     * @param winningFileAttachmentId
     * @param announcementTransactionAnnouncementId
     * @param dealNotificationAttachmentId
     * @param responseFileAttachmentId
     * @param oid
     * @return
     */
    @Override
    public ResultMap updateTenderByoid(int winningFileAttachmentId, int announcementTransactionAnnouncementId, int dealNotificationAttachmentId, int responseFileAttachmentId, int oid) {
        try {
            int updateNo = openTenderMapper.updateAnnexByoid(winningFileAttachmentId, announcementTransactionAnnouncementId, dealNotificationAttachmentId, responseFileAttachmentId, oid);
            if (updateNo > 0) {
                resultMap.success().message("成功更新" + updateNo + "条数据");
            } else if (updateNo < 0) {
                resultMap.success().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 获取最新id
     *
     * @return
     */
    @Override
    public OpenTender getNewData() {
        return openTenderMapper.getNewData();
    }



    /**
     * 招标附件上传
     * @param oid                     招标备案表id
     * @param winningDocument         中标文件附件
     * @param transactionAnnouncement 成交公告附件
     * @param noticeTransaction       成交通知书附件
     * @param responseFile            响应文件附件
     * @return
     * @throws IOException
     */
    @Override
    public ResultMap tenderMultiUpload(String token, HttpServletResponse response, int oid, MultipartFile winningDocument, MultipartFile transactionAnnouncement, MultipartFile noticeTransaction, MultipartFile responseFile) throws IOException, FileUploadException {
//      User user = new User();
//        try {
//            user = tokenService.compare(response, token);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (UserNameNotExistentException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (ClaimsNullException e){
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        }catch (Exception e) {
//            e.printStackTrace();
//            log.error("MenuServiceImpl 中 TokenService 出现问题");
//            return resultMap.message("系统异常");
//        }
//        //当前登录者
//        Integer uid = user.getId();
//        String username = user.getUsername();


        String username = "测试人员";
        //根据招标备案表的id 获取该公司的名字
        String unitName = openTenderMapper.queryUnitNameByoid(oid);
        try {
            /**
             * 中标文件附件
             */
            //判断上传中标文件附件的后缀名是否正确
            String winningDocumentName = winningDocument.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudge.suffixJudge(winningDocumentName);
            if(aBoolean == false){
                resultMap.fail().message("中标文件附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取中标文件附件的地址
            String winningDocumentUrl = multiFileUploadUntil(winningDocument, unitName, "中标文件附件", oid);
            //获取文件后缀名
            String winningDocumentSuffixName = winningDocumentName.substring(winningDocumentName.lastIndexOf(".") + 1);
            // 获取文件大小
            File winningDocumentFile = new File(winningDocumentUrl);
            String winningDocumentFileSize = String.valueOf(winningDocumentFile.length());
            AnnexUpload winningDocumentAnnex = new AnnexUpload(0, winningDocumentUrl, winningDocumentName, "中标文件附件", winningDocumentSuffixName, winningDocumentFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(winningDocumentAnnex);
            /**
             * 成交公告附件
             */
            //判断上传成交公告附件的后缀名是否正确
            String transactionAnnouncementName = transactionAnnouncement.getOriginalFilename();
            Boolean bBoolean = FileSuffixJudge.suffixJudge(transactionAnnouncementName);
             if (bBoolean == false) {
                resultMap.fail().message("成交公告附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取成交公告附件的地址
            String transactionAnnouncementUrl = multiFileUploadUntil(transactionAnnouncement, unitName, "成交公告附件", oid);
            //获取文件后缀名
            String transactionAnnouncementSuffixName = transactionAnnouncementName.substring(winningDocumentName.lastIndexOf(".") + 1);
            // 获取文件大小
            File transactionAnnouncementFile = new File(transactionAnnouncementUrl);
            String transactionAnnouncementFileSize = String.valueOf(winningDocumentFile.length());
            AnnexUpload transactionAnnouncementAnnex = new AnnexUpload(0, transactionAnnouncementUrl, transactionAnnouncementName, "成交公告附件", transactionAnnouncementSuffixName, transactionAnnouncementFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(transactionAnnouncementAnnex);
            /**
             * 成交通知书附件
             */
            //判断上传成交通知书附件的后缀名是否正确
            String noticeTransactionName = noticeTransaction.getOriginalFilename();
            Boolean cBoolean = FileSuffixJudge.suffixJudge(noticeTransactionName);
            if (cBoolean == false) {
                resultMap.fail().message("成交通知书附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取成交通知书附件的地址
            String noticeTransactionUrl = multiFileUploadUntil(noticeTransaction, unitName, "成交通知书附件", oid);
            //获取文件后缀名
            String noticeTransactionSuffixName = noticeTransactionName.substring(noticeTransactionName.lastIndexOf(".") + 1);
            // 获取文件大小
            File noticeTransactionFile = new File(noticeTransactionUrl);
            String noticeTransactionFileSize = String.valueOf(noticeTransactionFile.length());
            AnnexUpload noticeTransactionAnnex = new AnnexUpload(0, noticeTransactionUrl, noticeTransactionName, "成交公告附件", noticeTransactionSuffixName, noticeTransactionFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(noticeTransactionAnnex);
            /**
             * 响应文件附件
             */
            //判断上传响应文件附件的后缀名是否正确
            String responseFileName = responseFile.getOriginalFilename();
            Boolean dBoolean = FileSuffixJudge.suffixJudge(responseFileName);
            if (dBoolean == false) {
                resultMap.fail().message("成交通知书附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取响应文件附件的地址
            String responseFileUrl = multiFileUploadUntil(responseFile, unitName, "响应文件附件", oid);
            //获取文件后缀名
            String responseFileSuffixName = responseFileName.substring(responseFileName.lastIndexOf(".") + 1);
            // 获取文件大小
            File responseFileFile = new File(responseFileUrl);
            String responseFileFileSize = String.valueOf(responseFileFile.length());
            AnnexUpload responseFileAnnex = new AnnexUpload(0, responseFileUrl, responseFileName, "响应文件附件", responseFileSuffixName, responseFileFileSize, null, username);
            //把该文件上传到文件表中
           uploadFileMapper.insertUpload(responseFileAnnex);

            /**
             * 把上传附件的id取出，存到招标备案表中
             */
           openTenderMapper.updateAnnexByoid(winningDocumentAnnex.getId(), transactionAnnouncementAnnex.getId(), noticeTransactionAnnex.getId(), responseFileAnnex.getId(), oid);
            return resultMap.success().message("多个附件上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("附件上传出错:" + e.getMessage());
            throw new FileUploadException("附件上传失败");
        } catch (FileUploadException e) {
            e.printStackTrace();
            resultMap.success().message("附件上传失败");
        }
        return resultMap;
    }


    /**
     * 文件上传基本方法
     * @param file
     * @param fileType
     * @return
     * @throws IOException
     */
    public String multiFileUploadUntil(MultipartFile file, String unitName, String fileType, int oid) throws IOException, FileUploadException {

        //判断文件是否为空
        if (file.isEmpty()) {
            return "上传文件不可为空";
        }
        // 获取文件名拼接当前系统时间作为新文件名
        String nowtime = new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
        StringBuilder pinjiefileName = new StringBuilder(nowtime).append(file.getOriginalFilename());
        String fileName = pinjiefileName.toString();

        //获取招标课题名稱
        Object ketiName = openTenderMapper.getTenderById(oid).get("subjectName");
        //获取文件上传绝对路径
        String FilePath = "D:/xdmd/environment/" + unitName + "/" + ketiName + "/" + fileType + "/";
        StringBuilder initPath = new StringBuilder(FilePath);
        String filePath = initPath.append(fileName).toString();
        File dest = new File(filePath);

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            //保存文件
            file.transferTo(dest);
            //返回文件路径url
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("multiFileUploadUntil出错 :" + e.getMessage());
            throw new FileUploadException("文件上传失败");
        }
    }


    /**
     * 单位管理员审核
     *
     *
     * @param token
     * @param response
     * @param type   审核状态
     * @param reason 审核不通过原因
     * @param oid    审核表id
     * @return
     */
    @Override
    public ResultMap tenderShenHeByUnitManager(String token, HttpServletResponse response, Boolean type, String reason, Integer oid) throws InsertSqlException {
//       User user = new User();
//        try {
//            user = tokenService.compare(response, token);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (UserNameNotExistentException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (ClaimsNullException e){
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        }catch (Exception e) {
//            e.printStackTrace();
//            log.error("MenuServiceImpl 中 TokenService 出现问题");
//            return resultMap.message("系统异常");
//        }
//        //当前登录者
//        Integer uid = user.getId();
//        String username = user.getUsername();



        String username = "单位管理员";
        //根据招标备案表的id 获取该公司的名字
        String unitName = openTenderMapper.queryUnitNameByoid(oid);
        //获取当前系统时间
        String nowtime = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(System.currentTimeMillis());

        try {
            //判断是审核通过还是审核未通过
            if (type) {
                //此时为审核通过时

                //审核通过时,先把上一条数据进行更新，再新增下一条数据
                String state = "已处理";
                String handleContent = "单位管理员审核通过";
                //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
                int num = 0;
                num = openTenderMapper.updateOpenTenderStateRecord(oid,username,state,handleContent,nowtime);
                System.out.println(num);
                if (num == 0) {
                        throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
                }

                //新增下一条数据的处理
                String auditStep = "通过单位管理员初审，等待评估中心审核";
                String newState = "等待处理";
                int num2 = 0;
                num2 = openTenderMapper.insertNewOpenTenderStateRecord(oid, username,auditStep,nowtime,newState);
                System.out.println(num2);
                if (num2 == 0) {
                    throw new InsertSqlException("审核通过时，在新增审核状态时，新增下一条数据时出错");
                }

                //当把审核状态表更新完成后，更新验收申请表中这条数据的验收审核状态
                int num3 = 0;
                int auditStatus = 2;
                num3 = openTenderMapper.updateTenderStatus(auditStatus,oid);
                System.out.println(num3);
                if (num3 == 0) {
                    throw new UpdateTenderStatusException("更新招标备案表中审核状态出错");
                }
               resultMap.success().message("通过单位管理员初审，等待评估中心审核");

            } else {
                //此时审核未通过，首先更新上一条语句
                //审核通过时,先把上一条数据进行更新，再新增下一条数据
                String state = "已退回";
                String handleContent = reason;
                //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
                int num = 0;
                num = openTenderMapper.updateOpenTenderStateRecord(oid,username,state,handleContent,nowtime);
                System.out.println(num);
                if (num == 0) {
                    throw new UpdateSqlException("审核未通过时，在更新审核状态，更新上一条数据时出错");
                }

                //新增下一条数据的处理
                String auditStep = "等待单位员工重新提交";
                String newState = "等待处理";
                int num2 = 0;
                num2 = openTenderMapper.insertNewOpenTenderStateRecord(oid,username,auditStep,nowtime,newState);
                System.out.println(num2);
                if (num2 == 0) {
                    throw new InsertSqlException("在新增审核状态时，新增下一条数据时出错");
                }

                //当把审核状态表更新完成后，更新招标备案表中这条数据的审核状态
                int num3 = 0;
                int auditStatus = 0;
                num3 =openTenderMapper.updateTenderStatus(auditStatus,oid);
                System.out.println(num3);
                if (num3 == 0) {
                    throw new UpdateTenderStatusException("更新招标备案表的审核状态字段时出错");
                }
               resultMap.success().message("单位管理员不通过[具体原因见审核记录],单位员工重新修改提交");
            }
        } catch (InsertSqlException | UpdateSqlException e) {
            e.printStackTrace();
            log.info("在新增审核状态时，新增下一条数据时出错");
        } catch (UpdateTenderStatusException e) {
            e.printStackTrace();
            log.info("更新招标备案表的审核状态字段时出错");
        }
        return resultMap;
    }


    /**
     * 评估中心审核
     * @param type   审核状态
     * @param reason 审核不通过原因
     * @param oid    审核表id
     * @return
     */
    @Override
    public ResultMap tenderShenHeByPingGuCenter(String token, HttpServletResponse response,Boolean type, String reason, Integer oid)  {
//       User user = new User();
//        try {
//            user = tokenService.compare(response, token);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (UserNameNotExistentException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (ClaimsNullException e){
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        }catch (Exception e) {
//            e.printStackTrace();
//            log.error("MenuServiceImpl 中 TokenService 出现问题");
//            return resultMap.message("系统异常");
//        }
//        //当前登录者
//        Integer uid = user.getId();
//        String username = user.getUsername();


        String username = "评估中心";
        //根据招标备案表的id 获取该公司的名字
        String unitName = openTenderMapper.queryUnitNameByoid(oid);
        //获取当前系统时间
        String nowtime = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(System.currentTimeMillis());

        try {
            //判断是审核通过还是审核未通过
            if (type) {
                //此时为审核通过时
                //审核通过时,先把上一条数据进行更新，再新增下一条数据
               String state = "已处理";
               String handleContent = "评估中心审核通过";
               //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
               int num = 0;
               num = openTenderMapper.updateOpenTenderStateRecord(oid,username,state,handleContent,nowtime);
               System.out.println(num);
               if (num == 0) {
                   throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
               }

                //评估中心是最后一步审核,所以审核记录不需要在新增了
                //当把审核状态表更新完成后，更新招标备案表中这条数据的审核状态
                int num3 = 0;
                int auditStatus = 3;
                num3 = openTenderMapper.updateTenderStatus(auditStatus,oid);
                System.out.println(num3);
                if (num3 == 0) {
                    throw new UpdateTenderStatusException("更新招标备案表中审核状态出错");
                }

            } else {
                //此时审核未通过，首先更新上一条语句
                //审核通过时,先把上一条数据进行更新，再新增下一条数据
               String state = "已退回";
               String handleContent = reason;
               //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
               int num = 0;
               num = openTenderMapper.updateOpenTenderStateRecord(oid,username,state,handleContent,nowtime);
               System.out.println(num);
               if (num == 0) {
                   throw new UpdateSqlException("审核未通过时，在更新审核状态，更新上一条数据时出错");
               }

                //新增下一条数据的处理
                String auditStep = "等待单位员工重新提交";
                String newState = "等待处理";
                int num2 = 0;
                num2 = openTenderMapper.insertNewOpenTenderStateRecord(oid, auditStep, newState, username, nowtime);
                System.out.println(num2);
                if (num2 == 0) {
                    throw new InsertSqlException("在新增审核状态时，新增下一条数据时出错");
                }

                //当把审核状态表更新完成后，更新招标备案表中这条数据的审核状态
                int num3 = 0;
                int auditStatus = 0;
                num3 =openTenderMapper.updateTenderStatus(auditStatus,oid);
                System.out.println(num3);
                if (num3 == 0) {
                    throw new UpdateTenderStatusException("更新招标备案表的审核状态字段时出错");
                }
                return resultMap.fail().message("评估中心不通过[具体原因见审核记录],单位员工重新修改提交");
            }
        } catch (UpdateTenderStatusException e) {
            e.printStackTrace();
            log.info("更新招标备案表的审核状态字段时出错");
        } catch (UpdateSqlException e) {
            e.printStackTrace();
            log.info("");
        } catch (InsertSqlException e) {
            e.printStackTrace();
        }
        return resultMap.success().message("通过评估中心审核");
    }


    /**
     * 展示所有未通过单位管理员审批的
     * @return
     */
    @Override
    public ResultMap showAllNoPassTenderReviewByUnitManager(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize,true);
            List<OpenTender> openTenderList=openTenderMapper.showAllNoPassTenderReviewByUnitManager();
            PageInfo pageInfo=new PageInfo(openTenderList);
            if (openTenderList.size() > 0) {
                resultMap.success().message(pageInfo);
            } else if (openTenderList.size() == 0) {
                resultMap.fail().message("没有找到未审批的招标备案");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 展示所有通过单位管理员审批的
     * @return
     */
    @Override
    public ResultMap showAllPassTenderReviewByUnitManager(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize,true);
            List<OpenTender> openTenderList=openTenderMapper.showAllPassTenderReviewByUnitManager();
            PageInfo pageInfo=new PageInfo(openTenderList);
            if (openTenderList.size() > 0) {
                resultMap.success().message(pageInfo);
            } else if (openTenderList.size() == 0) {
                resultMap.fail().message("没有找到未审批的招标备案");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 展示所有通过评估中心审批的
     * @return
     */
    @Override
    public ResultMap showAllPassTenderReviewByPingGu(int pageNum,int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize,true);
            List<OpenTender> openTenderList=openTenderMapper.showAllPassTenderReviewByPingGu();
            PageInfo pageInfo=new PageInfo(openTenderList);
            if (openTenderList.size() > 0) {
                resultMap.success().message(pageInfo);
            } else if (openTenderList.size() == 0) {
                resultMap.fail().message("没有找到未审批的招标备案");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
    /**
     * 展示所有未通过评估中心审批的
     * @return
     */
    @Override
    public ResultMap showAllNoPassReviewTenderByPingGu(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize,true);
            List<OpenTender> openTenderList=openTenderMapper.showAllNoPassTenderReviewByPingGu();
            PageInfo pageInfo=new PageInfo(openTenderList);
            if (openTenderList.size() > 0) {
                resultMap.success().message(pageInfo);
            } else if (openTenderList.size() == 0) {
                resultMap.fail().message("没有找到未审批的招标备案");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

}
