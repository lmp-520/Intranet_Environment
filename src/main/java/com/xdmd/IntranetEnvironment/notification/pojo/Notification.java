package com.xdmd.IntranetEnvironment.notification.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Notification {
    private Integer id;
    @NotNull(message = "标题不能为空")
    private String title;
    @NotNull(message = "内容不能为空")
    private String content;
    private String createTime;
    private String createAuthor;
    @NotNull(message = "开始时间不能为空")
    private String startTime;
    @NotNull(message = "结束时间不能为空")
    private String endTime;

    private Integer isDelete;   //0:启用  1：逻辑删除

    public Notification(Integer id, @NotNull(message = "标题不能为空") String title, @NotNull(message = "内容不能为空") String content, String createTime, String createAuthor, @NotNull(message = "开始时间不能为空") String startTime, @NotNull(message = "结束时间不能为空") String endTime, Integer isDelete) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.createAuthor = createAuthor;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDelete = isDelete;
    }

    public Notification() {
    }
}
