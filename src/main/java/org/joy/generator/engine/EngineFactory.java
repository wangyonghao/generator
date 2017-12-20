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

import org.joy.generator.engine.freemarker.FreeMarkerImpl;
import org.joy.generator.engine.velocity.VelocityImpl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class EngineFactory implements Serializable {

    private static final long serialVersionUID = 2299973831636720810L;

    private static transient Map<String, TemplateEngine> engineMap;

    static {
        engineMap = new HashMap<String, TemplateEngine>();
        engineMap.put("freemarker", new FreeMarkerImpl());
        engineMap.put("velocity", new VelocityImpl());
    }

    public static TemplateEngine getEngine(String engine) {
        return engineMap.get(engine);
    }
}
