package com.l271an.entity;

public class Result {
    private String code;
    private String message;
    private boolean success;
    private Object data;

    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}
