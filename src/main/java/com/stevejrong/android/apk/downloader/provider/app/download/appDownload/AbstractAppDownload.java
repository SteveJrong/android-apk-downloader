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

import com.stevejrong.android.apk.downloader.config.download.DownloadConfig;
import com.stevejrong.android.apk.downloader.provider.app.download.browser.IBrowserPreferences;
import com.stevejrong.android.apk.downloader.util.SpringBeanUtil;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class AbstractAppDownload {
    protected static DownloadConfig DOWNLOAD_CONFIG;

    protected String searchUrl;
    protected List<String> htmlAnalysisExpressions;

    protected AbstractAppDownload(String searchUrl, List<String> htmlAnalysisExpressions) {
        this.searchUrl = searchUrl;
        this.htmlAnalysisExpressions = htmlAnalysisExpressions;
    }

    /**
     * 根据默认配置来初始化浏览器参数
     *
     * @return
     */
    protected WebDriver initBrowserPrefsByDefaultConfig() {
        DOWNLOAD_CONFIG = SpringBeanUtil.getBean("downloadConfig");

        IBrowserPreferences browserPreferences = SpringBeanUtil.getBean(DOWNLOAD_CONFIG.getBrowserName());
        return browserPreferences.initBrowserPrefs();
    }
}
