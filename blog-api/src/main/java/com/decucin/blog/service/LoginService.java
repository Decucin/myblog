package com.decucin.blog.service;

import com.decucin.blog.dao.pojo.SysUser;
import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.params.LoginParam;

public interface LoginService {

    Result login(LoginParam loginParam);

    Result logout(String token);

    Result register(LoginParam loginParam);

    SysUser checkToken(String token);

}
