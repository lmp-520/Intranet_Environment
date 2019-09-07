package com.xdmd.IntranetEnvironment.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.mapper.UserMapper;
import com.xdmd.IntranetEnvironment.user.pojo.User;
import com.xdmd.IntranetEnvironment.user.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class TokenService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 错误返回 0   成功返回 1
     * @param response
     * @param token
     * @return
     */
    //内网登陆时的IntranecToken
    public  User compare(HttpServletResponse response, String token) throws Exception{

        //对token中的内容进行解析
        Claims claims = JwtUtil.checkJWT(token);
        if (claims == null){
            throw new ClaimsNullException("请先登录");
        }
        Object id = claims.get("id");
        int newid = Integer.parseInt(id.toString());
        Object username = claims.get("username");
        String newUsername = String.valueOf(username);
//        String sqlUsername = null;
//        Integer newid2 = new Integer(newid);
//        sqlUsername = userMapper.queryUserNameById(newid2);
//        if (!newUsername.equals(sqlUsername)) {
//           throw  new UserNameNotExistentException("请先登录");
//        }

        //对token进行刷新
        User user = new User();
        user.setId(newid);
        user.setUsername(newUsername);
        JSONObject jsonObject = JSON.parseObject(user.toString());
        jsonObject.remove("name");
        jsonObject.remove("isDelete");
        jsonObject.remove("password");
        jsonObject.remove("department");
        jsonObject.remove("createTime");
        jsonObject.remove("status");
        jsonObject.remove("modify");

        //通过JwtUtil工具 生成token
        String newToken = JwtUtil.geneJsonWebToken(user);

        //把token存放到cookie中
        Cookie cookie = new Cookie("IntranecToken", newToken);
        cookie.setMaxAge(60 * 60 * 24);//三十分钟--24小时
        cookie.setPath("/");
        response.addCookie(cookie);

        return user;
    }
}
