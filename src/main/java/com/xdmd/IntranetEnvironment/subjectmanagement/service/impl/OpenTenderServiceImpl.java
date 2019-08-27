package com.xdmd.IntranetEnvironment.subjectmanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdmd.IntranetEnvironment.common.AnnexUpload;
import com.xdmd.IntranetEnvironment.common.FileSuffixJudge;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectmanagement.mapper.OpenTenderMapper;
import com.xdmd.IntranetEnvironment.subjectmanagement.mapper.UploadFileMapper;
import com.xdmd.IntranetEnvironment.subjectmanagement.pojo.OpenTender;
import com.xdmd.IntranetEnvironment.subjectmanagement.service.OpenTenderService;
import com.xdmd.IntranetEnvironment.user.service.impl.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/07/26
 * @description: 招标备案业务实现
 */
@Service
public class OpenTenderServiceImpl implements OpenTenderService {
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
            int updateNo = openTenderMapper.updateTenderByoid(winningFileAttachmentId,announcementTransactionAnnouncementId,dealNotificationAttachmentId,responseFileAttachmentId,oid);
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
     * @param file
     * @param fileType
     * @return
     * @throws IOException
     */
    @Override
    public String tenderMultiUpload(MultipartFile file, String fileType, int oid) throws IOException {
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
        String FilePath = "D:/xdmd/environment/"+"單位名稱"+"/"+ketiName+"/"+fileType+"/";
        StringBuilder initPath = new StringBuilder(FilePath);
        String filePath=initPath.append(fileName).toString();
        System.out.println("文件路径-->"+filePath);
        File dest = new File(filePath);

        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //判断上传文件类型是否符合要求
        Boolean typeIsOK= FileSuffixJudge.suffixJudge(file.getOriginalFilename());
        if (typeIsOK==false){
            return "上传的文件类型不符合要求";
        }
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
            //封装到uploadfile
            AnnexUpload annexUpload=new AnnexUpload();
            annexUpload.setUploadFilePath(dest.getAbsolutePath());
            annexUpload.setFileSize(fileSize);
            annexUpload.setUploadFileName(fileName);
            annexUpload.setUploadFileType(fileType);
            annexUpload.setUploadSuffixName(suffixName);
            annexUpload.setCreateAuthor("創建者");
            //文件信息保存到数据库
            int upNo= uploadFileMapper.insertUpload(annexUpload);
            return "上传成功-->"+filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败";
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
