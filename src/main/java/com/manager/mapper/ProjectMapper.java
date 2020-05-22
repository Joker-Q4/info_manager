package com.manager.mapper;

import com.manager.entity.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {

    List<Project> queryProject();
    List<Project> queryProjectByEntrepreneur(int entrepreneurId);
    List<Project> queryProjectByTeacher(int entrepreneurId, int teacherState, int managerState);
    List<Project> queryProjectByManager(int managerState);
    Project queryProjectById(int id);
    int insertProject(Project project);
    int updateProject(Project project);
    int deleteProject(int id);
}
