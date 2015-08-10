package com.coul.core.domain.base.Repoistory;

import java.util.List;

import com.coul.core.domain.base.entity.BaseModel;

/**
 * 持久化的抽象类
 * @author zengshl   ，采用mybaties的方式只要一个接口映射mybaties的实现即可
       对数据最公共的方法进行抽象
 */
public interface IBaseRepository<T>  {
    
	/**
	 * 添加数据对象
	 * @param t   泛型参数
	 */
    public void addObject(T t);
	
    /**
     * 更新数据对象
     * @param t
     */
	public void updateObject(T t);
	
	/**
	 * 只修改不为NULL的字段
	 * @param t  泛型对象
	 */
	public void updateByNotNull(T t); 	
	
	/**
	 * 根据主键删除的操作
	 * @param id   主键ID
	 */
	public void deleteObject(Object id);
	
    /**
     * 分页获取根据查询对象获取分页的条数
     * @param model
     * @return
     */
	public int queryByCount(BaseModel model);
	
	/**
	 * 分页获取记录集结果
	 * @param model
	 * @return
	 */
	public List<T> queryByList(BaseModel model);
	
	/**
	 * 根据ID获取实体对象
	 * @param id
	 * @return
	 */
	public T queryById(Object id);
	
	public List<T> queryById(BaseModel model);
}
