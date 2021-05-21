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
package com.stevejrong.android.apk.downloader.exception.install;

/**
 * @author Steve Jrong
 * create date: 2021-05-20 12:47 PM
 * @since 1.0
 */
public class InstallAppFailedException extends RuntimeException {
    private String apkFilePath;

    public String getApkFilePath() {
        return apkFilePath;
    }

    public void setApkFilePath(String apkFilePath) {
        this.apkFilePath = apkFilePath;
    }

    public InstallAppFailedException(String apkFilePath) {
        super(String.format("自动化安装 <%s> 失败！", apkFilePath));
        this.apkFilePath = apkFilePath;
    }
}