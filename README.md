# android-apk-downloader

#### 介绍
常用安卓APP一键下载、一键安装的小工具  
免去手机换机、刷机重置后，用户一个个去应用商店中点击安装的麻烦和辛苦  

#### 项目结构
控制台应用程序  

#### 说明
* 安卓APP一键下载功能，基于Selenium自动化执行框架实现  
* 安卓APP一键安装功能，基于Java封装的Android Debug Bridge（ADB）库实现

###### 功能说明
* 支持安卓APP一键下载和一键安装功能
* 暂不支持双击运行此应用程序

#### 如何使用
* 下载Java IDE（```Eclipse for Java```或```Intellij IDEA```）
* 下载```Gradle```的```zip```压缩包
* 使用```git clone```命令将项目克隆到本地计算机的任意位置
* 打开Java IDE并将项目导入
* 确保在本地计算机中安装了较新版本的```Google Chrome网页浏览器```
* 下载适用于本地计算机中对应```Google Chrome网页浏览器```的Web Driver驱动程序文件
* 下载适用于当前操作系统的```ADB组件包```
* 在本地计算机上配置ADB环境变量，并能正常执行```adb devices```命令
* 转到项目的```src/main/resources/define-config.yml```文件中，根据文件中的注释视实际情况修改相关参数
* 转到项目的```com.stevejrong.android.apk.downloader.boot.AndroidApkDownloaderApplication```类中，在此文件上直接以右键运行，根据控制台内的相应文字提示执行所需功能即可
* 期间的操作日志文件，请查看项目根目录下```logs```中的```run.log```文件

###### 先决条件
已在```如何使用```中进行了描述
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
#### 版本信息
2.0.0

#### 开发计划
* 应用程序打包功能

#### 更新日志
2021/05/12：
* 第一版提交。  

2021/05/15：
* 项目结构大改，融合Spring框架。
* 为了解决单一应用商店APP部分缺失的问题，现已加入多应用商店联合下载APP的支持。
* 初步添加Android Debug Bridge（ADB）命令支持。

2021/05/15：
* 支持一键自动化下载和批量安装APP。
* 改为控制台程序逻辑，便于任务执行。

2021/05/16：
* 代码优化，注释补充。
* 发布release版本：2.0.0。
* 更新master版本为release版本：2.0.0。
* 增加下一个develop分支：2.0.1。

2021/05/19：
* 修复下载APP时因APP未搜索到时，当前APP在APP队列中阻塞的Bug。
* 增加一些常用APP。
* 发布release版本：2.0.1。
* 更新master版本为release版本：2.0.1。