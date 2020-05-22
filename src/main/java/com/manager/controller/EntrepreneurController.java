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
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/entrepreneur")
public class EntrepreneurController {

    private static final Logger logger = LoggerFactory.getLogger(EntrepreneurController.class);

    @Resource
    private ProjectService projectService;

    @Resource
    private EntrepreneurService entrepreneurService;

    @Resource
    private TeacherService teacherService;

    @ResponseBody
    @RequestMapping(value = "/queryProjects", method = RequestMethod.GET)
    public Map<String, Object> index(@RequestParam(defaultValue = "") String entrepreneurId){
        List<Project> list;
        if(entrepreneurId != null && !entrepreneurId.isEmpty()){
            list = projectService.getProjectList(Integer.valueOf(entrepreneurId));
        }else {
            return JsonTools.toResult(1, "参数异常", 0, null);
        }
        logger.info(list.toString());
        return JsonTools.toResult(0, "", list.size(), list);
    }

    @ResponseBody
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public Map<String, Object> addProject(
            HttpServletRequest request
    ) throws UnsupportedEncodingException {
        final Properties properties = Global.getRequest(request);
        final String json = properties.getProperty("json");
        JSONObject jsonObject = JSONObject.parseObject(json);
        Project project = new Project();
        CommonController.setEntrepreneurAttributeProjectAdd(jsonObject, project);
        project.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if(projectService.addProject(project))
            return JsonTools.toResult(true,"添加成功");
        else
            return JsonTools.toResult(false,"添加失败");
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
        CommonController.setEntrepreneurAttributeProject(jsonObject, project);
        if(projectService.modifyProject(project))
            return JsonTools.toResult(true,"修改成功");
        else
            return JsonTools.toResult(false,"修改失败");
    }

    @ResponseBody
    @RequestMapping(value = "/queryTeacher", method = RequestMethod.GET)
    public Map<String, Object> queryTeacher(@RequestParam(defaultValue = "") String entrepreneurId){
        Teacher teacher;
        Entrepreneur entrepreneur;
        if(entrepreneurId != null && !entrepreneurId.isEmpty()){
            entrepreneur = entrepreneurService.getEntrepreneurById(Integer.valueOf(entrepreneurId));
            if(entrepreneur == null)
                return JsonTools.toResult(1, "数据库无此创业者信息", 0, null);
            teacher = entrepreneur.getTeacher();
        }else {
            return JsonTools.toResult(1, "传递参数异常", 0, null);
        }
        if(teacher == null){
            return JsonTools.toResult(1, "无绑定教师信息，去挑选一个心仪的老师吧", 0, null);
        }else {
            List<Entrepreneur> list = new ArrayList<>();
            Teacher temp = teacherService.getTeacherById(teacher.getAccount());
            entrepreneur.setTeacher(temp);
            list.add(entrepreneur);
            return JsonTools.toResult(0, "", 1, list);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/queryTeachers", method = RequestMethod.GET)
    public Map<String, Object> queryTeachers(){
        List<Teacher> list = teacherService.getTeacherList();
        return JsonTools.toResult(0, "", list.size(), list);
    }

    @ResponseBody
    @RequestMapping(value = "/unBindTeacher", method = RequestMethod.POST)
    public Map<String, Object> unBindTeacher(
            HttpServletRequest request
    ) throws UnsupportedEncodingException {
        JSONObject jsonObject = CommonController.getJson(request);
        int account = jsonObject.getInteger(GlobalKey.ENTREPRENEUR_ACCOUNT);
        Entrepreneur entrepreneur = entrepreneurService.getEntrepreneurById(account);
        entrepreneur.setTeacherAccount(null);
        //默认
        entrepreneur.setCurrentState(3);
        if(entrepreneurService.deleteTeacher(entrepreneur)){
            return JsonTools.toResult(true,"解除绑定成功");
        }else {
            return JsonTools.toResult(true,"解除绑定失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/bindTeacher", method = RequestMethod.POST)
    public Map<String, Object> bindTeacher(
            HttpServletRequest request
    ) throws UnsupportedEncodingException {
        JSONObject jsonObject = CommonController.getJson(request);
        int account = jsonObject.getInteger(GlobalKey.ENTREPRENEUR_ACCOUNT);
        int teacherAccount = jsonObject.getInteger(GlobalKey.ENTREPRENEUR_TEACHER_ACCOUNT);
        Entrepreneur entrepreneur = entrepreneurService.getEntrepreneurById(account);
        entrepreneur.setTeacherAccount(teacherAccount);
        //申请中
        entrepreneur.setCurrentState(0);
        if(entrepreneurService.deleteTeacher(entrepreneur)){
            return JsonTools.toResult(true,"绑定成功");
        }else {
            return JsonTools.toResult(true,"绑定失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/queryInformation", method = RequestMethod.GET)
    public Map<String, Object> information(@RequestParam(defaultValue = "") String account){
        Entrepreneur entrepreneur;
        if(account != null && !account.isEmpty()){
            entrepreneur = entrepreneurService.getEntrepreneurById(Integer.valueOf(account));
        }else {
            return JsonTools.toResult(false,"参数无效");
        }
        if(entrepreneur == null){
            return JsonTools.toResult(false,"数据库无此项");
        }
        return JsonTools.toResult(0, "", 0, entrepreneur);
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
        if(entrepreneurService.modifyEntrepreneur(entrepreneur))
            return JsonTools.toResult(true,"修改成功");
        else
            return JsonTools.toResult(false,"修改失败");
    }

}
