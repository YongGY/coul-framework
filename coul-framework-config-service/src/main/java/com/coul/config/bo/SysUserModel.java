package com.coul.config.bo;

import com.coul.core.domain.base.entity.BaseModel;


/**
 * 案例说明  --简单的案例
 * 
 * 实体对象
 * 
 * @author zengshl
 *
 */
public class SysUserModel extends BaseModel {
	
	
	
	private Integer superAdmin;//超级管理员
	
	private String roleStr;//用户权限, 按","区分
	
	
	
		return roleStr;
	}
	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}
	public Integer getId() {
	public Integer getSuperAdmin() {
		return superAdmin;
	}
	public void setSuperAdmin(Integer superAdmin) {
		this.superAdmin = superAdmin;
	}
	
	
 
}