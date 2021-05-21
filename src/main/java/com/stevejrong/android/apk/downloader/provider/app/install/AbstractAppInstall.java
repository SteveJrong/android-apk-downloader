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

import com.stevejrong.android.apk.downloader.exception.install.DeviceConnectedFailedException;
import com.stevejrong.android.apk.downloader.exception.install.InstallAppFailedException;
import com.stevejrong.android.apk.downloader.exception.install.SearchAppFailedOnDeviceException;
import com.stevejrong.android.apk.downloader.exception.install.SearchNotFoundForAppOnDeviceException;
import com.stevejrong.android.apk.downloader.util.ADBUtil;
import com.stevejrong.android.apk.downloader.util.ApkUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;
import se.vidstige.jadb.managers.PackageManager;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * AbstractClass - APP安装抽象类
 *
 * @author Steve Jrong
 * create date: 2021-05-15 8:23 PM
 * @since 1.0
 */
public abstract class AbstractAppInstall {
    private static final Logger LOGGER = Logger.getLogger(AbstractAppInstall.class);

    private static JadbConnection connection;
    private static PackageManager packageManager;

    /**
     * 获取ADB连接对象
     *
     * @return JadbConnection连接对象
     */
    private JadbConnection getAdbConnection() {
        connection = new JadbConnection();
        return connection;
    }

    /**
     * 获取已连接的设备
     *
     * @return JadbDevice集合
     * @throws IOException
     * @throws JadbException
     */
    private List<JadbDevice> getDevices() throws IOException, JadbException {
        return getAdbConnection().getDevices();
    }

    /**
     * 获取PackageManager对象
     *
     * @return PackageManager对象
     * @throws IOException
     * @throws JadbException
     */
    private PackageManager getPackageManager() throws IOException, JadbException {
        if (packageManager == null) {
            packageManager = new PackageManager(getDevices().get(0));
        }

        return packageManager;
    }

    /**
     * 检查是否存在连接好的设备
     *
     * @return true-已连接好至少一台设备；false-未连接任何设备或连接失败
     */
    protected boolean checkConnectDevices() throws DeviceConnectedFailedException {
        List<JadbDevice> devices;
        try {
            devices = getDevices();
        } catch (IOException | JadbException e) {
            throw new DeviceConnectedFailedException();
        }

        return CollectionUtils.isNotEmpty(devices);
    }

    /**
     * 使用ADB命令安装APP
     *
     * @param apkFilePath 安卓APP应用程序文件的绝对路径
     */
    protected void installAppWithAdb(String apkFilePath) throws DeviceConnectedFailedException, InstallAppFailedException {

        try {
            File apkFile = new File(apkFilePath);
            getPackageManager().install(apkFile);
        } catch (IOException | JadbException ex1) {
            throw new DeviceConnectedFailedException();
        } catch (Exception ex2) {
            throw new InstallAppFailedException(apkFilePath);
        }
    }

    /**
     * 检查手机中是否已安装了将要安装的安卓APP应用程序
     *
     * @param apkFilePath
     * @return true-已安装目标APP应用程序；false-未安装目标APP应用程序
     */
    protected boolean checkAlreadyInstalledForApp(String apkFilePath) throws SearchAppFailedOnDeviceException, DeviceConnectedFailedException {
        // 要按装的APK文件的完整包名
        String apkFullPackageName = ApkUtil.getApkFullPackageName(apkFilePath);

        // 向设备发送ADB命令检测是否已安装
        String installedAppFullPackageName;
        try {
            installedAppFullPackageName = ADBUtil.searchAppOnDeviceByApkFullPackageName(getDevices().get(0), apkFullPackageName);
        } catch (SearchAppFailedOnDeviceException ex1) {
            throw new SearchAppFailedOnDeviceException();
        } catch (SearchNotFoundForAppOnDeviceException ex2) {
            return false;
        } catch (IOException | JadbException ex3) {
            throw new DeviceConnectedFailedException();
        }

        // 表示APK已安装
        return StringUtils.isNotEmpty(installedAppFullPackageName) & StringUtils.isNotBlank(installedAppFullPackageName);
    }
}