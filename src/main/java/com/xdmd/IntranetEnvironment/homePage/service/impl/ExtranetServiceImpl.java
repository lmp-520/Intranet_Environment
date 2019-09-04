package com.xdmd.IntranetEnvironment.homePage.service.impl;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.JwtInformation;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.impl.ExtranetTokenService;
import com.xdmd.IntranetEnvironment.homePage.mapper.ExtranetMapper;
import com.xdmd.IntranetEnvironment.homePage.service.ExtranetService;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ExtranetServiceImpl implements ExtranetService {
    @Autowired
    private ExtranetTokenService extranetTokenService;
    ResultMap resultMap = new ResultMap();
    @Autowired
    private ExtranetMapper extranetMapper;

    @Override
    public ResultMap querySubjectTotal(String token, HttpServletResponse response) {
        JwtInformation jwtInformation = new JwtInformation();
        try {
            jwtInformation = extranetTokenService.compare(response, token);
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

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();

        //根据公司查询，该企业待处理的信息
        Integer extranetTotal = extranetMapper.queryExtranetTotal(cid);
        return resultMap.success().message(extranetTotal);

    }

    @Override
    public ResultMap queryExpertTotal() {
        Integer expertTotal = extranetMapper.queryExpertTotal();
        return resultMap.success().message(expertTotal);
    }
}
