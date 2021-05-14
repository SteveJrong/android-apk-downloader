package com.stevejrong.android.apk.downloader.config.download;

import com.stevejrong.android.apk.downloader.provider.app.download.appDownload.IAppDownload;

import java.util.List;

/**
 * 应用下载相关配置类
 */
public class DownloadConfig {
    /**
     * 要使用的浏览器名称
     */
    private String browserName;

    /**
     * 浏览器Web驱动的存放路径
     */
    private String browserWebDriverPath;

    /**
     * 设置浏览器中文件下载的存放路径
     */
    private String browserDownloadPath;

    /**
     * 安卓APP下载网站的配置
     */
    private List<IAppDownload> appDownloadSitesConfig;

    /**
     * 指定当前APP应用列表节点名称
     */
    private String currentAppListNodeName;

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserWebDriverPath() {
        return browserWebDriverPath;
    }

    public void setBrowserWebDriverPath(String browserWebDriverPath) {
        this.browserWebDriverPath = browserWebDriverPath;
    }

    public String getBrowserDownloadPath() {
        return browserDownloadPath;
    }

    public void setBrowserDownloadPath(String browserDownloadPath) {
        this.browserDownloadPath = browserDownloadPath;
    }

    public String getCurrentAppListNodeName() {
        return currentAppListNodeName;
    }

    public void setCurrentAppListNodeName(String currentAppListNodeName) {
        this.currentAppListNodeName = currentAppListNodeName;
    }

    public List<IAppDownload> getAppDownloadSitesConfig() {
        return appDownloadSitesConfig;
    }

    public void setAppDownloadSitesConfig(List<IAppDownload> appDownloadSitesConfig) {
        this.appDownloadSitesConfig = appDownloadSitesConfig;
    }
}

