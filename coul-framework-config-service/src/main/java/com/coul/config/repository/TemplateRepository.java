package com.coul.config.repository;

import com.coul.common.exception.DaoAccessException;
import com.coul.config.entity.Template;
import com.coul.core.base.condition.Condition;
import com.coul.core.control.action.RequestData;
import com.coul.core.domain.base.entity.EntityDao;
import com.coul.core.domain.db.Entity;
import com.coul.core.domain.model.DataStore;
import com.coul.core.domain.model.PagingParameter;

/**
 * 
 *
 * @created：2014-02-12        ---springJdbcTemplate
 * @author wangk
 */
public interface TemplateRepository extends EntityDao<Template> {
	
	
	/**
	 * 根据查询条件查询分页的数据信息
	 * @param con
	 * @return
	 */
	public   DataStore<Template>  queryTemplate(Condition con);
	
	

	/**
	 * 查询分页的数据结果
	 * @param cn
	 * @param page
	 * @return
	 * @throws DaoAccessException 
	 */
	public  DataStore<Template>  queryTemplateDate(Condition cn , PagingParameter page ) throws DaoAccessException;
	
	
	
	/**
	 * 普通的查询拼接操作数据结构
	 * @param data
	 * @param page
	 * @return
	 * @throws DaoAccessException
	 */
	public DataStore<Template>  queryTemplate(RequestData data , PagingParameter page) throws DaoAccessException;
	
	

}
