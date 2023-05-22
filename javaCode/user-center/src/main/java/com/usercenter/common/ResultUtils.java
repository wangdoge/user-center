package com.usercenter.common;

/**
 * Date 2023/5/16 21:19
 * author:wyf
 * 返回工具类
 */
public class ResultUtils {
    /**
     * 成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0,data,"ok");
    }

    /**
     * 失败
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode){
        return new BaseResponse(errorCode);
    }

    /**
     * 失败
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode,String message,String description){
        return new BaseResponse(errorCode.getCode(),message,description);
    }

    /**
     * 失败
     * @param code
     * @return
     */
    public static BaseResponse error(int code,String message,String description){
        return new BaseResponse(code,message,description);
    }
}
