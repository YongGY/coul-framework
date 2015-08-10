package com.coul.core.domain.model;

import java.util.List;

/**
 * 对于前端不同的JS数据分页要求的格式存在的差异进行重新定义  jQgrid的表格数据格式
 * @author zengshl
 *
 */
public class PageInfoData {
	
	/**
	 * 当前的页码
	 */
	private  int   page ;
	
	/**
	 * 总页数多少
	 */
	private  int   total;
	
	/**
	 * 总记录数
	 */
	private  int   records;
	
	/**
	 * 分页的数据清单
	 */
	
	private  int  rows;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	} 
	
	
	/**
	 * 获取查询的开始位置
	 * @return
	 */
	public int  getStartIndex(){
		if(this.page == 0) return 0;
		return (this.page-1)*this.rows;
	}
	
	/**
	 * 获取总页数信息
	 * @return
	 */
	public int  getAllPage(){
		if(this.records % this.rows == 0 )
		 return (this.records/this.rows);
		else
		 return (this.records/this.rows)+1; 
	}

	

}
