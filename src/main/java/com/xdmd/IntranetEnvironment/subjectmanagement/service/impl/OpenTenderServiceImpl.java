package com.xdmd.IntranetEnvironment.subjectmanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdmd.IntranetEnvironment.common.*;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.JwtInformation;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.impl.ExtranetTokenService;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.InsertSqlException;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.UpdateSqlException;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.UpdateStatusException;
import com.xdmd.IntranetEnvironment.subjectmanagement.mapper.OpenTenderMapper;
import com.xdmd.IntranetEnvironment.subjectmanagement.pojo.AttachmentAttribute;
import com.xdmd.IntranetEnvironment.subjectmanagement.pojo.OpenTender;
import com.xdmd.IntranetEnvironment.subjectmanagement.service.OpenTenderService;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import com.xdmd.IntranetEnvironment.user.pojo.User;
import com.xdmd.IntranetEnvironment.user.service.impl.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/07/26
 * @description: 招标备案业务实现
 */
@Service
@Transactional
public class OpenTenderServiceImpl implements OpenTenderService {
    private static Logger log = LoggerFactory.getLogger(OpenTenderServiceImpl.class);
    @Autowired
    OpenTenderMapper openTenderMapper;
    @Autowired
    UploadFileMapper uploadFileMapper;
    @Autowired
    ExtranetTokenService extranetTokenService;//外网
    @Autowired
    TokenService tokenService;//内网


    /**
     * 状态码
     */
    ResultMap resultMap = new ResultMap();


    /**
     * 新增招标备案
     *
     * @param token
     * @param response
     * @param openTender
     * @return
     */
    @Override
    public ResultMap insertTender(String token, HttpServletResponse response, OpenTender openTender) {
        JwtInformation jwtInformation = new JwtInformation();
        try {
            jwtInformation = extranetTokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }
        //Integer userid = jwtInformation.getUid();
        String username = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        //String cname = jwtInformation.getCompanyName();

        try {
            //获取课题编号
            openTender.setProjectNo(setProjectNo());
            //执行新增操作
            int insertNo = openTenderMapper.insertTender(openTender);
            //单位关联招标备案
            insertTidAndUid(cid, openTender.getId());
            //获取当前系统时间
            String nowtime = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date());
            //新增员工提交信息
            // String auditStep = "单位员工提交，等待单位管理员审核";
            String auditStep = "单位员工提交，等待评估中心审核";
            String newState = "等待处理";
            int num = 0;
            num = openTenderMapper.insertNewOpenTenderStateRecord(openTender.getId(), username, auditStep, nowtime, newState);
            if (num == 0) {
                throw new InsertSqlException("审核通过时，在新增审核状态时，新增数据时出错");
            }
            if (insertNo > 0) {
                resultMap.success().message(openTender.getId());
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
     * 根據单位id查詢相应单位的招标备案
     *
     * @param projectName
     * @param subjectName
     * @param subjectLeader
     * @param leaderContact
     * @return
     */
    @Override
    public ResultMap getTenderByUid(String token, HttpServletResponse response, String projectName, String subjectName, String subjectLeader, String leaderContact, int pageNum, int pageSize) {
        JwtInformation jwtInformation = new JwtInformation();
        try {
            jwtInformation = extranetTokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }
        //用户id
        //Integer userid = jwtInformation.getUid();
        //用户名
        //String username = jwtInformation.getUsername();
        //单位id
        Integer cid = jwtInformation.getCid();
        //单位名称
        //String cname = jwtInformation.getCompanyName();
        try {
            String orderby="ot.id desc";
            PageHelper.startPage(pageNum, pageSize, orderby);
            List<Map> getTenderByUidMap = openTenderMapper.getTenderByUid(cid, projectName, subjectName, subjectLeader, leaderContact);
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
     * 根據id获取招标备案详情【内外网】
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
        String finalResult = null;
        if (openTenderMapper.isGetData() > 0) {
            String str = new String(openTenderMapper.getNewData());
            /**
             * 分离出数字并转换成int类型
             */
            int num = Integer.parseInt(str.substring(4));
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
            finalResult = sBuilder.insert(sBuilder.length(), num).toString();
        } else {
            //int num = 20190000;//预设值
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
            int num = Integer.parseInt(number.toString());
            num += 1;

            StringBuilder sBuilder = new StringBuilder("xdmd");
            finalResult = sBuilder.insert(sBuilder.length(), num).toString();
        }
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
     * 招标附件上传
     * @param oid                     招标主表id
     * @param winningDocument         中标文件附件
     * @param transactionAnnouncement 成交公告附件
     * @param noticeTransaction       成交通知书附件
     * @param responseFile            响应文件附件
     * @param otherAttachments        其他附件
     * @return
     * @throws IOException
     */
    @Override
    public ResultMap tenderMultiUpload(String token, HttpServletResponse response, int oid, MultipartFile winningDocument, MultipartFile transactionAnnouncement, MultipartFile noticeTransaction, MultipartFile responseFile, MultipartFile otherAttachments) throws IOException, FileUploadException {
        JwtInformation jwtInformation = new JwtInformation();
        try {
            jwtInformation = extranetTokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }
        //Integer userid = jwtInformation.getUid();
        String username = jwtInformation.getUsername();
        //Integer cid = jwtInformation.getCid();
        //String cname = jwtInformation.getCompanyName();

        //根据招标备案表的id 获取该公司的名字
        String unitName = openTenderMapper.queryUnitNameByoid(oid);
        try {
            /**
             * 中标文件附件
             */
            //判断上传中标文件附件的后缀名是否正确
            String winningDocumentName = winningDocument.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudge.suffixJudge(winningDocumentName);
            if (aBoolean == false) {
                resultMap.fail().message("中标文件附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取中标文件附件的地址
            String winningDocumentUrl = fileUploadUntil(winningDocument, unitName, "中标文件附件");
            //获取文件后缀名
            String winningDocumentSuffixName = winningDocumentName.substring(winningDocumentName.lastIndexOf(".") + 1);
            // 获取文件大小
            File winningDocumentFile = new File(winningDocumentUrl);
            String winningDocumentFileSize = String.valueOf(winningDocumentFile.length());
            AnnexUpload winningDocumentData = new AnnexUpload(0, winningDocumentUrl, winningDocumentName, "中标文件附件", winningDocumentSuffixName, winningDocumentFileSize, null, username);
            //把该文件上传到文件记录表中
            uploadFileMapper.insertUpload(winningDocumentData);
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
            String transactionAnnouncementUrl = fileUploadUntil(transactionAnnouncement, unitName, "成交公告附件");
            //获取文件后缀名
            String transactionAnnouncementSuffixName = transactionAnnouncementName.substring(transactionAnnouncementName.lastIndexOf(".") + 1);
            // 获取文件大小
            File transactionAnnouncementFile = new File(transactionAnnouncementUrl);
            String transactionAnnouncementFileSize = String.valueOf(winningDocumentFile.length());
            AnnexUpload transactionAnnouncementData = new AnnexUpload(0, transactionAnnouncementUrl, transactionAnnouncementName, "成交公告附件", transactionAnnouncementSuffixName, transactionAnnouncementFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(transactionAnnouncementData);
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
            String noticeTransactionUrl = fileUploadUntil(noticeTransaction, unitName, "成交通知书附件");
            //获取文件后缀名
            String noticeTransactionSuffixName = noticeTransactionName.substring(noticeTransactionName.lastIndexOf(".") + 1);
            // 获取文件大小
            File noticeTransactionFile = new File(noticeTransactionUrl);
            String noticeTransactionFileSize = String.valueOf(noticeTransactionFile.length());
            AnnexUpload noticeTransactionData = new AnnexUpload(0, noticeTransactionUrl, noticeTransactionName, "成交公告附件", noticeTransactionSuffixName, noticeTransactionFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(noticeTransactionData);
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
            String responseFileUrl = fileUploadUntil(responseFile, unitName, "响应文件附件");
            //获取文件后缀名
            String responseFileSuffixName = responseFileName.substring(responseFileName.lastIndexOf(".") + 1);
            // 获取文件大小
            String responseFileFileSize = String.valueOf(new File(responseFileUrl).length());
            AnnexUpload responseFileAttachmentData = new AnnexUpload(0, responseFileUrl, responseFileName, "响应文件附件", responseFileSuffixName, responseFileFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(responseFileAttachmentData);
            /**
             * 其他附件
             */
            //判断上传其他附件的后缀名是否正确
            String otherAttachmentsName = otherAttachments.getOriginalFilename();
            Boolean eBoolean = FileSuffixJudge.suffixJudge(otherAttachmentsName);
            if (eBoolean == false) {
                resultMap.fail().message("其他附件文件格式不正确,请上传正确的文件格式");
            }
            //获取其他附件的地址
            String otherAttachmentsUrl = fileUploadUntil(otherAttachments, unitName, "其他附件");
            //获取文件后缀名
            String otherAttachmentsSuffixName = responseFileName.substring(otherAttachmentsName.lastIndexOf(".") + 1);
            // 获取文件大小
            String otherAttachmentsFileSize = String.valueOf(new File(otherAttachmentsUrl).length());
            AnnexUpload otherAttachmentsData = new AnnexUpload(0, otherAttachmentsUrl, otherAttachmentsName, "其他附件", otherAttachmentsSuffixName, otherAttachmentsFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(otherAttachmentsData);

            /**
             * 把上传附件的id取出，存到招标备案表中
             */
            openTenderMapper.updateAnnexByoid(winningDocumentData.getId(), transactionAnnouncementData.getId(), noticeTransactionData.getId(), responseFileAttachmentData.getId(), otherAttachmentsData.getId(), oid);
            return resultMap.success().message("多个附件上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("附件上传出错:" + e.getMessage());
            throw new FileUploadException("附件上传失败");
        } catch (FileUploadException e) {
            e.printStackTrace();
            resultMap.fail().message("附件上传失败");
        }
        return resultMap;
    }


    /**
     * 文件上传基本方法
     *
     * @param file
     * @param fileType
     * @return
     * @throws IOException
     */
    public String fileUploadUntil(MultipartFile file, String unitName, String fileType) throws IOException, FileUploadException {

        //判断文件是否为空
        if (file.isEmpty()) {
            return "上传文件不可为空";
        }
        // 获取文件名拼接当前系统时间作为新文件名
        String nowtime = new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
        StringBuilder pinjiefileName = new StringBuilder(nowtime).append(file.getOriginalFilename());
        String fileName = pinjiefileName.toString();

        //获取招标课题名稱
        //Object ketiName = openTenderMapper.getTenderById(oid).get("subjectName");
        //获取文件上传绝对路径
        String path = "D:/xdmd/environment/" + unitName + "/" + fileType + "/";
        StringBuilder initPath = new StringBuilder(path);
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
     * 评估中心审核
     *
     * @param type   审核状态
     * @param reason 审核不通过原因
     * @param oid    审核表id
     * @return
     */
    @Override
    public ResultMap tenderShenHeByPingGuCenter(String token, HttpServletResponse response, Boolean type, String reason, Integer oid) {
        User user = new User();
        try {
            user = tokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }
        //当前登录者信息
        // Integer uid = user.getId();
        String username = user.getUsername();

        //根据招标备案表的id 获取该公司的名字
        String unitName = openTenderMapper.queryUnitNameByoid(oid);
        //获取当前系统时间
        String nowtime = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date());

        try {
            //判断是审核通过还是审核未通过
            System.out.println(type);
            if (type) {
                //此时为审核通过时
                //审核通过时,先把上一条数据进行更新，再新增下一条数据
                String state = "已处理";
                String handleContent = "审核通过";
                //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
                int num = 0;
                num = openTenderMapper.updateOpenTenderStateRecord(oid, username, state, handleContent, nowtime);
                if (num == 0) {
                    throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
                }

                //评估中心是最后一步审核,所以审核记录不需要在新增了
                //当把审核状态表更新完成后，更新招标备案表中这条数据的审核状态
                int num3 = 0;
                int auditStatus = 3;
                num3 = openTenderMapper.updateTenderStatus(auditStatus, oid);

                if (num3 == 0) {
                    throw new UpdateStatusException("更新招标备案表中审核状态出错");
                }
                resultMap.success().message("通过评估中心审核");

            } else {
                //此时审核未通过，首先更新上一条语句
                //审核通过时,先把上一条数据进行更新，再新增下一条数据
                String state = "已退回";
                String handleContent = reason;
                //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
                int num = 0;
                num = openTenderMapper.updateOpenTenderStateRecord(oid, username, state, handleContent, nowtime);
                System.out.println(num);
                if (num == 0) {
                    throw new UpdateSqlException("审核未通过时，在更新审核状态，更新上一条数据时出错");
                }

                //新增下一条数据的处理
                String auditStep = "等待单位员工重新提交";
                String newState = "等待处理";
                int num2 = 0;
                num2 = openTenderMapper.insertNewOpenTenderStateRecord(oid, username, auditStep, nowtime, newState);
                System.out.println(num2);
                if (num2 == 0) {
                    throw new InsertSqlException("在新增审核状态时，新增下一条数据时出错");
                }

                //当把审核状态表更新完成后，更新招标备案表中这条数据的审核状态
                int num3 = 0;
                int auditStatus = 0;
                num3 = openTenderMapper.updateTenderStatus(auditStatus, oid);
                System.out.println(num3);
                if (num3 == 0) {
                    throw new UpdateStatusException("更新招标备案表的审核状态字段时出错");
                }
                resultMap.fail().message("评估中心不通过[具体原因见审核记录],单位员工重新修改提交");
            }
        } catch (UpdateStatusException e) {
            e.printStackTrace();
            log.info("更新招标备案表的审核状态字段时出错");
        } catch (UpdateSqlException e) {
            e.printStackTrace();
            log.info("");
        } catch (InsertSqlException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 展示所有未通过单位管理员审批的
     *
     * @return
     */
    @Override
    public ResultMap showAllNoPassTenderReviewByUnitManager(String projectName, String subjectName, String subjectLeader, String leaderContact, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize, true);
            List<Map> openTenderList = openTenderMapper.showAllNoPassTenderReviewByUnitManager(projectName, subjectName, subjectLeader, leaderContact);
            PageInfo pageInfo = new PageInfo(openTenderList);
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
     *
     * @return
     */
    @Override
    public ResultMap showAllPassTenderReviewByUnitManager(String projectName, String subjectName, String subjectLeader, String leaderContact, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize, true);
            List<Map> openTenderList = openTenderMapper.showAllPassTenderReviewByUnitManager(projectName, subjectName, subjectLeader, leaderContact);
            PageInfo pageInfo = new PageInfo(openTenderList);
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
     *
     * @return
     */
    @Override
    public ResultMap showAllPassTenderReviewByPingGu(String projectName, String subjectName, String subjectLeader, String leaderContact, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize, true);
            List<Map> openTenderList = openTenderMapper.showAllPassTenderReviewByPingGu(projectName, subjectName, subjectLeader, leaderContact);
            PageInfo pageInfo = new PageInfo(openTenderList);
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
     *
     * @return
     */
    @Override
    public ResultMap showAllNoPassReviewTenderByPingGu(String projectName, String subjectName, String subjectLeader, String leaderContact, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize, true);
            List<Map> openTenderList = openTenderMapper.showAllNoPassTenderReviewByPingGu(projectName, subjectName, subjectLeader, leaderContact);
            PageInfo pageInfo = new PageInfo(openTenderList);
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
     * 不通过被退回时重新提交[即修改]【外网】
     * @param token
     * @param response
     * @param oldWinningDocumentFileUrl
     * @param oldTransactionAnnouncementFileUrl
     * @param oldNoticeTransactionFileUrl
     * @param oldResponseFileFileUrl
     * @param oldOtherAttachmentsFileUrl
     * @param winningDocument
     * @param transactionAnnouncement
     * @param noticeTransaction
     * @param responseFile
     * @param otherAttachments
     * @param openTender
     * @return
     */
    @Override
    public ResultMap updateTenderStatusByReturnCommit(String token, HttpServletResponse response, String oldWinningDocumentFileUrl, String oldTransactionAnnouncementFileUrl, String oldNoticeTransactionFileUrl, String oldResponseFileFileUrl, String oldOtherAttachmentsFileUrl, MultipartFile winningDocument, MultipartFile transactionAnnouncement, MultipartFile noticeTransaction, MultipartFile responseFile, MultipartFile otherAttachments, OpenTender openTender) {
        JwtInformation jwtInformation = new JwtInformation();
        try {
            jwtInformation = extranetTokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }
        //Integer userid = jwtInformation.getUid();
        String username = jwtInformation.getUsername();
        //Integer cid = jwtInformation.getCid();
        String unitName = jwtInformation.getCompanyName();

        try {
            //更新招标数据
            int updateNum = openTenderMapper.updateTenderStatusByReturnCommit(openTender);

            /**
             * 判断五个旧文件是否为空
             * 中标文件附件
             */
            if (oldWinningDocumentFileUrl != null) {
                //判断上传中标文件附件的后缀名是否正确
                String winningDocumentName = winningDocument.getOriginalFilename();
                Boolean aBoolean = FileSuffixJudge.suffixJudge(winningDocumentName);
                if (aBoolean == false) {
                    resultMap.fail().message("中标文件附件的文件格式不正确,请上传正确的文件格式");
                }
                //再根据旧的文件地址，先把文件给删除掉
                File file = new File(oldWinningDocumentFileUrl);
                file.delete();
                //对新的中标文件附件进行上传
                String winningDocumentUrl = fileUploadUntil(winningDocument, unitName, "中标文件附件");
                //获取文件后缀名
                String winningDocumentSuffixName = winningDocumentName.substring(winningDocumentName.lastIndexOf(".") + 1);
                // 获取文件大小
                String winningDocumentFileSize = String.valueOf(new File(winningDocumentUrl).length());
                AnnexUpload winningDocumentData = new AnnexUpload(0, winningDocumentUrl, winningDocumentName, "中标文件附件", winningDocumentSuffixName, winningDocumentFileSize, null, username);
                //把该文件上传到文件表中
                uploadFileMapper.insertUpload(winningDocumentData);
                //把上传附件的id取出,存到招标备案表中
                openTenderMapper.updateWinningFileAttachmentIdByoid(winningDocumentData.getId(),openTender.getId());
            }
            /**
             * 成交公告附件
             */
            if (oldTransactionAnnouncementFileUrl != null) {
                String transactionAnnouncementName = transactionAnnouncement.getOriginalFilename();
                //判断上传成交公告附件的后缀名是否正确
                Boolean bBoolean = FileSuffixJudge.suffixJudge(transactionAnnouncementName);
                if (bBoolean == false) {
                    resultMap.fail().message("成交公告附件的文件格式不正确,请上传正确的文件格式");
                }
                //再根据旧的文件地址，先把文件给删除掉
                File file = new File(oldTransactionAnnouncementFileUrl);
                file.delete();
                //对新的成交公告附件进行上传
                String transactionAnnouncementUrl = fileUploadUntil(transactionAnnouncement, unitName, "成交公告附件");
                //获取文件后缀名
                String transactionAnnouncementSuffixName = transactionAnnouncementName.substring(transactionAnnouncementName.lastIndexOf(".") + 1);
                // 获取文件大小
                File transactionAnnouncementFile = new File(transactionAnnouncementUrl);
                String transactionAnnouncementFileSize = String.valueOf(transactionAnnouncementFile.length());
                AnnexUpload transactionAnnouncementData = new AnnexUpload(0, transactionAnnouncementUrl, transactionAnnouncementName, "成交公告附件", transactionAnnouncementSuffixName, transactionAnnouncementFileSize, null, username);
                //把该文件上传到文件表中
                uploadFileMapper.insertUpload(transactionAnnouncementData);
                //把上传附件的id取出,存到招标备案表中
                openTenderMapper.updateAnnouncementTransactionAnnouncementIdByoid(transactionAnnouncementData.getId(),openTender.getId());
            }
            /**
             * 成交通知书附件
             */
            if (oldNoticeTransactionFileUrl != null) {
                //判断上传成交通知书附件的后缀名是否正确
                String noticeTransactionName = noticeTransaction.getOriginalFilename();
                Boolean cBoolean = FileSuffixJudge.suffixJudge(noticeTransactionName);
                if (cBoolean == false) {
                    resultMap.fail().message("成交通知书附件的文件格式不正确,请上传正确的文件格式");
                }
                //再根据旧的文件地址，先把文件给删除掉
                File file = new File(oldNoticeTransactionFileUrl);
                file.delete();
                //对新的成交通知书附件进行上传
                String noticeTransactionUrl = fileUploadUntil(noticeTransaction, unitName, "成交通知书附件");
                //获取文件后缀名
                String noticeTransactionSuffixName = noticeTransactionName.substring(noticeTransactionName.lastIndexOf(".") + 1);
                // 获取文件大小
                File noticeTransactionFile = new File(noticeTransactionUrl);
                String noticeTransactionFileSize = String.valueOf(noticeTransactionFile.length());
                AnnexUpload noticeTransactionData = new AnnexUpload(0, noticeTransactionUrl, noticeTransactionName, "成交公告附件", noticeTransactionSuffixName, noticeTransactionFileSize, null, username);
                //把该文件上传到文件表中
                uploadFileMapper.insertUpload(noticeTransactionData);
                //把上传附件的id取出,存到招标备案表中
                openTenderMapper.updateDealNotificationAttachmentIdByoid(noticeTransactionData.getId(),openTender.getId());
            }
            /**
             * 响应文件附件
             */
            if (oldResponseFileFileUrl != null) {
                //判断上传响应文件附件的后缀名是否正确
                String responseFileName = responseFile.getOriginalFilename();
                Boolean dBoolean = FileSuffixJudge.suffixJudge(responseFileName);
                if (dBoolean == false) {
                    resultMap.fail().message("成交通知书附件的文件格式不正确,请上传正确的文件格式");
                }
                //再根据旧的文件地址，先把文件给删除掉
                File file = new File(oldResponseFileFileUrl);
                file.delete();
                //获取响应文件附件的地址
                String responseFileUrl = fileUploadUntil(responseFile, unitName, "响应文件附件");
                //获取文件后缀名
                String responseFileSuffixName = responseFileName.substring(responseFileName.lastIndexOf(".") + 1);
                // 获取文件大小
                String responseFileFileSize = String.valueOf(new File(responseFileUrl).length());
                AnnexUpload responseFileAttachmentData = new AnnexUpload(0, responseFileUrl, responseFileName, "响应文件附件", responseFileSuffixName, responseFileFileSize, null, username);
                //把该文件上传到文件表中
                uploadFileMapper.insertUpload(responseFileAttachmentData);
                //把上传附件的id取出,存到招标备案表中
                openTenderMapper.updateResponseFileAttachmentIdByoid(responseFileAttachmentData.getId(),openTender.getId());
            }
            /**
             * 其他附件
             */
            if (oldOtherAttachmentsFileUrl != null) {
                //判断上传其他附件的后缀名是否正确
                String otherAttachmentsName = otherAttachments.getOriginalFilename();
                Boolean eBoolean = FileSuffixJudge.suffixJudge(otherAttachmentsName);
                if (eBoolean == false) {
                    resultMap.fail().message("其他附件文件格式不正确,请上传正确的文件格式");
                }
                //再根据旧的文件地址，先把文件给删除掉
                File file = new File(oldOtherAttachmentsFileUrl);
                file.delete();
                //获取其他附件的地址
                String otherAttachmentsUrl = fileUploadUntil(otherAttachments, unitName, "其他附件");
                //获取文件后缀名
                String otherAttachmentsSuffixName = otherAttachmentsName.substring(otherAttachmentsName.lastIndexOf(".") + 1);
                // 获取文件大小
                String otherAttachmentsFileSize = String.valueOf(new File(otherAttachmentsUrl).length());
                AnnexUpload otherAttachmentsData = new AnnexUpload(0, otherAttachmentsUrl, otherAttachmentsName, "其他附件", otherAttachmentsSuffixName, otherAttachmentsFileSize, null, username);
                //把该文件上传到文件表中
                uploadFileMapper.insertUpload(otherAttachmentsData);
                //把上传附件的id取出,存到招标备案表中
                openTenderMapper.updateOtherAttachmentsIdByoid(otherAttachmentsData.getId(),openTender.getId());
            }

            //获取当前系统时间
            String nowtime = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date());
            //审核不通过重新修改数据提交时,先把上一条数据进行更新，再新增下一条数据
            String state = "已处理";
            //String handleContent = "等待单位管理员审核";
            String handleContent = "审核通过";
            //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
            int num0 = 0;
            num0 = openTenderMapper.updateOpenTenderStateRecord(openTender.getId(), username, state, handleContent, nowtime);
            if (num0 == 0) {
                throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
            }
            //新增员工提交信息
            String auditStep = "单位员工提交，等待评估中心审核";
            String newState = "等待处理";
            int num1 = 0;
            num1 = openTenderMapper.insertNewOpenTenderStateRecord(openTender.getId(), username, auditStep, nowtime, newState);
            //当把审核状态表更新完成后，更新招标备案表中这条数据的审核状态
            int num3 = 0;
            int auditStatus = 2;
            num3 = openTenderMapper.updateTenderStatus(auditStatus, openTender.getId());
            if (num3 == 0) {
                throw new UpdateStatusException("更新招标备案表的审核状态字段时出错");
            }

            if (updateNum>0) {
                resultMap.success().message("修改数据并上传附件成功");
            } else if (updateNum == 0) {
                resultMap.fail().message("修改数据并上传附件失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }



    /**
     * 根据招标备案表id获取中标文件路径和文件名
     *
     * @param oid
     * @return
     */
    @Override
    public ResultMap getfileInfo(int oid) {
        AttachmentAttribute attachmentAttribute = new AttachmentAttribute();

        int winningFileId = openTenderMapper.queryWinningFileIdByOid(oid);
        String winningDocumentFileName = openTenderMapper.queryFileNameByOid(winningFileId);
        String winningDocumentFileUrl = openTenderMapper.queryFileUrlOid(winningFileId);
        attachmentAttribute.setWinningDocumentFileName(winningDocumentFileName);
        attachmentAttribute.setWinningDocumentFileUrl(winningDocumentFileUrl);

        int transactionAnnouncementFileId = openTenderMapper.queryTransactionAnnouncementIdByOid(oid);
        String transactionAnnouncementName = openTenderMapper.queryFileNameByOid(transactionAnnouncementFileId);
        String transactionAnnouncementUrl = openTenderMapper.queryFileUrlOid(transactionAnnouncementFileId);
        attachmentAttribute.setTransactionAnnouncementName(transactionAnnouncementName);
        attachmentAttribute.setTransactionAnnouncementUrl(transactionAnnouncementUrl);

        int noticeTransactionFileId = openTenderMapper.queryNoticeTransactionIdByOid(oid);
        String noticeTransactionName = openTenderMapper.queryFileNameByOid(noticeTransactionFileId);
        String noticeTransactionUrl = openTenderMapper.queryFileUrlOid(noticeTransactionFileId);
        attachmentAttribute.setNoticeTransactionName(noticeTransactionName);
        attachmentAttribute.setNoticeTransactionUrl(noticeTransactionUrl);

        int responseFileId = openTenderMapper.queryResponseIdByOid(oid);
        String responseFileName = openTenderMapper.queryFileNameByOid(responseFileId);
        String responseFiletUrl = openTenderMapper.queryFileUrlOid(responseFileId);
        attachmentAttribute.setResponseFileName(responseFileName);
        attachmentAttribute.setResponseFiletUrl(responseFiletUrl);

        int otherAttachmentsFileId = openTenderMapper.queryOtherAttachmentIdByOid(oid);
        String otherAttachmentsName = openTenderMapper.queryFileNameByOid(otherAttachmentsFileId);
        String otherAttachmentsUrl = openTenderMapper.queryFileUrlOid(otherAttachmentsFileId);
        attachmentAttribute.setOtherAttachmentsName(otherAttachmentsName);
        attachmentAttribute.setOtherAttachmentsUrl(otherAttachmentsUrl);

        return resultMap.success().message(attachmentAttribute);
    }


    /**
     * 根据招标表id查询审核记录
     * @param oid
     * @return
     */
    @Override
    public ResultMap getAllShenHeTableRecordInfoByContractId(int oid) {
        try {
            List<TenderContractShenheRecordDTO> shenHeInfo = openTenderMapper.getAllShenHeTableRecordInfoByContractId(oid);
            if (shenHeInfo.size() > 0) {
                resultMap.success().message(shenHeInfo);
            } else if (shenHeInfo.size() == 0) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 单位关联招标备案
     *
     * @param unitId
     * @param tenderId
     * @return
     */
    @Override
    public ResultMap insertTidAndUid(int unitId, int tenderId) {
        try {
            int insertNo = openTenderMapper.insertTidAndUid(unitId, tenderId);
            if (insertNo > 0) {
                resultMap.success().message("新增成功");
            } else if (insertNo == 0) {
                resultMap.fail().message("新增失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

}
