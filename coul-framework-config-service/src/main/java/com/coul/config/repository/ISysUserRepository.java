package com.coul.config.repository;

import com.coul.config.entity.SysUser;
import com.coul.core.domain.base.Repoistory.BaseMapper;
import com.coul.core.domain.base.Repoistory.IBaseRepository;

/**
 * SysUser Mapper     实体对象操作逻辑过程
 * @author Administrator
 *
 * ---实现过程体现在  myBaties的XML配置文件中,脚本执行内容
 */
public interface ISysUserRepository<T> extends IBaseRepository<T> {
	
	/**
	 * 检查登录
	 * @param email
	 * @param pwd
	 * @return
	 */
	public T queryLogin(SysUser model);
	
	
	/**
	 * 查询邮箱总数，检查是否存在
	 * @param email
	 * @return
	 */
	public int getUserCountByEmail(String email);
}
