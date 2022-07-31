package com.decucin.blog.config;

import com.decucin.blog.handler.LoginIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/19 17:46
 * @description：WebMVC配置，主要处理跨域问题
 * @modified By：
 * @version: 1.0$
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Autowired
    private LoginIntercepter loginIntercepter;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域配置，将前端项目部署在3000端口
        registry.addMapping("/**").allowedOrigins("http://localhost:3000");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntercepter)
                .addPathPatterns("/test")
                .excludePathPatterns("/login")
                .excludePathPatterns("/register");
    }
}
