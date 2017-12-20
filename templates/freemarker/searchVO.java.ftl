package ${basePackage}.${table.moduleName}.${table.entityName}.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * ${table.remarks} 查询条件
 * @author ${author}
 * @version ${.now?date}
 */
@ApiModel("${table.remarks}查询条件")
@Getter
@Setter
@ToString
public class ${table.entityName ? cap_first}SearchVO implements Serializable {
<#list table.baseColumns as col>
    <#if (!col.commonflag)>
    @ApiModelProperty(value="${col.remarks!col.javaProperty}")
    private ${col.javaType} ${col.javaProperty};
    </#if>
</#list>
    @ApiModelProperty(value="分页参数，页码")
    Integer page = 1;
    @ApiModelProperty(value="分页参数，每页显示条数")
    Integer size = 0;
    @ApiModelProperty(value="分页参数，排序")
    String sort = "";
}