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
package org.joy.dbutils.model;

import org.apache.commons.lang3.StringUtils;
import org.joy.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Table implements java.io.Serializable {
    private String            tableName;
    private String            tableType;
    private String            catalog          = null;
    private String            schema           = null;
    private String            remarks;
    private List<Column>      primaryKeys      = new ArrayList<Column>(); //主键
    private List<Column>      baseColumns      = new ArrayList<Column>(); //列
    private List<ForeignKey>  importedForeignKeys = new ArrayList<ForeignKey>();
    private List<ForeignKey>  exportedForeignKeys = new ArrayList<ForeignKey>(); //外键

    //实体类名
    private String entityName;
    //所属模块名
    private String moduleName;
    //名称得数形式
    private String pluralName;

    public Table(){}

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
        String prefix = StringUtil.substringBefore(tableName,"_").toLowerCase();
        this.moduleName = ModuleNames.get(prefix);
        this.entityName = StringUtil.substringAfter(tableName,"_").toLowerCase();
    }

    public String getRemarks() {
        return remarks == null ? "" : remarks.trim();
    }

    public boolean isHasRemarks() {
        return StringUtil.isNotEmpty(remarks);
    }

    public String getRemarksUnicode() {
        return StringUtil.toUnicodeString(getRemarks());
    }

    public void setRemarks(String remarks) {

        this.remarks = StringUtils.removeEnd(remarks,"表");
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getPluralName() {
        return StringUtil.plural(this.entityName);
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public Column getColumn(String columnName) {
        for (Column column : primaryKeys) {
            if (column.getColumnName().equals(columnName)) {
                return column;
            }
        }
        for (Column column : baseColumns) {
            if (column.getColumnName().equals(columnName)) {
                return column;
            }
        }
        return null;
    }

    public List<Column> getColumns() {
        List<Column> allColumns = new ArrayList<Column>();
        allColumns.addAll(primaryKeys);
        allColumns.addAll(baseColumns);
        return allColumns;
    }

    public List<Column> getBaseColumns() {
        return baseColumns;
    }

    public void addBaseColumn(Column column) {
        this.baseColumns.add(column);
    }

    public List<Column> getPrimaryKeys() {
        return primaryKeys;
    }

    public void addPrimaryKey(Column primaryKeyColumn) {
        this.primaryKeys.add(primaryKeyColumn);
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public boolean isHasDateColumn() {
        for (Column column : getColumns()) {
            if (column.isDate()) {
                return true;
            }
        }
        return false;
    }

    public boolean isHasBigDecimalColumn() {
        for (Column column : getColumns()) {
            if (column.isBigDecimal()) {
                return true;
            }
        }
        return false;
    }

    public boolean isHasNotNullColumn() {
        for (Column column : getColumns()) {
            if (!column.isNullable() && !column.isString()) {
                return true;
            }
        }
        return false;
    }

    public boolean isHasNotBlankColumn() {
        for (Column column : getColumns()) {
            if (!column.isNullable() && column.isString()) {
                return true;
            }
        }
        return false;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<ForeignKey> getImportedForeignKeys() {
        return importedForeignKeys;
    }


    public List<ForeignKey> getExportedForeignKeys() {
        return exportedForeignKeys;
    }

    public void addImportedKey(ForeignKey importedForeignKey) {
        this.importedForeignKeys.add(importedForeignKey);
    }

    public void addExportedKey(ForeignKey exportedForeignKey) {
        this.exportedForeignKeys.add(exportedForeignKey);
    }
}
