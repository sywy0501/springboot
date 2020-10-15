package com.cs.springboot.qiniuyun.utils;

/**
 * @description:
 * @author: chushi
 * @create: 2020-07-28 11:20
 **/
public enum MsgEnum {
    SUCCESS_1(200, "文件上传成功"),
    OK_1(400, "请先登录"),
    OK_2(401, "文件非图片"),
    OK_3(402, "图片上传失败"),
    ;

    private Integer code;
    private String message;

    MsgEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
