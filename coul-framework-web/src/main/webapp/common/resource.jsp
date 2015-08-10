<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String contextPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;   
%>
<%--
1. 默认加载jquery和easyui.--%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="<%=contextPath%>/skin/default/css/common/default.css"
	rel="stylesheet" type="text/css" />
<link href="<%=contextPath%>/skin/blue/css/top.css" rel="stylesheet"
	type="text/css" />
<link href="<%=contextPath%>/js/jquery/jquery-easyui/themes/icon.css"
	rel="stylesheet" type="text/css" />
<link href="<%=contextPath%>/js/jquery/jquery-easyui/themes/default/easyui.css"
	rel="stylesheet" type="text/css" />
	
<%--扩展的基础样式 --%>
<link href="<%=contextPath%>/css/base.css"
	rel="stylesheet" type="text/css" />
	
<script type="text/javascript">   		
	   	var path = "<%=path%>"; 
	   	var contextPath="<%=contextPath%>";
</script>
<script type="text/javascript"
	src="<%=contextPath%>/js/jquery/1.8.0/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/js/jquery/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/js/jquery/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="<%=contextPath%>/js/eloader.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/common.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/package.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/jquery/json/jquery.json.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/base.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/EventBus/EventBus.js"></script>
<%-- <script type="text/javascript" src="<%=contextPath%>/js/eventDriven.js.js"></script> --%>
