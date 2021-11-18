package com.zerox.entity;

/**
 * @Author: zhuxi
 * @Time: 2021/10/14 11:45
 * @Description: 封装的响应结构
 * @ModifiedBy: zhuxi
 */
public class Result<T> {
    private Integer code;
    private String msg;
    private T body;

    private Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public Result(T body) {
        this(ResultCode.SUCCESS);
        this.body = body;
    }

    public Result(Throwable t) {
        this(ResultCode.ERROR);
        this.body = null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
