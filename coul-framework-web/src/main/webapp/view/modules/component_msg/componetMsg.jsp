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
 </i> &nbsp;&nbsp; 自动化受理-》构件配置管理（构件基础数据管理）</p>

<!-- 配置基础数据操作信息表格 -->
<div class="widget-box">
	<div class="widget-header header-color-grey">
		<h5>构件配置管理</h5>
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
	src="modules/component_msg/js/componetMsg.js"></script>
</body>
</html>

