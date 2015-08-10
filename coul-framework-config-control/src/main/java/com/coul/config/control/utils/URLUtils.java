package com.coul.config.control.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class URLUtils {
	
	private static ResourceBundle res = ResourceBundle.getBundle("urls");
	private static 	Map<String,String> urlsMap = null;
	
	/**
	 * 获取urlMap
	 * @return
	 */
	public static Map<String,String> getUrlMap(){
		if(urlsMap != null && !urlsMap.isEmpty()){
			return urlsMap;
		}
		urlsMap= new HashMap<String, String>();
		Enumeration e = res.getKeys();
		while(e.hasMoreElements()){
			String key = e.nextElement().toString();
			String value = get(key);
			urlsMap.put(key, value);
			System.out.println(key+"---"+value);
		}
		return urlsMap;
	}
	
	
	public static String get(String key){
		return res.getString(key);
	}
	
	

	
	
}
