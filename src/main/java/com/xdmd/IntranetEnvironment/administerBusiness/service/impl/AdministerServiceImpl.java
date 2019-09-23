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
    public ResultMap modify(AdministerInformation administerInformation, String oldBusinessFilUrl, MultipartFile businessFile, String oldLegalCardIdFileUrl, MultipartFile legalCardIdFile, String oldContactCardFileUrl, MultipartFile contactCardFile) throws Exception {

        //判断公司名称是否有发生修改
        if(administerInformation.getCompanyName() == null){
            //此时公司名称没有发生修改

        }else{
            //此时公司名称已经发生修改
            //判断修改后的公司名称是否已经存在
            int num = administerMapper.queryCompanyNameExist(administerInformation.getCompanyName());
            if(num != 0){
                //此时意味着已经存在这个公司的名称
                return resultMap.fail().message("该公司名称已经存在");
            }else {
                //该公司修改后的名称可以使用
                //更新shiro_company_name表中 公司名称
                administerMapper.updateCompanyName(administerInformation.getOldCompanyName(),administerInformation.getCompanyName());
                //更新administrator_information中公司名称
                administerMapper.updateCompanyNameFromAdministratorInformation(administerInformation.getOldCompanyName(),administerInformation.getCompanyName());
                //更新staff_information中的公司名称
                administerMapper.updateCompanyNameFromStaffInformation(administerInformation.getOldCompanyName(),administerInformation.getCompanyName());

            }
        }

        //判断是否有修改这三个文件
        if(oldBusinessFilUrl != null){
            //此时意味着 营业执照已经被修改了
            //根据旧的文件地址，把旧的文件删除
            File file = new File(oldBusinessFilUrl);
            file.delete();

            //再把新的营业执照文件进行上传
            String businessFileUrl = FileUploadUtil.fileUpload(businessFile, "xdmd", "营业执照");
            //把营业执照文件上传到upload_file中
            UploadFile uploadBusinessFile = IntegrationFile.IntegrationFile(businessFile, businessFileUrl, "营业执照", "xdmdAdmin");
            administerMapper.uploadFile(uploadBusinessFile);//对文件进行上传

            //对旧的营业执照文件id进行更新
            administerMapper.uploadBusinessFileIdById(administerInformation.getId(), uploadBusinessFile.getId());
        }


        if(oldLegalCardIdFileUrl !=null){
            //此时意味着 法人身份证文件被修改
            File file = new File(oldLegalCardIdFileUrl);
            file.delete();

            //把新的法人身份证文件进行上传
            String legalCardIdFileUrl = FileUploadUtil.fileUpload(legalCardIdFile, "xdmd", "法人身份证文件");
            //把法人身份证文件上传到upload_file中
            UploadFile uploadLegalCardFile = IntegrationFile.IntegrationFile(legalCardIdFile, legalCardIdFileUrl, "法人身份证文件", "xdmdAdmin");
            administerMapper.uploadFile(uploadLegalCardFile);//对文件进行上传

            //对旧的法人身份证文件id进行更新
            administerMapper.uploadLegalCardFileIdById(administerInformation.getId(), uploadLegalCardFile.getId());
        }

        if(oldContactCardFileUrl != null){
            File file = new File(oldContactCardFileUrl);
            file.delete();

            //把新的联系人身份证文件进行上传
            String contactCardFileUrl = FileUploadUtil.fileUpload(contactCardFile,"xdmd","联系人身份证文件");
            //把联系人身份证文件上传到upload_file中
            UploadFile uploadContactFile = IntegrationFile.IntegrationFile(contactCardFile, contactCardFileUrl, "法人身份证文件", "xdmdAdmin");
            administerMapper.uploadFile(uploadContactFile);//对文件进行上传

            //对旧的联系人身份证文件id进行上传
            administerMapper.uploadContactFileIdById(administerInformation.getId(), uploadContactFile.getId());
        }



        return resultMap;
    }
}