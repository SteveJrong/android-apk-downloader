/**
 * Copyright 2021 Steve Jrong
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
package com.stevejrong.android.apk.downloader.provider.app.download.browser.impl;

import com.stevejrong.android.apk.downloader.provider.app.download.browser.AbstractBrowserPreferences;
import com.stevejrong.android.apk.downloader.provider.app.download.browser.IBrowserPreferences;
import org.openqa.selenium.WebDriver;

/**
 * Service Implements - 微软Edge浏览器参数设置业务实现类
 *
 * @author Steve Jrong
 * create date: 2021-05-15 8:23 PM
 * @since 1.0
 */
public class MicrosoftEdgePreferences extends AbstractBrowserPreferences implements IBrowserPreferences {
    @Override
    public WebDriver initBrowserPrefs() {
        return null;
    }
}
