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
import java.text.SimpleDateFormat;
import java.util.Date;
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
     * 新增中期检查表[回显id]
     * @param midCheckTemplateDTO
     * @return

    @Override
    public ResultMap insertMidCheckTemplate(MidCheckTemplateDTO midCheckTemplateDTO) {
        try{
            int midchecktemplate= midCheckMapper.insertMidCheckTemplate(midCheckTemplateDTO);
            if(midchecktemplate>0){
                resultMap.success().message(midCheckTemplateDTO.getId());
            }else if(midchecktemplate<0){
                resultMap.success().message("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }
     */


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
    public ResultMap WaiCommitFile(String token, HttpServletResponse response, MidCheckTemplateDTO midCheckTemplateDTO, ExpertAssessmentDTO expertAssessmentDTO, MultipartFile midCheckAnnex, MultipartFile expertAssessmentAnnex) {
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

        try{
            //中期检查表模板
            int mctNum= midCheckMapper.insertMidCheckTemplate(midCheckTemplateDTO);

            int eaNum=midCheckMapper.insertEA(expertAssessmentDTO);
            //genju
            int updateNum=midCheckMapper.updateContractMidCheckUpLoadFileIdByCid(midCheckTemplateDTO.getId(),expertAssessmentDTO.getId(),cid);

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
            }else if(mctNum==0 && eaNum==0){
                resultMap.success().message("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
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
                resultMap.success().message("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
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
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
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
    public ResultMap midCheckExpertOpinionFileUpload(String token, HttpServletResponse response, MultipartFile midCheckExpertOpinion, int mid) throws IOException, FileUploadException {
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

        //判断文件是否为空
        if (midCheckExpertOpinion.isEmpty()) {
            resultMap.fail().message("上传文件不可为空");
        }
        // 获取文件名拼接当前系统时间作为新文件名
        String nowtime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        StringBuilder pinjiefileName = new StringBuilder(nowtime).append(midCheckExpertOpinion.getOriginalFilename());
        String fileName = pinjiefileName.toString();

        //根据合同主表的id 获取该公司的名字
        //String unitName = contractManageMapper.queryUnitNameBycid(uid);

        //获取文件上传绝对路径
        String path = "D:/xdmd/environment/" +"第"+mid+"次中期检查记录"+"/"+ "专家总意见附件" + "/";
        StringBuilder initPath = new StringBuilder(path);
        String filePath = initPath.append(fileName).toString();
        File dest = new File(filePath);

        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //判断上传文件类型是否符合要求
        Boolean typeIsOK = FileSuffixJudge.suffixJudge(midCheckExpertOpinion.getOriginalFilename());
        if (typeIsOK == false) {
            resultMap.fail().message("上传的文件类型不符合要求");
        }
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            //保存文件
            midCheckExpertOpinion.transferTo(dest);
            // 获取文件大小
            String fileSize = String.valueOf(dest.length());
            //封装对象
            AnnexUpload annexUpload = new AnnexUpload(0, filePath, fileName, "专家总意见附件", suffixName, fileSize, null, username);
            //保存到数据库中
            uploadFileMapper.insertUpload(annexUpload);
            //更改专家总意见附件的id
            midCheckMapper.updateMidCheckExpertOpinionAnnexIdByCid(annexUpload.getId(),mid);
            resultMap.success().message("专家总意见附件上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("专家总意见附件上传失败");
        }
        return resultMap;

    }



    /**
     * 更新合同中期检查状态【当外网提交完所有材料但内网未审核】
     * @return
     */
    @Override
    public ResultMap updateContractMidCheckStateOne() {
        try {
            int updateNum = midCheckMapper.updateContractMidCheckStateOne();
            if (updateNum > 0) {
                resultMap.success().message("更新成功");
            } else if (updateNum== 0) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 更新合同中期检查状态【当外网提交完所有材料且内网已审核并提交相应材料】
     * @return
     */
    @Override
    public ResultMap updateContractMidCheckStateTwo() {
        try {
            int updateNum = midCheckMapper.updateContractMidCheckStateTwo();
            if (updateNum > 0) {
                resultMap.success().message("更新成功");
            } else if (updateNum== 0) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
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
            List<UploadFile> fileinfo = midCheckMapper.getMidCheckExpertOpinionInfo(mid);
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


        //判断文件是否为空
        if (file.isEmpty()) {
            resultMap.fail().message("上传文件不可为空");
        }
        // 获取文件名拼接当前系统时间作为新文件名
        String nowtime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        StringBuilder pinjiefileName = new StringBuilder(nowtime).append(file.getOriginalFilename());
        String fileName = pinjiefileName.toString();

        //根据合同主表的id 获取该公司的名字
        //String unitName = contractManageMapper.queryUnitNameBycid(uid);

        //获取文件上传绝对路径
        String path = "D:/xdmd/environment/+" + "未知类型附件/";
        StringBuilder initPath = new StringBuilder(path);
        String filePath = initPath.append(fileName).toString();
        File dest = new File(filePath);

        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //判断上传文件类型是否符合要求
        Boolean typeIsOK = FileSuffixJudge.suffixJudge(file.getOriginalFilename());
        if (typeIsOK == false) {
            resultMap.fail().message("上传的文件类型不符合要求");
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
            AnnexUpload annexUpload = new AnnexUpload(0, filePath, fileName, "未知类型附件", suffixName, fileSize, null, username);
            //保存到数据库中
            int insertNum = uploadFileMapper.insertUpload(annexUpload);
            //更改相应合同附件id
            midCheckMapper.updateContractWeiZhiAnnexIdByCid(annexUpload.getId(), cid);
            resultMap.success().message("上传合同附件成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("上传失败");
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
        try{
            int midcheckrecord= midCheckMapper.updateMidCheckRecord();
            if(midcheckrecord>0){
                resultMap.success().message("更新成功");
            }else if(midcheckrecord<0){
                resultMap.success().message("更新失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }


    /**
     * [更新] 中期检察记录状态
     * @return
     */
    @Override
    public ResultMap updateMidCheckRecord() {
        try{
            int midcheckrecord= midCheckMapper.updateMidCheckRecord();
            if(midcheckrecord>0){
                resultMap.success().message("更新成功");
            }else if(midcheckrecord<0){
                resultMap.success().message("更新失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }
}
