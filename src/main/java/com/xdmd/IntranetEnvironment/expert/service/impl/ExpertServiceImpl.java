package com.xdmd.IntranetEnvironment.expert.service.impl;

import com.xdmd.IntranetEnvironment.common.MD5Utils;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.expert.mapper.ExpertMapper;
import com.xdmd.IntranetEnvironment.expert.pojo.ExpertInformation;
import com.xdmd.IntranetEnvironment.expert.service.ExpertService;
import com.xdmd.IntranetEnvironment.subjectAcceptance.controller.AcceptStateController;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.InsertSqlException;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import com.xdmd.IntranetEnvironment.user.pojo.User;
import com.xdmd.IntranetEnvironment.user.service.impl.TokenService;
import jdk.nashorn.internal.parser.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ExpertServiceImpl implements ExpertService {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ExpertMapper expertMapper;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(ExpertServiceImpl.class);


    //给专家分配账号
    @Override
    public ResultMap distributionAccount(String token, HttpServletResponse response, ExpertInformation expertInformation) throws InsertSqlException {
        User user = new User();
        try {
            user = tokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e){
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        }catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }
        Integer uid = user.getId();
        String username = user.getUsername();

        //把专家信息表的内容存入数据库
        String newPassword = MD5Utils.md5(expertInformation.getPassword());//对获取的密码进行加密
        expertInformation.setPassword(newPassword);     //把新的密码存入到对象中
        expertInformation.setIsFirst("true");//设置isFirst  是否第一次登陆，默认true
        expertInformation.setCreateAuthor(username);    //存入创建此条信息的人

        //获取当前时间，存入创建时间字段
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(sdf);
        expertInformation.setCreateTime(nowTime);

        try {
            expertMapper.addExpertInformation(expertInformation);    //新增专家信息表主表
        } catch (Exception e) {
            e.printStackTrace();
            throw new InsertSqlException("ExpertServiceImpl 中 distributionAccount 方法  新增专家信息表主表sql出错 -- "+e.getMessage());
        }

        //获取专家信息表的id

        //新增专家信息表中的文章表
        expertMapper.add


    }
}
