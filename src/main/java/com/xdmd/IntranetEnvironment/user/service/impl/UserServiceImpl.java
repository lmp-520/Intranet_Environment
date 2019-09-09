package com.xdmd.IntranetEnvironment.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdmd.IntranetEnvironment.common.ExtranetLoginLog;
import com.xdmd.IntranetEnvironment.common.MD5Utils;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.company.Pojo.JwtInformation;
import com.xdmd.IntranetEnvironment.company.Pojo.LoginReturnContent;
import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import com.xdmd.IntranetEnvironment.user.mapper.UserMapper;
import com.xdmd.IntranetEnvironment.user.service.UserService;
import com.xdmd.IntranetEnvironment.user.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    ResultMap resultMap = new ResultMap();

    //用户的登陆
    @Override
    public ResultMap login(String name, String password, HttpServletResponse response) {

        //判断登陆名是否存在
        String sqlName = null;
        //判断是否存在这个登陆名
        sqlName = userMapper.queryLoginName(name);
        if(sqlName ==null){
            return resultMap.fail().message("用户名不存在");
        }

        //获取数据库中，该登陆名对应的密码
        String sqlPassword = userMapper.querySqlPasswordByName(name);
        //对用户输入的密码进行加密
        String newPassword = MD5Utils.md5(password);
        if(!sqlPassword.equals(newPassword)){
            //此时密码不正确
            return resultMap.fail().message("密码错误");
        }

        //此时登陆成功，根据登陆名取出数据
        UserInformation userInformation = userMapper.queryAllInformation(name);

        //获取该登陆人的身份
        String identity = userInformation.getIdentity();

        //判断此人是否是内网人员   身份( 0：管理员 1：员工 2：专家 3: 科长 4：科员 5：法规科技处)
        if(identity.equals("0") || identity.equals("1") || identity.equals("2")){
            return resultMap.fail().message("该账号是外网账号，不允许在内网登陆");
        }
        LoginReturnContent loginReturnContent = new LoginReturnContent();
        loginReturnContent.setUid(userInformation.getUid());
        loginReturnContent.setIdentity(Integer.parseInt(userInformation.getIdentity()));
        loginReturnContent.setRealName(userInformation.getRealName());
        JSONObject parseObject = JSON.parseObject(loginReturnContent.toString());

        //判断该账号是否是第一次登陆
        String isFirst = userInformation.getIsFirst();
        if(isFirst.equals("0")){
            //第一次登陆
            parseObject.put("isFirst",0);
        }else {
            //多次登陆
            parseObject.put("isFirst",1);
        }

        JwtInformation jwtInformation = new JwtInformation();
        jwtInformation.setUid(userInformation.getUid());
        jwtInformation.setUsername(userInformation.getRealName());

        //通过JwtUtil工具 生成token
        String token = JwtUtil.IntranetJwt(jwtInformation);

        //把token存放到cookie中
        Cookie cookie = new Cookie("IntranecToken", token);
        //cookie.setMaxAge(60*30);//三十分钟
        cookie.setMaxAge(60*60*24);//三十分钟
        cookie.setPath("/");
        response.addCookie(cookie);

        //新增内网登陆日志
        ExtranetLoginLog extranetLoginLog = new ExtranetLoginLog();
        extranetLoginLog.setIdentity(Integer.parseInt(userInformation.getIdentity()));     //身份( 3: 科长 4：科员 5：法规科技处)
        extranetLoginLog.setLoginName(name);
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        extranetLoginLog.setLoginTime(nowTime);
        //新增登陆日志表
        userMapper.addLoginLog(extranetLoginLog);

        return resultMap.success().message(parseObject);
    }
}