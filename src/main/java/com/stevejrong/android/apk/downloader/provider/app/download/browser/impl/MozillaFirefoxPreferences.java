package com.stevejrong.android.apk.downloader.provider.app.download.browser.impl;

import com.stevejrong.android.apk.downloader.provider.app.download.browser.AbstractBrowserPreferences;
import com.stevejrong.android.apk.downloader.provider.app.download.browser.IBrowserPreferences;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MozillaFirefoxPreferences extends AbstractBrowserPreferences implements IBrowserPreferences {

    @Override
    public WebDriver initBrowserPrefs() {
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setCapability("firefox_binary", "D:\\firefox\\firefox.exe");

        return new FirefoxDriver(caps);
    }
}
