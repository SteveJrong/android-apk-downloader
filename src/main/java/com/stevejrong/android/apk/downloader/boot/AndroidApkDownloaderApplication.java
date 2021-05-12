package com.stevejrong.android.apk.downloader.boot;

import com.stevejrong.android.apk.downloader.provider.SeleniumProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AndroidApkDownloaderApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-bean.xml");

        SeleniumProvider seleniumProvider = (SeleniumProvider) context.getBean("seleniumProvider");
        seleniumProvider.action();
    }
}
