package com.xdmd.IntranetEnvironment.subjectmanagement.uploadunits;

import com.xdmd.IntranetEnvironment.common.AnnexUpload;
import com.xdmd.IntranetEnvironment.common.FileSuffixJudge;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author: Kong
 * @createDate: 2019/8/22
 * @description: 文件上传工具类
 */
public class FileUploadUtil {
    /**
     * 招标附件上传
     * @param file
     * @param fileType
     * @return
     * @throws IOException
     */
    public static String FileUpload(MultipartFile file,String uploader,String unitName,String ketiName,String fileType) throws IOException {
        //判断文件是否为空
        if (file.isEmpty()) {
            return "上传文件不可为空";
        }
        // 获取文件名拼接当前系统时间作为新文件名
        String nowtime =  new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
        StringBuilder pinjiefileName=new StringBuilder(nowtime).append(file.getOriginalFilename());
        String fileName =pinjiefileName.toString();

       //获取招标课题名称
       // Map getTenderByIdMap=openTenderMapper.getTenderById(oid);
       //Object subjectName = getTenderByIdMap.get("subjectName");
        //获取文件上传绝对路径
        String FilePath = "D:/xdmd/environment/"+unitName+"/"+ketiName+"/"+fileType+"/";
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
            annexUpload.setCreateAuthor(uploader);
            //文件信息保存到数据库
            //int upNo= uploadFileMapper.insertUpload(annexUpload);
            return "上传成功-->"+filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}
