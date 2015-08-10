
package com.coul.common.utils.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * SPRING CGLIB动态代理
 * 
 * @author chillming
 */
public abstract class CglibDynProxy implements MethodInterceptor, IProxyHandler {
    private Object target;
    
    /**
     * 获取代理实例对象
     * 
     * @param target
     *            原目标实例对象
     * @return 代理实例对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getProxyInstance(T target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this); // call back method
        return (T) enhancer.create(); // create proxy instance
    }
    
    /**
     * (non-Javadoc)
     * @see net.sf.cglib.proxy.MethodInterceptor#intercept(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], net.sf.cglib.proxy.MethodProxy)
     */
    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy)
        throws Throwable {
        Class<?> cls = target.getClass();
        boolean isProxy = isMethodProxy(cls, method, args);
        Object handleObj = null;
        
        try {
            if (isProxy) {
                handleObj = doBefore(cls, method, args);
            }
            return method.invoke(this.target, args);
        } catch (Throwable t) {
            Throwable thr = t;
            if (t instanceof InvocationTargetException) {
                thr = ((InvocationTargetException) t).getTargetException();
                if (thr == null) {
                    thr = t;
                }
            }
            if (isProxy) {
                doError(cls, method, args, handleObj, thr);
            }
            throw thr;
        } finally {
            if (isProxy) {
                doAfter(cls, method, args, handleObj);
            }
        }
        
    }
}
