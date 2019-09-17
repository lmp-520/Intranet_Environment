package com.xdmd.IntranetEnvironment.dailymanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdmd.IntranetEnvironment.common.AnnexUpload;
import com.xdmd.IntranetEnvironment.common.FileSuffixJudge;
import com.xdmd.IntranetEnvironment.common.FileUploadException;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.dailymanagement.mapper.ExpertAssessmentMapper;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.ExpertAssessmentDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.service.ExpertAssessmentService;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.JwtInformation;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.impl.ExtranetTokenService;
import com.xdmd.IntranetEnvironment.subjectmanagement.service.impl.OpenTenderServiceImpl;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
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
 * @createDate: 2019/8/17
 * @description: 专家评估业务实现
 */
@Service
public class ExpertAssessmentServiceImpl implements ExpertAssessmentService {
    private static final Logger log = LoggerFactory.getLogger(ExpertAssessmentServiceImpl.class);
    ResultMap resultMap=new ResultMap();

    @Autowired
    ExpertAssessmentMapper expertAssessmentMapper;

    @Autowired
    ExtranetTokenService extranetTokenService;


    /**
     * 新增
     * @param expertAssessment
     * @return

    @Override
    public ResultMap insert(ExpertAssessmentDTO expertAssessment) {
        try{
            int insertNo=expertAssessmentMapper.insertEA(expertAssessment);
            if(insertNo>0){
                resultMap.success().message(expertAssessment.getId());
            }else if(insertNo==0){
                resultMap.fail().message("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }*/


    /**
     * 根据id查询专家评估
     * @param id
     * @return
     */
    @Override
    public ResultMap getEAByid(int id) {
        try{
            ExpertAssessmentDTO ea=expertAssessmentMapper.getEAByid(id);
            if(ea!=null){
                resultMap.success().message(ea);
            }else if(ea==null){
                resultMap.fail().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 查询全部专家评估
     * @return
     */
    @Override
    public ResultMap getAllEA(int pageNum,int pageSize) {
        try{
            PageHelper.startPage(pageNum,pageSize,true);
            List<ExpertAssessmentDTO> eaList=expertAssessmentMapper.getAllEA();
            PageInfo pageInfo=new PageInfo(eaList);
            if(eaList.size()>0){
                resultMap.success().message(pageInfo);
            }else if(eaList.size()==0){
                resultMap.fail().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 从字典里查询全部单选评估内容
     * @return
     */
    @Override
    public ResultMap getAllEvaluationContent() {
        try{
            List<Map> pinggucontent=expertAssessmentMapper.getAllEvaluationContent();
            if(pinggucontent.size()>0){
                resultMap.success().message(pinggucontent);
            }else if(pinggucontent.size()==0){
                resultMap.fail().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 专家评估附件上传
     * @param token
     * @param response
     * @param expertAssessmentAnnex
     * @return
     * @throws IOException
     * @throws FileUploadException
     */

    public ResultMap EAFileUpload(String token, HttpServletResponse response, MultipartFile expertAssessmentAnnex, int eid) throws IOException, FileUploadException {
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
            expertAssessmentMapper.updateExpertAssessmentAnnexIdByCid(expertAssessmentFileAnnexData.getId(),eid);
            resultMap.fail().message("上传专家评估表附件成功");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("附件上传出错:" + e.getMessage());
            throw new FileUploadException("附件上传失败");
        } catch (FileUploadException e) {
            e.printStackTrace();
            log.error("附件上传出错:" + e.getMessage());
            throw new FileUploadException("附件上传失败");
        }
        return resultMap;
    }




}
