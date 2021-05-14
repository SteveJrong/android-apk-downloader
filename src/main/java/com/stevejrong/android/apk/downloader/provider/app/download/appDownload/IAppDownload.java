package com.stevejrong.android.apk.downloader.provider.app.download.appDownload;

import org.openqa.selenium.WebDriver;

public interface IAppDownload {

    /**
     * 浏览器参数及设置初始化
     *
     * 此方法实现时不需调用
     *
     * @return
     */
    WebDriver initBrowserPrefs();

    /**
     * 自动化下载
     */
    void automaticDownload();
}
