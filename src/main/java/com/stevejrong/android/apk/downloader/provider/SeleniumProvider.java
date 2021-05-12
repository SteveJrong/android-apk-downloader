package com.stevejrong.android.apk.downloader.provider;

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

    private static AppInfoBean appInfoBean;
    private static final String EXP_FOR_ANDROID_APP_STORE =
            "//div[@class='app_info']/child::span/child::a[text()='#APP_NAME']/ancestor::span[@class='app_name']/ancestor::div[@class='app_info']/following::div[@class='app_down']/child::a[text()='立即下载']";

    static {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-bean.xml");
        appInfoBean = (AppInfoBean) context.getBean("appInfoBean");
    }

    public void action() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        String downloadsPath = "/Users/stevejrong/Desktop/apks";
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadsPath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(ChromeOptions.CAPABILITY, options);

        WebDriver webDriver = new ChromeDriver(caps);

        if (appInfoBean.getAppNames().length > 0) {
            for (String appName : appInfoBean.getAppNames()) {
                webDriver.get("http://www.anzhi.com/search.php?keyword=" + appName);
                Thread.sleep(100);

                try {
                    webDriver.findElement(By.xpath(EXP_FOR_ANDROID_APP_STORE.replace("#APP_NAME", appName))).click();
                } catch (NoSuchElementException ex) {
                    System.out.println(appName + "未搜索到！");
                    continue;
                }
            }
        }
    }
}
