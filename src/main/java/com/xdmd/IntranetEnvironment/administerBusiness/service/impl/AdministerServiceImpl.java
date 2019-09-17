package com.xdmd.IntranetEnvironment.administerBusiness.service.impl;

import com.xdmd.IntranetEnvironment.administerBusiness.mapper.AdministerMapper;
import com.xdmd.IntranetEnvironment.administerBusiness.pojo.AdministerInformation;
import com.xdmd.IntranetEnvironment.administerBusiness.pojo.AdministerListPage;
import com.xdmd.IntranetEnvironment.administerBusiness.service.AdministerService;
import com.xdmd.IntranetEnvironment.common.FileUploadUtil;
import com.xdmd.IntranetEnvironment.common.PageBean;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.utils.IntegrationFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class AdministerServiceImpl implements AdministerService {

    @Autowired
    private AdministerMapper administerMapper;
    PageBean pageBean = new PageBean();
    ResultMap resultMap = new ResultMap();

    @Override
    public ResultMap queryAdminister(String companyName, String socialCreditCode, Integer page, Integer total) {
        //首先查询出所有符合条件的企业信息条数
        int allTotal = administerMapper.queryAllAdministerTotal(companyName, socialCreditCode);

        //计算从第几条数据开始
        int newPage = (page - 1) * total;

        //查询出列表页的具体数据信息
        List<AdministerListPage> administerListPageList = administerMapper.queryAdministerInformation(companyName,socialCreditCode,newPage,total);

        pageBean.setAlltotal(allTotal);
        pageBean.setData(administerListPageList);

        return resultMap.success().message(pageBean);
    }

    //根据id 查询出详细的数据
    @Override
    public ResultMap queryInformation(Integer id) {

        //首先通过id，查询出用户基本信息的uid
        int uid = administerMapper.queryUidByid(id);

        //查询出企业基本信息
        AdministerInformation administerInformation = new AdministerInformation();

        //查询出企业的具体信息
        administerInformation = administerMapper.queryInformation(id);

        String realName = administerMapper.queryRealName(uid);
        String loginName = administerMapper.queryLoginName(uid);
        String isDelete = administerMapper.queryIsDelete(uid);  //(0:启用 1：停用)

        administerInformation.setIsDelete(isDelete);
        administerInformation.setRealName(realName);
        administerInformation.setLoginName(loginName);

        //获取营业执照文件的文件名
        String businessFileName = administerMapper.queryFileNameByFileId(administerInformation.getBusinessUrlId());
        String businessFileUrl = administerMapper.queryFileUrlByFileId(administerInformation.getBusinessUrlId());

        //获取法人身份证文件的文件名
        String legalCardIdFileName = administerMapper.queryFileNameByFileId(administerInformation.getLegalCardIdUrlId());
        String legalCardIdFileUrl = administerMapper.queryFileUrlByFileId(administerInformation.getLegalCardIdUrlId());

        //获取联系人文件的文件名
        String contactCardFileName = administerMapper.queryFileNameByFileId(administerInformation.getContactCardUrlId());
        String contactCardFileUrl = administerMapper.queryFileUrlByFileId(administerInformation.getContactCardUrlId());

        administerInformation.setBusinessFileName(businessFileName);
        administerInformation.setBusinessFileUrl(businessFileUrl);

        administerInformation.setLegalCardFileName(legalCardIdFileName);
        administerInformation.setLegalCardFileUrl(legalCardIdFileUrl);

        administerInformation.setContactFileName(contactCardFileName);
        administerInformation.setContactFileUrl(contactCardFileUrl);

        return resultMap.success().message(administerInformation);
    }

    @Override
    public ResultMap changeState(Integer id, Boolean state) {

        //根据用户详细信息表的id，查询出用户基本信息的id
        int uid = administerMapper.queryUidByid(id);

        if(state){
            //此时把账号给启用
            administerMapper.changeStateEnable(uid);
        }else {
            //此时把账号停用
            administerMapper.changeStateStop(uid);
        }
        return resultMap.success().message("修改成功");
    }

    @Override
    public ResultMap modify(AdministerInformation administerInformation, String oldBusinessFilUrl, MultipartFile businessFile, String oldLegalCardIdFileUrl, MultipartFile legalCardIdFile, String oldContactCardFileUrl, MultipartFile contactCardFile) {
        //判断是否有修改这三个文件
        if(oldBusinessFilUrl != null){
            //此时意味着 营业执照已经被修改了
            //根据旧的文件地址，把旧的文件删除
            File file = new File(oldBusinessFilUrl);
            file.delete();

//            //再把新的营业执照文件进行上传
//            String lastReportFileUrl = FileUploadUtil.fileUpload(businessFile, "xdmd", "最终验收证书文件");
//            //把最终验收文件上传到upload_file中
//            UploadFile uploadLastReportFile = IntegrationFile.IntegrationFile(businessFile, lastReportFileUrl, "最终验收证书文件", "xdmdAdmin");
//            administerMapper.uploadFile(uploadLastReportFile);//对文件进行上传
//
//            //对旧的最终验收证书文件id进行更新
//            administerMapper.uploadLastReportFileIdById(caId, uploadLastReportFile.getId());
        }
        return resultMap;
    }
}