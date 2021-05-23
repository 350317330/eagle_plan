package com.example.demo.aop;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
@Documented
public @interface Security {


    /**
     * 入参是否解密，默认解密
     */
    boolean encrypt() default true;

    /**
     * 出参是否加密，默认加密
     */
    boolean decrypt() default true;

}
