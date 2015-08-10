package com.ffcs.atte.test;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coul.common.utils.JsonUtil;
import com.coul.config.entity.SysUser;
import com.coul.config.entity.Template;
import com.coul.config.entity.User;
import com.coul.config.repository.TemplateRepository;
import com.coul.config.repository.impl.UserRepositoryImpl;
import com.coul.config.service.ISysUserService;
import com.coul.config.service.ITemplateService;
import com.coul.core.base.condition.Condition;
import com.coul.core.base.condition.SimpleCondition;
import com.coul.core.domain.model.PagingParameter;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {		                 
        "classpath*:conf/spring/applicationContext-common.xml" })
public class SysUserTest extends AbstractJUnit4SpringContextTests {
   
	@Resource
    ISysUserService<SysUser> sysUserServiceImpl;
	
	@Autowired
	private TemplateRepository  templateRepositoryImpl;
	
	@Resource
	private ITemplateService	    templateServiceImpl;
	
	@Resource
	private UserRepositoryImpl      userRepositoryImpl;

   
    @Test
    public void testInfoQuery() {
    	
    	try {
    		Template  temp = new  Template();
    		temp.setName("shuanglin");
    		temp.setVersion("V1.0");
    		templateRepositoryImpl.save(temp);
    		//templateServiceImpl.save(temp);
     		//System.out.println(sysUserServiceImpl.getUserCountByEmail("dsdsd"));
			//SysUser  sysuser = (SysUser) sysUserServiceImpl.queryById(1);
		    //System.out.println(sysuser.getEmail());
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
    }
    
    /**
     * 测试分页查询的哦数据信息格式
     */
    @Test
    public void testInfoQueryPage() {
    	
    	try {
    		StringBuffer  sb = new  StringBuffer();
    		sb.append("SELECT  *  FROM  TEMPLATE WHERE ID > ?");
    		PagingParameter paging = new PagingParameter();
    		paging.setStart(0);
    		paging.setLimit(10);
    		Integer params = 0;		
    		//DataStore<Template> data =templateRepositoryImpl.query(sb.toString(), paging,params);  
    		SimpleCondition con = new SimpleCondition();
    		//DataStore<User> data = userRepositoryImpl.queryUserDate(con , paging);
    		//System.out.println(JsonUtil.obj2json(data));
    		
    		//测试案例： template是1 、 user是多的情况下（关联查询的时候，被关联出来查询的数据如果是NULL的话， 在转对象的过程中会出现问题）
    		//templateRepositoryImpl.queryAll(User.class);  --这种查询的方式是 
    		//userRepositoryImpl.query(condition, Template.class);
    		//List<Template> listUser = templateRepositoryImpl.query(Condition.eq("id", 1), User.class);
    		//System.out.println(JsonUtil.obj2json(listUser));
    		
    		//返回的数据格式如下：
    		/*
    		 * [{"id":1,"name":"11212","version":"1121212","user":{"id":1,"email":"8888@xx.com","pwd":"21232f297a57a5a743894a0e4a801fc3","sex":"1","createTime":"1400826373000","updateTime":"1400826376000","createUser":"NULL","updateUser":"NULL","templateId":1,"transient":false},"transient":false},{"id":1,"name":"11212","version":"1121212","user":{"id":2,"email":"hjt@xx.com","pwd":"202cb962ac59075b964b07152d234b70","sex":"1","createTime":"1400826566000","updateTime":"1400826566000","createUser":"admin","updateUser":"admin","templateId":1,"transient":false},"transient":false},{"id":1,"name":"11212","version":"1121212","user":{"id":3,"email":"jzm@xx.com","pwd":"202cb962ac59075b964b07152d234b70","sex":"1","createTime":"1400827520000","updateTime":"1400827520000","createUser":"admin","updateUser":"admin","templateId":1,"transient":false},"transient":false},{"id":1,"name":"11212","version":"1121212","user":{"id":4,"email":"test@qq.com","pwd":"202cb962ac59075b964b07152d234b70","sex":"1","createTime":"1400827545000","updateTime":"1400827545000","createUser":"admin","updateUser":"admin","templateId":1,"transient":false},"transient":false},{"id":1,"name":"11212","version":"1121212","user":{"id":5,"email":"test@qq.com","pwd":"202cb962ac59075b964b07152d234b70","sex":"1","createTime":"1400827563000","updateTime":"1400827563000","createUser":"admin","updateUser":"admin","templateId":1,"transient":false},"transient":false},{"id":1,"name":"11212","version":"1121212","user":{"id":10,"email":"test@qq.com","pwd":"202cb962ac59075b964b07152d234b70","sex":"0","createTime":"1400827687000","updateTime":"1400827687000","createUser":"admin","updateUser":"admin","templateId":1,"transient":false},"transient":false},{"id":1,"name":"11212","version":"1121212","user":{"id":17,"email":"test@qq.com","pwd":"098f6bcd4621d373cade4e832627b4f6","sex":"2","createTime":"1400829883000","updateTime":"1400829883000","createUser":"admin","updateUser":"admin","templateId":1,"transient":false},"transient":false}]
            */
    		Template  a = new  Template();
    		a.setId(1);
    		a.setName("dddd");
    		//User  temp = templateRepositoryImpl.getReferenced(a , User.class);
    		//List<User>  list = userRepositoryImpl.queryReferences(a, User.class);
    		List<User> list = templateRepositoryImpl.queryRelation(1, User.class);
    		System.out.println(list.size());
    		
    		//System.out.println(temp);
    		
    		//将该对象关联的USER的内容全部查询出来
			//List<User>   user = templateRepositoryImpl.queryReferences(a, User.class);
    		//templateRepositoryImpl.queryReferences(referenced, referenceClass)
    		//System.out.println(JsonUtil.obj2json(templateRepositoryImpl.queryAll(User.class)));
    	
    		System.out.println("qqwqw");
    		
    		
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
    }


}
