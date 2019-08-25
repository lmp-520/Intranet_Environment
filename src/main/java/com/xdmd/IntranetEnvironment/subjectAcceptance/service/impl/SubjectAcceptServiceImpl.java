package com.xdmd.IntranetEnvironment.subjectAcceptance.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdmd.IntranetEnvironment.common.*;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.InsertSqlException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.UpdateAcceptancePhaseException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.UpdateSqlException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.mapper.AcceptApplyMapper;
import com.xdmd.IntranetEnvironment.subjectAcceptance.mapper.SubjectAcceptMapper;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApplyState;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.ExpertGroupComment;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.ExpertGroupCommentsName;
import com.xdmd.IntranetEnvironment.subjectAcceptance.service.SubjectAcceptSerivce;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import com.xdmd.IntranetEnvironment.user.pojo.User;
import com.xdmd.IntranetEnvironment.user.service.impl.TokenService;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class SubjectAcceptServiceImpl implements SubjectAcceptSerivce {

    @Autowired
    private SubjectAcceptMapper subjectAcceptMapper;
    @Autowired
    private AcceptApplyMapper acceptApplyMapper;
    @Autowired
    private TokenService tokenService;
    ResultMap resultMap = new ResultMap();
    PageBean pageBean = new PageBean();
    private static Logger log = LoggerFactory.getLogger(SubjectAcceptServiceImpl.class);


    //课题验收的查询
    @Override
    public ResultMap SubjectAcceptQuery(String topicName, String subjectUndertakingUnit, Integer unitNature, String projectLeader, Integer page, Integer total) {
        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }

        //获取验收申请表的总数
        int alltotal = 0;
        alltotal = subjectAcceptMapper.queryAllSubjectAccept(topicName, subjectUndertakingUnit, unitNature, projectLeader);
        if (alltotal == 0) {
            return resultMap.fail().message();
        }

        //获取验收申请表的集合
        List<CheckApply> checkApplyList = subjectAcceptMapper.subjectAcceptQuery(newpage, total, topicName, subjectUndertakingUnit, unitNature, projectLeader);

        List<JSONObject> jsonObjectList = new ArrayList<>();
        //通过查询出来的文件id 获取文件的地址
        for (CheckApply checkApply : checkApplyList) {
            //获取验收申请表Id
            Integer applicationUrlId = checkApply.getApplicationUrlId();
            //通过验收申请表id获取文件的地址
            String applicationFileUrl = subjectAcceptMapper.queryFileUrlByFileId(applicationUrlId);
            checkApply.setApplicationAcceptanceUrl(applicationFileUrl);

            //获取提交清单Id
            Integer submitUrlId = checkApply.getSubmitUrlId();
            //通过提交清单Id获取文件的地址
            String submitFileUrl = subjectAcceptMapper.queryFileUrlByFileId(submitUrlId);
            checkApply.setSubmitInventoryUrl(submitFileUrl);

            //获取成果附件Id
            Integer achievementUrlId = checkApply.getAchievementUrlId();
            //通过成果附件Id获取文件的地址
            String achievementFileUrl = subjectAcceptMapper.queryFileUrlByFileId(achievementUrlId);
            checkApply.setAchievementsUrl(achievementFileUrl);

            //取出验收申请表中数据对应的id
            Integer id = checkApply.getId();
            //根据数据id到验收审核状态表中查询审核状态
            List<CheckApplyState> checkApplyStateList = subjectAcceptMapper.queryAcceptApplyState(id);

            checkApply.setCheckApplyStateList(checkApplyStateList);

            //获取审核状态id获取审核状态的名称
            String apName = acceptApplyMapper.queryAcceptancePhaseNameByApId(checkApply.getAcceptancePhaseId());
            checkApply.setAcceptancePhaseName(apName);

            //获取专家组意见与每个专家验收评议表文件的地址，如果没获取到，则公司还没有上传该文件，则内网可以上传这个文件，并且填写是否通过验收，假如通过验收，直接到验收结束
            //若获取到了，则由内网可以点击通过审核，或者未通过审核

            Integer expertGroupCommentsUrlId = null;
            Integer expertAcceptanceFormId = null;
            expertGroupCommentsUrlId = subjectAcceptMapper.queryExpertGroupCommentsUrlId(checkApply.getId());  //根据验收申请表的id获取对应的 专家组意见文件的id
            expertAcceptanceFormId = subjectAcceptMapper.queryExpertAcceptanceFormId(checkApply.getId());  //根据验收申请表的id获取对应的 专家验收评议表文件的id

            if (expertGroupCommentsUrlId != null && expertAcceptanceFormId != null) {
                //专家组意见与每个专家验收评议表文件都存在，意味着，这两个文件公司已经上传了,根据文件的id，把文件的地址查询出来，把这两个文件地址传给前端
                String expertGroupCommentsUrl = subjectAcceptMapper.queryFileUrlByFileId(expertGroupCommentsUrlId);  //根据专家意见表的id，查询出专家意见文件的地址
                String expertAcceptanceFormUrl = subjectAcceptMapper.queryFileUrlByFileId(expertAcceptanceFormId);//根据专家评议表文件的id，查询出专家评议表的文件地址
                //把获取到的文件地址插入到checkApply中
                checkApply.setExpertGroupCommentsUrl(expertGroupCommentsUrl);
                checkApply.setExpertAcceptanceFormUrl(expertAcceptanceFormUrl);
            }

            JSONObject jsonObject = JSON.parseObject(checkApply.toString()); //对checkAply实体类进行序列化

            if (expertGroupCommentsUrlId == null || expertAcceptanceFormId == null) {
                //这两个文件没有上传的话，把这两个文件的地址设置为空，返回给前端
                jsonObject.put("expertGroupCommentsUrl", null);
                jsonObject.put("expertAcceptanceFormUrl", null);
            }

            //查询专家组意见表，返回给前端
            ExpertGroupComment expertGroupComment = subjectAcceptMapper.queryExpertGroupCommentById(id);//根据验收申请表的id，获取对应专家组意见表
            if (StringUtils.isEmpty(expertGroupComment)) {
                //如果没有查询出结果，意味着公司还没有上传专家组意见表
                jsonObject.put("expertGroupComment", null);
            } else {
                //此时可以查询出数据
                int egcId = expertGroupComment.getEgcId();//获取出专家组意见表中的id
                List<ExpertGroupCommentsName> expertGroupCommentsNameList = subjectAcceptMapper.queryAllExpertNameByEgcId(egcId);//通过专家组意见表的id，获取到专家的信息
                expertGroupComment.setExpertGroupCommentsNameList(expertGroupCommentsNameList);
                jsonObject.put("expertGroupComment", expertGroupComment);
            }

            jsonObject.remove("achievementUrlId");
            jsonObject.remove("submitUrlId");
            jsonObject.remove("auditReportUrlId");
            jsonObject.remove("firstInspectionReportUrlId");
            jsonObject.remove("expertGroupCommentsUrlId");
            jsonObject.remove("expertAcceptanceFormId");
            jsonObject.remove("applicationUrlId");
            jsonObject.remove("createTime");
            jsonObject.remove("createAuthor");
            jsonObject.remove("acceptancePhaseId");

            jsonObjectList.add(jsonObject);
        }
        pageBean.setAlltotal(alltotal);
        pageBean.setData(jsonObjectList);

        return resultMap.success().message(pageBean);
    }

    //课题验收中的审核
    @Override
    public ResultMap SubjectAcceptState(String token, HttpServletResponse response, Boolean type, String reason, Integer id, MultipartFile expertGroupCommentsFile, MultipartFile expertAcceptanceFormFile, Integer acceptanceFinalResultId, ExpertGroupComment expertGroupComment) throws Exception {
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

        //判断是审核通过还是审核未通过
        if (type) {
            //如果审核通过，则意味着，专家的两个文件一定是有的，那么先判断这两个文件，是原先公司已经上传过的，还是内网上传的
            Integer expertGroupCommentsId = null;
            Integer expertAcceptanceFormId = null;
            expertGroupCommentsId = subjectAcceptMapper.queryExpertGroupCommentsUrlId(id);  //判断专家意见表文件在验收申请表中是否存在
            expertAcceptanceFormId = subjectAcceptMapper.queryExpertAcceptanceFormId(id);  //判断专家评议表文件在验收申请表中是否存在

            //如果这两个不存在，则意味着这两个文件，都是内网上传的，则要把这个文件上传到对应的文件夹中
            if (expertGroupCommentsId == null || expertAcceptanceFormId == null) {
                int cid = subjectAcceptMapper.queryCompanyIdByid(id);//根据验收申请表id找到对应的公司id
                String companyName = subjectAcceptMapper.queryCompanyNameByCid(cid);//根据公司的id，找到公司的名称
                String subejctName = subjectAcceptMapper.querySubjectNameByCid(id);//根据验收申请表id，找到该课题名称

                //判断这两个上传的文件后缀名是否正确
                String expertGroupCommentsFilename = expertGroupCommentsFile.getOriginalFilename();      //获取专家意见表文件名
                List<String> expertGroupCommentsSuffixList = new ArrayList<String>(Arrays.asList(".doc", ".docx")); //定义专家组意见表允许上传的类型
                Boolean flag1 = FileSuffixJudgeUtil.SuffixJudge(expertGroupCommentsFilename, expertGroupCommentsSuffixList);  //判断专家组意见表后缀名是否有误
                //获取专家评议表的文件名
                String expertAcceptanceFormFilename = expertAcceptanceFormFile.getOriginalFilename();
                List<String> expertAcceptanceFormSuffixList = new ArrayList<String>(Arrays.asList(".zip", ".7z", ".rar"));//定义专家评议表的允许上传的类型
                Boolean flag2 = FileSuffixJudgeUtil.SuffixJudge(expertAcceptanceFormFilename, expertAcceptanceFormSuffixList);//判断专家评议表上传的类型是否有误

                if (flag1 == false || flag2 == false) {
                    //两个文件中有一个错误，就意味着有文件上传格式不正确
                    return resultMap.fail().message("请上传正确的文件格式");
                }
                try {
                    String expertGroupCommentsUrl = FileUploadUtil.UploadSubejctAcceptExpertFile(expertGroupCommentsFile, "专家组意见", companyName, subejctName);
                    UploadFile UploadExpertGroupComments = IntegrationFile.IntegrationFile(expertGroupCommentsFile, expertGroupCommentsUrl, "专家组意见", username);

                    subjectAcceptMapper.insertFile(UploadExpertGroupComments);//把专家组意见文件新增到文件表中
                    subjectAcceptMapper.updateExpertGroupCommentsUrlById(id, UploadExpertGroupComments.getId());    //根据验收申请表的id，把专家组意见文件id更新上去

                    String expertAcceptanceFormUrl = FileUploadUtil.UploadSubejctAcceptExpertFile(expertAcceptanceFormFile, "专家组评议", companyName, subejctName);
                    UploadFile UploadExpertAcceptanceForm = IntegrationFile.IntegrationFile(expertAcceptanceFormFile, expertAcceptanceFormUrl, "专家组评议", username);

                    subjectAcceptMapper.insertFile(UploadExpertAcceptanceForm);        //把专家评议压缩包文件新增到文件表中
                    subjectAcceptMapper.updateExpertAcceptanceFormUrlById(id, UploadExpertAcceptanceForm.getId());    //根据验收申请表的id，把专家评议文件id更新上去


                    /**
                     * 这里做一个专家组意见表的内容传输到数据库中
                     */
                    //此时因为文件是由内网上传的，所以专家组意见表，也肯定是由内网填写的
                    Date nowTime = new Date();  //获取此条数据的创建时间
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    expertGroupComment.setCreateTime(sdf.format(nowTime));

                    expertGroupComment.setCreateAuthor(username);//获取创建此条信息的人
                    expertGroupComment.setCaId(id);//获取此条验收申请的数据id
                    expertGroupComment.setUid(uid);
                    expertGroupComment.setIsSubmit("1");    //把此条数据设置为提交

                    try {
                        subjectAcceptMapper.addExpertGroupComment(expertGroupComment);  //对专家组意见主表进行新增
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new InsertSqlException("专家组意见主表进行新增时出错-- " + e.getMessage());
                    }

                    //遍历专家组意见表的专家姓名
                    List<ExpertGroupCommentsName> expertGroupCommentsNameList = expertGroupComment.getExpertGroupCommentsNameList();

                    for (ExpertGroupCommentsName egcn : expertGroupCommentsNameList) {
                        egcn.setEgcId(expertGroupComment.getEgcId());   //把专家意见表id，插入进去
                        //对专家组意见表中的专家信息进行新增
                        try {
                            subjectAcceptMapper.addExpertGroupCommentName(egcn);
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new InsertSqlException("专家组意见表中的专家信息进行新增时 出现错误" + e.getMessage());
                        }
                    }

                } catch (Exception e) {
                    log.error("SubjectAcceptServiceImpl 中 SubjectAcceptState方法中 文件上传出现异常: -------" + e.getMessage());
                    e.printStackTrace();
                    return resultMap.fail().message("系统异常");
                }

                //此时审核已经通过了，但是要做一个最终结果的判断
                //如果审核通过，则继续走后面的流程，需要公司上传最终的验收报告
                //如果审核不通过，则直接结束所有验收流程
                //如果审核结题，则直接结束所有的验收流程

                //通过最终结果的验收报告id，来获取最终的验收结果(从字典表里进行查询)
                String acceptanceFinalResult = subjectAcceptMapper.queryAcceptanceFinalResultByAfrId(acceptanceFinalResultId);

                if (acceptanceFinalResult.equals("不通过验收")) {
                    //此时不通过验收
                    //审核通过时,把上一条数据进行更新
                    String state = "已处理";
                    String handleContent = "审核通过";
                    Date date = new Date();
                    //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
                    int num = 0;
                    num = subjectAcceptMapper.UpdateCheckApplyState(id, username, state, handleContent, date);
                    if (num == 0) {
                        throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
                    }

                    //此时把这条验收申请的状态更新为，99：验收不通过
                    int acceptancePhaseNum = 99; //  99：验收不通过
                    int num4 = 0;
                    num4 = subjectAcceptMapper.updateAcceptancePhaseById(id, acceptancePhaseNum);
                    if (num4 == 0) {
                        throw new UpdateAcceptancePhaseException("更新验收申请表的验收审核状态字段时出错");
                    }
                    return resultMap.success().message("审核成功");
                } else if (acceptanceFinalResult.equals("结题")) {
                    //此时结题
                    //审核通过时,把上一条数据进行更新
                    String state = "已处理";
                    String handleContent = "审核通过";
                    Date date = new Date();
                    //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
                    int num = 0;
                    num = subjectAcceptMapper.UpdateCheckApplyState(id, username, state, handleContent, date);
                    if (num == 0) {
                        throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
                    }

                    //此时把这条验收申请的状态更新为，88：验收结题
                    int acceptancePhaseNum = 88; //  88：验收结题
                    int num4 = 0;
                    num4 = subjectAcceptMapper.updateAcceptancePhaseById(id, acceptancePhaseNum);
                    if (num4 == 0) {
                        throw new UpdateAcceptancePhaseException("更新验收申请表的验收审核状态字段时出错");
                    }
                    return resultMap.success().message("审核成功");
                } else {

                    //审核通过时,先把上一条数据进行更新，再新增下一条数据
                    String state = "已处理";
                    String handleContent = "审核通过";
                    Date date = new Date();
                    //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
                    int num = 0;
                    num = subjectAcceptMapper.UpdateCheckApplyState(id, username, state, handleContent, date);
                    if (num == 0) {
                        throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
                    }

                    //新增下一条数据的处理
                    //获取上一次该状态信息的最后提交处理时间，作为新增数据的交办时间
                    String firstHandleTime = subjectAcceptMapper.queryCheckApplyLastTime(id);
                    String auditStep = "等待公司上传最终验收报告";
                    String newState = "等待处理";
                    int num2 = 0;
                    num2 = subjectAcceptMapper.addNewCheckApplyState(id, auditStep, newState, username, firstHandleTime);
                    if (num2 == 0) {
                        throw new InsertSqlException("审核通过时，在新增审核状态时，新增下一条数据时出错");
                    }

                    //当把审核状态表更新完成后，更新验收申请表中这条数据的验收审核状态
                    int num3 = 0;
                    int acceptancePhaseNum = 6; //6:等待公司上传最终的验收报告
                    num3 = subjectAcceptMapper.updateAcceptancePhaseById(id, acceptancePhaseNum);
                    if (num3 == 0) {
                        throw new UpdateAcceptancePhaseException("更新验收申请表的验收审核状态字段时出错");
                    }

                    return resultMap.success().message("审核成功");
                }


            } else {
                //此时为审核通过，查询出来的信息中，有这两个文件的地址，那么意味着，这两个文件是公司上传的
                //通过最终结果的验收报告id，来获取最终的验收结果(从字典表里进行查询)
                String acceptanceFinalResult = subjectAcceptMapper.queryAcceptanceFinalResultByAfrId(acceptanceFinalResultId);

                if (acceptanceFinalResult.equals("不通过验收")) {
                    //此时不通过验收
                    //审核通过时,把上一条数据进行更新
                    String state = "已处理";
                    String handleContent = "审核通过";
                    Date date = new Date();
                    //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
                    int num = 0;
                    num = subjectAcceptMapper.UpdateCheckApplyState(id, username, state, handleContent, date);
                    if (num == 0) {
                        throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
                    }

                    //此时把这条验收申请的状态更新为，99：验收不通过
                    int acceptancePhaseNum = 99; //  99：验收不通过
                    int num4 = 0;
                    num4 = subjectAcceptMapper.updateAcceptancePhaseById(id, acceptancePhaseNum);
                    if (num4 == 0) {
                        throw new UpdateAcceptancePhaseException("更新验收申请表的验收审核状态字段时出错");
                    }
                    return resultMap.success().message("审核成功");
                } else if (acceptanceFinalResult.equals("结题")) {
                    //此时结题
                    //审核通过时,把上一条数据进行更新
                    String state = "已处理";
                    String handleContent = "审核通过";
                    Date date = new Date();
                    //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
                    int num = 0;
                    num = subjectAcceptMapper.UpdateCheckApplyState(id, username, state, handleContent, date);
                    if (num == 0) {
                        throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
                    }

                    //此时把这条验收申请的状态更新为，88：验收结题
                    int acceptancePhaseNum = 88; //  88：验收结题
                    int num4 = 0;
                    num4 = subjectAcceptMapper.updateAcceptancePhaseById(id, acceptancePhaseNum);
                    if (num4 == 0) {
                        throw new UpdateAcceptancePhaseException("更新验收申请表的验收审核状态字段时出错");
                    }
                    return resultMap.success().message("审核成功");
                } else {
                    //审核通过时,先把上一条数据进行更新，再新增下一条数据
                    String state = "已处理";
                    String handleContent = "审核通过";
                    Date date = new Date();
                    //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
                    int num = 0;
                    num = subjectAcceptMapper.UpdateCheckApplyState(id, username, state, handleContent, date);
                    if (num == 0) {
                        throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
                    }

                    //新增下一条数据的处理
                    //获取上一次该状态信息的最后提交处理时间，作为新增数据的交办时间
                    String firstHandleTime = subjectAcceptMapper.queryCheckApplyLastTime(id);
                    String auditStep = "等待公司上传最终验收报告";
                    String newState = "等待处理";
                    int num2 = 0;
                    num2 = subjectAcceptMapper.addNewCheckApplyState(id, auditStep, newState, username, firstHandleTime);
                    if (num2 == 0) {
                        throw new InsertSqlException("审核通过时，在新增审核状态时，新增下一条数据时出错");
                    }

                    //当把审核状态表更新完成后，更新验收申请表中这条数据的验收审核状态
                    int num3 = 0;
                    int acceptancePhaseNum = 6; //6:等待公司上传最终的验收报告
                    num3 = subjectAcceptMapper.updateAcceptancePhaseById(id, acceptancePhaseNum);
                    if (num3 == 0) {
                        throw new UpdateAcceptancePhaseException("更新验收申请表的验收审核状态字段时出错");
                    }
                    return resultMap.success().message("审核成功");
                }

            }
        } else {
            //此时为审核未通过 先把上一条数据进行更新，再新增下一条数据
            String state = "已退回";
            String handleContent = reason;
            Date date = new Date();
            //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
            int num = 0;
            num = subjectAcceptMapper.UpdateCheckApplyState(id, username, state, handleContent, date);
            if (num == 0) {
                throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
            }

            //新增下一条数据的处理
            //获取上一次该状态信息的最后提交处理时间，作为新增数据的交办时间
            String firstHandleTime = subjectAcceptMapper.queryCheckApplyLastTime(id);
            String auditStep = "通过初审，等待提交专家表";
            String newState = "等待处理";
            int num2 = 0;
            num2 = subjectAcceptMapper.addNewCheckApplyState(id, auditStep, newState, username, firstHandleTime);
            if (num2 == 0) {
                throw new InsertSqlException("审核通过时，在新增审核状态时，新增下一条数据时出错");
            }

            //当把审核状态表更新完成后，更新验收申请表中这条数据的验收审核状态
            int num3 = 0;
            int acceptancePhaseNum = 4; //5：通过初审，等待提交专家表
            num3 = subjectAcceptMapper.updateAcceptancePhaseById(id, acceptancePhaseNum);
            if (num3 == 0) {
                throw new UpdateAcceptancePhaseException("更新验收申请表的验收审核状态字段时出错");
            }
            return resultMap.success().message("审核成功");
        }
    }


    //上传专家组意见
    @Override
    public ResultMap SubjectAcceptStateExpertGroup(String token, HttpServletResponse response, Boolean type, Integer id, ExpertGroupComment expertGroupComment) throws Exception {
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

        if (type) {
            //此时验收通过，判断外网是否已经上传过专家验收表
            Integer egcId = null;
            egcId = subjectAcceptMapper.queryExpertGroupCommentsUrlId(id);
            if (egcId != null) {
                //此时外网已经上传过专家表，就不需要再把专家组的信息写入数据库内
                return resultMap.success().message();
            } else {
                //此时因为文件是由内网上传的，所以专家组意见表，也肯定是由内网填写的
                Date nowTime = new Date();  //获取此条数据的创建时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                expertGroupComment.setCreateTime(sdf.format(nowTime));

                expertGroupComment.setCreateAuthor(username);//获取创建此条信息的人
                expertGroupComment.setCaId(id);//获取此条验收申请的数据id

                try {
                    subjectAcceptMapper.addExpertGroupComment(expertGroupComment);  //对专家组意见主表进行新增
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new InsertSqlException("专家组意见主表进行新增时出错-- " + e.getMessage());
                }
                //遍历专家组意见表的专家姓名
                List<ExpertGroupCommentsName> expertGroupCommentsNameList = expertGroupComment.getExpertGroupCommentsNameList();

                for (ExpertGroupCommentsName egcn : expertGroupCommentsNameList) {
                    egcn.setEgcId(expertGroupComment.getEgcId());   //把专家意见表id，插入进去
                    //对专家组意见表中的专家信息进行新增
                    try {
                        subjectAcceptMapper.addExpertGroupCommentName(egcn);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new InsertSqlException("专家组意见表中的专家信息进行新增时 出现错误" + e.getMessage());
                    }
                }
            }
        }
        return resultMap.success().message();
    }

    //验收申请中的保存  需要保存专家组意见表
    @Override
    public ResultMap SubjectAcceptSave(String token, HttpServletResponse response, Boolean type, String reason, Integer id, Integer acceptanceFinalResultId, ExpertGroupComment expertGroupComment, MultipartFile expertGroupCommentsFile, MultipartFile expertAcceptanceFormFile) {
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

        ResultMap resultMap = new ResultMap();
        return resultMap;
    }
}