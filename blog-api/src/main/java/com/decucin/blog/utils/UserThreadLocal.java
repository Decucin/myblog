package com.decucin.blog.utils;

import com.decucin.blog.dao.pojo.SysUser;

public class UserThreadLocal {
    
    private UserThreadLocal(){

    }

    public static final ThreadLocal<SysUser>  LOCAL = new ThreadLocal<>();

    public static void put(SysUser user){
        LOCAL.set(user);
    }

    public static SysUser get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
