package com.manager.entity;

/**
 * 创业者
 */
public class Entrepreneur {

    private int account;            //账户
    private String name;            //姓名
    private String password;        //密码
    private String sex;             //性别
    private String phoneNumber;     //联系方式
    private String address;         //家庭住址
    private String resume;          //个人简历
    private int currentState;       //申请教师的状态
    private Integer teacherAccount;     //教师ID
    private Teacher teacher;        //指导教师

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public Integer getTeacherAccount() {
        return teacherAccount;
    }

    public void setTeacherAccount(Integer teacherAccount) {
        this.teacherAccount = teacherAccount;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Entrepreneur{" +
                "account=" + account +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", resume='" + resume + '\'' +
                ", currentState=" + currentState +
                ", teacherAccount=" + teacherAccount +
                ", teacher=" + teacher +
                '}';
    }
}
