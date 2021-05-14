package com.stevejrong.android.apk.downloader.provider.app.download.appDownload;

import com.stevejrong.android.apk.downloader.common.Constants;
import com.stevejrong.android.apk.downloader.config.download.DownloadConfig;
import com.stevejrong.android.apk.downloader.provider.app.download.browser.IBrowserPreferences;
import com.stevejrong.android.apk.downloader.util.FileUtil;
import com.stevejrong.android.apk.downloader.util.SpringBeanUtil;
import com.stevejrong.android.apk.downloader.util.XmlUtil;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class AbstractAppDownload {
    protected static DownloadConfig DOWNLOAD_CONFIG;

    protected String searchUrl;
    protected List<String> htmlAnalysisExpressions;

    protected AbstractAppDownload(String searchUrl, List<String> htmlAnalysisExpressions) {
        this.searchUrl = searchUrl;
        this.htmlAnalysisExpressions = htmlAnalysisExpressions;
    }

    /**
     * 根据默认配置来初始化浏览器参数
     *
     * @return
     */
    protected WebDriver initBrowserPrefsByDefaultConfig() {
        DOWNLOAD_CONFIG = SpringBeanUtil.getBean("downloadConfig");

        IBrowserPreferences browserPreferences = SpringBeanUtil.getBean(DOWNLOAD_CONFIG.getBrowserName());
        return browserPreferences.initBrowserPrefs();
    }
}
