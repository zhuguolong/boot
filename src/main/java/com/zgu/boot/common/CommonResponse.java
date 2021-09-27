package com.zgu.boot.common;

public class CommonResponse {

    private Integer code;

    private String msg;

    private Object result;

    public CommonResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResponse(Integer code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
