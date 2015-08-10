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
<p class="alert alert-success"><i class="icon-tag">
 </i> &nbsp;&nbsp; 自动化受理-》模块配置管理（模块基础数据管理）</p>

<!-- 配置基础数据操作信息表格 -->
<div class="widget-box">
	<div class="widget-header header-color-grey">
		<h5>模块配置管理</h5>
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

<!-- 关联信息操作数据表格 start -->
<div class="widget-box" style="opacity: 1;">
	<div class="widget-header header-color-grey">
		<h5>模板关联的构件信息列表</h5>
		<div class="widget-toolbar no-border">
			<button class="btn btn-xs btn-light bigger">关联关系</button>
			<button data-toggle="dropdown"
				class="btn btn-xs bigger btn-yellow dropdown-toggle">删除关系</button>
			<a class="btn btn-purple btn-sm" id="id-btn-dialog1" href="#">弹出窗口</a>
		</div>
	</div>

	<div class="widget-body">
		<div class="widget-body-inner" style="display: block;">
			<table id="grid-table-relation"></table>
			<div id="grid-pager-relation"></div>
		</div>
	</div>
</div>

<!--关联关系添加弹出窗体数据信息-->

<div id="dialog-message" class="hide">
	<form role="form" class="form-horizontal" id="templateUpdate"
		action="../template/fromSave.html">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"> 模板名称</label>
			<div class="col-sm-9">
				<input type="text" class="col-xs-10 col-sm-5" placeholder="Username"
					name="userName" id="name">
			</div>
		</div>

		<div class="space-1"></div>

		<div class="form-group">
			<label for="form-field-2"
				class="col-sm-3 control-label no-padding-right"> Password
				Field </label>

			<div class="col-sm-9">
				<input type="password" class="col-xs-10 col-sm-5"
					placeholder="Password" id="password" name="password" />
			</div>
		</div>
	</form>
</div>
<!-- #dialog-message -->

<!-- /.row -->

<script type="text/javascript"
	src="modules/service_event/js/eventList.js"></script>
</body>
</html>

