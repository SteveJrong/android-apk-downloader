package com.stevejrong.android.apk.downloader.provider.app.download.browser.impl;

import com.stevejrong.android.apk.downloader.common.Constants;
import com.stevejrong.android.apk.downloader.provider.app.download.browser.AbstractBrowserPreferences;
import com.stevejrong.android.apk.downloader.provider.app.download.browser.IBrowserPreferences;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class GoogleChromePreferences extends AbstractBrowserPreferences implements IBrowserPreferences {

    @Override
    public WebDriver initBrowserPrefs() {
        System.setProperty(Constants.CHROME_WEB_DRIVER_PACKAGE_NAME.val(), DOWNLOAD_CONFIG.getBrowserWebDriverPath());

        String downloadsPath = DOWNLOAD_CONFIG.getBrowserDownloadPath();
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put(Constants.CHROME_DOWNLOAD_DIRECTORY_PREFERENCE_COMMAND.val(), downloadsPath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(Constants.CHROME_PREFERENCE_OPTION.val(), chromePrefs);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(ChromeOptions.CAPABILITY, options);

        return new ChromeDriver(caps);
    }
}
