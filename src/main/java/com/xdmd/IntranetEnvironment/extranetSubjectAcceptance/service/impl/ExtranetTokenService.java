package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.JwtInformation;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.utils.JwtUtil;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class ExtranetTokenService {

//    @Autowired
//    private UserMapper userMapper;

    /**
     * 错误返回 0   成功返回 1
     * @param response
     * @param token
     * @return
     */
    //外网登陆时的token
    public JwtInformation compare(HttpServletResponse response, String token) throws Exception{

        //对token中的内容进行解析
        Claims claims = JwtUtil.checkJWT(token);
        if (claims == null){
            throw new ClaimsNullException("请先登录");
        }
        Object id = claims.get("uid");
        int uid = Integer.parseInt(id.toString());
        Object username = claims.get("uname");
        String uname = String.valueOf(username);
        Object Ocid = claims.get("cid");
        int cid = Integer.parseInt(Ocid.toString());
        Object Ocname = claims.get("cname");
        String cname = String.valueOf(Ocname);


        //对token进行刷新
        JwtInformation jwtInformation = new JwtInformation();
        jwtInformation.setCid(cid);

        jwtInformation.setCompanyName(cname);
        jwtInformation.setUid(uid);
        jwtInformation.setUsername(uname);
        JSONObject jsonObject = JSON.parseObject(jwtInformation.toString());
        jsonObject.remove("name");
        jsonObject.remove("isDelete");
        jsonObject.remove("password");
        jsonObject.remove("department");
        jsonObject.remove("createTime");
        jsonObject.remove("status");
        jsonObject.remove("modify");

        //通过JwtUtil工具 生成token
        String newToken = JwtUtil.geneJsonWebToken(jwtInformation);

        //把token存放到cookie中
        Cookie cookie = new Cookie("token", newToken);
        cookie.setMaxAge(60 * 30);//三十分钟
        cookie.setPath("/");
        response.addCookie(cookie);

        return jwtInformation;
    }
}
