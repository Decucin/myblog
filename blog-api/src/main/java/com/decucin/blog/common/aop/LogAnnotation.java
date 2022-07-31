package com.decucin.blog.common.aop;


import java.lang.annotation.*;

@Target(ElementType.METHOD) //加在方法上
@Documented
@Retention(RetentionPolicy.RUNTIME) //运行时有效
public @interface LogAnnotation {

    String module() default "";
    String operation() default "";

}
