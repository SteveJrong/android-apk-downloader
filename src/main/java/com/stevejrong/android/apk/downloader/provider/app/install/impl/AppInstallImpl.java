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
package com.stevejrong.android.apk.downloader.provider.app.install.impl;

import com.stevejrong.android.apk.downloader.common.Constants;
import com.stevejrong.android.apk.downloader.config.download.DownloadConfig;
import com.stevejrong.android.apk.downloader.provider.app.install.AbstractAppInstall;
import com.stevejrong.android.apk.downloader.provider.app.install.IAppInstall;
import com.stevejrong.android.apk.downloader.util.SpringBeanUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Service Implements - APP安装业务实现类
 *
 * @author Steve Jrong
 * create date: 2021-05-15 6:16 PM
 * @since 1.0
 */
public class AppInstallImpl extends AbstractAppInstall implements IAppInstall {
    private static final Logger LOGGER = Logger.getLogger(AppInstallImpl.class);

    protected static DownloadConfig DOWNLOAD_CONFIG;

    @Override
    public void automaticInstall() {
        DOWNLOAD_CONFIG = SpringBeanUtil.getBean(Constants.DOWNLOAD_CONFIG_SPRING_BEAN_ID.val());

        try {
            Files.newDirectoryStream(Paths.get(DOWNLOAD_CONFIG.getBrowserDownloadPath()),
                    path -> path.toString().endsWith(Constants.APK_SUFFIX.val()))
                    .forEach(file -> {
                        String apkFilePath = file.toAbsolutePath().toString();

                        super.checkConnectDevices();
                        LOGGER.info(String.format("检测连接状态成功！准备安装APK文件：<%s>", apkFilePath));

                        if (super.checkAlreadyInstalledForApp(apkFilePath)) {
                            LOGGER.info(String.format("APK文件：<%s> 已安装，跳过安装", apkFilePath));
                        } else {
                            try {
                                LOGGER.info(String.format("开始安装 >>>>>>>> APK文件：<%s>", apkFilePath));

                                super.installAppWithAdb(apkFilePath);

                                LOGGER.info(String.format("安装成功！ >>>>>>>> APK文件：<%s>", apkFilePath));
                            } catch (Exception ex1) {
                                LOGGER.error(String.format("自动安装APP期间发生异常。异常信息：%s", ex1.getMessage()));
                            }
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}