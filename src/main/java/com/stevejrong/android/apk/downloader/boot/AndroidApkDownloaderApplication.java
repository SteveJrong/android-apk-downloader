/**
 * Copyright ${YEAR} Steve Jrong
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

import com.stevejrong.android.apk.downloader.provider.app.download.appDownload.IAppDownload;
import com.stevejrong.android.apk.downloader.provider.app.install.IAppInstall;
import com.stevejrong.android.apk.downloader.util.QueueUtil;
import com.stevejrong.android.apk.downloader.util.SpringBeanUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Scanner;

/**
 *
 */
public class AndroidApkDownloaderApplication {
    private static final Logger LOGGER = Logger.getLogger(AndroidApkDownloaderApplication.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n欢迎使用Android APK Downloader软件！\n输入以下对应数字序号来执行任务：\n1.\t自动化下载APP\n2.\t自动化安装APP\n0.\t退出程序\n→\n");
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
                    IAppInstall appInstall = SpringBeanUtil.getBean("appInstall");
                    appInstall.automaticInstall();
                    break;

                case "0":
                    System.err.println("感谢您的使用！\n\n");
                    System.exit(0);
                    break;

                default:
                    System.err.println("您输入的数字有误，请重新输入！\n\n");
                    break;
            }
        }


    }
}
