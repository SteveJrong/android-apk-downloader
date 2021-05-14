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

public class AnZhiAppDownload extends AbstractAppDownload implements IAppDownload {
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
        // 获取APP队列中的APP列表
        LinkedList<String> appList = QueueUtil.APP_QUEUE;

        for (int i = 0; i < appList.size(); i++) {
            try {
                webDriver.get(super.searchUrl.replace(Constants.APP_NAME_SYMBOL.val(), appList.element()));
                Thread.sleep(800);

                for (String htmlAnalysisExp : super.htmlAnalysisExpressions) {
                    webDriver.findElement(By.xpath(htmlAnalysisExp.replace(Constants.APP_NAME_SYMBOL.val(), appList.element()))).click();
                }

                QueueUtil.APP_QUEUE.poll();
            } catch (NoSuchElementException ex) {
                System.out.println(String.format("%s 未搜索到！", appList.element()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
