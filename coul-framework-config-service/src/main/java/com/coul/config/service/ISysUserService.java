package com.coul.config.service;

import com.coul.config.entity.SysUser;
import com.coul.core.domain.base.service.IGenericService;

/**
 * 业务逻辑层
 * @author zengshl
 *
 * @param <T>
 */
public interface ISysUserService<T> extends IGenericService<T>{
	
	public void delete(Object[] ids) throws Exception;
	
	public SysUser queryLogin(String email,String pwd);
	
	public int getUserCountByEmail(String email);
	
	
}
