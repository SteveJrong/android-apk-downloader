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
package com.stevejrong.android.apk.downloader.config.download;

@Deprecated
public class AndroidAppStoresConfig {
    /**
     * 下载网站对应的SpringBean ID
     */
    private String springBeanNameOfAndroidAppStore;

    /**
     * 下载网站应用搜索URL地址
     */
    private String searchUrlOfAndroidAppStore;

    public String getSpringBeanNameOfAndroidAppStore() {
        return springBeanNameOfAndroidAppStore;
    }

    public void setSpringBeanNameOfAndroidAppStore(String springBeanNameOfAndroidAppStore) {
        this.springBeanNameOfAndroidAppStore = springBeanNameOfAndroidAppStore;
    }

    public String getSearchUrlOfAndroidAppStore() {
        return searchUrlOfAndroidAppStore;
    }

    public void setSearchUrlOfAndroidAppStore(String searchUrlOfAndroidAppStore) {
        this.searchUrlOfAndroidAppStore = searchUrlOfAndroidAppStore;
    }
}
