package com.xdmd.IntranetEnvironment.expert.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//专家信息表中文章
@Data
@ApiModel("专家信息表中文章")
public class ExpertInformationArticle {
    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id")
    private  Integer id;

    //专家信息表id
    @ApiModelProperty("专家信息表id")
    private  Integer expertId;

    //文章题目
    @ApiModelProperty("文章题目")
    private  String articleTitle;

    //作者排序
    @ApiModelProperty("作者排序")
    private  String authorrRanking;

    //文章期刊名
    @ApiModelProperty("文章期刊名")
    private  String titleArticlesPeriodicals;

    //文章发布时间
    @ApiModelProperty("文章发布时间")
    private String articlesPublicationTime;

    public ExpertInformationArticle(Integer expertId, String articleTitle, String authorrRanking, String titleArticlesPeriodicals, String articlesPublicationTime) {
        this.expertId = expertId;
        this.articleTitle = articleTitle;
        this.authorrRanking = authorrRanking;
        this.titleArticlesPeriodicals = titleArticlesPeriodicals;
        this.articlesPublicationTime = articlesPublicationTime;
    }

    public ExpertInformationArticle() {
    }
}
