package com.lixianch.common;

/**
 * created by lixianch on 2023/4/14
 */
public class ResultModel<T> {

    private static final String succ = "0000";
    private static final String fail = "9999";
    private static final String SUCC_MESSAGE = "成功";
    private String code;
    private String message;
    private T data;
    public ResultModel(String code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static ResultModel fail(String message){
        return new ResultModel<>(fail, message, null);
    }

    public static ResultModel success(){
        return new ResultModel(succ, SUCC_MESSAGE, null);
    }
    public static <T> ResultModel success(T data){
        return new ResultModel(succ, SUCC_MESSAGE, data);
    }

    public boolean isSuccess(){
        return succ.equals(this.code);
    }
}
