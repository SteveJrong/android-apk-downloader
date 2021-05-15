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
package com.stevejrong.android.apk.downloader.provider.app.download.appDownload;

import org.openqa.selenium.WebDriver;

public interface IAppDownload {

    /**
     * 浏览器参数及设置初始化
     *
     * 此方法实现时不需调用
     *
     * @return
     */
    WebDriver initBrowserPrefs();

    /**
     * 自动化下载
     */
    void automaticDownload();
}
