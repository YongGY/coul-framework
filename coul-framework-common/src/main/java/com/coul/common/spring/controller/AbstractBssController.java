package com.coul.common.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * 
 * 功能说明:
 * SpringMVC 应用Controler基类
 *
 * @author 
 * 
 * @Date 2015年4月15日 上午11:21:47
 *
 *
 * 版本号  |   作者   |  修改时间   |   修改内容
 *
 */
public abstract class AbstractBssController {  

	/**
	 * 拦截请求异常
	 * @param request
	 * @param e
	 * @return
	 */
    @ExceptionHandler
    public String exception(HttpServletRequest request, Exception e) {  
        
    	e.printStackTrace();
    	//TODO 添加异常处理逻辑，如日志记录
        request.setAttribute("exceptionMessage", e.getMessage());  
                 
        //返回异常VIEW显示
        return "error";  
    }     
}
