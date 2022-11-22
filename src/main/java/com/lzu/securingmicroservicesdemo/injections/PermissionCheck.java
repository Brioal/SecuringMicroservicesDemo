package com.lzu.securingmicroservicesdemo.injections;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionCheck {

    // 是否允许管理员使用
    boolean ADMIN_ALLOW() default false;

    // 是否允许用户使用
    boolean USER_ALLOW() default false;

    // 必须的权限
    String requireAuthorities() default "";

}
