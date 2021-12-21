package com.decucin.blog.controller;

import com.decucin.blog.vo.Result;
import com.decucin.blog.service.SysUserService;
import com.decucin.blog.vo.UserVo;
import com.decucin.blog.vo.params.PasswordParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/20 14:38
 * @description：与用户有关功能
 * @modified By：
 * @version: 1.0$
 */
@RestController
@RequestMapping("users")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token){
        return sysUserService.findUserVoByToken(token);
    }

    @GetMapping("showInfo")
    public Result showInfo(@RequestHeader("Authorization") String token){
        return sysUserService.showInfo(token);
    }

    @PostMapping("updateInfo")
    public Result updateInfo(@RequestHeader("Authorization") String token, @RequestBody UserVo userVo){
        return sysUserService.updateInfo(token, userVo);
    }

    @PostMapping("changePassword")
    public Result changePassword(@RequestHeader("Authorization") String token, @RequestBody PasswordParam params){
        return sysUserService.changePassword(token, params);
    }
}
