package com.decucin.blog.service;

import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.params.LoginParams;

public interface LoginService {

    Result login(LoginParams loginParams);

    Result logout(String token);

    Result register(LoginParams loginParams);

}
