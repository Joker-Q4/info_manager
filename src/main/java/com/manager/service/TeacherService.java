package com.manager.service;

import com.manager.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getTeacherList();
    Teacher getTeacherById(int account);
    boolean addTeacher(Teacher teacher);
    boolean modifyTeacher(Teacher teacher);
    boolean deleteTeacher(int account);
}
