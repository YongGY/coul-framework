package com.coul.core.domain.base.Repoistory.impl;
//package com.ffcs.atte.core.domain.base.mapper.impl;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.ffcs.atte.core.domain.base.mapper.IBaseRepository;
//import com.ffcs.atte.core.domain.base.repositories.ClassSpringRepository;
//
//public abstract class AbstractBaseRepository extends ClassSpringRepository
//		implements IBaseRepository {
//
//	private Map<String, String> sqlMap = new HashMap<String, String>();
//
//	/**
//	 * 向仓库注册SQL语句。
//	 * 
//	 * @param sqlName
//	 *            SQL语句名称
//	 * @param sql
//	 *            SQL语句
//	 */
//	protected void registerSql(String sqlName, String sql) {
//		this.sqlMap.put(sqlName, sql);
//	}
//	
//    /**
//     * 根据SQL名称，获取对应的SQL语句。
//     * 
//     * @param sqlName SQL语句名称
//     * @return SQL语句
//     */
//    protected String getSqlByName(String sqlName) {
//        String hql = this.sqlMap.get(sqlName);
//        if (hql == null) {
//        	//需抛出自定义异常
//        }
//        return hql;
//    }
//
//}
