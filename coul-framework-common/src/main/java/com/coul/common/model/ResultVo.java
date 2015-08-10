package com.coul.common.model;

/*
 * @# Result.java	1.0	Feb 28, 2008	2:11:11 PM
 * 
 * Copyright (C) 2007 - 2009 中国电信广东分公司China Telecom - Guangdong Telecom Co.Ltd. 
 * All rights reserved!
 */
import java.util.List;
import java.util.Map;


/**
 * 用于前台service与action之间的交互
 * 
 * @author Huang Weijian
 * @since Feb 28, 2008
 */
public final class ResultVo implements java.io.Serializable{
	private static final long serialVersionUID = 6676226796886237276L;
	/**
	 * 是否操作成功
	 */
	private boolean successful;
	/**
	 * List结果集
	 */
	@SuppressWarnings("rawtypes")
	private List resultList;
	/**
	 * Map结果集
	 */
	@SuppressWarnings("rawtypes")
	private Map resultMap;
	/**
	 * 单一结果
	 */
	private Object result;
	/**
	 * 反馈信息1
	 */
	private String message;
	

	public ResultVo() {
		setSuccessful(false);
	}
	
	public ResultVo(boolean successful, String message) {
		this(successful, message, null);
	}
	
	public ResultVo(boolean successful, StringBuffer message) {
		setSuccessful(successful);
		setMessage(message);
	}

	public ResultVo(boolean successful, String message, Object result) {
		setSuccessful(successful);
		setMessage(message);
		setResult(result);
	}
	
	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	@SuppressWarnings("rawtypes")
	public List getResultList() {
		return resultList;
	}

	@SuppressWarnings("rawtypes")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// 重载
	public void setMessage(StringBuffer message) {
		this.message = message.toString();
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@SuppressWarnings("rawtypes")
	public Map getResultMap() {
		return resultMap;
	}

	@SuppressWarnings("rawtypes")
	public void setResultMap(Map resultMap) {
		this.resultMap = resultMap;
	}

	

	/**
	 * Constructs a <code>String</code> with all attributes in name = value
	 * format.
	 * 
	 * @return a <code>String</code> representation of this object.
	 */
	public String toString() {
		final String TAB = "    ";

		String retValue = "";

		retValue = "Result ( " + super.toString() + TAB + "successful = "
				+ this.successful + TAB + "resultList = " + this.resultList
				+ TAB + "resultMap = " + this.resultMap + TAB + "result = "
				+ this.result + TAB + "message = " + this.message + TAB + " )";

		return retValue;
	}

}

