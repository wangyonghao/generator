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
package org.joy.generator.engine.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;
import org.joy.generator.engine.AbstractTemplateEngine;
import org.joy.generator.engine.TemplateEngineException;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

public class VelocityImpl extends AbstractTemplateEngine {

    private static final VelocityEngine velocityEngine = new VelocityEngine();

    public VelocityImpl(){
        File f = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
        String classPath = f.getPath() + File.pathSeparator;
        this.templatePath = classPath.replaceAll("%20", " ");
        this.init();
    }

    public VelocityImpl(String classPath){
        this.templatePath = classPath;
        this.init();
    }

    public void init(){
        final Properties props = new Properties();
        props.setProperty(Velocity.INPUT_ENCODING, DEFAULT_ENCODING);
        props.setProperty(Velocity.OUTPUT_ENCODING, DEFAULT_ENCODING);
        props.setProperty(Velocity.ENCODING_DEFAULT, DEFAULT_ENCODING);
        props.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, NullLogChute.class.getName());
        props.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, this.templatePath + "templates/velocity");
        velocityEngine.init(props);
    }

    private Map<String,Object> convertToMap(Object dataModel){
        return (Map<String,Object>)dataModel;
    }

    @Override
    public String evaluate(Object dataModel, String stringTemplate) throws TemplateEngineException {
        try {
            StringWriter writer = new StringWriter();
            VelocityContext context = new VelocityContext(convertToMap(dataModel));
            velocityEngine.evaluate(context, writer, "", stringTemplate);
            return writer.toString();
        } catch (Exception e) {
            throw new TemplateEngineException(e.getMessage(), e);
        }
    }

    @Override
    public void generate(Object dataModel, String inputFile, Writer out)
            throws TemplateEngineException {
        try {
            VelocityContext context = new VelocityContext(convertToMap(dataModel));
            Template template = velocityEngine.getTemplate(inputFile, this.inputEncoding);
            template.merge(context, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new TemplateEngineException(e.getMessage(), e);
        }
    }




}
