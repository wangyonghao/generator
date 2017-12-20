package org.joy.dbutils.model;

import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 通用业务表字段
 * @author wyh
 * @version 2017/12/19.
 */
public class CommonColumns {
    private static Set<String> columnSet;

    static{
        columnSet = new HashSet<>();
        columnSet.add("create_user");
        columnSet.add("create_time");
        columnSet.add("update_user");
        columnSet.add("update_time");
        columnSet.add("delete_flag");
    }

    public static boolean contains(String key){
        if(StringUtils.isEmpty(key)){
            return false;
        }
        return columnSet.contains(key.toLowerCase());
    }
}
