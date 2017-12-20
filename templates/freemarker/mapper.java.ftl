package ${basePackage}.${table.moduleName}.${table.entityName}.mapper;

import com.bzgwl.yoreach.${table.moduleName}.${table.entityName}.entity.${table.entityName ? cap_first};
import com.bzgwl.yoreach.${table.moduleName}.${table.entityName}.vo.${table.entityName ? cap_first}InsertVO;
import com.bzgwl.yoreach.${table.moduleName}.${table.entityName}.vo.${table.entityName ? cap_first}SearchVO;
import com.bzgwl.yoreach.${table.moduleName}.${table.entityName}.vo.${table.entityName ? cap_first}UpdateVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * ${table.remarks} 数据库操作
 * @author ${author}
 * @version ${.now?date}
 */
@Repository
public interface ${table.entityName ? cap_first}Mapper{
    /**
     * 查询${table.remarks}
     * @param ${table.entityName} 查询条件
     * @return 符合条件的${table.remarks}
     */
    List<${table.entityName ? cap_first}> find(${table.entityName ? cap_first}SearchVO ${table.entityName});

    /**
     * 根据id获取${table.remarks}
     * @param id ${table.remarks}id
     * @return ${table.remarks}
     */
    ${table.entityName ? cap_first} get(@Param("id") Serializable id);

    /**
     * 插入${table.remarks}
     * @param ${table.entityName} 待插入的${table.remarks}
     */
    void insert(${table.entityName ? cap_first}InsertVO ${table.entityName});

    /**
     * 待更新${table.remarks}
     * @param ${table.entityName} 待更新的${table.remarks}
     */
    void update(${table.entityName ? cap_first}UpdateVO ${table.entityName});

    /**
     * 逻辑删除${table.remarks}
     * @param params {
     *                   id: 待删除${table.remarks}的id,
     *                   updateUser: 更新者
     *               }
     */
    void delete(Map<String,Object> params);
}