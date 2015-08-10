package com.coul.config.control.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coul.common.model.ResultVo;
import com.coul.config.control.utils.HtmlUtil;
import com.coul.config.entity.Template;
import com.coul.config.service.ITemplateService;
import com.coul.core.base.condition.SimpleCondition;
import com.coul.core.control.action.PagingGridData;
import com.coul.core.control.action.PagingRequestData;
import com.coul.core.control.action.RequestData;
import com.coul.core.domain.model.DataStore;
import com.coul.core.domain.model.PageInfoData;
import com.coul.core.domain.model.PagingParameter;

@Controller
@RequestMapping("/template")
public class TemplateAction extends BaseAction {

	/**
	 * 引入业务服务对象
	 */
	@Autowired
	private ITemplateService templateServieImpl;

	/**
	 * total总页数 
	 * page 当前页
	 * records 查询出的记录数 
	 * rows 包含实际数据的数组 
	 * id行 
	 * rows 每页显示的条数
	 * cell当前行的所有单元格
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/dataList")
	public void datalist(PagingRequestData page, HttpServletResponse response)
			throws Exception {
		PagingGridData<Template> temp = templateServieImpl.queryTemplateDate(page);
		// 设置页面数据
		HtmlUtil.writerJson(response, temp);
	}
	
	
	/**
	 * 
	 * @param page
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/datalistTemplate")
	public void datalistTemplate(PagingRequestData page, HttpServletResponse response)
			throws Exception {
		PagingGridData<Template> temp = templateServieImpl.queryTemplate(page);
		// 设置页面数据
		HtmlUtil.writerJson(response, temp);
	}

	/**
	 * 对于jQgrid表格数据的基本操作 包括增加、修改、查询等动作进行操作
	 * 
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/baseOpt")
	public void baseTemplate(Template model,Integer[] id ,  HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
        //Template model = data.toBean(Template.class);
		if (model == null)
			return;
		// 1.获取jQgrid的操作类型
		String operation = request.getParameter("oper").toString();
		// 2、操作校验不同的请求类型
		if (operation != null && operation.length() > 0
				&& operation.equals("add")) {
			ResultVo res = templateServieImpl.save(model);
			HtmlUtil.writerJson(response, res);
		} else if (operation.equals("edit")) {
			ResultVo res = templateServieImpl.update(model);
			HtmlUtil.writerJson(response, res);
		} else if (operation.equals("del")) {
			ResultVo res = templateServieImpl.delete((long)model.getId());
			HtmlUtil.writerJson(response, res);
		}
	}
	
	
	/**
	 * 表单保存 的操作
	 * @param data
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/fromSave")
	public void fromSave(RequestData data ,  HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		 System.out.println("..........");
	}

	// @Autowired
	// private SiteMainService<SiteMain> siteMainService;
	//
	// @Autowired
	// private SiteTypeService<SiteType> siteTypeService;
	//
	// /**
	// *
	// * @param url
	// * @param classifyId
	// * @return
	// * @throws Exception
	// */
	// @RequestMapping("/list")
	// public ModelAndView list(SiteMainModel model,HttpServletRequest request)
	// throws Exception{
	// Map<String,Object> context = getRootMap();
	// return forword("siteMain/siteMain",context);
	// }
	//
	//
	// /**
	// * ilook 首页
	// * @param url
	// * @param classifyId
	// * @return
	// * @throws Exception
	// */

	//
	// /**
	// * 添加或修改数据
	// * @param url
	// * @param classifyId
	// * @return
	// * @throws Exception
	// */
	// @RequestMapping("/save")
	// public void save(SiteMain bean,Integer[] typeIds,HttpServletResponse
	// response) throws Exception{
	// Map<String,Object> context = new HashMap<String,Object>();
	// bean.setDeleted(DELETED.NO.key);
	// if(bean.getId() == null){
	// siteMainService.add(bean);
	// }else{
	// siteMainService.update(bean);
	// }
	// siteMainService.addTypeRel(typeIds, bean.getId());
	// sendSuccessMessage(response, "保存成功~");
	// }
	//
	//
	// @RequestMapping("/getId")
	// public void getId(Integer id,HttpServletResponse response) throws
	// Exception{
	// Map<String,Object> context = new HashMap();
	// SiteMain bean = siteMainService.queryById(id);
	// if(bean == null){
	// sendFailureMessage(response, "没有找到对应的记录!");
	// return;
	// }
	// List<SiteType> types= siteTypeService.queryBySiteId(bean.getId());
	// if(types != null && !types.isEmpty()){
	// int[] typeIds = new int[types.size()];
	// for(int i=0;i< types.size() ;i++){
	// typeIds[i] = types.get(i).getId();
	// }
	// bean.setTypeIds(typeIds);
	// }
	// context.put(SUCCESS, true);
	// context.put("data", bean);
	// HtmlUtil.writerJson(response, context);
	// }
	//
	//
	//
	// @RequestMapping("/delete")
	// public void delete(Integer[] id,HttpServletResponse response) throws
	// Exception{
	// siteMainService.delete(id);
	// sendSuccessMessage(response, "删除成功");
	// }
	//
	// private String getTypeStr(Integer siteId) throws Exception{
	// List<SiteType> types= siteTypeService.queryBySiteId(siteId);
	// if(types == null || types.isEmpty()){
	// return null;
	// }
	// StringBuffer str = new StringBuffer();
	// int i=1;
	// for(SiteType type : types){
	// str.append(type.getName());
	// if(i < types.size()){
	// str.append(",");
	// }
	// i++;
	// }
	// return str.toString();
	//
	// }
}
