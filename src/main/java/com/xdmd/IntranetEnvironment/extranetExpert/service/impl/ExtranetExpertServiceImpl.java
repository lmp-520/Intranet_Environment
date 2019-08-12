package com.xdmd.IntranetEnvironment.extranetExpert.service.impl;

import com.xdmd.IntranetEnvironment.common.FileSuffixJudgeUtil;
import com.xdmd.IntranetEnvironment.common.FileUploadUtil;
import com.xdmd.IntranetEnvironment.common.MD5Utils;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.company.Pojo.LoginReturnContent;
import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import com.xdmd.IntranetEnvironment.expert.pojo.*;
import com.xdmd.IntranetEnvironment.extranetExpert.mapper.ExtranetExpertMapper;
import com.xdmd.IntranetEnvironment.extranetExpert.service.ExtranetExpertService;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ExtranetExpertServiceImpl implements ExtranetExpertService {
    @Autowired
    private ExtranetExpertMapper extranetExpertMapper;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(ExtranetExpertServiceImpl.class);

    //外网的专家注册
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultMap register(UserInformation userInformation, MultipartFile expertFile) {
        //判断上传的文件后缀名是否正确
        //专家信息文件后缀名的判断
        List<String> expertSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".zip", ".rar", ".7z"));
        //获取文件名
        String expertFileName = expertFile.getOriginalFilename();
        Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(expertFileName, expertSuffixList);
        if (aBoolean == false) {
            return resultMap.fail().message("请上传正确的专家信息文件格式");
        }

        //判断登陆名是否存在
        Integer uid = null;
        uid = extranetExpertMapper.queryUidByLoginNameExist(userInformation.getLoginName());

        if(uid != null){
            //此时意味着登陆名已经存在
            return resultMap.fail().message("登陆名已存在");
        }

        //对专家信息表进行上传
        //对营业执照文件进行上传
        try {
            String businessFileUrl = FileUploadUtil.UploadExpertInformationFile(expertFile, "专家信息库");
            //把营业执照文件上传到upload_file中
            UploadFile uploadBusinessFile = IntegrationFile.IntegrationFile(expertFile, businessFileUrl, "专家信息库", userInformation.getRealName());
            extranetExpertMapper.uploadFile(uploadBusinessFile);   //对文件进行上传
            ExpertInformation expertInformation = userInformation.getExpertInformation();
            expertInformation.setExpertInformationUrlId(uploadBusinessFile.getId());//把上传后的文件id，插入到专家信息表中
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExtranetExpertServiceImpl 中  register 方法 专家信息文件上传失败");
            return resultMap.fail().message("系统异常");
        }

        //对输入的密码进行加密
        String password = userInformation.getPassword();
        //对密码进行加密
        String newPassword  = MD5Utils.md5(password);
        userInformation.setPassword(newPassword);

        //设置管理员身份
        userInformation.setIdentity("2");
        //设置为多次登陆
        userInformation.setIsFirst("1");
        //设置审核通过
        userInformation.setIsState("2");
        //设置该账号启用
        userInformation.setIsDelete("1");

        //把专家的基本信息存入到基本信息表中，具体信息存入专家表中
        extranetExpertMapper.addUserInformation(userInformation);

        //根据登陆名查询刚刚插入数据的id
        Integer id = extranetExpertMapper.addIdByLoginName(userInformation.getLoginName());
        ExpertInformation expertInformation = userInformation.getExpertInformation();
        expertInformation.setAid(id);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        expertInformation.setCreateTime(nowTime);

        //把专家具体信息存储到数据库中
        extranetExpertMapper.addExpertInformation(expertInformation);

        //新增专家信息表中的文章表
        List<ExpertInformationArticle> expertInformationArticleList = expertInformation.getExpertInformationArticleList();  //获取专家信息表中文章列表集合
        for (ExpertInformationArticle expertInformationArticle : expertInformationArticleList) {
            expertInformationArticle.setExpertId(expertInformation.getId());    //把文章列表中对应的专家信息表的id存入
            extranetExpertMapper.addExpertInformationArticle(expertInformationArticle); //新增专家信息表对应的文章列表

        }

        //新增专家信息表中著作表
        List<ExpertInformationBook> expertInformationBookList = expertInformation.getExpertInformationBookList();   //获取专家信息表中的著作列表集合
        for (ExpertInformationBook expertInformationBook : expertInformationBookList) {
            expertInformationBook.setExpertId(expertInformation.getId());   //把文章列表中的对应的专家信息表的id存入
            extranetExpertMapper.addExpertInformationBook(expertInformationBook);   //新增专家信息表对应的著作列表
        }

        //新增专家信息表中的专利表
        List<ExpertInformationPatent> expertInformationPatentList = expertInformation.getExpertInformationPatentList(); //获取专家信息表中的专利表集合
        for (ExpertInformationPatent expertInformationPatent : expertInformationPatentList) {
            expertInformationPatent.setExpertId(expertInformation.getId());   //把文章列表中的对应的专家信息表的id存入
            extranetExpertMapper.addExpertInformationPatent(expertInformationPatent);   //新增专家信息表对应的专利列表
        }

        //新增专家信息表中的获奖表
        List<ExpertInformationPrizeWinning> expertInformationPrizeWinningList = expertInformation.getExpertInformationPrizeWinningList();   //获取专家信息表中的获奖表集合
        for (ExpertInformationPrizeWinning expertInformationPrizeWinning : expertInformationPrizeWinningList) {
            expertInformationPrizeWinning.setExpertId(expertInformation.getId());   //把获奖表中的对应的专家信息表的id存入
            extranetExpertMapper.addExpertInformationPrizeWinning(expertInformationPrizeWinning);   //新增专家信息表对应的获奖列表
        }

        //新增专家信息表中的研究方向
        List<ExpertInformationResearchDirection> expertInformationResearchDirectionList = expertInformation.getExpertInformationResearchDirectionList();     //获取专家信息表中的研究方向表集合
        for (ExpertInformationResearchDirection expertInformationResearchDirection : expertInformationResearchDirectionList) {
            expertInformationResearchDirection.setExpertId(expertInformation.getId());  //把研究方向中的对应的专家信息表的id存入
            extranetExpertMapper.addExpertInformationResearchDirection(expertInformationResearchDirection); //新增专家信息表对应的研究方向列表
        }

        //新增该账号与角色之间的关系
        extranetExpertMapper.addUserRole(userInformation.getUid());

        return resultMap.success().message("注册成功，请等待审核");
    }

    //专家的登录
    @Override
    public ResultMap login(String loginName, String password, HttpServletResponse response) {
        //判断登录名是否存在
        Integer uid = null;
        uid = extranetExpertMapper.queryLoginNameByExist(loginName);

        if(uid ==null){
            //如果id为空，意味着数据库中没有这个账号
            return resultMap.success().message("没有此账号,请先注册");
        }

        //判断这个账号是否被启用
        String isDelete = extranetExpertMapper.queryIsDelete(uid);
        if(isDelete.equals("1")){
            return resultMap.success().message("该账号已被停用");
        }

        //判断审核状态
        String isState = extranetExpertMapper.queryIsState(uid);
        if(isState.equals("2")){
            return resultMap.success().message("账号正在审核中");
        }
        if(isState.equals("3")){
            //此时审核未通过
            //根据uid在专家信息表中获取未通过的原因
            String reason = extranetExpertMapper.queryReasonByUid(uid);
            return resultMap.fail().message(reason);
        }


        //根据登录名获取数据库中的密码
        String sqlPassword = extranetExpertMapper.queryPasswordByLoginName(loginName);
        //对用户的密码进行加密
        String newPassword = MD5Utils.md5(password);
        if(!sqlPassword.equals(newPassword)){
            //此时密码不相同，则返回
            return resultMap.success().message("密码错误");
        }


        //根据uid获取登陆人的真实姓名
        String realName = extranetExpertMapper.queryRealNameByUid(uid);

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
        loginReturnContent.setIdentity(2);
        loginReturnContent.setRealName(realName);

        return resultMap.success().message(loginReturnContent);
    }
}
