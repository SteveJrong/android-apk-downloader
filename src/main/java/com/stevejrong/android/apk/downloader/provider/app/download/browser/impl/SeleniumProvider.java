/*
package com.stevejrong.android.apk.downloader.provider.app.download.impl;

import com.stevejrong.android.apk.downloader.bean.AppInfoBean;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class SeleniumProvider {

    private static final String EXP_FOR_ANDROID_APP_STORE =
            "";

    public void action() throws InterruptedException {


        if (appInfoBean.getAppNames().length > 0) {
            for (String appName : appInfoBean.getAppNames()) {
                webDriver.get("http://www.anzhi.com/search.php?keyword=" + appName);
                Thread.sleep(900);

                try {
                    webDriver.findElement(By.xpath(EXP_FOR_ANDROID_APP_STORE.replace("#APP_NAME", appName))).click();
                } catch (NoSuchElementException ex) {
                    System.out.println(appName + "未搜索到！");
                }
            }
        }
    }
}
*/
