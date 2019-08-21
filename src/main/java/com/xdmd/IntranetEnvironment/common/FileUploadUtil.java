package com.xdmd.IntranetEnvironment.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 文件上传工具类
 */
public class FileUploadUtil {

    /**
     * 0 为正确  1未错误
     *
     * @param file        上传的文件
     * @param companyName 公司名
     * @param type        类别
     * @return
     */
    public static String fileUpload(MultipartFile file, String companyName, String type) throws Exception {
        Logger log = LoggerFactory.getLogger(FileUploadUtil.class);

        HashMap<String, String> result = new HashMap<String, String>();

        //获取文件名
        String originalFilename = file.getOriginalFilename();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = dateFormat.format(date);

        String newOriginalFilename = nowTime + originalFilename;

        //文件保存的路径
        String FilePath = "D:/xdmd_environment/" + companyName + "/" + type + "/";

        File dest = new File(FilePath + newOriginalFilename);

        //检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            //文件上传
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("FileUploadUtils出错 :" + e.getMessage());
            throw new FileUploadException("文件上传失败");
        }

        return FilePath + newOriginalFilename;
    }

    //上传专家信息表
    public static String UploadExpertInformationFile(MultipartFile file, String type) throws Exception {
        Logger log = LoggerFactory.getLogger(FileUploadUtil.class);

        HashMap<String, String> result = new HashMap<String, String>();

        //获取文件名
        String originalFilename = file.getOriginalFilename();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = dateFormat.format(date);

        String newOriginalFilename = nowTime + originalFilename;

        //文件保存的路径
        String FilePath = "D:/xdmd_environment/" + type + "/";

        File dest = new File(FilePath + newOriginalFilename);

        //检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            //文件上传
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("FileUploadUtils出错 :" + e.getMessage());
            throw new FileUploadException("文件上传失败");
        }
        return FilePath + newOriginalFilename;
    }


    //上传专家组意见表 或 专家评议表
    public static String UploadSubejctAcceptExpertFile(MultipartFile file, String type, String companyName, String subjectName) throws Exception {
        Logger log = LoggerFactory.getLogger(FileUploadUtil.class);

        HashMap<String, String> result = new HashMap<String, String>();

        //获取文件名
        String originalFilename = file.getOriginalFilename();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = dateFormat.format(date);

        String newOriginalFilename = nowTime + originalFilename;

        //文件保存的路径
        String FilePath = "D:/xdmd_environment/" + companyName  + "/" + type + "/";

        File dest = new File(FilePath + newOriginalFilename);

        //检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            //文件上传
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("FileUploadUtils出错 :" + e.getMessage());
            throw new FileUploadException("文件上传失败");
        }

        return FilePath + newOriginalFilename;
    }

}
