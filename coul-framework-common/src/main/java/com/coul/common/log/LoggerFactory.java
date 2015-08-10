package com.coul.common.log;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 日志工厂类.
 * @author zhanghr
 *
 */
public class LoggerFactory {
    
    private static ConcurrentMap<Class<?>, ILogger> loggerMap = new ConcurrentHashMap<Class<?>, ILogger>();
    private static ConcurrentMap<Class<?>, ILogger> bizMap    = new ConcurrentHashMap<Class<?>, ILogger>();
    
    private LoggerFactory() {
    };
    
    public static ILogger getLogger(Class<?> name) {
        ILogger logger = loggerMap.get(name);
        if (logger != null) {
            return logger;
        } else {
            logger = new LoggerAppender(name);
            loggerMap.putIfAbsent(name, logger);
        }
        return logger;
    }
    
    public static ILogger getBizLogger(Class<?> name) {
        ILogger logger = bizMap.get(name);
        if (logger != null) {
            return logger;
        } else {
            logger = new BizLoggerAppender(name);
            bizMap.putIfAbsent(name, logger);
        }
        return logger;
    }
}
