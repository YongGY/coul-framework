package com.coul.core.domain.base.Repoistory.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.coul.common.pagehelper.PageInfo;
import com.coul.common.utils.spring.ApplicationContextUtil;
import com.coul.common.utils.type.StringUtil;
import com.coul.core.domain.base.Repoistory.BaseDaoHibernate;

/**
 * @版权：福富软件 版权所有 (c) 2007
 * @文件：com.ffcs.crm.common.dao.hibernate.BaseDaoHibernate.java
 * @所含类：BaseDaoHibernate
 * @author: wuq
 * @version: V1.0
 * @see:
 * @创建日期：2007-9-13
 * @功能说明：
 * @修改记录： 
 * =============================================================<br>
 * 日期:2007-9-13 wuq 创建
 * =============================================================<br>
 */
@SuppressWarnings("unchecked")
public class BaseDaoHibernateImpl extends HibernateDaoSupport implements BaseDaoHibernate {
    /**
     * log Log
     */
    protected final Log log             = LogFactory.getLog(this.getClass());
    
    /**
     * 强制分页开关,-1为不控制
     */
    public static int   forcePageNum    = -1;
    
    /**
     * 分页的最大条数，如果传的参数超过该值，则强制设为此值，-1为不控制
     */
    public static int   forceMaxPageNum = 500;
    
    
//    /**
//     * add  zengshl  部分HIBERNATE采用JDBC的方式实现查询操作，目前可采取springJdbc 
//     */
//    @Autowired
//    private JdbcTemplate         jdbcTemplate;
//    
//    
//    /*
//     * */
//    public JdbcTemplate getJdbcTemplate() {
//        if (this.jdbcTemplate == null) {
//            this.jdbcTemplate = ApplicationContextUtil.getBean("jdbcTemplate");
//        }
//        return this.jdbcTemplate;
//    }
    
    /**
     * @param o
     *            Object
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-13 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public void saveObject(final Object o) {
        this.getHibernateTemplate().saveOrUpdate(o);
    }
    
    /**
     * @param o
     *            Object
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-13 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public void addObject(final Object o) {
        this.getHibernateTemplate().save(o);
    }
    
    /**
     * @param o
     *            类对象
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-13 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public void updateObject(final Object o) {
        this.getHibernateTemplate().update(o);
    }
    
    /**
     * @param clazz
     *            参数类
     * @param id
     *            参数类
     * @return Object
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-13 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
	public Object getObject(final Class clazz, final Serializable id) {
        final Object o = this.getHibernateTemplate().get(clazz, id);
        
        if (o == null) {
            throw new ObjectRetrievalFailureException(clazz, id);
        }
        
        return o;
    }
    
    /**
     * @param clazz
     *            参数类
     * @param obj
     *            类对象
     * @return List
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-13 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public List getObjects(final Class clazz, final Object obj) {
        
        // return getHibernateTemplate().loadAll(clazz);
        /*
         * Remove the line above and uncomment this code block if you want to
         * use Hibernate's Query by Example API.
         */

        if (obj == null) {
            return this.getHibernateTemplate().loadAll(clazz);
            // return getHibernateTemplate().find("from "+clazz.getName());
        } else {
            // filter on properties set in the saleTask
            final HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session)
                    throws HibernateException {
                    Example ex = Example.create(obj).excludeNone()
                        .excludeZeroes().ignoreCase().enableLike(
                            MatchMode.ANYWHERE);
                    return session.createCriteria(obj.getClass()).add(ex)
                        .list();
                }
            };
            return (List) this.getHibernateTemplate().execute(callback);
        }
        
    }
    
    /**
     * @param clazz
     *            参数类
     * @param id
     *            参数类
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-13 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public void removeObject(final Class clazz, final Serializable id) {
        this.getHibernateTemplate().delete(this.getObject(clazz, id));
    }
    
    /**
     * @param hql
     *            查询语句
     * @return List
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-4-13 chenjun 创建方法，并实现其功能 根据HQL语句查询对象集合
     *        ==============================================================<br>
     */
    public List findByHql(final String hql) {
        if (BaseDaoHibernateImpl.forcePageNum < 1) {
            // 如果小于1，则不强制进行分页
            return this.getHibernateTemplate().find(hql);
        } else {
            // 如果大于等于1,则默认返回第一页的信息
            return this.findFirstPageInfoByHQLAndParams(hql, null, 1,
                BaseDaoHibernateImpl.forcePageNum).get_dataList();
        }
    }
    
    
    public Integer executeUpdateByHqlAndparams(final String sqlStr,
			final List params){
    	return (Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(final Session session)
							throws HibernateException, SQLException {
						   final Query query = session.createQuery(sqlStr);
	                        
	                        // 判断是否有传入参数
	                        if (params != null) {
	                            for (int k = 0; k < params.size(); k++) {
	                                
	                                final Object param = params.get(k);
	                                
	                                if (param instanceof Integer) {
	                                    query.setInteger(k, ((Integer) params
	                                        .get(k)).intValue());
	                                }
	                                if (param instanceof String) {
	                                    query
	                                        .setString(k, params.get(k).toString());
	                                }
	                                if (param instanceof Long) {
	                                    query.setLong(k, ((Long) params.get(k))
	                                        .longValue());
	                                }
	                                if (param instanceof Date) {
	                                    query.setDate(k, ((Date) params.get(k)));
	                                }
	                            }
	                        }
	                        return query.executeUpdate();
					}
				});
    }
    
    /**
     * 
     * 通过jdbc的SQL操作数据
     * @param sql
     *            jdbcsql
     * @param params
     *            参数
     * @return List
     * @author: fanggq
     * @修改记录：
     *  ==============================================================<br>
     *  日期:2010-3-25 fanggq 创建方法，并实现其功能
     *  ==============================================================<br>
     */
	public Integer executeUpdateByJdbcAndParams(final String sqlStr,
			final List params) {
		return (Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(final Session session)
							throws HibernateException, SQLException {
						final Query query = session.createSQLQuery(sqlStr);

						// 判断是否有传入参数
						if (params != null) {
							for (int k = 0; k < params.size(); k++) {

								final Object param = params.get(k);
                                //避免NULL值导致数据无法插入
								if(param == null ){
									query.setString(k, "");
									query.setParameter(k, "");
								}
								if (param instanceof Integer) {
									query.setInteger(k, ((Integer) params
											.get(k)).intValue());
								}
								if (param instanceof String) {
									query
											.setString(k, params.get(k)
													.toString());
								}
								if (param instanceof Long) {
									query.setLong(k, ((Long) params.get(k))
											.longValue());
								}
								if (param instanceof Date) {
									query.setDate(k, ((Date) params.get(k)));
								}
								if(param instanceof byte[]){
									query.setBinary(k, (byte[])params.get(k));
								}
							}
						}
						return query.executeUpdate();
					}
				});
	}
    
    
    /**
     * 根据hibernate的Hql语句和参数查询出分页信息
     * 
     * @param sql
     *            查询语句
     * @param params
     *            查询参数
     * @param currentPage
     *            当前页
     * @param perPageNum
     *            每页记录数
     * @return PageInfo
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-3-29 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public PageInfo findPageInfoByHQLAndParams(final String sql,
        final List params, final int currentPage, final int perPageNum) {
        
        return (PageInfo) this.getHibernateTemplate().execute(
            new HibernateCallback() {
                public Object doInHibernate(final Session session)
                    throws HibernateException, SQLException {
                    final PageInfo pageInfo = new PageInfo();
                    List list = new ArrayList();
                    
                    String sqlStr = sql;
                    
                    int currentPageNum = currentPage;
                    int perPageSize = perPageNum;
                    
                    if (BaseDaoHibernateImpl.forceMaxPageNum >= 1) {
                        // 如果有强制控制返回的最大分页记录数
                        if (perPageSize > BaseDaoHibernateImpl.forceMaxPageNum) {
                            perPageSize = BaseDaoHibernateImpl.forceMaxPageNum;
                        }
                    }
                    
                    if (currentPageNum == 0) {
                        currentPageNum = 1;
                    }
                    
                    sqlStr = sqlStr.replaceAll("from ", "FROM ");
                    sqlStr = sqlStr.replaceAll("From ", "FROM ");
                    
                    final String totalCountSql = "select count(*) "
                        + sqlStr.substring(sqlStr.indexOf("FROM "));
                    
                    final Query totalQuery = session.createQuery(totalCountSql);
                    // 判断是否有传入参数
                    if (params != null) {
                        for (int k = 0; k < params.size(); k++) {
                            final Object param = params.get(k);
                            
                            if (param instanceof Integer) {
                                totalQuery.setInteger(k, ((Integer) params
                                    .get(k)).intValue());
                            }
                            if (param instanceof String) {
                                totalQuery.setString(k, params.get(k)
                                    .toString());
                            }
                            if (param instanceof Long) {
                                totalQuery.setLong(k, ((Long) params.get(k))
                                    .longValue());
                            }
                            if (param instanceof Date) {
                                totalQuery.setDate(k, ((Date) params.get(k)));
                            }
                        }
                    }
                    
                    final List countList = totalQuery.list();
                    
                    // 获取总记录数
                    int totalCounts = 0;
                    
                    // 以下是处理sql语句中有"group by"的时候取总记录数就是取list的个数
                    if (sqlStr.indexOf("Group by") == -1
                        && sqlStr.indexOf("group by") == -1
                        && sqlStr.indexOf("Group By") == -1) {
                        totalCounts = ((Long) countList.get(0)).intValue();
                    } else {
                        totalCounts = list.size();
                    }
                    
                    if (perPageSize == 0) {
                        perPageSize = 10;
                    }
                    // 计算总页数
                    int totalPages = totalCounts / perPageSize;
                    totalPages = (totalCounts % perPageSize) > 0 ? (totalPages + 1)
                        : totalPages;
                    
                    final Query query = session.createQuery(sqlStr);
                    
                    // 判断是否有传入参数
                    if (params != null) {
                        for (int k = 0; k < params.size(); k++) {
                            
                            final Object param = params.get(k);
                            
                            if (param instanceof Integer) {
                                query.setInteger(k, ((Integer) params.get(k))
                                    .intValue());
                            }
                            if (param instanceof String) {
                                query.setString(k, params.get(k).toString());
                            }
                            if (param instanceof Long) {
                                query.setLong(k, ((Long) params.get(k))
                                    .longValue());
                            }
                            if (param instanceof Date) {
                                query.setDate(k, ((Date) params.get(k)));
                            }
                        }
                    }
                    
                    // 获取开始记录
                    final int firstNum = currentPageNum * perPageSize
                        - perPageSize;
                    
                    query.setFirstResult(firstNum);
                    query.setMaxResults(perPageSize);
                    
                    // sql=hsql+ " limit " + (pageNo-1)*page_size + ","
                    // +page_size;
                    
                    list = query.list();
                    
                    if (list == null) {
                        list = new ArrayList(0);
                    }
                    // 当取当前页的条数
                    // perPageSize=list.size();
                    
                    pageInfo.set_totalCount(totalCounts);
                    pageInfo.set_totalPageCount(totalPages);
                    pageInfo.set_currentPage(currentPageNum);
                    pageInfo.set_perPageCount(perPageSize);
                    pageInfo.set_dataList(list);
                    
                    return pageInfo;
                }
            });
    }
    
    /**
     * @param sql
     *            查询语句
     * @param currentPage
     *            当前页
     * @param perPageNum
     *            每页记录数
     * @return PageInfo
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-3-29 chenjun 创建方法， 并实现其功能 根据HQL语句分页查询
     *        ==============================================================<br>
     */
    public PageInfo findPageInfoByHQL(final String sql, final int currentPage,
        final int perPageNum) {
        return this.findPageInfoByHQLAndParams(sql, null, currentPage,
            perPageNum);
    }
    
    /**
     * 保存对象，可实现对于已有的对象更新，新的对象增加
     * 
     * @param o
     *            对象类
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-4-14 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public void merge(final Object o) {
        this.getHibernateTemplate().merge(o);
    }
    
    /**
     * @param entities
     *            数据集
     * @author: liujr
     * @修改记录： ==============================================================<br>
     *        日期: 2007-4-11 liujr 创建方法，并实现其功能 从数据库删除指定数据集的记录
     *        ==============================================================<br>
     */
    public void deleteAll(final Collection entities) {
        
        this.getHibernateTemplate().deleteAll(entities);
        
    }
    
    /**
     * 根据JDBCSQL语句查询数据
     * 
     * @param sql
     *            查询语句
     * @return List
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期: 2007-6-8 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public List findListByJDBCSQL(final String sql) {
        if (BaseDaoHibernateImpl.forcePageNum < 1) {
            // 如果小于1，则不强制进行分页
            return (List) this.getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(final Session session)
                        throws HibernateException, SQLException {
                        List list = new ArrayList();
                        final Query query = session.createSQLQuery(sql);
                        list = query.list();
                        return list;
                    }
                });
        } else {
            // 如果大于等于1,则默认返回第一页的信息
            return this.findFirstPageInfoByJDBCSQLAndParams(sql, null, 1,
                BaseDaoHibernateImpl.forcePageNum, false).get_dataList();
        }
    }
    
    /**
     * 获取单一值
     * 
     * @param sql
     * @return
     * @author: zfz
     * @修改记录： ==============================================================<br>
     *        日期:2008-6-12 zfz 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public String getSingleValueByJDBCSQL(final String sql) {
        String result = "";
        final List list = this.findListByJDBCSQL(sql);
        if (list != null && list.size() > 0) {
            result = StringUtil.toString(list.get(0),"");
        }
        return result;
    }
    
    /**
     * 获取单一值
     * 
     * @param sql
     * @return
     * @author: wuq
     * @修改记录： ==============================================================<br>
     *        日期:2009-1-5 wuq 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public String getSingleValueByJDBCAndParamsSQL(final String sql,
        final List params) {
        String result = "";
        final List list = this.findListByJDBCSQLAndParams(sql, params);
        if (list != null && list.size() > 0) {
            result = StringUtil.toString(list.get(0),"");
        }
        return result;
    }
    
    
    /**
     * 获取单一值(获取CLOb的单一值数据,以字符串的格式进行输出)
     * 
     * @param sql
     * @return
     * @author: zengshl
     * @修改记录： ==============================================================<br>
     *        日期:2011-10-10 zengshl 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public String getSingleClobValueByJDBCAndParamsSQL(final String sql,
        final List params) {
        String result = "";
        final List<Object[]> list = this.findListByJDBCSQLAndParams(sql, params);
        if (list != null && list.size() > 0) {
        	System.out.println(list.size());
        	Object  objClob = list.get(0);
            Clob   clobtmp = (Clob)objClob;
            try {
				if(clobtmp == null   ||   clobtmp.length()==0){      
					result   =   "";      
				}else{      
					result = clobtmp.getSubString((long)1,(int)clobtmp.length());          
         }
			} catch (SQLException e) {
				e.printStackTrace();
			}   
        }
        return result;
    }
    
    /**
     * 获取单一值(获取Blob的单一值数据)
     * 
     * @param sql,params
     * @return Blob
     * @author: laiyongmin
     * @修改记录： ==============================================================<br>
     *        日期:2013-05-28 laiyongmin 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public Blob getBlobValueByJDBCAndParam(final String sql,final List<?> params){
    	 return (Blob) this.getHibernateTemplate().execute(
    	            new HibernateCallback() {
    	                public Object doInHibernate(final Session session)
    	                    throws HibernateException, SQLException {
    	                	logger.debug("sql:"+sql);
    	                	 final Query query = session.createSQLQuery(sql);
    	                	 // 判断是否有传入参数
    	                     if (params != null) {
    	                         for (int k = 0; k < params.size(); k++) {
    	                             
    	                             final Object param = params.get(k);
    	                             
    	                             if (param instanceof Integer) {
    	                                 query.setInteger(k, ((Integer) params.get(k))
    	                                     .intValue());
    	                             }
    	                             if (param instanceof String) {
    	                                 query.setString(k, params.get(k).toString());
    	                             }
    	                             if (param instanceof Long) {
    	                                 query.setLong(k, ((Long) params.get(k))
    	                                     .longValue());
    	                             }
    	                             if (param instanceof Date) {
    	                                 query.setDate(k, ((Date) params.get(k)));
    	                             }
    	                         }
    	                     }
    	                     List<?> list= query.list();
    	                     if(null != list && list.size() >0){
    	                    	 Blob blob=(Blob)list.get(0);
    	                    	return blob;
    	                     }
    	                	return null;
    	                }
    	});
    }
    
    
    /**
     * 根据hql查询唯一值
     * 
     * @param hql
     * @return
     * @author: zfz
     * @修改记录： ==============================================================<br>
     *        日期:Sep 4, 2008 zfz 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public String getSingleValueByHql(final String hql) {
        String result = "";
        final List list = this.findByHql(hql);
        if (list != null && list.size() > 0) {
            result = StringUtil.toString(list.get(0),"");
        }
        return result;
    }
    
    /**
     * 根据JDBC SQL语句分页查询
     * 
     * @param sql
     *            查询语句
     * @param params
     *            参数
     * @param currentPage
     *            单前页
     * @param perPageNum
     *            每页记录数
     * @param isGroupby
     *            是否分组查询
     * @return PageInfo
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-6-8 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public PageInfo findPageInfoByJDBCSQLAndParams(final String sql,
        final List params, final int currentPage, final int perPageNum,
        final boolean isGroupby) {
        
        return (PageInfo) this.getHibernateTemplate().execute(
            new HibernateCallback() {
                public Object doInHibernate(final Session session)
                    throws HibernateException, SQLException {
                    final PageInfo pageInfo = new PageInfo();
                    List list = new ArrayList();
                    
                    String sqlStr = sql;
                    
                    int currentPageNum = currentPage;
                    int perPageSize = perPageNum;
                    
                    if (BaseDaoHibernateImpl.forceMaxPageNum >= 1) {
                        // 如果有强制控制返回的最大分页记录数
                        if (perPageSize > BaseDaoHibernateImpl.forceMaxPageNum) {
                            perPageSize = BaseDaoHibernateImpl.forceMaxPageNum;
                        }
                    }
                    
                    if (currentPageNum == 0) {
                        currentPageNum = 1;
                    }
                    
                    sqlStr = sqlStr.replaceAll("from ", "FROM ");
                    sqlStr = sqlStr.replaceAll("From ", "FROM ");
                    
                    final String totalCountSql = " select count(*) from ("+sqlStr+")";
                    // + sqlStr.substring(sqlStr.lastIndexOf("FROM "));
                    
                    final Query totalQuery = session
                        .createSQLQuery(totalCountSql);
                    // 判断是否有传入参数
                    if (params != null) {
                        for (int k = 0; k < params.size(); k++) {
                            final Object param = params.get(k);
                            
                            if (param instanceof Integer) {
                                totalQuery.setInteger(k, ((Integer) params
                                    .get(k)).intValue());
                            }
                            if (param instanceof String) {
                                totalQuery.setString(k, params.get(k)
                                    .toString());
                            }
                            if (param instanceof Long) {
                                totalQuery.setLong(k, ((Long) params.get(k))
                                    .longValue());
                            }
                            if (param instanceof Date) {
                                totalQuery.setDate(k, ((Date) params.get(k)));
                            }
                        }
                    }
                    
                    final List countList = totalQuery.list();
                    
                    // 获取总记录数
                    int totalCounts = 0;
                    
                    // 以下是处理sql语句中有"group by"的时候取总记录数就是取list的个数
                    if (!isGroupby
                        || (sqlStr.indexOf("Group by") == -1
                            && sqlStr.indexOf("group by") == -1 && sqlStr
                            .indexOf("Group By") == -1)) {
                        
                        // 防止countList.size=0时越界访问get(0)
                        if (countList.size() > 0) {
                            final Long l = new Long(countList.get(0).toString());
                            totalCounts = l.intValue();
                        } else {
                            totalCounts = 0;
                        }
                    } else {
                        totalCounts = countList.size();
                    }
                    
                    if (perPageSize == 0) {
                        perPageSize = 10;
                    }
                    // 计算总页数
                    int totalPages = totalCounts / perPageSize;
                    totalPages = (totalCounts % perPageSize) > 0 ? (totalPages + 1)
                        : totalPages;
                    
                    // 获取开始记录
                    final int firstNum = currentPageNum * perPageSize
                    - perPageSize;
                    
                    final int lastNum = firstNum + perPageSize;
                    //组分页的Sql
                    sqlStr = "select * from (select src.*,rownum rn from (" + sqlStr;
                    sqlStr = sqlStr + ") src ) where rn > " +firstNum+ " and rn <= " + lastNum ;
                    
                    final Query query = session.createSQLQuery(sqlStr);
                    
                    // 判断是否有传入参数
                    if (params != null) {
                        for (int k = 0; k < params.size(); k++) {
                            
                            final Object param = params.get(k);
                            
                            if (param instanceof Integer) {
                                query.setInteger(k, ((Integer) params.get(k))
                                    .intValue());
                            }
                            if (param instanceof String) {
                                query.setString(k, params.get(k).toString());
                            }
                            if (param instanceof Long) {
                                query.setLong(k, ((Long) params.get(k))
                                    .longValue());
                            }
                            if (param instanceof Date) {
                                query.setDate(k, ((Date) params.get(k)));
                            }
                        }
                    }
                    
                    
                    //query.setFirstResult(firstNum);
                   // query.setMaxResults(perPageSize);
                    
                    // sql=hsql+ " limit " + (pageNo-1)*page_size + ","
                    // +page_size;
                    
                    list = query.list();
                    
                    if (list == null) {
                        list = new ArrayList(0);
                    }
                    // 当取当前页的条数
                    // perPageSize=list.size();
                    
                    pageInfo.set_totalCount(totalCounts);
                    pageInfo.set_totalPageCount(totalPages);
                    pageInfo.set_currentPage(currentPageNum);
                    pageInfo.set_perPageCount(perPageSize);
                    pageInfo.set_dataList(list);
                    
                    return pageInfo;
                }
            });
    }
    
    /**
     * 根据JDBC SQL语句分页查询
     * 
     * @param sql
     *            查询语句
     * @param currentPage
     *            当前页
     * @param perPageNum
     *            每页记录数
     * @param isGroupby
     *            是否分组
     * @return PageInfo
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-3-29 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public PageInfo findPageInfoByJDBCSQL(final String sql,
        final int currentPage, final int perPageNum, final boolean isGroupby) {
        return this.findPageInfoByJDBCSQLAndParams(sql, null, currentPage,
            perPageNum, isGroupby);
    }
    
    /**
     * 取SEQ
     * 
     * @param seqName
     *            序列名称
     * @return String
     * @author: panchh
     * @修改记录： ==============================================================<br>
     *        日期:2007-6-21 panchh 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public String getSeqNextval(final String seqName) {
        String retStr = "0";
        final String sql = "select " + seqName + ".nextval from dual";
        final List list = this.findListByJDBCSQL(sql);
        if (list != null && list.size() > 0) {
            final Object obj = list.get(0);
            retStr = obj.toString();
        }
        
        return retStr;
    }
    
    /**
     * @param sql
     *            HQL语句
     * @param params
     *            参数
     * @return List
     * @author: liujr
     * @修改记录： ==============================================================<br>
     *        日期:2007-10-29 liujr 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public List findListByHQLAndParams(final String sql, final List params) {
        if (BaseDaoHibernateImpl.forcePageNum < 1) {
            // 如果小于1，则不强制进行分页
            return (List) this.getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(final Session session)
                        throws HibernateException, SQLException {
                        List list = new ArrayList();
                        
                        String sqlStr = sql;
                        
                        sqlStr = sqlStr.replaceAll("from ", "FROM ");
                        sqlStr = sqlStr.replaceAll("From ", "FROM ");
                        final Query query = session.createQuery(sqlStr);
                        
                        // 判断是否有传入参数
                        if (params != null) {
                            for (int k = 0; k < params.size(); k++) {
                                
                                final Object param = params.get(k);
                                
                                if (param instanceof Integer) {
                                    query.setInteger(k, ((Integer) params
                                        .get(k)).intValue());
                                }
                                if (param instanceof String) {
                                    query
                                        .setString(k, params.get(k).toString());
                                }
                                if (param instanceof Long) {
                                    query.setLong(k, ((Long) params.get(k))
                                        .longValue());
                                }
                                if (param instanceof Date) {
                                    query.setDate(k, ((Date) params.get(k)));
                                }
                            }
                        }
                        
                        list = query.list();
                        
                        if (list == null) {
                            list = new ArrayList(0);
                        }
                        
                        return list;
                    }
                });
        } else {
            // 如果大于等于1,则默认返回第一页的信息
            return this.findFirstPageInfoByHQLAndParams(sql, params, 1,
                BaseDaoHibernateImpl.forcePageNum).get_dataList();
        }
    }
    
    /**
     * @param sql
     *            jdbcsql
     * @param params
     *            参数
     * @return List
     * @author: liujr
     * @修改记录： ==============================================================<br>
     *        日期:2007-10-29 liujr 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public List<Object[]> findListByJDBCSQLAndParams(final String sql, final List params) {
        if (BaseDaoHibernateImpl.forcePageNum < 1) {
            // 如果小于1，则不强制进行分页
            return (List) this.getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(final Session session)
                        throws HibernateException, SQLException {
                        List list = new ArrayList();
                        
                        String sqlStr = sql;
                        
                        sqlStr = sqlStr.replaceAll("from ", "FROM ");
                        sqlStr = sqlStr.replaceAll("From ", "FROM ");
                        final Query query = session.createSQLQuery(sqlStr);
                        
                        // 判断是否有传入参数
                        if (params != null) {
                            for (int k = 0; k < params.size(); k++) {
                                
                                final Object param = params.get(k);
                                
                                if (param instanceof Integer) {
                                    query.setInteger(k, ((Integer) params
                                        .get(k)).intValue());
                                }
                                if (param instanceof String) {
                                    query
                                        .setString(k, params.get(k).toString());
                                }
                                if (param instanceof Long) {
                                    query.setLong(k, ((Long) params.get(k))
                                        .longValue());
                                }
                                if (param instanceof Date) {
                                    query.setDate(k, ((Date) params.get(k)));
                                }
                            }
                        }
                        
                        list = query.list();
                        
                        if (list == null) {
                            list = new ArrayList(0);
                        }
                        
                        return list;
                    }
                });
        } else {
            // 如果大于等于1,则默认返回第一页的信息
            return this.findFirstPageInfoByJDBCSQLAndParams(sql, params, 1,
                BaseDaoHibernateImpl.forcePageNum, false).get_dataList();
        }
    }
    
    /**
     * 获取字段comment
     * 
     * @param owner
     *            表空间所有者
     * @param tableName
     *            表名
     * @param colName
     *            列名
     * @return 字段中文名
     * @throws ManagerException
     *             ManagerException
     * @author: panchh
     * @修改记录： ==============================================================<br>
     *        日期:Dec 3, 2007 panchh 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public String getColumnChineseName(final String owner,
        final String tableName, final String colName) {
        String comment = "";
        String sql = "select comments from all_col_comments";
        if (StringUtil.isEmptyString(owner)) {
            sql += " where owner = 'CRM' ";
        } else {
            sql += " where owner = '" + owner + "'";
        }
        sql += " and table_name = '" + tableName + "'";
        sql += " and column_name ='" + colName + "'";
        final List list = this.findListByJDBCSQL(sql);
        for (int i = 0; list != null && i < list.size(); i++) {
            final Object obj = list.get(i);
            comment = StringUtil.toString(obj,"");
        }
        return comment;
    }
    
    /**
     * 获取单一值HQL,绑定变量
     * 
     * @param hql
     * @param params
     * @return
     * @author: yejb
     * @修改记录： ==============================================================<br>
     *        日期:2008-12-31 yejb 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public String getSingleValueByHqlAndParams(final String hql,
        final List params) {
        String result = "";
        final List list = this.findListByHQLAndParams(hql, params);
        if (list != null && list.size() > 0) {
            result = StringUtil.toString(list.get(0),"");
        }
        return result;
    }
    
    /**
     * 获取单一值，绑定变量
     * 
     * @param sql
     * @param params
     * @return
     * @author: yejb
     * @修改记录： ==============================================================<br>
     *        日期:2008-12-31 yejb 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public String getSingleValueByJDBCSQLAndParams(final String sql,
        final List params) {
        String result = "";
        final List list = this.findListByJDBCSQLAndParams(sql, params);
        if (list != null && list.size() > 0) {
            result = StringUtil.toString(list.get(0),"");
        }
        return result;
    }
    
    /**
     * 根据hibernate的Hql语句和参数查询出分页信息
     * 
     * @param sql
     *            查询语句
     * @param params
     *            查询参数
     * @param currentPage
     *            当前页
     * @param perPageNum
     *            每页记录数
     * @return PageInfo
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-3-29 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public PageInfo findFirstPageInfoByHQLAndParams(final String sql,
        final List params, final int currentPage, final int perPageNum) {
        
        return (PageInfo) this.getHibernateTemplate().execute(
            new HibernateCallback() {
                public Object doInHibernate(final Session session)
                    throws HibernateException, SQLException {
                    final PageInfo pageInfo = new PageInfo();
                    List list = new ArrayList();
                    
                    final String sqlStr = sql;
                    
                    int currentPageNum = currentPage;
                    int perPageSize = perPageNum;
                    
                    if (BaseDaoHibernateImpl.forceMaxPageNum >= 1) {
                        // 如果有强制控制返回的最大分页记录数
                        if (perPageSize > BaseDaoHibernateImpl.forceMaxPageNum) {
                            perPageSize = BaseDaoHibernateImpl.forceMaxPageNum;
                        }
                    }
                    
                    if (currentPageNum == 0) {
                        currentPageNum = 1;
                    }
                    
                    if (perPageSize == 0) {
                        perPageSize = 10;
                    }
                    
                    final Query query = session.createQuery(sqlStr);
                    
                    // 判断是否有传入参数
                    if (params != null) {
                        for (int k = 0; k < params.size(); k++) {
                            
                            final Object param = params.get(k);
                            
                            if (param instanceof Integer) {
                                query.setInteger(k, ((Integer) params.get(k))
                                    .intValue());
                            }
                            if (param instanceof String) {
                                query.setString(k, params.get(k).toString());
                            }
                            if (param instanceof Long) {
                                query.setLong(k, ((Long) params.get(k))
                                    .longValue());
                            }
                            if (param instanceof Date) {
                                query.setDate(k, ((Date) params.get(k)));
                            }
                        }
                    }
                    
                    // 获取开始记录
                    final int firstNum = currentPageNum * perPageSize
                        - perPageSize;
                    
                    query.setFirstResult(firstNum);
                    query.setMaxResults(perPageSize);
                    
                    // sql=hsql+ " limit " + (pageNo-1)*page_size + ","
                    // +page_size;
                    
                    list = query.list();
                    
                    if (list == null) {
                        list = new ArrayList(0);
                    }
                    // 当取当前页的条数
                    // perPageSize=list.size();
                    
                    // pageInfo.setTotalCount(totalCounts);
                    // pageInfo.setTotalPageCount(totalPages);
                    // pageInfo.setCurrentPage(currentPageNum);
                    // pageInfo.setPerPageCount(perPageSize);
                    pageInfo.set_dataList(list);
                    
                    return pageInfo;
                }
            });
    }
    
    /**
     * @param sql
     * @param params
     * @param currentPage
     * @param perPageNum
     * @param isGroupby
     * @return
     * @author: wuq
     * @修改记录： ==============================================================<br>
     *        日期:Mar 20, 2009 wuq 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public PageInfo findFirstPageInfoByJDBCSQLAndParams(final String sql,
        final List params, final int currentPage, final int perPageNum,
        final boolean isGroupby) {
        
        return (PageInfo) this.getHibernateTemplate().execute(
            new HibernateCallback() {
                public Object doInHibernate(final Session session)
                    throws HibernateException, SQLException {
                    final PageInfo pageInfo = new PageInfo();
                    List list = new ArrayList();
                    
                    final String sqlStr = sql;
                    
                    int currentPageNum = currentPage;
                    int perPageSize = perPageNum;
                    
                    if (BaseDaoHibernateImpl.forceMaxPageNum >= 1) {
                        // 如果有强制控制返回的最大分页记录数
                        if (perPageSize > BaseDaoHibernateImpl.forceMaxPageNum) {
                            perPageSize = BaseDaoHibernateImpl.forceMaxPageNum;
                        }
                    }
                    
                    if (currentPageNum == 0) {
                        currentPageNum = 1;
                    }
                    
                    if (perPageSize == 0) {
                        perPageSize = 10;
                    }
                    
                    final Query query = session.createSQLQuery(sqlStr);
                    
                    // 判断是否有传入参数
                    if (params != null) {
                        for (int k = 0; k < params.size(); k++) {
                            
                            final Object param = params.get(k);
                            
                            if (param instanceof Integer) {
                                query.setInteger(k, ((Integer) params.get(k))
                                    .intValue());
                            }
                            if (param instanceof String) {
                                query.setString(k, params.get(k).toString());
                            }
                            if (param instanceof Long) {
                                query.setLong(k, ((Long) params.get(k))
                                    .longValue());
                            }
                            if (param instanceof Date) {
                                query.setDate(k, ((Date) params.get(k)));
                            }
                        }
                    }
                    
                    // 获取开始记录
                    final int firstNum = currentPageNum * perPageSize
                        - perPageSize;
                    
                    query.setFirstResult(firstNum);
                    query.setMaxResults(perPageSize);
                    
                    // sql=hsql+ " limit " + (pageNo-1)*page_size + ","
                    // +page_size;
                    
                    list = query.list();
                    
                    if (list == null) {
                        list = new ArrayList(0);
                    }
                    // 当取当前页的条数
                    // perPageSize=list.size();
                    
                    // pageInfo.setTotalCount(totalCounts);
                    // pageInfo.setTotalPageCount(totalPages);
                    // pageInfo.setCurrentPage(currentPageNum);
                    // pageInfo.setPerPageCount(perPageSize);
                    pageInfo.set_dataList(list);
                    
                    return pageInfo;
                }
            });
    }
    
    /**
     * @param sql
     * @param zz
     * @return
     * @author: nip
     * @修改记录： ==============================================================<br>
     *        日期:May 22, 2009 nip 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public List findListByJDBCSQL(final String sql, final Class zz) {
        
        return (List) this.getHibernateTemplate().execute(
            new HibernateCallback() {
                public Object doInHibernate(final Session session)
                    throws HibernateException, SQLException {
                    List list = new ArrayList();
                    final Query query = session.createSQLQuery(sql).addEntity(
                        zz);
                    list = query.list();
                    return list;
                }
            });
    }
    
    /**
     * @param sql
     * @param params
     * @param currentPage
     * @param perPageNum
     * @param isGroupby
     * @param zz
     * @return
     * @author: nip
     * @修改记录： ==============================================================<br>
     *        日期:May 22, 2009 nip 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public PageInfo findPageInfoByJDBCSQLAndParams(final String sql,
        final List params, final int currentPage, final int perPageNum,
        final boolean isGroupby, final Class zz) {
        
        return (PageInfo) this.getHibernateTemplate().execute(
            new HibernateCallback() {
                public Object doInHibernate(final Session session)
                    throws HibernateException, SQLException {
                    final PageInfo pageInfo = new PageInfo();
                    List list = new ArrayList();
                    
                    String sqlStr = sql;
                    
                    int currentPageNum = currentPage;
                    int perPageSize = perPageNum;
                    
                    if (currentPageNum == 0) {
                        currentPageNum = 1;
                    }
                    
                    sqlStr = sqlStr.replaceAll("from ", "FROM ");
                    sqlStr = sqlStr.replaceAll("From ", "FROM ");
                    
                    final String totalCountSql = "select count(*) "
                        + sqlStr.substring(sqlStr.indexOf("FROM "));
                    // + sqlStr.substring(sqlStr.lastIndexOf("FROM "));
                    
                    final Query totalQuery = session
                        .createSQLQuery(totalCountSql);
                    // 判断是否有传入参数
                    if (params != null) {
                        for (int k = 0; k < params.size(); k++) {
                            final Object param = params.get(k);
                            
                            if (param instanceof Integer) {
                                totalQuery.setInteger(k, ((Integer) params
                                    .get(k)).intValue());
                            }
                            if (param instanceof String) {
                                totalQuery.setString(k, params.get(k)
                                    .toString());
                            }
                            if (param instanceof Long) {
                                totalQuery.setLong(k, ((Long) params.get(k))
                                    .longValue());
                            }
                            if (param instanceof Date) {
                                totalQuery.setDate(k, ((Date) params.get(k)));
                            }
                        }
                    }
                    
                    final List countList = totalQuery.list();
                    
                    // 获取总记录数
                    int totalCounts = 0;
                    
                    // 以下是处理sql语句中有"group by"的时候取总记录数就是取list的个数
                    if (!isGroupby
                        || (sqlStr.indexOf("Group by") == -1
                            && sqlStr.indexOf("group by") == -1 && sqlStr
                            .indexOf("Group By") == -1)) {
                        
                        // 防止countList.size=0时越界访问get(0)
                        if (countList.size() > 0) {
                            final Long l = new Long(countList.get(0).toString());
                            totalCounts = l.intValue();
                        } else {
                            totalCounts = 0;
                        }
                    } else {
                        totalCounts = countList.size();
                    }
                    
                    if (perPageSize == 0) {
                        perPageSize = 10;
                    }
                    // 计算总页数
                    int totalPages = totalCounts / perPageSize;
                    totalPages = (totalCounts % perPageSize) > 0 ? (totalPages + 1)
                        : totalPages;
                    
                    final Query query = session.createSQLQuery(sqlStr)
                        .addEntity(zz);
                    
                    // 判断是否有传入参数
                    if (params != null) {
                        for (int k = 0; k < params.size(); k++) {
                            
                            final Object param = params.get(k);
                            
                            if (param instanceof Integer) {
                                query.setInteger(k, ((Integer) params.get(k))
                                    .intValue());
                            }
                            if (param instanceof String) {
                                query.setString(k, params.get(k).toString());
                            }
                            if (param instanceof Long) {
                                query.setLong(k, ((Long) params.get(k))
                                    .longValue());
                            }
                            if (param instanceof Date) {
                                query.setDate(k, ((Date) params.get(k)));
                            }
                        }
                    }
                    
                    // 获取开始记录
                    final int firstNum = currentPageNum * perPageSize
                        - perPageSize;
                    
                    query.setFirstResult(firstNum);
                    query.setMaxResults(perPageSize);
                    
                    // sql=hsql+ " limit " + (pageNo-1)*page_size + ","
                    // +page_size;
                    
                    list = query.list();
                    
                    if (list == null) {
                        list = new ArrayList(0);
                    }
                    // 当取当前页的条数
                    // perPageSize=list.size();
                    
                    pageInfo.set_totalCount(totalCounts);
                    pageInfo.set_totalPageCount(totalPages);
                    pageInfo.set_currentPage(currentPageNum);
                    pageInfo.set_perPageCount(perPageSize);
                    pageInfo.set_dataList(list);
                    
                    return pageInfo;
                }
            });
    }
    
    
    /**
     * 主要是采用JDBC的方式进行实现该功能点
     * @param sql
     * @param params
     * @param currentPage
     * @param perPageNum
     * @param zz
     * @return
     */
	  @SuppressWarnings("deprecation")
	public  PageInfo getResultPageInfoOfObject(final String sql,final List params , final Class c 
			,final int currentPage ,final int pageSize) {
		  
		  return (PageInfo) this.getHibernateTemplate().execute(
		            new HibernateCallback() {
		                public Object doInHibernate(final Session session)
		                    throws HibernateException, SQLException {
		                  //打印日志
		                  //logger.debug("sql:"+sql);
		                	//创建分页对象
		          		  final   PageInfo  pageInfo = new  PageInfo();
		          		  List list = null;
		          		  Connection conn = null;
		          		  Statement stmt = null;
		          		  ResultSet rs = null;
		          		  Method[] method = c.getMethods();
		          		  try {
		          		      list = new ArrayList();
		          		      //conn = session.connection();   ---待分析，session的版本方法失效
		          		      
		          		      //构造SQL执行语句
		          		      int  totalCount = getDataAccount(conn , params , sql);
		          		      rs = getPageDataMessage(conn , currentPage , pageSize , sql , params  , totalCount);      
		          		      ResultSetMetaData rsMetaData = rs.getMetaData();
		          		      //对结果集进行对象化转变
		          		      while (rs.next()) {
		          		          Object obj = c.newInstance();
		          		          for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
		          		              String columnName = rsMetaData.getColumnName(i)
		          		                .toLowerCase();
		          		              String methodName = "set"
		          		                + columnName.substring(0, 1).toUpperCase()
		          		                + columnName.substring(1);
		          		              if (rs.getObject(columnName) != null) {
		          		              for (int k = 0; k < method.length; k++) {
		          		                  Method m = method[k];
		          		                  String classMethodeName = m.getName().replace("_", "");
		          		                  //m.getName();
		          		                  if (classMethodeName.equalsIgnoreCase(methodName)) {
		          		                     Object object[] = new Object[1];
			          		                if (m.getParameterTypes()[0]
			          		                  .equals(Integer.TYPE)) {
			          		                    object[0] = new Integer(Integer
			          		                      .parseInt(String.valueOf(rs
			          		                        .getObject(columnName))));
			          		                } else if (m.getParameterTypes()[0]
			          		                  .equals(Long.TYPE)) {
			          		                    object[0] = new Long(Integer
			          		                      .parseInt(String.valueOf(rs
			          		                        .getObject(columnName))));
			          		                }else  if(m.getParameterTypes()[0].getName().equals("java.util.Date")){
			            						object[0] = rs.getObject(columnName);
			            					} else {
			            						object[0] = StringUtil.toString(String.valueOf(rs
					          		                      .getObject(columnName)), "");//解决字符存入为 null时直接展示为null;
			          		                }
			          		                m.invoke(obj, object);
			          		              continue;//避免过多的执行不必要的比对
		          		              }
		          		            }
		          		           }
		          		
		          		         }
		          		         list.add(obj);
		          		      }
		          		      pageInfo.set_currentPage(currentPage);
		          		      pageInfo.set_perPageCount(pageSize);
		          		      pageInfo.set_dataList(list);
		          		      pageInfo.set_totalCount(totalCount);
		          		      
		          		  } catch (Exception e) {
		          		        //log.info("------执行getResultListOfObject（获取数据库信息） 错误-----： "
		          		        //+ e.toString());
		          		      e.printStackTrace();
		          		  } finally {
		          		    try {
		          			    if(rs != null){
		          			    	rs.close();rs = null;
		          			    }
		          				if(stmt != null){
		          					stmt.close(); stmt = null;
		          				}
		          				if(conn != null){
		          					conn.close();conn = null;
		          				}
		          			} catch (SQLException e) {
		          				e.printStackTrace();
		          		    }
		          		  }
		          		  return pageInfo;      
		       }
		  });      
}
	  
	   
	    
	    /**
	     * 主要是采用JDBC的方式进行实现该功能点
	     * @param sql
	     * @param params
	     * @param currentPage
	     * @param perPageNum
	     * @param zz
	     * @return
	     */
		  @SuppressWarnings("deprecation")
		public  PageInfo getPageInfoToUnderLineObject(final String sql,final List params , final Class c 
				,final int currentPage ,final int pageSize) {
			  
			  return (PageInfo) this.getHibernateTemplate().execute(
			            new HibernateCallback() {
			                public Object doInHibernate(final Session session)
			                    throws HibernateException, SQLException {
			                	//打印日志
				                // logger.debug("sql:"+sql);
			                	//创建分页对象
			          		  final   PageInfo  pageInfo = new  PageInfo();
			          		  List list = null;
			          		  Connection conn = null;
			          		  Statement stmt = null;
			          		  ResultSet rs = null;
			          		  Method[] method = c.getMethods();
			          		  try {
			          		      list = new ArrayList();
			          		     //conn = session.connection();   ---待分析，session的版本方法失效
			          		      //构造SQL执行语句
			          		      int  totalCount = getDataAccount(conn , params , sql);
			          		      rs = getPageDataMessage(conn , currentPage , pageSize , sql , params  , totalCount);      
			          		      ResultSetMetaData rsMetaData = rs.getMetaData();
			          		      //对结果集进行对象化转变
			          		      while (rs.next()) {
			          		          Object obj = c.newInstance();
			          		          for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
			          		              String columnName = rsMetaData.getColumnName(i).toLowerCase();
			          		              String methodName = "set_"+columnName;
			          		              if (rs.getObject(columnName) != null) {
			          		              for (int k = 0; k < method.length; k++) {
			          		                  Method m = method[k];
			          		                  if (m.getName().equalsIgnoreCase(methodName)) {
			          		                Object object[] = new Object[1];
			          		                if (m.getParameterTypes()[0]
			          		                  .equals(Integer.TYPE)) {
			          		                    object[0] = new Integer(Integer
			          		                      .parseInt(String.valueOf(rs
			          		                        .getObject(columnName))));
			          		                } else if (m.getParameterTypes()[0]
			          		                  .equals(Long.TYPE)) {
			          		                    object[0] = new Long(Integer
			          		                      .parseInt(String.valueOf(rs
			          		                        .getObject(columnName))));
			          		                }else  if(m.getParameterTypes()[0].getName().equals("java.util.Date")){
			            						object[0] = rs.getObject(columnName);
			            					} else{
			          		                    object[0] = StringUtil.toString(String.valueOf(rs
					          		                      .getObject(columnName)), "");//解决字符存入为 null时直接展示为null;
			          		                }
			          		                m.invoke(obj, object);
			          		                continue;//避免过多的执行不必要的比对
			          		              }
			          		            }
			          		           }
			          		
			          		         }
			          		         list.add(obj);
			          		      }
			          		      pageInfo.set_currentPage(currentPage);
			          		      pageInfo.set_perPageCount(pageSize);
			          		      pageInfo.set_dataList(list);
			          		      pageInfo.set_totalCount(totalCount);
			          		      
			          		  } catch (Exception e) {
			          		        //log.info("------执行getResultListOfObject（获取数据库信息） 错误-----： "
			          		        //+ e.toString());
			          		      e.printStackTrace();
			          		  } finally {
			          		    try {
			          			    if(rs != null){
			          			    	rs.close();rs = null;
			          			    }
			          				if(stmt != null){
			          					stmt.close(); stmt = null;
			          				}
			          				if(conn != null){
			          					conn.close();conn = null;
			          				}
			          			} catch (SQLException e) {
			          				e.printStackTrace();
			          		    }
			          		  }
			          		  return pageInfo;      
			       }
			  });      
	}
		  
	/**
	 * 改进目的：区分获取记录数和数据集sql
	 * 提升获取记录数的效率
	 */
		  
	public PageInfo getPageUnderLineObject(final String countSql,final String dataSql,final List params , final Class c 
			,final int currentPage ,final int pageSize){
		 return (PageInfo) this.getHibernateTemplate().execute(
		            new HibernateCallback() {
		                public Object doInHibernate(final Session session)
		                    throws HibernateException, SQLException {
		                  //打印日志
			              //logger.debug("countSql:"+countSql);
			              //logger.debug("dataSql:"+dataSql);
		                  //创建分页对象
		          		  final   PageInfo  pageInfo = new  PageInfo();
		          		  List list = null;
		          		  Connection conn = null;
		          		  Statement stmt = null;
		          		  ResultSet rs = null;
		          		  Method[] method = c.getMethods();
		          		  try {
		          		      list = new ArrayList();
		          		      //conn = session.connection();
		          		      //构造SQL执行语句
		          		      int  totalCount = getDataAccount(conn , params , countSql);
		          		      rs = getPageDataMessage(conn , currentPage , pageSize , dataSql , params  , totalCount);      
		          		      ResultSetMetaData rsMetaData = rs.getMetaData();
		          		      //对结果集进行对象化转变
		          		      while (rs.next()) {
		          		          Object obj = c.newInstance();
		          		          for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
		          		              String columnName = rsMetaData.getColumnName(i).toLowerCase();
		          		              String methodName = "set_"+columnName;
		          		              if (rs.getObject(columnName) != null) {
		          		              for (int k = 0; k < method.length; k++) {
		          		                  Method m = method[k];
		          		                  if (m.getName().equalsIgnoreCase(methodName)) {
		          		                Object object[] = new Object[1];
		          		                if (m.getParameterTypes()[0]
		          		                  .equals(Integer.TYPE)) {
		          		                    object[0] = new Integer(Integer
		          		                      .parseInt(String.valueOf(rs
		          		                        .getObject(columnName))));
		          		                } else if (m.getParameterTypes()[0]
		          		                  .equals(Long.TYPE)) {
		          		                    object[0] = new Long(Integer
		          		                      .parseInt(String.valueOf(rs
		          		                        .getObject(columnName))));
		          		                }else  if(m.getParameterTypes()[0].getName().equals("java.util.Date")){
		            						object[0] = rs.getObject(columnName);
		            					} else{
		          		                    object[0] = StringUtil.toString(String.valueOf(rs
				          		                      .getObject(columnName)), "");//解决字符存入为 null时直接展示为null;
		          		                }
		          		                m.invoke(obj, object);
		          		                continue;//避免过多的执行不必要的比对
		          		              }
		          		            }
		          		           }
		          		
		          		         }
		          		         list.add(obj);
		          		      }
		          		      pageInfo.set_currentPage(currentPage);
		          		      pageInfo.set_perPageCount(pageSize);
		          		      pageInfo.set_dataList(list);
		          		      pageInfo.set_totalCount(totalCount);
		          		      
		          		  } catch (Exception e) {
		          			  throw new RuntimeException("数据库语句异常:"+e.toString());
		          		  } finally {
		          		    try {
		          			    if(rs != null){
		          			    	rs.close();rs = null;
		          			    }
		          				if(stmt != null){
		          					stmt.close(); stmt = null;
		          				}
		          				if(conn != null){
		          					conn.close();conn = null;
		          				}
		          			} catch (SQLException e) {
		          				throw new RuntimeException(e.toString());
		          		    }
		          		  }
		          		  return pageInfo; 	
		                }
		});
	}
		  

	/**zengshl
	* 获取所有记录集条数信息
	* @param pStmt
	* @param params
	* @param strSql
	* @return
	*/
	private   int   getDataAccount(Connection conn ,List params , String  sql){
		PreparedStatement  pStmt = null;
		ResultSet  rs = null;
		try
		{
		int  account = 0 ;//数目
		String sqlStr = sql;
		//1.构建数目查询条件
		sqlStr = sqlStr.replaceAll("from ", "FROM ");
		sqlStr = sqlStr.replaceAll("From ", "FROM ");   
		final String totalCountSql = "select count(*) "
		    + sqlStr.substring(sqlStr.indexOf("FROM "));
		pStmt = conn.prepareStatement(totalCountSql);
		
		//2.创建参数条件
		if(params != null && params.size() > 0){
		  for(int  index = 0 ; index < params.size() ; index++ ){
		    pStmt.setObject(index+1, params.get(index));
		  }      
		}
		//3.执行查询操作
		rs = pStmt.executeQuery();
		
		//4.返回数据结果
		while(rs.next()){
		  account = rs.getInt(1);
		}
		return account;
		}
		catch (Exception e)
		{
		//异常处理
		  e.printStackTrace();
		return 0;
		}finally{
			try {
				if(pStmt != null){pStmt.close();pStmt = null;}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	* zengshl 
	* @param conn
	* @param currentPageNum
	* @param perPageSize
	* @param sql
	* @param params
	* @param totalCounts
	* @return
	*/
	private    ResultSet   getPageDataMessage(Connection conn , int currentPageNum , int  perPageSize , 
	String  sql , List params , int totalCounts){
		PreparedStatement  pStmt = null;
		ResultSet  rs = null;
		try
		{
			String sqlStr = sql;
			//1.构建数目查询条件
			if (currentPageNum == 0) {
			currentPageNum = 1;
			}
			
			// 计算总页数
			int totalPages = totalCounts / perPageSize;
			totalPages = (totalCounts % perPageSize) > 0 ? (totalPages + 1)
			  : totalPages;
			
			// 获取开始记录
			final int firstNum = currentPageNum * perPageSize
			- perPageSize;
			
			final int lastNum = firstNum + perPageSize;
			//组分页的Sql
			sqlStr = "select * from (select src.*,rownum rn from (" + sqlStr;
			sqlStr = sqlStr + ") src ) where rn > " +firstNum+ " and rn <= " + lastNum ;
			logger.debug("sqlStr:"+sqlStr);
			
			pStmt = conn.prepareStatement(sqlStr);
			//2.创建参数条件
			if(params != null && params.size() > 0){
			for(int  index = 0 ; index < params.size() ; index++ ){
			  pStmt.setObject(index+1, params.get(index));
			}      
			}    
			//3.执行查询操作
			rs = pStmt.executeQuery();
			return rs;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}finally{
//			try {
//				//if(pStmt != null){pStmt.close(); pStmt = null;}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
	}
	
	
	/**
	 * 简单查询SQL数据对象信息
	 * @param sql
	 * @param c
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public  List getResultListOfObject(final String sql,final Class c) {
		
		return (List) this.getHibernateTemplate().execute(
	            new HibernateCallback() {
	                public Object doInHibernate(final Session session)
	                    throws HibernateException, SQLException {
	                	//打印日志
	                	//logger.debug("sql:"+sql);
	                	List list = null;
	            		Connection conn = null;
	            		Statement stmt = null;
	            		ResultSet rs = null;
	            		Method[] method = c.getMethods();
	            		try {
	            		    list = new ArrayList();
	            		    //conn = session.connection();
	            		    stmt = conn.createStatement();
	            		    rs = stmt.executeQuery(sql);
	            		    ResultSetMetaData rsMetaData = rs.getMetaData();
	            		    while (rs.next()) {
	            			Object obj = c.newInstance();
	            			for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
	            			    String columnName = rsMetaData.getColumnName(i)
	            				    .toLowerCase();
	            			    String methodName = "set"
	            				    + columnName.substring(0, 1).toUpperCase()
	            				    + columnName.substring(1);
	            			    if (rs.getObject(columnName) != null) {
	            				for (int k = 0; k < method.length; k++) {
	            				    Method m = method[k];
	            				    if (m.getName().equalsIgnoreCase(methodName)) {
	            					Object object[] = new Object[1];
	            					if (m.getParameterTypes()[0]
	            						.equals(Integer.TYPE)) {
	            					    object[0] = new Integer(Integer
	            						    .parseInt(String.valueOf(rs
	            							    .getObject(columnName))));
	            					} else if (m.getParameterTypes()[0]
	            						.equals(Long.TYPE)) {
	            					    object[0] = new Long(Integer
	            						    .parseInt(String.valueOf(rs
	            							    .getObject(columnName))));
	            					}else  if(m.getParameterTypes()[0].getName().equals("java.util.Date")){
	            						object[0] = rs.getObject(columnName);
	            					} else {
	            						object[0] = StringUtil.toString(String.valueOf(rs
			          		                      .getObject(columnName)), "");//解决字符存入为 null时直接展示为null;
	            					}
	            					m.invoke(obj, object);
	            					continue;//避免过多的执行不必要的比对
	            				    }
	            				}
	            			    }
	            			}
	            			list.add(obj);
	            		    }
	            		} catch (Exception e) {
	            		    e.printStackTrace();
	            		} finally {
	            			try {
	            				//this.getSessionFactory().close();
	            				if(rs != null){rs.close(); rs = null;}
	            				if(stmt != null){stmt.close(); stmt = null;}
	            				if(conn != null){conn.close();conn = null;}
	            			} catch (SQLException e) {
	            				e.printStackTrace();
	            			}
	            		}
	            		return list;  
	          }
	    });
	}
	
	/**
	 * 简单查询SQL数据对象信息
	 * @param sql
	 * @param c
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public  List getResultListToUnderLineObject(final String sql,final Class c) {
		
		return (List) this.getHibernateTemplate().execute(
	            new HibernateCallback() {
	                public Object doInHibernate(final Session session)
	                    throws HibernateException, SQLException {
	                	//打印日志
	                	logger.debug("sql:"+sql);
	                	List list = null;
	            		Connection conn = null;
	            		Statement stmt = null;
	            		ResultSet rs = null;
	            		Method[] method = c.getMethods();
	            		try {
	            		    list = new ArrayList();
	            		    //conn = session.connection();
	            		    stmt = conn.createStatement();
	            		    
	            		    rs = stmt.executeQuery(sql);
	            		    ResultSetMetaData rsMetaData = rs.getMetaData();
	            		    while (rs.next()) {
	            			Object obj = c.newInstance();
	            			for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
	            			    String columnName = rsMetaData.getColumnName(i)
	            				    .toLowerCase();
	            			    String methodName = "set_"
	            				    + columnName;
	            			    if (rs.getObject(columnName) != null) {
	            				for (int k = 0; k < method.length; k++) {
	            				    Method m = method[k];
	            				    if (m.getName().equalsIgnoreCase(methodName)) {
	            				    	Object object = new Object();
		            					if (m.getParameterTypes()[0]
		            						.equals(Integer.TYPE)) {
		            					    object = new Integer(Integer
		            						    .parseInt(String.valueOf(rs
		            							    .getObject(columnName))));
		            					} else if (m.getParameterTypes()[0]
		            						.equals(Long.TYPE)) {
		            					    object = new Long(Integer
		            						    .parseInt(String.valueOf(rs
		            							    .getObject(columnName))));
		            					} else  if(m.getParameterTypes()[0].getName().equals("java.util.Date")){
		            						object = rs.getObject(columnName);
		            						//--二进制数据
		            					}else{
		            					    object = StringUtil.toString(String.valueOf(rs
			            						    .getObject(columnName)), "");//解决字符存入为 null时直接展示为null;
		            					}
		            					m.invoke(obj, object);
		            					continue;//避免过多的执行不必要的比对
	            				    }
	            				}
	            			    }
	            			}
	            			list.add(obj);
	            		    }
	            		} catch (Exception e) {
	            		    e.printStackTrace();
	            		} finally {
	            			try {
	            				//this.getSessionFactory().close();
	            				if(rs != null){rs.close(); rs = null;}
	            				if(stmt != null){stmt.close(); stmt = null;}
	            				if(conn != null){conn.close();conn = null;}
	            			} catch (SQLException e) {
	            				e.printStackTrace();
	            			}
	            		}
	            		return list;  
	          }
	    });
	}
	
	/**
	 * 采用JDBC的形式进行对数据保存和更新操作
	 * @param sql
	 * @param params  前提是带参数的情况下（query对象对存储NULL的数据或者""这样的数据会出现数据保存不一致异常）
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public int executeUpdateWithParams(final String sql,final List params) throws SQLException
	  {
		  return (Integer)this.getHibernateTemplate().execute(
	            new HibernateCallback() {
	                @SuppressWarnings({ "unused", "deprecation" })
					public Object doInHibernate(final Session session)
	                    throws HibernateException, SQLException {
	                	//打印日志
	                	//logger.debug("sql:"+sql);
	                	int flag = 0;
	                	List list = null;
	            		Connection conn = null;
	            		PreparedStatement pStmt  = null;
	            		ResultSet rs = null;
	            		try {
	            		    list = new ArrayList();
	            		   // conn = session.connection();
	            		    pStmt = conn.prepareStatement(sql);
	            		    if (params != null && params.size() > 0)
	            		      {
	            		        for (int index = 0; index < params.size(); index++)
	            		        {
	            		          if(params.get(index)==null || params.get(index).equals(""))
	            		            pStmt.setObject(index + 1,"");
	            		          else
	            		            pStmt.setObject(index + 1, params.get(index));
	            		        }
	            		      }
	            		      flag = pStmt.executeUpdate();
	            		      return flag;
	            		} catch (Exception e) {
	            		    e.printStackTrace();
	            		} finally {
	            			try {
	            				if(rs != null){rs.close(); rs = null;}
	            				if(pStmt != null){pStmt.close(); pStmt = null;}
	            				if(conn != null){conn.close();conn = null;}
	            			} catch (SQLException e) {
	            				e.printStackTrace();
	            			}
	            		}
						return flag;
	          }
	    });
	 }

	/* (non-Javadoc)
	 * @see cn.ffcs.crm.common.dao.Dao#callProcedureAndParams(java.lang.String, java.util.List, java.util.List)
	 *注意：返回值意思是:在定义存储过程中参数类型为out类型
	 *使用实例：
	 * 参数设置
	 *	List<Object> params = new ArrayList<Object>(1);
	 *  params.add(0,23);
	 *  返回值设置
	 *  List<Object> outParams = new ArrayList<Object>(2);
	 *  outParams.add(0,oracle.jdbc.OracleTypes.VARCHAR);
	 *	outParams.add(1,oracle.jdbc.OracleTypes.VARCHAR);
	 *	outParams.add(2,oracle.jdbc.OracleTypes.CURSOR);//游标
	 *  Object[] objs = callProcedureAndParams("存储过程名称",params,outParams);
	 *  注意数据类型
	 *  params、outParams 可为 null.
	 *  crm用户登录数据库
	 */
//	@Override
//	public Object[] callProcedureAndParams(final String procedureName, final List<?> inParams,final List<?> outParams)
//			throws SQLException {
//		
//		return (Object[])this.getHibernateTemplate().execute(
//	            new HibernateCallback() {
//	            	@SuppressWarnings("deprecation")
//					public Object doInHibernate(final Session session) throws HibernateException, SQLException {
//	                	int length=0;
//	                	Object[] results=null;
//	                	//传入参数
//	            		if(null != inParams && inParams.size() >0){
//	            			length = inParams.size();
//	            		}
//	            		//返回参数
//	            		if(null != outParams && outParams.size() >0){
//	            			length += outParams.size();
//	            		}
//	            		Connection conn = null;
//	            		CallableStatement   callStmt  = null;
//	            		ResultSet rs = null;
//	            		try {
//	            		conn = session.connection();
//	            		/**
//	            		 * generationExcuteProcedure：生成调用存储过程语句
//	            		 */
//	            		callStmt = conn.prepareCall(StringUtil.generationExcuteProcedure(procedureName, length));
//	            		/**设置参数*/
//	            		if(null != inParams && inParams.size() >0){
//	            			for(int i=0;i<inParams.size();i++){
//	            				final Object param = inParams.get(i);
//	            				int k = i+1;
//	            				if (param instanceof Integer) {
//	            					callStmt.setInt(k, ((Integer) inParams.get(i)).intValue());
//	                            }
//	                            if (param instanceof String) {
//	                            	callStmt.setString(k, inParams.get(i).toString());
//	                            }
//	                            if (param instanceof Long) {
//	                            	callStmt.setLong(k, ((Long) inParams.get(i)).longValue());
//	                            }
//	                            if (param instanceof Date) {
//	                            	callStmt.setDate(k,(java.sql.Date) inParams.get(i));
//	                            }
//	            			}
//	            		}
//	            		/**注册返回值-->此返回值是存储过程参数为out类型*/
//	            		if(null != outParams && outParams.size() >0){
//	            			for(int i=0;i<outParams.size();i++){
//	            				final Object outParam = outParams.get(i);
//	            				int j = ((null != inParams && inParams.size() >0)?inParams.size():0)+(i+1);
//	            				if (StringUtil.equals(StringUtil.toString(outParam),oracle.jdbc.OracleTypes.INTEGER+"")) {
//	            					callStmt.registerOutParameter(j,oracle.jdbc.OracleTypes.INTEGER);
//	                            }
//	                            if (StringUtil.equals(StringUtil.toString(outParam),oracle.jdbc.OracleTypes.VARCHAR+"")) {
//	                            	callStmt.registerOutParameter(j,oracle.jdbc.OracleTypes.VARCHAR);
//	                            }
//	                            if(StringUtil.equals(StringUtil.toString(outParam),oracle.jdbc.OracleTypes.CURSOR+"")){
//	                            	//声明返回数据集
//	                            	callStmt.registerOutParameter(j,oracle.jdbc.OracleTypes.CURSOR);
//	                            }
//	            			}
//	            		}
//	            		//执行存储过程
//	            		callStmt.execute();
//	            		if(null != outParams && outParams.size() >0){
//	            			Object[]  obs= new Object[outParams.size()];
//	            			for(int i=0;i<outParams.size();i++){
//	            				int k = ((null != inParams && inParams.size() >0)?inParams.size():0)+(i+1);
//	            				/**支持返回数据集:-->在定义存储过程是定义输出类型为游标类型*/
//	            				if(callStmt.getObject(k) instanceof ResultSet){//ResultSet --游标值
//	            					List<Object[]> list = new ArrayList<Object[]>();
//	            					ResultSet result= (ResultSet)callStmt.getObject(k);
//	            					 ResultSetMetaData rsMetaData = result.getMetaData();
//	            					 Object[] obj = new Object[rsMetaData.getColumnCount()];
//	            					 //遍历数据集
//	            					while(result.next()){
//	            						for(int m=0;m<rsMetaData.getColumnCount();m++){
//	            							//遍历每个字段值
//	            							obj[m]=result.getObject(rsMetaData.getColumnName(m+1).toLowerCase());
//	            						}
//	            						//存放在list
//	            						list.add(obj);
//	            					}
//	            					obs[i] = list;
//	            				}else{
//	            					obs[i]=callStmt.getObject(k);//--
//	            				}
//	            			}
//	            			results = obs;
//	            		}
//	            		
//	            		} catch (Exception e) {
//	            		    e.printStackTrace();
//	            		} finally {
//	            			try {
//	            				if(rs != null){rs.close(); rs = null;}
//	            				if(callStmt != null){callStmt.close(); callStmt = null;}
//	            				if(conn != null){conn.close();conn = null;}
//	            			} catch (SQLException e) {
//	            				e.printStackTrace();
//	            			}
//	            		}
//	            		return results;
//	            	} 	
//	     });
//	}
//	//channel用户登录
//	public Object[] callProcedureAndParamsNew(final String procedureName, final List<?> inParams,final List<?> outParams){
//		int length=0;
//    	Object[] results=null;
//    	//传入参数
//		if(null != inParams && inParams.size() >0){
//			length = inParams.size();
//		}
//		//返回参数
//		if(null != outParams && outParams.size() >0){
//			length += outParams.size();
//		}
//		Connection conn = null;
//		CallableStatement   callStmt  = null;
//		ResultSet rs = null;
//		try {
//		//jdbc连接数据库
//		conn = Dbconn.getOtherConnection();
//		/**
//		 * generationExcuteProcedure：生成调用存储过程语句
//		 */
//		callStmt = conn.prepareCall(StringUtil.generationExcuteProcedure(procedureName, length));
//		/**设置参数*/
//		if(null != inParams && inParams.size() >0){
//			for(int i=0;i<inParams.size();i++){
//				final Object param = inParams.get(i);
//				int k = i+1;
//				if (param instanceof Integer) {
//					callStmt.setInt(k, ((Integer) inParams.get(i)).intValue());
//                }
//                if (param instanceof String) {
//                	callStmt.setString(k, inParams.get(i).toString());
//                }
//                if (param instanceof Long) {
//                	callStmt.setLong(k, ((Long) inParams.get(i)).longValue());
//                }
//                if (param instanceof Date) {
//                	callStmt.setDate(k,(java.sql.Date) inParams.get(i));
//                }
//			}
//		}
//		/**注册返回值-->此返回值是存储过程参数为out类型*/
//		if(null != outParams && outParams.size() >0){
//			for(int i=0;i<outParams.size();i++){
//				final Object outParam = outParams.get(i);
//				int j = ((null != inParams && inParams.size() >0)?inParams.size():0)+(i+1);
//				if (StringUtil.equals(StringUtil.toString(outParam),oracle.jdbc.OracleTypes.INTEGER+"")) {
//					callStmt.registerOutParameter(j,oracle.jdbc.OracleTypes.INTEGER);
//                }
//                if (StringUtil.equals(StringUtil.toString(outParam),oracle.jdbc.OracleTypes.VARCHAR+"")) {
//                	callStmt.registerOutParameter(j,oracle.jdbc.OracleTypes.VARCHAR);
//                }
//                if(StringUtil.equals(StringUtil.toString(outParam),oracle.jdbc.OracleTypes.CURSOR+"")){
//                	//声明返回数据集
//                	callStmt.registerOutParameter(j,oracle.jdbc.OracleTypes.CURSOR);
//                }
//			}
//		}
//		//执行存储过程
//		callStmt.execute();
//		if(null != outParams && outParams.size() >0){
//			Object[]  obs= new Object[outParams.size()];
//			for(int i=0;i<outParams.size();i++){
//				int k = ((null != inParams && inParams.size() >0)?inParams.size():0)+(i+1);
//				/**支持返回数据集:-->在定义存储过程是定义输出类型为游标类型*/
//				if(callStmt.getObject(k) instanceof ResultSet){//ResultSet --游标值
//					List<Object[]> list = new ArrayList<Object[]>();
//					ResultSet result= (ResultSet)callStmt.getObject(k);
//					 ResultSetMetaData rsMetaData = result.getMetaData();
//					 Object[] obj = new Object[rsMetaData.getColumnCount()];
//					 //遍历数据集
//					while(result.next()){
//						for(int m=0;m<rsMetaData.getColumnCount();m++){
//							//遍历每个字段值
//							obj[m]=result.getObject(rsMetaData.getColumnName(m+1).toLowerCase());
//						}
//						//存放在list
//						list.add(obj);
//					}
//					obs[i] = list;
//				}else{
//					obs[i]=callStmt.getObject(k);//--
//				}
//			}
//			results = obs;
//		}
//		
//		} catch (Exception e) {
//		    e.printStackTrace();
//		} finally {
//			try {
//				if(rs != null){rs.close(); rs = null;}
//				if(callStmt != null){callStmt.close(); callStmt = null;}
//				if(conn != null){conn.close();conn = null;}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return results;
//	} 

}
