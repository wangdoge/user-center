package com.usercenter.common;

/**
 * Date 2023/5/16 22:02
 * author:wyf
 * 错误码
 *
 */
public enum ErrorCode {
    SUCCESS(0,"OK",""),
    PARAM_ERROR(40000,"请求参数为空",""),
    PARAM_NULL_ERROR(40001,"请求数据为空",""),
    NO_AUTH(40101,"无权限",""),
    NOT_LOGIN(40100,"未登录",""),
    SYSTEM_ERROR(50000,"系统内部异常","");


    private final int code;
    private final String message;
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
