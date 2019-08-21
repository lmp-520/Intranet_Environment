package com.xdmd.IntranetEnvironment.subjectmanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectmanagement.mapper.OpenTenderMapper;
import com.xdmd.IntranetEnvironment.subjectmanagement.mapper.UploadFileMapper;
import com.xdmd.IntranetEnvironment.subjectmanagement.pojo.OpenTender;
import com.xdmd.IntranetEnvironment.subjectmanagement.service.OpenTenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
public class OpenTenderServiceImpl implements OpenTenderService {
    @Autowired
    OpenTenderMapper openTenderMapper;
    @Autowired
    UploadFileMapper uploadFileMapper;
    /**
     * 状态码
     */
    ResultMap resultMap = new ResultMap();


    /**
     * 新增
     * @param openTender
     * @return
     */
    @Override
    public ResultMap insertTender(OpenTender openTender) {
        try{
            openTender.setProjectNo(setProjectNo());
            int insertNo=openTenderMapper.insertTender(openTender);
            if(insertNo>0){
                resultMap.success().message("成功新增"+insertNo+"条数据");
            }else if(insertNo==0){
                resultMap.success().message("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 根據单位id查詢相应单位的招标公告
     * @param uid
     * @param projectName
     * @param subjectName
     * @param subjectLeader
     * @param leaderContact
     * @return
     */
    @Override
    public ResultMap getTenderByUid(int uid, String projectName, String subjectName, String subjectLeader, String leaderContact) {
        try{
            List<Map> getTenderByUidMap = openTenderMapper.getTenderByUid(uid,projectName,subjectName,subjectLeader,leaderContact);
            if(getTenderByUidMap!=null){
                resultMap.success().message(getTenderByUidMap);
            }else if(getTenderByUidMap==null){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 根據id获取招标公告详情
     * @param id
     * @return
     */
    @Override
    public ResultMap getTenderById(int id) {
        try{
            Map getTenderByIdMap = openTenderMapper.getTenderById(id);
            if(getTenderByIdMap!=null){
                resultMap.success().message(getTenderByIdMap);
            }else if(getTenderByIdMap==null){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 课题编号自增设置
     * @param
     */
    public String setProjectNo(){
        getNewData();
        String subString = new String(openTenderMapper.getNewData().getProjectNo());
        /**
         * 分离出数字并转换成int类型
         */
        int num = Integer.parseInt(subString.substring(4));
        if (num<=20190000) {
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
        String finalResult=sBuilder.insert(sBuilder.length(), num).toString();
        return finalResult;
    }

    /**
     * 分页筛选查询招标信息
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
        try{
            PageHelper.startPage(pageNum, pageSize);
            List<Map> openTenderList = openTenderMapper.getTenderPageList(projectName, subjectName, subjectLeader, leaderContact);
            if(openTenderList!=null){
                resultMap.success().message(openTenderList);
            }else if(openTenderList==null){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 根据招标备案id更新相应的附件id【外网上传附件】
     * @param winningFileAttachmentId
     * @param announcementTransactionAnnouncementId
     * @param dealNotificationAttachmentId
     * @param responseFileAttachmentId
     * @param oid
     * @return
     */
    @Override
    public ResultMap updateTenderByoid(int winningFileAttachmentId, int announcementTransactionAnnouncementId, int dealNotificationAttachmentId, int responseFileAttachmentId,int oid) {
        try{
            int updateNo = openTenderMapper.updateTenderByoid(winningFileAttachmentId,announcementTransactionAnnouncementId,dealNotificationAttachmentId,responseFileAttachmentId,oid);
            if(updateNo>0){
                resultMap.success().message("成功更新"+updateNo+"条数据");
            }else if(updateNo<0){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }


    @Override
    public OpenTender getNewData() {
        return openTenderMapper.getNewData();
    }

    /**
     * 备用
     * @param token
     * @param response
     * @param type
     * @param reason
     * @param id
     * @param specialAuditFile
     * @param firstInspectionFile
     * @return
     */
    @Override
    public ResultMap acceptState(String token, HttpServletResponse response, Boolean type, String reason, Integer id, MultipartFile specialAuditFile, MultipartFile firstInspectionFile) {
        return null;
    }

    /**
     * 招标审核
     * @param token
     * @param response
     * @param type
     * @param reason
     * @param id
     * @param specialAuditFile
     * @param firstInspectionFile
     * @return
     * @throws Exception

    @Override
    public ResultMap acceptState(String token, HttpServletResponse response, Boolean type, String reason, Integer id, MultipartFile specialAuditFile, MultipartFile firstInspectionFile) throws Exception {
        User user = new User();
        Integer uid = user.getId();
        String username = user.getUsername();

        //判断是审核通过还是审核未通过
        if (type) {
            //此时为审核通过时
            //判断上传文件的后缀名是否正确
            String specialAuditFileName = specialAuditFile.getOriginalFilename();
            List<String> specialAuditSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".xlsx", ".zip", ".7z", ".rar"));
            Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(specialAuditFileName, specialAuditSuffixList);

            String firstInspectionFileName = firstInspectionFile.getOriginalFilename();
            List<String> firstInspectionSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".xlsx", ".zip", ".7z", ".rar"));
            Boolean bBoolean = FileSuffixJudgeUtil.SuffixJudge(firstInspectionFileName, firstInspectionSuffixList);

            if(aBoolean == false || bBoolean == false){
                //两个文件中存在有一个错误，意味着有文件上传的格式不正确
                return resultMap.fail().message("请上传正确的文件格式");
            }

            //根据验收申请表的id 获取该公司的名字
            String cname = acceptStateMapper.queryCompanyNameByCid(id);

            //获取专项审计报告文件的地址
            String specialAuditFileUrl = FileUploadUtil.fileUpload(specialAuditFile, cname, "专项审计报告");
            UploadFile uploadSpecialAudit = IntegrationFile.IntegrationFile(specialAuditFile,specialAuditFileUrl,"专项审计报告",username);
            acceptStateMapper.insertFile(uploadSpecialAudit);   //把该文件上传到文件表中
            acceptStateMapper.updateCheckApplyFileId(id,uploadSpecialAudit.getId());//把新增该专项审计报告文件的id取出，存到check_apply中

            //初审报告文件
            String firstInspectionFIleUrl = FileUploadUtil.fileUpload(firstInspectionFile, cname, "初审报告");
            UploadFile uploadFirstInspection = IntegrationFile.IntegrationFile(firstInspectionFile,firstInspectionFIleUrl,"初审报告",username);
            acceptStateMapper.insertFile(uploadFirstInspection);    //上传此文件到文件表中
            acceptStateMapper.updateCheckApplyFirstInspectionFileId(id,uploadFirstInspection.getId());//把新增初审报告文件的id取出，存在check_apply中


            //审核通过时,先把上一条数据进行更新，再新增下一条数据
            String state = "已处理";
            String handleContent = "审核通过";
            Date date = new Date();
            //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
            int num = 0;
            num = acceptStateMapper.UpdateCheckApplyState(id, username, state, handleContent, date);
            if (num == 0) {
                throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
            }

            //新增下一条数据的处理
            //获取上一次该状态信息的最后提交处理时间，作为新增数据的交办时间
            String firstHandleTime = acceptStateMapper.queryCheckApplyLastTime(id);
            String auditStep = "通过初审，等待提交专家表";
            String newState = "等待处理";
            int num2 = 0;
            num2 = acceptStateMapper.addNewCheckApplyState(id, auditStep, newState, username, firstHandleTime);
            if (num2 == 0) {
                throw new InsertSqlException("审核通过时，在新增审核状态时，新增下一条数据时出错");
            }

            //当把审核状态表更新完成后，更新验收申请表中这条数据的验收审核状态
            int num3 = 0;
            int acceptancePhaseNum = 4;
            num3 = acceptApplyMapper.updateAcceptancePhaseById(id,acceptancePhaseNum);
            if(num3 ==0){
                throw new UpdateAcceptancePhaseException("更新验收申请表的验收审核状态字段时出错");
            }

        } else {
            //此时审核未通过，首先更新上一条语句
            //审核通过时,先把上一条数据进行更新，再新增下一条数据
            String state = "已退回";
            String handleContent = reason;
            Date date = new Date();
            //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
            int num = 0;
            num = acceptStateMapper.UpdateCheckApplyState(id, username, state, handleContent, date);
            if (num == 0) {
                throw new UpdateSqlException("审核未通过时，在更新审核状态，更新上一条数据时出错");
            }

            //新增下一条数据的处理
            //获取上一次该状态信息的最后提交处理时间，作为新增数据的交办时间
            String firstHandleTime = acceptStateMapper.queryCheckApplyLastTime(id);
            String auditStep = "等待企业管理员提交";
            String newState = "等待处理";
            int num2 = 0;
            num2 = acceptStateMapper.addNewCheckApplyState(id, auditStep, newState, username, firstHandleTime);
            if (num2 == 0) {
                throw new InsertSqlException("在新增审核状态时，新增下一条数据时出错");
            }

            //当把审核状态表更新完成后，更新验收申请表中这条数据的验收审核状态
            int num3 = 0;
            int acceptancePhaseNum = 2;
            num3 = acceptApplyMapper.updateAcceptancePhaseById(id,acceptancePhaseNum);
            if(num3 ==0){
                throw new UpdateAcceptancePhaseException("更新验收申请表的验收审核状态字段时出错");
            }
        }
        return resultMap.success().message("提交成功");
    }
     */
}
