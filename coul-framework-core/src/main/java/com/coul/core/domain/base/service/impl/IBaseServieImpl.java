package com.coul.core.domain.base.service.impl;


import com.coul.common.model.ResultVo;
import com.coul.core.base.condition.Condition;
import com.coul.core.domain.base.entity.EntityDao;
import com.coul.core.domain.base.service.IBaseServie;
import com.coul.core.domain.db.Entity;

public abstract class IBaseServieImpl<T extends Entity>
   implements IBaseServie<T>{
	
	
	/**
	 * 定义一个抽象类进行实例化--对接的业务对象，集成需要重写该方法的使用
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public abstract EntityDao getEntityDao();


	@SuppressWarnings("unchecked")
	@Override
	public ResultVo save(T entity) {
		ResultVo  rs = new  ResultVo();		
		try {
			this.getEntityDao().save(entity);
			rs.setSuccessful(true);
			rs.setMessage("保存数据成功！！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultVo delete(Long id) {
		ResultVo  rs = new  ResultVo();
		try {
			this.getEntityDao().delete(id);
			rs.setSuccessful(true);
			rs.setMessage("删除数据成功！！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultVo update(T entity) {
		ResultVo  rs = new  ResultVo();
		try {
			this.getEntityDao().update(entity);
			rs.setSuccessful(true);
			rs.setMessage("更新数据成功！！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultVo queryPageInfo(Condition con) {
		// TODO Auto-generated method stub
		return null;
	}

}
