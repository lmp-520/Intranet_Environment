package com.xdmd.IntranetEnvironment.contractmanage.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdmd.IntranetEnvironment.common.*;
import com.xdmd.IntranetEnvironment.contractmanage.mapper.ContractManageMapper;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.ContractManageDTO;
import com.xdmd.IntranetEnvironment.contractmanage.service.ContractManageService;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.InsertSqlException;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.UpdateSqlException;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.UpdateStatusException;
import com.xdmd.IntranetEnvironment.subjectmanagement.service.impl.OpenTenderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/8/4
 * @description: 合同管理业务层实现
 */
@Service
public class ContractManageServiceImpl implements ContractManageService {
    private static final Logger log = LoggerFactory.getLogger(ContractManageServiceImpl.class);
    @Autowired
    ContractManageMapper contractManageMapper;
    @Autowired
    UploadFileMapper uploadFileMapper;
    ResultMap resultMap = new ResultMap();

    @Override
    public ContractManageDTO getNewData() {
        return contractManageMapper.getNewData();
    }

    /**
     * 新增 合同主表信息
     *
     * @param token
     * @param response
     * @param contractManageDTO
     * @return
     */
    @Override
    public ResultMap insert(String token, HttpServletResponse response, ContractManageDTO contractManageDTO) {
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
            //执行新增操作
            int insertNo = contractManageMapper.insert(contractManageDTO);
            System.out.println(insertNo);
            //获取当前系统时间
            String nowtime = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(System.currentTimeMillis());
            //新增员工提交信息
            String auditStep = "单位员工提交，等待单位管理员审核";
            String newState = "等待处理";
            int num = 0;
            num = contractManageMapper.insertNewContractStateRecord(contractManageDTO.getId(), username, auditStep, nowtime, newState);
            System.out.println(num);
            if (num == 0) {
                throw new InsertSqlException("审核通过时，在新增审核状态时，新增数据时出错");
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
     * 根据合同主表id查询
     *
     * @param id
     * @return
     */
    @Override
    public ContractManageDTO getManageInfoById(int id) {
        return contractManageMapper.getManageInfoById(id);
    }


    /**
     * 根据单位id查询本单位的合同
     *
     * @param uid
     * @param subjectCategory
     * @param subjectName
     * @param subjectContact
     * @param subjectContactPhone
     * @param commitmentUnit
     * @param subjectSupervisorDepartment
     * @return
     */
    @Override
    public List<Map> getManageInfoByUid(int uid, String subjectCategory, String subjectName, String subjectContact, String subjectContactPhone, String commitmentUnit, String subjectSupervisorDepartment) {
        return contractManageMapper.getManageInfoByUid(uid, subjectCategory, subjectName, subjectContact, subjectContactPhone, commitmentUnit, subjectSupervisorDepartment);
    }

    /**
     * 全查合同主表
     *
     * @return
     */
    @Override
    public ResultMap getAllInfo(String subjectCategory, String subjectName, String subjectContact, String subjectContactPhone, String commitmentUnit, String subjectSupervisorDepartment, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize, true);
            List<Map> contractMap = contractManageMapper.getAllInfo(subjectCategory, subjectName, subjectContact, subjectContactPhone, commitmentUnit, subjectSupervisorDepartment);
            PageInfo pageInfo = new PageInfo(contractMap);
            if (contractMap.size() > 0) {
                resultMap.success().message(pageInfo);
            } else if (contractMap.size() == 0) {
                resultMap.success().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 根据勾选的合同主表id修改相应的中期检查记录【内网中检】
     *
     * @param ids
     * @return
     */
    @Override
    public int updateContractByIds(int mid, List<Long> ids) {
        //ids.forEach(id -> System.out.println("id-->" + id));
        return contractManageMapper.updateContractByIds(mid, ids);
    }

    /**
     * [查詢] 根据中期检查记录查詢相应合同主表
     *
     * @return
     */
    @Override
    public List<Map> getInfoByMidRecord(int mId) {
        return contractManageMapper.getInfoByMidRecord(mId);
    }

    /**
     * [查詢] 根据单位id和中检记录id 查詢本单位的课题合同
     *
     * @param Uid
     * @param Mid
     * @return
     */
    @Override
    public List<Map> getContractByUid(int Uid, int Mid) {
        return contractManageMapper.getContractByUid(Uid, Mid);
    }

    /**
     * 根据合同id更新相应的附件id【中期检查】
     *
     * @param midCheckAnnexId
     * @param expertAssessmentAnnexId
     * @param subjectSuggestAnnexId
     * @param cid
     * @return
     */
    @Override
    public int updateContractByCid(int midCheckAnnexId, int expertAssessmentAnnexId, int subjectSuggestAnnexId, int cid) {
        int num = contractManageMapper.updateMidCheckAnnextByCid(midCheckAnnexId, expertAssessmentAnnexId, subjectSuggestAnnexId, cid);
        return num;
    }

    /**
     * 根据合同id更新合同附件id
     *
     * @param contractAnnexId
     * @param cid
     * @return
     */
    @Override
    public int updateContractAnnexIdByCid(int contractAnnexId, int cid) {
        return contractManageMapper.updateContractAnnexIdByCid(contractAnnexId, cid);
    }


    /**
     * 合同附件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String ContractFileUpload(MultipartFile file) throws IOException {
        ContractManageDTO contractManageDTO=new ContractManageDTO();
        int cid=contractManageDTO.getId();
        //判断文件是否为空
        if (file.isEmpty()) {
            return "上传文件不可为空";
        }
        // 获取文件名拼接当前系统时间作为新文件名
        String nowtime = new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
        StringBuilder pinjiefileName = new StringBuilder(nowtime).append(file.getOriginalFilename());
        String fileName = pinjiefileName.toString();

        //根据合同主表的id 获取该公司的名字
        String unitName = contractManageMapper.queryUnitNameBycid(cid);
        //获取课题名称
        String ketiName = getManageInfoById(cid).getSubjectName();


        //获取文件上传绝对路径
        String path = "D:/xdmd/environment/" + unitName + "/" + ketiName + "/" + "合同附件" + "/";
        StringBuilder initPath = new StringBuilder(path);
        String filePath = initPath.append(fileName).toString();
        File dest = new File(filePath);

        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //判断上传文件类型是否符合要求
        Boolean typeIsOK = FileSuffixJudge.suffixJudge(file.getOriginalFilename());
        if (typeIsOK == false) {
            return "上传的文件类型不符合要求";
        }
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            //保存文件
            file.transferTo(dest);
            // 获取文件大小
            String fileSize = String.valueOf(dest.length());
            //封装对象
            AnnexUpload annexUpload = new AnnexUpload(0, filePath, fileName, "合同附件", suffixName, fileSize, null, "提交者");
            //保存到数据库中
            int insertNum = uploadFileMapper.insertUpload(annexUpload);
            //更改相应合同附件id
            contractManageMapper.updateContractAnnexIdByCid(annexUpload.getId(), cid);
            return "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    /**
     * 中期检查附件上传
     * @param token
     * @param response
     * @param midCheckAnnex
     * @param expertAssessmentAnnex
     * @param subjectSuggestAnnex
     * @return
     * @throws IOException
     * @throws FileUploadException
     */
    @Override
    public ResultMap midCheckFileUpload(String token, HttpServletResponse response,MultipartFile midCheckAnnex, MultipartFile expertAssessmentAnnex, MultipartFile subjectSuggestAnnex) throws IOException, FileUploadException {
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
        ContractManageDTO contractManageDTO=new ContractManageDTO();
        int cid=contractManageDTO.getId();
        String unitName = contractManageMapper.queryUnitNameBycid(cid);
        OpenTenderServiceImpl openTenderServiceImpl=new OpenTenderServiceImpl();
        try {
            /**
             * 中期检查附件
             */
            //判断上传中标文件附件的后缀名是否正确
            String midCheckAnnexName = midCheckAnnex.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudge.suffixJudge(midCheckAnnexName);
            if (aBoolean == false) {
                resultMap.fail().message("中期检查附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取中标文件附件的地址
            String midCheckAnnexUrl = openTenderServiceImpl.fileUploadUntil(midCheckAnnex, unitName, "中期检查附件", cid);
            //获取文件后缀名
            String midCheckAnnexSuffixName = midCheckAnnexName.substring(midCheckAnnexName.lastIndexOf(".") + 1);
            // 获取文件大小
            File midCheckAnnexFile = new File(midCheckAnnexUrl);
            String winningDocumentFileSize = String.valueOf(midCheckAnnexFile.length());
            AnnexUpload midCheckFileAnnex = new AnnexUpload(0, midCheckAnnexUrl,midCheckAnnexName, "中标文件附件", midCheckAnnexSuffixName, winningDocumentFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(midCheckFileAnnex);
            /**
             * 专家评估附件
             */
            //判断上传成交公告附件的后缀名是否正确
            String expertAssessmentAnnexName = expertAssessmentAnnex.getOriginalFilename();
            Boolean bBoolean = FileSuffixJudge.suffixJudge(expertAssessmentAnnexName);
            if (bBoolean == false) {
                resultMap.fail().message("专家评估附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取成交公告附件的地址
            String expertAssessmentAnnexUrl = openTenderServiceImpl.fileUploadUntil(expertAssessmentAnnex, unitName, "专家评估附件", cid);
            //获取文件后缀名
            String expertAssessmentAnnexSuffixName = expertAssessmentAnnexName.substring(expertAssessmentAnnexName.lastIndexOf(".") + 1);
            // 获取文件大小
            File expertAssessmentAnnexFile = new File(expertAssessmentAnnexUrl);
            String expertAssessmentAnnexFileSize = String.valueOf(expertAssessmentAnnexFile.length());
            AnnexUpload expertAssessmentFileAnnex = new AnnexUpload(0, expertAssessmentAnnexUrl, expertAssessmentAnnexName, "专家评估附件", expertAssessmentAnnexSuffixName, expertAssessmentAnnexFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(expertAssessmentFileAnnex);
            /**
             * 课题建议附件
             */
            //判断上传成交通知书附件的后缀名是否正确
            String subjectSuggestAnnexName = subjectSuggestAnnex.getOriginalFilename();
            Boolean cBoolean = FileSuffixJudge.suffixJudge(subjectSuggestAnnexName);
            if (cBoolean == false) {
                resultMap.fail().message("成交通知书附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取成交通知书附件的地址
            String subjectSuggestAnnexUrl = openTenderServiceImpl.fileUploadUntil(subjectSuggestAnnex, unitName, "课题建议附件", cid);
            //获取文件后缀名
            String subjectSuggestAnnexSuffixName = subjectSuggestAnnexName.substring(subjectSuggestAnnexName.lastIndexOf(".") + 1);
            // 获取文件大小 
            File subjectSuggestAnnexFile = new File(subjectSuggestAnnexUrl);
            String subjectSuggestAnnexFileSize = String.valueOf(subjectSuggestAnnexFile.length());
            AnnexUpload subjectSuggestFileAnnex = new AnnexUpload(0, subjectSuggestAnnexUrl, subjectSuggestAnnexName, "课题建议附件", subjectSuggestAnnexSuffixName, subjectSuggestAnnexFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(subjectSuggestFileAnnex);

            /**
             * 把上传附件的id取出，存到招标备案表中
             */
            contractManageMapper.updateMidCheckAnnextByCid(midCheckFileAnnex.getId(),expertAssessmentFileAnnex.getId(),subjectSuggestFileAnnex.getId(),cid);
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
     * 单位管理员审核
     *
     * @param token
     * @param response
     * @param type     审核状态
     * @param reason   审核不通过原因
     * @param cid      审核表id
     * @return
     * @throws UpdateSqlException
     * @throws InsertSqlException
     */
    @Override
    public ResultMap contractShenHeByUnitManager(String token, HttpServletResponse response, Boolean type, String reason, Integer cid) throws UpdateSqlException, InsertSqlException {
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
        //根据合同主表的id 获取该公司的名字
        String unitName = contractManageMapper.queryUnitNameBycid(cid);
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
                num = contractManageMapper.updateContractStateRecord(cid, username, state, handleContent, nowtime);
                if (num == 0) {
                    throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
                }

                //新增下一条数据的处理
                String auditStep = "通过单位管理员初审，等待评估中心审核";
                String newState = "等待处理";
                int num2 = 0;
                num2 = contractManageMapper.insertNewContractStateRecord(cid, username, auditStep, nowtime, newState);

                if (num2 == 0) {
                    throw new InsertSqlException("审核通过时，在新增审核状态时，新增下一条数据时出错");
                }

                //当把审核状态表更新完成后，更新合同主表表中这条数据的验收审核状态
                int num3 = 0;
                int auditStatus = 2;
                num3 = contractManageMapper.updateContractStatus(auditStatus, cid);

                if (num3 == 0) {
                    throw new UpdateStatusException("更新合同主表中审核状态出错");
                }
                resultMap.success().message("通过单位管理员初审，等待评估中心审核");

            } else {
                //此时审核未通过，首先更新上一条语句
                //审核通过时,先把上一条数据进行更新，再新增下一条数据
                String state = "已退回";
                String handleContent = reason;
                //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
                int num = 0;
                num = contractManageMapper.updateContractStateRecord(cid, username, state, handleContent, nowtime);
                System.out.println(num);
                if (num == 0) {
                    throw new UpdateSqlException("审核未通过时，在更新审核状态，更新上一条数据时出错");
                }

                //新增下一条数据的处理
                String auditStep = "等待单位员工重新提交";
                String newState = "等待处理";
                int num2 = 0;
                num2 = contractManageMapper.insertNewContractStateRecord(cid, username, auditStep, nowtime, newState);
                System.out.println(num2);
                if (num2 == 0) {
                    throw new InsertSqlException("在新增审核状态时，新增下一条数据时出错");
                }

                //当把审核状态表更新完成后，更新合同主表中这条数据的审核状态
                int num3 = 0;
                int auditStatus = 0;
                num3 = contractManageMapper.updateContractStatus(auditStatus, cid);
                System.out.println(num3);
                if (num3 == 0) {
                    throw new UpdateStatusException("更新合同主表的审核状态字段时出错");
                }
                resultMap.success().message("单位管理员不通过[具体原因见审核记录],单位员工重新修改提交");
            }
        } catch (InsertSqlException | UpdateSqlException e) {
            e.printStackTrace();
            log.info("在新增审核状态时，新增下一条数据时出错");
        } catch (UpdateStatusException e) {
            e.printStackTrace();
            log.info("更新合同主表的审核状态字段时出错");
        }
        return resultMap;
    }

    /**
     * 评估中心审核
     *
     * @param token
     * @param response
     * @param type
     * @param reason
     * @param cid
     * @return
     */
    @Override
    public ResultMap contractShenHeByPingGuCenter(String token, HttpServletResponse response, Boolean type, String reason, Integer cid) {
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
        //根据合同主表的id 获取该公司的名字
        String unitName = contractManageMapper.queryUnitNameBycid(cid);
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
                num = contractManageMapper.updateContractStateRecord(cid, username, state, handleContent, nowtime);
                if (num == 0) {
                    throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
                }

                //新增下一条数据的处理
                String auditStep = "通过评估中心审核，等待法规科技处审核";
                String newState = "等待处理";
                int num2 = 0;
                num2 = contractManageMapper.insertNewContractStateRecord(cid, username, auditStep, nowtime, newState);
                if (num2 == 0) {
                    throw new InsertSqlException("审核通过时，在新增审核状态时，新增下一条数据时出错");
                }

                //当把审核状态表更新完成后，更新合同主表表中这条数据的验收审核状态
                int num3 = 0;
                int auditStatus = 3;
                num3 = contractManageMapper.updateContractStatus(auditStatus, cid);

                if (num3 == 0) {
                    throw new UpdateStatusException("更新合同主表中审核状态出错");
                }
                resultMap.success().message("通过评估中心审核，等待法规科技处审核");

            } else {
                //此时审核未通过，首先更新上一条语句
                //审核通过时,先把上一条数据进行更新，再新增下一条数据
                String state = "已退回";
                String handleContent = reason;
                //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
                int num = 0;
                num = contractManageMapper.updateContractStateRecord(cid, username, state, handleContent, nowtime);
                System.out.println(num);
                if (num == 0) {
                    throw new UpdateSqlException("审核未通过时，在更新审核状态，更新上一条数据时出错");
                }

                //新增下一条数据的处理
                String auditStep = "等待单位员工重新提交";
                String newState = "等待处理";
                int num2 = 0;
                num2 = contractManageMapper.insertNewContractStateRecord(cid, username, auditStep, nowtime, newState);
                System.out.println(num2);
                if (num2 == 0) {
                    throw new InsertSqlException("在新增审核状态时，新增下一条数据时出错");
                }

                //当把审核状态表更新完成后，更新合同主表中这条数据的审核状态
                int num3 = 0;
                int auditStatus = 0;
                num3 = contractManageMapper.updateContractStatus(auditStatus, cid);
                System.out.println(num3);
                if (num3 == 0) {
                    throw new UpdateStatusException("更新合同主表的审核状态字段时出错");
                }
                resultMap.success().message("评估中审核不通过[具体原因见审核记录],单位员工重新修改提交");
            }
        } catch (InsertSqlException | UpdateSqlException e) {
            e.printStackTrace();
            log.info("在新增审核状态时，新增下一条数据时出错");
        } catch (UpdateStatusException e) {
            e.printStackTrace();
            log.info("更新合同主表的审核状态字段时出错");
        }
        return resultMap;
    }


    /**
     * 法规科技处审核
     *
     * @param token
     * @param response
     * @param type
     * @param reason
     * @param cid
     * @return
     */
    @Override
    public ResultMap contractShenHeByFaGui(String token, HttpServletResponse response, Boolean type, String reason, Integer cid) {
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


        String username = "法规科技处";
        //根据合同主表的id 获取该公司的名字
        String unitName = contractManageMapper.queryUnitNameBycid(cid);
        //获取当前系统时间
        String nowtime = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(System.currentTimeMillis());

        try {
            //判断是审核通过还是审核未通过
            if (type) {
                //此时为审核通过时
                //审核通过时,先把上一条数据进行更新，再新增下一条数据
                String state = "已处理";
                String handleContent = "法规科技处审核通过";
                //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
                int num = 0;
                num = contractManageMapper.updateContractStateRecord(cid, username, state, handleContent, nowtime);

                if (num == 0) {
                    throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
                }

                //评估中心是最后一步审核,所以审核记录不需要在新增了
                //当把审核状态表更新完成后，更新合同主表中这条数据的审核状态
                int num3 = 0;
                int auditStatus = 4;
                num3 = contractManageMapper.updateContractStatus(auditStatus, cid);
                System.out.println(num3);
                if (num3 == 0) {
                    throw new UpdateStatusException("更新合同主表中审核状态出错");
                }
                resultMap.success().message("通过评估中心审核");

            } else {
                //此时审核未通过，首先更新上一条语句
                //审核通过时,先把上一条数据进行更新，再新增下一条数据
                String state = "已退回";
                String handleContent = reason;
                //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
                int num = 0;
                num = contractManageMapper.updateContractStateRecord(cid, username, state, handleContent, nowtime);
                System.out.println(num);
                if (num == 0) {
                    throw new UpdateSqlException("审核未通过时，在更新审核状态，更新上一条数据时出错");
                }

                //新增下一条数据的处理
                String auditStep = "等待单位员工重新提交";
                String newState = "等待处理";
                int num2 = 0;
                num2 = contractManageMapper.insertNewContractStateRecord(cid, username, auditStep, nowtime, newState);
                System.out.println(num2);
                if (num2 == 0) {
                    throw new InsertSqlException("在新增审核状态时，新增下一条数据时出错");
                }

                //当把审核状态表更新完成后，更新合同主表中这条数据的审核状态
                int num3 = 0;
                int auditStatus = 0;
                num3 = contractManageMapper.updateContractStatus(auditStatus, cid);
                System.out.println(num3);
                if (num3 == 0) {
                    throw new UpdateStatusException("更新合同主表的审核状态字段时出错");
                }
                resultMap.fail().message("评估中心不通过[具体原因见审核记录],单位员工重新修改提交");
            }
        } catch (UpdateStatusException e) {
            e.printStackTrace();
            log.info("更新合同主表的审核状态字段时出错");
        } catch (UpdateSqlException e) {
            e.printStackTrace();
            log.info("");
        } catch (InsertSqlException e) {
            e.printStackTrace();
        }
        return resultMap;
    }


    /**
     * 不通过被退回时重新提交[即修改]【外网】
     * @param token
     * @param response
     * @param contractManageDTO
     * @return
     * @throws UpdateSqlException
     * @throws UpdateStatusException
     */
    @Override
    public ResultMap updateContractStatusByReturnCommit(String token, HttpServletResponse response,ContractManageDTO contractManageDTO) throws UpdateSqlException, UpdateStatusException {
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
        //执行更新操作
        int updateNum = contractManageMapper.updateContractStatusByReturnCommit(contractManageDTO);
        //获取当前系统时间
        String nowtime = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(System.currentTimeMillis());
        //审核不通过重新修改数据提交时,先把上一条数据进行更新，再新增下一条数据
        String state = "等待处理";
        String handleContent = "等待单位管理员审核";
        //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
        int num0 = 0;
        num0 = contractManageMapper.updateContractStateRecord(contractManageDTO.getId(), username, state, handleContent, nowtime);
        if (num0 == 0) {
            throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
        }
        //新增员工提交信息
        String auditStep = "单位员工修改后重新提交，等待单位管理员审核";
        String newState = "等待处理";
        int num1 = 0;
        num1 = contractManageMapper.insertNewContractStateRecord(contractManageDTO.getId(), username, auditStep, nowtime, newState);
        //当把审核状态表更新完成后，更新合同主表中这条数据的审核状态
        int num3 = 0;
        int auditStatus = 1;
        num3 = contractManageMapper.updateContractStatus(auditStatus, contractManageDTO.getId());
        if (num3 == 0) {
            throw new UpdateStatusException("更新合同主表的审核状态字段时出错");
        }
        if (updateNum > 0) {
            resultMap.success().message("重新修改并提交成功");
        } else if (updateNum == 0) {
            resultMap.fail().message("重新修改并提交失败");
        }
        return resultMap;
    }


    /**
     * 展示所有通过单位管理员审批的 【外网】
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultMap showAllPassContractReviewByUnitManager(String subjectCategory,String subjectName, String subjectContact,String subjectContactPhone,String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize, true);
            List<Map> contractMap= contractManageMapper.showAllPassContractReviewByUnitManager(subjectCategory,subjectName,subjectContact,subjectContactPhone,commitmentUnit,subjectSupervisorDepartmentint);
            PageInfo pageInfo = new PageInfo(contractMap);
            if (contractMap.size() > 0) {
                resultMap.success().message(pageInfo);
            } else if (contractMap.size() == 0) {
                resultMap.fail().message("没有找到数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 展示所有未通过单位管理员审批的 【外网】
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultMap showAllNoPassContractReviewByUnitManager(String subjectCategory,String subjectName, String subjectContact,String subjectContactPhone,String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize, true);
            List<Map> contractMap= contractManageMapper.showAllNoPassContractReviewByUnitManager(subjectCategory,subjectName,subjectContact,subjectContactPhone,commitmentUnit,subjectSupervisorDepartmentint);
            PageInfo pageInfo = new PageInfo(contractMap);
            if (contractMap.size() > 0) {
                resultMap.success().message(pageInfo);
            } else if (contractMap.size() == 0) {
                resultMap.fail().message("没有找到数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 展示所有通过评估中心审批的 【内网】
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultMap showAllPassContractReviewByPingGu(String subjectCategory,String subjectName, String subjectContact,String subjectContactPhone,String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize, true);
            List<Map> contractMap= contractManageMapper.showAllPassContractReviewByPingGu(subjectCategory,subjectName,subjectContact,subjectContactPhone,commitmentUnit,subjectSupervisorDepartmentint);
            PageInfo pageInfo = new PageInfo(contractMap);
            if (contractMap.size() > 0) {
                resultMap.success().message(pageInfo);
            } else if (contractMap.size() == 0) {
                resultMap.fail().message("没有找到数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 展示所有未通过评估中心审批的 【内网】
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultMap showAllNoPassReviewContractByPingGu(String subjectCategory,String subjectName, String subjectContact,String subjectContactPhone,String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize, true);
            List<Map> contractMap= contractManageMapper.showAllNoPassReviewContractByPingGu(subjectCategory,subjectName,subjectContact,subjectContactPhone,commitmentUnit,subjectSupervisorDepartmentint);
            PageInfo pageInfo = new PageInfo(contractMap);
            if (contractMap.size() > 0) {
                resultMap.success().message(pageInfo);
            } else if (contractMap.size() == 0) {
                resultMap.fail().message("没有找到数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 展示所有通过法规科技处审批的 【内网】
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultMap showAllPassContractReviewByFaGui(String subjectCategory,String subjectName, String subjectContact,String subjectContactPhone,String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize, true);
            List<ContractManageDTO> contractMap= contractManageMapper.showAllPassContractReviewByFaGui(subjectCategory,subjectName,subjectContact,subjectContactPhone,commitmentUnit,subjectSupervisorDepartmentint);
            PageInfo pageInfo = new PageInfo(contractMap);
            if (contractMap.size() > 0) {
                resultMap.success().message(pageInfo);
            } else if (contractMap.size() == 0) {
                resultMap.fail().message("没有找到数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 展示所有未通过法规科技处审批的 【内网】
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultMap showAllNoPassReviewContractByFaGui(String subjectCategory,String subjectName, String subjectContact,String subjectContactPhone,String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize, true);
            List<ContractManageDTO> contractMap= contractManageMapper.showAllNoPassReviewContractByFaGui(subjectCategory,subjectName,subjectContact,subjectContactPhone,commitmentUnit,subjectSupervisorDepartmentint);
            PageInfo pageInfo = new PageInfo(contractMap);
            if (contractMap.size() > 0) {
                resultMap.success().message(pageInfo);
            } else if (contractMap.size() == 0) {
                resultMap.fail().message("没有找到数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

}



