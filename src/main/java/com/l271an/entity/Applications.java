package com.l271an.entity;

public class Applications {
    private Integer id;
    private Integer user;
    private String content;
    private Integer status;
    private String time;

    public Applications(Integer id, Integer user, String content, Integer status, String time) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.status = status;
        this.time = time;
    }

    public Applications() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
