/*
 * Copyright 2014 ptma@163.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.joy.generator.engine;

import java.io.Writer;

public interface TemplateEngine {
    String DEFAULT_ENCODING = "UTF-8";

    /**
     *
     * @param dataModel 数据模型
     * @param inputFile 模板文件
     * @param outputFile 输出文件路径(包含目录和文件名),支持使用${}表达式访问model属性
     * @throws TemplateEngineException
     */
    void generate(Object dataModel, String inputFile, String outputFile) throws TemplateEngineException;

    void generate(Object dataModel, String inputFile, Writer out) throws TemplateEngineException;

    /**
     * 指定模板文件存放目录
     * @param path
     */
    void setTemplatePath(String path);

    void setInputEncoding(String encoding);

    void setOutputEncoding(String encoding);
}
