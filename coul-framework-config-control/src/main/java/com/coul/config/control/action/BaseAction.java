package com.coul.config.control.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.coul.config.control.utils.HtmlUtil;
import com.coul.config.control.utils.MyEditor;
import com.coul.config.control.utils.URLUtils;


public class BaseAction extends MultiActionController{
	

   @InitBinder  
   protected void initBinder(WebDataBinder binder) {  
		 binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));  
		 binder.registerCustomEditor(int.class,new MyEditor()); 
   }  
	 
	 /**
	  * 获取IP地址
	  * @param request
	  * @return
	  */
	 public String getIpAddr(HttpServletRequest request) {
			String ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			return ip;
		}
	
	 /**
	  * 所有ActionMap 统一从这里获取
	  * @return
	  */
	public Map<String,Object> getRootMap(){
		Map<String,Object> rootMap = new HashMap<String, Object>();
		//添加url到 Map中
		rootMap.putAll(URLUtils.getUrlMap());
		return rootMap;
	}
	
	public ModelAndView forword(String viewName,Map context){
		return new ModelAndView(viewName,context); 
	}
	
	public ModelAndView error(String errMsg){
		return new ModelAndView("error"); 
	}
	
	/**
	 *操作成功 返回json格式
	 */
	public void writerSuccess(HttpServletResponse response){
		Map map = new HashMap();
		HtmlUtil.writerJson(response, map);
	}
	
	/**
	 * 操作失败 返回json格式
	 * @param errMsg
	 */
	public void writerError(HttpServletResponse response,String errMsg){
		Map map = new HashMap();
		HtmlUtil.writerJson(response, map);
	}
}
