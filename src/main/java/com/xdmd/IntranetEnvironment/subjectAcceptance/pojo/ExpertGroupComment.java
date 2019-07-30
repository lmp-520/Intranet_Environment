package com.xdmd.IntranetEnvironment.subjectAcceptance.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

//专家组意见表
@Data
public class ExpertGroupComment {
    //主键id  (专家组意见表)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer egcId;

    //验收申请表id
    private  Integer caId;

    //课题名称
    private  String topicName;

    //课题编号
    private  String topicNumber;

    //课题负责人
    private  String projectLeader;

    //课题承担单位
    private  String subjectUndertakingUnit;

    //验收专家人数
    private  Integer acceptanceExpertNumber;

    //专家1评分
    private BigDecimal expertOneGrade;

    //专家2评分
    private  BigDecimal expertTwoGrade;

    //专家3评分
    private  BigDecimal expertThreeGrade;

    //专家4评分
    private  BigDecimal expertFourGrade;

    //专家5评分
    private  BigDecimal expertFiveGrade;

    //专家6评分
    private  BigDecimal expertSixGrade;

    //专家7评分
    private  BigDecimal expertSevenGrade;

    //综合得分
    private  BigDecimal synthesizeGrade;

    //对课题情况的总体评价
    private  String topicOverallEvaluation;

    //建议
    private  String suggest;

    //验收结论id
    private  Integer acceptanceConclusionId;

    //专家组意见表中的专家信息
    private List<ExpertGroupCommentsName> expertGroupCommentsNameList;

    //专家组组长姓名
    private  String expertLeader;

    //日期
    private String writeDate;

    //创建时间
    private String createTime;

    //创建人
    private  String createAuthor;

    public ExpertGroupComment(Integer caId, String topicName, String topicNumber, String projectLeader, String subjectUndertakingUnit, Integer acceptanceExpertNumber, BigDecimal expertOneGrade, BigDecimal expertTwoGrade, BigDecimal expertThreeGrade, BigDecimal expertFourGrade, BigDecimal expertFiveGrade, BigDecimal expertSixGrade, BigDecimal expertSevenGrade, BigDecimal synthesizeGrade, String topicOverallEvaluation, String suggest, Integer acceptanceConclusionId, List<ExpertGroupCommentsName> expertGroupCommentsNameList, String expertLeader, String writeDate, String createTime, String createAuthor) {
        this.caId = caId;
        this.topicName = topicName;
        this.topicNumber = topicNumber;
        this.projectLeader = projectLeader;
        this.subjectUndertakingUnit = subjectUndertakingUnit;
        this.acceptanceExpertNumber = acceptanceExpertNumber;
        this.expertOneGrade = expertOneGrade;
        this.expertTwoGrade = expertTwoGrade;
        this.expertThreeGrade = expertThreeGrade;
        this.expertFourGrade = expertFourGrade;
        this.expertFiveGrade = expertFiveGrade;
        this.expertSixGrade = expertSixGrade;
        this.expertSevenGrade = expertSevenGrade;
        this.synthesizeGrade = synthesizeGrade;
        this.topicOverallEvaluation = topicOverallEvaluation;
        this.suggest = suggest;
        this.acceptanceConclusionId = acceptanceConclusionId;
        this.expertGroupCommentsNameList = expertGroupCommentsNameList;
        this.expertLeader = expertLeader;
        this.writeDate = writeDate;
        this.createTime = createTime;
        this.createAuthor = createAuthor;
    }

    public ExpertGroupComment() {
    }
}
