package com.manager.entity;

import java.sql.Timestamp;

/**
 * 项目
 */
public class Project {

    private int id;                //项目主键
    private String name;           //项目名称
    private String content;        //内容
    private double funds;          //预计经费
    private int managerState;      //状态
    private String managerBack;    //退回原因
    private int teacherState;      //状态
    private String teacherBack;    //退回原因
    private Timestamp createTime;  //创建时间
    private Timestamp updateTime;  //修改时间
    private int entrepreneurId;    //创业者ID
    private Entrepreneur entrepreneur;  //创业者

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public int getTeacherState() {
        return teacherState;
    }

    public void setTeacherState(int teacherState) {
        this.teacherState = teacherState;
    }

    public int getManagerState() {
        return managerState;
    }

    public void setManagerState(int managerState) {
        this.managerState = managerState;
    }

    public String getManagerBack() {
        return managerBack;
    }

    public void setManagerBack(String managerBack) {
        this.managerBack = managerBack;
    }

    public String getTeacherBack() {
        return teacherBack;
    }

    public void setTeacherBack(String teacherBack) {
        this.teacherBack = teacherBack;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getEntrepreneurId() {
        return entrepreneurId;
    }

    public void setEntrepreneurId(int entrepreneurId) {
        this.entrepreneurId = entrepreneurId;
    }

    public Entrepreneur getEntrepreneur() {
        return entrepreneur;
    }

    public void setEntrepreneur(Entrepreneur entrepreneur) {
        this.entrepreneur = entrepreneur;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", funds=" + funds +
                ", managerState=" + managerState +
                ", managerBack='" + managerBack + '\'' +
                ", teacherState=" + teacherState +
                ", teacherBack='" + teacherBack + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", entrepreneurId=" + entrepreneurId +
                ", entrepreneur=" + entrepreneur +
                '}';
    }
}
