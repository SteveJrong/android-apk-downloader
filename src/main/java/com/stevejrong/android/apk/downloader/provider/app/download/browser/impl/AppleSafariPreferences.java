package com.stevejrong.android.apk.downloader.provider.app.download.browser.impl;

import com.stevejrong.android.apk.downloader.provider.app.download.browser.AbstractBrowserPreferences;
import com.stevejrong.android.apk.downloader.provider.app.download.browser.IBrowserPreferences;
import org.openqa.selenium.WebDriver;

public class AppleSafariPreferences extends AbstractBrowserPreferences implements IBrowserPreferences {
    @Override
    public WebDriver initBrowserPrefs() {
        return null;
    }
}
