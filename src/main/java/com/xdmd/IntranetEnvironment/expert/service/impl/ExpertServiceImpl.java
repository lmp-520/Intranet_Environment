package com.xdmd.IntranetEnvironment.expert.service.impl;

import com.xdmd.IntranetEnvironment.common.*;
import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import com.xdmd.IntranetEnvironment.expert.mapper.ExpertMapper;
import com.xdmd.IntranetEnvironment.expert.pojo.*;
import com.xdmd.IntranetEnvironment.expert.service.ExpertService;
import com.xdmd.IntranetEnvironment.expert.updateSqlException;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
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
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
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
    public ResultMap distributionExpertAccount(String token, HttpServletResponse response, UserInformation userInformation, MultipartFile expertFile) {
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


//        //判断上传的文件后缀名是否正确
//        //专家信息文件后缀名的判断
//        List<String> expertSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".zip", ".rar", ".7z"));
//        //获取文件名
//        String expertFileName = expertFile.getOriginalFilename();
//        Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(expertFileName, expertSuffixList);
//        if (aBoolean == false) {
//            return resultMap.fail().message("请上传正确的专家信息文件格式");
//        }

        //判断登陆名是否存在
        Integer uid1 = null;
        uid1 = expertMapper.queryUidByLoginNameExist(userInformation.getLoginName());

        if(uid1 != null){
            //此时意味着登陆名已经存在
            return resultMap.fail().message("登陆名已存在");
        }

        //对专家信息表进行上传
        try {
            String businessFileUrl = FileUploadUtil.UploadExpertInformationFile(expertFile, "专家信息库");
            //把专家信息文件上传到upload_file中
            UploadFile uploadBusinessFile = IntegrationFile.IntegrationFile(expertFile, businessFileUrl, "专家信息库", userInformation.getRealName());
            expertMapper.uploadFile(uploadBusinessFile);   //对专家信息进行上传
            ExpertInformation expertInformation = userInformation.getExpertInformation();
            expertInformation.setExpertInformationUrlId(uploadBusinessFile.getId());//把上传后的文件id，插入到专家信息表中
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExtranetExpertServiceImpl 中  register 方法 专家信息文件上传失败");
            return resultMap.fail().message("系统异常");
        }

        //对输入的密码进行加密
        String password = userInformation.getPassword();
        //对密码进行加密
        String newPassword  = MD5Utils.md5(password);
        userInformation.setPassword(newPassword);

        ExpertInformation expertInformation = userInformation.getExpertInformation();

        //设置管专家身份
        userInformation.setIdentity("2");
        //设置为多次登陆
        userInformation.setIsFirst("0");
        //设置通过审核
        userInformation.setIsState("1");    //1：审核通过 2：等待审核  3：审核未通过
        //设置该账号启用
        userInformation.setIsDelete("0");

        //把专家的基本信息存入到基本信息表中，具体信息存入专家表中
        expertMapper.addUserInformation(userInformation);
        expertInformation.setAid(userInformation.getUid());

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        expertInformation.setCreateTime(nowTime);
        expertInformation.setCreateAuthor(username);    //存入创建此条信息的人

        //把专家具体信息存储到数据库中
        expertMapper.addExpertInformation(expertInformation);

        //新增专家信息表中的文章表
        List<ExpertInformationArticle> expertInformationArticleList = expertInformation.getExpertInformationArticleList();  //获取专家信息表中文章列表集合
        for (ExpertInformationArticle expertInformationArticle : expertInformationArticleList) {
            expertInformationArticle.setExpertId(expertInformation.getId());    //把文章列表中对应的专家信息表的id存入
            expertMapper.addExpertInformationArticle(expertInformationArticle); //新增专家信息表对应的文章列表

        }

        //新增专家信息表中著作表
        List<ExpertInformationBook> expertInformationBookList = expertInformation.getExpertInformationBookList();   //获取专家信息表中的著作列表集合
        for (ExpertInformationBook expertInformationBook : expertInformationBookList) {
            expertInformationBook.setExpertId(expertInformation.getId());   //把文章列表中的对应的专家信息表的id存入
            expertMapper.addExpertInformationBook(expertInformationBook);   //新增专家信息表对应的著作列表
        }

        //新增专家信息表中的专利表
        List<ExpertInformationPatent> expertInformationPatentList = expertInformation.getExpertInformationPatentList(); //获取专家信息表中的专利表集合
        for (ExpertInformationPatent expertInformationPatent : expertInformationPatentList) {
            expertInformationPatent.setExpertId(expertInformation.getId());   //把文章列表中的对应的专家信息表的id存入
            expertMapper.addExpertInformationPatent(expertInformationPatent);   //新增专家信息表对应的专利列表
        }

        //新增专家信息表中的获奖表
        List<ExpertInformationPrizeWinning> expertInformationPrizeWinningList = expertInformation.getExpertInformationPrizeWinningList();   //获取专家信息表中的获奖表集合
        for (ExpertInformationPrizeWinning expertInformationPrizeWinning : expertInformationPrizeWinningList) {
            expertInformationPrizeWinning.setExpertId(expertInformation.getId());   //把获奖表中的对应的专家信息表的id存入
            expertMapper.addExpertInformationPrizeWinning(expertInformationPrizeWinning);   //新增专家信息表对应的获奖列表
        }

        //新增专家信息表中的研究方向
        List<ExpertInformationResearchDirection> expertInformationResearchDirectionList = expertInformation.getExpertInformationResearchDirectionList();     //获取专家信息表中的研究方向表集合
        for (ExpertInformationResearchDirection expertInformationResearchDirection : expertInformationResearchDirectionList) {
            expertInformationResearchDirection.setExpertId(expertInformation.getId());  //把研究方向中的对应的专家信息表的id存入
            expertMapper.addExpertInformationResearchDirection(expertInformationResearchDirection); //新增专家信息表对应的研究方向列表
        }

        //新增该账号与角色之间的关系
        expertMapper.addUserRole(userInformation.getUid());
        return resultMap.success().message("账号分配成功");
    }

    //专家表的修改
    @Override
    public ResultMap expertModify(String token, HttpServletResponse response, String oldExpertFile, ExpertInformation expertInformation, MultipartFile expertFile) throws Exception {
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

        //判断旧文件是否存在
        if(oldExpertFile!=null){
            //旧文件存在，意味着，上传了新的文件
            //首先对旧文件进行删除
            //判断文件输入的格式是否正确
            ArrayList<String> idCardFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z", ".pdf"));
            String expertFileName = expertFile.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(expertFileName, idCardFileSuffixList);
            if (aBoolean == false) {
                return resultMap.fail().message("请上传正确的专家信息文件格式");
            }
            //再根据旧的文件地址，先把文件给删除掉
            File file = new File(oldExpertFile);
            file.delete();

            //对新的专家信息文件进行上传
            String expertFileUrl = FileUploadUtil.UploadExpertInformationFile(expertFile, "专家信息库");
            //把专家信息文件上传到upload_file中
            UploadFile uploadExpertFile = IntegrationFile.IntegrationFile(expertFile, expertFileUrl, "专家信息库", username);
            expertMapper.uploadFile(uploadExpertFile);   //对专家信息进行上传
            expertInformation.setExpertInformationUrlId(uploadExpertFile.getId());//把上传后的文件id，插入到专家信息表中

            //把专家具体信息更新到数据库中
            expertMapper.updateExpertInformation(expertInformation);

            //更新专家信息表中的文章表
            List<ExpertInformationArticle> expertInformationArticleList = expertInformation.getExpertInformationArticleList();  //获取专家信息表中文章列表集合
            //根据专家的id，把专家文章表对应的旧的信息删除
            expertMapper.deleteExpertInformationArticleByExpertId(expertInformation.getId());
            for (ExpertInformationArticle expertInformationArticle : expertInformationArticleList) {
                expertInformationArticle.setExpertId(expertInformation.getId());    //把文章列表中对应的专家信息表的id存入
                expertMapper.addExpertInformationArticle(expertInformationArticle); //新增专家信息表对应的文章列表

            }

            //更新专家信息表中著作表
            List<ExpertInformationBook> expertInformationBookList = expertInformation.getExpertInformationBookList();   //获取专家信息表中的著作列表集合
            //根据专家的id，把专家著作表对应的旧的信息删除
            expertMapper.deleteExpertInformationBookByExpertId(expertInformation.getId());
            for (ExpertInformationBook expertInformationBook : expertInformationBookList) {
                expertInformationBook.setExpertId(expertInformation.getId());   //把文章列表中的对应的专家信息表的id存入
                expertMapper.addExpertInformationBook(expertInformationBook);   //新增专家信息表对应的著作列表
            }

            //更新专家信息表中的专利表
            List<ExpertInformationPatent> expertInformationPatentList = expertInformation.getExpertInformationPatentList(); //获取专家信息表中的专利表集合
            //根据专家的id，把专家专利表对应的旧的信息删除
            expertMapper.deleteExpertInformationPatentByExpertId(expertInformation.getId());
            for (ExpertInformationPatent expertInformationPatent : expertInformationPatentList) {
                expertInformationPatent.setExpertId(expertInformation.getId());   //把文章列表中的对应的专家信息表的id存入
                expertMapper.addExpertInformationPatent(expertInformationPatent);   //新增专家信息表对应的专利列表
            }

            //更新专家信息表中的获奖表
            List<ExpertInformationPrizeWinning> expertInformationPrizeWinningList = expertInformation.getExpertInformationPrizeWinningList();   //获取专家信息表中的获奖表集合
            //根据专家的id，把专家获奖表对应的旧的信息删除
            expertMapper.deleteExpertInformationPrizeWinningByExpertId(expertInformation.getId());
            for (ExpertInformationPrizeWinning expertInformationPrizeWinning : expertInformationPrizeWinningList) {
                expertInformationPrizeWinning.setExpertId(expertInformation.getId());   //把获奖表中的对应的专家信息表的id存入
                expertMapper.addExpertInformationPrizeWinning(expertInformationPrizeWinning);   //新增专家信息表对应的获奖列表
            }

            //更新专家信息表中的研究方向
            List<ExpertInformationResearchDirection> expertInformationResearchDirectionList = expertInformation.getExpertInformationResearchDirectionList();     //获取专家信息表中的研究方向表集合
            //根据专家的id，把专家研究方向表对应的旧的信息删除
            expertMapper.deleteExpertInformationResearchDirectionByExpertId(expertInformation.getId());
            for (ExpertInformationResearchDirection expertInformationResearchDirection : expertInformationResearchDirectionList) {
                expertInformationResearchDirection.setExpertId(expertInformation.getId());  //把研究方向中的对应的专家信息表的id存入
                expertMapper.addExpertInformationResearchDirection(expertInformationResearchDirection); //新增专家信息表对应的研究方向列表
            }

        }else {
            //没有上传新的文件，只改变了数据库中的信息
            //把专家具体信息更新到数据库中,此时不需要更新文件id，因为文件还是原来的文件
            expertMapper.updateExpertInformationNotFileId(expertInformation);

            //更新专家信息表中的文章表
            List<ExpertInformationArticle> expertInformationArticleList = expertInformation.getExpertInformationArticleList();  //获取专家信息表中文章列表集合
            //根据专家的id，把专家文章表对应的旧的信息删除
            expertMapper.deleteExpertInformationArticleByExpertId(expertInformation.getId());
            for (ExpertInformationArticle expertInformationArticle : expertInformationArticleList) {
                expertInformationArticle.setExpertId(expertInformation.getId());    //把文章列表中对应的专家信息表的id存入
                expertMapper.addExpertInformationArticle(expertInformationArticle); //新增专家信息表对应的文章列表

            }

            //更新专家信息表中著作表
            List<ExpertInformationBook> expertInformationBookList = expertInformation.getExpertInformationBookList();   //获取专家信息表中的著作列表集合
            //根据专家的id，把专家著作表对应的旧的信息删除
            expertMapper.deleteExpertInformationBookByExpertId(expertInformation.getId());
            for (ExpertInformationBook expertInformationBook : expertInformationBookList) {
                expertInformationBook.setExpertId(expertInformation.getId());   //把文章列表中的对应的专家信息表的id存入
                expertMapper.addExpertInformationBook(expertInformationBook);   //新增专家信息表对应的著作列表
            }

            //更新专家信息表中的专利表
            List<ExpertInformationPatent> expertInformationPatentList = expertInformation.getExpertInformationPatentList(); //获取专家信息表中的专利表集合
            //根据专家的id，把专家专利表对应的旧的信息删除
            expertMapper.deleteExpertInformationPatentByExpertId(expertInformation.getId());
            for (ExpertInformationPatent expertInformationPatent : expertInformationPatentList) {
                expertInformationPatent.setExpertId(expertInformation.getId());   //把文章列表中的对应的专家信息表的id存入
                expertMapper.addExpertInformationPatent(expertInformationPatent);   //新增专家信息表对应的专利列表
            }

            //更新专家信息表中的获奖表
            List<ExpertInformationPrizeWinning> expertInformationPrizeWinningList = expertInformation.getExpertInformationPrizeWinningList();   //获取专家信息表中的获奖表集合
            //根据专家的id，把专家获奖表对应的旧的信息删除
            expertMapper.deleteExpertInformationPrizeWinningByExpertId(expertInformation.getId());
            for (ExpertInformationPrizeWinning expertInformationPrizeWinning : expertInformationPrizeWinningList) {
                expertInformationPrizeWinning.setExpertId(expertInformation.getId());   //把获奖表中的对应的专家信息表的id存入
                expertMapper.addExpertInformationPrizeWinning(expertInformationPrizeWinning);   //新增专家信息表对应的获奖列表
            }

            //更新专家信息表中的研究方向
            List<ExpertInformationResearchDirection> expertInformationResearchDirectionList = expertInformation.getExpertInformationResearchDirectionList();     //获取专家信息表中的研究方向表集合
            //根据专家的id，把专家研究方向表对应的旧的信息删除
            expertMapper.deleteExpertInformationResearchDirectionByExpertId(expertInformation.getId());
            for (ExpertInformationResearchDirection expertInformationResearchDirection : expertInformationResearchDirectionList) {
                expertInformationResearchDirection.setExpertId(expertInformation.getId());  //把研究方向中的对应的专家信息表的id存入
                expertMapper.addExpertInformationResearchDirection(expertInformationResearchDirection); //新增专家信息表对应的研究方向列表
            }
        }
        return resultMap.success().message("更新成功");
    }

    //修改专家的启用或者停用状态
    @Override
    public ResultMap changeState(Integer id, Boolean type) {
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

        //修改这个账号的启用状态
        if (type) {
            //当状态为true时, 则启用这个账号  0 启用 1停用
            expertMapper.changeStateStart(id);
        } else {
            //此时状态为false，停用这个账号
            expertMapper.changeStateEnd(id);
        }
        return resultMap.success().message("设置成功");
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

        //获取符合条件的专家id集合
        List<Integer> allExpertIdList = expertMapper.queryAllExpertIdList(newpage,total,name,natureWork,professionalField,isProvince);

        List<UserInformation> userInformationList = new ArrayList<>();
        for (Integer expertId : allExpertIdList) {
            UserInformation userInformation = expertMapper.queryExpertUserInformation(expertId);    //查询专家的基本信息
            ExpertInformation expertInformation = userInformation.getExpertInformation();
            expertInformation = expertMapper.queryExpertInformationByExpertId(expertId);  //查询专家的全部信息
            String expertFileUrl = expertMapper.queryExpertFileUrlById(expertInformation.getExpertInformationUrlId());  //根据文件的id，获取文件地址
            String fileName = expertMapper.queryExpertFileNameById(expertInformation.getExpertInformationUrlId());      //根据文件的id，获取文件的真实名称
            expertInformation.setExpertInformationUrl(expertFileUrl);
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

            userInformation.setExpertInformation(expertInformation);
            userInformationList.add(userInformation);

        }
        pageBean.setAlltotal(alltotal);
        pageBean.setData(userInformationList);
        return resultMap.success().message(pageBean);

    }


    //专家账号的审核
    @Override
    public ResultMap expertState(Boolean type, String reason, Integer id) throws updateSqlException {
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
                expertMapper.ExpertReason(id,reason);       //把审核未通过的原因写入数据库
            } catch (Exception e) {
                e.printStackTrace();
                throw new updateSqlException("ExpertServiceImpl中expertState方法 审核未通过时，更新账号状态错误"+e.getMessage());
            }
        }
        return resultMap.success().message("审核通过");
    }
}
