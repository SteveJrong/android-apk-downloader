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
package com.stevejrong.android.apk.downloader.exception.install;

/**
 * Exception - 设备连接失败或未连接异常
 *
 * @author Steve Jrong
 * create date: 2021-05-20 11:43 AM
 * @since 1.0
 */
public class DeviceConnectedFailedException extends RuntimeException {
    public DeviceConnectedFailedException() {
        super("设备连接异常，未检测到连接的设备。请检查设备是否开启USB调试模式、电脑端口是否开放、杀毒软件是否拦截、其他进程是否占用等。");
    }
}