<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.${table.moduleName}.${table.entityName}.mapper.${table.entityName ?cap_first}Mapper">

    <sql id="Base_Column_List">
    <#list table.columns as col>
        ${col.columnName ? lower_case}${col_has_next?string(",","")}
    </#list>
    </sql>

    <select id="find" resultType="${table.entityName ? cap_first}">
        select <include refid="Base_Column_List" />
          from ${table.tableName ? lower_case}
         where delete_flag='0'
    <#list table.baseColumns as col>
	<#if (!col.commonflag)>
        <if test="${col.javaProperty} != null">
           and ${col.columnName ? lower_case}=${"#"}{${col.javaProperty}}
        </if>
	</#if>
    </#list>
    </select>

    <select id="get" resultType="${table.entityName ? cap_first}">
        select <include refid="Base_Column_List" />
          from ${table.tableName ? lower_case}
         where id=${"#"}{id}
    </select>

    <insert id="insert">
        insert into ${table.tableName ? lower_case} (
        <#list table.columns as col>
            ${col.columnName ? lower_case}${col_has_next?string(",","")}
        </#list>
        ) values (
        <#list table.columns as col>
        <#if (!col.commonflag)>
            ${"#"}{${col.javaProperty}}${col_has_next?string(",","")}
        </#if>
        </#list>
            sysdate,
            ${"#"}{createUser},
            sysdate,
            ${"#"}{createUser},
            '0')
    </insert>

    <update id="update">
        update ${table.tableName ? lower_case}
        <set>
        <#list table.baseColumns as col>
        <#if (!col.commonflag)>
            <if test="${col.javaProperty}!=null">
                ${col.columnName ? lower_case}=${"#"}{${col.javaProperty}},
            </if>
        </#if>
        </#list>
            update_user=${"#"}{updateUser},
            update_time=sysdate
        </set>
         where id = ${"#"}{id}
    </update>

    <update id="delete">
        update ${table.tableName ? lower_case}
           set delete_flag='1',
               update_user=${"#"}{updateUser},
               update_time=${"#"}{updateTime}
         where delete_flag='0'
           and id=${"#"}{id}
    </update>
</mapper>
