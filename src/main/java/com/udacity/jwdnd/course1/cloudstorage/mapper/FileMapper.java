package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<File> getUsersFiles(Integer userid);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId} AND userid = #{userid}")
    File getUserFile(Integer fileId, Integer userid);

    @Select("SELECT EXISTS(SELECT 1 FROM FILES WHERE filename = #{filename} AND userid = #{userid})")
    boolean filenameExistsForUser(String filename, Integer userid);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int deleteById(Integer fileId);
}