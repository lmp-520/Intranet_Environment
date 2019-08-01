package com.xdmd.IntranetEnvironment.achievementManagement.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 成果信息论文表
 * @Author:ZhangYuDeLong
 * @Date:2019.7.19
 */
@Data
public class OutcomeInformationPaper {
    //主键id
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    //主键id
    private  Integer id;

    //成果信息id
    private  Integer achievementsId;

    //论文序号
    @NotNull(message = "论文序号不能为空")
    private  String serialNumber;

    //名称
    @NotNull(message = "论文名称不能为空")
    private  String name;

    //刊物
    @NotNull(message = "论文刊物不能为空")
    private  String publication;

    //发表时间
    @NotNull(message = "论文时间不能为空")
    private  String publicationTime;

    //作者
    @NotNull(message = "论文作者不能为空")
    private  String author;

    //论文级别（SCI/EI/核心等）
    @NotNull(message = "论文级别不能为空")
    private  String paperLevel;

    public OutcomeInformationPaper(Integer achievementsId, @NotNull(message = "论文序号不能为空") String serialNumber, @NotNull(message = "论文名称不能为空") String name, @NotNull(message = "论文刊物不能为空") String publication, @NotNull(message = "论文时间不能为空") String publicationTime, @NotNull(message = "论文作者不能为空") String author, @NotNull(message = "论文级别不能为空") String paperLevel) {
        this.achievementsId = achievementsId;
        this.serialNumber = serialNumber;
        this.name = name;
        this.publication = publication;
        this.publicationTime = publicationTime;
        this.author = author;
        this.paperLevel = paperLevel;
    }

    public OutcomeInformationPaper() {
    }
}
