package com.coul.config.service;

import com.coul.common.exception.DaoAccessException;
import com.coul.common.model.ResultVo;
import com.coul.config.entity.Template;
import com.coul.core.base.condition.Condition;
import com.coul.core.control.action.PagingGridData;
import com.coul.core.control.action.PagingRequestData;
import com.coul.core.domain.base.service.IBaseServie;
import com.coul.core.domain.db.Entity;
import com.coul.core.domain.model.DataStore;
import com.coul.core.domain.model.PagingParameter;

/**
 * 
 * @author zengshl
 *
 * @param <T>
 */
public interface ITemplateService extends IBaseServie<Template>{
	
	/**
	 * 查询分页的数据结果--jQGrid自己封装的查询风格
	 * @param cn
	 * @param page
	 * @return
	 * @throws DaoAccessException 
	 */
	public  PagingGridData<Template>  queryTemplateDate(PagingRequestData page) throws DaoAccessException;

	
	/**
	 * 
	 * @param page
	 * @return
	 * @throws DaoAccessException 
	 */
	public PagingGridData<Template> queryTemplate(PagingRequestData page) throws DaoAccessException;
	
	
	

}
