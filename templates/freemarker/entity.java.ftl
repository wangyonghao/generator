package ${basePackage}.${table.moduleName}.${table.entityName}.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * ${table.remarks} 实体类
 * @author ${author}
 * @version ${.now?date}
 */
@ApiModel("${table.remarks}")
@Getter
@Setter
@ToString
public class ${table.entityName ? cap_first} implements Serializable {
<#list table.primaryKeys as key>
    @ApiModelProperty(value="${key.remarks!key.javaProperty}")
    private ${key.javaType} ${key.javaProperty};
</#list>
<#list table.baseColumns as column>
    @ApiModelProperty(value="${column.remarks!column.javaProperty}")
    private ${column.javaType} ${column.javaProperty};
</#list>
}