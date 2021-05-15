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
package com.stevejrong.android.apk.downloader.common;

import java.io.File;

public enum Constants {
    CHROME_WEB_DRIVER_PACKAGE_NAME("webdriver.chrome.driver"),
    CHROME_DOWNLOAD_DIRECTORY_PREFERENCE_COMMAND("download.default_directory"),
    CHROME_PREFERENCE_OPTION("prefs"),

    APP_NAME_SYMBOL("#APP_NAME"),

    APP_LIST_RELATIVE_PATH(File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "app-list.xml"),
    SPRING_XML_FILE_NAME("application-bean.xml");

    private String val;

    public String val() {
        return val;
    }

    Constants(String val) {
        this.val = val;
    }
}
