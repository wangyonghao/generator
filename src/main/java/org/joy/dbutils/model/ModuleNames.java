package org.joy.dbutils.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wyh
 * @version 2017/12/19.
 */
public class ModuleNames {
    private static Map<String,String> map;

    static {
        map = new HashMap<>();
        map.put("sys","system");
        map.put("cmd","commodity");
        map.put("scy","security");
        map.put("cmp","company");
        map.put("ord","order");
        map.put("stl","settlement");
        map.put("tsp","transport");
        map.put("whs","warehouse");
    }

    public static String get(String key){
        String name= map.get(key);
        if(name == null){
            return "";
        }
        return name;
    }
}

