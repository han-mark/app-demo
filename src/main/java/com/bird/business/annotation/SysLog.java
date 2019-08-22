package com.bird.business.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
	//注解参数设置,可设置多个
	String value() default "";
}