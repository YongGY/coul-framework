package com.coul.core.domain.base.service;

import com.coul.common.model.ResultVo;
import com.coul.core.base.condition.Condition;
import com.coul.core.domain.db.Entity;

/**
 * 统一的服务公共方法接口定义   --先写这些后续在定义添加更多
 * @author zengshl
 *
 */
public interface IBaseServie<T extends Entity>{
	
	/**
	 * 保存实体操作
	 * @param entity
	 * @return
	 */
	public  ResultVo   save(T entity);
	
	
	/**
	 * 根据ID主键删除相关记录信息
	 * @param id
	 * @return
	 */
	public  ResultVo   delete(Long  id);
	
	
	/**
	 * 更新实体数据信息
	 * @param entity   
	 * @return
	 */
	public  ResultVo   update(T entity);
	
	
	/**
	 * 根据查询条件查询获取相关记录信息
	 * @return
	 */
	public  ResultVo   queryPageInfo(Condition con);
	

}
