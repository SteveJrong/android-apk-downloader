package com.stevejrong.android.apk.downloader.util;

import com.google.common.collect.Lists;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public final class XmlUtil {

    /**
     * 将指定节点下的内容读取为String集合
     *
     * @param xmlFilePath XML文件路径
     * @param nodeName 节点名称
     * @return
     */
    public static List<String> readToStringList(String xmlFilePath, String nodeName){
        List<String> stringList = Lists.newArrayList();

        try {
            SAXReader reader = new SAXReader();
            File file = new File(xmlFilePath);
            Document document = reader.read(file);
            Element root = document.getRootElement();
            List<Element> childElements = root.elements();
            for (Element item : childElements) {
                if (nodeName.equals(item.getName())) {
                    Iterator<Element> elementIterator = item.elementIterator();
                    while (elementIterator.hasNext()) {
                        DefaultElement element = (DefaultElement) elementIterator.next();
                        stringList.add(element.getTextTrim());
                    }
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return stringList;
    }
}
