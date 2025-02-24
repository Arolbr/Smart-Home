package com.l271an.entity;

public class Data {
    private Integer id;
    private Integer device;
    private String type;
    private Double value;
    private String time;

    public Data() {
    }

    public Data(Integer id, Integer device, String type, Double value, String time) {
        this.id = id;
        this.device = device;
        this.type = type;
        this.value = value;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDevice() {
        return device;
    }

    public void setDevice(Integer device) {
        this.device = device;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
