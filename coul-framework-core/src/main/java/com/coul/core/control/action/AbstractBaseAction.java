package com.coul.core.control.action;
//package com.ctg.itrdc.core.control.action;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.ctg.itrdc.core.domain.base.entity.URLUtils;
//
///**
// * 
// * @author zengshl
// *
// */
//public abstract class AbstractBaseAction extends ActionSupport{
//	
//	 public final static String MSG ="msg";
//	 /*常用的方法*/
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
//	public Map<String, Object> sendSuccessMessage(String message) {
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put(SUCCESS, true);
//		result.put(MSG, message);
////		HtmlUtil.writerJson(response, result);
//		return result;
//	}
//
//	/**
//	 *
//	 * 提示失败信息
//	 *
//	 * @param message
//	 *
//	 */
//	public Map<String, Object> sendFailureMessage(String message) {
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put(SUCCESS, false);
//		result.put(MSG, message);
////		HtmlUtil.writerJson(response, result);
//		return result;
//	}
//
//}
