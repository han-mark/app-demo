package com.bird.business.annotation;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {
	//配置接入点
    @Pointcut("execution(* com.bird.business.controller..*.*(..))")
    private void controllerAspect(){}//定义一个切入点
    @Before("controllerAspect()")
    public void dataSwitch(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature =(MethodSignature) signature;
        Method method = methodSignature.getMethod();
        DataSource data = null;
        String dataSource = "";
        if(method != null){
            data = method.getAnnotation(DataSource.class);
            if(data != null){
                //获取dataSource注解的参数值
                dataSource = data.dataSource();
                if(!StringUtils.isBlank(dataSource)){
                    DynamicDataSource.setDataSourceKey(dataSource);
                }
            } else {
                //spring-mybatis.xml配置了默认的数据源,但是两个数据源之间存在乱跳
                //此处做配置,若没有使用DataSource注解,设置默认数据源
                DynamicDataSource.setDataSourceKey("dataSource");
            }
        }
    }
}
