package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insert(Note note);

    @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
    int update(Note note);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Note> getUsersNotes(Integer userid);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    int deleteById(Integer noteid);
}