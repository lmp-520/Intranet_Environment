package com.xdmd.IntranetEnvironment.dailymanagement.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdmd.IntranetEnvironment.common.AnnexUpload;
import com.xdmd.IntranetEnvironment.common.FileSuffixJudge;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.dailymanagement.mapper.MajorMattersFilingMapper;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.AdjustTypeDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.AdjustmentMattersDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MajorMattersFilingDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.service.MajorMattersFilingService;
import com.xdmd.IntranetEnvironment.subjectmanagement.mapper.UploadFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/08/19
 * @description: 重大事项变更
 */
@Service
public class MajorMattersFilingServiceImpl implements MajorMattersFilingService {
    @Autowired
    MajorMattersFilingMapper majorMattersFilingMapper;
    @Autowired
    UploadFileMapper uploadFileMapper;
    ResultMap resultMap = new ResultMap();

    /**
     * 新增【waiwang】
     *
     * @param majorMattersFiling
     * @return
     */
    @Override
    public ResultMap insert(MajorMattersFilingDTO majorMattersFiling) {
        try {
            int insertNo = majorMattersFilingMapper.insert(majorMattersFiling);
            if (insertNo > 0) {
                resultMap.success().message("新增" + insertNo + "条数据");
            } else if (insertNo == 0) {
                resultMap.fail().message("新增失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * [更新]重大事项附件id【waiwang】
     *
     * @return
     */
    @Override
    public ResultMap updateAnnexId(int changeApplicationAttachmentId, int expertArgumentationAttachmentId, int filingApplicationAttachmentId, int approvalDocumentsAttachmentId, int id) {
        try {
            int updateNo = majorMattersFilingMapper.updateAnnexId(changeApplicationAttachmentId, expertArgumentationAttachmentId, filingApplicationAttachmentId, approvalDocumentsAttachmentId, id);
            if (updateNo > 0) {
                resultMap.success().message("成功更新" + updateNo + "条数据");
            } else if (updateNo == 0) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * [查詢] 根據主鍵 id 查詢【内外网】
     *
     * @param id
     * @return
     */
    @Override
    public ResultMap getMajorById(int id) {
        try {
            MajorMattersFilingDTO majors = majorMattersFilingMapper.getMajorById(id);
            if (majors != null) {
                resultMap.success().message(majors);
            } else if (majors == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 分页筛选查询【内网】
     * @param subjectName
     * @param commitmentUnit
     * @param adjustTypId
     * @param adjustmentMattersId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultMap getAllMajorInfo(String subjectName, String commitmentUnit, Integer adjustTypId, Integer adjustmentMattersId, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize,true);
            List<Map> majors = majorMattersFilingMapper.getAllMajorInfo(subjectName, commitmentUnit, adjustTypId, adjustmentMattersId);
            PageInfo pageInfo=new PageInfo(majors);
            if (majors != null) {
                resultMap.success().message(pageInfo);
            } else if (majors == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 根据单位id分页筛选查询【内网】
     * @param uid
     * @param subjectName
     * @param commitmentUnit
     * @param adjustTypId
     * @param adjustmentMattersId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultMap getAllMajorInfoByUid(int uid, String subjectName, String commitmentUnit, Integer adjustTypId, Integer adjustmentMattersId, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize,true);
            List<Map> majorsByUid = majorMattersFilingMapper.getAllMajorInfoByUid(uid,subjectName, commitmentUnit, adjustTypId, adjustmentMattersId);
            PageInfo pageInfo=new PageInfo((List) majorsByUid);
            if (majorsByUid != null) {
                resultMap.success().message(pageInfo);
            } else if (majorsByUid == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 更新重大审核状态
     * @param id
     * @return
     */
    @Override
    public ResultMap updateMajorStatus(int id) {
        try {
            int updateNo = majorMattersFilingMapper.updateMajorStatus(id);
            if (updateNo > 0) {
                resultMap.success().message("成功更新" + updateNo + "条数据");
            } else if (updateNo == 0) {
                resultMap.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 查询所有调整类型
     *
     * @return
     */
    @Override
    public ResultMap AdjustType() {
        try {
            List<AdjustTypeDTO> adjustTypeList = majorMattersFilingMapper.getAllAdjustType();
            if (adjustTypeList != null) {
                resultMap.success().message(adjustTypeList);
            } else if (adjustTypeList == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 查询所有调整事项
     *
     * @return
     */
    @Override
    public ResultMap AdjustmentMatters() {
        try {
            List<AdjustmentMattersDTO> adjustmentMattersList = majorMattersFilingMapper.getAllAdjustmentMatters();
            if (adjustmentMattersList != null) {
                resultMap.success().message(adjustmentMattersList);
            } else if (adjustmentMattersList == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 重大事项附件上传
     * @param file
     * @param fileType
     * @return
     * @throws IOException
     */
    @Override
    public String MultipartFileUpload(MultipartFile file, String fileType, int mid) throws IOException {
        //判断文件是否为空
        if (file.isEmpty()) {
            return "上传文件不可为空";
        }
        // 获取文件名拼接当前系统时间作为新文件名
        String nowtime =  new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
        StringBuilder pinjiefileName=new StringBuilder(nowtime).append(file.getOriginalFilename());
        String fileName =pinjiefileName.toString();

        //获取课题名称
        Object ketiName=getMajorById(mid).get("subjectName");
        //获取文件上传绝对路径
        String FilePath = "D:/xdmd/environment/"+ketiName+"/"+fileType+"/";
        StringBuilder initPath = new StringBuilder(FilePath);
        String filePath=initPath.append(fileName).toString();
        File dest=new File(filePath);

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
            AnnexUpload annexUpload = new AnnexUpload();
            annexUpload.setUploadFileAddress(filePath);
            annexUpload.setFileSize(fileSize);
            annexUpload.setUploadFileName(fileName);
            annexUpload.setUploadFileType(fileType);
            annexUpload.setUploadSuffixName(suffixName);
            annexUpload.setCreateAuthor("创建者");
            //文件信息保存到数据库
            int upNo= uploadFileMapper.insertUpload(annexUpload);
            return "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}
