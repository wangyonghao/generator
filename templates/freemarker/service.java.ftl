package ${basePackage}.${table.moduleName}.${table.entityName}.service;


import com.bzgwl.yoreach.${table.moduleName}.${table.entityName}.entity.${table.entityName ? cap_first};
import com.bzgwl.yoreach.${table.moduleName}.${table.entityName}.mapper.${table.entityName ? cap_first}Mapper;
import com.bzgwl.yoreach.${table.moduleName}.${table.entityName}.vo.${table.entityName ? cap_first}InsertVO;
import com.bzgwl.yoreach.${table.moduleName}.${table.entityName}.vo.${table.entityName ? cap_first}SearchVO;
import com.bzgwl.yoreach.${table.moduleName}.${table.entityName}.vo.${table.entityName ? cap_first}UpdateVO;
import com.bzgwl.yoreach.commons.api.TokenHelper;
import com.bzgwl.yoreach.commons.api.page.Page;
import com.bzgwl.yoreach.commons.keygen.KeyGenHelper;
import com.bzgwl.yoreach.commons.validator.Validator;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
/**
 * ${table.remarks} 业务处理
 * @author ${author}
 * @version ${.now?date}
 */
@Service
public class ${table.entityName ? cap_first}Service{
    @Autowired
    ${table.entityName ? cap_first}Mapper mapper;

    /**
     * 查询${table.remarks}
     * @param ${table.entityName} 查询条件
     * @return 分页数据
     */
    public Page<${table.entityName ? cap_first}> find(${table.entityName ? cap_first}SearchVO ${table.entityName}) {
        PageHelper.startPage(${table.entityName});
        return new Page<>(mapper.find(${table.entityName}));
    }

    /**
     * 获取${table.remarks}
     * @param id 主键
     * @return ${table.remarks}
     */
    public ${table.entityName ? cap_first} get(Long id) {
        return mapper.get(id);
    }

    /**
     * 添加${table.remarks}
     * @param ${table.entityName} ${table.remarks}
     */
    @Transactional(rollbackFor = Exception.class)
    public void insert(${table.entityName ? cap_first}InsertVO ${table.entityName}) {
        //insert校验
        validateInsert(${table.entityName});

        ${table.entityName}.setId(KeyGenHelper.genKey());
        ${table.entityName}.setCreateUser(TokenHelper.getUserId());
        mapper.insert(${table.entityName});
    }

    /**
     * insert校验
     * @param ${table.entityName} 待添加的${table.remarks}
     */
    private void validateInsert(${table.entityName ? cap_first}InsertVO ${table.entityName}){
        //TODO 在这里添加校验代码
    }
    /**
     * 更新${table.remarks}
     * @param ${table.entityName} 待更新的${table.remarks}
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(${table.entityName ? cap_first}UpdateVO ${table.entityName}) {
        //update校验
        validateUpdate(${table.entityName});

        ${table.entityName}.setUpdateUser(TokenHelper.getUserId());
        mapper.update(${table.entityName});
    }

    /**
     * 更新校验
     * @param ${table.entityName} 待添加的${table.remarks}
     */
    private void validateUpdate(${table.entityName ? cap_first}UpdateVO ${table.entityName}){
        ${table.entityName ? cap_first} raw = this.get(${table.entityName}.getId());
        //更新时间戳
        Validator.equals(raw.getUpdateTime(),${table.entityName}.getUpdateTime(),"${table.remarks}已过时，可能已被其他人更新");

        //TODO 在这里添加校验代码
    }


    /**
     * 删除${table.remarks}
     * @param id 待删除${table.remarks}的id
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        Map<String,Object> params = Maps.newHashMap();
        params.put("id",id);
        params.put("updateUser",TokenHelper.getUserId());
        mapper.delete(params);
    }
}