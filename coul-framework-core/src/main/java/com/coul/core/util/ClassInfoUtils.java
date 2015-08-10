package com.coul.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.beanutils.PropertyUtils;

import com.coul.common.exception.PojoStructureException;
import com.coul.common.log.ILogger;
import com.coul.common.log.LoggerFactory;
import com.coul.common.utils.type.StringUtils;

public class ClassInfoUtils {
    
    private static final ILogger                               LOGGER              = LoggerFactory
                                                                                       .getLogger(ClassInfoUtils.class);
    
    private static ConcurrentMap<Class<?>, Boolean>            initMap             = new ConcurrentHashMap<Class<?>, Boolean>();
    
    private static ConcurrentMap<String, Class<?>>             tableNameToClassMap = new ConcurrentHashMap<String, Class<?>>();
    
    private static ConcurrentMap<Class<?>, String>             classToTableNameMap = new ConcurrentHashMap<Class<?>, String>();
    
    private static ConcurrentMap<Class<?>, EntityColumn>       idMap               = new ConcurrentHashMap<Class<?>, EntityColumn>();
    
    private static ConcurrentMap<Class<?>, List<EntityColumn>> columnMap           = new ConcurrentHashMap<Class<?>, List<EntityColumn>>();
    
    public static void initEntityInfo(Class<?> clazz) {
        if (!initMap.containsKey(clazz)) {
            Table table = clazz.getAnnotation(Table.class);
            if (table != null) {
                String tableName = table.name();
                tableNameToClassMap.putIfAbsent(tableName, clazz);
                classToTableNameMap.putIfAbsent(clazz, tableName);
            } else {
                throw new PojoStructureException(
                    "undefine POJO @Table, need Tablename(@Table(name))");
            }
            caculationColumnList(clazz);
            initMap.putIfAbsent(clazz, Boolean.TRUE);
        }
    }
    
    /**
     * 用于计算类定义 需要POJO中的属性定义@Column(name)
     */
    public static void caculationColumnList(Class<?> clazz) {
        if (columnMap.containsKey(clazz)) {
            return;
        }
        Class tempClazz = clazz;
        List<EntityColumn> columnList = new ArrayList<EntityColumn>();
        EntityColumn idColumn = null;
        while (tempClazz != null && !tempClazz.equals(Object.class)) {
            Map<String, Object> map = getColumns(tempClazz);
            if (map.containsKey("columnList")) {
                List<EntityColumn> list = (List<EntityColumn>) map.get("columnList");
                columnList.addAll(list);
            }
            if (idColumn == null && map.containsKey("idColumn")) {
                idColumn = (EntityColumn) map.get("idColumn");
            }
            tempClazz = tempClazz.getSuperclass();
        }
        
        columnMap.putIfAbsent(clazz, columnList);
        if (idColumn != null) {
            idMap.putIfAbsent(clazz, idColumn);
        }
    }
    
    private static Map<String, Object> getColumns(Class<?> clazz) {
        Map<String, Object> columnMap = new HashMap<String, Object>();
        List<EntityColumn> columnList = new ArrayList<EntityColumn>();
        EntityColumn idColumn = null;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                String column = field.getAnnotation(Column.class).name();
                EntityColumn columnInfo = new EntityColumn();
                columnInfo.setType(field.getType());
                if (!StringUtils.isNullOrEmpty(column)) {
                    columnInfo.setColumnName(column);
                    columnInfo.setFiledName(field.getName());
                } else {
                    columnInfo.setColumnName(field.getName());
                    columnInfo.setFiledName(field.getName());
                }
                columnList.add(columnInfo);
                if (field.isAnnotationPresent(Id.class)) {
                    idColumn = columnInfo;
                }
            }
        }
        
        if (columnList != null) {
            columnMap.put("columnList", columnList);
        }
        if (idColumn != null) {
            columnMap.put("idColumn", idColumn);
        }
        return columnMap;
    }
    
    /**
    * 获取POJO对应的表名 需要POJO中的属性定义@Table(name)
    * 
    * @return
    */
    public static String getTableName(Class<?> clazz) {
        if (!initMap.containsKey(clazz)) {
            initEntityInfo(clazz);
        }
        String tableName = classToTableNameMap.get(clazz);
        if (!StringUtils.isNullOrEmpty(tableName)) {
            return tableName;
        } else {
            throw new PojoStructureException("undefine POJO @Table, need Tablename(@Table(name))");
        }
    }
    
    /**
     * 获取POJO中的主键字段名 需要定义@Id
     * 
     * @return
     */
    public static String getIdField(Class<?> clazz) {
        if (!initMap.containsKey(clazz)) {
            initEntityInfo(clazz);
        }
        if (idMap.containsKey(clazz)) {
            EntityColumn column = idMap.get(clazz);
            return column.getColumnName();
        }
        throw new PojoStructureException("undefine POJO @Id");
    }
    
    /**
     * 获取POJO中的主键字段名 需要定义@Id
     * 
     * @return
     */
    public static String getIdFieldName(Class<?> clazz) {
        if (!initMap.containsKey(clazz)) {
            initEntityInfo(clazz);
        }
        if (idMap.containsKey(clazz)) {
            EntityColumn column = idMap.get(clazz);
            return column.getFiledName();
        }
        throw new PojoStructureException("undefine POJO @Id");
    }
    
    /**
     * 获取POJO中的主键字段名 需要定义@Id
     * 
     * @return
     */
    public static Class<?> getIdType(Class<?> clazz) {
        if (!initMap.containsKey(clazz)) {
            initEntityInfo(clazz);
        }
        if (idMap.containsKey(clazz)) {
            EntityColumn column = idMap.get(clazz);
            return column.getType();
        }
        
        throw new PojoStructureException("undefine POJO @Id");
    }
    
    /**
     * 获取POJO中的所有字段名 需要定义@Column
     * 
     * @return
     */
    public static String[] getColumnFields(Class<?> clazz) {
        List<EntityColumn> columnList = getColumnList(clazz);
        int length = columnList.size();
        if(length <= 0){
        	throw new PojoStructureException("undefine POJO @Column");
        }
        
        String[] columns = new String[length];
        EntityColumn column;
        for(int i = 0; i < length; i++){
        	column = columnList.get(i);
        	columns[i] = column.getColumnName();
        }
        return columns;
        
    }
    
    /**
     * 获取POJO中的所有字段名串（以,分隔）， 需要定义@Column
     * 
     * @return
     */
    public static String getColumnFieldString(Class<?> clazz) {
    	String[] columns = getColumnFields(clazz);
    	StringBuilder sb = new StringBuilder();
    	String split = ",";
    	for(String column: columns){
    		sb.append(column).append(split);
    	}
    	
    	return sb.substring(0, sb.length()-1);
    }
    
    /**
     * 获取POJO中的所有字段名 需要定义@Column
     * 
     * @return
     */
    public static String getColumnByField(Class<?> clazz, String fieldName) {
    	List<EntityColumn> columnList = getColumnList(clazz);

        
        
        for(EntityColumn entityColumn: columnList){
        	if(entityColumn.getFiledName().equals(fieldName)){
        		return entityColumn.getColumnName();
        	}
        }
        
    	return null;
    }
    
//    /**
//     * 用于存放POJO的列信息
//     */
//    
//    private static boolean isNull(Object obj, String fieldname) {
//        try {
//            Field field = obj.getClass().getDeclaredField(fieldname);
//            return isNull(obj, field);
//        } catch (SecurityException e) {
//            LOGGER.error(BaseEntityMeta.class.getName(), e);
//        } catch (NoSuchFieldException e) {
//            LOGGER.error(BaseEntityMeta.class.getName(), e);
//        } catch (IllegalArgumentException e) {
//            LOGGER.error(BaseEntityMeta.class.getName(), e);
//        }
//        
//        return false;
//    }
//    
//    private static boolean isNull(Object obj, Field field) {
//        try {
//            field.setAccessible(true);
//            return field.get(obj) == null;
//        } catch (SecurityException e) {
//            LOGGER.error(BaseEntityMeta.class.getName(), e);
//        } catch (IllegalArgumentException e) {
//            LOGGER.error(BaseEntityMeta.class.getName(), e);
//        } catch (IllegalAccessException e) {
//            LOGGER.error(BaseEntityMeta.class.getName(), e);
//        }
//        
//        return false;
//    }
//    
    /**
     * 用于获取Insert的字段累加
     * 
     * @return
     */
    public static String returnInsertColumnsName(Object inst, Class<?> clazz) {
        if (!initMap.containsKey(clazz)) {
            initEntityInfo(clazz);
        }
        StringBuilder sb = new StringBuilder();
        List<EntityColumn> list = columnMap.get(clazz);
        int i = 0;
        for (EntityColumn column : list) {
            //            if (isNull(inst, column.getFiledName())) {
            //                continue;
            //            }
            
            if (i++ != 0) {
                sb.append(',');
            }
            sb.append(column.getColumnName());
        }
        return sb.toString();
    }
    
    /**
     * 用于获取Insert的字段映射累加
     * 
     * @return
     */
    public static String returnInsertColumnsDefine(Object inst, Class<?> clazz) {
        if (!initMap.containsKey(clazz)) {
            initEntityInfo(clazz);
        }
        StringBuilder sb = new StringBuilder();
        List<EntityColumn> list = columnMap.get(clazz);
        int i = 0;
        for (EntityColumn column : list) {
            //            if (isNull(inst, column.getFiledName())) {
            //                continue;
            //            }
            //            
            if (i++ != 0) {
                sb.append(',');
            }
            sb.append("#{").append(column.getFiledName()).append('}');
        }
        return sb.toString();
    }
    
    /**
     * 根据字段名称获取对应的值
     * 
     * @param obj
     * @param fieldname
     * @return
     * @author zhengzhh
     * 2014年12月3日 zhengzhh
     */
    public static Object getValues(Object obj, String fieldname) {
        Object value = new Object();
        try {
            value = PropertyUtils.getProperty(obj, fieldname);
            return value;
        } catch (NoSuchMethodException e) {
            LOGGER.error(ClassInfoUtils.class.getName(), e);
        } catch (InvocationTargetException e) {
            LOGGER.error(ClassInfoUtils.class.getName(), e);
        } catch (IllegalAccessException e) {
            LOGGER.error(ClassInfoUtils.class.getName(), e);
        }
        return value;
    }
    
    /**
     * 根据值类型,返回INSERT字段映射值 
     * 
     * @param inst
     * @param clazz
     * @return
     * @author zhengzhh
     * 2014年12月3日 zhengzhh
     */
    public static String returnInsertColumnsValues(Object inst, Class<?> clazz) {
        if (!initMap.containsKey(clazz)) {
            initEntityInfo(clazz);
        }
        StringBuilder sb = new StringBuilder();
        List<EntityColumn> list = columnMap.get(clazz);
        int i = 0;
        for (EntityColumn column : list) {
            //            if (isNull(inst, column.getFiledName())) {
            //                continue;
            //            }
            
            if (i++ != 0) {
                sb.append(',');
            }
            Object value = getValues(inst, column.getFiledName());
            
            if (value == null) {
                sb.append("NULL");
            } else {
                if (column.getType() == String.class || column.getType() == Date.class
                    || column.getType() == Timestamp.class || column.getType() == Time.class) {
                    sb.append("'" + value + "'");
                } else {
                    sb.append(value);
                }
            }
        }
        return sb.toString();
    }
    
    /**
     * 用于获取Update Set的字段累加
     * 
     * @return
     */
    public static String returnUpdateSet(Class<?> clazz) {
        if (!initMap.containsKey(clazz)) {
            initEntityInfo(clazz);
        }
        StringBuilder sb = new StringBuilder();
        List<EntityColumn> list = columnMap.get(clazz);
        int i = 0;
        for (EntityColumn column : list) {
            // Id列更新的时候不返回
            if (column.getColumnName().equals(getIdField(clazz))) {
                continue;
            }
            if (i++ != 0) {
                sb.append(',');
            }
            sb.append(column.getColumnName()).append("=#{").append(column.getFiledName())
                .append('}');
        }
        return sb.toString();
    }
    
    /**
     * 
     * 方法功能:
     *  获取Select字段.
     * @param tableName
     * @param alias
     * @return
     * @author: linzq
     * @修改记录： 
     * ==============================================================<br>
     * 日期:2014-12-7 linzq 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String getSelectColumns(String tableName, String alias) {
        if (!tableNameToClassMap.containsKey(tableName)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        List<EntityColumn> list = columnMap.get(tableNameToClassMap.get(tableName));
        int i = 0;
        for (EntityColumn column : list) {
            //            if (isNull(inst, column.getFiledName())) {
            //                continue;
            //            }
            if (i++ != 0) {
                sb.append(',');
            }
            if (!StringUtils.isNullOrEmpty(alias)) {
                sb.append(alias + "." + column.getColumnName());
            } else {
                sb.append(column.getColumnName());
            }
        }
        sb.append(" ");
        return sb.toString();
    }
    
    /*
     * 根据类获取Column列表
     */
    public static List<EntityColumn> getColumnList(Class<?> clazz){
    	if (!initMap.containsKey(clazz)) {
    		initEntityInfo(clazz);
    	}
 
    	if (!columnMap.containsKey(clazz)) {
    		throw new PojoStructureException("undefine POJO @Column");
    	}
    	
    	List<EntityColumn> list = columnMap.get(clazz);
    	if(list.size()<=0){
    		list = new ArrayList<EntityColumn>();
    	}
    	return list;
    }
    
}
