package com.xdmd.IntranetEnvironment.guidemanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.JwtInformation;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.impl.ExtranetTokenService;
import com.xdmd.IntranetEnvironment.guidemanagement.mapper.GuideMapper;
import com.xdmd.IntranetEnvironment.guidemanagement.pojo.GuideCollection;
import com.xdmd.IntranetEnvironment.guidemanagement.pojo.GuideCollectionLimitTime;
import com.xdmd.IntranetEnvironment.guidemanagement.pojo.GuideSummary;
import com.xdmd.IntranetEnvironment.guidemanagement.service.GuideService;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: Administrator
 * @createDate: 2019/07/16
 * @description: 指南征集业务实现类
 */
@Service
public class GuideServiceImpl implements GuideService {
    private static final Logger log = LoggerFactory.getLogger(GuideServiceImpl.class);
    ResultMap resultMap = new ResultMap();
    @Autowired
    GuideMapper guideMapper;
    @Autowired
    private ExtranetTokenService extranetTokenService;

    /**
     * 实现分页查询指南申报
     *
     * @param guideName
     * @param domain
     * @param category
     * @param fillUnit
     * @param fillContacts
     * @param contactPhone
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultMap getCollectionByParam(String guideName, Integer domain, Integer category, String fillUnit, String fillContacts, String contactPhone, int pageNum, int pageSize) {
         try{
            PageHelper.startPage(pageNum,pageSize,true);
            List<Map> guideCollectionList = guideMapper.getCollectionByParam(guideName, domain, category, fillUnit, fillContacts, contactPhone);
            PageInfo pageInfo=new PageInfo(guideCollectionList);
            if(guideCollectionList.size()>0){
                resultMap.success().message(pageInfo);
            }else if(guideCollectionList.size()==0){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }

        return resultMap;
    }

    /**
     * 实现获取类别和领域
     *
     * @return
     */
    @Override
    public ResultMap getCategoryAndDomain() {
        try{
            List<Map> getCategoryAndDomains = guideMapper.getCategoryAndDomain();
            if(getCategoryAndDomains.size()>0){
                resultMap.success().message(getCategoryAndDomains);
            }else if(getCategoryAndDomains.size()==0){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;


    }

    /**
     * 实现新增指南申报信息
     * @param guideCollection
     * @return
     */
    @Override
    public ResultMap insertGuideInfo(String token, HttpServletResponse response,GuideCollection guideCollection) {
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
        //Integer userid = jwtInformation.getUid();
        //String username = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();
        try{
            guideCollection.setFillUnit(cname);
            int gmInfo=guideMapper.insertGuideInfo(guideCollection);
            //提交指南申报的同时新增单位关联指南申报表
            insertCidAndUid(cid,guideCollection.getId());
            if(gmInfo>0){
                resultMap.success().message("新增成功");
            }else if(gmInfo<0){
                resultMap.success().message("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 实现更新限制时间业务实现
     * 无论时间周期是否正确，都会导入正确数据到数据库
     * @param guideCollectionLimitTime
     * @return
     */
    @Override
    public ResultMap updateLimitTime(GuideCollectionLimitTime guideCollectionLimitTime) {
        /**
         * 判断时间大小
         */
        try {
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            //开始时间
            Date begin = fmt.parse(guideCollectionLimitTime.getGuideCollectionStartTime());
            //结束时间
            Date end = fmt.parse(guideCollectionLimitTime.getGuideCollectionEndTime());
            //判断开始时间是否在结束时间之后,返回布尔值
            if (begin.after(end) || end.before(begin)) {
                String begintime = guideCollectionLimitTime.getGuideCollectionStartTime();
                String endtime = guideCollectionLimitTime.getGuideCollectionEndTime();
                guideCollectionLimitTime.setGuideCollectionStartTime(endtime);
                guideCollectionLimitTime.setGuideCollectionEndTime(begintime);
            }
            guideMapper.updateLimitTime(guideCollectionLimitTime);

        } catch (Exception e) {
            return resultMap.success().message("更新失败");
        }
        return resultMap.success().message("更新成功");
    }


    /**
     * 新增汇总信息实现【批量插入】
     * @param guideSummary
     * @return
     */
    @Override
    public ResultMap batchInsertSummary(List<GuideSummary> guideSummary) {
        try{
            //获取当前时间戳
            String nowtime = new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());

            int manyInfo=guideMapper.batchInsertSummary(guideSummary);
            if(manyInfo>0){
                resultMap.success().message("操作成功,共批量新增"+manyInfo+"条");
            }else if(manyInfo==0){
                resultMap.success().message("操作失败,没有上传任何信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 汇总信息分页实现
     *
     * @param guideSummaryTitle
     * @param fillUnit
     * @param domain
     * @param category
     * @param projectTime
     * @param researchContentTechnology
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultMap getSummaryByParam(String guideSummaryTitle, String fillUnit, Integer domain, Integer category, String projectTime, String researchContentTechnology, int pageNum, int pageSize) {
        try{
            PageHelper.startPage(pageNum,pageSize,true);
            List<Map> guideSummaryList = guideMapper.getSummaryByParam(guideSummaryTitle, fillUnit, domain, category, projectTime, researchContentTechnology);
           PageInfo pageInfo=new PageInfo(guideSummaryList);
            if(guideSummaryList.size()>0){
                resultMap.success().message(pageInfo);
            }else if(guideSummaryList.size()==0){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 实现根据汇总标题查询汇总信息
     * @param guideSummaryTitle
     * @return
     */
    @Override
    public ResultMap getSummaryByGuideSummaryTitle(String guideSummaryTitle) {
        try{
            List<Map> gsList = guideMapper.getSummaryByGuideSummaryTitle(guideSummaryTitle);
            if(gsList.size()>0){
                resultMap.success().message(gsList);
            }else if(gsList.size()==0){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }





    /**
     * 实现根据单位id查询单位指南申报【外网】
     *
     * @param uid
     * @return
     */
    @Override
    public ResultMap getUnitCollection(String token, HttpServletResponse response, String guideName, Integer domain, Integer category, String fillUnit, String fillContacts, String contactPhone, int uid, int pageNum, int pageSize) {
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
        //Integer userid = jwtInformation.getUid();
        //String username = jwtInformation.getUsername();
        //获取单位id
        Integer unitid = jwtInformation.getCid();
        System.out.println(unitid);
        try{
            PageHelper.startPage(pageNum,pageSize,true);
            List<Map> mapList=guideMapper.getUnitCollection(guideName,domain,category,fillUnit,fillContacts,contactPhone,unitid);
            PageInfo pageInfo=new PageInfo(mapList);
            if(mapList.size()>0){
                resultMap.success().message(pageInfo);
            }else if(mapList.size()==0){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

    /**
     * [新增]单位关联指南征集
     * @param unitId
     * @param collectionId
     * @return
     */
    @Override
    public ResultMap insertCidAndUid(int unitId, int collectionId) {
        try{
            int insertNo=guideMapper.insertCidAndUid(unitId,collectionId);
            if(insertNo>0){
                resultMap.success().message("新增成功");
            }else if(insertNo==0){
                resultMap.fail().message("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 根据勾选的指南id更新相应指南申报选中状态【内网】
     * @param ids
     * @return
     */
    @Override
    public ResultMap updateIsSelectByIds(List<Long> ids) {
        try{
            List<Integer> guideMap=guideMapper.updateIsSelectByIds(ids);
            if(!guideMap.isEmpty()){
                resultMap.success().message(guideMap);
            }else if(guideMap.size()==0){
                resultMap.fail().message("没有查到相关信息");
            }
        }catch (Exception e){
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

}

