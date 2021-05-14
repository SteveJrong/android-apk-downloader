package com.stevejrong.android.apk.downloader.provider.app.download.appDownload.impl;

import com.stevejrong.android.apk.downloader.common.Constants;
import com.stevejrong.android.apk.downloader.provider.app.download.appDownload.AbstractAppDownload;
import com.stevejrong.android.apk.downloader.provider.app.download.appDownload.IAppDownload;
import com.stevejrong.android.apk.downloader.util.QueueUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

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
        // 获取APP队列中的APP列表
        List<String> appList = QueueUtil.APP_QUEUE;

        for (String app : appList) {
            try {
                webDriver.get(super.searchUrl.replace(Constants.APP_NAME_SYMBOL.val(), app));
                Thread.sleep(800);

                for (int i = 0; i < super.htmlAnalysisExpressions.size(); i++) {
                    webDriver.findElement(By.xpath(htmlAnalysisExpressions.get(i).replace(Constants.APP_NAME_SYMBOL.val(), app))).click();

                    if (i == 0) {
                        String firstHandle = webDriver.getWindowHandle();
                        for (String winHandle : webDriver.getWindowHandles()) {
                            if (winHandle.equals(firstHandle)) {
                                continue;
                            }

                            webDriver.switchTo().window(winHandle);
                            break;
                        }
                    }

                    if (i == 1) {
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
                System.out.println(String.format("%s 未搜索到！", app));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
