package com.xdmd.IntranetEnvironment.notification.mapper;

import com.xdmd.IntranetEnvironment.notification.pojo.Notification;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NotificationMapper {
    //通知公告的新增
    void addNotification(@Param("notification") Notification notification, @Param("username") String username);

    void deleteNotificationService(@Param("nid") Integer nid);

    void updateNotificationService(@Param("notification") Notification notification, @Param("nid") Integer nid);

    //获取通知公告总数
    @Select("select count(*) from notification where is_delete = 0")
    int queryAllNotification();

    //查询当页的通知公告的信息
    @Select("select * from notification where is_delete = 0  order by create_time desc limit #{newpage},#{total} ")
    List<Notification> queryNotificationList(@Param("newpage") int newpage, @Param("total") Integer total);

    @Select("select * from notification where is_delete = 0 and #{nowTime} between start_time and end_time order by start_time desc")
    List<Notification> queryNotificationToExtranet(@Param("nowTime") String nowTime);

}
