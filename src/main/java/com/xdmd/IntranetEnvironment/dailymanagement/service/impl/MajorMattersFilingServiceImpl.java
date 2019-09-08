package com.xdmd.IntranetEnvironment.dailymanagement.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdmd.IntranetEnvironment.common.*;
import com.xdmd.IntranetEnvironment.dailymanagement.mapper.MajorMattersFilingMapper;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.AdjustTypeDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.AdjustmentMattersDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MajorMattersFilingDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.service.MajorMattersFilingService;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.JwtInformation;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.impl.ExtranetTokenService;
import com.xdmd.IntranetEnvironment.subjectmanagement.service.impl.OpenTenderServiceImpl;
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
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/08/19
 * @description: 重大事项变更
 */
@Service
@Transactional
public class MajorMattersFilingServiceImpl implements MajorMattersFilingService {
private static final Logger log = LoggerFactory.getLogger(MajorMattersFilingServiceImpl.class);
    @Autowired
    MajorMattersFilingMapper majorMattersFilingMapper;
    @Autowired
    UploadFileMapper uploadFileMapper;
    @Autowired
    ExtranetTokenService extranetTokenService;
    ResultMap resultMap = new ResultMap();

    /**
     * 新增重大事项变更【外网】
     * @param majorMattersFiling
     * @return
     */
    @Override
    public ResultMap insert(String token, HttpServletResponse response,MajorMattersFilingDTO majorMattersFiling) {
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
            int insertNo = majorMattersFilingMapper.insert(majorMattersFiling);
            insertMidAndUid(cid,majorMattersFiling.getId());
            if (insertNo > 0) {
                resultMap.success().message("新增" + insertNo + "条数据");
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
     * [查詢] 根據主鍵 id 查詢【内外网】
     *
     * @param id
     * @return
     */
    @Override
    public ResultMap getMajorById(int id) {
        try {
            MajorMattersFilingDTO majors = majorMattersFilingMapper.getMajorById(id);
            if (majors != null) {
                resultMap.success().message(majors);
            } else if (majors == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 分页筛选查询【内网】
     * @param subjectName
     * @param commitmentUnit
     * @param adjustTypId
     * @param adjustmentMattersId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultMap getAllMajorInfo(String subjectName, String commitmentUnit, Integer adjustTypId, Integer adjustmentMattersId, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize,true);
            List<Map> majors = majorMattersFilingMapper.getAllMajorInfo(subjectName, commitmentUnit, adjustTypId, adjustmentMattersId);
            PageInfo pageInfo=new PageInfo(majors);
            if (majors != null) {
                resultMap.success().message(pageInfo);
            } else if (majors == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 根据单位id分页筛选查询【内网】
     * @param subjectName
     * @param commitmentUnit
     * @param adjustTypId
     * @param adjustmentMattersId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultMap getAllMajorInfoByUid(String token, HttpServletResponse response, String subjectName, String commitmentUnit, Integer adjustTypId, Integer adjustmentMattersId, int pageNum, int pageSize) {
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
        //String username = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        //String cname = jwtInformation.getCompanyName();
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<Map> majorsByUid = majorMattersFilingMapper.getAllMajorInfoByUid(cid,subjectName, commitmentUnit, adjustTypId, adjustmentMattersId);            //打印信息
            // getContractByUidMap.forEach(info-> System.out.println(info));
            PageInfo pageInfo = new PageInfo(majorsByUid);
            if (majorsByUid != null) {
                resultMap.success().message(pageInfo);
            } else if (majorsByUid == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 查询所有调整类型
     *
     * @return
     */
    @Override
    public ResultMap AdjustType() {
        try {
            List<AdjustTypeDTO> adjustTypeList = majorMattersFilingMapper.getAllAdjustType();
            if (adjustTypeList != null) {
                resultMap.success().message(adjustTypeList);
            } else if (adjustTypeList == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 查询所有调整事项
     *
     * @return
     */
    @Override
    public ResultMap AdjustmentMatters() {
        try {
            List<AdjustmentMattersDTO> adjustmentMattersList = majorMattersFilingMapper.getAllAdjustmentMatters();
            if (adjustmentMattersList != null) {
                resultMap.success().message(adjustmentMattersList);
            } else if (adjustmentMattersList == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 单位关联重大事项主表
     * @param unitId
     * @param majorId
     * @return
     */
    @Override
    public ResultMap insertMidAndUid(int unitId, int majorId) {
        if (majorMattersFilingMapper.insertMidAndUid(unitId,majorId)>0) {
            resultMap.success().message("关联成功");
        }
        else {
            resultMap.fail().message("关联失败");
        }
        return resultMap;
    }

    /**
     *重大事项的附件上传
     * @param token
     * @param response
     * @param changeApplicationAttachment 变更申请表附件
     * @param expertArgumentationAttachment 专家论证意见附件
     * @param filingApplicationAttachment 备案申请表附件
     * @param approvalDocumentsAttachment 批准文件附件
     * @return
     * @throws IOException
     * @throws FileUploadException
     */
    @Override
    public ResultMap majorFileUpload(String token, HttpServletResponse response, int majorid, MultipartFile changeApplicationAttachment, MultipartFile expertArgumentationAttachment, MultipartFile filingApplicationAttachment, MultipartFile approvalDocumentsAttachment) throws IOException, FileUploadException{
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
        Integer uid = jwtInformation.getCid();
        String unitName = jwtInformation.getCompanyName();
        try {
            /**
             * 变更申请表附件
             */
            //判断上传中标文件附件的后缀名是否正确
            String changeApplicationAttachmentName = changeApplicationAttachment.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudge.suffixJudge(changeApplicationAttachmentName);
            if (aBoolean == false) {
                resultMap.fail().message("变更申请表附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取变更申请表附件的地址
            String midCheckAnnexUrl = new OpenTenderServiceImpl().fileUploadUntil(changeApplicationAttachment, unitName, "变更申请表附件");
            //获取文件后缀名
            String changeApplicationAttachmentSuffixName = changeApplicationAttachmentName.substring(changeApplicationAttachmentName.lastIndexOf(".") + 1);
            // 获取文件大小
            File changeApplicationAttachmentFile = new File(midCheckAnnexUrl);
            String changeApplicationAttachmentFileSize = String.valueOf(changeApplicationAttachmentFile.length());
            AnnexUpload changeApplicationAttachmentData = new AnnexUpload(0, midCheckAnnexUrl,changeApplicationAttachmentName, "变更申请表附件", changeApplicationAttachmentSuffixName, changeApplicationAttachmentFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(changeApplicationAttachmentData);
            /**
             * 专家论证意见附件
             */
            //判断上传专家论证意见附件的后缀名是否正确
            String expertArgumentationAttachmentName = expertArgumentationAttachment.getOriginalFilename();
            Boolean bBoolean = FileSuffixJudge.suffixJudge(expertArgumentationAttachmentName);
            if (bBoolean == false) {
                resultMap.fail().message("专家论证意见附件附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取专家论证意见附件的地址
            String expertArgumentationAttachmentUrl = new OpenTenderServiceImpl().fileUploadUntil(expertArgumentationAttachment, unitName, "专家论证意见附件");
            //获取文件后缀名
            String expertArgumentationAttachmentSuffixName = expertArgumentationAttachmentName.substring(expertArgumentationAttachmentName.lastIndexOf(".") + 1);
            // 获取文件大小
            File expertArgumentationAttachmentFile = new File(expertArgumentationAttachmentUrl);
            String expertArgumentationAttachmentFileSize = String.valueOf(expertArgumentationAttachmentFile.length());
            AnnexUpload expertArgumentationAttachmentData = new AnnexUpload(0, expertArgumentationAttachmentUrl, expertArgumentationAttachmentName, "专家论证意见附件", expertArgumentationAttachmentSuffixName, expertArgumentationAttachmentFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(expertArgumentationAttachmentData);
            /**
             * 备案申请表附件
             */
            //判断上传备案申请表附件的后缀名是否正确
            String filingApplicationAttachmentName = filingApplicationAttachment.getOriginalFilename();
            Boolean cBoolean = FileSuffixJudge.suffixJudge(filingApplicationAttachmentName);
            if (cBoolean == false) {
                resultMap.fail().message("备案申请表附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取备案申请表附件的地址
            String filingApplicationAttachmentUrl = new OpenTenderServiceImpl().fileUploadUntil(filingApplicationAttachment, unitName, "备案申请表附件");
            //获取文件后缀名
            String filingApplicationAttachmentSuffixName = filingApplicationAttachmentName.substring(filingApplicationAttachmentName.lastIndexOf(".") + 1);
            // 获取文件大小
            File filingApplicationAttachmentFile = new File(filingApplicationAttachmentUrl);
            String filingApplicationAttachmentFileSize = String.valueOf(filingApplicationAttachmentFile.length());
            AnnexUpload filingApplicationAttachmentData = new AnnexUpload(0, filingApplicationAttachmentUrl, filingApplicationAttachmentName, "备案申请表附件", filingApplicationAttachmentSuffixName, filingApplicationAttachmentFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(filingApplicationAttachmentData);

            /**
             * 批准文件附件
             */
            //判断上传批准文件附件的后缀名是否正确
            String approvalDocumentsAttachmentName = approvalDocumentsAttachment.getOriginalFilename();
            Boolean dBoolean = FileSuffixJudge.suffixJudge(approvalDocumentsAttachmentName);
            if (cBoolean == false) {
                resultMap.fail().message("批准文件附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取批准文件附件的地址
            String approvalDocumentsAttachmentUrl = new OpenTenderServiceImpl().fileUploadUntil(approvalDocumentsAttachment, unitName, "批准文件附件");
            //获取文件后缀名
            String approvalDocumentsAttachmentSuffixName = approvalDocumentsAttachmentName.substring(approvalDocumentsAttachmentName.lastIndexOf(".") + 1);
            // 获取文件大小
            File approvalDocumentsAttachmentFile = new File(approvalDocumentsAttachmentUrl);
            String approvalDocumentsAttachmentFileSize = String.valueOf(approvalDocumentsAttachmentFile.length());
            AnnexUpload approvalDocumentsAttachmentData = new AnnexUpload(0, approvalDocumentsAttachmentUrl, approvalDocumentsAttachmentName, "批准文件附件", approvalDocumentsAttachmentSuffixName, approvalDocumentsAttachmentFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(approvalDocumentsAttachmentData);

            /**
             * 把上传附件的id取出，存到招标备案表中
             */
            majorMattersFilingMapper.updateMajorAnnexId(changeApplicationAttachmentData.getId(),expertArgumentationAttachmentData.getId(),filingApplicationAttachmentData.getId(),approvalDocumentsAttachmentData.getId(),majorid);
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
     * 更新重大事项的审核状态
     * @param id
     * @return
     */
    @Override
    public ResultMap updateMajorStatus(int id) {

            if (majorMattersFilingMapper.updateMajorStatus(id)>0) {
                resultMap.success().message("审核成功");
            }
            else {
                resultMap.fail().message("审核失败");
            }
            return resultMap;
    }

}
