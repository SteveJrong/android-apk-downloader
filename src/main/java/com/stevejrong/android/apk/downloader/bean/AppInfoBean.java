package com.stevejrong.android.apk.downloader.bean;

public class AppInfoBean {
    private String[] appNames;

    public String[] getAppNames() {
        return appNames;
    }

    public void setAppNames(String[] appNames) {
        this.appNames = appNames;
    }

    public AppInfoBean(String[] appNames) {
        this.appNames = appNames;
    }

    public AppInfoBean() {
    }
}
