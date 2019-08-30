package com.xdmd.IntranetEnvironment.common;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * @author: Kong
 * @createDate: 2019/07/31
 * @description: 保存文件信息到数据库【通用上传sql】
 */
@Mapper
@Repository
public interface UploadFileMapper {
    /**
     *
     * @param annexUpload
     * @return
     */
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    @Insert(value = "INSERT INTO upload_file (upload_file_address,upload_file_name, upload_file_type,upload_suffix_name,file_size,create_time,create_author)\n" +
            "VALUES(" +
            "#{uploadFileAddress},"+
            "#{uploadFileName},"+
            "#{uploadFileType},"+
            "#{uploadSuffixName},"+
            "#{fileSize},"+
            "NOW(),"+
            "#{createAuthor})")
    Integer insertUpload(AnnexUpload annexUpload);
}
