<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String contextPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 1、默认加载的CSS -->
<link href="<%=contextPath%>/assets/css/bootstrap.min.css"rel="stylesheet" type="text/css" />
<link rel="stylesheet"href="<%=contextPath%>/assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/assets/css/ace-fonts.css" />

<!-- 扩展的CSS文件 -->
<link rel="stylesheet" href="<%=contextPath%>/assets/css/jquery-ui-1.10.3.full.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/assets/css/ui.jqgrid.css" />
<link rel="stylesheet" href="<%=contextPath%>/assets/css/datepicker.css" />

<!-- ace styles -->
<link rel="stylesheet" href="<%=contextPath%>/assets/css/ace.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/assets/css/ace-rtl.min.css" />
<link rel="stylesheet"  href="<%=contextPath%>/assets/css/ace-skins.min.css" />


<!-- 2、默认加载的JS文件信息 -->
<script src="<%=contextPath%>/assets/js/ace-extra.min.js"></script>
<script src="<%=contextPath%>/assets/js/jquery-2.0.3.min.js"></script>
<script src="<%=contextPath%>/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<script src="<%=contextPath%>/assets/js/jquery.mobile.custom.min.js"></script>

<script src="<%=contextPath%>/assets/js/bootstrap.min.js"></script>
<script src="<%=contextPath%>/assets/js/typeahead-bs2.min.js"></script>

<!-- ace scripts -->
<script src="<%=contextPath%>/assets/js/ace-elements.min.js"></script>
<script src="<%=contextPath%>/assets/js/ace.min.js"></script>


<!-- 3、扩展加载的JS文件信息 -->
<!-- 3.1 表格的控件 -->


<script src="<%=contextPath%>/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="<%=contextPath%>/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>

<!-- 3.2 日期的控件 -->
<script src="<%=contextPath%>/assets/js/date-time/bootstrap-datepicker.min.js"></script>


<!-- 公共的JS信息 -->
<script src="<%=contextPath%>/js/common.js"></script>
<script src="<%=contextPath%>/js/common/package.js"></script>
<script src="<%=contextPath%>/js/common/jquery.form.js"></script>
<script src="<%=contextPath%>/js/common/map.js"></script>
<script src="<%=contextPath%>/js/common/base.js"></script>





