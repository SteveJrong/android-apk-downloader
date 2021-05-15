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
package com.stevejrong.android.apk.downloader.provider.app.install;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;
import se.vidstige.jadb.managers.PackageManager;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Steve Jrong
 * create date: 2021-05-15 6:18 PM
 * @since 1.0
 */
public abstract class AbstractAppInstall {
    private static final Logger LOGGER = Logger.getLogger(AbstractAppInstall.class);

    protected static JadbConnection connection;

    /**
     * 获取ADB连接对象
     *
     * @return
     */
    private JadbConnection getAdbConnection() {
        connection = new JadbConnection();
        return connection;
    }

    /**
     * 获取已连接的设备
     *
     * @return
     * @throws IOException
     * @throws JadbException
     */
    private List<JadbDevice> getDevices() throws IOException, JadbException {
        return getAdbConnection().getDevices();
    }

    /**
     * 检查是否存在连接好的设备
     *
     * @return true-已连接好至少一台设备；false-未连接任何设备或连接失败
     */
    protected boolean checkConnectDevices() {
        List<JadbDevice> devices = null;
        try {
            devices = getDevices();
        } catch (IOException | JadbException e) {
            e.printStackTrace();
        }

        return CollectionUtils.isNotEmpty(devices);
    }

    /**
     * 使用ADB命令安装APP
     *
     * @param apkFilePath APK应用绝对路径
     */
    protected void installAppWithAdb(String apkFilePath) {
        try {
            LOGGER.info(String.format("[%s] 开始自动化安装 <%s> ！", this.getClass().getCanonicalName(), apkFilePath));

            new PackageManager(getDevices().get(0)).install(new File(apkFilePath));

            LOGGER.info(String.format("[%s] 自动化安装 <%s> 成功！", this.getClass().getCanonicalName(), apkFilePath));
        } catch (IOException | JadbException ex) {
            LOGGER.error(String.format("[%s] 自动化安装 <%s> 失败！文件位置：<%s>。异常信息：<%s>。", this.getClass().getCanonicalName(), apkFilePath, ex.getMessage()));
        }
    }
}