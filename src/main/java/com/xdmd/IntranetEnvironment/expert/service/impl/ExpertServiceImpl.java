package com.xdmd.IntranetEnvironment.expert.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdmd.IntranetEnvironment.common.*;
import com.xdmd.IntranetEnvironment.expert.mapper.ExpertMapper;
import com.xdmd.IntranetEnvironment.expert.pojo.*;
import com.xdmd.IntranetEnvironment.expert.service.ExpertService;
import com.xdmd.IntranetEnvironment.expert.updateSqlException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.InsertSqlException;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import com.xdmd.IntranetEnvironment.user.pojo.User;
import com.xdmd.IntranetEnvironment.user.service.impl.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ExpertServiceImpl implements ExpertService {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ExpertMapper expertMapper;
    ResultMap resultMap = new ResultMap();
    PageBean pageBean = new PageBean();
    private static Logger log = LoggerFactory.getLogger(ExpertServiceImpl.class);


    //给专家分配账号
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultMap distributionAccount(String token, HttpServletResponse response, ExpertInformation expertInformation, MultipartFile expertFile) throws Exception {
//        User user = new User();
//        try {
//            user = tokenService.compare(response, token);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (UserNameNotExistentException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (ClaimsNullException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("MenuServiceImpl 中 TokenService 出现问题");
//            return resultMap.message("系统异常");
//        }
//        Integer uid = user.getId();
//        String username = user.getUsername();

        String username = "测试人名";

        //判断输入的登陆名是否存在
        Integer eid = null;
        eid = expertMapper.queryEidByUsername(expertInformation.getUsername());
        if (eid != null) {
            //eid有值，意味着登陆名已存在
            return resultMap.fail().message("登陆名已存在");
        }

        //对文件的后缀名进行判断
        List<String> suffixList = new ArrayList<>(Arrays.asList(".doc",".docx",".pdf"));
        //获取文件名
        String filename = expertFile.getOriginalFilename();
        Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(filename, suffixList);
        if(aBoolean == false){
            return resultMap.fail().message("请上传正确的文件格式");
        }

        //对文件进行上传
        String expertInformationFileUrl = null;
//       try {
            expertInformationFileUrl = FileUploadUtil.UploadExpertInformationFile(expertFile, "专家信息库");
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw Exception;
//        }

        //把上传文件的信息存储到upload_file
        UploadFile uploadFileExpert = IntegrationFile.IntegrationFile(expertFile, expertInformationFileUrl, "专家信息库", username);
        expertMapper.uploadExpertFile(uploadFileExpert);    //把文件上传的信息存储到upload_file表中

        //把专家信息表的内容存入数据库
        String newPassword = MD5Utils.md5(expertInformation.getPassword());//对获取的密码进行加密
        expertInformation.setPassword(newPassword);     //把新的密码存入到对象中
        expertInformation.setIsFirst("true");//设置isFirst  是否第一次登陆，默认true
        expertInformation.setCreateAuthor(username);    //存入创建此条信息的人
        expertInformation.setExpertInformationUrlId(uploadFileExpert.getId());  //把保存的的专家信息文件的id，存储起来
        expertInformation.setIsSubmit("1"); //设置已提交状态

        //获取当前时间，存入创建时间字段
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        expertInformation.setCreateTime(nowTime);

        expertInformation.setIsState("1");  //设置审核已经通过
        expertInformation.setIsDelete("1"); //设置该账号启用

        try {
            expertMapper.addExpertInformation(expertInformation);    //新增专家信息表主表
        } catch (Exception e) {
            e.printStackTrace();
            throw new InsertSqlException("ExpertServiceImpl 中 distributionAccount 方法  新增专家信息表主表sql出错 -- " + e.getMessage());
        }

        //获取专家信息表的id

        //新增专家信息表中的文章表
        List<ExpertInformationArticle> expertInformationArticleList = expertInformation.getExpertInformationArticleList();  //获取专家信息表中文章列表集合
        for (ExpertInformationArticle expertInformationArticle : expertInformationArticleList) {
            expertInformationArticle.setExpertId(expertInformation.getId());    //把文章列表中对应的专家信息表的id存入
            try {
                expertMapper.addExpertInformationArticle(expertInformationArticle); //新增专家信息表对应的文章列表
            } catch (Exception e) {
                e.printStackTrace();
                throw new InsertSqlException("ExpertServiceImpl 中 distributionAccount 方法 新增文章表sql出错 -- " + e.getMessage());
            }
        }

        //新增专家信息表中著作表
        List<ExpertInformationBook> expertInformationBookList = expertInformation.getExpertInformationBookList();   //获取专家信息表中的著作列表集合
        for (ExpertInformationBook expertInformationBook : expertInformationBookList) {
            expertInformationBook.setExpertId(expertInformation.getId());   //把文章列表中的对应的专家信息表的id存入
            try {
                expertMapper.addExpertInformationBook(expertInformationBook);   //新增专家信息表对应的著作列表
            } catch (Exception e) {
                e.printStackTrace();
                throw new InsertSqlException("ExpertServiceImpl 中 distributionAccount 方法 新增著作表sql出错 -- " + e.getMessage());
            }
        }

        //新增专家信息表中的专利表
        List<ExpertInformationPatent> expertInformationPatentList = expertInformation.getExpertInformationPatentList(); //获取专家信息表中的专利表集合
        for (ExpertInformationPatent expertInformationPatent : expertInformationPatentList) {
            expertInformationPatent.setExpertId(expertInformation.getId()); //把专利表中的对应的专家信息表的id存入
            try {
                expertMapper.addExpertInformationPatent(expertInformationPatent);   //新增专家信息表对应的专利列表
            } catch (Exception e) {
                e.printStackTrace();
                throw new InsertSqlException("ExpertServiceImpl 中 distributionAccount 方法 新增专利表sql出错 -- " + e.getMessage());
            }
        }

        //新增专家信息表中的获奖表
        List<ExpertInformationPrizeWinning> expertInformationPrizeWinningList = expertInformation.getExpertInformationPrizeWinningList();   //获取专家信息表中的获奖表集合
        for (ExpertInformationPrizeWinning expertInformationPrizeWinning : expertInformationPrizeWinningList) {
            expertInformationPrizeWinning.setExpertId(expertInformation.getId());   //把获奖表中的对应的专家信息表的id存入
            try {
                expertMapper.addExpertInformationPrizeWinning(expertInformationPrizeWinning);   //新增专家信息表对应的获奖列表
            } catch (Exception e) {
                e.printStackTrace();
                throw new InsertSqlException("ExpertServiceImpl 中 distributionAccount 方法 新增获奖表sql出错 -- " + e.getMessage());
            }
        }

        //新增专家信息表中的研究方向
        List<ExpertInformationResearchDirection> expertInformationResearchDirectionList = expertInformation.getExpertInformationResearchDirectionList();     //获取专家信息表中的研究方向表集合
        for (ExpertInformationResearchDirection expertInformationResearchDirection : expertInformationResearchDirectionList) {
            expertInformationResearchDirection.setExpertId(expertInformation.getId());  //把研究方向中的对应的专家信息表的id存入
            try {
                expertMapper.addExpertInformationResearchDirection(expertInformationResearchDirection); //新增专家信息表对应的研究方向列表
            } catch (Exception e) {
                e.printStackTrace();
                throw new InsertSqlException("ExpertServiceImpl 中 distributionAccount 方法 新增研究方向表sql出错 -- " + e.getMessage());
            }
        }

        return resultMap.success().message("新增成功");
    }

    //专家信息的保存
    @Override
    public ResultMap expertSave(String token, HttpServletResponse response, ExpertInformation expertInformation, MultipartFile expertFile) throws Exception{
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


        //对文件的后缀名进行判断
        List<String> suffixList = new ArrayList<>(Arrays.asList(".doc",".docx",".pdf"));
        //获取文件名
        String filename = expertFile.getOriginalFilename();
        Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(filename, suffixList);
        if(aBoolean == false){
            return resultMap.fail().message("请上传正确的文件格式");
        }

        //对文件进行上传
        String expertInformationFileUrl = null;
        try {
            expertInformationFileUrl = FileUploadUtil.UploadExpertInformationFile(expertFile, "专家信息库");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //把上传文件的信息存储到upload_file
        UploadFile uploadFileExpert = IntegrationFile.IntegrationFile(expertFile, expertInformationFileUrl, "专家信息库", username);
        expertMapper.uploadExpertFile(uploadFileExpert);    //把文件上传的信息存储到upload_file表中


        //把专家信息表的内容存入数据库
        String newPassword = MD5Utils.md5(expertInformation.getPassword());//对获取的密码进行加密
        expertInformation.setPassword(newPassword);     //把新的密码存入到对象中
      //  expertInformation.setIsFirst("true");//设置isFirst  是否第一次登陆，默认true
        expertInformation.setCreateAuthor(username);    //存入创建此条信息的人
        expertInformation.setExpertInformationUrlId(uploadFileExpert.getId());  //把上传专家信息的id存储起来

        //获取当前时间，存入创建时间字段
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        expertInformation.setCreateTime(nowTime);

        expertInformation.setIsSubmit("0"); //设置提交信息是保存
        expertInformation.setUid(uid);  //保存此条信息的提交人

        try {
            expertMapper.addExpertInformation(expertInformation);    //新增专家信息表主表
        } catch (Exception e) {
            e.printStackTrace();
            throw new InsertSqlException("ExpertServiceImpl 中 distributionAccount 方法  新增专家信息表主表sql出错 -- " + e.getMessage());
        }

        //获取专家信息表的id

        //新增专家信息表中的文章表
        List<ExpertInformationArticle> expertInformationArticleList = expertInformation.getExpertInformationArticleList();  //获取专家信息表中文章列表集合
        for (ExpertInformationArticle expertInformationArticle : expertInformationArticleList) {
            expertInformationArticle.setExpertId(expertInformation.getId());    //把文章列表中对应的专家信息表的id存入
            try {
                expertMapper.addExpertInformationArticle(expertInformationArticle); //新增专家信息表对应的文章列表
            } catch (Exception e) {
                e.printStackTrace();
                throw new InsertSqlException("ExpertServiceImpl 中 distributionAccount 方法 新增文章表sql出错 -- " + e.getMessage());
            }
        }

        //新增专家信息表中著作表
        List<ExpertInformationBook> expertInformationBookList = expertInformation.getExpertInformationBookList();   //获取专家信息表中的著作列表集合
        for (ExpertInformationBook expertInformationBook : expertInformationBookList) {
            expertInformationBook.setExpertId(expertInformation.getId());   //把文章列表中的对应的专家信息表的id存入
            try {
                expertMapper.addExpertInformationBook(expertInformationBook);   //新增专家信息表对应的著作列表
            } catch (Exception e) {
                e.printStackTrace();
                throw new InsertSqlException("ExpertServiceImpl 中 distributionAccount 方法 新增著作表sql出错 -- " + e.getMessage());
            }
        }

        //新增专家信息表中的专利表
        List<ExpertInformationPatent> expertInformationPatentList = expertInformation.getExpertInformationPatentList(); //获取专家信息表中的专利表集合
        for (ExpertInformationPatent expertInformationPatent : expertInformationPatentList) {
            expertInformationPatent.setExpertId(expertInformation.getId()); //把专利表中的对应的专家信息表的id存入
            try {
                expertMapper.addExpertInformationPatent(expertInformationPatent);   //新增专家信息表对应的专利列表
            } catch (Exception e) {
                e.printStackTrace();
                throw new InsertSqlException("ExpertServiceImpl 中 distributionAccount 方法 新增专利表sql出错 -- " + e.getMessage());
            }
        }

        //新增专家信息表中的获奖表
        List<ExpertInformationPrizeWinning> expertInformationPrizeWinningList = expertInformation.getExpertInformationPrizeWinningList();   //获取专家信息表中的获奖表集合
        for (ExpertInformationPrizeWinning expertInformationPrizeWinning : expertInformationPrizeWinningList) {
            expertInformationPrizeWinning.setExpertId(expertInformation.getId());   //把获奖表中的对应的专家信息表的id存入
            try {
                expertMapper.addExpertInformationPrizeWinning(expertInformationPrizeWinning);   //新增专家信息表对应的获奖列表
            } catch (Exception e) {
                e.printStackTrace();
                throw new InsertSqlException("ExpertServiceImpl 中 distributionAccount 方法 新增获奖表sql出错 -- " + e.getMessage());
            }
        }

        //新增专家信息表中的研究方向
        List<ExpertInformationResearchDirection> expertInformationResearchDirectionList = expertInformation.getExpertInformationResearchDirectionList();     //获取专家信息表中的研究方向表集合
        for (ExpertInformationResearchDirection expertInformationResearchDirection : expertInformationResearchDirectionList) {
            expertInformationResearchDirection.setExpertId(expertInformation.getId());  //把研究方向中的对应的专家信息表的id存入
            try {
                expertMapper.addExpertInformationResearchDirection(expertInformationResearchDirection); //新增专家信息表对应的研究方向列表
            } catch (Exception e) {
                e.printStackTrace();
                throw new InsertSqlException("ExpertServiceImpl 中 distributionAccount 方法 新增研究方向表sql出错 -- " + e.getMessage());
            }
        }

        return resultMap.success().message("保存成功");
    }

    //专家的查询
    @Override
    public ResultMap query(String name, String natureWork, String professionalField, String isProvince, Integer page, Integer total) {
        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }

        //获取专家总数
        int alltotal = 0;
        alltotal = expertMapper.queryAllExpert(name, natureWork, professionalField, isProvince);
        if (alltotal == 0) {
            return resultMap.fail().message();
        }

        //判断用户输入的页数是否超过总页数
        int allPage = 0;
        if (alltotal % page == 0) {
            allPage = alltotal / page;
        } else {
            allPage = (alltotal / page) + 1;
        }
        if (page > allPage) {
            return resultMap.fail().message("页数超过总页数");
        }

        //获取专家信息的集合
        List<ExpertInformation> expertInformationList = expertMapper.queryExpertInformation(newpage, total, name, natureWork, professionalField, isProvince);

        for (ExpertInformation expertInformation : expertInformationList) {
            JSONObject jsonObject = JSON.parseObject(expertInformation.toString());
            //通过专家信息文件的id找到专家信息文件
            String expertInformationFileUrl = expertMapper.queryExpertInformationFileByFileId(expertInformation.getExpertInformationUrlId());
            expertInformation.setExpertInformationUrl(expertInformationFileUrl);

            //通过专家信息文件的id找到专家信息文件的名称
            String fileName = expertMapper.queryExpertInformationFileNameByFileId(expertInformation.getExpertInformationUrlId());
            expertInformation.setExpertInformationFileName(fileName);

            //获取专家信息表中文章
            List<ExpertInformationArticle> expertInformationArticleList = expertMapper.queryExpertInformationArticleByExpertId(expertInformation.getId());
            expertInformation.setExpertInformationArticleList(expertInformationArticleList);

            //获取专家表中著作
            List<ExpertInformationBook> expertInformationBookList = expertMapper.queryExpertInformationBookByExpertId(expertInformation.getId());
            expertInformation.setExpertInformationBookList(expertInformationBookList);

            //获取专家表中专利
            List<ExpertInformationPatent> expertInformationPatentList = expertMapper.queryExpertInformationPatentByExpertId(expertInformation.getId());
            expertInformation.setExpertInformationPatentList(expertInformationPatentList);

            //获取专家表中获奖
            List<ExpertInformationPrizeWinning> expertInformationPrizeWinningList = expertMapper.queryExpertInformationPrizeWinningByExpertId(expertInformation.getId());
            expertInformation.setExpertInformationPrizeWinningList(expertInformationPrizeWinningList);

            //获取专家表中研究方向
            List<ExpertInformationResearchDirection> expertInformationResearchDirectionList = expertMapper.queryExpertInformationResearchDirectionByExpertId(expertInformation.getId());
            expertInformation.setExpertInformationResearchDirectionList(expertInformationResearchDirectionList);
        }

        pageBean.setAlltotal(alltotal);
        pageBean.setData(expertInformationList);
        return resultMap.success().message(pageBean);
    }


    //专家账号的审核
    @Override
    public ResultMap expertState(String token, HttpServletResponse response, Boolean type, String reason, Integer id) throws updateSqlException {
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

        //两种情况，审核通过与审核不通过
        if(type){
            //审核通过时,则修改此账号的审核状态
            try {
                expertMapper.ExpertStateSuccess(id);
            } catch (Exception e) {
                e.printStackTrace();
                throw new updateSqlException("ExpertServiceImpl中expertState方法 审核通过时，更新账号状态错误"+e.getMessage());
            }
        }else {
            //此时审核未通过，修改账号的状态与未通过的原因
            try {
                expertMapper.ExpertStateFail(id,reason);
            } catch (Exception e) {
                e.printStackTrace();
                throw new updateSqlException("ExpertServiceImpl中expertState方法 审核未通过时，更新账号状态错误"+e.getMessage());
            }
        }
        return resultMap.success().message("审核通过");
    }
}
