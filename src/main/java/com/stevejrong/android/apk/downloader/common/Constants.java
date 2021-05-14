package com.stevejrong.android.apk.downloader.common;

import java.io.File;

public enum Constants {
    CHROME_WEB_DRIVER_PACKAGE_NAME("webdriver.chrome.driver"),
    CHROME_DOWNLOAD_DIRECTORY_PREFERENCE_COMMAND("download.default_directory"),
    CHROME_PREFERENCE_OPTION("prefs"),

    APP_NAME_SYMBOL("#APP_NAME"),

    APP_LIST_RELATIVE_PATH(File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "app-list.xml"),
    SPRING_XML_FILE_NAME("application-bean.xml");

    private String val;

    public String val() {
        return val;
    }

    Constants(String val) {
        this.val = val;
    }
}
