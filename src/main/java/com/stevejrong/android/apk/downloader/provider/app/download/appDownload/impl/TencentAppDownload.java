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
package com.stevejrong.android.apk.downloader.provider.app.download.appDownload.impl;

import com.stevejrong.android.apk.downloader.common.Constants;
import com.stevejrong.android.apk.downloader.provider.app.download.appDownload.AbstractAppDownload;
import com.stevejrong.android.apk.downloader.provider.app.download.appDownload.IAppDownload;
import com.stevejrong.android.apk.downloader.util.QueueUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.List;

public class TencentAppDownload extends AbstractAppDownload implements IAppDownload {
    public TencentAppDownload(String searchUrl, List<String> htmlAnalysisExpressions) {
        super(searchUrl, htmlAnalysisExpressions);
    }

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
            try {
                webDriver.get(super.searchUrl.replace(Constants.APP_NAME_SYMBOL.val(), appQueue.element()));
                Thread.sleep(800);

                for (int j = 0; j < super.htmlAnalysisExpressions.size(); j++) {
                    webDriver.findElement(By.xpath(htmlAnalysisExpressions.get(j).replace(Constants.APP_NAME_SYMBOL.val(), appQueue.element()))).click();

                    if (j == 0) {
                        String firstHandle = webDriver.getWindowHandle();
                        for (String winHandle : webDriver.getWindowHandles()) {
                            if (winHandle.equals(firstHandle)) {
                                continue;
                            }

                            webDriver.switchTo().window(winHandle);
                            break;
                        }
                    }

                    if (j == 1) {
                        QueueUtil.APP_QUEUE.poll();

                        try {
                            String winHandleBefore = webDriver.getWindowHandle(); //关闭当前窗口前，获取当前窗口句柄

                            webDriver.close();//关闭窗口

                            // 迭代winHandles里的句柄
                            for (String win : webDriver.getWindowHandles()) { // 有下一个窗口,如果有就切换到下一个窗口
                                if (!win.equals(winHandleBefore)) { // 如果此窗口不是关闭前的窗口
                                    webDriver.switchTo().window(win);//切换到新窗口
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    Thread.sleep(800);
                }
            } catch (NoSuchElementException ex) {
                System.out.println(String.format("%s 未搜索到！", appQueue.element()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
