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
package org.joy.generator.config.model;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
public class DatabaseElement implements Serializable, Comparable<DatabaseElement> {
    private String            name;
    private String            driverClass;
    private String            connectionUrl;
    private String            username;
    private String            password;
    private String            schema;

    public DatabaseElement(String name, String driverClass, String connectionUrl, String username, String password,
                           String schema){
        setName(name);
        setDriverClass(driverClass);
        setConnectionUrl(connectionUrl);
        setUsername(username);
        setPassword(password);
        setSchema(schema);
    }

    public String getUsername() {
        return username == null ? "" : username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name == null ? "" : name;
    }

    public void setName(String name) {
        if (name == null || name.length() == 0) {
            throw new NullPointerException("You must specify a name!");
        }
        this.name = name;
    }

    public String getDriverClass() {
        return this.driverClass == null ? "" : driverClass;
    }

    public void setDriverClass(String driverClass) {
        if (driverClass == null) {
            throw new NullPointerException("You must specify a driver!");
        }
        this.driverClass = driverClass;
    }

    public String getConnectionUrl() {
        return this.connectionUrl == null ? "" : connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        if (connectionUrl == null) {
            throw new NullPointerException("You must specify a connection URL!");
        }
        this.connectionUrl = connectionUrl;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DatabaseElement)){
            return false;
        }
        return ((DatabaseElement) obj).getName().equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(DatabaseElement other) {
        return name.toLowerCase().compareTo(other.getName().toLowerCase());
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getSchema() {
        return schema == null ? "" : schema.toUpperCase();
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
