package com.manager.mapper;

import com.manager.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {

    List<Teacher> queryTeacher();
    Teacher queryTeacherById(int account);
    int insertTeacher(Teacher teacher);
    int updateTeacher(Teacher teacher);
    int deleteTeacher(int account);
}
