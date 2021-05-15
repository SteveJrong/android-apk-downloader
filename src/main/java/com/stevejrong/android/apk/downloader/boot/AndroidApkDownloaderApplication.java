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
package com.stevejrong.android.apk.downloader.boot;

import com.stevejrong.android.apk.downloader.common.Constants;
import com.stevejrong.android.apk.downloader.config.download.BaseConfig;
import com.stevejrong.android.apk.downloader.provider.app.download.appDownload.IAppDownload;
import com.stevejrong.android.apk.downloader.provider.app.install.IAppInstall;
import com.stevejrong.android.apk.downloader.util.FileUtil;
import com.stevejrong.android.apk.downloader.util.QueueUtil;
import com.stevejrong.android.apk.downloader.util.SpringBeanUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.util.Scanner;

/**
 * Boot - 启动入口文件
 */
public class AndroidApkDownloaderApplication {
    private static final Logger LOGGER = Logger.getLogger(AndroidApkDownloaderApplication.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure(FileUtil.getResourcesDirectoryAbsolutePath() + File.separator
                + Constants.LOG4J_CONFIG_FILE_NAME.val());
        BaseConfig baseConfig = SpringBeanUtil.getBean(Constants.BASE_CONFIG_SPRING_BEAN_ID.val());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(baseConfig.getWelcomeMessage());
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    for (IAppDownload appDownloadSite : QueueUtil.APP_DOWNLOAD_SITES_QUEUE) {
                        LOGGER.info(String.format("[%s] 开始使用 <%s> 下载器下载APP。", AndroidApkDownloaderApplication.class.getName(), appDownloadSite.getClass().getCanonicalName()));

                        if (CollectionUtils.isNotEmpty(QueueUtil.APP_QUEUE)) {
                            appDownloadSite.automaticDownload();
                        }
                    }
                    break;

                case "2":
                    IAppInstall appInstall = SpringBeanUtil.getBean(Constants.APP_INSTALL_SPRING_BEAN_ID.val());
                    appInstall.automaticInstall();
                    break;

                case "0":
                    System.err.println("感谢您的使用！\n\n");
                    System.exit(0);

                default:
                    System.err.println("您输入的数字有误，请重新输入！\n");
                    break;
            }
        }
    }
}
