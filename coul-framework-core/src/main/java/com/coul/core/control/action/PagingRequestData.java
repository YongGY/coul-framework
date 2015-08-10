package com.coul.core.control.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.coul.common.utils.JsonUtil;
import com.coul.core.base.condition.AndCondition;
import com.coul.core.base.condition.Condition;
import com.coul.core.base.condition.OrCondition;
import com.coul.core.base.condition.SimpleCondition;
import com.coul.core.domain.model.BaseDomain;
import com.coul.core.domain.model.PagingParameter;

/**
 * 封装JqGrid插件请求的数据 --请求的查询数据/分页数据信息 创建日期：2012-8-15
 * 
 * @author zengshl
 */
public class PagingRequestData extends BaseDomain {
	private static final long serialVersionUID = 4545969837991981187L;

	/** 业务相关的请求数据 */
	private RequestData requestData; // 一般查询条件的数据信息
	/** 当前页数 */
	private int page;
	/** 每页显示的行数 */
	private int rows;
	/** 排序列 */
	private String sidx;
	/** 排序方式 */
	private String sord;

	/**
	 * 查询条件的数据操 groupOp ：表示查询条件 AND或者OR field 字段名称 op 操作类型 data 请求的数据 filters
	 * {"groupOp"
	 * :"AND","rules":[{"field":"id","op":"eq","data":"000"},{"field":"name"
	 * ,"op":"bw","data":"121212" }]}
	 */
	private String filters; // jQgrid 1.7的新特性
	
	private String searchJsonData; //查询条件数据
	
	

	public String getSearchJsonData() {
		return searchJsonData;
	}

	public void setSearchJsonData(String searchJsonData) {
		this.searchJsonData = searchJsonData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	/**
	 * 获得业务相关的请求数据
	 *
	 * @return 创建日期：2012-8-15 修改说明：
	 * @author zengshl
	 */
	public RequestData getRequestData() {
		if(this.getSearchJsonData() != null && this.getSearchJsonData().length() > 0){
			return  new  RequestData(this.getSearchJsonData());
		}
		return requestData;
	}

	public void setRequestData(RequestData requestData) {
		this.requestData = requestData;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	/**
	 * 获得分页参数对象
	 *
	 * @return 创建日期：2012-8-15 修改说明：
	 * @author zengshl
	 */
	public PagingParameter getPaging() {
		if (page < 1 || rows < 1) {
			return null;
		}
		return new PagingParameter((page - 1) * rows, rows);
	}

	/**
	 * 获得排序对象
	 *
	 * @return 创建日期：2012-8-15 修改说明：
	 * @author zengshl
	 */
	public String getString() {
		if (StringUtils.isBlank(sidx)) {
			return null;
		}
		String orderString = sidx;
		if (StringUtils.isNotBlank(sord)) {
			orderString += " " + sord;
		}
		return orderString;
	}
	
	/**
	 * 获取查询条件封装的数据结果
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Condition   getSearchCondtion(){
				
		String searchJson = this.getFilters();
		if(searchJson == null || searchJson.length() <= 0) return null;
		//1.条件数据个转化   
		/*{"groupOp"
	    * :"AND","rules":[{"field":"id","op":"eq","data":"000"},{"field":"name"
	   * ,"op":"bw","data":"121212" }]}*/
		Map<String , Object> jsonMap = new  HashMap<String , Object>();
		if(JsonUtil.isJsonObjectString(searchJson)){
			 //{rules=[{op=eq, field=name, data=111}], groupOp=AND}
			 jsonMap = JsonUtil.parseMap(searchJson);
		}
		
		//2.1查询条件数据的封装
		List<Map<String , Object>>   list = (List<Map<String, Object>>) jsonMap.get("rules");
		List<Condition> components = new ArrayList<Condition>();
		for(int  index = 0 ; index < list.size() ; index++){
			Map<String , Object>  mapValue = list.get(index);
			//2.2 判断条件处理操作
				//components.add(Condition.eq(mapValue.get("field").toString(), mapValue.get("data")));
			String type = Condition.map.get(mapValue.get("op").toString());
			components.add(new SimpleCondition(mapValue.get("field").toString(), type, 
					 mapValue.get("data")));
		}
		
		
		//2.2 根据获取查询的英子条件进行区分
		if(jsonMap.get("groupOp") != null & jsonMap.get("groupOp").toString().trim().equals("AND")){
			//2.2.1 and的查询条件
			AndCondition  andCon = new  AndCondition();
			andCon.setComponents(components);
			return andCon;
		}else{
			//2.2.2 or的查询条件
			OrCondition  orCon = new  OrCondition();
			orCon.setComponents(components);
			return orCon;
		}
		
	}

}
