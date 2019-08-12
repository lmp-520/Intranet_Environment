package com.xdmd.IntranetEnvironment.backstageManager.service.impl;

import com.xdmd.IntranetEnvironment.backstageManager.controller.PersonInformationManageController;
import com.xdmd.IntranetEnvironment.backstageManager.mapper.PersonInformationManageMapper;
import com.xdmd.IntranetEnvironment.backstageManager.pojo.Subaccount;
import com.xdmd.IntranetEnvironment.backstageManager.service.PersonInformationManageService;
import com.xdmd.IntranetEnvironment.common.FileSuffixJudgeUtil;
import com.xdmd.IntranetEnvironment.common.FileUploadUtil;
import com.xdmd.IntranetEnvironment.common.MD5Utils;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import com.xdmd.IntranetEnvironment.company.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.company.exception.UserNameNotExistentException;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.JwtInformation;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.impl.ExtranetTokenService;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.utils.IntegrationFile;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PersonInformationManageServiceImpl implements PersonInformationManageService {
    @Autowired
    private PersonInformationManageMapper personInformationManageMapper;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(PersonInformationManageController.class);
    @Autowired
    private ExtranetTokenService tokenService;


    //修改自己的密码
    @Override
    public ResultMap changePassword(Integer uid, String oldPassword, String newPassword) {
        //判断旧密码是否正确
        //根据登陆名获取数据库中的密码
        String sqlPassword = personInformationManageMapper.queryPasswordByUsername(uid);
        //对用户输入的旧密码进行加密
        String Md5OldPassword = MD5Utils.md5(oldPassword);
        if (!sqlPassword.equals(Md5OldPassword)) {
            //此时用户输入的旧密码与数据库的对应不上
            return resultMap.success().message("原密码错误");
        }

        //判断该账号是否第一次登陆，如果是，则改变第一次登陆的状态
        String isFirst = personInformationManageMapper.queryIsFirst(uid);
        if (isFirst.equals("0")) {
            //如果此时是第一次登陆  修改判断第几次登陆的状态
            personInformationManageMapper.updateIsFirst(uid);
        }

        //对新的密码进行加密
        String newSqlPassword = MD5Utils.md5(newPassword);

        //使用新密码把旧密码替换掉
        personInformationManageMapper.UpdateNewPasswordByUserName(uid, newSqlPassword);

        return resultMap.success().message("密码更新成功");

    }

    //给单位用户分配子账号
    @Override
    public ResultMap addSubaccount(String token, HttpServletResponse response, UserInformation userInformation, MultipartFile idCardFile) throws Exception {
        JwtInformation jwtInformation = new JwtInformation();
        try {
            jwtInformation = tokenService.compare(response, token);
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

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();


        //判断文件输入的格式是否正确
        ArrayList<String> idCardFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z", ".pdf"));
        String idCardFileName = idCardFile.getOriginalFilename();
        Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(idCardFileName, idCardFileSuffixList);
        if (aBoolean == false) {
            return resultMap.fail().message("请上传正确的身份证扫描件格式");
        }

        //判断登录名是否存在
        Integer uid2 = null;
        uid2 = personInformationManageMapper.queryLoginNameExist(userInformation.getLoginName());
        if (uid2 != null) {
            return resultMap.fail().message("登录名已存在");
        }


        Subaccount subaccount = userInformation.getSubaccount();

        //对身份证扫描件进行上传
        String idCardFileUrl = FileUploadUtil.fileUpload(idCardFile, cname, "员工信息扫描件");
        //把身份证扫描文件上传到upload_file中
        UploadFile uploadExpertGroupCommentsFile = IntegrationFile.IntegrationFile(idCardFile, idCardFileUrl, "员工信息扫描件", uname);
        personInformationManageMapper.uploadFile(uploadExpertGroupCommentsFile);//对文件进行上传
        subaccount.setIdCardUrlId(uploadExpertGroupCommentsFile.getId());  //把身份证文件扫描件的id，存入员工信息表中

        //把员工的基本信息储存到数据库中
        String loginName = userInformation.getLoginName();
        String passWord = loginName + "@123";
        userInformation.setPassword(passWord);
        userInformation.setIdentity("1");   //设置身份
        userInformation.setIsDelete("0");   //设置启用
        userInformation.setIsFirst("0"); //设置为第一次登陆
        userInformation.setIsState("1");    //设置为审核通过

        subaccount.setCreateName(uname);    //存储创建人

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        subaccount.setCreateTime(nowTime);
        subaccount.setCompanyId(cid);
        subaccount.setCompanyName(cname);

        personInformationManageMapper.addNewUserInformation(userInformation);  //把员工信息新增到数据库中

        //把员工具体信息存储到具体的员工表中
        subaccount.setAid(userInformation.getUid());

        //把员工的具体信息存储到具体的员工信息表中
        personInformationManageMapper.addSubaccount(subaccount);

        //新增改账号与角色之间的关系
        personInformationManageMapper.addUserRole(userInformation.getUid());

        return resultMap.success().message("新增成功");
    }

    //查询本单位所有的员工
    @Override
    public ResultMap queryCompanyStaff(String token, HttpServletResponse response) {
        JwtInformation jwtInformation = new JwtInformation();
        try {
            jwtInformation = tokenService.compare(response, token);
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

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();

        //根据cid查询该公司下的员工
        List<UserInformation> userInformationList = new ArrayList<>();
        //查询该公司下的员工信息
        List<Subaccount> subaccountList = personInformationManageMapper.queryStaffByCid(cid);
        for (Subaccount subaccount : subaccountList) {
            //根据aid，获取员工的真实姓名，登陆名
            UserInformation userInformation = personInformationManageMapper.queryStaffInformation(subaccount.getAid());
            //根据idCard的id，获取文件的地址
            String idCardUrl = personInformationManageMapper.queryIdCardUrl(subaccount.getIdCardUrlId());
            subaccount.setIdCardUrl(idCardUrl);
            userInformation.setUid(subaccount.getAid());
            userInformation.setSubaccount(subaccount);
            userInformationList.add(userInformation);
        }
        return resultMap.success().message(userInformationList);
    }

    //改变改账号的状态  如果原本启用，点击后变为停用，若原本停用，点击后则启用
    @Override
    public ResultMap changeState(String token, HttpServletResponse response, Integer uid1, Boolean type) {
        JwtInformation jwtInformation = new JwtInformation();
        try {
            jwtInformation = tokenService.compare(response, token);
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

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();

        //修改这个账号的启用状态
        if (type) {
            //当状态为true时, 则启用这个账号  0 启用 1停用
            personInformationManageMapper.changeStateStart(uid1);
        } else {
            //此时状态为false，停用这个账号
            personInformationManageMapper.changeStateEnd(uid1);
        }
        return resultMap.success().message("设置成功");
    }

    //对员工信息进行修改
    @Override
    public ResultMap modify(String token, HttpServletResponse response, String oldFileUrl, Subaccount subaccount, MultipartFile idCardFile) throws Exception {
        JwtInformation jwtInformation = new JwtInformation();
        try {
            jwtInformation = tokenService.compare(response, token);
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

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();


        //判断用户是否上传新的文件
        if (StringUtils.isEmpty(oldFileUrl)) {
            //此时用户没有上传新的文件 此时仅仅更新数据库中个人信息
            personInformationManageMapper.updateStaffInformation(subaccount.getAid(), subaccount);
            return resultMap.success().message("修改成功");
        } else {
            //此时用户重新上传了新的文件
            //首先判断上传的新的文件格式是否正确
            //判断文件输入的格式是否正确
            ArrayList<String> idCardFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z", ".pdf"));
            String idCardFileName = idCardFile.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(idCardFileName, idCardFileSuffixList);
            if (aBoolean == false) {
                return resultMap.fail().message("请上传正确的身份证扫描件格式");
            }

            //再根据旧的文件地址，先把文件给删除掉
            File file = new File(oldFileUrl);
            file.delete();

            //对身份证扫描件进行上传
            String idCardFileUrl = FileUploadUtil.fileUpload(idCardFile, cname, "员工信息扫描件");
            //把身份证扫描文件上传到upload_file中
            UploadFile uploadExpertGroupCommentsFile = IntegrationFile.IntegrationFile(idCardFile, idCardFileUrl, "员工信息扫描件", uname);
            personInformationManageMapper.uploadFile(uploadExpertGroupCommentsFile);//对文件进行上传
            subaccount.setIdCardUrlId(uploadExpertGroupCommentsFile.getId());   //把上传文件的id赋值给员工信息表中

            //把员工信息更新到数据库中
            personInformationManageMapper.updateStaffInformationAndIdCardFile(subaccount.getAid(), subaccount);
            return resultMap.success().message("修改成功");

        }

    }

}
