<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String contextPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<head>
</head>

<!-- 导航的位置 -->
<p class="alert alert-success" style="margin-bottom: 3px;">
	<i class="icon-tag"> </i> &nbsp;&nbsp; 自动化受理-》业务数据配置管理（业务数据-界面的数据信息内容）
</p>

<!-- 查询条件的内容写法 -->
<div class="clearfix form-actions" style="margin: 0px;">

	<div class="col-md-10">
		<form action="" class="form-horizontal"  id="searchFrom">
			<div class="from-group">
				<div class="col-sm-1 control-label no-padding-right">
					版本:</div>
				<div class="col-sm-3">
					<input type="text"  name="version"
						id="version">
				</div>
				
				<label class="col-sm-1 control-label no-padding-right">
					信息名称:</label>
				<div class="col-sm-3">
					<input type="text"  name="name"
						id="name">
				</div>
				
				<label class="col-sm-1 control-label no-padding-right">
					信息名称:</label>
				<div class="col-sm-3">
					<input type="text"  name="userName"
						id="userName">
				</div>

			</div>
		</form>
	</div>
	<div class="col-md-2">
		<button type="button" class="btn btn-sm btn-info"  id="searchButton">
			<i class="icon-search bigger-110"></i> 查询
		</button>
		&nbsp;
		<button type="reset" class="btn btn-sm">
			<i class="icon-undo bigger-110"></i> 重置
		</button>
	</div>
</div>

<!-- 业务数据操作信息表格 -->
<div class="widget-box">
	<div class="widget-header header-color-grey">
		<h5>业务数据管理</h5>
	</div>
	<div class="widget-body" id="template-widget">
		<div class="widget-body-inner"
			style="display: block; padding-right: 17px;">
			<table id="grid-table"></table>
			<div id="grid-pager"></div>

		</div>
	</div>
</div>
<!-- END配置基础数据操作表格 -->

<div class="hr hr-double hr-dotted hr18"></div>

<!-- /.row -->

<script type="text/javascript"
	src="modules/business_datamsg/js/businessDatamsg.js"></script>
</body>
</html>

