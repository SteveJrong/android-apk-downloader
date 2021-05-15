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
package com.stevejrong.android.apk.downloader.provider.app.download.appDownload.impl;

import com.stevejrong.android.apk.downloader.common.Constants;
import com.stevejrong.android.apk.downloader.provider.app.download.appDownload.AbstractAppDownload;
import com.stevejrong.android.apk.downloader.provider.app.download.appDownload.IAppDownload;
import com.stevejrong.android.apk.downloader.util.QueueUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.List;

public class AnZhiAppDownload extends AbstractAppDownload implements IAppDownload {
    private static final Logger LOGGER = Logger.getLogger(AnZhiAppDownload.class);

    public AnZhiAppDownload(String searchUrl, List<String> htmlAnalysisExpressions) {
        super(searchUrl, htmlAnalysisExpressions);
    }

    /**
     * 浏览器参数及设置初始化
     *
     * @return
     */
    @Override
    public WebDriver initBrowserPrefs() {
        return super.initBrowserPrefsByDefaultConfig();
    }

    @Override
    public void automaticDownload() {
        // 浏览器参数初始化
        WebDriver webDriver = this.initBrowserPrefs();
        // APP队列
        LinkedList<String> appQueue = QueueUtil.APP_QUEUE;

        for (int i = 0; i < appQueue.size(); i++) {
            LOGGER.info(String.format("[%s] 开始自动化下载 <%s> ！", this.getClass().getCanonicalName(), appQueue.element()));

            try {
                webDriver.get(super.searchUrl.replace(Constants.APP_NAME_SYMBOL.val(), appQueue.element()));
                Thread.sleep(800);

                for (String htmlAnalysisExp : super.htmlAnalysisExpressions) {
                    webDriver.findElement(By.xpath(htmlAnalysisExp.replace(Constants.APP_NAME_SYMBOL.val(), appQueue.element()))).click();
                }
                LOGGER.info(String.format("[%s] 自动化下载 <%s> 成功！", this.getClass().getCanonicalName(), appQueue.element()));

                QueueUtil.APP_QUEUE.poll();
            } catch (NoSuchElementException ex) {
                LOGGER.warn(String.format("[%s] APP <%s> 未在当前应用市场搜索到！", this.getClass().getCanonicalName(), appQueue.element()));
            } catch (Exception ex1) {
                LOGGER.error(String.format("[%s] 发生其他异常。信息：%s", this.getClass().getCanonicalName(), ex1.getMessage()));
            }
        }
    }
}
