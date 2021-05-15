/**
 * Copyright ${YEAR} Steve Jrong
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
