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
package org.joy.generator.engine.freemarker;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.joy.generator.engine.AbstractTemplateEngine;
import org.joy.generator.engine.TemplateEngineException;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

@Slf4j
public class FreeMarkerImpl extends AbstractTemplateEngine {
    private Configuration       config;

    public FreeMarkerImpl(){
        initConfiguration();
    }
    public void initConfiguration() {
        try {
            File file =new File("templates/freemarker");
            if(!file.exists()){
                System.out.println("path "+ file.getPath()+" is not exist");
            }
            config = new Configuration();
            config.setDirectoryForTemplateLoading(file);
            config.setObjectWrapper(new DefaultObjectWrapper());
            config.setDefaultEncoding(DEFAULT_ENCODING);
            config.setSetting("classic_compatible", "true");
            config.setSetting("whitespace_stripping", "true");
            config.setSetting("template_update_delay", "1");
            config.setSetting("locale", "zh_CN");
            config.setSetting("url_escaping_charset", DEFAULT_ENCODING);
            config.setSetting("datetime_format", "yyyy-MM-dd hh:mm:ss");
            config.setSetting("date_format", "yyyy-MM-dd");
            config.setSetting("time_format", "HH:mm:ss");
            config.setSetting("number_format", "0.######;");
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }

    @Override
    public String evaluate(Object model, String stringTemplate) throws TemplateEngineException {
        try {
            Configuration cfg = new Configuration();
            cfg.setTemplateLoader(new StringTemplateLoader(stringTemplate));
            cfg.setDefaultEncoding(this.inputEncoding);

            Template template = cfg.getTemplate("");
            StringWriter writer = new StringWriter();
            template.process(model, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new TemplateEngineException(e.getMessage(), e);
        }
    }

    @Override
    public void generate(Object model, String inputFile, Writer out) throws TemplateEngineException {
        try {
            Template template =config.getTemplate(inputFile, this.inputEncoding);
            template.process(model, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new TemplateEngineException(e.getMessage(), e);
        }
    }


}
