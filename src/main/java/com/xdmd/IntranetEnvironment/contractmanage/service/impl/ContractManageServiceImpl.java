package com.xdmd.IntranetEnvironment.contractmanage.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdmd.IntranetEnvironment.common.AnnexUpload;
import com.xdmd.IntranetEnvironment.common.FileSuffixJudge;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.contractmanage.mapper.ContractManageMapper;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.ContractManageDTO;
import com.xdmd.IntranetEnvironment.contractmanage.service.ContractManageService;
import com.xdmd.IntranetEnvironment.subjectmanagement.mapper.UploadFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/8/4
 * @description: 合同管理业务层实现
 */
@Service
public class ContractManageServiceImpl implements ContractManageService {
    @Autowired
    ContractManageMapper contractManageMapper;
    @Autowired
    UploadFileMapper uploadFileMapper;
    ResultMap resultMap=new ResultMap();

    @Override
    public ContractManageDTO getNewData() {
        return contractManageMapper.getNewData();
    }

    /**
     * 新增
     * @param contractManageDTO
     * @return
     */
    @Override
    public int insert(ContractManageDTO contractManageDTO) {
        return contractManageMapper.insert(contractManageDTO);
    }

    /**
     * 根据合同主表id查询
     * @param id
     * @return
     */
    @Override
    public ContractManageDTO getManageInfoById(int id) {
        return contractManageMapper.getManageInfoById(id);
    }


    /**
     * 根据单位id查询本单位的合同
     * @param uid
     * @param subjectCategory
     * @param subjectName
     * @param subjectContact
     * @param subjectContactPhone
     * @param commitmentUnit
     * @param subjectSupervisorDepartment
     * @return
     */
    @Override
    public List<Map> getManageInfoByUid(int uid, String subjectCategory, String subjectName, String subjectContact, String subjectContactPhone, String commitmentUnit, String subjectSupervisorDepartment) {
        return contractManageMapper.getManageInfoByUid(uid,subjectCategory,subjectName,subjectContact,subjectContactPhone,commitmentUnit,subjectSupervisorDepartment);
    }

    /**
     * 全查
     * @return
     */
    @Override
    public ResultMap getAllInfo(String subjectCategory, String subjectName, String subjectContact, String subjectContactPhone, String commitmentUnit, String subjectSupervisorDepartment,int pageNum,int pageSize) {
        try{
           PageHelper.startPage(pageNum,pageSize,true);
           List<Map> contractMap =contractManageMapper.getAllInfo(subjectCategory,subjectName,subjectContact,subjectContactPhone,commitmentUnit,subjectSupervisorDepartment);
           PageInfo pageInfo=new PageInfo(contractMap);
           if(contractMap.size()>0){
                resultMap.success().message(pageInfo);
            }else if(contractMap.size()==0){
                resultMap.fail().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 根据勾选的合同主表id修改相应的中期检查记录【内网中检】
     * @param ids
     * @return
     */
    @Override
    public int updateContractByIds(int mid,List<Long> ids) {
        //ids.forEach(id -> System.out.println("id-->" + id));
        return contractManageMapper.updateContractByIds(mid,ids);
    }

    /**
     * [查詢] 根据中期检查记录查詢相应合同主表
     * @return
     */
    @Override
    public List<Map> getInfoByMidRecord(int mId) {
        return contractManageMapper.getInfoByMidRecord(mId);
    }

    /**
     * [查詢] 根据单位id和中检记录id 查詢本单位的课题合同
     * @param Uid
     * @param Mid
     * @return
     */
    @Override
    public List<Map> getContractByUid(int Uid,int Mid) {
        return contractManageMapper.getContractByUid(Uid,Mid);
    }

    /**
     * 根据合同id更新相应的附件id
     * @param midCheckAnnexId
     * @param expertAssessmentAnnexId
     * @param subjectSuggestAnnexId
     * @param cid
     * @return
     */
    @Override
    public int updateContractByCid(int midCheckAnnexId,int expertAssessmentAnnexId,int subjectSuggestAnnexId,int cid) {
        int num= contractManageMapper.updateContractByCid(midCheckAnnexId,expertAssessmentAnnexId,subjectSuggestAnnexId,cid);
        return num;
    }

    /**
     * 根据合同id更新合同附件id
     * @param contractAnnexId
     * @param cid
     * @return
     */
    @Override
    public int updateContractAnnexIdByCid(int contractAnnexId, int cid) {
        return contractManageMapper.updateContractAnnexIdByCid(contractAnnexId,cid);
    }


    /**
     * 中期检查附件上传
     * @param file
     * @param fileType
     * @return
     * @throws IOException
     */
    @Override
    public String midFileUpload(MultipartFile file,String fileType,int cid) throws IOException {
        //判断文件是否为空
        if (file.isEmpty()) {
            return "上传文件不可为空";
        }
        // 获取文件名拼接当前系统时间作为新文件名
        String nowtime =  new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
        StringBuilder pinjiefileName=new StringBuilder(nowtime).append(file.getOriginalFilename());
        String fileName =pinjiefileName.toString();

        //获取课题名称
        String ketiName=getManageInfoById(cid).getSubjectName();
        //获取文件上传绝对路径
        String FilePath = "D:/xdmd/environment/"+ketiName+"/"+fileType+"/";
        StringBuilder initPath = new StringBuilder(FilePath);
        String filePath=initPath.append(fileName).toString();
        File dest=new File(filePath);

        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //判断上传文件类型是否符合要求
        Boolean typeIsOK=FileSuffixJudge.suffixJudge(file.getOriginalFilename());
        if (typeIsOK==false){
            return "上传的文件类型不符合要求";
        }
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            //保存文件
            file.transferTo(dest);
            // 获取文件大小
            File file1 = new File(filePath);
            String fileSize = String.valueOf(file1.length());
            //封装到uploadfile
            AnnexUpload annexUpload = new AnnexUpload();
            annexUpload.setUploadFilePath(filePath);
            annexUpload.setFileSize(fileSize);
            annexUpload.setUploadFileName(fileName);
            annexUpload.setUploadFileType(fileType);
            annexUpload.setUploadSuffixName(suffixName);
            annexUpload.setCreateAuthor("创建者");
            //文件信息保存到数据库
            int upNo= uploadFileMapper.insertUpload(annexUpload);
            return "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}



