/**
 * Copyright 2021 Steve Jrong
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stevejrong.android.apk.downloader.util;

import com.google.common.collect.Lists;
import com.stevejrong.android.apk.downloader.common.Constants;
import com.stevejrong.android.apk.downloader.config.download.DownloadConfig;
import com.stevejrong.android.apk.downloader.provider.app.download.appDownload.IAppDownload;

import java.io.File;
import java.util.List;
import java.util.Queue;

/**
 * Util - 队列工具类
 *
 * @author Steve Jrong
 * create date: 2021-05-15 8:23 PM
 * @since 1.0
 */
public final class QueueUtil {
    public static final Queue<IAppDownload> APP_DOWNLOAD_SITES_QUEUE;
    public static final Queue<String> APP_QUEUE, NOT_FOUND_APP_QUEUE;

    static {
        DOWNLOAD_CONFIG = null;

        APP_DOWNLOAD_SITES_QUEUE = Lists.newLinkedList();
        APP_QUEUE = Lists.newLinkedList();
        NOT_FOUND_APP_QUEUE = Lists.newLinkedList();

        initAppDownloadSitesQueue();
        initAppQueue();
    }

    private static DownloadConfig DOWNLOAD_CONFIG;

    private static void initAppDownloadSitesQueue() {
        DOWNLOAD_CONFIG = SpringBeanUtil.getBean(Constants.DOWNLOAD_CONFIG_SPRING_BEAN_ID.val());

        List<IAppDownload> appDownloadSites = DOWNLOAD_CONFIG.getAppDownloadSitesConfig();
        APP_DOWNLOAD_SITES_QUEUE.addAll(appDownloadSites);
    }

    private static void initAppQueue() {
        List<String> appList = XmlUtil.readToStringList(FileUtil.getResourcesDirectoryAbsolutePath() + File.separator
                        + Constants.APP_LIST_XML_FILE_NAME.val(),
                DOWNLOAD_CONFIG.getCurrentAppListNodeName());
        APP_QUEUE.addAll(appList);
    }
}
