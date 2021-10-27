package com.decucin.blog.vo;


/**
 * @author ：decucin
 * @date ：Created in 2021/10/21 21:35
 * @description：这个类用来保存结果的枚举信息
 * @modified By：
 * @version: 1.0$
 */
public enum ResultEnum {


    SUCCESS(200, "success"),
    NOT_FOUND (404, "page not found");

    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
