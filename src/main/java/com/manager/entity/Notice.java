package com.manager.entity;

public class Notice {

    private int id;
    private String content;
    private int isShow;

    public Notice() {
    }

    public Notice(String content, int isShow) {
        this.content = content;
        this.isShow = isShow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", isShow=" + isShow +
                '}';
    }
}
