package com.coul.config.entity;

import com.coul.core.domain.db.Association;
import com.coul.core.domain.db.Column;
import com.coul.core.domain.db.Entity;
import com.coul.core.domain.db.Id;
import com.coul.core.domain.db.Reference;
import com.coul.core.domain.db.Relation;

/**
 * 
 *
 * @created：2014-02-12     ----springJdbcTemplate的实现方式
 * @author zengshl 
 * 
 * 这里面想要表达的是  template 是1  user 是多的关系  一个template有多个USER的关联关系  ，
 * template表里面的ID和user表里面的template_id是关联关系的
 */
@SuppressWarnings("serial")
@Relation(Template.TABLE)
public class Template extends Entity {

    /** 表名常量 */
    public static final String TABLE = Table.TEMPLATE;
    /**
     * 列名常量
     */
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_VERSION = "VERSION";
    /**
     * 列属性
     */
    /**  */
    @Id
    @Column(COL_ID)
    @Reference(value=User.class , column="template_id")
    private Integer id;
    /**  */
    @Column(COL_NAME)
    private String name;
    /**  */
    @Column(COL_VERSION)
    private String version;
    
    @Association
    private  User  user;
        
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    
    
	
}
