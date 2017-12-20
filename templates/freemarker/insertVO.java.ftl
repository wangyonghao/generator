package ${basePackage}.${table.moduleName}.${table.entityName}.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * ${table.remarks} 插入参数
 * @author ${author}
 * @version ${.now?date}
 */
@ApiModel("${table.remarks}插入参数")
@Getter
@Setter
@ToString
public class ${table.entityName ? cap_first}InsertVO implements Serializable{
<#list table.primaryKeys as key>
    @ApiModelProperty(value="${key.remarks!key.javaProperty}",hidden = true)
    private ${key.javaType} ${key.javaProperty};
</#list>
<#list table.baseColumns as col>
<#if (!col.commonflag)>
    @ApiModelProperty(value="${col.remarks!col.javaProperty}")
    private ${col.javaType} ${col.javaProperty};
</#if>
</#list>
    @ApiModelProperty(value="创建者",hidden = true)
    private Long createUser;
    @ApiModelProperty(value="更新者",hidden = true)
    private Long updateUser;
}