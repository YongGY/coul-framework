package com.coul.config.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coul.common.exception.DaoAccessException;
import com.coul.config.entity.SysUser;
import com.coul.config.entity.Template;
import com.coul.config.repository.ISysUserRepository;
import com.coul.config.repository.impl.TemplateRepositoryImpl;
import com.coul.config.service.ISysUserService;
import com.coul.core.domain.base.service.impl.AbstractGenericService;
/**
 * 
 *
 */
@Service("sysUserServiceImpl")
public class SysUserServiceImpl<T>  extends AbstractGenericService<T>  implements ISysUserService<T>{
	
    private final static Logger log= Logger.getLogger(SysUserServiceImpl.class);
	


	public void delete(Object[] ids) throws Exception {
		//super.deleteObject(ids);
		for(Object id :  ids){
			//sysRoleRelService.deleteByObjId((Integer)id, RelType.USER.key);
		}
	}
	/**
	 * 检查登录
	 * @param email
	 * @param pwd
	 * @return
	 */
	public SysUser queryLogin(String email,String pwd){
		SysUser model = new SysUser();
		model.setEmail(email);
		model.setPwd(pwd);
		return  null;// (SysUserModel) getMapper().queryLogin(model);
	}
	
	/**
	 * 查询邮箱总数，检查是否存在
	 * @param email
	 * @return
	 */
	public int getUserCountByEmail(String email){
		
		//return getRepository().getUserCountByEmail(email);
		//templateRepositoryImpl.queryTemplate(null);
		Template  temp = new  Template();
		//temp.setId((long) 8);
		temp.setName("shuanglin");
		temp.setVersion("V1.0");
		try {
			templateRepositoryImpl.save(temp);
		} catch (DaoAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;//this.getRepository().getUserCountByEmail("admin@qq.com");
	}
	
	/**
	 * 添加用户权限
	 * @param userId
	 * @param roleIds
	 * @throws Exception
	 */
	public void addUserRole(Integer userId,Integer[] roleIds) throws Exception{
		
		
		if(userId == null ||  roleIds == null || roleIds.length < 1 ){ 
			return;
		}
	}
	
	//@Autowired
   // private ISysUserRepository<T> sysUserRepository;

	@Autowired
	private TemplateRepositoryImpl  templateRepositoryImpl;

	@Override
	public  ISysUserRepository getRepository() {
		// TODO Auto-generated method stub
		return null;
	}

   
		
}
