package com.xdmd.IntranetEnvironment.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdmd.IntranetEnvironment.common.MD5Utils;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.user.mapper.UserMapper;
import com.xdmd.IntranetEnvironment.user.pojo.User;
import com.xdmd.IntranetEnvironment.user.service.UserService;
import com.xdmd.IntranetEnvironment.user.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


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
        sqlName = userMapper.queryName(name);
        if (sqlName == null) {
            return resultMap.fail().message("用户名不存在");
        }

        //获取数据库中的密码
        String sqlPassword = userMapper.querySqlPasswordByName(name);
        //对用户输入的密码进行加密
        String newPassword = MD5Utils.md5(password);

        //判断用户输入的密码与数据库中的密码是否相同
        if (!sqlPassword.equals(newPassword)) {
            return resultMap.fail().message("密码错误");
        }

        //登陆成功后，查询用户的信息存放到cookie中
        User user = userMapper.querInformation(name);
        JSONObject jsonObject = JSON.parseObject(user.toString());
        jsonObject.remove("name");
        jsonObject.remove("isDelete");
        jsonObject.remove("password");
        jsonObject.remove("department");
        jsonObject.remove("createTime");
        jsonObject.remove("status");
        jsonObject.remove("modify");

        //通过JwtUtil工具 生成token
        String token = JwtUtil.geneJsonWebToken(user);

        //把token存放到cookie中
        Cookie cookie = new Cookie("IntranecToken", token);
        cookie.setMaxAge(60*30);//三十分钟
        cookie.setPath("/");
        response.addCookie(cookie);

        return resultMap.success().message("登陆成功");
    }
}
