package com.stevejrong.android.apk.downloader.boot;

import com.stevejrong.android.apk.downloader.provider.app.download.appDownload.IAppDownload;
import com.stevejrong.android.apk.downloader.util.QueueUtil;
import org.apache.commons.collections4.CollectionUtils;

public class AndroidApkDownloaderApplication {
    public static void main(String[] args) {
        for (IAppDownload appDownloadSite : QueueUtil.APP_DOWNLOAD_SITES_QUEUE) {
            if (CollectionUtils.isNotEmpty(QueueUtil.APP_QUEUE)) {
                appDownloadSite.automaticDownload();
            }
        }
    }
}
