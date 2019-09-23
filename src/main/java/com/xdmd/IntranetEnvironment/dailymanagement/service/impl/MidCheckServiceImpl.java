package com.xdmd.IntranetEnvironment.dailymanagement.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdmd.IntranetEnvironment.common.AnnexUpload;
import com.xdmd.IntranetEnvironment.common.FileSuffixJudge;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.common.UploadFileMapper;
import com.xdmd.IntranetEnvironment.company.exception.FileUploadException;
import com.xdmd.IntranetEnvironment.dailymanagement.mapper.MidCheckMapper;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.ExpertAssessmentDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckRecordDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckTemplateDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.service.MidCheckService;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.JwtInformation;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.impl.ExtranetTokenService;
import com.xdmd.IntranetEnvironment.subjectmanagement.service.impl.OpenTenderServiceImpl;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import com.xdmd.IntranetEnvironment.user.pojo.User;
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
 * @createDate: 2019/8/12
 * @description: 中期检查记录实现
 */
@Service
public class MidCheckServiceImpl implements MidCheckService {
    private static final Logger log = LoggerFactory.getLogger(MidCheckServiceImpl.class);
    @Autowired
    MidCheckMapper midCheckMapper;
    @Autowired
    ExtranetTokenService extranetTokenService;
    @Autowired
    TokenService tokenService;
    @Autowired
    UploadFileMapper uploadFileMapper;


    //状态
    ResultMap resultMap=new ResultMap();



    /**
     * 外网提交中期检查文件材料
     * @param token
     * @param response
     * @param midCheckTemplateDTO
     * @param expertAssessmentDTO
     * @param midCheckAnnex
     * @param expertAssessmentAnnex
     * @return
     */
    @Override
    public ResultMap WaiCommitFile(String token, HttpServletResponse response, Integer cid, MidCheckTemplateDTO midCheckTemplateDTO, ExpertAssessmentDTO expertAssessmentDTO, MultipartFile midCheckAnnex, MultipartFile expertAssessmentAnnex) {
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
        String cname = jwtInformation.getCompanyName();

        try{
            //中期检查表模板
            int mctNum= midCheckMapper.insertMidCheckTemplate(midCheckTemplateDTO);
            System.out.println(mctNum);
            int eaNum=midCheckMapper.insertEA(expertAssessmentDTO);
            System.out.println(mctNum);
            //把中期检查模板表的id和专家评估表的id更新到合同表对应字段
            int updateNum=midCheckMapper.updateContractMidCheckUpLoadFileIdByCid(midCheckTemplateDTO.getId(),expertAssessmentDTO.getId(),cid);
            System.out.println(updateNum);
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
            String midCheckAnnexUrl = new OpenTenderServiceImpl().fileUploadUntil(midCheckAnnex, cname, "中期检查附件");
            //获取文件后缀名
            String midCheckAnnexSuffixName = midCheckAnnexName.substring(midCheckAnnexName.lastIndexOf(".") + 1);
            // 获取文件大小
            File midCheckAnnexFile = new File(midCheckAnnexUrl);
            String winningDocumentFileSize = String.valueOf(midCheckAnnexFile.length());
            AnnexUpload midCheckFileAnnexData = new AnnexUpload(0, midCheckAnnexUrl, midCheckAnnexName, "中期检查附件", midCheckAnnexSuffixName, winningDocumentFileSize, null, username);
            //把该文件上传到中期检查文件表中
            uploadFileMapper.insertUpload(midCheckFileAnnexData);
            midCheckMapper.updateMidCheckAnnexIdByMid(midCheckFileAnnexData.getId(),midCheckTemplateDTO.getId());

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
            String expertAssessmentAnnexUrl = new OpenTenderServiceImpl().fileUploadUntil(expertAssessmentAnnex, cname, "专家评估附件");
            //获取文件后缀名
            String expertAssessmentAnnexSuffixName = expertAssessmentAnnexName.substring(expertAssessmentAnnexName.lastIndexOf(".") + 1);
            // 获取文件大小
            File expertAssessmentAnnexFile = new File(expertAssessmentAnnexUrl);
            String expertAssessmentAnnexFileSize = String.valueOf(expertAssessmentAnnexFile.length());
            AnnexUpload expertAssessmentFileAnnexData = new AnnexUpload(0, expertAssessmentAnnexUrl, expertAssessmentAnnexName, "专家评估附件", expertAssessmentAnnexSuffixName, expertAssessmentAnnexFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(expertAssessmentFileAnnexData);
            midCheckMapper.updateExpertAssessmentAnnexIdByCid(expertAssessmentFileAnnexData.getId(),expertAssessmentDTO.getId());

            resultMap.success().message("附件上传成功");

            if(mctNum>0 && eaNum>0){
                //resultMap.success().message(midCheckTemplateDTO.getId());
                //resultMap.success().message(expertAssessmentDTO.getId());
                resultMap.success().message("提交成功");
                int num= midCheckMapper.updateContractMidCheckStateOne(cid);
                System.out.println(num);
            }else if(mctNum==0 && eaNum==0){
                resultMap.fail().message("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;

    }

    /**
     * 根据中期检查表id查询详情
     * @param midchecktemplateid
     * @return
     */
    @Override
    public ResultMap getAllMidCheckTemplate(int midchecktemplateid) {
        try{
           MidCheckTemplateDTO midCheckTemplateDTO= midCheckMapper.getAllMidCheckTemplate(midchecktemplateid);
            if(midCheckTemplateDTO!=null){
                resultMap.success().message(midCheckTemplateDTO);
            }else if(midCheckTemplateDTO==null){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 中期检查附件上传
     * @param token
     * @param response
     * @param midCheckAnnex
     * @return
     * @throws IOException
     * @throws FileUploadException
     */
    public ResultMap midCheckFileUpload(String token, HttpServletResponse response, MultipartFile midCheckAnnex, int mid) throws IOException, FileUploadException {
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
        String cname = jwtInformation.getCompanyName();


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
            String midCheckAnnexUrl = new OpenTenderServiceImpl().fileUploadUntil(midCheckAnnex, cname, "中期检查附件");
            //获取文件后缀名
            String midCheckAnnexSuffixName = midCheckAnnexName.substring(midCheckAnnexName.lastIndexOf(".") + 1);
            // 获取文件大小
            File midCheckAnnexFile = new File(midCheckAnnexUrl);
            String winningDocumentFileSize = String.valueOf(midCheckAnnexFile.length());
            AnnexUpload midInspectionAnnexData = new AnnexUpload(0, midCheckAnnexUrl, midCheckAnnexName, "中期检查附件", midCheckAnnexSuffixName, winningDocumentFileSize, null, username);
            //把该文件上传到中期检查文件表中
            midCheckMapper.updateMidCheckAnnexIdByMid(midInspectionAnnexData.getId(),mid);
            resultMap.success().message("上传中期检查表附件成功");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("附件上传出错:" + e.getMessage());
            throw new FileUploadException("附件上传失败");
        } catch (com.xdmd.IntranetEnvironment.common.FileUploadException e) {
            e.printStackTrace();
        }
        return resultMap;
    }





    /**
     * [新增] 中期检察记录[回显id]
     * @param midCheckRecordDTO
     * @return
     */
    @Override
    public ResultMap insertMidCheckRecord(MidCheckRecordDTO midCheckRecordDTO) {
        try{
            int midcheckrecord= midCheckMapper.insertMidCheckRecord(midCheckRecordDTO);
            if(midcheckrecord>0){
                resultMap.success().message(midCheckRecordDTO.getId());
            }else if(midcheckrecord<0){
                resultMap.fail().message("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }



    /**
     * [查询] 中期检查记录状态
     * @return
     */
    @Override
    public ResultMap getMidCheckRecord(int pageNum, int pageSize) {
        try{
            String orderby="id desc";
            PageHelper.startPage(pageNum,pageSize,orderby);
            List<MidCheckRecordDTO> midCheckRecordDTOs= midCheckMapper.getMidCheckRecord();
            PageInfo pageInfo=new PageInfo(midCheckRecordDTOs);
            if(midCheckRecordDTOs.size()>0){
                resultMap.success().message(pageInfo);
            }else if(midCheckRecordDTOs.size()==0){
                resultMap.fail().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 专家总意见附件上传
     * @param token
     * @param response
     * @param midCheckExpertOpinion
     * @param mid
     * @return
     * @throws IOException
     * @throws FileUploadException
     */
    @Override
    public ResultMap midCheckExpertOpinionFileUpload(String token, HttpServletResponse response, MultipartFile midCheckExpertOpinion, int mid){
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

        //当前登录者
        //Integer uid = user.getId();
        String username = user.getUsername();

        try {
            /**
             * 专家总意见附件
             */
            //判断文件是否为空
             if (midCheckExpertOpinion.isEmpty()) {
                 resultMap.fail().message("上传文件不可为空");
             }
            //判断上传中标文件附件的后缀名是否正确
            String midCheckExpertOpinionName = midCheckExpertOpinion.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudge.suffixJudge(midCheckExpertOpinionName);
            if (aBoolean == false) {
                resultMap.fail().message("专家总意见附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取中标文件附件的地址
            String midCheckExpertOpinionUrl = new OpenTenderServiceImpl().fileUploadUntil(midCheckExpertOpinion, "环保评估中心", "专家总意见附件");
            //获取文件后缀名
            String midCheckExpertOpinionSuffixName = midCheckExpertOpinionName.substring(midCheckExpertOpinionName.lastIndexOf(".") + 1);
            // 获取文件大小
            File midCheckExpertOpinionFile = new File(midCheckExpertOpinionUrl);
            String midCheckExpertOpinionFileSize = String.valueOf(midCheckExpertOpinionFile.length());
            AnnexUpload midCheckExpertOpinionData = new AnnexUpload(0, midCheckExpertOpinionUrl, midCheckExpertOpinionName, "专家总意见附件", midCheckExpertOpinionSuffixName, midCheckExpertOpinionFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(midCheckExpertOpinionData);
            //更改专家总意见附件的id
            midCheckMapper.updateMidCheckExpertOpinionAnnexIdByCid(midCheckExpertOpinionData.getId(),mid);
            //当上传完专家意见表后修改中期检查发起状态
            int midcheckrecordNum=midCheckMapper.updateMidCheckRecord(mid);
            System.out.println(midcheckrecordNum);
            resultMap.success().message("专家总意见附件上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (com.xdmd.IntranetEnvironment.common.FileUploadException e) {
            e.printStackTrace();
        }
        return resultMap;

    }




    /**
     * 根据合同id查询关联的中期检查模板表
     * @param cid
     * @return
     */
    @Override
    public ResultMap getMidCheckTemplateByCid(int cid) {
        try{
            MidCheckTemplateDTO midCheckTemplateDTO= midCheckMapper.getMidCheckTemplateByCid(cid);
            if(midCheckTemplateDTO!=null){
                resultMap.success().message(midCheckTemplateDTO);
            }else if(midCheckTemplateDTO==null){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 根据合同id查询关联专家评估表
     * @param cid
     * @return
     */
    @Override
    public ResultMap getExpertAssessmentByCid(int cid) {
        try{
            ExpertAssessmentDTO expertAssessmentDTO= midCheckMapper.getExpertAssessmentByCid(cid);
            if(expertAssessmentDTO!=null){
                resultMap.success().message(expertAssessmentDTO);
            }else if(expertAssessmentDTO==null){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }


    /**
     *获取专家中期检查模板表附件的路径和文件名
     * @param mid
     * @return
     */
    @Override
    public ResultMap getMidCheckFileInfo(int mid) {
        try {
            List<Map> fileinfo = midCheckMapper.getMidCheckFileInfo(mid);
            if (fileinfo.size() > 0) {
                resultMap.success().message(fileinfo);
            } else if (fileinfo.size() == 0) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 获取中期检查专家总意见附件的路径和文件名
     * @param mid
     * @return
     */
    @Override
    public ResultMap getMidCheckExpertOpinionInfo(int mid) {
        try {
            UploadFile fileinfo = midCheckMapper.getMidCheckExpertOpinionInfo(mid);
            if (fileinfo!=null) {
                resultMap.success().message(fileinfo);
            } else if (fileinfo==null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }



    /**
     * 获取专家评估附件的路径和文件名
     * @param eid
     * @return
     */
    @Override
    public ResultMap getEAFileInfo(int eid) {
        try {
            List<Map> fileinfo = midCheckMapper.getEAFileInfo(eid);
            if (fileinfo.size() > 0) {
                resultMap.success().message(fileinfo);
            } else if (fileinfo.size() == 0) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 未知类型附件上传
     * @param file
     * @param cid
     * @return
     * @throws IOException
     */
    @Override
    public ResultMap AnnexUpload(String token, HttpServletResponse response, MultipartFile file, int cid) throws IOException {
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
        //当前登录者
        //Integer uid = user.getId();
        String username = user.getUsername();

        try {
            /**
             * 未知类型附件
             */
            //判断文件是否为空
            if (file.isEmpty()) {
                resultMap.fail().message("上传文件不可为空");
            }
            //判断上传中标文件附件的后缀名是否正确
            String fileName = file.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudge.suffixJudge(fileName);
            if (aBoolean == false) {
                resultMap.fail().message("未知类型附件的文件格式不正确,请上传正确的文件格式");
            }
            //获取中标文件附件的地址
            String fileUrl = new OpenTenderServiceImpl().fileUploadUntil(file, "单位名称", "未知类型附件");
            //获取文件后缀名
            String fileSuffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 获取文件大小
            File weizhiFile = new File(fileUrl);
            String weizhiFileSize = String.valueOf(weizhiFile.length());
            AnnexUpload weizhiFileData = new AnnexUpload(0, fileUrl, fileName, "未知类型附件", fileSuffixName, weizhiFileSize, null, username);
            //把该文件上传到文件表中
            uploadFileMapper.insertUpload(weizhiFileData);
            //更改合同中相应未知类型附件id
            midCheckMapper.updateContractWeiZhiAnnexIdByCid(weizhiFileData.getId(), cid);
            //当前合同上传完未知附件后修改合同中期检查状态
            int updateNum = midCheckMapper.updateContractMidCheckStateTwo(cid);
            resultMap.success().message("未知类型附件上传成功");
        } catch (com.xdmd.IntranetEnvironment.common.FileUploadException e) {
            e.printStackTrace();
        }
        return resultMap;
    }


    /**
     *获取未知类型附件的路径和文件名
     * @param cid
     * @return
     */
    @Override
    public ResultMap getWeizhiFileInfo(int cid) {
        try {
            UploadFile fileinfo = midCheckMapper.getWeizhiFileInfo(cid);
            if (fileinfo!=null) {
                resultMap.success().message(fileinfo);
            } else if (fileinfo==null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }



    /**
     * 在提交回显通过最终审核的关联常用的合同信息
     * @param token
     * @param response
     * @return
     */
    @Override
    public ResultMap queryAllEndContractInfo(String token, HttpServletResponse response) {
        //解析token中的数据
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
        //根据登陆信息获取单位id
        Integer unitId = jwtInformation.getCid();
        try {
            //获取该公司所有审核通过的招标id
            List<Map> queryAllEndContractInfo = midCheckMapper.queryAllEndContractInfo();
            if (queryAllEndContractInfo.size() > 0) {
                resultMap.success().message(queryAllEndContractInfo);
            } else {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 判断中期检查状态
     * @param cid
     * @return
     */
    @Override
    public ResultMap getMidRecordState(int cid) {
        try{
            String midcheckrecordstate= midCheckMapper.getMidRecordState(cid);
            if(midcheckrecordstate!=null){
                resultMap.success().message(midcheckrecordstate);
            }else if(midcheckrecordstate==null){
                resultMap.fail().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

}
