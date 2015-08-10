package com.coul.common.exception;

import com.coul.common.utils.MessageFormatter;

public class ApplictionRuntimeException extends BaseRuntimeException {
    private static final long serialVersionUID = 3005924432785438810L;
    
    public ApplictionRuntimeException(String expCode, String expDesc) {
        super(expCode, expDesc);
    }
    
    public ApplictionRuntimeException(String expCode, String expDesc, Throwable cause) {
        super(expCode, expDesc, cause);
    }
    
    public ApplictionRuntimeException(String expCode, String expDesc, Object obj) {
        super(expCode, expDesc);
        String msgStr = MessageFormatter.format(expDesc, obj);
        setExpDesc(msgStr);
    }
    
    public ApplictionRuntimeException(String expCode, String expDesc, Object obj1, Object obj2) {
        super(expCode, expDesc);
        String msgStr = MessageFormatter.format(expDesc, obj1, obj2);
        setExpDesc(msgStr);
    }
    
    public ApplictionRuntimeException(String expCode, String expDesc, Object... objs) {
        super(expCode, expDesc);
        String msgStr = MessageFormatter.arrayFormat(expDesc, objs);
        setExpDesc(msgStr);
    }
}
