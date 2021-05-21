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

import com.stevejrong.android.apk.downloader.exception.install.DeviceConnectedFailedException;
import com.stevejrong.android.apk.downloader.exception.install.SearchAppFailedOnDeviceException;
import com.stevejrong.android.apk.downloader.exception.install.SearchNotFoundForAppOnDeviceException;
import org.apache.commons.lang3.StringUtils;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Util - Android Debug Bridge(ADB)工具类
 *
 * @author Steve Jrong
 * create date: 2021-05-20 11:55 AM
 * @since 1.0
 */
public class ADBUtil {

    /**
     * 根据安卓APP应用程序安装文件的包名，在设备上使用ADB命令进行搜索
     * <p>
     * 以检测包名对应的APP是否在设备上进行了安装
     *
     * @return 设备上已安装APP对应的包名
     */
    public static String searchAppOnDeviceByApkFullPackageName(JadbDevice device, String apkFullPackageName) throws DeviceConnectedFailedException, SearchAppFailedOnDeviceException, SearchNotFoundForAppOnDeviceException {
        BufferedReader input;
        String installedFullPackageName = null;
        try {
            input = new BufferedReader(new InputStreamReader(device.executeShell("pm", "list", "packages", apkFullPackageName), StandardCharsets.UTF_8));
            String line;
            while ((line = input.readLine()) != null) {
                installedFullPackageName = line;
            }
        } catch (IOException | JadbException ex1) {
            throw new DeviceConnectedFailedException();
        } catch (Exception ex2) {
            throw new SearchAppFailedOnDeviceException();
        }

        // 当ADB命令返回的查询结果不为空时，进行结果字符串的截取，将 package: 前缀截取掉
        if (StringUtils.isNotBlank(installedFullPackageName)) {
            installedFullPackageName = installedFullPackageName.substring(installedFullPackageName.lastIndexOf(":") + 1);
        }

        // 若截取掉后，即 package: 之后的字符串为空字符串，则表明未查询到APP，抛出异常
        if (StringUtils.isBlank(installedFullPackageName)) {
            throw new SearchNotFoundForAppOnDeviceException();
        }

        return installedFullPackageName;
    }
}