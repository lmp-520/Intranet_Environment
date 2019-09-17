package com.xdmd.IntranetEnvironment.dailymanagement.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdmd.IntranetEnvironment.common.*;
import com.xdmd.IntranetEnvironment.dailymanagement.mapper.ProjectProgressMapper;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.*;
import com.xdmd.IntranetEnvironment.dailymanagement.service.ProjectProgressService;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.JwtInformation;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.impl.ExtranetTokenService;
import com.xdmd.IntranetEnvironment.subjectmanagement.service.impl.OpenTenderServiceImpl;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import com.xdmd.IntranetEnvironment.user.service.impl.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/08/14
 * @description: 课题进展报告实现
 */
@Service
public class ProjectProgressServiceImpl implements ProjectProgressService {
    private static final Logger log = LoggerFactory.getLogger(ProjectProgressServiceImpl.class);
    ResultMap resultMap = new ResultMap();
    @Autowired
    ProjectProgressMapper projectProgressMapper;
    @Autowired
    UploadFileMapper uploadFileMapper;
    @Autowired
    ExtranetTokenService extranetTokenService;
    @Autowired
    TokenService tokenService;

    /**
     * [新增] 课题进展主体
     *
     * @param token
     * @param response
     * @param progressDTO
     * @return
     */
    @Override
    public ResultMap insert(String token, HttpServletResponse response, ProjectProgressDTO progressDTO) {
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
        Integer uid = jwtInformation.getCid();
        //单位名称
        //String cname = jwtInformation.getCompanyName();

        try {
            int insertNo = projectProgressMapper.insert(progressDTO);
            //单位关联课题进度主表
            insertPidAndUid(uid, progressDTO.getId());
            if (insertNo > 0) {
                resultMap.success().message(progressDTO.getId());
            } else if (insertNo == 0) {
                resultMap.fail().message("新增失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;

    }

    /**
     * [查詢] 根據主鍵 id 查詢 课题主题详情
     *
     * @param id
     * @return
     */
    @Override
    public ResultMap getInfoById(int id) {
        try {
            ProjectProgressDTO progressDTO = projectProgressMapper.getInfoById(id);
            if (progressDTO != null) {
                resultMap.success().message(progressDTO);
            } else if (progressDTO == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;

    }


    /**
     * 根据单位id查询课题进展
     * @param token
     * @param response
     * @param subjectName
     * @param bearerUnit
     * @param progress
     * @return
     */
    @Override
    public ResultMap getProgressInfoByUid(String token, HttpServletResponse response, String subjectName, String bearerUnit, Integer progress, int pageNum, int pageSize) {
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
        Integer uid = jwtInformation.getCid();
        //单位名称
        //String cname = jwtInformation.getCompanyName();
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<Map> getProgressByUidMap = projectProgressMapper.getProgressInfoByUid(uid, subjectName, bearerUnit, progress);
            PageInfo pageInfo = new PageInfo(getProgressByUidMap);
            if (getProgressByUidMap != null) {
                resultMap.success().message(pageInfo);
            } else if (getProgressByUidMap == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * [查詢] 分页筛选查询【内网】
     *
     * @param token
     * @param response
     * @param subjectName
     * @param bearerUnit
     * @param progress
     * @return
     */
    @Override
    public ResultMap getInfoByParam(String token, HttpServletResponse response, String subjectName, String bearerUnit, Integer progress, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize, true);
            List<Map> progressDTOList = projectProgressMapper.getInfoByParam(subjectName, bearerUnit, progress);
            PageInfo pageInfo = new PageInfo(progressDTOList);
            if (progressDTOList.size() > 0) {
                resultMap.success().message(pageInfo);
            } else if (progressDTOList.size() == 0) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * [新增] 合同要求研发任务【课题进展第一部分】
     *
     * @author Kong
     * @date 2019/08/14
     **/
    @Override
    public ResultMap insertCRDT(List<ContractResearchDevelopmentTasksDTO> contractResearchDevelopmentTasks) {
        try {
            int insertNo = projectProgressMapper.insertCRDT(contractResearchDevelopmentTasks);
            if (insertNo > 0) {
                resultMap.success().message("成功新增" + insertNo + "条数据");
            } else if (insertNo == 0) {
                resultMap.fail().message("新增失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * [查詢] 根據课题进展id查詢【课题进展第一部分】
     *
     * @param Pid
     * @return
     */
    @Override
    public ResultMap getCRDTByPid(int Pid) {
        try {
            List<ContractResearchDevelopmentTasksDTO> crdt = projectProgressMapper.getCRDTByPid(Pid);
            if (crdt != null) {
                resultMap.success().message(crdt);
            } else if (crdt == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * [新增]目前进展情况【课题进展第二部分】
     *
     * @param currentProgress
     * @return
     */
    @Override
    public ResultMap insertCP(List<CurrentProgressDTO> currentProgress) {
        try {
            int insertNo = projectProgressMapper.insertCP(currentProgress);
            if (insertNo > 0) {
                resultMap.success().message("成功新增" + insertNo + "条数据");
            } else if (insertNo == 0) {
                resultMap.fail().message("新增失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * [查詢] 根據课题进展id查詢【课题进展第二部分】
     *
     * @param Pid
     * @return
     */
    @Override
    public ResultMap getCPByPid(int Pid) {
        try {
            List<CurrentProgressDTO> cp = projectProgressMapper.getCPByPid(Pid);
            if (cp != null) {
                resultMap.success().message(cp);
            } else if (cp == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * [新增] 课题实施中存在的主要问题【课题进展第四部分】
     *
     * @param projectMainProblems
     * @return
     */
    @Override
    public ResultMap insertPMP(List<ProjectMainProblemsDTO> projectMainProblems) {
        try {
            int insertNo = projectProgressMapper.insertPMP(projectMainProblems);
            if (insertNo > 0) {
                resultMap.success().message("成功新增" + insertNo + "条数据");
            } else if (insertNo == 0) {
                resultMap.fail().message("新增失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * [查詢] 根據课题进展id查詢【课题进展第四部分】
     *
     * @param Pid
     * @return
     */
    @Override
    public ResultMap getPMPByPid(int Pid) {
        try {
            List<ProjectMainProblemsDTO> pmp = projectProgressMapper.getPMPByPid(Pid);
            if (pmp != null) {
                resultMap.success().message(pmp);
            } else if (pmp == null) {
                resultMap.success().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

    /**
     * [新增] 下一步工作计划【课题进展第五部分】
     *
     * @param nextWorkPlan
     * @return
     */
    @Override
    public ResultMap insertNWP(List<NextWorkPlanDTO> nextWorkPlan) {
        try {
            int insertNo = projectProgressMapper.insertNWP(nextWorkPlan);
            if (insertNo > 0) {
                resultMap.success().message("成功新增" + insertNo + "条数据");
            } else if (insertNo == 0) {
                resultMap.success().message("新增失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

    /**
     * [查詢] 根據课题进展id查詢【课题进展第四部分】
     *
     * @param Pid
     * @return
     */
    @Override
    public ResultMap getNWPByPid(int Pid) {
        try {
            List<NextWorkPlanDTO> nwp = projectProgressMapper.getNWPByPid(Pid);
            if (nwp != null) {
                resultMap.success().message(nwp);
            } else if (nwp == null) {
                resultMap.success().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 根据课题进展主表id更新相应的附件id
     *
     * @param openReportAnnexId
     * @param subjectProgressAnnexId
     * @param fundProgressAnnexId
     * @param expertSuggestAnnexId
     * @param pid
     * @return
     */
    @Override
    public ResultMap updateSubjectProgressByPid(int openReportAnnexId, int subjectProgressAnnexId, int fundProgressAnnexId, int expertSuggestAnnexId, int pid) {
        try {
            int updateNo = projectProgressMapper.updateSubjectProgressByPid(openReportAnnexId, subjectProgressAnnexId, fundProgressAnnexId, expertSuggestAnnexId, pid);
            if (updateNo > 0) {
                resultMap.success().message("成功更新" + updateNo + "条数据");
            } else if (updateNo == 0) {
                resultMap.success().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 课题进展附件上传
     *
     * @param token
     * @param response
     * @param pid
     * @param openReportAnnex      开题报告附件
     * @param expertSuggestAnnex   专家意见附件
     * @param subjectProgressAnnex 课题进展附件
     * @param fundProgressAnnex    进度经费使用情况附件
     * @return
     */
    @Override
    public ResultMap ProgressMultiUpload(String token, HttpServletResponse response, int pid, MultipartFile openReportAnnex, MultipartFile expertSuggestAnnex, MultipartFile subjectProgressAnnex, MultipartFile fundProgressAnnex) throws FileUploadException {
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
        String unitName = jwtInformation.getCompanyName();

        try {
            /**
             * 开题报告附件
             */
            //判断上传中标文件附件的后缀名是否正确
            String openReportAnnexName = openReportAnnex.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudge.suffixJudge(openReportAnnexName);
            if (aBoolean == false) {
                resultMap.fail().message("开题报告附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取开题报告附件的地址
            String openReportAnnexUrl = new OpenTenderServiceImpl().fileUploadUntil(openReportAnnex, unitName, "开题报告附件");
            //获取文件后缀名
            String openReportAnnexSuffixName = openReportAnnexName.substring(openReportAnnexName.lastIndexOf(".") + 1);
            // 获取文件大小
            File openReportAnnexFile = new File(openReportAnnexUrl);
            String openReportAnnexFileSize = String.valueOf(openReportAnnexFile.length());
            AnnexUpload openReportAnnexData = new AnnexUpload(0, openReportAnnexUrl, openReportAnnexName, "开题报告附件", openReportAnnexSuffixName, openReportAnnexFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(openReportAnnexData);
            /**
             * 专家意见附件
             */
            //判断上传专家意见附件的后缀名是否正确
            String expertSuggestAnnexName = expertSuggestAnnex.getOriginalFilename();
            Boolean bBoolean = FileSuffixJudge.suffixJudge(expertSuggestAnnexName);
            if (bBoolean == false) {
                resultMap.fail().message("专家意见附件附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取专家意见附件的地址
            String expertSuggestAnnexUrl = new OpenTenderServiceImpl().fileUploadUntil(expertSuggestAnnex, unitName, "专家论证意见附件");
            //获取文件后缀名
            String expertSuggestAnnexSuffixName = expertSuggestAnnexName.substring(expertSuggestAnnexName.lastIndexOf(".") + 1);
            // 获取文件大小
            File expertSuggestAnnexFile = new File(expertSuggestAnnexUrl);
            String expertSuggestAnnexFileSize = String.valueOf(expertSuggestAnnexFile.length());
            AnnexUpload expertSuggestAnnexData = new AnnexUpload(0, expertSuggestAnnexUrl, expertSuggestAnnexName, "专家论证意见附件", expertSuggestAnnexSuffixName, expertSuggestAnnexFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(expertSuggestAnnexData);
            /**
             * 课题进展附件
             */
            //判断上传课题进展附件的后缀名是否正确
            String subjectProgressAnnexName = subjectProgressAnnex.getOriginalFilename();
            Boolean cBoolean = FileSuffixJudge.suffixJudge(subjectProgressAnnexName);
            if (cBoolean == false) {
                resultMap.fail().message("课题进展附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取课题进展附件的地址
            String subjectProgressAnnexUrl = new OpenTenderServiceImpl().fileUploadUntil(subjectProgressAnnex, unitName, "课题进展附件");
            //获取文件后缀名
            String subjectProgressAnnexSuffixName = subjectProgressAnnexName.substring(subjectProgressAnnexName.lastIndexOf(".") + 1);
            // 获取文件大小
            File subjectProgressAnnexFile = new File(subjectProgressAnnexUrl);
            String subjectProgressAnnexFileSize = String.valueOf(subjectProgressAnnexFile.length());
            AnnexUpload subjectProgressAnnexData = new AnnexUpload(0, subjectProgressAnnexUrl, subjectProgressAnnexName, "课题进展附件", subjectProgressAnnexSuffixName, subjectProgressAnnexFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(subjectProgressAnnexData);

            /**
             * 进度经费使用情况附件
             */
            //判断上传进度经费使用情况附件的后缀名是否正确
            String fundProgressAnnexName = fundProgressAnnex.getOriginalFilename();
            Boolean dBoolean = FileSuffixJudge.suffixJudge(fundProgressAnnexName);
            if (cBoolean == false) {
                resultMap.fail().message("进度经费使用情况附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取进度经费使用情况附件的地址
            String fundProgressAnnexUrl = new OpenTenderServiceImpl().fileUploadUntil(fundProgressAnnex, unitName, "进度经费使用情况附件");
            //获取文件后缀名
            String fundProgressAnnexSuffixName = fundProgressAnnexName.substring(fundProgressAnnexName.lastIndexOf(".") + 1);
            // 获取文件大小
            File fundProgressAnnexFile = new File(fundProgressAnnexUrl);
            String fundProgressAnnexFileSize = String.valueOf(fundProgressAnnexFile.length());
            AnnexUpload fundProgressAnnexData = new AnnexUpload(0, fundProgressAnnexUrl, fundProgressAnnexName, "进度经费使用情况附件", fundProgressAnnexSuffixName, fundProgressAnnexFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(fundProgressAnnexData);

            /**
             * 把上传附件的id取出，存到课题进展主表中
             */
            int progressNum = projectProgressMapper.updateSubjectProgressByPid(openReportAnnexData.getId(), subjectProgressAnnexData.getId(), fundProgressAnnexData.getId(), expertSuggestAnnexData.getId(), pid);
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
     * 单位关联课题进度主表
     * @param uid
     * @param pid
     */
    @Override
    public ResultMap insertPidAndUid(int uid, int pid) {
        try {
            int insertNo = projectProgressMapper.insertPidAndUid(uid, pid);
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


    /**
     * 获取课题进展文件路径和文件名
     * @param pid
     * @return
     */
    @Override
    public ResultMap getfileInfo(int pid) {
        try {
            List<Map> fileInfoMap = projectProgressMapper.getfileInfo(pid);
            if (fileInfoMap.size() > 0) {
                resultMap.success().message(fileInfoMap);
            } else if (fileInfoMap.size() == 0) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 定期修改上传课题进展情况及附件
     * @param token
     * @param response
     * @param oldSubjectProgressAnnexUrl
     * @param oldFundProgressAnnexUrl
     * @param subjectProgressAnnex
     * @param fundProgressAnnex
     * @param projectProgressDTO
     * @return
     */
    public ResultMap updateTenderStatusByReturnCommit(String token, HttpServletResponse response, String oldSubjectProgressAnnexUrl, String oldFundProgressAnnexUrl, MultipartFile subjectProgressAnnex, MultipartFile fundProgressAnnex, ProjectProgressDTO projectProgressDTO) {
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
            //定期更新课题进展信息
            int updateNum = projectProgressMapper.regularUpdateProgressInfo(projectProgressDTO);
            /**
             * 判断旧文件是否为空
             * 课题进展附件
             */
            if (oldSubjectProgressAnnexUrl != null) {
                //判断上传中标文件附件的后缀名是否正确
                String subjectProgressAnnexName = subjectProgressAnnex.getOriginalFilename();
                Boolean aBoolean = FileSuffixJudge.suffixJudge(subjectProgressAnnexName);
                if (aBoolean == false) {
                    resultMap.fail().message("中标文件附件的文件格式不正确,请上传正确的文件格式");
                }
                //再根据旧的文件地址，先把文件给删除掉
                File file = new File(oldSubjectProgressAnnexUrl);
                file.delete();
                //对新的中标文件附件进行上传
                String subjectProgressAnnexUrl = new OpenTenderServiceImpl().fileUploadUntil(subjectProgressAnnex, unitName, "课题进展附件");
                //获取文件后缀名
                String subjectProgressAnnexSuffixName = subjectProgressAnnexName.substring(subjectProgressAnnexName.lastIndexOf(".") + 1);
                // 获取文件大小
                String subjectProgressAnnexFileSize = String.valueOf(new File(subjectProgressAnnexUrl).length());
                AnnexUpload subjectProgressAnnexData = new AnnexUpload(0, subjectProgressAnnexUrl, subjectProgressAnnexName, "课题进展附件", subjectProgressAnnexSuffixName, subjectProgressAnnexFileSize, null, username);
                //把该文件上传到文件表中
                uploadFileMapper.insertUpload(subjectProgressAnnexData);
                //把上传附件的id取出,存到课题进展表中
                projectProgressMapper.updateSubjectProgressAnnexIdByPid(subjectProgressAnnexData.getId(), projectProgressDTO.getId());
            }
            /**
             * 进度经费使用情况附件
             */
            if (oldFundProgressAnnexUrl != null) {
                String fundProgressAnnexName = fundProgressAnnex.getOriginalFilename();
                //判断上传成交公告附件的后缀名是否正确
                Boolean bBoolean = FileSuffixJudge.suffixJudge(fundProgressAnnexName);
                if (bBoolean == false) {
                    resultMap.fail().message("进度经费使用情况附件的文件格式不正确,请上传正确的文件格式");
                }
                //再根据旧的文件地址，先把文件给删除掉
                File file = new File(oldFundProgressAnnexUrl);
                file.delete();
                //对新的成交公告附件进行上传
                String fundProgressAnnexUrl = new OpenTenderServiceImpl().fileUploadUntil(fundProgressAnnex, unitName, "进度经费使用情况附件");
                //获取文件后缀名
                String fundProgressAnnexSuffixName = fundProgressAnnexName.substring(fundProgressAnnexName.lastIndexOf(".") + 1);
                // 获取文件大小
                File fundProgressAnnexFile = new File(fundProgressAnnexUrl);
                String fundProgressAnnexFileSize = String.valueOf(fundProgressAnnexFile.length());
                AnnexUpload fundProgressAnnexData = new AnnexUpload(0, fundProgressAnnexUrl, fundProgressAnnexName, "进度经费使用情况附件", fundProgressAnnexSuffixName, fundProgressAnnexFileSize, null, username);
                //把该文件上传到文件表中
                uploadFileMapper.insertUpload(fundProgressAnnexData);
                //把上传附件的id取出,存到课题进展表中
                projectProgressMapper.updateFundProgressAnnexIdByPid(fundProgressAnnexData.getId(), projectProgressDTO.getId());
            }
            if (updateNum > 0) {
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

}
