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

    Result showInfo(String token);

    Result updateInfo(UserVo userVo);

    Result changePassword(PasswordParams params);
}
