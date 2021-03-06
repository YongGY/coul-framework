
package com.coul.common.model;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import com.coul.common.utils.type.ObjectUtils;
import com.coul.common.utils.type.StringUtils;

/**
 * 直接使用Map不太方便,采用MapBean代替   
 * 
 * @author 
 */
public class MapBean extends ConcurrentHashMap<String, Object> {
    private static final long serialVersionUID = 8807995314376316468L;
    
    public MapBean() {
        super();
    }
    
    public MapBean(int initialCapacity) {
        super(initialCapacity);
    }
    
    public int getInt(String key) {
        return getInt(key, 0);
    }
    
    public int getInt(String key, int defaultValue) {
        Object obj = get(key);
        return ObjectUtils.toInt(obj, defaultValue);
    }
    
    public long getLong(String key) {
        return getLong(key, 0);
    }
    
    public long getLong(String key, long defaultValue) {
        Object obj = get(key);
        return ObjectUtils.toLong(obj, defaultValue);
    }
    
    public float getFloat(String key) {
        return getFloat(key, 0);
    }
    
    public float getFloat(String key, float defaultValue) {
        Object obj = get(key);
        return ObjectUtils.toFloat(obj, defaultValue);
    }
    
    public double getDouble(String key) {
        return getDouble(key, 0);
    }
    
    public double getDouble(String key, double defaultValue) {
        Object obj = get(key);
        return ObjectUtils.toDouble(obj, defaultValue);
    }
    
    public String getString(String key) {
        return getString(key, StringUtils.EMPTY);
    }
    
    public String getString(String key, String defaultValue) {
        Object obj = get(key);
        return ObjectUtils.toString(obj, defaultValue);
    }
    
    public Date getDate(String key) {
        Object obj = get(key);
        return ObjectUtils.toDate(obj);
    }
    
    public MapBean set(String key, Object value) {
        return this.put(key, value);
    }
    
    public MapBean put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
