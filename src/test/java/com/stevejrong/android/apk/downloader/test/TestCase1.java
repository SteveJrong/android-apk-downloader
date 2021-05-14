package com.stevejrong.android.apk.downloader.test;

import com.stevejrong.android.apk.downloader.provider.app.download.appDownload.IAppDownload;
import com.stevejrong.android.apk.downloader.util.FileUtil;
import com.stevejrong.android.apk.downloader.util.SpringBeanUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;
import se.vidstige.jadb.managers.PackageManager;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-bean.xml")
public class TestCase1 {

    @Resource
    private ApplicationContext CONTEXT;

    @Test
    public void adbTest() throws IOException, JadbException {
        JadbConnection conn = new JadbConnection();
        List<JadbDevice> devices = conn.getDevices();

        if (CollectionUtils.isNotEmpty(devices)) {
            new PackageManager(devices.get(0)).install(new File("/Users/stevejrong/Desktop/apks/com.tencent.qqpimsecure_8.12.0_1423.apk"));
        }
    }

    @Test
    public void xmlReadTest() throws DocumentException {
        SAXReader reader = new SAXReader();
        File file = new File(FileUtil.getProjectAbsolutePath() + "/src/main/resources/app-list.xml");
        Document document = reader.read(file);
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();
        for (Element item : childElements) {
            if ("multimedia-app-list".equals(item.getName())) {
                Iterator elementIterator = item.elementIterator();
                while (elementIterator.hasNext()) {
                    DefaultElement value = (DefaultElement) elementIterator.next();
                    System.out.println(value.getText());
                }
            }
        }
    }

    @Test
    public void tencentAppDownloadTest() {
        IAppDownload tencentAppDownload = SpringBeanUtil.getBean("tencentAppDownload");
        tencentAppDownload.initBrowserPrefs();
        tencentAppDownload.automaticDownload();
    }
}
