package com.stevejrong.android.apk.downloader.util;

import com.stevejrong.android.apk.downloader.common.Constants;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanUtil {
    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(Constants.SPRING_XML_FILE_NAME.val());

    /**
     * 从静态变量applicationContext中得到Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(String name) {
        if (name == null || applicationContext == null)
            return null;
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中得到Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
