package com.xdmd.IntranetEnvironment.achievementManagement.service.impl;

import com.xdmd.IntranetEnvironment.achievementManagement.controller.AchievementController;
import com.xdmd.IntranetEnvironment.achievementManagement.mapper.AchievementMapper;
import com.xdmd.IntranetEnvironment.achievementManagement.pojo.*;
import com.xdmd.IntranetEnvironment.achievementManagement.service.AchievementService;
import com.xdmd.IntranetEnvironment.common.*;
import com.xdmd.IntranetEnvironment.common.PageBean;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.*;
import com.xdmd.IntranetEnvironment.subjectAcceptance.mapper.AcceptApplyMapper;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApplyState;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.ExpertGroupComment;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.ExpertGroupCommentsName;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    private AchievementMapper achievementMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AcceptApplyMapper acceptApplyMapper;

    ResultMap resultMap = new ResultMap();
    PageBean pageBean = new PageBean();
    private static Logger log = LoggerFactory.getLogger(AchievementController.class);

    //成果的查询，可以查询所有已经加入成果库的信息
    @Override
    public ResultMap queryAchivement(String topicName, String companyName, Integer page, Integer total) {
        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }

        //查询成果的总数
        int alltotal = 0;
        alltotal = achievementMapper.queryAllAchievement(topicName, companyName);
        if (alltotal == 0) {
            return resultMap.fail().message();
        }

        //判断用户输入的页数是否超过总页数
        int allPage = 0;
        if (alltotal % page == 0) {
            allPage = alltotal / page;
        } else {
            allPage = (alltotal / page) + 1;
        }
        if (page > allPage) {
            return resultMap.fail().message("页数超过总页数");
        }

        //获取成果主表的集合
        List<OutcomeInformationAll> outcomeInformationAllList = achievementMapper.queryAchievementList(topicName, companyName, newpage, total);

        //遍历成果主表的集合
        for (OutcomeInformationAll outcomeInformationAll : outcomeInformationAllList) {
            //获取成果的id
            Integer id = outcomeInformationAll.getId();
            String achievementUrl = achievementMapper.queryAchievementFileNameByAchievementId(id);//获取成果表中 成果附件的id， 通过成果附件的id，查询到成果附件的地址
            outcomeInformationAll.setAchievementUrl(achievementUrl);

            //根据成果附件的id，获取成果附件的真实名字
            String fileName = achievementMapper.queryOutcomeInformationById(id);
            outcomeInformationAll.setAchievementName(fileName);

            List<OutcomeInformationPatent> outcomeInformationPatentList = achievementMapper.queryAchievementPatentByOid(id);//根据成果主表的id，获取对应的专利表
            outcomeInformationAll.setOutcomeInformationPatentList(outcomeInformationPatentList);

            List<OutcomeInformationPaper> outcomeInformationPaperList = achievementMapper.queryAchievementPaperByOid(id);//根据成果主表的id，获取对应的论文表
            outcomeInformationAll.setOutcomeInformationPaperList(outcomeInformationPaperList);
        }
        pageBean.setAlltotal(alltotal);
        pageBean.setData(outcomeInformationAllList);

        return resultMap.success().message(pageBean);
    }

    //此时查询的是，通过验收与结题的 待加入成果库的内容信息
    @Override
    public ResultMap queryAddAchivement(String topicName, String companyName, Integer page, Integer total) {

        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }

        //查询待加入成果的信息总数 通过验收与结题的
        int alltotal = 0;
        alltotal = achievementMapper.queryAddAchievement(topicName, companyName);
        if (alltotal == 0) {
            return resultMap.fail().message();
        }

        //获取已经通过验收或结题的  等待加入成果库的内容
        List<TopicNumberName> topicNumberNameList = achievementMapper.queryAddChievement(topicName, companyName, newpage, total);
        for (TopicNumberName topicNumberName : topicNumberNameList) {
            //遍历待加入成果库的信息
            String achievementFileUrl = achievementMapper.queryAchievementFileUrlById(topicNumberName.getId());//根据验收表的id，查询改验收表对应的成果附件地址
            topicNumberName.setAchievementFileUrl(achievementFileUrl);
            String achievementFileName = achievementMapper.queryAchievementFileNameById(topicNumberName.getId());//根据验收表的id，查询改验收表对应的成果附件真实名字
            topicNumberName.setFileName(achievementFileName);
        }
        pageBean.setAlltotal(alltotal);
        pageBean.setData(topicNumberNameList);
        return resultMap.success().message(pageBean);
    }

    //成果新增的保存
    @Override
    public ResultMap AddAchievementSave(String token, HttpServletResponse response, String cid, MultipartFile achievementFileUrl, OutcomeInformationAll outcomeInformationAll) {
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

        Integer uid = user.getId();
        String username = user.getUsername();


        //判断保存，是第一次保存还是第多次保存   第一次保存的话，是新增操作，  多次保存的话，是更新操作
        Integer saveStateId = null;
        //通过uid cid找到对应的新增成果时，保存的数据，如果能找到，意味着这条数据被这个人保存过了，这次第多次操作，是更新操作，
        //如果找不到，则意味着这条操作是第一次操作是新增操作
        saveStateId = achievementMapper.querySaveState(uid, cid);

        if (saveStateId != null) {
            //此时可以找到，意味着，这条数据被这个用户已经保存过的，接下来是进行更新操作
            //把创建时间保存到字段中
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowDate = sdf.format(date);

            //更新成果主表
            achievementMapper.UpdateAchievementInformation(outcomeInformationAll,cid,uid);

            //更新成果表中的论文表
            List<OutcomeInformationPaper> outcomeInformationPaperList = outcomeInformationAll.getOutcomeInformationPaperList();
            for (OutcomeInformationPaper outcomeInformationPaper : outcomeInformationPaperList) {
                outcomeInformationPaper.setAchievementsId(outcomeInformationAll.getId());
                achievementMapper.UpdateAchievementInformationPaper(outcomeInformationPaper);
            }

            //更新成果表中的专利表
            List<OutcomeInformationPatent> outcomeInformationPatentList = outcomeInformationAll.getOutcomeInformationPatentList();
            for (OutcomeInformationPatent outcomeInformationPatent : outcomeInformationPatentList) {
                outcomeInformationPatent.setAchievementsId(outcomeInformationAll.getId());
                achievementMapper.UpdateAchievementInformationPatent(outcomeInformationPatent);
            }
            return resultMap.success().message("保存成功");
        }


        //把保存的成果信息保存到数据库中
        outcomeInformationAll.setCreateAuthor(username);    //把创建人保存到字段中

        //把创建时间保存到字段中
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = sdf.format(date);
        outcomeInformationAll.setCreateTime(nowDate);

        outcomeInformationAll.setCheckApplyId(cid);

        achievementMapper.addAchievementInformation(outcomeInformationAll);     //新增成果主表

        //新增成果表中的论文表
        List<OutcomeInformationPaper> outcomeInformationPaperList = outcomeInformationAll.getOutcomeInformationPaperList();
        for (OutcomeInformationPaper outcomeInformationPaper : outcomeInformationPaperList) {
            outcomeInformationPaper.setAchievementsId(outcomeInformationAll.getId());
            achievementMapper.addAchievementInformationPaper(outcomeInformationPaper);
        }

        //新增成果表中的专利表
        List<OutcomeInformationPatent> outcomeInformationPatentList = outcomeInformationAll.getOutcomeInformationPatentList();
        for (OutcomeInformationPatent outcomeInformationPatent : outcomeInformationPatentList) {
            outcomeInformationPatent.setAchievementsId(outcomeInformationAll.getId());
            achievementMapper.addAchievementInformationPatent(outcomeInformationPatent);
        }

        return resultMap.success().message("保存成功");
    }

    //成果新增的提交
    @Override
    public ResultMap AddAchievement(String token, HttpServletResponse response, String cid, MultipartFile achievementFile, OutcomeInformationAll outcomeInformationAll) {
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

        Integer uid = user.getId();
        String username = user.getUsername();

        //判断上传的文件后缀名是否正确
//        List<String> achievementSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".zip", ".rar", ".7z"));
//        //获取文件名
//        String achievementFileName = achievementFile.getOriginalFilename();
//        Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(achievementFileName, achievementSuffixList);
//        if (aBoolean == false) {
//            return resultMap.fail().message("请上传正确的成果信息文件格式");
//        }

        //对成果信息文件进行上传
        try {
            String achievementFileUrl = FileUploadUtil.UploadExpertInformationFile(achievementFile, "成果信息文件");
            //把成果信息文件上传到upload_file中
            UploadFile uploadAchievementFile = IntegrationFile.IntegrationFile(achievementFile, achievementFileUrl, "成果信息文件", username);
            achievementMapper.uploadFile(uploadAchievementFile);   //对成果信息进行上传
            outcomeInformationAll.setAchievementUrlId(String.valueOf(uploadAchievementFile.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AchievementServiceImpl 中  AddAchievement 方法 成果信息文件上传失败");
            return resultMap.fail().message("系统异常");
        }

        //把保存的成果信息保存到数据库中
        outcomeInformationAll.setCreateAuthor(username);    //把创建人保存到字段中

        //把创建时间保存到字段中
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = sdf.format(date);
        outcomeInformationAll.setCreateTime(nowDate);

        outcomeInformationAll.setCheckApplyId(cid);

        //把验收申请表中的is_outcome 字段设置为1
        achievementMapper.setCheckApplyIsOutomt(cid);

        achievementMapper.addAchievementInformation(outcomeInformationAll);     //新增成果主表

        //新增成果表中的论文表
        List<OutcomeInformationPaper> outcomeInformationPaperList = outcomeInformationAll.getOutcomeInformationPaperList();
        for (OutcomeInformationPaper outcomeInformationPaper : outcomeInformationPaperList) {
            outcomeInformationPaper.setAchievementsId(outcomeInformationAll.getId());
            achievementMapper.addAchievementInformationPaper(outcomeInformationPaper);
        }

        //新增成果表中的专利表
        List<OutcomeInformationPatent> outcomeInformationPatentList = outcomeInformationAll.getOutcomeInformationPatentList();
        for (OutcomeInformationPatent outcomeInformationPatent : outcomeInformationPatentList) {
            outcomeInformationPatent.setAchievementsId(outcomeInformationAll.getId());
            achievementMapper.addAchievementInformationPatent(outcomeInformationPatent);
        }

        return resultMap.success().message("提交成功");
    }


    /**
     * 根据cid获取验收申请表所有内容
     * @param cid
     * @return
     */
    @Override
    public ResultMap queryAllCheckApply(String cid) {
        //获取验收申请表Id
        Integer applicationUrlId = Integer.parseInt(cid);

        //根据验收申请表的id，获取验收申请表的信息
        CheckApply checkApply = acceptApplyMapper.queryCheckApply(cid);

        Integer applyApplicationUrlId = checkApply.getApplicationUrlId();
        //通过验收申请表id获取文件的地址
        String applicationFileUrl = acceptApplyMapper.queryFileUrlByFileId(applyApplicationUrlId);
        checkApply.setApplicationAcceptanceUrl(applicationFileUrl);
        //获取验收申请表的真实名字
        String applicationFileName = acceptApplyMapper.queryFileNameByFileId(applyApplicationUrlId);
        checkApply.setApplicationAcceptanceUrlName(applicationFileName);

        //获取提交清单Id
        Integer submitUrlId = checkApply.getSubmitUrlId();
        //通过提交清单Id获取文件的地址
        String submitFileUrl = acceptApplyMapper.queryFileUrlByFileId(submitUrlId);
        checkApply.setSubmitInventoryUrl(submitFileUrl);
        //获取提交清单的真实名字
        String submitFileName = acceptApplyMapper.queryFileNameByFileId(submitUrlId);
        checkApply.setSubmitInventoryUrlName(submitFileName);

        //获取成果附件Id
        Integer achievementUrlId = checkApply.getAchievementUrlId();
        //通过成果附件Id获取文件的地址
        String achievementFileUrl = acceptApplyMapper.queryFileUrlByFileId(achievementUrlId);
        checkApply.setAchievementsUrl(achievementFileUrl);
        String achievementFileName = acceptApplyMapper.queryFileNameByFileId(achievementUrlId);
        checkApply.setAchievementsName(achievementFileName);

        //获取审计报告文件的id
        Integer auditReportUrlId = checkApply.getAuditReportUrlId();
        //通过审计报告文件id获取文件的地址
        String auditReportFileUrl = acceptApplyMapper.queryFileUrlByFileId(auditReportUrlId);
        checkApply.setAuditReportUrl(auditReportFileUrl);
        String auditReportFileName = acceptApplyMapper.queryFileNameByFileId(auditReportUrlId);
        checkApply.setAuditReportUrlName(auditReportFileName);

        //获取初审报告文件的id
        Integer firstInspectionReportUrlId = checkApply.getFirstInspectionReportUrlId();
        //通过初审报告文件的id获取文件的地址
        String firstInspectionReportUrl = acceptApplyMapper.queryFileUrlByFileId(firstInspectionReportUrlId);
        checkApply.setFirstInspectionReportUrl(firstInspectionReportUrl);
        String firstInspectionReportFileName = acceptApplyMapper.queryFileNameByFileId(firstInspectionReportUrlId);
        checkApply.setFirstInspectionReportUrlName(firstInspectionReportFileName);

        //获取专家组意见文件id
        Integer expertGroupCommentsUrlId = null;
        expertGroupCommentsUrlId = checkApply.getExpertGroupCommentsUrlId();

        if (expertGroupCommentsUrlId != null) {
            //此时意味着 这条信息上传过专家组意见与评议表

            //通过专家组文件的id获取专家组意见文件的地址
            String expertGroupCommentsUrl = acceptApplyMapper.queryFileUrlByFileId(expertGroupCommentsUrlId);
            checkApply.setExpertGroupCommentsUrl(expertGroupCommentsUrl);
            String expertGroupCommentsFileName = acceptApplyMapper.queryFileNameByFileId(expertGroupCommentsUrlId);
            checkApply.setExpertGroupCommentsUrlName(expertGroupCommentsFileName);

            //通过专家组评议表id获取专家组评议表文件的地址
            //获取专家评议表文件的id
            Integer expertAcceptanceFormId = checkApply.getExpertAcceptanceFormId();
            String expertAcceptanceFormUrl = acceptApplyMapper.queryFileUrlByFileId(expertAcceptanceFormId);
            checkApply.setExpertAcceptanceFormUrl(expertAcceptanceFormUrl);
            String expertAcceptanceFormFileName = acceptApplyMapper.queryFileNameByFileId(expertAcceptanceFormId);
            checkApply.setExpertAcceptanceFormUrlName(expertAcceptanceFormFileName);

            //获取专家组意见主表信息
            ExpertGroupComment expertGroupComment = acceptApplyMapper.queryExpertGroupComments(checkApply.getId());

            //获取专家组意见从表信息
            List<ExpertGroupCommentsName> expertGroupCommentsName = acceptApplyMapper.queryExpertGroupCommentsName(expertGroupComment.getEgcId());
            expertGroupComment.setExpertGroupCommentsNameList(expertGroupCommentsName);
            checkApply.setExpertGroupComment(expertGroupComment);
        }

        //获取最终验收报告文件id  acceptance_certificate_id
        Integer acceptanceCertificateId = null;
        acceptanceCertificateId = checkApply.getAcceptanceCertificateId();
        if (acceptanceCertificateId != null) {
            //此时最终验收报告文件存在
            //获取最终验收报告地址
            String acceptanceCertificatedFileUrl = acceptApplyMapper.queryFileUrlByFileId(acceptanceCertificateId);
            String acceptanceCertificateFileName = acceptApplyMapper.queryFileNameByFileId(acceptanceCertificateId);
            checkApply.setAcceptanceCertificateUrl(acceptanceCertificatedFileUrl);
            checkApply.setAcceptanceCertificateUrlName(acceptanceCertificateFileName);

            //获取最终验收报告主表
            AcceptanceCertificate acceptanceCertificate = acceptApplyMapper.queryAcceptCertificate(checkApply.getId());

            //获取最终报告专利表
            List<AcceptanceCertificatePatent> acceptanceCertificatePatentList = acceptApplyMapper.queryAcceptanceCertificatePatent(acceptanceCertificate.getId());
            acceptanceCertificate.setAcceptanceCertificatePatentList(acceptanceCertificatePatentList);

            //获取最终报告主要参与人员  acceptance_certificate_principal_personnel
            List<AcceptanceCertificatePrincipalPersonnel> acceptanceCertificatePrincipalPersonnelList = acceptApplyMapper.queryAcceptanceCertificatePrincipalPersonnel(acceptanceCertificate.getId());
            acceptanceCertificate.setAcceptanceCertificatePrincipalPersonnelList(acceptanceCertificatePrincipalPersonnelList);

            //获取最终验收报告中的课题负责人   acceptance_certificate_subject_people
            List<AcceptanceCertificateSubjectPeople> acceptanceCertificateSubjectPeopleList = acceptApplyMapper.queryAcceptanceCertificateSubjectPeople(acceptanceCertificate.getId());
            acceptanceCertificate.setAcceptanceCertificateSubjectPeopleList(acceptanceCertificateSubjectPeopleList);

            checkApply.setAcceptanceCertificate(acceptanceCertificate);
        }


        //取出验收申请表中数据对应的id
        Integer id = checkApply.getId();
        //根据id到验收审核状态表中查询审核状态
        List<CheckApplyState> checkApplyStateList = acceptApplyMapper.queryAcceptApplyState(id);

        checkApply.setCheckApplyStateList(checkApplyStateList);

        //获取审核状态id获取审核状态的名称
        String apName = acceptApplyMapper.queryAcceptancePhaseNameByApId(checkApply.getAcceptancePhaseId());
        checkApply.setAcceptancePhaseName(apName);

        return resultMap.success().message(checkApply);


//        JSONObject jsonObject = JSON.parseObject(checkApply.toString());
//        jsonObject.remove("achievementUrlId");
//        jsonObject.remove("submitUrlId");
//        jsonObject.remove("auditReportUrlId");
//        jsonObject.remove("firstInspectionReportUrlId");
//        jsonObject.remove("expertGroupCommentsUrlId");
//        jsonObject.remove("expertAcceptanceFormId");
//        jsonObject.remove("applicationUrlId");
//        jsonObject.remove("createTime");
//        jsonObject.remove("createAuthor");
//        jsonObject.remove("acceptancePhaseId");
//
//        jsonObjectList.add(jsonObject);
    }
}
