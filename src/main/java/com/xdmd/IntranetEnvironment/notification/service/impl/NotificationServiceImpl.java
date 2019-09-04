package com.xdmd.IntranetEnvironment.notification.service.impl;

import com.xdmd.IntranetEnvironment.common.PageBean;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.notification.mapper.NotificationMapper;
import com.xdmd.IntranetEnvironment.notification.pojo.Notification;
import com.xdmd.IntranetEnvironment.notification.service.NotificationService;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import com.xdmd.IntranetEnvironment.user.pojo.User;
import com.xdmd.IntranetEnvironment.user.service.impl.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class NotificationServiceImpl implements NotificationService {
    ResultMap resultMap = new ResultMap();

    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private TokenService tokenService;
    private static Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);
    PageBean pageBean = new PageBean();

    //通知公告的新增
    @Override
    public ResultMap addNotificationService(String token, HttpServletResponse response, Notification notification) {
        User user = new User();
        try {
            user = tokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }
        Integer uid = user.getId();
        String username = user.getUsername();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        notification.setCreateTime(nowTime);    //设置创建时间

        notification.setIsDelete(0);    //设置该条信息启用
        notificationMapper.addNotification(notification, username);

        return resultMap.success().message("新增成功");
    }

    //通知通告的删除
    @Override
    public ResultMap deleteNotificationService(String token, HttpServletResponse response, Integer[] ids) {
        User user = new User();
        try {
            user = tokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }
        Integer uid = user.getId();
        String username = user.getUsername();

        for (Integer id : ids) {
            notificationMapper.deleteNotificationService(id);
        }
        return resultMap.success().message("删除成功");
    }

    //通知公告的修改
    @Override
    public ResultMap updateNotificationService(String token, HttpServletResponse response, Integer nid, Notification notification) {
        User user = new User();
        try {
            user = tokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }
        Integer uid = user.getId();
        String username = user.getUsername();

        notificationMapper.updateNotificationService(notification,nid);

        return resultMap.success().message("更新成功");
    }

    //通知公告的查询
    @Override
    public ResultMap queryNotification(Integer page, Integer total) {
        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }

        //获取通知公告总数
        int alltotal = 0;
        alltotal = notificationMapper.queryAllNotification();
        if (alltotal == 0) {
            return resultMap.fail().message();
        }

        //获取当页的数据
        List<Notification> notificationList = notificationMapper.queryNotificationList(newpage,total);

        pageBean.setAlltotal(alltotal);
        pageBean.setData(notificationList);

        return resultMap.success().message(pageBean);

    }

    //给外网显示
    @Override
    public ResultMap queryNotificationToExtranet() {
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = sdf.format(date);
        List<Notification> notificationList =  notificationMapper.queryNotificationToExtranet(nowTime);
        return resultMap.success().message(notificationList);
    }
}
