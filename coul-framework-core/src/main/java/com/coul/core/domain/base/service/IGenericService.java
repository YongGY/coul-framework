package com.coul.core.domain.base.service;

import java.util.List;

import com.coul.core.domain.base.entity.BaseModel;

/**
 * 业务逻辑的实现接口  
 * @author zengshl
 *
 * @param <T>
 */
public interface IGenericService<T> {
  
	/**
	 * 
	 * @param t
	 * @throws Exception
	 */
	public void addObject(T t)  throws Exception;
	
	/**
	 * 
	 * @param t
	 * @throws Exception
	 */
	public void updateObject(T t)  throws Exception;
	
	/**
	 * 
	 * @param t
	 */
	public void updateByNotNull(T t);
	
	/**
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteObject(Object... ids) throws Exception;
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int queryByCount(BaseModel model)throws Exception;
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<T> queryByList(BaseModel model) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T queryById(Object id) throws Exception;
	
	public List<T> queryById(BaseModel model) throws Exception;
}
