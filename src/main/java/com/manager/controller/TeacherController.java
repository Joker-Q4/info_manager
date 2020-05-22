package com.manager.controller;

import com.alibaba.fastjson.JSONObject;
import com.manager.entity.Entrepreneur;
import com.manager.entity.Project;
import com.manager.entity.Teacher;
import com.manager.global.Global;
import com.manager.global.GlobalKey;
import com.manager.global.JsonTools;
import com.manager.service.EntrepreneurService;
import com.manager.service.ProjectService;
import com.manager.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Resource
    private ProjectService projectService;

    @Resource
    private EntrepreneurService entrepreneurService;

    @Resource
    private TeacherService teacherService;

    @ResponseBody
    @RequestMapping(value = "/queryProjects", method = RequestMethod.GET)
    public Map<String, Object> index(
            HttpSession session,
            @RequestParam(defaultValue = "2") String currentState
            ){
        int account = Integer.valueOf((String) session.getAttribute("teacher"));
        List<Entrepreneur> entrepreneurs = entrepreneurService.getEntrepreneurList(account, 2);
        if(entrepreneurs == null || entrepreneurs.isEmpty()){
            return JsonTools.toResult(1, "无数据", 0, null);
        }
        List<Project> list = new ArrayList<>();
        for(Entrepreneur entrepreneur: entrepreneurs){
            list.addAll(projectService.getProjectTeacherList(entrepreneur.getAccount(), Integer.valueOf(currentState)));
        }
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
        CommonController.setTeacherAttributeProject(jsonObject, project);
        if(projectService.modifyProject(project))
            return JsonTools.toResult(true,"修改成功");
        else
            return JsonTools.toResult(false,"修改失败");
    }

    @ResponseBody
    @RequestMapping(value = "/queryEntrepreneurs", method = RequestMethod.GET)
    public Map<String, Object> queryStudents(
            @RequestParam(defaultValue = "") String applyTeacherAccount,
            @RequestParam(defaultValue = "") String currentState
    ){
        List<Entrepreneur> list;
        if(applyTeacherAccount != null && !applyTeacherAccount.isEmpty()){
            list = entrepreneurService.getEntrepreneurList(Integer.valueOf(applyTeacherAccount), Integer.valueOf(currentState));
        }else {
            list = entrepreneurService.getEntrepreneurList();
        }
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
        if(jsonObject.containsKey(GlobalKey.ENTREPRENEUR_STATE)){
            entrepreneur.setCurrentState(jsonObject.getInteger(GlobalKey.ENTREPRENEUR_STATE));
        }
        if(entrepreneurService.modifyEntrepreneur(entrepreneur))
            return JsonTools.toResult(true,"修改成功");
        else
            return JsonTools.toResult(false,"修改失败");
    }

    @ResponseBody
    @RequestMapping(value = "/queryInformation", method = RequestMethod.GET)
    public Map<String, Object> information(@RequestParam(defaultValue = "") String account){
        Teacher teacher;
        if(account != null && !account.isEmpty()){
            teacher = teacherService.getTeacherById(Integer.valueOf(account));
        }else {
            return JsonTools.toResult(false,"参数无效");
        }
        if(teacher == null){
            return JsonTools.toResult(false,"数据库无此项");
        }
        return JsonTools.toResult(0, "", 0, teacher);
    }

    @ResponseBody
    @RequestMapping(value = "/updateTeacher", method = RequestMethod.POST)
    public Map<String, Object> updateTeacher(
            HttpServletRequest request
    ) throws UnsupportedEncodingException {
        final Properties properties = Global.getRequest(request);
        final String json = properties.getProperty("json");
        JSONObject jsonObject = JSONObject.parseObject(json);
        int account = jsonObject.getInteger(GlobalKey.TEACHER_ACCOUNT);
        Teacher teacher = teacherService.getTeacherById(account);
        if(teacher == null){
            return JsonTools.toResult(false,"数据库无此项");
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
        if(teacherService.modifyTeacher(teacher))
            return JsonTools.toResult(true,"修改成功");
        else
            return JsonTools.toResult(false,"修改失败");
    }
}
