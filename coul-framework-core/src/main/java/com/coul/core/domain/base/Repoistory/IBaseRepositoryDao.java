package com.coul.core.domain.base.Repoistory;

import java.util.List;
import java.util.Map;

import com.coul.common.exception.DaoAccessException;
import com.coul.core.domain.db.Entity;
import com.coul.core.domain.model.DataStore;
import com.coul.core.domain.model.PagingParameter;

/**
 * --统一的数据接口层的数据操作
 * --方法进行统一的抽象过程
 * --该持久化接口可以由  --重复使用操作的过程
 * --1、springJdbc继承实现
 * --2、hibernate继承实现
 * --3、mybaites继承是实现
 * --4、springJpa继承的过程
 * @author zengshl
 *
 */
public interface IBaseRepositoryDao<E extends Entity> {
	
	/*数据经常使用的操作，增加、修改、删除、查询
	 * 1、增加：单个增加、批量增加、关联增加
	 * 2、修改：单个对象修改、批量对象修改、关联修改
	 * 3、删除：单个对象输出、多个对象删除、关联删除
	 * 4、查询：单挑记录查询、多条记录查询、主键ID查询、分页数据查询、关联查询等等*/
	
	
	/****************************************************************
	 * 统一的接口层操作增加动作
	 * zengshl
	 * *************************************************************/
	
	/**
	 * 新增记录（不指定ID值，ID值由数据库生成）
	 *
	 * @param <K>     ID属性的类型参数
	 * @param entity  新增记录的实体对象，其ID属性值必须为空，否则将抛出DuplicateRecordException
	 * @return K      数据库生成的ID值，接收返回值的变量类型必须和ID属性的类型相同，否则将抛出ClassCastException
	 * 创建日期：2012-9-25
	 * 修改说明：
	 * @author zengshl
	 */
	public <K extends Number> K save(E entity) throws DaoAccessException;
	
	/**
	 * 新增记录（指定ID值）
	 *
	 * @param <K>    ID属性的类型参数
	 * @param entity 新增记录的实体对象，其ID属性值必须为空，否则将抛出DuplicateRecordException
	 * @param id     ID值，如果指定的ID值为null，则ID值由数据库生成
	 * 修改说明：
	 * @author zengshl
	 */
	public <K extends Number> void save(E entity, K id) throws DaoAccessException;

	  
	/**
	 * 批量新增
	 *
	 * @param <K>      ID属性类型参数
	 * @param entitys  实体集合，各个对象的ID值必须为空，否则将抛出DuplicateRecordException
	 * @param ids      指定ID集合，entitys的前ids.length个对象通过指定的ID值新增，其他的对象ID值由数据库生成
	 * @return
	 * 创建日期：2012-9-25
	 * 修改说明：
	 * @author zengshl
	 */
	public <K extends Number> void saves(List<E> entitys, K... ids) throws DaoAccessException;


	
	
	/****************************************************************
	 * 统一的接口层操作修改动作
	 * zengshl
	 * *************************************************************/
	  
	/**
	 * 更新记录
	 *
	 * @param entity
	 * 修改说明：
	 * @author zengshl
	 */
	public void update(E entity) throws DaoAccessException;

	/**
	 * 新增或更新记录，ID值为空做新增操作，ID值非空做更新操作
	 *
	 * @param entity
	 * 修改说明：
	 * @author zengshl
	 */
	public void saveOrUpdate(E entity) throws DaoAccessException;
	

	/**
	 * 批量更新
	 *
	 * @param entitys
	 * 创建日期：2012-9-25
	 * 修改说明：
	 * @author zengshl
	 */
	public void updates(List<E> entitys) throws DaoAccessException;

	/**
	 * 批量新增或更新
	 *
	 * @param entitys
	 * 创建日期：2012-9-25
	 * 修改说明：
	 * @author zengshl
	 */
	public void saveOrUpdates(List<E> entitys) throws DaoAccessException;
	
	
	/****************************************************************
	 * 统一的接口层操作删除动作
	 * zengshl
	 * *************************************************************/
	
	/**
	 * 通过ID值删除记录
	 *
	 * @param id
	 * 修改说明：
	 * @author zengshl
	 */
	public <K extends Number> void delete(K id) throws DaoAccessException;
	
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * 创建日期：2012-9-25
	 * 修改说明：
	 * @author zengshl
	 */
	public <K extends Number> void deletes(List<K> ids) throws DaoAccessException;
	
	
	/****************************************************************
	 * 统一的接口层操作查询动作---该动作的内容比较多，涉及的范围也比较广 
	 * 关键点：1、查询条件（object... , List<Object>  ,  Map<String , value> ， object等等对于不同类型的查询条件的处理） 
	 *      2、查询脚本（自动生成脚本、人工复杂脚本的编写过程）
	 *      3、查询结果（单个数据信息、多个数据信息、分页数据信息）
	 *      4、结果数据转换的过程 （Map的数据信息、List<Object>的数据信息、分页提供的数据格式信息<DataStore><pageInfo>、实体对象等等的业务逻辑的数据格式）
	 * zengshl
	 * *************************************************************/
	
	
	/**（1）比较-就是简单的处理--查询的操作
	 * 根据SQL语句获得实体对象，
	 *
	 * @param sql     SQL语句
	 * @param params  查询参数
	 * @return E 没有记录返回null, 仅有一条记录返回实体对象，否则抛出DuplicatedRecordException
	 * 			  如果SQL语句指定了查询列，则返回的实体对象只有相应的属性有值，其他为null
	 * 修改说明：
	 * @author zengshl
	 */
	public E get(String sql, Object... params) throws DaoAccessException;
	public E get(String sql, List<Object> params) throws DaoAccessException;
	public E get(String sql, Map<String, Object> params) throws DaoAccessException;
	
	
	
	
	/**
	 * 根据ID值获得实体对象，可选参数associationLink，如果不指定只查询单个实体对象
	 * 否则使用左连接查询当前实体(左连接查询的主表)和associationLink指定的实体对象
	 *
	 * @param id               ID值
	 * @param associationLink  <p>关联实体链，从当前实体的父表开始依次指定，必须按从子表到父表的顺序且不能间断
	 * 						       否则将抛出AnnotationNotFoundException
	 * 						       如果有多个分支，则各分支可以依次指定，各个分支之间没有顺序
	 * 						   </p>
	 * @return E               实体对象，记录不存在时返回null
	 * 创建日期：2012-9-25
	 * 修改说明：
	 * @author zengshl
	 */
	public <K extends Number> E get(K id, Class<?>... associationLink) throws DaoAccessException;
	public <K extends Number> E get(K id) throws DaoAccessException;
	
	
	/**（2）比较-就是简单的处理--查询的操作List的结果集
	 * 根据SQL语句查询记录
	 *
	 * @param sql       SQL语句，可以只是WHERE子句，为null或空字符串时表时查询全部记录
	 * @param params    SQL参数
	 * @return List<E>  实体集合，若SQL语句指定了查询列，则实体对象中没有被指定列对应的属性为空值
	 * 修改说明：
	 * @author zengshl
	 */
	public List<E> query(String sql, Object... params) throws DaoAccessException;
	public List<E> query(String sql, List<Object> params) throws DaoAccessException;
	public List<E> query(String sql, Map<String, Object> params) throws DaoAccessException;
	
	
	
	/**（3）--查询的操作分页的结果集
	 * 根据SQL语句和分页参数查询记录
	 *
	 * @param sql           参数说明请参考query(sql, params)
	 * @param paging
	 * @param params
	 * @return DataStore<E> 分页数据
	 * 修改说明：
	 * @author zengshl
	 */
	public DataStore<E> query(String sql, PagingParameter paging, Object... params) throws DaoAccessException;
	public DataStore<E> query(String sql, PagingParameter paging, List<Object> params) throws DaoAccessException;
	public DataStore<E> query(String sql, PagingParameter paging, Map<String, Object> params) throws DaoAccessException;
	
	
	/**
	 * 计算表里的记录数
	 *
	 * @return
	 * 创建日期：2012-10-20
	 * 修改说明：
	 * @author zengshl
	 */
	public int count() throws DaoAccessException;
	
	

}
