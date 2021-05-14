package com.stevejrong.android.apk.downloader.util;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Util - 文件操作工具类
 */
public final class FileUtil {

    /**
     * 字节数组存为图片
     *
     * @param byteArray 字节数组
     * @param path      图片的存储路径
     */
    public static void byteArraySaveToImg(byte[] byteArray, String path) {
        try {
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(byteArray, 0, byteArray.length);
            imageOutput.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 图片转字节数组
     *
     * @param path 图片路径
     * @return
     * @throws Exception
     */
    public static byte[] imgFileToByteArray(String path) {
        File file = new File(path);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] imgByteArray = new byte[]{};

        try {
            BufferedImage bi = ImageIO.read(file);
            ImageIO.write(bi, "jpg", byteArrayOutputStream);
            imgByteArray = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imgByteArray;
    }

    /**
    /**
     * 字节数组转换为File对象
     * 此方法会生成临时文件
     *
     * @param byteArray 字节数组
     * @param filePath  临时文件创建路径
     * @return File对象
     */
    public static File byteArrayToFile(byte[] byteArray, String filePath) {
        File file = new File(filePath);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            OutputStream output = new FileOutputStream(file);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
            bufferedOutput.write(byteArray);
            bufferedOutput.flush();
            bufferedOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    /**
     * 获取项目的根绝对路径
     *
     * @return 项目的根绝对路径
     */
    public static String getProjectAbsolutePath() {
        String projectAbsolutePath = null;

        File file = new File("");
        try {
            projectAbsolutePath = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return projectAbsolutePath;
    }

    /**
     * 字符串内容写入文本文件
     *
     * @param content    要写入的字符串内容
     * @param fileSuffix 保存的文件后缀名
     * @param fileName   保存的文件名称
     * @param filePath   保存的文件路径
     */
    public static void writeStringContentToFile(String content, String fileSuffix, String fileName, String filePath) {
        File file = new File(filePath.concat(File.separator).concat(fileName).concat(fileSuffix));

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Files.write(Paths.get(filePath.concat(File.separator).concat(fileName).concat(fileSuffix)), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
