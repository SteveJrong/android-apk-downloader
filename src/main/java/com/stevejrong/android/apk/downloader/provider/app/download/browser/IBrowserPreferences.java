package com.stevejrong.android.apk.downloader.provider.app.download.browser;

import org.openqa.selenium.WebDriver;

public interface IBrowserPreferences {

    /**
     * 浏览器参数及设置初始化
     *
     * @return
     */
    WebDriver initBrowserPrefs();
}
