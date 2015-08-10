package com.coul.config.entity;

import com.coul.core.domain.db.Column;
import com.coul.core.domain.db.Entity;
import com.coul.core.domain.db.Id;
import com.coul.core.domain.db.Relation;


/**
 * 案例说明  --简单的案例
 * 
 * 实体对象
 * 
 * @author zengshl
 *
 */
@Relation("user")
public class User extends Entity {
	
	
	/*定义数据库里面的表字段*/
	
	@Id
	@Column("id")	private Integer id;//   id主键
	@Column("email")	private String email;//   邮箱也是登录帐号
	@Column("password")	private String pwd;//   登录密码
	@Column("sex")
	private String sex;//  
	@Column("create_time")	private String createTime;   //   创建时间
	@Column("update_time")	private String updateTime;   //   修改时间
	@Column("create_user")	private String createUser;   //   创建人
	@Column("update_user")	private String updateUser;   //   修改人
	
	@Column("template_id")
	//@Reference(value=Template.class , column="id")
	private Integer templateId;  //模板ID
	
	//@Association
	//private Template  temp;
	
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
//	public Template getTemp() {
//		return temp;
//	}
//	public void setTemp(Template temp) {
//		this.temp = temp;
//	}
	
}
