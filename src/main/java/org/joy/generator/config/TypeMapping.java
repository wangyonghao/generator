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
package org.joy.generator.config;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.joy.dbutils.model.Column;
import org.joy.dbutils.model.util.JavaTypeResolver;
import org.joy.dbutils.model.util.JdbcTypeResolver;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.io.Serializable;
import java.util.*;

public class TypeMapping implements Serializable {

    private static final String MAPING_FILE = "config/TypeMapping.xml";

    private String mappginFile;

    private Map<Integer, String> typeMap;
    private Map<Integer, String> fullTypeMap;

    public TypeMapping() {
        this.mappginFile = MAPING_FILE;
        typeMap = new HashMap<Integer, String>();
        fullTypeMap = new HashMap<Integer, String>();
    }

    public void loadMapping() {
        Configurations configs = new Configurations();
        try {
            XMLConfiguration config = configs.xml(MAPING_FILE);

            List<String> sqlTypes = config.getList(String.class, "map[@sqlType]");
            for (int i = 0; i<sqlTypes.size();i++){
                int type = JdbcTypeResolver.getJdbcType(sqlTypes.get(i));
//                typeMap.put(type, config.getString("map("+i+")[@javaType]"));
                fullTypeMap.put(type, config.getString("map("+i+")[@fullJavaType]"));
            }

        } catch (ConfigurationException cex) {
            throw new RuntimeException("Error occurred on loading TypeMapping.xml",cex);
        }
    }

    private Properties parseAttributes(Node node) {
        Properties attributes = new Properties();
        NamedNodeMap nnm = node.getAttributes();
        for (int i = 0; i < nnm.getLength(); i++) {
            Node attribute = nnm.item(i);
            String value = attribute.getNodeValue();
            attributes.put(attribute.getNodeName(), value);
        }

        return attributes;
    }

    public String calculateJavaType(Column column) {
        String javaType = typeMap.get(column.getJdbcType());

        if (javaType == null) {
            javaType = JavaTypeResolver.calculateJavaType(column);
        }
        return javaType;
    }

    public String calculateFullJavaType(Column column) {
        String javaType = fullTypeMap.get(column.getJdbcType());

        if (javaType == null) {
            javaType = JavaTypeResolver.calculateFullJavaType(column);
        }
        return javaType;
    }

    public String[] getAllJavaTypes() {
        Set<String> javaTypeSet = new HashSet<String>();
        javaTypeSet.addAll(typeMap.values());
        if (javaTypeSet.isEmpty()) {
            return JavaTypeResolver.getAllJavaTypes();
        }

        String[] values = new String[javaTypeSet.size()];
        int index = 0;
        for (String itemValue : javaTypeSet) {
            values[index++] = itemValue;
        }
        return values;
    }
}
