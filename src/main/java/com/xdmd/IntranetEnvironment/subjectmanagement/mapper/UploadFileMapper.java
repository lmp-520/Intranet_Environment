package com.xdmd.IntranetEnvironment.subjectmanagement.mapper;

import com.xdmd.IntranetEnvironment.common.AnnexUpload;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author: Kong
 * @createDate: 2019/07/31
 * @description: 保存文件信息到数据库
 */
@Mapper
@Repository
public interface UploadFileMapper {
    /**
     *
     * @param annexUpload
     * @return
     */
    @Insert(value = "INSERT INTO upload_file (upload_file_path,upload_file_name, upload_file_type,upload_suffix_name,file_size,create_time,create_author)\n" +
            "VALUES(" +
            "#{uploadFilePath},"+
            "#{uploadFileName},"+
            "#{uploadFileType},"+
            "#{uploadSuffixName},"+
            "#{fileSize},"+
            "NOW(),"+
            "#{createAuthor})")
    Integer insertUpload(AnnexUpload annexUpload);

    /**
     * 根据文件名查询文件路径
     * @param id
     * @return
     */
    @Select(value = "select upload_file_path,upload_file_name from upload_file where id= #{id}")
    AnnexUpload getAnnexPath(int id);
}
