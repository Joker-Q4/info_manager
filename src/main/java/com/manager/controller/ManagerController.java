package com.manager.controller;

import com.alibaba.fastjson.JSONObject;
import com.manager.entity.*;
import com.manager.global.GlobalKey;
import com.manager.global.JsonTools;
import com.manager.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Resource
    private NoticeService noticeService;

    @Resource
    private ProjectService projectService;

    @Resource
    private EntrepreneurService entrepreneurService;

    @Resource
    private TeacherService teacherService;

    @Resource
    private ManagerService managerService;

    @ResponseBody
    @RequestMapping(value = "/queryEntrepreneurs", method = RequestMethod.GET)
    public Map<String, Object> queryEntrepreneurs(){
        List<Entrepreneur> list = entrepreneurService.getEntrepreneurList();
        logger.info(list.toString());
        return JsonTools.toResult(0, "", list.size(), list);
    }

    @ResponseBody
    @RequestMapping(value = "/updateEntrepreneur", method = RequestMethod.POST)
    public Map<String, Object> updateEntrepreneur(
            HttpServletRequest request
    ) throws UnsupportedEncodingException {
        JSONObject jsonObject = CommonController.getJson(request);
        int account = jsonObject.getInteger(GlobalKey.ENTREPRENEUR_ACCOUNT);
        Entrepreneur entrepreneur = entrepreneurService.getEntrepreneurById(account);
        if(entrepreneur == null){
            return JsonTools.toResult(false,"数据库无此项");
        }
        CommonController.setManagerAttributeEntrepreneur(jsonObject, entrepreneur);
        if(entrepreneurService.modifyEntrepreneur(entrepreneur))
            return JsonTools.toResult(true,"修改成功");
        else
            return JsonTools.toResult(false,"修改失败");
    }

    @ResponseBody
    @RequestMapping(value = "/addEntrepreneur", method = RequestMethod.POST)
    public Map<String, Object> addEntrepreneur(
            HttpServletRequest request
    ) throws UnsupportedEncodingException {
        JSONObject jsonObject = CommonController.getJson(request);
        int account = jsonObject.getInteger(GlobalKey.ENTREPRENEUR_ACCOUNT);
        Entrepreneur entrepreneur = entrepreneurService.getEntrepreneurById(account);
        if(entrepreneur != null){
            return JsonTools.toResult(false,"数据库已存在此账号");
        }
        entrepreneur = new Entrepreneur();
        CommonController.addManagerAttributeEntrepreneur(jsonObject, entrepreneur);
        if(entrepreneurService.addEntrepreneur(entrepreneur))
            return JsonTools.toResult(true,"添加成功");
        else
            return JsonTools.toResult(false,"添加失败");
    }

    @ResponseBody
    @RequestMapping(value = "/queryTeachers", method = RequestMethod.GET)
    public Map<String, Object> queryTeachers(){
        List<Teacher> list = teacherService.getTeacherList();
        logger.info(list.toString());
        return JsonTools.toResult(0, "", list.size(), list);
    }

    @ResponseBody
    @RequestMapping(value = "/updateTeacher", method = RequestMethod.POST)
    public Map<String, Object> updateTeacher(
            HttpServletRequest request
    ) throws UnsupportedEncodingException {
        JSONObject jsonObject = CommonController.getJson(request);
        int account = jsonObject.getInteger(GlobalKey.TEACHER_ACCOUNT);
        Teacher teacher = teacherService.getTeacherById(account);
        if(teacher == null){
            return JsonTools.toResult(false,"数据库无此项");
        }
        CommonController.setManagerAttributeTeacher(jsonObject, teacher);
        if(teacherService.modifyTeacher(teacher))
            return JsonTools.toResult(true,"修改成功");
        else
            return JsonTools.toResult(false,"修改失败");
    }

    @ResponseBody
    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
    public Map<String, Object> addTeacher(
            HttpServletRequest request
    ) throws UnsupportedEncodingException {
        JSONObject jsonObject = CommonController.getJson(request);
        int account = jsonObject.getInteger(GlobalKey.TEACHER_ACCOUNT);
        Teacher teacher = teacherService.getTeacherById(account);
        if(teacher != null){
            return JsonTools.toResult(false,"数据库已存在此账号");
        }
        teacher = new Teacher();
        CommonController.addManagerAttributeTeacher(jsonObject, teacher);
        if(teacherService.addTeacher(teacher))
            return JsonTools.toResult(true,"添加成功");
        else
            return JsonTools.toResult(false,"添加失败");
    }

    @ResponseBody
    @RequestMapping(value = "/queryProjects", method = RequestMethod.GET)
    public Map<String, Object> queryProjects(@RequestParam(defaultValue = "2") String managerState){
        List<Project> list = projectService.getProjectManagerList(Integer.valueOf(managerState));
        logger.info(list.toString());
        return JsonTools.toResult(0, "", list.size(), list);
    }

    @ResponseBody
    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    public Map<String, Object> updateProject(
            HttpServletRequest request
    ) throws UnsupportedEncodingException {
        JSONObject jsonObject = CommonController.getJson(request);
        int id = jsonObject.getInteger(GlobalKey.PROJECT_ID);
        Project project = projectService.getProjectById(id);
        if(project == null){
            return JsonTools.toResult(false,"数据库无此项");
        }
        CommonController.setManagerAttributeProject(jsonObject, project);
        logger.info(project.toString());
        if(projectService.modifyProject(project))
            return JsonTools.toResult(true,"修改成功");
        else
            return JsonTools.toResult(false,"修改失败");
    }

    @ResponseBody
    @RequestMapping(value = "/queryInformation", method = RequestMethod.GET)
    public Map<String, Object> information(@RequestParam(defaultValue = "") String account){
        Manager manager;
        if(account != null && !account.isEmpty()){
            manager = managerService.getManagerById(Integer.valueOf(account));
        }else {
            return JsonTools.toResult(false,"参数无效");
        }
        if(manager == null){
            return JsonTools.toResult(false,"数据库无此项");
        }
        return JsonTools.toResult(0, "", 0, manager);
    }

    @ResponseBody
    @RequestMapping(value = "/updateManager", method = RequestMethod.POST)
    public Map<String, Object> updateManager(
            HttpServletRequest request
    ) throws UnsupportedEncodingException {
        JSONObject jsonObject = CommonController.getJson(request);
        int account = jsonObject.getInteger(GlobalKey.MANAGER_ACCOUNT);
        Manager manager = managerService.getManagerById(account);
        if(manager == null){
            return JsonTools.toResult(false,"数据库无此项");
        }
        CommonController.setManagerAttribute(jsonObject, manager);
        if(managerService.modifyManager(manager))
            return JsonTools.toResult(true,"修改成功");
        else
            return JsonTools.toResult(false,"修改失败");
    }

    @ResponseBody
    @RequestMapping(value = "/getNotice", method = RequestMethod.GET)
    public Map<String, Object> getNotice(){
        Notice notice = noticeService.getNotice();
        if(notice == null){
            return JsonTools.toResult(2, "获取公告信息异常", 0, null);
        }
        return JsonTools.toResult(0, "成功", 1, noticeService.getNotice());
    }

    @ResponseBody
    @RequestMapping(value = "/updateNotice", method = RequestMethod.POST)
        public Map<String, Object> updateNotice(
            HttpServletRequest request
    ) throws UnsupportedEncodingException {
        JSONObject jsonObject = CommonController.getJson(request);
        String content = jsonObject.getString("content");
        String isShow = jsonObject.getString("isShow");
        Notice notice = new Notice(content, Integer.valueOf(isShow));
        if(noticeService.modifyNotice(notice)){
            return JsonTools.toResult(true, "修改成功");
        }
        return JsonTools.toResult(false, "修改失败");
    }
}
