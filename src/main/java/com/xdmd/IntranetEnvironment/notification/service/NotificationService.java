package com.xdmd.IntranetEnvironment.notification.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.notification.pojo.Notification;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface NotificationService {
    ResultMap addNotificationService(String token, HttpServletResponse response, Notification notification);

    ResultMap updateNotificationService(String token, HttpServletResponse response, Integer nid, Notification notification);

    ResultMap queryNotification(Integer page, Integer total);

    ResultMap queryNotificationToExtranet();

    ResultMap deleteNotificationService(String token, HttpServletResponse response, Integer[] ids);
}
