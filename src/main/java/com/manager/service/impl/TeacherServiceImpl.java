package com.manager.service.impl;

import com.manager.entity.Teacher;
import com.manager.mapper.TeacherMapper;
import com.manager.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Resource
    TeacherMapper teacherMapper;

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public List<Teacher> getTeacherList() {
        return teacherMapper.queryTeacher();
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public Teacher getTeacherById(int account) {
        return teacherMapper.queryTeacherById(account);
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public boolean addTeacher(Teacher teacher) {
        return teacherMapper.insertTeacher(teacher) > 0;
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public boolean modifyTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher) > 0;
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public boolean deleteTeacher(int account) {
        return teacherMapper.deleteTeacher(account) > 0;
    }
}
