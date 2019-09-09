package com.xdmd.IntranetEnvironment.extranetExpert.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdmd.IntranetEnvironment.common.*;
import com.xdmd.IntranetEnvironment.company.Pojo.LoginReturnContent;
import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import com.xdmd.IntranetEnvironment.company.mapper.ExtranetLoginLogMapper;
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
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ExtranetExpertServiceImpl implements ExtranetExpertService {
    @Autowired
    private ExtranetExpertMapper extranetExpertMapper;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(ExtranetExpertServiceImpl.class);
    @Autowired
    private ExtranetLoginLogMapper extranetLoginLogMapper;

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
        try {
            String businessFileUrl = FileUploadUtil.UploadExpertInformationFile(expertFile, "专家信息库");
            //把专家信息文件上传到upload_file中
            UploadFile uploadBusinessFile = IntegrationFile.IntegrationFile(expertFile, businessFileUrl, "专家信息库", userInformation.getRealName());
            extranetExpertMapper.uploadFile(uploadBusinessFile);   //对专家信息进行上传
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

        ExpertInformation expertInformation = userInformation.getExpertInformation();

        //设置管专家身份
        userInformation.setIdentity("2");
        //设置为多次登陆
        userInformation.setIsFirst("1");
        //设置等待审核
        userInformation.setIsState("2");    //1：审核通过 2：等待审核  3：审核未通过
        //设置该账号启用
        userInformation.setIsDelete("0");

        //把专家的基本信息存入到基本信息表中，具体信息存入专家表中
        extranetExpertMapper.addUserInformation(userInformation);

        //根据登陆名查询刚刚插入数据的id
        Integer id = extranetExpertMapper.addIdByLoginName(userInformation.getLoginName());
//        ExpertInformation expertInformation1 = userInformation.getExpertInformation();
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
            return resultMap.fail().message("没有此账号,请先注册");
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
            LoginReturnContent loginReturnContent = new LoginReturnContent();
            JSONObject jsonObject = JSON.parseObject(loginReturnContent.toString());
            jsonObject.put("errorReason",reason);
            jsonObject.put("uid",uid);
            return resultMap.fail().message(jsonObject);
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

        JSONObject jsonObject = JSON.parseObject(loginReturnContent.toString());

        //判断该员工是不是第一次登陆
        String isFirst = extranetExpertMapper.queryIsFirst(uid);
        if(isFirst.equals("0")){
            //第一次登陆
            jsonObject.put("isFirst",0);
        }else {
            //多次登陆
            jsonObject.put("isFirst",1);
        }

        //把登陆信息插入到外网登陆日志表中
        ExtranetLoginLog extranetLoginLog = new ExtranetLoginLog();
        extranetLoginLog.setIdentity(2);     //身份( 0：管理员 1：员工 2：专家)
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

    //当专家账号审核不通过时，可以通过uid获取个人信息
    @Override
    public ResultMap queryOwnInformation(Integer uid) {
        //查询专家主表中的信息
        UserInformation userInformation = extranetExpertMapper.queryOwnInformation(uid);

        //查询专家个人信息
        ExpertInformation expertInformation = extranetExpertMapper.queryExperInformation(uid);

        //查询专家的文章列表信息
        List<ExpertInformationArticle> expertInformationArticleList = extranetExpertMapper.queryExpertInformationArticle(expertInformation.getId());
        expertInformation.setExpertInformationArticleList(expertInformationArticleList);

        //查询专家的著作
        List<ExpertInformationBook> expertInformationBookList = extranetExpertMapper.queryExpertInformationBook(expertInformation.getId());
        expertInformation.setExpertInformationBookList(expertInformationBookList);

        //查询专家中的专利
        List<ExpertInformationPatent> expertInformationPatentList = extranetExpertMapper.queryExpertInformationPatent(expertInformation.getId());
        expertInformation.setExpertInformationPatentList(expertInformationPatentList);

        //获取专家中的获奖
        List<ExpertInformationPrizeWinning> expertInformationPrizeWinningList = extranetExpertMapper.queryExpertInformationPrizeWinning(expertInformation.getId());
        expertInformation.setExpertInformationPrizeWinningList(expertInformationPrizeWinningList);

        //获取专家中的主要研究方向
        List<ExpertInformationResearchDirection> expertInformationResearchDirectionList = extranetExpertMapper.queryExpertInformationResearchDirectionList(expertInformation.getId());
        expertInformation.setExpertInformationResearchDirectionList(expertInformationResearchDirectionList);

        //获取专家信息文件的id
        Integer expertInformationUrlId = expertInformation.getExpertInformationUrlId();
        //根据文件Id，获取文件的地址
        String expertInformationUrl = extranetExpertMapper.queryFileUrlByFileId(expertInformationUrlId);
        expertInformation.setExpertInformationUrl(expertInformationUrl);
        String FileName = extranetExpertMapper.queryFileName(expertInformationUrlId);   //根据文件id，获取文件的名称
        expertInformation.setExpertInformationFileName(FileName);

        userInformation.setExpertInformation(expertInformation);

        return resultMap.success().message(userInformation);
    }

    //对个人信息进行修改
    @Override
    public ResultMap updateOwnInformation(UserInformation userInformation, MultipartFile expertInformationFile, String oldExpertInformationFile) throws Exception {
        //判断是否有旧的文件地址上传
        if(oldExpertInformationFile !=null){
            //此时有旧的文件地址上传，判断新上传的文件地址是否正确
            //判断文件输入的格式是否正确
            ArrayList<String> expertFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z", ".pdf"));
            String expertFileName = expertInformationFile.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(expertFileName, expertFileSuffixList);
            if (aBoolean == false) {
                return resultMap.fail().message("请上传正确的专家信息文件格式");
            }
            //再根据旧的文件地址，先把文件给删除掉
            File file = new File(oldExpertInformationFile);
            file.delete();

            //对新上传的文件进行上传
            //对新的专家信息文件进行上传
            String expertInformationFileUrl = FileUploadUtil.fileUpload(expertInformationFile, userInformation.getRealName(), "专家信息库");
            //把专家信息文件上传到upload_file中
            UploadFile uploadExpertInformationFile = IntegrationFile.IntegrationFile(expertInformationFile, expertInformationFileUrl, "专家信息库", userInformation.getRealName());
            extranetExpertMapper.uploadFile(uploadExpertInformationFile);//对文件进行上传
            //对旧的专家信息文件id进行更新
            extranetExpertMapper.updateExpertInformationUrlIdById(userInformation.getUid(), uploadExpertInformationFile.getId());
        }

        //对expertInformation表进行更新
        extranetExpertMapper.updateExpertInformationById(userInformation.getExpertInformation());
        ExpertInformation expertInformation = userInformation.getExpertInformation();

        //对专家信息表进行更新
        //更新专家信息表中的文章表
        List<ExpertInformationArticle> expertInformationArticleList = expertInformation.getExpertInformationArticleList();  //获取专家信息表中文章列表集合
        //根据专家的id，把专家文章表对应的旧的信息删除
        extranetExpertMapper.deleteExpertInformationArticleByExpertId(expertInformation.getId());
        for (ExpertInformationArticle expertInformationArticle : expertInformationArticleList) {
            expertInformationArticle.setExpertId(expertInformation.getId());    //把文章列表中对应的专家信息表的id存入
            extranetExpertMapper.addExpertInformationArticle(expertInformationArticle); //新增专家信息表对应的文章列表
        }

        //更新专家信息表中著作表
        List<ExpertInformationBook> expertInformationBookList = expertInformation.getExpertInformationBookList();   //获取专家信息表中的著作列表集合
        //根据专家的id，把专家著作表对应的旧的信息删除
        extranetExpertMapper.deleteExpertInformationBookByExpertId(expertInformation.getId());
        for (ExpertInformationBook expertInformationBook : expertInformationBookList) {
            expertInformationBook.setExpertId(expertInformation.getId());   //把文章列表中的对应的专家信息表的id存入
            extranetExpertMapper.addExpertInformationBook(expertInformationBook);   //新增专家信息表对应的著作列表
        }

        //更新专家信息表中的专利表
        List<ExpertInformationPatent> expertInformationPatentList = expertInformation.getExpertInformationPatentList(); //获取专家信息表中的专利表集合
        //根据专家的id，把专家专利表对应的旧的信息删除
        extranetExpertMapper.deleteExpertInformationPatentByExpertId(expertInformation.getId());
        for (ExpertInformationPatent expertInformationPatent : expertInformationPatentList) {
            expertInformationPatent.setExpertId(expertInformation.getId());   //把文章列表中的对应的专家信息表的id存入
            extranetExpertMapper.addExpertInformationPatent(expertInformationPatent);   //新增专家信息表对应的专利列表
        }

        //更新专家信息表中的获奖表
        List<ExpertInformationPrizeWinning> expertInformationPrizeWinningList = expertInformation.getExpertInformationPrizeWinningList();   //获取专家信息表中的获奖表集合
        //根据专家的id，把专家获奖表对应的旧的信息删除
        extranetExpertMapper.deleteExpertInformationPrizeWinningByExpertId(expertInformation.getId());
        for (ExpertInformationPrizeWinning expertInformationPrizeWinning : expertInformationPrizeWinningList) {
            expertInformationPrizeWinning.setExpertId(expertInformation.getId());   //把获奖表中的对应的专家信息表的id存入
            extranetExpertMapper.addExpertInformationPrizeWinning(expertInformationPrizeWinning);   //新增专家信息表对应的获奖列表
        }

        //更新专家信息表中的研究方向
        List<ExpertInformationResearchDirection> expertInformationResearchDirectionList = expertInformation.getExpertInformationResearchDirectionList();     //获取专家信息表中的研究方向表集合
        //根据专家的id，把专家研究方向表对应的旧的信息删除
        extranetExpertMapper.deleteExpertInformationResearchDirectionByExpertId(expertInformation.getId());
        for (ExpertInformationResearchDirection expertInformationResearchDirection : expertInformationResearchDirectionList) {
            expertInformationResearchDirection.setExpertId(expertInformation.getId());  //把研究方向中的对应的专家信息表的id存入
            extranetExpertMapper.addExpertInformationResearchDirection(expertInformationResearchDirection); //新增专家信息表对应的研究方向列表
        }
        return resultMap.success().message("更新成功");
    }
}
