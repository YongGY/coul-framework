package com.coul.core.domain.base.service.impl;

import java.util.List;

import com.coul.core.domain.base.Repoistory.BaseMapper;
import com.coul.core.domain.base.Repoistory.IBaseRepository;
import com.coul.core.domain.base.entity.BaseModel;
import com.coul.core.domain.base.service.IGenericService;

/**
 * 业务抽象类
 * @author zengshl
 *
 * @param <T>
 */
public abstract class AbstractGenericService<T> implements IGenericService<T> {
   
	 
	/**
	 * 定义一个抽象类进行实例化--对接的业务对象，集成需要重写该方法的使用
	 * @return
	 */
	public abstract IBaseRepository<T> getRepository();

	
	
	/**
	 * 
	 */
	public void addObject(T t)  throws Exception{
		getRepository().addObject(t);
	}
	
	
	/**
	 * 
	 */
	public void updateObject(T t)  throws Exception{
		getRepository().updateObject(t);
	}
	
	
	/**
	 * 
	 */
	public void updateByNotNull(T t){
		getRepository().updateByNotNull(t);
	}
	
	
	/**
	 * 
	 */
	public void deleteObject(Object... ids) throws Exception{
		if(ids == null || ids.length < 1){
			return;
		}
		for(Object id : ids ){
			getRepository().deleteObject(id);
		}
	}
	
	
	/**
	 * 
	 */
	public int queryByCount(BaseModel model)throws Exception{
		return getRepository().queryByCount(model);
	}
	
	
	/**
	 * 
	 */
	public List<T> queryByList(BaseModel model) throws Exception{
		Integer rowCount = queryByCount(model);
		model.getPager().setRowCount(rowCount);
		return getRepository().queryByList(model);
	}	

	
	/**
	 * 
	 */
	public T queryById(Object id) throws Exception{
		return getRepository().queryById(id);
	}
	
	/**
	 * 
	 */
	public List<T> queryById(BaseModel model) throws Exception{
		return getRepository().queryById(model);
	}
}
