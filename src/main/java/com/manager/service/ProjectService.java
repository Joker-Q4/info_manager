package com.manager.service;

import com.manager.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getProjectList();
    List<Project> getProjectList(int entrepreneurId);
    List<Project> getProjectTeacherList(int entrepreneurId, int teacherState);
    List<Project> getProjectManagerList(int managerState);
    Project getProjectById(int id);
    boolean addProject(Project Project);
    boolean modifyProject(Project Project);
    boolean deleteProject(int id);
}
