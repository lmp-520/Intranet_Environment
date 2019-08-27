package com.xdmd.IntranetEnvironment.notification.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.notification.pojo.Notification;
import com.xdmd.IntranetEnvironment.notification.service.NotificationService;
import com.xdmd.IntranetEnvironment.subjectAcceptance.controller.AcceptStateController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

//通知公告
@Controller
@RequestMapping("notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(NotificationController.class);


    //通知公告的新增
    @PostMapping("add")
    @ResponseBody
    public ResultMap addNotificationService(@CookieValue(value = "IntranecToken") String token, HttpServletResponse response,
                                            @Valid Notification notification, BindingResult result){

        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }

        //用于判断用户传输的参数是否有误
        if (result.hasErrors()) {
            List<ObjectError> ls = result.getAllErrors();
            String errorMessage = ls.get(0).getDefaultMessage();
            return resultMap.fail().message(errorMessage);
        }


        try {
            resultMap = notificationService.addNotificationService(token,response,notification);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("NotificationController 中 addNotificationService方法错误 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //通知通告的删除
    @PostMapping("delete")
    @ResponseBody
    public ResultMap deleteNotificationService(@CookieValue(value = "IntranecToken") String token, HttpServletResponse response,
                                               @RequestParam Integer [] ids){   //通知公告的id
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }
        try {
            resultMap = notificationService.deleteNotificationService(token,response,ids);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("NotificationController 中 deleteNotificationService 方法出错 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }

        return resultMap;
    }

    //通知公告的修改
    @ResponseBody
    @PostMapping("update")
    public ResultMap updateNotificationService(@CookieValue(value = "IntranecToken") String token, HttpServletResponse response,
                                               @RequestParam("nid") Integer nid,
                                               @Valid Notification notification, BindingResult result){
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }
        //用于判断用户传输的参数是否有误
        if (result.hasErrors()) {
            List<ObjectError> ls = result.getAllErrors();
            String errorMessage = ls.get(0).getDefaultMessage();
            return resultMap.fail().message(errorMessage);
        }

        try {
            resultMap = notificationService.updateNotificationService(token,response,nid,notification);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("NotificationController 中 updateNotificationService 方法错误 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }

        return resultMap;
    }


    //通知公告的查询
    @PostMapping("query")
    @ResponseBody
    public ResultMap queryNotification(@RequestParam("page") Integer page,@RequestParam("total") Integer total){

        if (page <= 0 || total <= 0) {
            return resultMap.fail().message("请返回正确的页数或每页显示条数");
        }

        try {
            resultMap  =  notificationService.queryNotification(page,total);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("NotificationController 中 queryNotification方法出错 -- "+e.getMessage()+e.toString());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    //给外网显示
    @ResponseBody
    @PostMapping("ExtranetQuery")
    public ResultMap queryNotificationToExtranet(){

        try {
            resultMap = notificationService.queryNotificationToExtranet();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("NotificationController 中 ExtranetQuery 方法有误 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }

        return resultMap;
    }

}
