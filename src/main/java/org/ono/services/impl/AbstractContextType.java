package org.ono.services.impl;

import org.ono.services.IContextType;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by ono on 2018/11/21.
 */
public abstract class AbstractContextType implements IContextType{

    public Map<String, String> convertProp2Map(Properties props){

        if (null == props) return new HashMap<>();

        Map<String, String> propMap = new HashMap<>();
        for (String key: props.stringPropertyNames()){
            propMap.put(key, props.getProperty(key));
        }

        return propMap;
    }
}
