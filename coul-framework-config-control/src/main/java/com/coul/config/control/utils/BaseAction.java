package com.coul.config.control.utils;
//package com.ffcs.atte.config.control.utils;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.struts2.interceptor.ServletRequestAware;
//import org.apache.struts2.interceptor.ServletResponseAware;
//
//import com.opensymphony.xwork2.ActionSupport;
//
//public class BaseAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{
//
//	
//	public final static String MSG ="msg";  
//	
//	public final static String DATA ="data";  
//	
//	public final static String LOGOUT_FLAG = "logoutFlag";  
//	
//	public HttpServletRequest request; 
//	
//	public HttpServletResponse response;
//	
//	 /**
//	  * 获取IP地址
//	  * @param request
//	  * @return
//	  */
//	 public String getIpAddr(HttpServletRequest request) {
//		String ip = request.getHeader("x-forwarded-for");
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("WL-Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getRemoteAddr();
//		}
//		return ip;
//	}
//
//	 
//	 /**
//	  * 所有ActionMap 统一从这里获取
//	  * @return
//	  */
//	public Map<String,Object> getRootMap(){
//		Map<String,Object> rootMap = new HashMap<String, Object>();
//		//添加url到 Map中
//		rootMap.putAll(URLUtils.getUrlMap());
//		return rootMap;
//	}
//	
//	/**
//	 *
//	 * 提示成功信息
//	 *
//	 * @param message
//	 *
//	 */
//	public void sendSuccessMessage(HttpServletResponse response,  String message) {
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put(SUCCESS, true);
//		result.put(MSG, message);
//		HtmlUtil.writerJson(response, result);
//	}
//
//	/**
//	 *
//	 * 提示失败信息
//	 *
//	 * @param message
//	 *
//	 */
//	public void sendFailureMessage(HttpServletResponse response,String message) {
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put(SUCCESS, false);
//		result.put(MSG, message);
//		HtmlUtil.writerJson(response, result);
//	}
//
//	@Override
//	public void setServletResponse(HttpServletResponse httpServletResponse) {
//		this.response=httpServletResponse;
//	}
//
//	@Override
//	public void setServletRequest(HttpServletRequest httpServletRequest) {
//		this.request=httpServletRequest;
//	}
//}
