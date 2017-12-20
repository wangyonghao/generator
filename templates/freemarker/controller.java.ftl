package ${basePackage}.${table.moduleName}.${table.entityName}.controller;

import ${basePackage}.${table.moduleName}.${table.entityName}.service.${table.entityName?cap_first}Service;
import ${basePackage}.${table.moduleName}.${table.entityName}.vo.${table.entityName?cap_first}InsertVO;
import ${basePackage}.${table.moduleName}.${table.entityName}.vo.${table.entityName?cap_first}SearchVO;
import ${basePackage}.${table.moduleName}.${table.entityName}.vo.${table.entityName?cap_first}UpdateVO;
import com.bzgwl.yoreach.commons.api.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ${table.remarks} API接口
 * @author ${author}
 * @version ${.now?date}
 */
@Api(tags = "${table.entityName}", description = "${table.remarks}")
@RestController
@RequestMapping("/${table.pluralName}")
public class ${table.entityName ? cap_first}Controller{
    @Autowired
    ${table.entityName ?cap_first}Service service;

    @ApiOperation(value = "查询${table.remarks}")
    @GetMapping
    public ResponseResult find(@ApiParam("查询条件") ${table.entityName ?cap_first}SearchVO condition) {
        return ResponseResult.ok(service.find(condition));
    }

    @ApiOperation(value = "添加${table.remarks}")
    @PostMapping
    public ResponseResult add(@ApiParam(value = "待插入的${table.remarks}", required = true) @RequestBody ${table.entityName ?cap_first}InsertVO ${table.entityName}) {
        service.insert(${table.entityName});
        return ResponseResult.ok();
    }

    @ApiOperation(value = "获取${table.remarks}")
    @GetMapping(value = "/{id}")
    public ResponseResult get(@ApiParam(value = "${table.remarks}id", required = true) @PathVariable Long id) {
        return ResponseResult.ok(service.get(id));
    }

    @ApiOperation(value = "更新${table.remarks}")
    @PutMapping(value = "/{id}")
    public ResponseResult update(@ApiParam(value = "${table.remarks}id", required = true) @PathVariable Long id,
                                 @ApiParam(value = "待更新的${table.remarks}", required = true) @RequestBody ${table.entityName ?cap_first}UpdateVO ${table.entityName}) {
        ${table.entityName}.setId(id);
        service.update(${table.entityName});
        return ResponseResult.ok();
    }

    @ApiOperation(value = "删除${table.remarks}")
    @DeleteMapping("/{id}")
    public ResponseResult delete(@ApiParam(value = "${table.remarks}id", required = true) @PathVariable Long id) {
        service.delete(id);
        return ResponseResult.ok();
    }
}