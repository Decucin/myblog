package com.decucin.blog.controller;

import com.decucin.blog.service.LoginService;
import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/21 23:53
 * @description：登录功能
 * @modified By：
 * @version: 1.0$
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public Result login(@RequestBody LoginParam loginParam){
        return loginService.login(loginParam);
    }

    @GetMapping("logout")
    public Result logout(@RequestHeader("Authorization") String token){
        return loginService.logout(token);
    }

    @PostMapping("register")
    public Result register(@RequestBody LoginParam loginParam){
        return loginService.register(loginParam);
    }

}
