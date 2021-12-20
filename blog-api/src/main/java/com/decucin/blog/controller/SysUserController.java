package com.decucin.blog.controller;

import com.decucin.blog.vo.Result;
import com.decucin.blog.service.SysUserService;
import com.decucin.blog.vo.UserVo;
import com.decucin.blog.vo.params.PasswordParams;
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

    @GetMapping("showInfo/{id}")
<<<<<<< HEAD
    public Result showInfo(@PathVariable("id") Long id){
        return sysUserService.showInfo(id);
=======
    public Result showInfo(@RequestHeader("Authorization") String token){
        return sysUserService.showInfo(token);
>>>>>>> master
    }

    @PostMapping("updateInfo")
    public Result updateInfo(@RequestBody UserVo userVo){
        return sysUserService.updateInfo(userVo);
    }

    @PostMapping("changePassword")
    public Result changePassword(@RequestBody PasswordParams params){
        return sysUserService.changePassword(params);
    }
}
