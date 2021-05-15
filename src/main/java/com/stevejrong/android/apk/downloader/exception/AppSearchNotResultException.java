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
package com.stevejrong.android.apk.downloader.exception;

/**
 * Exception - APP未在应用市场搜索到异常
 *
 * @author Steve Jrong
 * create date: 2021-05-15 9:36 AM
 * @since 1.0
 */
@Deprecated
public class AppSearchNotResultException extends RuntimeException {

    /**
     * 未搜索到的APP名称
     */
    private String appName;

    /**
     * APP下载网站下载器的类名称
     */
    private String appDownloadClassName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDownloadClassName() {
        return appDownloadClassName;
    }

    public void setAppDownloadClassName(String appDownloadClassName) {
        this.appDownloadClassName = appDownloadClassName;
    }

    public AppSearchNotResultException(String appName, String appDownloadClassName) {
        super(String.format("APP %s 未在应用市场 %s 搜索到!", appName, appDownloadClassName));
    }
}