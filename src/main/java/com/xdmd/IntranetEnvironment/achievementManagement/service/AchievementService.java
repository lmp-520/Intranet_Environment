package com.xdmd.IntranetEnvironment.achievementManagement.service;

import com.xdmd.IntranetEnvironment.achievementManagement.pojo.OutcomeInformation;
import com.xdmd.IntranetEnvironment.achievementManagement.pojo.OutcomeInformationAll;
import com.xdmd.IntranetEnvironment.achievementManagement.pojo.OutcomeInformationPaper;
import com.xdmd.IntranetEnvironment.achievementManagement.pojo.OutcomeInformationPatent;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AchievementService {
    ResultMap queryAchivement(String topicName, String topicNumber, Integer page, Integer total);

    ResultMap queryAddAchivement(String topicName, String topicNumber, Integer page, Integer total);

    ResultMap AddAchievementSave(String token, HttpServletResponse response, String cid, MultipartFile achievementFileUrl, OutcomeInformationAll outcomeInformationAll);

    ResultMap AddAchievement(String token, HttpServletResponse response, String cid, MultipartFile achievementFileUrl, OutcomeInformationAll outcomeInformationAll);

}
