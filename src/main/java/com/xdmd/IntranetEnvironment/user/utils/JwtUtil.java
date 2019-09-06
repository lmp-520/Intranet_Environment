package com.xdmd.IntranetEnvironment.user.utils;

import com.xdmd.IntranetEnvironment.company.Pojo.JwtInformation;
import com.xdmd.IntranetEnvironment.user.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    public static final String SUBJECT = "onehee";

//    public static final long EXPIRE = 1000*60*30;  //过期时间，毫秒，三十分钟
    public static final long EXPIRE = 1000*60*60*24;  //过期时间，毫秒，二十四小时

    //秘钥
    public static final  String APPSECRET = "onehee666";

    /**
     * 生成jwt
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user){

        if(user == null || user.getId() == null || user.getUsername() == null){
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id",user.getId())
                .claim("username",user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .signWith(SignatureAlgorithm.HS256,APPSECRET).compact();

        return token;
    }


    /**
     * 校验token
     * @param token
     * @return
     */
    public static Claims checkJWT(String token ){

        try{
            final Claims claims =  Jwts.parser().setSigningKey(APPSECRET).
                    parseClaimsJws(token).getBody();
            return  claims;

        }catch (Exception e){ }
        return null;

    }

    //内网生成JWT
    public static String IntranetJwt(JwtInformation jwtInformation){

//        if(user == null || user.getId() == null || user.getUsername() == null){
//            return null;
//        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id",jwtInformation.getUid())
                .claim("username",jwtInformation.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .signWith(SignatureAlgorithm.HS256,APPSECRET).compact();

        return token;
    }
}
