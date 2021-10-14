package com.zerox.entity;

/**
 * @Author: zhuxi
 * @Time: 2021/10/14 14:35
 * @Description:
 * @ModifiedBy: zhuxi
 */
public enum ResultCode {
    SUCCESS(0, "成功"),
    ERROR(-1, "抛出异常");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
