package com.coul.core.domain.base.Repoistory;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.coul.common.pagehelper.PageInfo;

/**
 *
 * @文件：com.ffcs.crm.common.dao.Dao.java
 * @所含类：Dao
 * @author: wuq
 * @version: V1.0
 * @see:
 * @创建日期：2007-9-13
 * @功能说明：
 * @修改记录： =============================================================<br>
 *        日期:2007-9-13 wuq 创建
 *        =============================================================<br>
 */
@SuppressWarnings("unchecked")
public interface BaseDaoHibernate {
    /**
     * @param clazz
     *            参数类
     * @param obj
     *            参数对象
     * @return List
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-13 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public List getObjects(Class clazz, Object obj);
    
    /**
     * @param clazz
     *            参数类
     * @param id
     *            参数
     * @return Object
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-13 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public Object getObject(Class clazz, Serializable id);
    
    /**
     * @param o
     *            参数类
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-13 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public void saveObject(Object o);
    
    /**
     * @param o
     *            参数类
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-13 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public void addObject(Object o);
    
    /**
     * @param o
     *            参数类
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-13 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public void merge(Object o);
    
    /**
     * @param o
     *            参数类
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-3-28 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    
    public void updateObject(Object o);
    
    /**
     * Generic method to delete an object based on class and id
     * 
     * @param clazz
     *            model class to lookup
     * @param id
     *            the identifier (primary key) of the class
     */
    public void removeObject(Class clazz, Serializable id);
    
    /**
     * @param string
     *            查询语句
     * @return List
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-3-28 chenjun 创建方法，并实现其功能 根据HQL语句查询
     *        ==============================================================<br>
     */
    
    public List findByHql(String string);
    
    /**
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
     *        日期:2007-9-13 chenjun 创建方法，并实现其功能 根据SQL语句分页查找PO对象数据
     *        ==============================================================<br>
     */
    public PageInfo findPageInfoByHQLAndParams(final String sql,
        final List params, final int currentPage, final int perPageNum);
    
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
     *        日期:2007-4-4 chenjun 创建方法，并实现其功能 分页查询
     *        ==============================================================<br>
     */
    public PageInfo findPageInfoByHQL(final String sql, final int currentPage,
        final int perPageNum);
    
    /**
     * @param entities
     *            数据集记录
     * @author: liujr
     * @修改记录： ==============================================================<br>
     *        日期: 2007-4-11 liujr 创建方法，并实现其功能 从数据库删除指定数据集的记录
     *        ==============================================================<br>
     */
    public void deleteAll(Collection entities);
    
    /**
     * @param sql
     *            查询语句
     * @return List
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-6-8 chenjun 创建方法，并实现其功能 根据JDBCSQL语句查询数据
     *        ==============================================================<br>
     */
    
    public List findListByJDBCSQL(final String sql);
    
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
    public String getSingleValueByJDBCSQL(final String sql);
    
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
        final List params);
    
    public String getSingleValueByHql(final String hql);
    
    /**
     * @param sql
     *            查询语句
     * @param params
     *            查询参数
     * @param currentPage
     *            当前页
     * @param perPageNum
     *            每页记录数
     * @param isGroupby
     *            是否分组
     * @return PageInfo
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-6-8 chenjun 创建方法，并实现其功能 根据JDBCSQL语句查询数据
     *        ==============================================================<br>
     */
    public PageInfo findPageInfoByJDBCSQLAndParams(final String sql,
        final List params, final int currentPage, final int perPageNum,
        final boolean isGroupby);
    
    /**
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
     *        日期:2007-6-8 chenjun 创建方法，并实现其功能 根据JDBCSQL语句查询数据
     *        ==============================================================<br>
     */
    public PageInfo findPageInfoByJDBCSQL(final String sql,
        final int currentPage, final int perPageNum, final boolean isGroupby);
    
    /**
     * @param seqName
     *            参数
     * @return String
     * @author: panchh
     * @修改记录： ==============================================================<br>
     *        日期:2007-6-21 panchh 创建方法，并实现其功能 取SEQ值
     *        ==============================================================<br>
     */
    public String getSeqNextval(String seqName);
    
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
    public List findListByHQLAndParams(final String sql, final List params);
    
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
    public List findListByJDBCSQLAndParams(final String sql, final List params);
    
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
    public String getColumnChineseName(String owner, String tableName,
        String colName);
    
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
    public String getSingleValueByHqlAndParams(String hql, List params);
    
    
    /**
     * 采用JDBC的方式进行获取相关数据信息
     * 
     * @param hql
     * @param params
     * @return
     * @author: zengshl
     * @修改记录： ==============================================================<br>
     *        日期:2008-12-31 zengshl 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public  PageInfo getResultPageInfoOfObject(String sql,List params , Class c , int currentPage , int pageSize);
    
    /**
     * 采用JDBC的方式进行获取相关数据信息获取简单的数据信息
     * 
     * @param hql
     * @param params
     * @return
     * @author: zengshl
     * @修改记录： ==============================================================<br>
     *        日期:2008-12-31 zengshl 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public  List getResultListOfObject(String sql, Class c);
    
    /**
     * 采用JDBC的方式进行修改和保存数据信息
     * 
     * @param sql
     * @param params
     * @return
     * @author: zengshl
     * @修改记录： ==============================================================<br>
     *        日期:2008-12-31 zengshl 创建方法，并实现其功能
     *        ==============================================================<br>
     * @throws SQLException 
     */
    public int executeUpdateWithParams(final String sql,final List params) throws SQLException;
//    /***
//     * 区分获取记录数和数据集sql
//     * @author: laiyongmin
//     * */
//    public PageInfo getPageUnderLineObject(final String countSql,final String dataSql,final List params , final Class c 
//			,final int currentPage ,final int pageSize)throws SQLException;
//    
//    /**
//     * 调用存储过程
//     * 
//     * @param procedureName 存储过程名称
//     * @param params：输入参数;outParams:返回参数
//     * @return
//     * @author: zengshl
//     * @修改记录： ==============================================================<br>
//     *        日期:2013-1-10 laiyongmin 创建方法，并实现其功能
//     *        ==============================================================<br>
//     * @throws SQLException 
//     */
//    public Object[] callProcedureAndParams(final String procedureName,final List<?> inParams,final List<?> outParams) throws SQLException;
//
//    public Object[] callProcedureAndParamsNew(final String procedureName,final List<?> inParams,final List<?> outParams) throws SQLException;
}
