
package com.coul.common.exception;

import com.coul.common.utils.MessageFormatter;

/**
 * 
 * 逻辑异常
 * 
 * @author 
 * 
 */
public class LogicException extends BaseRuntimeException {
    private static final long serialVersionUID = 8839333569395935969L;
    
    public LogicException(String expCode, String expDesc) {
        super(expCode, expDesc);
    }
    
    public LogicException(String expCode, String expDesc, Throwable cause) {
        super(expCode, expDesc, cause);
    }
    
    public LogicException(String expCode, String expDesc, Object obj) {
        super(expCode, expDesc);
        String msgStr = MessageFormatter.format(expDesc, obj);
        setExpDesc(msgStr);
    }
    
    public LogicException(String expCode, String expDesc, Object obj1, Object obj2) {
        super(expCode, expDesc);
        String msgStr = MessageFormatter.format(expDesc, obj1, obj2);
        setExpDesc(msgStr);
    }
    
    public LogicException(String expCode, String expDesc, Object... objs) {
        super(expCode, expDesc);
        String msgStr = MessageFormatter.arrayFormat(expDesc, objs);
        setExpDesc(msgStr);
    }
}
