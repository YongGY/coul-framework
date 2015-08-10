package com.coul.config.api.impl;

import org.springframework.stereotype.Service;

import com.coul.config.dubbo.TestRegistryService;

/**
 * 服务接口的是实现
 * @author zengshl
 *
 */
@Service("testRegistryService")
public class TestRegistryServiceImpl implements TestRegistryService {
	public String hello(String name) {	
		return "hello"+name;
	}
}
