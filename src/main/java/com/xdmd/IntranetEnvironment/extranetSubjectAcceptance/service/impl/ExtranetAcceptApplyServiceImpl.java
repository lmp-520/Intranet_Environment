package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.impl;

import com.xdmd.IntranetEnvironment.common.FileSuffixJudgeUtil;
import com.xdmd.IntranetEnvironment.common.FileUploadUtil;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.exception.MysqlErrorException;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.mapper.ExtranetAcceptApplyMapper;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.*;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.ExtranetAcceptApplyService;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.utils.IntegrationFile;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional(rollbackFor = Exception.class)
public class ExtranetAcceptApplyServiceImpl implements ExtranetAcceptApplyService {
    @Autowired
    private ExtranetTokenService extranetTokenService;
    @Autowired
    private ExtranetAcceptApplyMapper acceptApplyMapper;
    ResultMap resultMap = new ResultMap();
    PageBean pageBean = new PageBean();
    ExtranetCheckApplyState extranetCheckApplyState = new ExtranetCheckApplyState();
    //打印日志
    private static Logger log = LoggerFactory.getLogger(ExtranetAcceptApplyServiceImpl.class);

    //企业填写验收申请表
    @Transactional(rollbackFor = Exception.class)
    public ResultMap AddAcceptApply(ExtranetCheckApply extranetCheckApply, MultipartFile submitInventoryFile, MultipartFile applicationAcceptanceFile, MultipartFile achievementsFile, String createname) throws MysqlErrorException {
        //新增验收申请表
        acceptApplyMapper.addAcceptApply(extranetCheckApply);

        //更新验收申请表的状态
        //新增第一条验收数据
        int cid = extranetCheckApply.getId();//获取验收申请表的id
        String firstHandler = createname;   //提交的人名
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        String state = "待处理";

        extranetCheckApplyState.setCheckApplyId(cid);
        extranetCheckApplyState.setFistHandler(firstHandler);
        extranetCheckApplyState.setAuditStep("公司审批");
        extranetCheckApplyState.setFirstHandleTime(nowTime);
        extranetCheckApplyState.setState("待处理");


        //新增验收审核状态
        acceptApplyMapper.insertCheckApplyState(extranetCheckApplyState);

        return resultMap.success().message("新增成功");

    }

    //企业修改验收申请表
    @Transactional(rollbackFor = Exception.class)
    public ResultMap updateAcceptApply(ExtranetCheckApply extranetCheckApply, MultipartFile submitInventoryFile, MultipartFile applicationAcceptanceFile, MultipartFile achievementsFile, String createname) throws MysqlErrorException {
        int number = 0;
        int number2 = 0;
        int number3 = 0;
        int number4 = 0;
        try {
            number = acceptApplyMapper.updateByPrimaryKey(extranetCheckApply);

//            //把上传的文件上传到文件表
//            //验收申请表的上传
//            IntegrationFile applicationAcceptanceIntegrationFile = new IntegrationFile();
//            UploadFile applicationUploadFile = applicationAcceptanceIntegrationFile.IntegrationFile(applicationAcceptanceFile, extranetCheckApply.getId(), extranetCheckApply.getApplicationAcceptanceUrl(), "验收申请表", createname, extranetCheckApply.getApplicationId());
//            number2 = acceptApplyFileUploadMapper.updateByPrimaryKey(applicationUploadFile);
//
//            //成果附件的上传
//            IntegrationFile achievementsUploadFile = new IntegrationFile();
//            UploadFile achievementsUploadFile2 = achievementsUploadFile.IntegrationFile(achievementsFile, extranetCheckApply.getId(), extranetCheckApply.getAchievementsUrl(), "成果附件", createname, extranetCheckApply.getAchievementId());
//            number3 = acceptApplyFileUploadMapper.updateByPrimaryKey(achievementsUploadFile2);
//
//            //提交清单的上传
//            IntegrationFile submitInventoryUploadFile = new IntegrationFile();
//            UploadFile submitUploadFile = submitInventoryUploadFile.IntegrationFile(submitInventoryFile, extranetCheckApply.getId(), extranetCheckApply.getSubmitInventoryUrl(), "提交清单", createname, extranetCheckApply.getSubmitId());
//            number4 = acceptApplyFileUploadMapper.updateByPrimaryKey(submitUploadFile);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExtranetAcceptApplyServiceImpl -- updateAcceptApply 中更新语句出错");
            throw new MysqlErrorException();
        }
        if (number == 0 || number2 == 0 || number3 == 0 || number4 == 0) {
            resultMap.fail().message("更新失败");
        } else {
            resultMap.success().message("更新成功");
        }
        return resultMap;
    }

//    //验收申请表的查询
//    public ResultMap queryAcceptApply(String subjectName, String projectLeader, Integer page, Integer total) throws StringToDateException {
//        //页数
//        int newpage = 0;
//        if (page == 1) {
//            newpage = page - 1;
//        } else {
//            newpage = (page - 1) * total;
//        }
//
//        List<ExtranetCheckApply> checkApplyList = acceptApplyMapper.queryAcceptApply(subjectName, projectLeader, newpage, total);
//        Integer alltotal = acceptApplyMapper.queryAllTotal(subjectName, projectLeader, newpage, total);
//
//        List<JSONObject> jsonObjectList = new ArrayList<>();
//
//        //判断根据用户输入的筛选条件是否有内容
//        if (alltotal == null) {
//            return resultMap.success().message(jsonObjectList);
//        }
//
//        //判断用户输入的页数是否超过总页数
//        int allPage = 0;
//        if (alltotal % page == 0) {
//            allPage = alltotal / page;
//        } else {
//            allPage = (alltotal / page) + 1;
//        }
//        if (page > allPage) {
//            return resultMap.fail().message("页数超过总页数");
//        }
//
//
//        for (ExtranetCheckApply checkApply : checkApplyList) {
//            //对查询出来的日期进行处理
//            Date agreementStartTime = checkApply.getAgreementStartTime();
//            String agreementStartTimeString = SqlDateToString.dateToString(agreementStartTime);
//
//            Date agreementEndTime = checkApply.getAgreementEndTime();
//            String agreementEndTimeString = SqlDateToString.dateToString(agreementEndTime);
//
//            Date applicationAcceptanceTime = checkApply.getApplicationAcceptanceTime();
//            String applicationAcceptanceTimeString = SqlDateToString.dateToString(applicationAcceptanceTime);
//
//            //获取申请表的主键
//            Integer id = checkApply.getId();
//
//            String AcceptApplyFile = "验收申请表";
//            String submitInventoryFile = "提交清单";
//            String achievementsFile = "成果附件";
//
//
//            //验收申请表
//            UploadFile uploadFile1 = acceptApplyFileUploadMapper.queryFileUrl(id, AcceptApplyFile);
//            checkApply.setApplicationId(uploadFile1.getId());
//            checkApply.setApplicationAcceptanceUrl(uploadFile1.getUploadFileAddress());
//
//            //提交清单
//            UploadFile uploadFile2 = acceptApplyFileUploadMapper.queryFileUrl(id, submitInventoryFile);
//            checkApply.setSubmitId(uploadFile2.getId());
//            checkApply.setSubmitInventoryUrl(uploadFile2.getUploadFileAddress());
//
//            //成果附件
//            UploadFile uploadFile3 = acceptApplyFileUploadMapper.queryFileUrl(id, achievementsFile);
//            checkApply.setAchievementId(uploadFile3.getId());
//            checkApply.setAchievementsUrl(uploadFile3.getUploadFileAddress());
//
//
//            //通过AliBaBa fastJson工具 把实体类中不需要的字段去除
//            JSONObject jsonObject = JSON.parseObject(checkApply.toString());
//            jsonObject.put("agreementStartTimeString", agreementStartTimeString);
//            jsonObject.put("agreementEndTimeString", agreementEndTimeString);
//            jsonObject.put("applicationAcceptanceTimeString", applicationAcceptanceTimeString);
//
//            jsonObjectList.add(jsonObject);
//
//        }
//        PageBean<Object> objectPageBean = new PageBean<>();
//        objectPageBean.setData(jsonObjectList);
//        objectPageBean.setCount(alltotal);
//
//
//        resultMap.success().message(objectPageBean);
//        return resultMap;
//    }

    //根据公司的id，查询公司的名字
    @Override
    public String queryCompanyNameByCid(Integer cid) {
        String companyName = acceptApplyMapper.queryCompanyNameByCid(cid);
        return companyName;
    }

    //对文件进行上传
    @Override
    public void uploadFile(UploadFile uploadBusinessFile) {
        acceptApplyMapper.uploadFile(uploadBusinessFile);
    }


    //公司管理员进行验收审核查询     其中查询的是，正在审核过程中的内容
    @Override
    public ResultMap query(String token, HttpServletResponse response, String topicName, String topicNumber, Integer page, Integer total) {
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

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();


        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }

        //获取本公司的申请验收信息总条数
        int alltotal = 0;
        alltotal = acceptApplyMapper.queryAllExpert(cid, topicName, topicNumber);
        if (alltotal == 0) {
            return resultMap.fail().message(null);
        }

        //查询该单位正在审核过程中的信息
        List<ExtranetCheckApply> extranetCheckApplyList = acceptApplyMapper.queryAcceptApply(cid, topicName, topicNumber, newpage, total);

        //遍历验收申请的表格，获取每条信息的审核记录
        for (ExtranetCheckApply extranetCheckApply : extranetCheckApplyList) {
            //查询专家信息组意见信息  根据验收申请表的id查询
            ExtranetExpertGroupComment extranetExpertGroupComment = null;
            try {
                extranetExpertGroupComment = acceptApplyMapper.queryExpertGroupComment(extranetCheckApply.getId());
            } catch (NullPointerException e) {
                extranetExpertGroupComment = null;
            }
            //根据验收专家组意见表的id，获取对应的专家组成员信息
            List<ExtranetExpertGroupCommentsName> extranetExpertGroupCommentsNameList = null;
            try {
                extranetExpertGroupCommentsNameList = acceptApplyMapper.queryExpertGroupCommentsName(extranetExpertGroupComment.getEgcId());
                extranetExpertGroupComment.setExtranetExpertGroupCommentsNameList(extranetExpertGroupCommentsNameList);//把专家组成员信息存放到专家组信息中
            } catch (NullPointerException e) {
                extranetExpertGroupCommentsNameList = null;
            }
            extranetCheckApply.setExtranetExpertGroupComment(extranetExpertGroupComment);   //把专家组信息插入到验收申请实体类中

            //根据验收申请表的id，查询最终验收报告主表信息
            AcceptanceCertificate acceptanceCertificate = null;
            try {
                acceptanceCertificate = acceptApplyMapper.queryAcceptanceCertificate(extranetCheckApply.getId());
            } catch (NullPointerException e) {
                acceptanceCertificate = null;
            }
            //根据最终验收报告的id，查询验收报告中的专利信息
            List<AcceptanceCertificatePatent> acceptanceCertificatePatentList = null;
            try {
                acceptanceCertificatePatentList = acceptApplyMapper.queryAcceptanceCertificatePatentByCid(acceptanceCertificate.getId());
                acceptanceCertificate.setAcceptanceCertificatePatentList(acceptanceCertificatePatentList);  //把专利表的信息插入到最终验收报告中

            } catch (NullPointerException e) {
                acceptanceCertificatePatentList = null;
            }
            //根据最终验收报告的id，查询验收报告中的主要参与人员信息
            List<AcceptanceCertificatePrincipalPersonnel>  acceptanceCertificatePrincipalPersonnelList = null;
            try {
                acceptanceCertificatePrincipalPersonnelList = acceptApplyMapper.queryAcceptanceCertificatePersonnel(acceptanceCertificate.getId());
                acceptanceCertificate.setAcceptanceCertificatePrincipalPersonnelList(acceptanceCertificatePrincipalPersonnelList);//把查询出来的主要参与人员信息插入到最终验收报告中
            } catch (NullPointerException e) {
                acceptanceCertificatePrincipalPersonnelList = null;
            }
            //根据验收报告的id，查询出验收报告中的课题负责人信息
            List<AcceptanceCertificateSubjectPeople> acceptanceCertificateSubjectPeopleList = null;
            try {
                acceptanceCertificateSubjectPeopleList = acceptApplyMapper.queryAcceptanceCertificateSubjectPeople(acceptanceCertificate.getId());
                acceptanceCertificate.setAcceptanceCertificateSubjectPeopleList(acceptanceCertificateSubjectPeopleList);
            } catch (NullPointerException e) {
                acceptanceCertificateSubjectPeopleList = null;
            }
            //把最终验收报告信息存入验收申请表中
            extranetCheckApply.setAcceptanceCertificate(acceptanceCertificate);

            Integer id = extranetCheckApply.getId();   //获取验收申请表的id
            //通过验收申请表的id，获取到对应的审核状态
            List<ExtranetCheckApplyState> extranetCheckApplyStateList = acceptApplyMapper.queryCheckApplyState(id);
            //把验收申请表的内容存放到checkApply中
            extranetCheckApply.setExtranetCheckApplyStateList(extranetCheckApplyStateList);

            //通过验收状态的id，查询出验收审核的状态
            String acceotancePhaseName = acceptApplyMapper.queryAcceptancePhaseName(extranetCheckApply.getAcceptancePhaseId());
            extranetCheckApply.setAcceptancePhaseName(acceotancePhaseName);

            //根据验收申请表的id，查询出验收申请表的Url
            String applicationFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getApplicationUrlId());
            extranetCheckApply.setApplicationAcceptanceUrl(applicationFileUrl);
            //获取验收申请表Url的名称
            String applicationFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getApplicationUrlId());
            extranetCheckApply.setApplicationAcceptanceUrlName(applicationFileName);

            //获取成果附件的URL 与 名称
            String achievementFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getAchievementUrlId());
            extranetCheckApply.setAchievementsUrl(achievementFileUrl);
            //获取成果附件Url的名称
            String achievementName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getAchievementUrlId());
            extranetCheckApply.setAchievementsName(achievementName);

            //获取提交清单的URL 与 名称
            String submitFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getSubmitUrlId());
            extranetCheckApply.setSubmitInventoryUrl(submitFileUrl);
            //获取提交清单Url的名称
            String submitFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getSubmitUrlId());
            extranetCheckApply.setSubmitInventoryUrlName(submitFileName);

            //获取审计报告的Url 与 名称
            String auditReportFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getAuditReportUrlId());
            extranetCheckApply.setAuditReportUrl(auditReportFileUrl);
            //获取审计报告Url的名称
            String auditReportFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getAuditReportUrlId());
            extranetCheckApply.setAuditReportUrlName(auditReportFileName);

            //获取初审报告的Url 与 名称
            String firstInspectionReportFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getFirstInspectionReportUrlId());
            extranetCheckApply.setFirstInspectionReportUrl(firstInspectionReportFileUrl);
            //获取初审报告Url的名称
            String firstInspectionReportFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getFirstInspectionReportUrlId());
            extranetCheckApply.setFirstInspectionReportUrlName(firstInspectionReportFileName);

            //获取专家组意见的Url与名称
            String expertGroupCommentsFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getExpertGroupCommentsUrlId());
            extranetCheckApply.setExpertGroupCommentsUrl(expertGroupCommentsFileUrl);
            //获取专家组意见Url的名称
            String expertGroupCommentsFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getExpertGroupCommentsUrlId());
            extranetCheckApply.setExpertGroupCommentsUrlName(expertGroupCommentsFileName);

            //获取专家组评议表的Url与名称
            String expertAcceptanceFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getExpertAcceptanceFormId());
            extranetCheckApply.setExpertAcceptanceFormUrl(expertAcceptanceFileUrl);
            //获取专家组评议表Url的名称
            String expertAcceptanceFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getExpertAcceptanceFormId());
            extranetCheckApply.setExpertAcceptanceFormUrlName(expertAcceptanceFileName);

            //获取最终验收证书的URL与名称
            String acceptanceCertificateFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getAcceptanceCertificateId());
            extranetCheckApply.setAcceptanceCertificateUrl(acceptanceCertificateFileUrl);
            //获取最终验收证书Url的名称
            String acceptanceCertificateFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getAcceptanceCertificateId());
            extranetCheckApply.setAcceptanceCertificateUrlName(acceptanceCertificateFileName);
        }

        pageBean.setCount(alltotal);
        pageBean.setData(extranetCheckApplyList);
        return resultMap.success().message(pageBean);

    }

    //管理员进行审核
    @Override
    public ResultMap examine(String token, HttpServletResponse response, Boolean type, String reason, Integer id) {
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

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();

        //审核状态分为两种
        if (type) {
            //此时审核通过，相当于把信息提交给内网
            //更新上一条员工的提交信息状态
            String state = "已处理";
            String handleContent = "审核通过";
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = sdf.format(date);

            //根据数据的id，把处理人，审核状态，审核内容内容，处理时间更新
            acceptApplyMapper.updateCheckApplyState(id, uname, state, handleContent, nowTime);

            //新增下一条的数据状态
            String auditStep = "等待验收初审";
            String newState = "等待处理";
            acceptApplyMapper.addNewCheckApplyState(id, uname, auditStep, nowTime, newState);

            //当把审核状态表更新完成后，更新验收申请表中这条数据的验收审核状态
            int acceptancePhaseNum = 3;
            acceptApplyMapper.updateAcceptancePhaseById(id, acceptancePhaseNum);
        } else {
            //此时审核没有通过，退回给企业员工进行操作
            String state = "已退回";
            String handleContent = reason;
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = sdf.format(date);

            //更新上一条数据
            acceptApplyMapper.updateCheckApplyState(id, uname, state, handleContent, nowTime);

            //新增下一条数据
            String auditStep = "等待员工提交";
            String newState = "等待处理";
            acceptApplyMapper.addNewCheckApplyState(id, uname, auditStep, nowTime, newState);

            //当审核状态表更新完成后，更新验收申请表中的验收审核状态
            int acceptancePhaseNum = 1;
            acceptApplyMapper.updateAcceptancePhaseById(id, acceptancePhaseNum);
        }

        return resultMap.success().message("审核通过");
    }

    //查询最后的验收情况   只有结题 不通过验收 与通过验收这三种
    @Override
    public ResultMap queryResult(String token, HttpServletResponse response, String topicName, String topicNumber, Integer page, Integer total) {
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

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();

        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }

        //获取本公司验收已经结束的信息条数
        int alltotal = 0;
        alltotal = acceptApplyMapper.queryAllResultCheckApply(cid, topicName, topicNumber);
        if (alltotal == 0) {
            return resultMap.fail().message(null);
        }

        //查询所有的结题 通过验收 没通过验收 的信息内容
        List<ExtranetCheckApply> extranetCheckApplyList = acceptApplyMapper.queryResultCheckApply(cid, topicName, topicNumber, newpage, total);

        for (ExtranetCheckApply extranetCheckApply : extranetCheckApplyList) {
            //查询专家信息组意见信息  根据验收申请表的id查询
            ExtranetExpertGroupComment extranetExpertGroupComment = null;
            try {
                extranetExpertGroupComment = acceptApplyMapper.queryExpertGroupComment(extranetCheckApply.getId());
            } catch (NullPointerException e) {
                extranetExpertGroupComment = null;
            }
            //根据验收专家组意见表的id，获取对应的专家组成员信息
            List<ExtranetExpertGroupCommentsName> extranetExpertGroupCommentsNameList = null;
            try {
                extranetExpertGroupCommentsNameList = acceptApplyMapper.queryExpertGroupCommentsName(extranetExpertGroupComment.getEgcId());
                extranetExpertGroupComment.setExtranetExpertGroupCommentsNameList(extranetExpertGroupCommentsNameList);//把专家组成员信息存放到专家组信息中
            } catch (NullPointerException e) {
                extranetExpertGroupCommentsNameList = null;
            }
            extranetCheckApply.setExtranetExpertGroupComment(extranetExpertGroupComment);   //把专家组信息插入到验收申请实体类中

            //根据验收申请表的id，查询最终验收报告主表信息
            AcceptanceCertificate acceptanceCertificate = null;
            try {
                acceptanceCertificate = acceptApplyMapper.queryAcceptanceCertificate(extranetCheckApply.getId());
            } catch (NullPointerException e) {
                acceptanceCertificate = null;
            }
            //根据最终验收报告的id，查询验收报告中的专利信息
            List<AcceptanceCertificatePatent> acceptanceCertificatePatentList = null;
            try {
                acceptanceCertificatePatentList = acceptApplyMapper.queryAcceptanceCertificatePatentByCid(acceptanceCertificate.getId());
                acceptanceCertificate.setAcceptanceCertificatePatentList(acceptanceCertificatePatentList);  //把专利表的信息插入到最终验收报告中

            } catch (NullPointerException e) {
                acceptanceCertificatePatentList = null;
            }
            //根据最终验收报告的id，查询验收报告中的主要参与人员信息
            List<AcceptanceCertificatePrincipalPersonnel>  acceptanceCertificatePrincipalPersonnelList = null;
            try {
                acceptanceCertificatePrincipalPersonnelList = acceptApplyMapper.queryAcceptanceCertificatePersonnel(acceptanceCertificate.getId());
                acceptanceCertificate.setAcceptanceCertificatePrincipalPersonnelList(acceptanceCertificatePrincipalPersonnelList);//把查询出来的主要参与人员信息插入到最终验收报告中
            } catch (NullPointerException e) {
                acceptanceCertificatePrincipalPersonnelList = null;
            }
            //根据验收报告的id，查询出验收报告中的课题负责人信息
            List<AcceptanceCertificateSubjectPeople> acceptanceCertificateSubjectPeopleList = null;
            try {
                acceptanceCertificateSubjectPeopleList = acceptApplyMapper.queryAcceptanceCertificateSubjectPeople(acceptanceCertificate.getId());
                acceptanceCertificate.setAcceptanceCertificateSubjectPeopleList(acceptanceCertificateSubjectPeopleList);
            } catch (NullPointerException e) {
                acceptanceCertificateSubjectPeopleList = null;
            }
            //把最终验收报告信息存入验收申请表中
            extranetCheckApply.setAcceptanceCertificate(acceptanceCertificate);


            Integer id = extranetCheckApply.getId();   //获取验收申请表的id
            //通过验收申请表的id，获取到对应的审核状态
            List<ExtranetCheckApplyState> extranetCheckApplyStateList = acceptApplyMapper.queryCheckApplyState(id);
            //把验收申请表的内容存放到checkApply中
            extranetCheckApply.setExtranetCheckApplyStateList(extranetCheckApplyStateList);

            //通过验收状态的id，查询出验收审核的状态
            String acceotancePhaseName = acceptApplyMapper.queryAcceptancePhaseName(extranetCheckApply.getAcceptancePhaseId());
            extranetCheckApply.setAcceptancePhaseName(acceotancePhaseName);

            //根据验收申请表的id，查询出验收申请表的Url
            String applicationFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getApplicationUrlId());
            extranetCheckApply.setApplicationAcceptanceUrl(applicationFileUrl);
            //获取验收申请表Url的名称
            String applicationFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getApplicationUrlId());
            extranetCheckApply.setApplicationAcceptanceUrlName(applicationFileName);

            //获取成果附件的URL 与 名称
            String achievementFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getAchievementUrlId());
            extranetCheckApply.setAchievementsUrl(achievementFileUrl);
            //获取成果附件Url的名称
            String achievementName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getAchievementUrlId());
            extranetCheckApply.setAchievementsName(achievementName);

            //获取提交清单的URL 与 名称
            String submitFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getSubmitUrlId());
            extranetCheckApply.setSubmitInventoryUrl(submitFileUrl);
            //获取提交清单Url的名称
            String submitFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getSubmitUrlId());
            extranetCheckApply.setSubmitInventoryUrlName(submitFileName);

            //获取审计报告的Url 与 名称
            String auditReportFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getAuditReportUrlId());
            extranetCheckApply.setAuditReportUrl(auditReportFileUrl);
            //获取审计报告Url的名称
            String auditReportFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getAuditReportUrlId());
            extranetCheckApply.setAuditReportUrlName(auditReportFileName);

            //获取初审报告的Url 与 名称
            String firstInspectionReportFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getFirstInspectionReportUrlId());
            extranetCheckApply.setFirstInspectionReportUrl(firstInspectionReportFileUrl);
            //获取初审报告Url的名称
            String firstInspectionReportFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getFirstInspectionReportUrlId());
            extranetCheckApply.setFirstInspectionReportUrlName(firstInspectionReportFileName);

            //获取专家组意见的Url与名称
            String expertGroupCommentsFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getExpertGroupCommentsUrlId());
            extranetCheckApply.setExpertGroupCommentsUrl(expertGroupCommentsFileUrl);
            //获取专家组意见Url的名称
            String expertGroupCommentsFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getExpertGroupCommentsUrlId());
            extranetCheckApply.setExpertGroupCommentsUrlName(expertGroupCommentsFileName);

            //获取专家组评议表的Url与名称
            String expertAcceptanceFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getExpertAcceptanceFormId());
            extranetCheckApply.setExpertAcceptanceFormUrl(expertAcceptanceFileUrl);
            //获取专家组评议表Url的名称
            String expertAcceptanceFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getExpertAcceptanceFormId());
            extranetCheckApply.setExpertAcceptanceFormUrlName(expertAcceptanceFileName);

            //获取最终验收证书的URL与名称
            String acceptanceCertificateFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getAcceptanceCertificateId());
            extranetCheckApply.setAcceptanceCertificateUrl(acceptanceCertificateFileUrl);
            //获取最终验收证书Url的名称
            String acceptanceCertificateFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getAcceptanceCertificateId());
            extranetCheckApply.setAcceptanceCertificateUrlName(acceptanceCertificateFileName);
        }

        PageBean pageBean = new PageBean();
        pageBean.setCount(alltotal);
        pageBean.setData(extranetCheckApplyList);
        return resultMap.success().message(pageBean);
    }

    //提交最终验收报告
    @Override
    public ResultMap submitLastReport(String token, HttpServletResponse response, Integer caId, MultipartFile lastReport, AcceptanceCertificate acceptanceCertificate) throws Exception {
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

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();

        //判断验收证书后缀名是否正确
        List<String> acceptanceCertificateSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z"));
        String lastReportFilename = lastReport.getOriginalFilename();
        Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(lastReportFilename, acceptanceCertificateSuffixList);
        if (aBoolean == false) {
            return resultMap.fail().message("请上传正确的验收证书格式");
        }

        //对验收证书进行文件上传
        String lastReportFileUrl = FileUploadUtil.fileUpload(lastReport, cname, "验收证书");
        //把验收证书上传到upload_file中
        UploadFile uploadLastReportFile = IntegrationFile.IntegrationFile(lastReport, lastReportFileUrl, "验收证书", uname);
        acceptApplyMapper.uploadFile(uploadLastReportFile);//对文件进行上传
        //根据验收申请表的id，新增最终验收报告的id
        acceptApplyMapper.updateAcceptanceFinalResultIdById(caId, uploadLastReportFile.getId());
        //修改验收证书的状态
        acceptApplyMapper.updateAcceptancePhaseById(cid, 7);

        //新增最终验收报告表单
        acceptanceCertificate.setCid(caId);
        //新增最终验收报告的主表
        acceptApplyMapper.addAcceptanceCertificate(acceptanceCertificate);
        //新增最终验收报告的专利表
        List<AcceptanceCertificatePatent> acceptanceCertificatePatentList = acceptanceCertificate.getAcceptanceCertificatePatentList();
        for (AcceptanceCertificatePatent acceptanceCertificatePatent : acceptanceCertificatePatentList) {
            acceptanceCertificatePatent.setAcceptanceCertificateId(acceptanceCertificate.getId());
            acceptApplyMapper.addAcceptanceCertificatePatent(acceptanceCertificatePatent);
        }

        //新增最终验收报告的主要参加人员
        List<AcceptanceCertificatePrincipalPersonnel> acceptanceCertificatePrincipalPersonnelList = acceptanceCertificate.getAcceptanceCertificatePrincipalPersonnelList();
        for (AcceptanceCertificatePrincipalPersonnel acceptanceCertificatePrincipalPersonnel : acceptanceCertificatePrincipalPersonnelList) {
            acceptanceCertificatePrincipalPersonnel.setAcceptanceCertificateId(acceptanceCertificate.getId());
            acceptApplyMapper.addAcceptanceCertificatePrincipalPersonnel(acceptanceCertificatePrincipalPersonnel);
        }

        //新增验收证书的课题负责人
        List<AcceptanceCertificateSubjectPeople> acceptanceCertificateSubjectPeopleList = acceptanceCertificate.getAcceptanceCertificateSubjectPeopleList();
        for (AcceptanceCertificateSubjectPeople acceptanceCertificateSubjectPeople : acceptanceCertificateSubjectPeopleList) {
            acceptanceCertificateSubjectPeople.setAcceptanceCertificateId(acceptanceCertificate.getId());
            acceptApplyMapper.addAcceptanceCertificateSubjectPeople(acceptanceCertificateSubjectPeople);
        }


        //把验收申请的状态表进行修改
        //首先更新上一条表的状态
        String state = "已处理";
        String handleContent = "审核通过";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        //根据数据的id，把处理人，审核状态，审核内容，处理时间更新
        acceptApplyMapper.updateCheckApplyState(cid, uname, state, handleContent, nowTime);

        //新增下一条状态数据
        String auditStep = "审核最终验收报告";
        String newState = "等待审核";
        acceptApplyMapper.addNewCheckApplyState(cid, uname, auditStep, nowTime, newState);

        return resultMap.success().message("提交成功");
    }

    //上传专家组意见信息 与专家组意见文件与专家组评议表文件
    @Override
    public ResultMap submitExpertGroup(String token, HttpServletResponse response, Integer caId, ExtranetExpertGroupComment extranetExpertGroupComment, MultipartFile expertGroupCommentsFile, MultipartFile expertAcceptanceFormFile) throws Exception {
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

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();


        //判断专家组文件类型是否正确
        ArrayList<String> expertGroupCommentsFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z"));
        String expertGroupCommentsFileName = expertGroupCommentsFile.getOriginalFilename();
        Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(expertGroupCommentsFileName, expertGroupCommentsFileSuffixList);
        if (aBoolean == false) {
            return resultMap.fail().message("请上传正确的专家组意见文件格式");
        }

        //判断专家组评议表文件是否正确
        ArrayList<String> expertAcceptanceFormFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z"));
        String expertAcceptanceFormFilename = expertAcceptanceFormFile.getOriginalFilename();
        Boolean bBoolean = FileSuffixJudgeUtil.SuffixJudge(expertAcceptanceFormFilename, expertAcceptanceFormFileSuffixList);
        if (bBoolean == false) {
            return resultMap.fail().message("请上传正确的验收证书格式");
        }

        //对专家组意见文件进行上传
        String expertGroupCommentsFileUrl = FileUploadUtil.fileUpload(expertGroupCommentsFile, cname, "专家组意见");
        //把专家组意见上传到upload_file中
        UploadFile uploadExpertGroupCommentsFile = IntegrationFile.IntegrationFile(expertGroupCommentsFile, expertGroupCommentsFileUrl, "专家组意见", uname);
        acceptApplyMapper.uploadFile(uploadExpertGroupCommentsFile);//对文件进行上传
        //根据专家组意见文件的id，新增最终验收报告中专家组意见的id
        acceptApplyMapper.updateExpertGroupFileId(caId, uploadExpertGroupCommentsFile.getId());

        //对专家组评议表文件进行上传
        String expertAcceptanceFormFileUrl = FileUploadUtil.fileUpload(expertAcceptanceFormFile, cname, "专家组评议");
        //把专家组评议上传到upload_file中
        UploadFile uploadExpertAcceptanceFormFileUrl = IntegrationFile.IntegrationFile(expertAcceptanceFormFile, expertAcceptanceFormFileUrl, "专家组评议", uname);
        acceptApplyMapper.uploadFile(uploadExpertAcceptanceFormFileUrl);//对文件进行上传
        //根据专家组评议文件的id，新增最终验收报告中专家组评议的id
        acceptApplyMapper.updateExpertAcceptanceFormFileId(caId, uploadExpertAcceptanceFormFileUrl.getId());

        extranetExpertGroupComment.setCreateAuthor(uname);  //存入创建人名
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        extranetExpertGroupComment.setCreateTime(nowTime);//存入创建时间

        //把专家组主表信息存储到数据库中
        acceptApplyMapper.addExpertGroupComment(extranetExpertGroupComment);

        //把专家组从表存储到数据库中
        List<ExtranetExpertGroupCommentsName> extranetExpertGroupCommentsNameList = extranetExpertGroupComment.getExtranetExpertGroupCommentsNameList();
        for (ExtranetExpertGroupCommentsName extranetExpertGroupCommentsName : extranetExpertGroupCommentsNameList) {
            acceptApplyMapper.addExpertGroupCommentName(extranetExpertGroupComment.getEgcId(), extranetExpertGroupCommentsName);
        }

        //更新验收申请的状态表

        //首先更新上一条表的状态
        String state = "已处理";
        String handleContent = "审核通过";
        //根据数据的id，把处理人，审核状态，审核内容，处理时间更新
        acceptApplyMapper.updateCheckApplyState(caId, uname, state, handleContent, nowTime);

        //新增下一条状态数据
        String auditStep = "等待审核公司上传的专家文件";
        String newState = "等待审核";
        acceptApplyMapper.addNewCheckApplyState(caId, uname, auditStep, nowTime, newState);

        return resultMap.success().message("提交成功");
    }

    //验收申请的修改
    @Override
    public ResultMap modifyApply(String token, HttpServletResponse response, String oldSubmitInventoryFileUrl, String oldAchievementsFileUrl, String oldApplicationAcceptanceFileUrl, MultipartFile submitInventoryFile, MultipartFile applicationAcceptanceFile, MultipartFile achievementsFile, ExtranetCheckApply extranetCheckApply) throws Exception {
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

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();


        //判断三个旧文件是否为空
        if (oldSubmitInventoryFileUrl != null) {
            //提交清单不为空
            //判断文件输入的格式是否正确
            ArrayList<String> idCardFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z", ".pdf"));
            String submitInventoryFileName = submitInventoryFile.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(submitInventoryFileName, idCardFileSuffixList);
            if (aBoolean == false) {
                return resultMap.fail().message("请上传正确的提交清单格式");
            }
            //再根据旧的文件地址，先把文件给删除掉
            File file = new File(oldSubmitInventoryFileUrl);
            file.delete();

            //对新的提交清单进行上传
            String submitInventoryFileUrl = FileUploadUtil.fileUpload(submitInventoryFile, cname, "提交清单");
            //把提交清单文件上传到upload_file中
            UploadFile uploadSubmitInventoryFile = IntegrationFile.IntegrationFile(submitInventoryFile, submitInventoryFileUrl, "提交清单", uname);
            acceptApplyMapper.uploadFile(uploadSubmitInventoryFile);//对文件进行上传
            //对旧的提交清单文件id进行更新
            acceptApplyMapper.updateSubmitInventoryIdById(extranetCheckApply.getId(), uploadSubmitInventoryFile.getId());

            //把上传文件的id，存入checkApply中
            extranetCheckApply.setSubmitUrlId(uploadSubmitInventoryFile.getId());
        }

        if (oldAchievementsFileUrl != null) {
            //旧的成果附件不为null时
            //判断文件输入的格式是否正确
            ArrayList<String> idCardFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z", ".pdf"));
            String achievementsFileName = achievementsFile.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(achievementsFileName, idCardFileSuffixList);
            if (aBoolean == false) {
                return resultMap.fail().message("请上传正确的成果附件格式");
            }
            //再根据旧的文件地址，先把文件给删除掉
            File file = new File(oldAchievementsFileUrl);
            file.delete();

            //对新的提交清单进行上传
            String achievementsFileUrl = FileUploadUtil.fileUpload(achievementsFile, cname, "成果附件");
            //把提交清单文件上传到upload_file中
            UploadFile uploadAchievementsFile = IntegrationFile.IntegrationFile(achievementsFile, achievementsFileUrl, "成果附件", uname);
            acceptApplyMapper.uploadFile(uploadAchievementsFile);//对文件进行上传
            //对旧的成果附件文件文件id进行更新
            acceptApplyMapper.updateAchievementIdById(extranetCheckApply.getId(), uploadAchievementsFile.getId());

            //把上传文件的id，存入checkApply中
            extranetCheckApply.setAchievementUrlId(uploadAchievementsFile.getId());
        }

        if (oldApplicationAcceptanceFileUrl != null) {
            //旧的验收申请不为null时
            ArrayList<String> idCardFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z", ".pdf"));
            String applicationAcceptanceFileName = applicationAcceptanceFile.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(applicationAcceptanceFileName, idCardFileSuffixList);
            if (aBoolean == false) {
                return resultMap.fail().message("请上传正确的验收申请表格式");
            }
            //再根据旧的文件地址，先把文件给删除掉
            File file = new File(oldApplicationAcceptanceFileUrl);
            file.delete();

            //对新的验收申请表进行上传
            String applicationAcceptanceFileUrl = FileUploadUtil.fileUpload(applicationAcceptanceFile, cname, "验收申请表");
            //把验收申请表文件上传到upload_file中
            UploadFile uploadApplicationAcceptanceFile = IntegrationFile.IntegrationFile(applicationAcceptanceFile, applicationAcceptanceFileUrl, "验收申请表", uname);
            acceptApplyMapper.uploadFile(uploadApplicationAcceptanceFile);//对文件进行上传

            //对旧的验收申请表文件文件id进行更新
            acceptApplyMapper.updateApplicationAcceptanceIdById(extranetCheckApply.getId(), uploadApplicationAcceptanceFile.getId());

            //把上传文件的id，存入checkApply中
            extranetCheckApply.setAchievementUrlId(uploadApplicationAcceptanceFile.getId());
        }

        acceptApplyMapper.updateCheckApply(extranetCheckApply);
        return resultMap.fail().message("修改成功");
    }

    //对专家组信息，专家组文件，专家组评议表文件进行修改
    @Override
    public ResultMap expertGroupModify(String token, HttpServletResponse response, ExtranetExpertGroupComment extranetExpertGroupComment, Integer caId, String oldExpertGroupFileUrl, String oldExpertAcceptanceFormFile, MultipartFile expertGroupFile, MultipartFile expertAcceptanceFormFile) throws Exception {
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

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();

        if (expertGroupFile != null) {
            //此时专家组意见文件不为空，则意味着上传了新的专家组意见
            //判断上传的专家组意见文件是否后缀名正确

            ArrayList<String> idCardFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z"));
            String expertGroupFileName = expertGroupFile.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(expertGroupFileName, idCardFileSuffixList);
            if (aBoolean == false) {
                return resultMap.fail().message("请上传正确的专家组文件格式");
            }
            //再根据旧的文件地址，先把文件给删除掉
            File file = new File(oldExpertGroupFileUrl);
            file.delete();
        }

        if (expertAcceptanceFormFile != null) {
            //此时专家组意见文件不为空，则意味着上传了新的专家组意见
            //判断上传的专家组意见文件是否后缀名正确

            ArrayList<String> idCardFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z"));
            String expertAcceptanceFormFileName = expertAcceptanceFormFile.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(expertAcceptanceFormFileName, idCardFileSuffixList);
            if (aBoolean == false) {
                return resultMap.fail().message("请上传正确的专家组评议文件格式");
            }
            //再根据旧的文件地址，先把文件给删除掉
            File file = new File(oldExpertAcceptanceFormFile);
            file.delete();
        }


        if (oldExpertGroupFileUrl != null) {
            //此时旧的专家组意见文件地址存在

            //对新的专家组文件进行上传
            String applicationAcceptanceFileUrl = FileUploadUtil.fileUpload(expertGroupFile, cname, "专家组文件");
            //把专家组意见文件上传到upload_file中
            UploadFile uploadExpertGroupFile = IntegrationFile.IntegrationFile(expertGroupFile, applicationAcceptanceFileUrl, "专家组文件", uname);
            acceptApplyMapper.uploadFile(uploadExpertGroupFile);//对文件进行上传

            //对旧的专家组意见文件文件id进行更新
            acceptApplyMapper.updateExpertGroupFileIdById(caId, uploadExpertGroupFile.getId());
        }

        if (oldExpertAcceptanceFormFile != null) {
            //此时旧的专家评议表文件地址存在

            //对新的专家组文件进行上传
            String expertAcceptanceFormFileUrl = FileUploadUtil.fileUpload(expertAcceptanceFormFile, cname, "专家组评议文件");
            //把专家组意见文件上传到upload_file中
            UploadFile uploadExpertAcceptanceFormFile = IntegrationFile.IntegrationFile(expertAcceptanceFormFile, expertAcceptanceFormFileUrl, "专家组评议文件", uname);
            acceptApplyMapper.uploadFile(uploadExpertAcceptanceFormFile);//对文件进行上传

            //对旧的专家组评议文件文件id进行更新
            acceptApplyMapper.updateExpertAcceptanceFormFileIdById(caId, uploadExpertAcceptanceFormFile.getId());
        }
        //对专家组意见主表进行更新
        acceptApplyMapper.updateExpertGroupByCaId(caId, extranetExpertGroupComment);

        //对专家组从表进行更新
        //对专家组意见从表旧的内容首先进行删除
        acceptApplyMapper.deleteExpertGroupCommentsNameById(extranetExpertGroupComment.getEgcId());

        List<ExtranetExpertGroupCommentsName> extranetExpertGroupCommentsNameList = extranetExpertGroupComment.getExtranetExpertGroupCommentsNameList();
        for (ExtranetExpertGroupCommentsName extranetExpertGroupCommentsName : extranetExpertGroupCommentsNameList) {
            acceptApplyMapper.addExpertGroupCommentName(extranetExpertGroupComment.getEgcId(), extranetExpertGroupCommentsName);
        }
        return resultMap.success().message("更新成功");
    }

    //对最终证书文件 与信息 修改
    @Override
    public ResultMap lastReportModify(String token, HttpServletResponse response, Integer caId, MultipartFile lastReportFile, String oldLastReportFileUrl, AcceptanceCertificate acceptanceCertificate) throws Exception {
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

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();

        if (lastReportFile != null) {
            //此时专家组意见文件不为空，则意味着上传了新的专家组意见
            //判断上传的专家组意见文件是否后缀名正确

            ArrayList<String> idCardFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z"));
            String lastReportFileName = lastReportFile.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(lastReportFileName, idCardFileSuffixList);
            if (aBoolean == false) {
                return resultMap.fail().message("请上传正确的最终验收证书文件格式");
            }
            //再根据旧的文件地址，先把文件给删除掉
            File file = new File(oldLastReportFileUrl);
            file.delete();
        }

        if (oldLastReportFileUrl != null) {
            //此时旧的最终验收文件地址存在

            //对新的最终验收文件进行上传
            String lastReportFileUrl = FileUploadUtil.fileUpload(lastReportFile, cname, "最终验收证书文件");
            //把最终验收文件上传到upload_file中
            UploadFile uploadLastReportFile = IntegrationFile.IntegrationFile(lastReportFile, lastReportFileUrl, "最终验收证书文件", uname);
            acceptApplyMapper.uploadFile(uploadLastReportFile);//对文件进行上传

            //对旧的最终验收证书文件id进行更新
            acceptApplyMapper.uploadLastReportFileIdById(caId, uploadLastReportFile.getId());
        }

        //对验收证书报告信息的主表进行更新
        acceptApplyMapper.UpdateLastReportFile(caId, acceptanceCertificate);

        //把验收证书中专利表对应的信息删除
        acceptApplyMapper.deleteAcceptanceCertificatePatent(caId);
        //把验证证书中专利表的信息新增进去
        List<AcceptanceCertificatePatent> acceptanceCertificatePatentList = acceptanceCertificate.getAcceptanceCertificatePatentList();
        for (AcceptanceCertificatePatent acceptanceCertificatePatent : acceptanceCertificatePatentList) {
            acceptanceCertificatePatent.setAcceptanceCertificateId(acceptanceCertificate.getCid());
            acceptApplyMapper.addAcceptanceCertificatePatent(acceptanceCertificatePatent);
        }

        //把验收证书中主要参与人员删除
        acceptApplyMapper.deleteAcceptanceCertificatePrincipalPersonnel(caId);
        //新增最终验收报告的主要参加人员
        List<AcceptanceCertificatePrincipalPersonnel> acceptanceCertificatePrincipalPersonnelList = acceptanceCertificate.getAcceptanceCertificatePrincipalPersonnelList();
        for (AcceptanceCertificatePrincipalPersonnel acceptanceCertificatePrincipalPersonnel : acceptanceCertificatePrincipalPersonnelList) {
            acceptanceCertificatePrincipalPersonnel.setAcceptanceCertificateId(acceptanceCertificate.getCid());
            acceptApplyMapper.addAcceptanceCertificatePrincipalPersonnel(acceptanceCertificatePrincipalPersonnel);
        }

        //把验收证书中的课题负责人删除
        acceptApplyMapper.deleteAcceptanceCertificateSubjectPeople(caId);

        //新增验收证书的课题负责人
        List<AcceptanceCertificateSubjectPeople> acceptanceCertificateSubjectPeopleList = acceptanceCertificate.getAcceptanceCertificateSubjectPeopleList();
        for (AcceptanceCertificateSubjectPeople acceptanceCertificateSubjectPeople : acceptanceCertificateSubjectPeopleList) {
            acceptanceCertificateSubjectPeople.setAcceptanceCertificateId(acceptanceCertificate.getCid());
            acceptApplyMapper.addAcceptanceCertificateSubjectPeople(acceptanceCertificateSubjectPeople);
        }

        return resultMap.success().message("更新成功");
    }

    //查询可进行新增验收申请的合同
    @Override
    public ResultMap queryTopicName(String token, HttpServletResponse response) throws ParseException {
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

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = sdf.format(date);

        //首先获取该公司所有已经结束的合同id
        List<Integer> ids = acceptApplyMapper.queryAllEndContractId(nowTime,cname);

        //存放符合条件的id集合
        List<Integer> resultIds = new ArrayList<>();

        for (Integer id : ids) {
            //根据id获取合同的结束时间
            String contractEndTime = acceptApplyMapper.queryEndTimeById(id);
            //把日期字符串进行Date
            Date sqlTimeParse = sdf.parse(contractEndTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sqlTimeParse);
            cal.add(Calendar.MONTH, 3);  //对月份加3
            String dateOver = sdf.format(cal.getTime());

            if(sdf.parse(dateOver).getTime()>sdf.parse(nowTime).getTime()){
                //此时该合同符合要求
                resultIds.add(id);
            }
        }
        List<SubjectNameAndId> subjectNameAndIdList = new ArrayList<>();

        //遍历符合要求的id，获取课题名称
        for (Integer resultId : resultIds) {
            SubjectNameAndId subjectNameAndId = new SubjectNameAndId();
            //通过id，获取课题名称
            String subjectName = acceptApplyMapper.querySubjectNameById(resultId);
            subjectNameAndId.setId(resultId);
            subjectNameAndId.setSubjectName(subjectName);
            subjectNameAndIdList.add(subjectNameAndId);
        }
        return resultMap.success().message(subjectNameAndIdList);
    }

    //通过课题名称获取合同id
    @Override
    public ResultMap queryTopicNumber(String projectName) throws ParseException {

        //通过课题名称获取合同id集合
        List<Integer> ids = acceptApplyMapper.queryIdByProjectName(projectName);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String nowTime = sdf.format(date);

        //存放符合条件的id集合
        List<Integer> resultIds = new ArrayList<>();

        for (Integer id : ids) {
            //根据id获取合同的结束时间
            String contractEndTime = acceptApplyMapper.queryEndTimeById(id);
            //把日期字符串进行Date
            Date sqlTimeParse = sdf.parse(contractEndTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sqlTimeParse);
            cal.add(Calendar.MONTH, 3);  //对月份加3
            String dateOver = sdf.format(cal.getTime());

            if(sdf.parse(dateOver).getTime()>sdf.parse(nowTime).getTime()){
                //此时该合同符合要求
                resultIds.add(id);
            }
        }

        List<String> topicNumberList = new ArrayList<>();
        for (Integer resultId : resultIds) {
            String topicNumber = acceptApplyMapper.queryTopicNumberById(resultId);  //通过id获取课题编号
            topicNumberList.add(topicNumber);
        }

        return resultMap.success().message(topicNumberList);
    }

    //验收申请中，根据课题编号获取信息
    @Override
    public ResultMap queryInformationByTopicNumber(String projectNumber) {
        //根据课题编号获取承担单位
        String companyName = acceptApplyMapper.queryCompanyNameByProjectNumber(projectNumber);
        //通过承担单位名称获取单位性质
        Integer unitNature = acceptApplyMapper.queryUnitNatureByCompanyName(companyName);
        //获取课题负责人
        String projectLeader = acceptApplyMapper.querySubjectLeader(projectNumber);
        //获取课题负责人联系电话
        String projectLeaderPhone = acceptApplyMapper.querySubjectLeaderPhone(projectNumber);
        //获取合同开始时间
        String contractStartTime = acceptApplyMapper.queryContractStartTime(projectNumber);
        //获取合同结束时间
        String contractEndTime = acceptApplyMapper.queryContractEndTime(projectNumber);

        HashMap<String, Object> result = new HashMap<>();
        result.put("subjectUndertakingUnit",companyName);
        result.put("unitNature",unitNature);
        result.put("projectLeader",projectLeader);
        result.put("projectLeaderPhone",projectLeaderPhone);
        result.put("agreementStartTime",contractStartTime);
        result.put("agreementEndTime",contractEndTime);

        return resultMap.success().message(result);
    }
}
