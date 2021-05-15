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
package com.stevejrong.android.apk.downloader.provider.app.download.appDownload;

import com.stevejrong.android.apk.downloader.common.Constants;
import com.stevejrong.android.apk.downloader.config.download.DownloadConfig;
import com.stevejrong.android.apk.downloader.provider.app.download.browser.IBrowserPreferences;
import com.stevejrong.android.apk.downloader.util.SpringBeanUtil;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * AbstractClass - APP下载抽象类
 *
 * @author Steve Jrong
 * create date: 2021-05-15 8:23 PM
 * @since 1.0
 */
public abstract class AbstractAppDownload {
    protected static DownloadConfig DOWNLOAD_CONFIG;

    /**
     * 应用商店APP搜索页的URL地址
     */
    protected String searchUrl;

    /**
     * 从应用商店下载APP时所需的XPath解析表达式
     *
     * 此表达式会在Spring的application.xml配置文件中被注入。其在配置文件中的先后顺序就是依次解析的顺序
     * 即：
     * 当此集合中有且仅有一个数据时，表示此应用商店在下载APP时无需新开选项卡，在当前页面就能一次性完成下载；
     * 当此集合中有一个以上数据时（目前仅支持新开一个选项卡，所以此集合中最多能有2个元素），表示此应用商店在下载APP时需要新开选项卡才能完成下载。
     */
    protected List<String> htmlAnalysisExpressions;

    protected AbstractAppDownload(String searchUrl, List<String> htmlAnalysisExpressions) {
        this.searchUrl = searchUrl;
        this.htmlAnalysisExpressions = htmlAnalysisExpressions;
    }

    /**
     * 根据默认配置来初始化浏览器参数
     *
     * @return Selenium的WebDriver对象
     */
    protected WebDriver initBrowserPrefsByDefaultConfig() {
        DOWNLOAD_CONFIG = SpringBeanUtil.getBean(Constants.DOWNLOAD_CONFIG_SPRING_BEAN_ID.val());

        IBrowserPreferences browserPreferences = SpringBeanUtil.getBean(DOWNLOAD_CONFIG.getBrowserName());
        return browserPreferences.initBrowserPrefs();
    }
}
