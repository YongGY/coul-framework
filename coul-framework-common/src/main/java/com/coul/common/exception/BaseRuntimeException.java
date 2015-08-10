
package com.coul.common.exception;

import java.util.Iterator;
import java.util.List;

import com.coul.common.model.MapBean;
import com.coul.common.utils.type.ObjectUtils;

/**
 * 
 * 异常基类
 * 
 * @author linhz
 * 
 */
public abstract class BaseRuntimeException extends RuntimeException implements IException {
    
    /**
     * 
     */
    private static final long serialVersionUID = -331225421320749430L;
    
    private String            expCode          = null;                // 异常编码
                                                                       
    private String            expDesc          = null;                // 异常描述
                                                                       
    private MapBean           expMap;                                 // 异常参数Map
      
    
    /**
     * 
     */
    public BaseRuntimeException(){
    	
    }
    
    public BaseRuntimeException(Throwable cause){
    	
    }
    
    public BaseRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
    
    /**
     * 构造
     * 
     * @param expCode
     *            异常编码
     */
    public BaseRuntimeException(String expCode) {
        this(expCode, null, null);
    }
    
    /**
     * 构造
     * 
     * @param expCode
     *            异常编码
     * @param expDesc
     *            系统内部异常描述信息
     */
    public BaseRuntimeException(String expCode, String expDesc) {
        
        this(expCode, expDesc, null);
    }
    
    /**
     * 
     * @param expCode
     * @param expDesc
     * @param cause
     */
    public BaseRuntimeException(String expCode, String expDesc, Throwable cause) {
        
        super(expDesc == null ? ""
            : expDesc, cause);
        
        this.expCode = expCode;
        this.expDesc = expDesc;
        
        this.expMap = new MapBean();
        
        // 默认参数
        this.expMap.put("$errorCode", expCode);
        this.expMap.put("$message", getMessage());
    }
    
    /**
     * 获取异常中的BaseRuntimeException。
     * 
     * @return 如果异常中不存在BaseRuntimeException则返回null
     */
    public static BaseRuntimeException getBottomBaseRuntimeException(Exception e) {
        Throwable tmp = e;
        while (tmp != null) {
            if (tmp instanceof BaseRuntimeException)
                return (BaseRuntimeException) tmp;
            tmp = tmp.getCause();
        }
        return null;
    }
    
    /**
     * 提取异常中嵌套的简短消息（过滤了CallStack的信息）
     * 
     * @param t
     * @return
     */
    public static String getNestedMessage(Throwable t) {
        StringBuilder sb = null;
        
        if (t == null) {
            return null;
        } else {
            sb = new StringBuilder();
            getNestedMessage(t, sb);
            
            return sb.toString();
        }
    }
    
    /**
     * 
     * @param t
     * @param sb
     */
    private static void getNestedMessage(Throwable t, StringBuilder sb) {
        String msg = null;
        Throwable cause = null;
        
        msg = t.getMessage();
        if (msg != null) {
            sb.append(msg).append(" ");
        } else {
            sb.append(" null. ");
        }
        
        cause = t.getCause();
        while (cause != null && cause != t) {
            msg = cause.getMessage();
            
            if (msg != null) {
                sb.append(" {Nested caused by: ").append(msg).append("} ");
            } else {
                sb.append(" {Nested caused by: ").append("null").append("} ");
            }
            
            cause = cause.getCause();
        }
        
        return;
    }
    
    /**
     * 提取异常中嵌套的简短消息（过滤了CallStack的信息）
     * 
     * @param t
     * @return
     */
    public static String getNestedMessage(List<Throwable> tList) {
        StringBuilder sb = null;
        int index = 1;
        
        if (tList == null) {
            return null;
        } else {
            sb = new StringBuilder();
            for (Iterator<Throwable> i = tList.iterator(); i.hasNext(); index++) {
                Throwable t = i.next();
                
                sb.append(String.format(" Caused by[%d]{ ", index));
                getNestedMessage(t, sb);
                sb.append(" } ");
            }
            
            return sb.toString();
        }
    }
    
    /**
     * 
     * @param t
     * @return
     */
    public static String toNestedString(Throwable t) {
        StringBuilder sb = null;
        
        if (t == null) {
            return null;
        } else {
            sb = new StringBuilder();
            
            toNestedString(t, sb);
            
            return sb.toString();
        }
    }
    
    /**
     * 
     * @param tList
     * @return
     */
    public static String toNestedString(List<Throwable> tList) {
        StringBuilder sb = null;
        int index = 1;
        
        if (tList == null) {
            return null;
        } else {
            sb = new StringBuilder();
            for (Iterator<Throwable> i = tList.iterator(); i.hasNext(); index++) {
                Throwable t = i.next();
                
                sb.append(String.format("Caused by[%d]{ ", index));
                toNestedString(t, sb);
                sb.append(" }");
            }
            
            return sb.toString();
        }
    }
    
    /**
     * 
     * @param t
     * @param sb
     */
    private static void toNestedString(Throwable t, StringBuilder sb) {
        String msg = null;
        Throwable cause = null;
        
        msg = t.toString();
        sb.append(msg).append(" ");
        
        cause = t.getCause();
        while (cause != null && cause != t) {
            msg = cause.toString();
            sb.append(" {Nested caused by: ").append(msg).append(" } ");
            cause = cause.getCause();
        }
        
        return;
    }
    
    /**
     * 增加异常参数
     * 
     * @param name
     *            参数名称
     * @param value
     *            参数值
     * @return CRM2Exception对象本身
     */
    public BaseRuntimeException addParam(String name, Object value) {
        this.expMap.put(name, ObjectUtils.toString(value));
        return this;
    }
    
    public String toString() {
        return String.format("[异常类型是：%s；异常编码是：%s；异常提示是：%s]", this.getClass().getName(), expCode,
            expDesc);
    }
    
    /**
     * 获取异常参数
     * 
     * @return 异常参数
     */
    public MapBean getExpMap() {
        return expMap;
    }
    
    /**
     * 获取异常编码
     * 
     * @return 异常编码
     */
    public String getExpCode() {
        return expCode;
    }
    
    /**
     * 设置异常编码
     * 
     * @param expCode
     *            异常编码
     */
    public void setExpCode(String expCode) {
        this.expCode = expCode;
        this.expMap.put("$errorCode", expCode);
    }
    
    /**
     * 获取异常描述
     * 
     * @return 异常描述
     */
    public String getExpDesc() {
        return expDesc;
    }
    
    /**
     * 設置异常描述
     * 
     * @am常描述
     */
    public void setExpDesc(String expDesc) {
        this.expDesc = expDesc;
    }
    
}
