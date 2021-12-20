package com.decucin.blog.service;

import com.decucin.blog.dao.pojo.SysUser;
import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.UserVo;
import com.decucin.blog.vo.params.LoginParams;
import com.decucin.blog.vo.params.PasswordParams;

public interface SysUserService {

    SysUser findUserById(Long id);

    SysUser findUserByUsername(String userName);

    Result findUserVoByToken(String token);

    Integer addUser(LoginParams loginParams);

<<<<<<< HEAD
    Result showInfo(Long id);
=======
    Result showInfo(String token);
>>>>>>> master

    Result updateInfo(UserVo userVo);

    Result changePassword(PasswordParams params);
}
