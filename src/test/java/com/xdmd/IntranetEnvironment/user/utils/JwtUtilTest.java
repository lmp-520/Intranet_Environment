package com.xdmd.IntranetEnvironment.user.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdmd.IntranetEnvironment.user.pojo.User;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import static org.junit.Assert.*;

public class JwtUtilTest {

    @Test
    public void geneJsonWebToken() {
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        String s = JwtUtil.geneJsonWebToken(user);
        System.out.println(s);
    }

    @Test
    public void checkJWT() {
        String s = "1yJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvbmVoZWUiLCJpZCI6MSwidXNlcm5hbWUiOiLlvKDkuIkiLCJpYXQiOjE1NjM4NzQ4NzQsImV4cCI6MTU2Mzg3NjY3NH0.eLWdRJU8bJgGDMuSoJneg3RXViFc61m981rGUo9_bA4";
        Claims claims = JwtUtil.checkJWT(s);
      //  JSONObject jsonObject = JSON.parseObject(claims.toString());
     //   Object id  = jsonObject.get("id");
      //  System.out.println(id);
      //  int newId = Integer.parseInt(id.toString());
      //  System.out.println(newId);
        Object id = claims.get("id");
        System.out.println(claims);
        System.out.println(id);
    }
}