
package com.coul.common.utils.reflect;

import java.lang.reflect.Method;

/**
 * 代理处理接口类
 * 
 * @author chillming
 */
public interface IProxyHandler {
    /**
     * 是否代理此方法
     * 
     * @param cls
     *            原目标实例对象的类名
     * @param method
     *            原目标实例对象方法
     * @param args
     *            调用参数
     * @return 是否执行代理方法
     */
    public boolean isMethodProxy(Class<?> cls, Method method, Object[] args);
    
    /**
     * 执行之前
     * 
     * @param cls
     *            原目标实例对象的类名
     * @param method
     *            原目标实例对象方法
     * @param args
     *            调用参数
     * @return 处理过程的内部参数对象
     */
    public Object doBefore(Class<?> cls, Method method, Object[] args);
    
    /**
     * 执行之后
     * 
     * @param cls
     *            原目标实例对象的类名
     * @param method
     *            原目标实例对象方法
     * @param args
     *            调用参数
     * @param innerObj
     *            处理过程的内部参数对象
     */
    public void doAfter(Class<?> cls, Method method, Object[] args, Object innerObj);
    
    /**
     * 执行出错
     * 
     * @param cls
     *            原目标实例对象的类名
     * @param method
     *            原目标实例对象方法
     * @param args
     *            调用参数
     * @param innerObj
     *            处理过程的内部参数对象
     */
    public void doError(Class<?> cls, Method method, Object[] args, Object innerObj, Throwable t);
}
