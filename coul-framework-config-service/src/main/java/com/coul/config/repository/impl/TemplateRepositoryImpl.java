package com.coul.config.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.coul.common.exception.DaoAccessException;
import com.coul.config.entity.Template;
import com.coul.config.entity.User;
import com.coul.config.repository.TemplateRepository;
import com.coul.core.base.condition.Condition;
import com.coul.core.control.action.RequestData;
import com.coul.core.domain.base.entity.impl.EntityDaoSupport;
import com.coul.core.domain.model.DataStore;
import com.coul.core.domain.model.PagingParameter;

/**
 * 
 *
 * @created：2014-02-12
 * @author wangk
 */
@Repository("templateRepositoryImpl")
public class TemplateRepositoryImpl  extends EntityDaoSupport<Template> implements TemplateRepository {

	@Override
	public DataStore<Template> queryTemplate(Condition con) {
		// TODO Auto-generated method stub
		try {
			//相关的查询脚本填写的过程
			StringBuilder  sb = new  StringBuilder();
			sb.append("SELECT  *  FROM  TEMPLATE  WHERE  "); //.....的数据脚本的操作
			Template  temp =  this.get(1);
			System.out.println(temp.getName());
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public DataStore<Template> queryTemplateDate(Condition cn,
			PagingParameter page) throws DaoAccessException {       
		StringBuffer  sb = new  StringBuffer();
		sb.append("SELECT  *  FROM  TEMPLATE  ");
		if(cn != null && cn.toSqlString().length() > 0){
			sb.append("WHERE " + cn.toSqlString());	
			
			return this.query(sb.toString(), page,cn.getParameters());   
		}
		//判断是否存在查询条件
		
		DataStore<Template> data =  this.query(sb.toString(), page);   		
		return data;
	}
	
	@Override
	public DataStore<Template>  queryTemplate(RequestData data , PagingParameter page) throws DaoAccessException{
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT  *   FROM  TEMPLATE  WHERE  1 = 1");
		List<Object>   list = new  ArrayList<Object>();
		//自己拼装SQL语句的写法了，由脚本的业务来控制
		if(data != null){
			Template  temp = data.toBean(Template.class);
			if(temp.getName() != null && temp.getName().length() > 0){
				sb.append("  AND  NAME  like  '%"+temp.getName()+"%' ");
				//list.add(temp.getName());
				
			}	
			if(temp.getVersion() != null && temp.getVersion().length() > 0){			
				sb.append(" AND  VERSION  = ?");
				list.add(temp.getVersion());
			}
		}
		DataStore<Template>  ds = this.query(sb.toString(), page, list);
	
		return ds;
	}
	

}
