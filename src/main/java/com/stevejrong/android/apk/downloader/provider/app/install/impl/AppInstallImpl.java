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
 * Service Implements - APP安装
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

        if (super.checkConnectDevices()) {
            try {
                Files.newDirectoryStream(Paths.get(DOWNLOAD_CONFIG.getBrowserDownloadPath()),
                        path -> path.toString().endsWith(Constants.APK_SUFFIX.val()))
                        .forEach(file -> super.installAppWithAdb(file.toAbsolutePath().toString()));
            } catch (IOException ex) {
                LOGGER.error(String.format("[%s] 发生其他异常。信息：%s", this.getClass().getCanonicalName(), ex.getMessage()));
            }
        } else {
            LOGGER.error(String.format("[%s] ADB连接异常，未检测到连接的设备。请检查设备是否开启USB调试模式、电脑端口是否开放、杀毒软件是否拦截、其他进程是否占用等。", this.getClass().getCanonicalName()));
        }
    }
}