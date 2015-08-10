package com.coul.config.dubbo;

/**
 * dubbo对外提供的API接口----
 * 
 * @author zengshl
 *
 */
public interface TestRegistryService {
	
	   /**
	    * 服务名称
	    * @param name
	    * @return
	    */
	   public String hello(String name);
}
