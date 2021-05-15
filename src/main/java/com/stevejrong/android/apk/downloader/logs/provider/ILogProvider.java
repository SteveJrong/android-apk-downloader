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
package com.stevejrong.android.apk.downloader.logs.provider;

/**
 * 日志业务接口
 */
public interface ILogProvider {

    /**
     * 方法正常执行后的日志处理
     *
     * @param params 可选参数
     */
    void afterReturnDoAction(Object... params);

    /**
     * 方法执行期间出现异常的日志处理
     *
     * @param params 可选参数
     */
    void afterExceptionDoAction(Object... params);
}
