package com.decucin.blog.handler;

import com.alibaba.fastjson.JSON;
import com.decucin.blog.dao.pojo.SysUser;
import com.decucin.blog.service.LoginService;
import com.decucin.blog.service.SysUserService;
import com.decucin.blog.utils.UserThreadLocal;
import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.ResultEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginIntercepter implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 判断当前方法是否需要拦截
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        String token = request.getHeader("Authorization");
        if(StringUtils.isBlank(token)){
            Result fail = Result.fail(ResultEnum.ILLEGAL_TOKEN);
            response.getWriter().print(JSON.toJSON(fail));
            return false;
        }
        // 登录逻辑
        SysUser user = loginService.checkToken(token);
        if(user == null){
            Result result = Result.fail(ResultEnum.ILLEGAL_TOKEN);
            response.getWriter().print(JSON.toJSON(result));
            return false;
        }

        // 将用户信息保存在ThreadLocal中
        UserThreadLocal.put(user);

        // 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
