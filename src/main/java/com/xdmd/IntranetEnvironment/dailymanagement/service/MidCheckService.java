package com.xdmd.IntranetEnvironment.dailymanagement.service;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.company.exception.FileUploadException;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.ExpertAssessmentDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckRecordDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckTemplateDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MidCheckService {
    /**
     * 新增中期检查表
     * @param midCheckTemplateDTO
     * @return

    ResultMap insertMidCheckTemplate(MidCheckTemplateDTO midCheckTemplateDTO);
    */
    /**
     * [新增] 专家评估表
     * @author Kong
     * @date 2019/08/17
     *
     * @return
    ResultMap insert(ExpertAssessmentDTO expertAssessment);
     */

    /**
     * 外网提交中期检查文件材料
     * @param midCheckTemplateDTO
     * @return
     * */
    ResultMap WaiCommitFile(String token, HttpServletResponse response, MidCheckTemplateDTO midCheckTemplateDTO, ExpertAssessmentDTO expertAssessmentDTO, MultipartFile midCheckAnnex, MultipartFile expertAssessmentAnnex);

    /**
     * 根据中期检查表id查询详情
     * @param midchecktemplateid
     * @return
     */
    ResultMap getAllMidCheckTemplate(int midchecktemplateid);


    /**
     *中期检查附件 && 专家评估附件 && 上传
     * @param token
     * @param response
     * @param midCheckAnnex
     * @return
     * @throws IOException
    ResultMap midCheckFileUpload(String token, HttpServletResponse response, MultipartFile midCheckAnnex, int mid) throws IOException, FileUploadException;
     */



    /**
     * [新增] 中期检察记录
     * @author Kong
     * @date 2019/08/14
     **/
    ResultMap insertMidCheckRecord(MidCheckRecordDTO midCheckRecordDTO);




    /**
     * [更新] 中期检察记录状态
     * @return
     */
    ResultMap updateMidCheckRecord();


    /**
     * [查询] 中期检察记录状态
     * @return
     */
    ResultMap getMidCheckRecord(int pageNum, int pageSize);

    /**
     *专家总意见附件上传
     * @param token
     * @param response
     * @param midCheckAnnex
     * @return
     * @throws IOException
     */
    ResultMap midCheckExpertOpinionFileUpload(String token, HttpServletResponse response, MultipartFile midCheckAnnex, int mid) throws IOException, FileUploadException;




    /**
     * 获取中期检查专家总意见附件的路径和文件名
     * @param mid
     * @return
     */
    ResultMap getMidCheckExpertOpinionInfo(int mid);

    /**
     * 更新合同中期检查状态【当外网提交完所有材料但内网未审核】
     * @return
     */
    ResultMap updateContractMidCheckStateOne();

    /**
     * 更新合同中期检查状态【当外网提交完所有材料且内网已审核并提交相应材料】
     * @return
     */
    ResultMap updateContractMidCheckStateTwo();


    /**
     * 根据合同id查询关联的中期检查模板表
     * @param cid
     * @return
     */
    ResultMap getMidCheckTemplateByCid(int cid);

    /**
     * 获取中期检查表附件的路径和文件名
     * @param mid
     * @return
     */
    ResultMap getMidCheckFileInfo(int mid);

    /**
     * 根据合同id查询关联的中期检查模板表
     * @param cid
     * @return
     */
    ResultMap getExpertAssessmentByCid(int cid);

    /**
     * 获取专家评估附件的路径和文件名
     * @param eid
     * @return
     */
    ResultMap getEAFileInfo(int eid);



    /**
     * 未知类型附件上传
     * @param file
     * @return
     */
    ResultMap AnnexUpload(String token,HttpServletResponse response,MultipartFile file, int cid) throws IOException;


    /**
     * 获取未知类型附件的路径和文件名
     * @return
     */
    ResultMap getWeizhiFileInfo(int cid);






}
