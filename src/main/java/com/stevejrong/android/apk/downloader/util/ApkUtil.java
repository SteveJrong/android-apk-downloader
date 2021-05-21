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

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Util - 安卓APP工具类
 *
 * @author Steve Jrong
 * create date: 2021-05-20 11:20 AM
 * @since 1.0
 */
public final class ApkUtil {
    private static final Logger LOGGER = Logger.getLogger(ApkUtil.class);

    /**
     * 获取安卓APP应用安装文件的完整包名
     *
     * @param apkFilePath 安卓APP应用安装文件的绝对路径
     * @return 安卓APP应用安装文件的完整包名
     */
    public static String getApkFullPackageName(String apkFilePath) {
        ApkFile apkFile = null;
        String apkFullPackageName = null;

        try {
            apkFile = new ApkFile(apkFilePath);
            ApkMeta apkMeta = apkFile.getApkMeta();
            apkFullPackageName = apkMeta.getPackageName();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (apkFile != null) {
                    apkFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return apkFullPackageName;
    }
}