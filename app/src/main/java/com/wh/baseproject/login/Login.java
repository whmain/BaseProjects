package com.wh.baseproject.login;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author PC-WangHao on 2019/09/10 16:13.
 * E-Mail: wh_main@163.com
 * Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Login {
    boolean isNeed() default false;
}
