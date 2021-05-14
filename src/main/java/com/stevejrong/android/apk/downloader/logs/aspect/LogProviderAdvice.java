package com.stevejrong.android.apk.downloader.logs.aspect;

import org.aspectj.lang.JoinPoint;

public class LogProviderAdvice {

    /**
     * 后置通知
     * 在目标方法执行无异常后执行
     */
    public void afterReturn(JoinPoint joinpoint, Object returnObject) {
        System.out.println(String.format("AOP：%o, %o", joinpoint, returnObject));
    }

    /**
     * 后置异常通知
     * 在目标方法执行异常后执行
     */
    public void afterException(JoinPoint joinpoint, Throwable exception) {
        System.out.println(String.format("AOP：%o, %o", joinpoint, exception));
    }
}
