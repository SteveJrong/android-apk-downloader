package com.stevejrong.android.apk.downloader.config.download;

@Deprecated
public class AndroidAppStoresConfig {
    /**
     * 下载网站对应的SpringBean ID
     */
    private String springBeanNameOfAndroidAppStore;

    /**
     * 下载网站应用搜索URL地址
     */
    private String searchUrlOfAndroidAppStore;

    public String getSpringBeanNameOfAndroidAppStore() {
        return springBeanNameOfAndroidAppStore;
    }

    public void setSpringBeanNameOfAndroidAppStore(String springBeanNameOfAndroidAppStore) {
        this.springBeanNameOfAndroidAppStore = springBeanNameOfAndroidAppStore;
    }

    public String getSearchUrlOfAndroidAppStore() {
        return searchUrlOfAndroidAppStore;
    }

    public void setSearchUrlOfAndroidAppStore(String searchUrlOfAndroidAppStore) {
        this.searchUrlOfAndroidAppStore = searchUrlOfAndroidAppStore;
    }
}
