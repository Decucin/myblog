package com.decucin.blog.service;

import com.decucin.blog.dao.pojo.SysUser;
import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.UserVo;
import com.decucin.blog.vo.params.LoginParam;
import com.decucin.blog.vo.params.PasswordParam;

public interface SysUserService {

    SysUser findUserById(Long id);

    SysUser findUserByUsername(String userName);

    Result findUserVoByToken(String token);

    Integer addUser(LoginParam loginParam);


    Result showInfo(String token);


    Result updateInfo(UserVo userVo);

    Result changePassword(PasswordParam params);
}
