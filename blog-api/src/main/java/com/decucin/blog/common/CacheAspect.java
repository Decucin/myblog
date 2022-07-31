package com.decucin.blog.common;


import com.alibaba.fastjson.JSON;
import com.decucin.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;

@Aspect
@Slf4j
@Component
public class CacheAspect {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Pointcut("@annotation(com.decucin.blog.common.Cache)")
    public void pt(){};

    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint){
        try {
            Signature signature = joinPoint.getSignature();
            // 类名
            String className = joinPoint.getTarget().getClass().getSimpleName();
            // 方法名
            String methodName = signature.getName();

            Class[] parameterTypes = new Class[joinPoint.getArgs().length];

            Object[] args = joinPoint.getArgs();

            String params = "";

            for(int i = 0; i < args.length; ++i){
                if(args[i] != null){
                    params += JSON.toJSONString(args[i]);
                    parameterTypes[i] = args[i].getClass();
                }else{
                    parameterTypes[i] = null;
                }
            }
            if(StringUtils.isEmpty(params)){
                DigestUtils.md5Hex(params);
            }
            Method method = joinPoint.getSignature().getDeclaringType().getMethod(methodName, parameterTypes);
            Cache annotation = method.getAnnotation(Cache.class);

            // 得到缓存的过期事件以及名称
            long expire = annotation.expire();
            String name = annotation.name();

            // 先查redis，再查数据库
            String redisKey = name + "::" + className + "::" + methodName + "::" + params;
            String redisValue = redisTemplate.opsForValue().get(redisKey);
            // 不为空说明缓存中有
            if(StringUtils.isNotEmpty(redisValue)){
                log.info("从缓存中取值:{}, {}, => {}", className, methodName, redisValue);
                return JSON.parseObject(redisValue, Result.class);
            }

            // 此时缓存中没有
            Object proceed = joinPoint.proceed();
            // 放入缓存中
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONString(proceed), Duration.ofMillis(expire));
            log.info("放入缓存中:{}, {}", className, methodName);
            return proceed;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return Result.fail(500, "系统错误");
    }


}
