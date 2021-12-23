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
    ILLEGAL_TOKEN(201,"illegal token"),
    NOT_FOUND (404, "article not found"),
    COMMENT_NOT_EXIST(406,"comment not exist"),
    USER_NOT_EXIST(400,"user not exist"),
//    LIKE(300, "operate successfully"),
    ALREADY_OPERATE(301,"already operated"),
    ILLEGAL_OPERATION(407, "illegal_operation"),
    ERROR_PASSWORD(402,"error password"),
//    ERROR_OPERATION(405,"error operation"),
    ERROR_TOKEN(403,"illegal token"),
    NOT_NULL(408, "username/password can not null"),
    USER_EXIST(409, "user already exist");

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
