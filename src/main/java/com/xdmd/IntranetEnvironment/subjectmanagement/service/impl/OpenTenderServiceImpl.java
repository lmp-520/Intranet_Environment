package com.xdmd.IntranetEnvironment.subjectmanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdmd.IntranetEnvironment.common.*;
import com.xdmd.IntranetEnvironment.subjectmanagement.mapper.OpenTenderMapper;
import com.xdmd.IntranetEnvironment.subjectmanagement.mapper.UploadFileMapper;
import com.xdmd.IntranetEnvironment.subjectmanagement.pojo.OpenTender;
import com.xdmd.IntranetEnvironment.subjectmanagement.service.OpenTenderService;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import com.xdmd.IntranetEnvironment.user.pojo.User;
import com.xdmd.IntranetEnvironment.user.service.impl.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: Kong
 * @createDate: 2019/07/26
 * @description: 招标备案业务实现
 */
@Service
public class OpenTenderServiceImpl implements OpenTenderService {
    Logger log = LoggerFactory.getLogger(OpenTenderServiceImpl.class);

    @Autowired
    OpenTenderMapper openTenderMapper;
    @Autowired
    UploadFileMapper uploadFileMapper;
    @Autowired
    TokenService tokenService;


    /**
     * 状态码
     */
    ResultMap resultMap = new ResultMap();


    /**
     * 新增
     * @param openTender
     * @return
     */
    @Override
    public ResultMap insertTender(OpenTender openTender) {
        try{
            openTender.setProjectNo(setProjectNo());
            int insertNo=openTenderMapper.insertTender(openTender);
            if(insertNo>0){
                resultMap.success().message("成功新增"+insertNo+"条数据");
            }else if(insertNo==0){
                resultMap.success().message("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 根據单位id查詢相应单位的招标公告
     * @param uid
     * @param projectName
     * @param subjectName
     * @param subjectLeader
     * @param leaderContact
     * @return
     */
    @Override
    public ResultMap getTenderByUid(int uid, String projectName, String subjectName, String subjectLeader, String leaderContact,int pagenNum,int pageSize) {
        try{
            PageHelper.startPage(pagenNum,pageSize,true);
            List<Map> getTenderByUidMap = openTenderMapper.getTenderByUid(uid,projectName,subjectName,subjectLeader,leaderContact);
            PageInfo pageInfo=new PageInfo(getTenderByUidMap);
            if(getTenderByUidMap!=null){
                resultMap.success().message(pageInfo);
            }else if(getTenderByUidMap==null){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 根據id获取招标公告详情
     * @param id
     * @return
     */
    @Override
    public ResultMap getTenderById(int id) {
        try{
            Map getTenderByIdMap = openTenderMapper.getTenderById(id);
            if(getTenderByIdMap!=null){
                resultMap.success().message(getTenderByIdMap);
            }else if(getTenderByIdMap==null){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 课题编号自增设置
     * @param
     */
    public String setProjectNo(){
        getNewData();
        String subString = new String(openTenderMapper.getNewData().getProjectNo());
        /**
         * 分离出数字并转换成int类型
         */
        int num = Integer.parseInt(subString.substring(4));
        if (num<=20190000) {
            /**
             * 获取当前年份
             */
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date date = new Date();
            String sDate = date.toString();
            sDate = sdf.format(date);
            /**
             * 拼接课题编号和年份
             */
            StringBuilder number = new StringBuilder(sDate);
            number.append("0000");
            num = Integer.parseInt(number.toString());
        }
        num += 1;

        StringBuilder sBuilder = new StringBuilder("xdmd");
        String finalResult=sBuilder.insert(sBuilder.length(), num).toString();
        return finalResult;
    }

    /**
     * 分页筛选查询招标信息
     * @param projectName
     * @param subjectName
     * @param subjectLeader
     * @param leaderContact
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultMap getTenderPageList(String projectName, String subjectName, String subjectLeader, String leaderContact, int pageNum, int pageSize) {
        try{
            PageHelper.startPage(pageNum, pageSize);
            List<Map> openTenderList = openTenderMapper.getTenderPageList(projectName, subjectName, subjectLeader, leaderContact);
            PageInfo pageInfo=new PageInfo(openTenderList);
            if(openTenderList!=null){
                resultMap.success().message(pageInfo);
            }else if(openTenderList==null){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 根据招标备案id更新相应的附件id【外网上传附件】
     * @param winningFileAttachmentId
     * @param announcementTransactionAnnouncementId
     * @param dealNotificationAttachmentId
     * @param responseFileAttachmentId
     * @param oid
     * @return
     */
    @Override
    public ResultMap updateTenderByoid(int winningFileAttachmentId, int announcementTransactionAnnouncementId, int dealNotificationAttachmentId, int responseFileAttachmentId,int oid) {
        try{
            int updateNo = openTenderMapper.updateAnnexByoid(winningFileAttachmentId,announcementTransactionAnnouncementId,dealNotificationAttachmentId,responseFileAttachmentId,oid);
            if(updateNo>0){
                resultMap.success().message("成功更新"+updateNo+"条数据");
            }else if(updateNo<0){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 获取最新id
     * @return
     */
    @Override
    public OpenTender getNewData() {
        return openTenderMapper.getNewData();
    }


    /**
     * 招标附件上传
     * @param oid 招标备案表id
     * @param winningDocument 中标文件附件
     * @param transactionAnnouncement 成交公告附件
     * @param noticeTransaction 成交通知书附件
     * @param responseFile 响应文件附件
     * @return
     * @throws IOException
     */
    @Override
    public ResultMap tenderMultiUpload(String token, HttpServletResponse response, Integer oid, MultipartFile winningDocument, MultipartFile transactionAnnouncement, MultipartFile noticeTransaction, MultipartFile responseFile) throws IOException, FileUploadException {
        User user = new User();
        try {
            user = tokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e){
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        }catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }
        //当前登录者
        Integer uid = user.getId();
        String username = user.getUsername();
        //根据招标备案表的id 获取该公司的名字
        String uname = openTenderMapper.queryUnitNameByOid(oid);


        //判断上传中标文件附件的后缀名是否正确
        String winningDocumentName = winningDocument.getOriginalFilename();
        List<String> winningDocumentSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".xlsx", ".zip", ".7z", ".rar"));
        Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(winningDocumentName, winningDocumentSuffixList);
        //判断上传成交公告附件的后缀名是否正确
        String transactionAnnouncementName = transactionAnnouncement.getOriginalFilename();
        List<String> transactionAnnouncementSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".xlsx", ".zip", ".7z", ".rar"));
        Boolean bBoolean = FileSuffixJudgeUtil.SuffixJudge(transactionAnnouncementName, transactionAnnouncementSuffixList);
        //判断上传成交公告附件的后缀名是否正确
        String noticeTransactionName = noticeTransaction.getOriginalFilename();
        List<String> noticeTransactionSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".xlsx", ".zip", ".7z", ".rar"));
        Boolean cBoolean = FileSuffixJudgeUtil.SuffixJudge(noticeTransactionName, noticeTransactionSuffixList);
        //判断上传响应文件附件的后缀名是否正确
        String responseFileName = responseFile.getOriginalFilename();
        List<String> responseFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".xlsx", ".zip", ".7z", ".rar"));
        Boolean dBoolean = FileSuffixJudgeUtil.SuffixJudge(responseFileName, responseFileSuffixList);

        if(aBoolean == false || bBoolean == false||cBoolean == false || dBoolean == false){
            //四个文件中存在有一个错误，意味着有文件上传的格式不正确
            return resultMap.fail().message("请上传正确的文件格式");
        }
        /**
         * 上传中标文件附件
         */
        //获取中标文件附件的地址
        String winningDocumentUrl =MultiFileUpload(winningDocument,uname,"中标文件附件",oid);
        //获取文件后缀名
        String winningDocumentSuffixName = winningDocumentName.substring(winningDocumentName.lastIndexOf(".") + 1);
        // 获取文件大小
        File winningDocumentFile= new File(winningDocumentUrl);
        String winningDocumentFileSize = String.valueOf(winningDocumentFile.length());
        AnnexUpload annexUpload0=new AnnexUpload(0,winningDocumentUrl,winningDocumentName,"中标文件附件",winningDocumentSuffixName,winningDocumentFileSize,null,username);
        //把该文件上传到文件表中
        uploadFileMapper.insertUpload(annexUpload0);
        /**
         * 成交公告附件
         */
        //获取中标文件附件的地址
        String transactionAnnouncementUrl =MultiFileUpload(transactionAnnouncement,uname,"中标文件附件",oid);
        //获取文件后缀名
        String transactionAnnouncementSuffixName = transactionAnnouncementName.substring(winningDocumentName.lastIndexOf(".") + 1);
        // 获取文件大小
        File transactionAnnouncementFile= new File(transactionAnnouncementUrl);
        String transactionAnnouncementFileSize = String.valueOf(winningDocumentFile.length());
        AnnexUpload annexUpload1=new AnnexUpload(0,transactionAnnouncementUrl,transactionAnnouncementName,"成交公告附件",transactionAnnouncementSuffixName,transactionAnnouncementFileSize,null,username);
        //把该文件上传到文件表中
        uploadFileMapper.insertUpload(annexUpload1);
        /**
         * 暂未写完
         */











        //openTenderMapper.updateCheckApplyFileId(id,uploadSpecialAudit.getId());//把新增该专项审计报告文件的id取出，存到check_apply中
        return resultMap.success().message("上传成功");
    }


    /**
     *　文件上传
     * @param file
     * @param fileType
     * @return
     * @throws IOException
     */
    public String MultiFileUpload(MultipartFile file, String unitName,String fileType, int oid) throws IOException, FileUploadException {
        //判断文件是否为空
        if (file.isEmpty()) {
            return "上传文件不可为空";
        }
        // 获取文件名拼接当前系统时间作为新文件名
        String nowtime =  new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
        StringBuilder pinjiefileName=new StringBuilder(nowtime).append(file.getOriginalFilename());
        String fileName =pinjiefileName.toString();

        //获取招标课题名稱
        Object ketiName = openTenderMapper.getTenderById(oid).get("subjectName");
        //获取招标课题編號
        Object ketiNo=openTenderMapper.getTenderById(oid).get("ProjectNo");

        //获取文件上传绝对路径
        String FilePath = "D:/xdmd/environment/"+unitName+"/"+ketiName+"/"+ketiNo+"/"+fileType+"/";
        StringBuilder initPath = new StringBuilder(FilePath);
        String filePath=initPath.append(fileName).toString();
        File dest = new File(filePath);

        //获取文件后缀名
        //String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //判断上传文件类型是否符合要求
        //Boolean typeIsOK= FileSuffixJudge.suffixJudge(file.getOriginalFilename());
        //if (typeIsOK==false){
          //  return "上传的文件类型不符合要求";
        //}

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            //保存文件
            file.transferTo(dest);
            // 获取文件大小
            File file1 = new File(filePath);
            String fileSize = String.valueOf(file1.length());
            //返回文件url
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("FileUploadUtils出错 :" + e.getMessage());
            throw new FileUploadException("文件上传失败");
        }
    }


    /**
     * 招标备案审核
     * @param type 审核状态
     * @param reason 审核不通过原因
     * @param oid 审核表id
     * @return
     */
    @Override
    public ResultMap tenderShenHe(Boolean type, String reason, Integer oid) {
        return null;
    }


}
