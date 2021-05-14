package com.stevejrong.android.apk.downloader.logs.provider.impl;


import com.stevejrong.android.apk.downloader.logs.provider.ILogProvider;

/**
 * APP下载的日志记录业务类
 */
public class AppDownloadLogProviderImpl implements ILogProvider {

    @Override
    public void afterReturnDoAction(Object... params) {
        System.out.println(String.format("AOP日志：%o", params));
    }

    @Override
    public void afterExceptionDoAction(Object... params) {
        System.out.println(String.format("AOP日志：%o", params));
    }
}
