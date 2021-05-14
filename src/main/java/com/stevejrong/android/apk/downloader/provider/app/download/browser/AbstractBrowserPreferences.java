package com.stevejrong.android.apk.downloader.provider.app.download.browser;

import com.stevejrong.android.apk.downloader.common.Constants;
import com.stevejrong.android.apk.downloader.config.download.DownloadConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AbstractBrowserPreferences {
    protected static final ApplicationContext CONTEXT;
    protected static DownloadConfig DOWNLOAD_CONFIG;

    static {
        CONTEXT = new ClassPathXmlApplicationContext(Constants.SPRING_XML_FILE_NAME.val());
        DOWNLOAD_CONFIG = (DownloadConfig) CONTEXT.getBean("downloadConfig");
    }
}
