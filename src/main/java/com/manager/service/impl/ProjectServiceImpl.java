package com.manager.service.impl;

import com.manager.entity.Project;
import com.manager.mapper.ProjectMapper;
import com.manager.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Resource
    ProjectMapper projectMapper;

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public List<Project> getProjectList() {
        return projectMapper.queryProject();
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public List<Project> getProjectList(int entrepreneurId) {
        return projectMapper.queryProjectByEntrepreneur(entrepreneurId);
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public List<Project> getProjectTeacherList(int entrepreneurId, int teacherState) {
        return projectMapper.queryProjectByTeacher(entrepreneurId, teacherState, 2);
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public List<Project> getProjectManagerList(int managerState) {
        return projectMapper.queryProjectByManager(managerState);
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public Project getProjectById(int id) {
        return projectMapper.queryProjectById(id);
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public boolean addProject(Project Project) {
        return projectMapper.insertProject(Project) > 0;
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public boolean modifyProject(Project Project) {
        return projectMapper.updateProject(Project) > 0;
    }

    @Transactional(propagation= Propagation.NESTED)
    @Override
    public boolean deleteProject(int id) {
        return projectMapper.deleteProject(id) > 0;
    }
}
