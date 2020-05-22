package com.manager.controller;

import com.alibaba.fastjson.JSONObject;
import com.manager.entity.Entrepreneur;
import com.manager.entity.Manager;
import com.manager.entity.Notice;
import com.manager.entity.Teacher;
import com.manager.global.JsonTools;
import com.manager.service.EntrepreneurService;
import com.manager.service.ManagerService;
import com.manager.service.NoticeService;
import com.manager.service.TeacherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private NoticeService noticeService;

    @Resource
    private EntrepreneurService entrepreneurService;

    @Resource
    private TeacherService teacherService;

    @Resource
    private ManagerService managerService;

    @ResponseBody
    @RequestMapping(value = "/getNotice", method = RequestMethod.GET)
    public Map<String, Object> getNotice(){
        Notice notice = noticeService.getNotice();
        if(notice == null){
            return JsonTools.toResult(2, "获取公告信息异常", 0, null);
        }
        if(notice.getIsShow() == 0){
            return JsonTools.toResult(1, null, 0, null);
        }
        return JsonTools.toResult(0, "成功", 0, noticeService.getNotice().getContent());
    }

    @ResponseBody
    @RequestMapping(value = "/loginEntrepreneur", method = RequestMethod.POST)
    public Map<String, Object> loginEntrepreneur(
            HttpSession session,
            HttpServletRequest request
    ) throws UnsupportedEncodingException {
        JSONObject jsonObject = CommonController.getJson(request);
        if(jsonObject.containsKey("account")){
            if(!jsonObject.containsKey("password")){
                return JsonTools.toResult(false,"登录失败，密码不能为空");
            }
            String account = jsonObject.getString("account");
            if(account == null || account.isEmpty()){
                return JsonTools.toResult(false,"登录失败，账号不能为空");
            }
            String password = jsonObject.getString("password");
            if(password == null || password.isEmpty()){
                return JsonTools.toResult(false,"登录失败，密码不能为空");
            }
            Entrepreneur entrepreneur = entrepreneurService.getEntrepreneurById(Integer.valueOf(account));
            if(entrepreneur == null){
                return JsonTools.toResult(false,"登录失败，此账号不存在");
            }
            if(!jsonObject.containsKey("password")){
                return JsonTools.toResult(false,"登录失败，密码不能为空");
            }
            if(!password.equals(entrepreneur.getPassword())){
                return JsonTools.toResult(false,"登录失败，密码错误");
            }else {
                session.setAttribute("entrepreneur", account);
                return JsonTools.toResult(true,"登录成功");
            }
        }else {
            return JsonTools.toResult(false,"登录失败，账号不能为空");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/loginTeacher", method = RequestMethod.POST)
    public Map<String, Object> loginTeacher(
            HttpSession session,
            HttpServletRequest request
    ) throws UnsupportedEncodingException {
        JSONObject jsonObject = CommonController.getJson(request);
        if(jsonObject.containsKey("account")){
            if(!jsonObject.containsKey("password")){
                return JsonTools.toResult(false,"登录失败，密码不能为空");
            }
            String account = jsonObject.getString("account");
            if(account == null || account.isEmpty()){
                return JsonTools.toResult(false,"登录失败，账号不能为空");
            }
            String password = jsonObject.getString("password");
            if(password == null || password.isEmpty()){
                return JsonTools.toResult(false,"登录失败，密码不能为空");
            }
            Teacher teacher = teacherService.getTeacherById(Integer.valueOf(account));
            if(teacher == null){
                return JsonTools.toResult(false,"登录失败，此账号不存在");
            }
            if(!jsonObject.containsKey("password")){
                return JsonTools.toResult(false,"登录失败，密码不能为空");
            }
            if(!password.equals(teacher.getPassword())){
                return JsonTools.toResult(false,"登录失败，密码错误");
            }else {
                session.setAttribute("teacher", account);
                return JsonTools.toResult(true,"登录成功");
            }
        }else {
            return JsonTools.toResult(false,"登录失败，账号不能为空");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/loginManager", method = RequestMethod.POST)
    public Map<String, Object> loginManager(
            HttpSession session,
            HttpServletRequest request
    ) throws UnsupportedEncodingException {
        JSONObject jsonObject = CommonController.getJson(request);
        if(jsonObject.containsKey("account")){
            if(!jsonObject.containsKey("password")){
                return JsonTools.toResult(false,"登录失败，密码不能为空");
            }
            String account = jsonObject.getString("account");
            if(account == null || account.isEmpty()){
                return JsonTools.toResult(false,"登录失败，账号不能为空");
            }
            String password = jsonObject.getString("password");
            if(password == null || password.isEmpty()){
                return JsonTools.toResult(false,"登录失败，密码不能为空");
            }
            Manager manager = managerService.getManagerById(Integer.valueOf(account));
            if(manager == null){
                return JsonTools.toResult(false,"登录失败，此账号不存在");
            }
            if(!jsonObject.containsKey("password")){
                return JsonTools.toResult(false,"登录失败，密码不能为空");
            }
            if(!password.equals(manager.getPassword())){
                return JsonTools.toResult(false,"登录失败，密码错误");
            }else {
                session.setAttribute("manager", account);
                return JsonTools.toResult(true,"登录成功");
            }
        }else {
            return JsonTools.toResult(false,"登录失败，账号不能为空");
        }
    }
}
