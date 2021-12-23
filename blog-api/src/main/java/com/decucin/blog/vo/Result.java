package com.decucin.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/21 21:35
 * @description：这个类用来保存同意显示的结果
 * @modified By：
 * @version: 1.0$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object data;

    public Result(ResultEnum resultEnum, Object data){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = data;
    }

    /**
    * @param data
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:46
    **/
    public static Result success(Object data){
        /**
         *  TODO 通过data返回成功结果
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        return new Result(ResultEnum.SUCCESS, data);
    }

    /**
    *  @param code
    *  @param msg
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:47
    **/
    public static Result fail(int code, String msg){
        /**
         *  TODO 通过code以及msg返回错误信息
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        return new Result(code, msg, null);
    }

    public static  Result fail(ResultEnum resultEnum){
        return fail(resultEnum.getCode(), resultEnum.getMsg());
    }
}
