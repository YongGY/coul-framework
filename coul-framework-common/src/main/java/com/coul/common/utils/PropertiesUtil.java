package com.coul.common.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.coul.common.log.ILogger;
import com.coul.common.log.LoggerFactory;

public class PropertiesUtil {
    
    private static final ILogger       LOG       = LoggerFactory.getLogger(PropertiesUtil.class);
    /**
     * 缓存.
     */
    private static Map<String, String> cacheMap  = new HashMap<String, String>();
    
    /**
     * 分格符.
     */
    public static final String         CACHE_TAG = "__";
    
    /**
     * 构造函数.
     */
    protected PropertiesUtil() {
        
    }
    
    public static void clearCache() {
        cacheMap.clear();
    }
    
    public static String getConfigValueCache(final String fileName, final String key) {
        String fkey = fileName + CACHE_TAG + key;
        if ( cacheMap.containsKey(fkey)) {
            return PropertiesUtil.cacheMap.get(fkey);
        }
         cacheMap.put(fkey, getConfigValue(fileName, key));
        return  cacheMap.get(fkey);
    }
    
    public static String getConfigValue(final String fileName, final String key) {
        InputStream dataIn = null;
        try {
            final Properties properties = new Properties();
            if (fileName.endsWith(".properties")) {
                dataIn = PropertiesUtil.class.getResourceAsStream("/" + fileName);
            } else {
                dataIn = PropertiesUtil.class.getResourceAsStream("/" + fileName + ".properties");
            }
            properties.load(dataIn);
            dataIn.close();
            return properties.getProperty(key);
        } catch (final Exception e) {
            LOG.error(PropertiesUtil.class.getName(), e);
            return null;
        } finally {
            try {
                if (dataIn != null) {
                    dataIn.close();
                }
            } catch (Exception e) {
                LOG.error(PropertiesUtil.class.getName(), e);
            }
        }
    }
}
