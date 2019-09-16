package com.xdmd.IntranetEnvironment.company.Service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdmd.IntranetEnvironment.common.*;
import com.xdmd.IntranetEnvironment.company.Pojo.AdministratorInformation;
import com.xdmd.IntranetEnvironment.company.Pojo.LoginReturnContent;
import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import com.xdmd.IntranetEnvironment.company.Service.CompanyServiceTwo;
import com.xdmd.IntranetEnvironment.company.mapper.CompanyMapper;
import com.xdmd.IntranetEnvironment.company.mapper.ExtranetLoginLogMapper;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.JwtInformation;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.utils.IntegrationFile;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyServiceTwoImpl implements CompanyServiceTwo {

    @Autowired
    private CompanyMapper companyMapper;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(CompanyServiceTwoImpl.class);
    @Autowired
    private ExtranetLoginLogMapper extranetLoginLogMapper;

    //公司的注册
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultMap register(UserInformation userInformation, MultipartFile businessFile, MultipartFile legalCardIdFile, MultipartFile contactCardFile) {

        //判断上传文件的类型是否正确


//        //营业执照的格式判断
//        List<String> businessSuffixList = new ArrayList<String>(Arrays.asList(".jpg", ".png", ".jpeg", ".pdf"));
//        //获取文件名
//        String businessName = businessFile.getOriginalFilename();
//        Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(businessName, businessSuffixList);
//        if (aBoolean == false) {
//            return resultMap.fail().message("请上传正确的营业执照格式");
//        }
//
//        //法人身份证文件格式的判断
//        List<String> legalCardIdSuffixList = new ArrayList<String>(Arrays.asList(".doc", ".docx", ".zip", ".rar", ".7z"));
//        String legalName = legalCardIdFile.getOriginalFilename();
//        Boolean aBoolean1 = FileSuffixJudgeUtil.SuffixJudge(legalName, legalCardIdSuffixList);
//        if (aBoolean1 == false) {
//            return resultMap.fail().message("请上传正确的法人身份证文件格式");
//        }
//
//        //联系人身份证的格式判断
//        List<String> contactCardSuffixList = new ArrayList<String>(Arrays.asList(".doc", ".docx", ".zip", ".rar", ".7z"));
//        String originalName = contactCardFile.getOriginalFilename();
//        Boolean aBoolean2 = FileSuffixJudgeUtil.SuffixJudge(originalName, contactCardSuffixList);
//        if (aBoolean2 == false) {
//            return resultMap.fail().message("请上传正确的联系人身份证格式");
//        }

        //判断该公司是否已经存在
        Integer cid = null;
        cid = companyMapper.queryCidByCname(userInformation.getAdministratorInformation().getCompanyName());

        if(cid != null){
            //此时意味着 该公司已经被注册过
            return resultMap.fail().message("该公司已经被注册，请找公司管理员分配账号");
        }

        //判断注册的登陆名是否存在
        Integer cid2 = null;
        cid2 = companyMapper.queryLoginNameByExist(userInformation.getLoginName());

        if(cid2 !=null){
            //此时意味着，这个登陆名已经被注册过了
            return resultMap.fail().message("登陆名已存在");
        }

        //对营业执照文件进行上传
        try {
            String businessFileUrl = FileUploadUtil.fileUpload(businessFile, userInformation.getAdministratorInformation().getCompanyName(), "营业执照");
            //把营业执照文件上传到upload_file中
            UploadFile uploadBusinessFile = IntegrationFile.IntegrationFile(businessFile, businessFileUrl, "营业执照", userInformation.getRealName());
            companyMapper.uploadFile(uploadBusinessFile);   //对文件进行上传
            AdministratorInformation administratorInformation = userInformation.getAdministratorInformation();
            administratorInformation.setBusinessUrlId(uploadBusinessFile.getId());//把上传后的文件id，插入到公司管理员表中
        } catch (Exception e) {
            e.printStackTrace();
            log.error("CompanyServiceImpl register 方法 营业执照上传失败");
            return resultMap.fail().message("系统异常");
        }

        //法人身份证文件进行上传
        try {
            String legalCardFileUrl = FileUploadUtil.fileUpload(legalCardIdFile, userInformation.getAdministratorInformation().getCompanyName(), "法人身份证文件");
            //把法人身份证文件上传到upload_file中
            UploadFile uploadLegalCardFile = IntegrationFile.IntegrationFile(legalCardIdFile, legalCardFileUrl, "法人身份证文件", userInformation.getRealName());
            companyMapper.uploadFile(uploadLegalCardFile);
            AdministratorInformation administratorInformation = userInformation.getAdministratorInformation();
            administratorInformation.setLegalCardIdUrlId(uploadLegalCardFile.getId());//把上传后的文件id，插入到公司管理员表中
        } catch (Exception e) {
            e.printStackTrace();
            log.error("CompanyServiceImpl 中 companyRegister 方法 法人身份证上传失败");
            return resultMap.fail().message("系统异常");
        }

        //联系人身份证文件上传
        try {
            String contactCardFIleUrl = FileUploadUtil.fileUpload(contactCardFile, userInformation.getAdministratorInformation().getCompanyName(),"联系人身份证文件");
            //把联系人身份证文件上传到upload_file中
            UploadFile uploadContactCardFile = IntegrationFile.IntegrationFile(contactCardFile, contactCardFIleUrl, "联系人身份证文件", userInformation.getRealName());
            companyMapper.uploadFile(uploadContactCardFile);
            AdministratorInformation administratorInformation = userInformation.getAdministratorInformation();
            administratorInformation.setContactCardUrlId(uploadContactCardFile.getId());//把上传后的文件id，插入到公司管理员表中
        } catch (Exception e) {
            e.printStackTrace();
            log.error("CompanyServiceImpl 中 companyRegister 方法 联系人身份证上传失败");
            return resultMap.fail().message("系统异常");
        }

        //把新注册的公司的名称存入公司表中
        companyMapper.addCompanyName(userInformation.getAdministratorInformation().getCompanyName());

        //获取新注册的公司的id
        Integer cid3 = companyMapper.queryCidByCname(userInformation.getAdministratorInformation().getCompanyName());
        AdministratorInformation administratorInformation = userInformation.getAdministratorInformation();
        administratorInformation.setCompanyId(cid3);

        //对用户输入的密码进行加密
        String password = userInformation.getPassword();
        String newPassword = MD5Utils.md5(password);
        userInformation.setPassword(newPassword);

        //设置管理员身份
        userInformation.setIdentity("0");

        //设置为多次登陆
        userInformation.setIsFirst("1");

        //设置审核通过
        userInformation.setIsState("1");

        //设置该账号启用
        userInformation.setIsDelete("0");

        //把公司注册的基本信息存入到基本信息表中，具体信息存入管理员表中
        companyMapper.addCompanyInformation(userInformation);

        //根据登陆名查询加入到基本信息表中的数据id
        Integer id2 = companyMapper.addIdByLoginName(userInformation.getLoginName());
        administratorInformation.setAid(id2);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        administratorInformation.setCreateTime(nowTime);

        //把公司管理员的具体信息填入数据库中
        companyMapper.addAdministratorInformation(administratorInformation);

        //新增改账号与角色之间的关系
        companyMapper.addUserRole(userInformation.getUid());

        return resultMap.success().message("注册成功");

    }

    //企业的登陆
    @Override
    public ResultMap login(String loginName, String password, HttpServletResponse response) {
        //根据登陆名判断是否存在
        Integer uid = null;
        uid = companyMapper.queryLoginNameByExist(loginName);
        if(uid ==null){
            //如果id为空，意味着数据库中没有这个账号
            return resultMap.fail().message("没有此账号");
        }
        //根据登陆名获取数据库中的密码
        String sqlPassword = companyMapper.querySqlPasswordByLoginName(loginName);
        //对用户输入的密码进行加密
        String newPassword = MD5Utils.md5(password);

        //判断用户输入的密码与数据库中的密码是否相同
        if(!sqlPassword.equals(newPassword)){
            return resultMap.fail().message("密码错误");
        }

        //判断登陆的人是员工还是管理员
        String identity = companyMapper.queryIdentity(uid);

        if(identity.equals("0")){

            //管理员登陆
            //获取公司名 公司id
            String companyName = companyMapper.queryCompanyName(uid);
            Integer cid = companyMapper.queryCompanyId(uid);

            //根据uid获取真实姓名
            String realName = companyMapper.queryRealNameByUid(uid);

            JwtInformation jwtInformation = new JwtInformation();
            jwtInformation.setCid(cid);
            jwtInformation.setCompanyName(companyName);
            jwtInformation.setUid(uid);
            jwtInformation.setUsername(realName);

            //通过JwtUtil 工具生成token
            String token = JwtUtil.geneJsonWebToken(jwtInformation);
            //把生成的token存放在cookie中
            //设置cookie的最大有效时间
            Cookie cookie = new Cookie("token", token);
            //cookie.setMaxAge(60 * 30);//三十分钟
            cookie.setMaxAge(60 * 60 * 24);//二十四小时
            cookie.setPath("/");
            response.addCookie(cookie);

            LoginReturnContent loginReturnContent = new LoginReturnContent();
            loginReturnContent.setUid(uid);
            loginReturnContent.setIdentity(0);
            loginReturnContent.setRealName(realName);
            JSONObject jsonObject = JSON.parseObject(loginReturnContent.toString());
            jsonObject.put("isFirst",1);    //设置为多次登陆

            //把登陆信息插入到外网登陆日志表中
            ExtranetLoginLog extranetLoginLog = new ExtranetLoginLog();
            extranetLoginLog.setIdentity(0);     //身份( 0：管理员 1：员工 2：专家)
            extranetLoginLog.setLoginName(loginName);
            //获取当前时间
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = sdf.format(date);
            extranetLoginLog.setLoginTime(nowTime);
            //新增登陆日志表
            extranetLoginLogMapper.addLoginLog(extranetLoginLog);

            return resultMap.success().message(jsonObject);
        }else {
            //员工登陆
            //获取员工公司名 公司id
            String companyName = companyMapper.queryCompanyNameStaff(uid);
            Integer cid = companyMapper.queryCompanyIdStaff(uid);

            //根据uid获取真实姓名
            String realName = companyMapper.queryRealNameByUidStaff(uid);

            JwtInformation jwtInformation = new JwtInformation();
            jwtInformation.setCid(cid);
            jwtInformation.setCompanyName(companyName);
            jwtInformation.setUid(uid);
            jwtInformation.setUsername(realName);

            //通过JwtUtil 工具生成token
            String token = JwtUtil.geneJsonWebToken(jwtInformation);
            //把生成的token存放在cookie中
            //设置cookie的最大有效时间
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 30);//三十分钟
            cookie.setPath("/");
            response.addCookie(cookie);

            LoginReturnContent loginReturnContent = new LoginReturnContent();
            loginReturnContent.setUid(uid);
            loginReturnContent.setIdentity(1);
            loginReturnContent.setRealName(realName);
            JSONObject jsonObject = JSON.parseObject(loginReturnContent.toString());

            //判断该员工是不是第一次登陆
            String isFirst = companyMapper.queryIsFirst(uid);
            if(isFirst.equals("0")){
                //第一次登陆
                jsonObject.put("isFirst",0);
            }else {
                //多次登陆
                jsonObject.put("isFirst",1);
            }

            //把登陆信息插入到外网登陆日志表中
            ExtranetLoginLog extranetLoginLog = new ExtranetLoginLog();
            extranetLoginLog.setIdentity(1);     //身份( 0：管理员 1：员工 2：专家)
            extranetLoginLog.setLoginName(loginName);
            //获取当前时间
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = sdf.format(date);
            extranetLoginLog.setLoginTime(nowTime);
            //新增登陆日志表
            extranetLoginLogMapper.addLoginLog(extranetLoginLog);

            return resultMap.success().message(jsonObject);
        }

    }

    //外网的的登陆
    @Override
    public ResultMap ExtranetLogin(String loginName, String password, HttpServletResponse response) {
        //首先判断登陆名是否存在
        Integer uid = null;
        uid = companyMapper.queryLoginNameByExist(loginName);
        if(uid == null){
            return resultMap.fail().message("登陆名不存在");
        }

        //根据登陆名判断密码是否正确
        String newPassword = MD5Utils.md5(password);    //首先对密码进行加密
        String sqlPassword = companyMapper.querySqlPasswordByLoginName(loginName);//取出数据库中登陆名对应的密码
        if(!newPassword.equals(sqlPassword)){
            return resultMap.fail().message("密码错误");
        }

        //判断身份
        String identity = companyMapper.queryIdentity(uid);//根据登陆名获取身份
        if(identity.equals("3")|| identity.equals("4")|| identity.equals("5")){
            return resultMap.fail().message("内网账号不允许在外网登陆");
        }

        if(identity.equals("0")){
            //公司管理员登陆

            //获取公司名 公司id
            String companyName = companyMapper.queryCompanyName(uid);
            Integer cid = companyMapper.queryCompanyId(uid);

            //根据uid获取真实姓名
            String realName = companyMapper.queryRealNameByUid(uid);

            JwtInformation jwtInformation = new JwtInformation();
            jwtInformation.setCid(cid);
            jwtInformation.setCompanyName(companyName);
            jwtInformation.setUid(uid);
            jwtInformation.setUsername(realName);

            //通过JwtUtil 工具生成token
            String token = JwtUtil.geneJsonWebToken(jwtInformation);
            //把生成的token存放在cookie中
            //设置cookie的最大有效时间
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 30);//三十分钟
            cookie.setPath("/");
            response.addCookie(cookie);

            LoginReturnContent loginReturnContent = new LoginReturnContent();
            loginReturnContent.setUid(uid);
            loginReturnContent.setIdentity(0);
            loginReturnContent.setRealName(realName);
            JSONObject jsonObject = JSON.parseObject(loginReturnContent.toString());
            jsonObject.put("isFirst",1);    //设置为多次登陆

            //把登陆信息插入到外网登陆日志表中
            ExtranetLoginLog extranetLoginLog = new ExtranetLoginLog();
            extranetLoginLog.setIdentity(0);     //身份( 0：管理员 1：员工 2：专家)
            extranetLoginLog.setLoginName(loginName);
            //获取当前时间
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = sdf.format(date);
            extranetLoginLog.setLoginTime(nowTime);
            //新增登陆日志表
            extranetLoginLogMapper.addLoginLog(extranetLoginLog);

            return resultMap.success().message(jsonObject);
        }else if(identity.equals("1")){
            //此时为员工登陆
            //获取公司名 公司id
            String companyName = companyMapper.queryCompanyName(uid);
            Integer cid = companyMapper.queryCompanyId(uid);

            //根据uid获取真实姓名
            String realName = companyMapper.queryRealNameByUid(uid);

            JwtInformation jwtInformation = new JwtInformation();
            jwtInformation.setCid(cid);
            jwtInformation.setCompanyName(companyName);
            jwtInformation.setUid(uid);
            jwtInformation.setUsername(realName);

            //通过JwtUtil 工具生成token
            String token = JwtUtil.geneJsonWebToken(jwtInformation);
            //把生成的token存放在cookie中
            //设置cookie的最大有效时间
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 30);//三十分钟
            cookie.setPath("/");
            response.addCookie(cookie);

            LoginReturnContent loginReturnContent = new LoginReturnContent();
            loginReturnContent.setUid(uid);
            loginReturnContent.setIdentity(1);
            loginReturnContent.setRealName(realName);
            JSONObject jsonObject = JSON.parseObject(loginReturnContent.toString());

            //判断该员工是不是第一次登陆
            String isFirst = companyMapper.queryIsFirst(uid);
            if(isFirst.equals("0")){
                //第一次登陆
                jsonObject.put("isFirst",0);
            }else {
                //多次登陆
                jsonObject.put("isFirst",1);
            }

            //把登陆信息插入到外网登陆日志表中
            ExtranetLoginLog extranetLoginLog = new ExtranetLoginLog();
            extranetLoginLog.setIdentity(1);     //身份( 0：管理员 1：员工 2：专家)
            extranetLoginLog.setLoginName(loginName);
            //获取当前时间
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = sdf.format(date);
            extranetLoginLog.setLoginTime(nowTime);
            //新增登陆日志表
            extranetLoginLogMapper.addLoginLog(extranetLoginLog);

            return resultMap.success().message(jsonObject);
        }else {
            //此时为专家登陆

            //判断审核状态
            String isState = companyMapper.queryIsState(uid);
            if(isState.equals("2")){
                return resultMap.success().message("账号正在审核中");
            }

            if(isState.equals("3")){
                //此时审核未通过
                //根据uid在专家信息表中获取未通过的原因
                String reason = companyMapper.queryReasonByUid(uid);
                LoginReturnContent loginReturnContent = new LoginReturnContent();
                JSONObject jsonObject = JSON.parseObject(loginReturnContent.toString());
                jsonObject.put("errorReason",reason);
                jsonObject.put("uid",uid);
                return resultMap.fail().message(jsonObject);
            }

            //根据uid获取登陆人的真实姓名
            String realName = companyMapper.queryRealNameByUid(uid);

            JwtInformation jwtInformation = new JwtInformation();
            jwtInformation.setUid(uid);
            jwtInformation.setUsername(realName);

            String token = JwtUtil.geneJsonWebToken(jwtInformation);
            //把生成的token存放在cookie中
            //设置cookie的最大有效时间
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 30);//三十分钟
            cookie.setPath("/");
            response.addCookie(cookie);

            LoginReturnContent loginReturnContent = new LoginReturnContent();
            loginReturnContent.setUid(uid);
            loginReturnContent.setIdentity(3);
            loginReturnContent.setRealName(realName);

            JSONObject jsonObject = JSON.parseObject(loginReturnContent.toString());

            //判断该员工是不是第一次登陆
            String isFirst = companyMapper.queryIsFirst(uid);
            if(isFirst.equals("0")){
                //第一次登陆
                jsonObject.put("isFirst",0);
            }else {
                //多次登陆
                jsonObject.put("isFirst",1);
            }

            //把登陆信息插入到外网登陆日志表中
            ExtranetLoginLog extranetLoginLog = new ExtranetLoginLog();
            extranetLoginLog.setIdentity(3);     //身份( 0：管理员 1：员工 2：专家)
            extranetLoginLog.setLoginName(loginName);
            //获取当前时间
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = sdf.format(date);
            extranetLoginLog.setLoginTime(nowTime);
            //新增登陆日志表
            extranetLoginLogMapper.addLoginLog(extranetLoginLog);

            return resultMap.success().message(jsonObject);


        }

//
//        //此时为登陆成功，获取所有的用户数据
//        UserInformation userInformation = companyMapper.queryUserInformation(loginName);
//
//        LoginReturnContent loginReturnContent = new LoginReturnContent();
//        loginReturnContent.setRealName(userInformation.getRealName());
//        loginReturnContent.setIdentity(Integer.valueOf(userInformation.getIdentity()));
//        loginReturnContent.setUid(userInformation.getUid());
//        JSONObject parseObject = JSON.parseObject(loginReturnContent.toString());
//
//        //判断该账号是否是第一次登陆
//        String isFirst = userInformation.getIsFirst();
//        if(isFirst.equals("0")){
//            //第一次登陆
//            parseObject.put("isFirst",0);
//        }else {
//            //多次登陆
//            parseObject.put("isFirst",1);
//        }
//        //把外网的登陆信息，插入到外网的登陆日志中
//        ExtranetLoginLog extranetLoginLog = new ExtranetLoginLog();
//        extranetLoginLog.setIdentity(Integer.valueOf(userInformation.getIdentity()));     //身份( 0：管理员 1：员工 2：专家)
//        extranetLoginLog.setLoginName(loginName);
//        //获取当前时间
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String nowTime = sdf.format(date);
//        extranetLoginLog.setLoginTime(nowTime);
//        //新增登陆日志表
//        extranetLoginLogMapper.addLoginLog(extranetLoginLog);
//
//        return resultMap.success().message(parseObject);
    }
}
