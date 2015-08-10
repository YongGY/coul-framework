package com.coul.core.domain.db;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.coul.common.exception.AnnotationNotFoundException;
import com.coul.common.exception.DaoAccessException;
import com.coul.common.exception.ErrorCode;
import com.coul.common.exception.FieldColumnMappingException;
import com.coul.common.exception.IllegalRecordException;
import com.coul.common.utils.ObjectUtil;
import com.coul.core.domain.model.MapRowMapper;
import com.coul.core.domain.model.TreeNode;

/**
 * 单表增删改查的SQL语句创建类
 * 创建日期：2012-8-1
 * @author zengshl
 */
public class SimpleSqlBuilder<E extends Entity> {
	/** 日志对象  */
	private static final Logger log = Logger.getLogger(SimpleSqlBuilder.class);

	/** 表名 */
	private String tableName;
	/** ID属性  */
	private String idField;
	/** 实体类属性和数据表字段的映射 */
	private Map<String, String> fieldColumnMapping;
	/** 实体类类型  */
	private Class<E> entityClass;

	/**
	 * 构造方法
	 * 创建日期：2012-8-1
	 * 修改说明：
	 * @author zengshl
	 */
	public SimpleSqlBuilder(Class<E> entityClass) {
		init(entityClass);
	}

	/**
	 * 初始化方法
	 *
	 * @param entityClass  实体类类型
	 * 创建日期：2012-10-11
	 * 修改说明：
	 * @author zengshl
	 */
	private void init(Class<E> entityClass) {
		this.entityClass = entityClass;
		//1.获取对象的表名的注解对象
		Relation annotation = entityClass.getAnnotation(Relation.class);
		if(annotation == null) {
			throw new AnnotationNotFoundException(ErrorCode.ANNOTATION_NOT_FOUND_AS_RELATION,
					"Annotation " + Relation.class.getName() + " 没有找到实体配置的表名 " + this.entityClass.getName());
		}
		//1.1获取表名
		String tableName = annotation.value();
		if(tableName.equals(Entity.TABLE_NAME_DEFAULT)) {
			tableName = this.entityClass.getSimpleName().toUpperCase();
		}
		//初始化表名
		setTableName(tableName);
		
		//2.获取实体对象的字段名
		List<Field> columnFields = ObjectUtil.getFieldsByAnnotation(this.entityClass, Column.class);
		if(CollectionUtils.isEmpty(columnFields)) {
			throw new AnnotationNotFoundException(ErrorCode.ANNOTATION_NOT_FOUND_AS_COLUMN,
					"Annotation " + Column.class.getName() + " 字段名没有找到 " + this.entityClass.getName());
		}
		//2.1初始化实体类属性和数据表字段的映射
		Map<String, String> mapping = new HashMap<String, String>();
		for (Field field  : columnFields) {
			//2.1.1检查列属性是否符合实体属性规则
			checkColumnFieldType(field);
			Id id = field.getAnnotation(Id.class);
			if(id != null) {
				if(idField == null) {
					if(!ObjectUtil.isExtends(field.getType(), Number.class)) {
						throw new FieldColumnMappingException(ErrorCode.FIELD_COLUMN_MAPPING_AS_ID_TYPE_ERROR, 
								"Id type error for " + this.entityClass.getName() + ", the type must extends class Number!");
					}
					setIdField(field.getName());
				} else {
					throw new FieldColumnMappingException(ErrorCode.FIELD_COLUMN_MAPPING_AS_ID_NOT_UNIQUE, 
							"Id field not unique for " + this.entityClass.getName());
				}
			}
			//2.2.2 获取其他字段的注解内容
			String name = field.getAnnotation(Column.class).value();
			if(name.equals(Entity.COLUMN_NAME_DEFAULT)) {
				name = field.getName().toUpperCase();
			}
			mapping.put(field.getName(), name);		
		}
		if(idField == null) {
			throw new AnnotationNotFoundException(ErrorCode.ANNOTATION_NOT_FOUND_AS_ID, 
					"Annotation " + Id.class.getName() + " not found for " + this.entityClass.getName());
		}
		setFieldColumnMapping(mapping);
	}

	/**
	 * 检查列属性是否符合实体属性规则，列属性类型不能是泛型、原始类型、数组、枚举、接口
	 *
	 * @param columnField 列属性反射对象
	 * 创建日期：2012-12-10
	 * 修改说明：
	 * @author zengshl
	 */
	private void checkColumnFieldType(Field columnField) {
		if(columnField.getGenericType() instanceof ParameterizedType) {
			throw new FieldColumnMappingException(ErrorCode.FIELD_COLUMN_MAPPING_AS_FIELD_TYPE_ERROR,
					"Column field " + columnField.getName() + " type error for " +
					entityClass.getName() + ", the type can't be generic!");
		}
		Class<?> fieldType = columnField.getType();
		if(fieldType.isPrimitive()) {
			throw new FieldColumnMappingException(ErrorCode.FIELD_COLUMN_MAPPING_AS_FIELD_TYPE_ERROR,
					"Column field " + columnField.getName() + " type error for " +
					entityClass.getName() + ", the type can't be primitive!");
		}
		if(fieldType.isEnum()) {
			throw new FieldColumnMappingException(ErrorCode.FIELD_COLUMN_MAPPING_AS_FIELD_TYPE_ERROR,
					"Column field " + columnField.getName() + " type error for " +
					entityClass.getName() + ", the type can't be enum!");
		}
		if(fieldType.isInterface()) {
			throw new FieldColumnMappingException(ErrorCode.FIELD_COLUMN_MAPPING_AS_FIELD_TYPE_ERROR,
					"Column field " + columnField.getName() + " type error for " +
					entityClass.getName() + ", the type can't be interface!");
		}
	}
	
	/**
	 * 获得表名
	 *
	 * @return
	 * 创建日期：2012-9-25
	 * 修改说明：
	 * @author zengshl
	 */
	public String getTableName() {
		return tableName;
	}

	private void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 获得ID属性名
	 *
	 * @return
	 * 创建日期：2012-9-25
	 * 修改说明：
	 * @author zengshl
	 */
	public String getIdField() {
		return idField;
	}

	private void setIdField(String idField) {
		this.idField = idField;
	}

	/**
	 * 获得实体类属性和数据表字段的映射
	 *
	 * @return Map<String, String> FieldName -> ColumnName
	 * 创建日期：2012-9-19
	 * 修改说明：
	 * @author zengshl
	 */
	public Map<String, String> getFieldColumnMapping() {
		return fieldColumnMapping;
	}
	
	private void setFieldColumnMapping(Map<String, String> fieldColumnMapping) {
		this.fieldColumnMapping = fieldColumnMapping;
	}

	/**
	 * 获得实体类型
	 *
	 * @return
	 * 创建日期：2012-9-25
	 * 修改说明：
	 * @author zengshl
	 */	
	public Class<E> getEntityClass() {
		return entityClass;
	}

	/**
	 * 获得指定列的属性名
	 *
	 * @param column   列名
	 * @return String  属性名
	 * 创建日期：2012-10-8
	 * 修改说明：
	 * @author zengshl
	 */
	public String getColumnField(String column) {
		for (String field : fieldColumnMapping.keySet()) {
			if(fieldColumnMapping.get(field).equalsIgnoreCase(column)) {
				return field;
			}
		}
		return null;
	}

	/**
	 * 获得指定被引用类型的外键属性名
	 *
	 * @param referencedClass 被引用类型
	 * @return String         外键属性名
	 * 创建日期：2012-10-8
	 * 修改说明：
	 * @author zengshl
	 * 
	 * --案例说明：
	 * 
	 * 在实体里面定义了referenced的注解
	 * 
	 * @referenced(value=entity.class, colmun="attrName")
	 * String  valueName ---> valueName 就是该方法需要获取的属性名称
	 * 
	 */
	public String getReferenceField(Class<? extends Entity> referencedClass) {
		for (Field field  : ObjectUtil.getFieldsByAnnotation(entityClass, Reference.class)) {
			Reference reference = field.getAnnotation(Reference.class);
			Class<? extends Entity> referenceValue = reference.value();
			if(referenceValue == Entity.REFERENCE_CLASS_DEFAULT) {
				referenceValue = entityClass;
			}
			if(referenceValue == referencedClass) {
				return field.getName();
			}
		}
		return null;
	}
	
	/**
	 * 获得指定被引用类型的被引用列名
	 *
	 * @param referencedClass 被引用类型
	 * @return String         被引用列名
	 * 创建日期：2012-10-8
	 * 修改说明：
	 * @author zengshl
	 * 
	 * --案例说明：
	 * 
	 * 在实体里面定义了referenced的注解
	 * 
	 * @referenced(value=entity.class, colmun="attrName")  ---> attrName 就是该方法需要获取的属性名称
	 * String  valueName 
	 */
	public String getReferencedColumn(Class<? extends Entity> referencedClass) {
		for (Field field  : ObjectUtil.getFieldsByAnnotation(entityClass, Reference.class)) {
			Reference reference = field.getAnnotation(Reference.class);
			Class<? extends Entity> referenceValue = reference.value();
			if(referenceValue == Entity.REFERENCE_CLASS_DEFAULT) {
				referenceValue = entityClass;
			}
			if(referenceValue == referencedClass) {
				String column = reference.column();
				if(column.equals(Entity.COLUMN_NAME_DEFAULT)) {
					Field idField = ObjectUtil.getFieldsByAnnotation(referencedClass, Id.class).get(0);
					column = idField.getAnnotation(Column.class).value();
					if(column.equals(Entity.COLUMN_NAME_DEFAULT)) {
						column = idField.getName().toUpperCase();
					}
				}
				@SuppressWarnings({"rawtypes", "unchecked" })
				String columnField = new SimpleSqlBuilder(referencedClass).getColumnField(column);
				if(columnField == null) {
					throw new FieldColumnMappingException(ErrorCode.FIELD_COLUMN_MAPPING_AS_NOT_EXIST_COLUMN,
							"Column name " + column + " not exist for " + referencedClass.getName());
				}
				Class<?> columnFieldType = null;
				try {
					columnFieldType = referencedClass.getDeclaredField(columnField).getType();
				} catch (Exception e) {
					log.error(e);
					throw new RuntimeException(e);
				}
				if(columnFieldType != field.getType()) {
					throw new FieldColumnMappingException(ErrorCode.FIELD_COLUMN_MAPPING_AS_REFERENCE_TYPE_ERROR, 
							"Reference " + entityClass.getName() +  " field " + field.getName() + " type " +
							field.getType().getName() + " and referenced " + referencedClass.getName() + " field " +
							columnField + " type " + columnFieldType.getName() + " not mapping, type must same!");
				}
				return column;
			}
		}
		return null;
	}
	
	/**
	 * 获得关联实体属性，没有返回null
	 *
	 * @param associationClass 关联实体类型
	 * @return
	 * 创建日期：2012-12-5
	 * 修改说明：
	 * @author zengshl
	 * 
	 * 
	 * --案例说明--获取关联表对应的属性信息
	 * 
	 *     @Association
           private  User  user;      ---》该方法就是获取user的属性对象
    
	 */
	public Field getAssociationField(Class<? extends Entity> associationClass) {
		List<Field> fields = ObjectUtil.getFieldsByAnnotation(entityClass, Association.class);
		if(CollectionUtils.isEmpty(fields)) {
			//实体类中没有带有Association的属性
			return null;
		}
		for (Field field : fields) {
			if(field.getType() == associationClass) {
				return field;
			}
		}
		return null;
	}
	
	/**
	 * 获得通过ID查询单条记录的SQL语句
	 * @return
	 * 创建日期：2012-8-1
	 * 修改说明：
	 * @author zengshl
	 */
	public String getQuerySimpleSql() {
		String sql = "SELECT * FROM " + tableName + " WHERE " + 
				fieldColumnMapping.get(idField) + " = ?";
		log.debug(sql);
		return sql;
	}
	
	/**
	 * 获得查询所有记录的SQL语句
	 * @return
	 * 创建日期：2012-8-1
	 * 修改说明：
	 * @author zengshl
	 */
	public String getQueryAllSql() {
		String sql = "SELECT * FROM " + tableName;
		log.debug(sql);
		return sql;
	}
	
	/**
	 * 获得查询总记录数的SQL语句
	 * @return
	 * 创建日期：2012-8-1
	 * 修改说明：
	 * @author zengshl
	 */
	public String getQueryCountSql() {
		String sql = "SELECT count(*) FROM " + tableName;
		log.debug(sql);
		return sql;
	}
	
	/**
	 * 获得通过ID删除一条记录的SQL语句
	 * @return
	 * 创建日期：2012-8-1
	 * 修改说明：
	 * @author zengshl
	 */
	public String getDeleteSql() {
		String sql = "DELETE FROM " + tableName + " WHERE " + fieldColumnMapping.get(idField) + " = ?";
		log.debug(sql);
		return sql;
	}

	/**
	 * 获得插入一条记录的SQL语句（不包含ID属性，ID值由数据库生成）
	 *
	 * @return
	 * 创建日期：2012-9-19
	 * 修改说明：
	 * @author zengshl
	 */
	public String getInsertSql() {
		return getInsertSql(fieldColumnMapping.keySet(), false);
	}

	/**
	 * 获得插入一条记录的SQL语句（不包含ID属性，ID值由数据库生成），指定需要插入的列
	 *
	 * @param fieldSet
	 * @return
	 * 创建日期：2013-1-22
	 * 修改说明：
	 * @author zengshl
	 */
	public String getInsertSql(Set<String> fieldSet) {
		return getInsertSql(fieldSet, false);
	}

	/**
	 * 获得插入一条记录的SQL语句
	 * 
	 * @param isIncludeIdField 是否包含ID属性
	 * @return
	 * 创建日期：2012-12-3
	 * 修改说明：
	 * @author zengshl
	 */
	private String getInsertSql(Set<String> fieldSet, boolean isIncludeIdField) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		sb.append("INSERT INTO ").append(tableName).append("(");
		for (String field : fieldSet) {
			if(field.equals(idField) && !isIncludeIdField) {
				continue;
			}
			sb.append(fieldColumnMapping.get(field)).append(", ");
			sb2.append(":").append(field).append(", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb2.delete(sb2.length()-2, sb2.length());
		sb.append(") VALUES(");
		sb.append(sb2);
		sb.append(")");
		String sql = sb.toString();
		log.debug(sql);
		return sql;
	}
	
	/**
	 * 获得插入一条记录的SQL语句（包含ID属性，ID值由参数指定）
	 *
	 * @return
	 * 创建日期：2012-12-3
	 * 修改说明：
	 * @author zengshl
	 */
	public String getIncludeIdFieldInsertSql() {
		return getInsertSql(fieldColumnMapping.keySet(), true);
	}
	
	/**
	 * 获得插入一条记录的SQL语句（包含ID属性，ID值由参数指定），指定需要插入的列
	 *
	 * @param fieldSet
	 * @return
	 * 创建日期：2013-1-22
	 * 修改说明：
	 * @author zengshl
	 */
	public String getIncludeIdFieldInsertSql(Set<String> fieldSet) {
		return getInsertSql(fieldSet, true);
	}

	/**
	 * 获得更新一条记录的SQL语句
	 *
	 * @return
	 * 创建日期：2012-9-19
	 * 修改说明：
	 * @author zengshl
	 */
	public String getUpdateSql() {
		return getUpdateSql(fieldColumnMapping.keySet());
	}

	/**
	 * 获得更新一条记录的SQL语句，指定需要更新的列
	 *
	 * @param fieldSet    需要更新的列集合
	 * @return
	 * 创建日期：2013-1-22
	 * 修改说明：
	 * @author zengshl
	 */
	public String getUpdateSql(Set<String> fieldSet) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ").append(tableName).append(" SET");
		for (String field : fieldSet) {
			if(field.equals(idField)) {
				continue;
			}
			sb.append(" ").append(fieldColumnMapping.get(field)).append(" =:" + field + ",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(" WHERE ").append(fieldColumnMapping.get(idField)).append(" =:").append(idField);
		String sql = sb.toString();
		log.debug(sql);
		return sql;
	}
	
	/**
	 * 获得InsertSql和UpdateSql的参数Map对象，过滤注解约束的列
	 *
	 * @param entity 实体对象
	 * @return       Map对象
	 * 创建日期：2012-9-19
	 * 修改说明：
	 * @author zengshl
	 */
	public Map<String, Object> getSqlParameters(E entity) {
		return getSqlParameters(entity, true);
	}
	
	/**
	 * 获得InsertSql和UpdateSql的参数Map对象，不过滤注解约束的列
	 *
	 * @param entity
	 * @return
	 * 创建日期：2013-1-22
	 * 修改说明：
	 * @author zengshl
	 */
	public Map<String, Object> getAllSqlParameter(E entity) {
		return getSqlParameters(entity, false);
	}

	/**
	 * 获得InsertSql和UpdateSql的参数Map对象
	 *
	 * @param entity    实体参象
	 * @param isFilter  是否过滤注解约束的列
	 * @return
	 * 创建日期：2013-1-22
	 * 修改说明：
	 * @author zengshl
	 */
	private Map<String, Object> getSqlParameters(E entity, boolean isFilter) {
		Map<String, Object> params = new HashMap<String, Object>();
		for (String field : fieldColumnMapping.keySet()) {
			try {
				Field field0 = entityClass.getDeclaredField(field);
				Object value = new PropertyDescriptor(
						field, entityClass).getReadMethod().invoke(entity);
				if(value == null) {
					if(field.equals(idField)) {
						//新增记录时不指定ID属性
						continue;
					}
					if(isFilter) {
						Column column = field0.getAnnotation(Column.class);
						if(column.isNotNull()) {
							if(column.hasDefault()) {
								continue;
							}
							throw new IllegalRecordException(ErrorCode.ILLEGAL_RECORD_AS_NULL_VALUE_ERROR,
									"Column field " + field + " value error for " +
									entityClass.getName() + ", the value can't is null!");
						}
					}
					params.put(field, null);
				} else {
					if(field0.getType().isEnum()) {
						//作为实体类属性类型的枚举类型必须要有getCode()和getByCode()方法
						params.put(field, field0.getType().getDeclaredMethod("getCode").invoke(value));
					} else {
						params.put(field, value);
					}
				}
			} catch (Exception e) {
				throw new RuntimeException("getSqlParameters exception!", e);
			}
		}
		log.debug(params);
		return params;
	}

	/**
	 * 将结果集的记录赋值到实体对象中  
	 * @param entity 实体对象
	 * @param resultSet 结果集
	 * 创建日期：2012-8-1
	 * 修改说明：
	 * @author zengshl
	 * @throws Exception 
	 * 
	 * 案例说明：
	 * 
	 * 就是将查询的结果映射到实体对象里面去
	 */
	private void setEntityField(E entity, ResultSet resultSet) throws Exception {
		for (String field  : fieldColumnMapping.keySet()) {
			Method writeMethod = new PropertyDescriptor(
					field, entityClass).getWriteMethod();
			Object value = resultSet.getObject(fieldColumnMapping.get(field));
			Class<?> type = entityClass.getDeclaredField(field).getType();
			if(value == null) {
				Object _null = null;
				writeMethod.invoke(entity, _null);
			} else if(type.isPrimitive() || type.isInstance(value)) {
				writeMethod.invoke(entity, value);
			} else if(type.isEnum()) {
				Class<?> codeFieldType = type.getDeclaredMethod("getCode").getReturnType();
				writeMethod.invoke(entity, type.getDeclaredMethod("getByCode", codeFieldType).invoke(
						null, codeFieldType.isPrimitive()? value : codeFieldType.getConstructor(String.class).newInstance(value.toString())));
			} else {
				writeMethod.invoke(entity, type.getConstructor(String.class)
						.newInstance(value.toString()));
			}	
		}
	}

	/**
	 * 将ResultSet转换成实体对象
	 *
	 * @param resultSet  查询结果集
	 * @return
	 * 创建日期：2012-12-3
	 * 修改说明：
	 * @author zengshl
	 */
	private E convertToEntity(ResultSet resultSet) {
		Map<String, Object> _entity = new HashMap<String, Object>();
		for (String field  : fieldColumnMapping.keySet()) {
			try {
				_entity.put(field, resultSet.getObject(fieldColumnMapping.get(field)));
			} catch (SQLException e) {
				log.error(e);
				throw new RuntimeException(e);
			}		
		}
		return ObjectUtil.toBean(_entity, entityClass);
	}

	/**
	 * 获得ORM对象
	 *
	 * @return
	 * 创建日期：2012-9-25
	 * 修改说明：
	 * @author zengshl
	 */
	public RowMapper<E> getRowMapper() {
		return new RowMapper<E>() {
			@Override
			public E mapRow(ResultSet rs, int index) throws SQLException {
				try {			
					E e = entityClass.newInstance();
					setEntityField(e, rs);
					return e;
				} catch (Exception e) {
					log.error(e);
				}
				return convertToEntity(rs);
			}
		};
	}

	/**
	 * 获得指定列的ORM对象
	 *
	 * @param columns 列名集合
	 * @return
	 * 创建日期：2012-9-25
	 * 修改说明：
	 * @author zengshl
	 */
	public RowMapper<E> getRowMapper(Set<String> columns) {
		final Set<String> columnSet = columns;
		return new RowMapper<E>() {
			@Override
			public E mapRow(ResultSet rs, int index) throws SQLException {
				Map<String, Object> entity = new HashMap<String, Object>();
				for (String field  : fieldColumnMapping.keySet()) {
					String column = fieldColumnMapping.get(field);
					if(!columnSet.contains(column.toUpperCase())) {
						continue;
					}
					try {
						entity.put(field, rs.getObject(fieldColumnMapping.get(field)));
					} catch (SQLException e) {
						log.error(e);
						throw new RuntimeException(e);
					}		
				}
				return ObjectUtil.toBean(entity, entityClass);
			}
		};
	}

	/**
	 * 获得指定关联链的ORM对象
	 *
	 * @param associationLink
	 * @return
	 * 创建日期：2012-12-5
	 * 修改说明：
	 * @author zengshl
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RowMapper<E> getRowMapper(Class<?>[] associationLink) {
		final Class<?>[] associationClassLink;
		if(associationLink == null) {
			associationClassLink = getAssociationLink();
		} else {
			associationClassLink = associationLink;
		}
		return new RowMapper<E>() {
			@Override
			public E mapRow(ResultSet rs, int index) throws SQLException {
				//实体对象Map
				Map<String, Object> entityMap = new HashMap<String, Object>();
				//设置实体对象Map键值对
				for (String field  : fieldColumnMapping.keySet()) {
					try {
						entityMap.put(field, rs.getObject(tableName + "_0_" + fieldColumnMapping.get(field)));
					} catch (SQLException e) {
						log.error(e);
						throw new RuntimeException(e);
					}		
				}
				//将实体对象Map转换成实体对象
				E entitey = ObjectUtil.toBean(entityMap, entityClass);
				//实体集合，用于临时存储实体对象及其关联对象，最终长度为：1 + associationLink.length
				List<Object> entityList = new ArrayList<Object>();
				entityList.add(entitey);
				//关联类型三标志(被关联类型下标、被关联类型、关联类型)集合
				List<List<Object>> associationFlags = new ArrayList<List<Object>>();
				//遍历关联链类型，一个类型参数生成一个实体对象（如果外键属性不为null）并赋值给被关联实体的关联属性
				for (int i = 0;i < associationClassLink.length;i++) {
					//关联类型参数
					Class<?> tempClass = associationClassLink[i];
					if(!ObjectUtil.isExtends(tempClass, Entity.class)) {
						throw new FieldColumnMappingException(ErrorCode.FIELD_COLUMN_MAPPING_AS_ASSOCIATION_TYPE_ERROR,
								"Association " + tempClass.getName() + " type error for " +
								entityClass.getName() + ", the type must extends Entity!");
					}
					Class<? extends Entity> associationClass = (Class<? extends Entity>)tempClass;
					SimpleSqlBuilder<? extends Entity> associationSqlBuilder = new SimpleSqlBuilder(associationClass);
					//被关联类型
					Class<? extends Entity> clazz = null;
					SimpleSqlBuilder<? extends Entity> sqlBuilder = null;
					//被关联类型中的关联属性
					Field associationField = null;
					//被关联类型在关联链中的下标，当被关联类型为entityClass时其值为-1
					int j = -1;
					for (; j < i; j++) {
						if(j >= 0) {
							clazz = (Class<? extends Entity>)associationClassLink[j];
						} else {
							clazz = entityClass;
						}
						sqlBuilder = new SimpleSqlBuilder(clazz);
						associationField = sqlBuilder.getAssociationField(associationClass);
						if(associationField != null) {
							List<Object> associationFlag = new ArrayList<Object>();
							associationFlag.add(j + 1);
							associationFlag.add(clazz);
							associationFlag.add(associationClass);
							if(!associationFlags.contains(associationFlag)) {
								//检查指定的关联类型对应的引用属性是否存在
								sqlBuilder.checkExistReferenceField(associationClass);
								associationFlags.add(associationFlag);
								break;
							}
						}
					}
					if(j == i) {
						//当没有找到被关联类型时抛出AnnotationNotFoundException
						throw new AnnotationNotFoundException(ErrorCode.ANNOTATION_NOT_FOUND_AS_ASSOCIATION,
								"Annotation " + Association.class.getName() + " not found for association " +
								associationClass.getName() + " of " + entityClass.getName());
					}
					//被关联对象
					Object associationEntity = entityList.get(j+1);
					if(associationEntity == null) {
						//如果被关联对象为空则不进行赋值
						entityList.add(null);
						continue;
					}
					//关联属性值
					Object value = null;
					String columnPrefix1 = sqlBuilder.getTableName() + "_" + (j + 1) + "_";
					Map<String, String> fieldColumnMapping1 = sqlBuilder.getFieldColumnMapping();
					//如果外键属性为null则不创建关联对象(关联对象也null)，进入下一次遍历
					if(rs.getObject(columnPrefix1 + fieldColumnMapping1.get(sqlBuilder.getReferenceField(associationClass))) != null) {
						String columnPrefix2 = associationSqlBuilder.getTableName() + "_" + (i + 1) + "_";
						Map<String, String> fieldColumnMapping2 = associationSqlBuilder.getFieldColumnMapping();
						Map<String, Object> associationEntityMap = new HashMap<String, Object>();
						for (String field  : fieldColumnMapping2.keySet()) {
							try {
								associationEntityMap.put(field, rs.getObject(columnPrefix2 + fieldColumnMapping2.get(field)));	
							} catch (SQLException e) {
								log.error(e);
								throw new RuntimeException(e);
							}
						}
						value = ObjectUtil.toBean(associationEntityMap, associationClass);
					}
					try {
						//设置关联属性值
						new PropertyDescriptor(associationField.getName(), clazz).getWriteMethod().invoke(associationEntity, value);
					} catch (Exception e) {
						log.error(e);
						throw new RuntimeException(e);
					}
					entityList.add(value);
				}
				return entitey;
			}
		};
	}

	/**
	 * 获得关联链
	 *
	 * @return
	 * 创建日期：2012-12-10
	 * 修改说明：
	 * @author zengshl
	 */	
	public Class<?>[] getAssociationLink() {
		//递归解析实体关联链
		List<Class<?>> associationList = parseAssociationLink(new TreeNode<Class<?>>(entityClass));
		Class<?>[] associationLink = new Class<?>[associationList.size()];
		return associationList.toArray(associationLink);
	}
	
	/**
	 * 递归解析实体关联链，自身关联的实体出现第二次后将停止递归，除自身关联外如果出现循环关联将抛出FieldColumnMappingException
	 *
	 * @param treeNode  递归树节点，用于判断是否出现循环递归
	 * 
	 * 创建日期：2012-12-10
	 * 修改说明：
	 * @author zengshl
	 */	
	private List<Class<?>> parseAssociationLink(TreeNode<Class<?>> treeNode) {
		List<Class<?>> associationList = new ArrayList<Class<?>>();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Class<?>> associationSubList = new SimpleSqlBuilder(treeNode.data).parseAssociationLink();
		for (Class<?> associationClass : associationSubList) {
			associationList.add(associationClass);
			if(associationClass == treeNode.data) {
				continue;
			}
			TreeNode<Class<?>> childNode = new TreeNode<Class<?>>(associationClass, treeNode, treeNode.depth+1);
			if(childNode.isClosedLoop()) {
				throw new FieldColumnMappingException(ErrorCode.FIELD_COLUMN_MAPPING_AS_ASSOCIATION_CLOSED_LOOP,
						"Association link " + childNode + " is closed loop!");
			}
			associationList.addAll(parseAssociationLink(childNode));
		}
		return associationList;
	}
	
	/**
	 * 解析实体关联链
	 *
	 * @param entityClass  实体类型
	 * 
	 * 创建日期：2012-12-10
	 * 修改说明：
	 * @author zengshl
	 */
	@SuppressWarnings("unchecked")
	public List<Class<?>> parseAssociationLink() {
		List<Class<?>> associationList = new ArrayList<Class<?>>();
		List<Field> associationFields = ObjectUtil.getFieldsByAnnotation(entityClass, Association.class);
		for (Field associationField : associationFields) {
			Class<?> associationClass = associationField.getType();
			if(associationList.contains(associationClass)) {
				throw new FieldColumnMappingException(ErrorCode.FIELD_COLUMN_MAPPING_AS_DUPLICATE_ASSOCIATION,
						"Exist duplicate association " + associationClass.getName() + " for " + entityClass.getName() + "!");
			}
			if(!ObjectUtil.isExtends(associationClass, Entity.class)) {
				throw new FieldColumnMappingException(ErrorCode.FIELD_COLUMN_MAPPING_AS_ASSOCIATION_TYPE_ERROR,
						"Association " + associationClass.getName() + " type error for " +
						entityClass.getName() + ", the type must extends Entity!");
			}
			checkExistReferenceField((Class<? extends Entity>)associationClass);
			associationList.add(associationClass);
		}
		return associationList;
	}
	
	/**
	 * 检查指定的关联类型对应的引用属性是否存在
	 *
	 * @param associationClass  关联类型
	 * 
	 * 创建日期：2012-12-10
	 * 修改说明：
	 * @author zengshl
	 */
	public void checkExistReferenceField(Class<? extends Entity> associationClass) {
		boolean hasReference = false;
		List<Field> referenceFields = ObjectUtil.getFieldsByAnnotation(entityClass, Reference.class);
		for (Field referenceField : referenceFields) {
			Class<?> referenceClass = referenceField.getAnnotation(Reference.class).value();
			if(referenceClass == Entity.REFERENCE_CLASS_DEFAULT) {
				referenceClass = entityClass;
			}
			if(referenceClass == associationClass) {
				if(hasReference) {
					throw new FieldColumnMappingException(ErrorCode.FIELD_COLUMN_MAPPING_AS_DUPLICATE_REFERENCE,
							"Exist duplicate reference " + referenceClass.getName() + " for " + entityClass.getName() + "!");
				}
				hasReference = true;
			}
		}
		if(!hasReference) {
			throw new AnnotationNotFoundException(ErrorCode.ANNOTATION_NOT_FOUND_AS_REFERENCE, 
					"Annotation " + Reference.class.getName() + " not found for association " +
					associationClass.getName() + " of " + entityClass.getName());
		}
	}

	/**
	 * 检查实体所有的引用属性列是否存在和引用属性类型和被引用属性类型是否相同
	 *
	 * 创建日期：2012-12-11
	 * 修改说明：
	 * @author zengshl
	 */
	public void checkReferenceFields() {
		for (Field field  : ObjectUtil.getFieldsByAnnotation(entityClass, Reference.class)) {
			Reference reference = field.getAnnotation(Reference.class);
			Class<? extends Entity> referenceValue = reference.value();
			if(referenceValue == Entity.REFERENCE_CLASS_DEFAULT) {
				referenceValue = entityClass;
			}
			getReferencedColumn(referenceValue);
		}
	}
	
	
	/**
	 * 获取对象进行转换的操作
	 * @return
	 */
	public MapRowMapper<E>  getMapRowMapper(){
		
		return new  MapRowMapper<E>(){
	    	@Override
			public E mapRow(Map<String , Object> map, int index) throws DaoAccessException {
	    		Map<String, Object> entity = new HashMap<String, Object>();
				for (String field  : fieldColumnMapping.keySet()) {
					try {
						entity.put(field, map.get(fieldColumnMapping.get(field)));
					} catch (Exception e) {
						log.error(e);
						throw new RuntimeException(e);
					}		
				}
				return ObjectUtil.toBean(entity, entityClass);
			}
			
		};
	}

}
