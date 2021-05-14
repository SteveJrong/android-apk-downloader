package com.stevejrong.android.apk.downloader.util;

import com.google.common.collect.Lists;
import com.stevejrong.android.apk.downloader.common.Constants;
import com.stevejrong.android.apk.downloader.config.download.DownloadConfig;
import com.stevejrong.android.apk.downloader.provider.app.download.appDownload.IAppDownload;

import java.util.LinkedList;
import java.util.List;

public final class QueueUtil {
    public static final LinkedList<IAppDownload> APP_DOWNLOAD_SITES_QUEUE;
    public static final LinkedList<String> APP_QUEUE;

    static {
        DOWNLOAD_CONFIG = null;

        APP_DOWNLOAD_SITES_QUEUE = Lists.newLinkedList();
        APP_QUEUE = Lists.newLinkedList();

        initAppDownloadSitesQueue();
        initAppQueue();
    }

    private static DownloadConfig DOWNLOAD_CONFIG;

    private static void initAppDownloadSitesQueue() {
        DOWNLOAD_CONFIG = SpringBeanUtil.getBean("downloadConfig");

        List<IAppDownload> appDownloadSites = DOWNLOAD_CONFIG.getAppDownloadSitesConfig();
        APP_DOWNLOAD_SITES_QUEUE.addAll(appDownloadSites);
    }

    private static void initAppQueue() {
        List<String> appList = XmlUtil.readToStringList(FileUtil.getProjectAbsolutePath() + Constants.APP_LIST_RELATIVE_PATH.val(),
                DOWNLOAD_CONFIG.getCurrentAppListNodeName());
        APP_QUEUE.addAll(appList);
    }
}
