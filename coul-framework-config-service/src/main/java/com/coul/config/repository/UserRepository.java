package com.coul.config.repository;

import com.coul.common.exception.DaoAccessException;
import com.coul.config.entity.User;
import com.coul.core.base.condition.Condition;
import com.coul.core.control.action.RequestData;
import com.coul.core.domain.base.entity.EntityDao;
import com.coul.core.domain.db.Entity;
import com.coul.core.domain.model.DataStore;
import com.coul.core.domain.model.PagingParameter;

/**
 * 
 *
 * @created：2014-02-12        ---springJdbcUser
 * @author wangk
 */
public interface UserRepository extends EntityDao<User> {
	
	
	/**
	 * 根据查询条件查询分页的数据信息
	 * @param con
	 * @return
	 */
	public   DataStore<User>  queryUser(Condition con);
	
	

	/**
	 * 查询分页的数据结果
	 * @param cn
	 * @param page
	 * @return
	 * @throws DaoAccessException 
	 */
	public  DataStore<User>  queryUserDate(Condition cn , PagingParameter page ) throws DaoAccessException;
	
	
	
	/**
	 * 普通的查询拼接操作数据结构
	 * @param data
	 * @param page
	 * @return
	 * @throws DaoAccessException
	 */
	public DataStore<User>  queryUser(RequestData data , PagingParameter page) throws DaoAccessException;
	
	

}
