package com.manager.controller;

import com.alibaba.fastjson.JSONObject;
import com.manager.entity.Entrepreneur;
import com.manager.entity.Manager;
import com.manager.entity.Project;
import com.manager.entity.Teacher;
import com.manager.global.Global;
import com.manager.global.GlobalKey;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Properties;

class CommonController {

    static JSONObject getJson(HttpServletRequest request) throws UnsupportedEncodingException {
        final Properties properties = Global.getRequest(request);
        final String json = properties.getProperty("json");
        return JSONObject.parseObject(json);
    }

    //教师更新项目
    static void setTeacherAttributeProject(JSONObject jsonObject, Project project){
        if(jsonObject.containsKey(GlobalKey.PROJECT_T_STATE)){
            project.setTeacherState(jsonObject.getInteger(GlobalKey.PROJECT_T_STATE));
        }
        if(jsonObject.containsKey(GlobalKey.PROJECT_FUNDS)){
            project.setFunds(jsonObject.getDouble(GlobalKey.PROJECT_FUNDS));
        }
        if(jsonObject.containsKey(GlobalKey.PROJECT_T_BACK)){
            project.setTeacherBack(jsonObject.getString(GlobalKey.PROJECT_T_BACK));
        }
        project.setUpdateTime(new Timestamp(System.currentTimeMillis()));
    }

    //管理员更新项目
    static void setManagerAttributeProject(JSONObject jsonObject, Project project){
        if(jsonObject.containsKey(GlobalKey.PROJECT_M_STATE)){
            project.setManagerState(jsonObject.getInteger(GlobalKey.PROJECT_M_STATE));
        }
        if(jsonObject.containsKey(GlobalKey.PROJECT_FUNDS)){
            project.setFunds(jsonObject.getDouble(GlobalKey.PROJECT_FUNDS));
        }
        if(jsonObject.containsKey(GlobalKey.PROJECT_M_BACK)){
            project.setManagerBack(jsonObject.getString(GlobalKey.PROJECT_M_BACK));
        }
        project.setUpdateTime(new Timestamp(System.currentTimeMillis()));
    }

    //创业者更新项目
    static void setEntrepreneurAttributeProject(JSONObject jsonObject, Project project){
        if(jsonObject.containsKey(GlobalKey.PROJECT_NAME)){
            project.setName(jsonObject.getString(GlobalKey.PROJECT_NAME));
        }
        if(jsonObject.containsKey(GlobalKey.PROJECT_CONTENT)){
            project.setContent(jsonObject.getString(GlobalKey.PROJECT_CONTENT));
        }
        if(jsonObject.containsKey(GlobalKey.PROJECT_FUNDS)){
            project.setFunds(jsonObject.getDouble(GlobalKey.PROJECT_FUNDS));
        }
        project.setUpdateTime(new Timestamp(System.currentTimeMillis()));
    }

    //创业者添加项目
    static void setEntrepreneurAttributeProjectAdd(JSONObject jsonObject, Project project){
        if(jsonObject.containsKey(GlobalKey.PROJECT_NAME)){
            project.setName(jsonObject.getString(GlobalKey.PROJECT_NAME));
        }
        if(jsonObject.containsKey(GlobalKey.PROJECT_CONTENT)){
            project.setContent(jsonObject.getString(GlobalKey.PROJECT_CONTENT));
        }
        if(jsonObject.containsKey(GlobalKey.PROJECT_FUNDS)){
            project.setFunds(jsonObject.getDouble(GlobalKey.PROJECT_FUNDS));
        }
        if(jsonObject.containsKey(GlobalKey.ENTREPRENEUR_ACCOUNT)){
            project.setEntrepreneurId(jsonObject.getInteger(GlobalKey.ENTREPRENEUR_ACCOUNT));
        }
    }

    //管理员修改创业者
    static void setManagerAttributeEntrepreneur(JSONObject jsonObject, Entrepreneur entrepreneur){
        if(jsonObject.containsKey(GlobalKey.ENTREPRENEUR_PASSWORD)){
            entrepreneur.setPassword(jsonObject.getString(GlobalKey.ENTREPRENEUR_PASSWORD));
        }
        if(jsonObject.containsKey(GlobalKey.ENTREPRENEUR_SEX)){
            entrepreneur.setSex(jsonObject.getString(GlobalKey.ENTREPRENEUR_SEX));
        }
        if(jsonObject.containsKey(GlobalKey.ENTREPRENEUR_PHONE)){
            entrepreneur.setPhoneNumber(jsonObject.getString(GlobalKey.ENTREPRENEUR_PHONE));
        }
        if(jsonObject.containsKey(GlobalKey.ENTREPRENEUR_ADDRESS)){
            entrepreneur.setAddress(jsonObject.getString(GlobalKey.ENTREPRENEUR_ADDRESS));
        }
        if(jsonObject.containsKey(GlobalKey.ENTREPRENEUR_RESUME)){
            entrepreneur.setResume(jsonObject.getString(GlobalKey.ENTREPRENEUR_RESUME));
        }
    }

    //管理员添加创业者
    static void addManagerAttributeEntrepreneur(JSONObject jsonObject, Entrepreneur entrepreneur){
        if(jsonObject.containsKey(GlobalKey.ENTREPRENEUR_ACCOUNT)){
            entrepreneur.setAccount(jsonObject.getInteger(GlobalKey.ENTREPRENEUR_ACCOUNT));
        }
        if(jsonObject.containsKey(GlobalKey.ENTREPRENEUR_NAME)){
            entrepreneur.setName(jsonObject.getString(GlobalKey.ENTREPRENEUR_NAME));
        }
        if(jsonObject.containsKey(GlobalKey.ENTREPRENEUR_PASSWORD)){
            entrepreneur.setPassword(jsonObject.getString(GlobalKey.ENTREPRENEUR_PASSWORD));
        }
        if(jsonObject.containsKey(GlobalKey.ENTREPRENEUR_SEX)){
            entrepreneur.setSex(jsonObject.getString(GlobalKey.ENTREPRENEUR_SEX));
        }
        if(jsonObject.containsKey(GlobalKey.ENTREPRENEUR_PHONE)){
            entrepreneur.setPhoneNumber(jsonObject.getString(GlobalKey.ENTREPRENEUR_PHONE));
        }
        if(jsonObject.containsKey(GlobalKey.ENTREPRENEUR_ADDRESS)){
            entrepreneur.setAddress(jsonObject.getString(GlobalKey.ENTREPRENEUR_ADDRESS));
        }
        if(jsonObject.containsKey(GlobalKey.ENTREPRENEUR_RESUME)){
            entrepreneur.setResume(jsonObject.getString(GlobalKey.ENTREPRENEUR_RESUME));
        }
    }

    //管理员修改教师
    static void setManagerAttributeTeacher(JSONObject jsonObject, Teacher teacher){
        if(jsonObject.containsKey(GlobalKey.TEACHER_PASSWORD)){
            teacher.setPassword(jsonObject.getString(GlobalKey.TEACHER_PASSWORD));
        }
        if(jsonObject.containsKey(GlobalKey.TEACHER_SEX)){
            teacher.setSex(jsonObject.getString(GlobalKey.TEACHER_SEX));
        }
        if(jsonObject.containsKey(GlobalKey.TEACHER_PHONE)){
            teacher.setPhoneNumber(jsonObject.getString(GlobalKey.TEACHER_PHONE));
        }
        if(jsonObject.containsKey(GlobalKey.TEACHER_ADDRESS)){
            teacher.setAddress(jsonObject.getString(GlobalKey.TEACHER_ADDRESS));
        }
        if(jsonObject.containsKey(GlobalKey.TEACHER_RESUME)){
            teacher.setResume(jsonObject.getString(GlobalKey.TEACHER_RESUME));
        }
    }

    //管理员添加教师
    static void addManagerAttributeTeacher(JSONObject jsonObject, Teacher teacher){
        if(jsonObject.containsKey(GlobalKey.TEACHER_ACCOUNT)){
            teacher.setAccount(jsonObject.getInteger(GlobalKey.TEACHER_ACCOUNT));
        }
        if(jsonObject.containsKey(GlobalKey.TEACHER_NAME)){
            teacher.setName(jsonObject.getString(GlobalKey.TEACHER_NAME));
        }
        if(jsonObject.containsKey(GlobalKey.TEACHER_PASSWORD)){
            teacher.setPassword(jsonObject.getString(GlobalKey.TEACHER_PASSWORD));
        }
        if(jsonObject.containsKey(GlobalKey.TEACHER_SEX)){
            teacher.setSex(jsonObject.getString(GlobalKey.TEACHER_SEX));
        }
        if(jsonObject.containsKey(GlobalKey.TEACHER_PHONE)){
            teacher.setPhoneNumber(jsonObject.getString(GlobalKey.TEACHER_PHONE));
        }
        if(jsonObject.containsKey(GlobalKey.TEACHER_ADDRESS)){
            teacher.setAddress(jsonObject.getString(GlobalKey.TEACHER_ADDRESS));
        }
        if(jsonObject.containsKey(GlobalKey.TEACHER_RESUME)){
            teacher.setResume(jsonObject.getString(GlobalKey.TEACHER_RESUME));
        }
    }

    //修改管理员个人信息
    static void setManagerAttribute(JSONObject jsonObject, Manager manager){
        if(jsonObject.containsKey(GlobalKey.MANAGER_PASSWORD)){
            manager.setPassword(jsonObject.getString(GlobalKey.MANAGER_PASSWORD));
        }
        if(jsonObject.containsKey(GlobalKey.MANAGER_SEX)){
            manager.setSex(jsonObject.getString(GlobalKey.MANAGER_SEX));
        }
        if(jsonObject.containsKey(GlobalKey.MANAGER_PHONE)){
            manager.setPhoneNumber(jsonObject.getString(GlobalKey.MANAGER_PHONE));
        }
        if(jsonObject.containsKey(GlobalKey.MANAGER_ADDRESS)){
            manager.setAddress(jsonObject.getString(GlobalKey.MANAGER_ADDRESS));
        }
        if(jsonObject.containsKey(GlobalKey.MANAGER_RESUME)){
            manager.setResume(jsonObject.getString(GlobalKey.MANAGER_RESUME));
        }
    }
}
