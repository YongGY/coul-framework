<%@ page language="java" pageEncoding="GBK"%>
<%@ page isELIgnored="false"%>
<%
	request.setAttribute("itpubTitle", "�й����Ź�˾XXXϵͳ");
	request.setAttribute("modTitle", "ϵͳ����");
	request.setAttribute("pageTitle", "������ʾ");
	String contextPath = (String) request.getContextPath();
	request.setAttribute("contextPath", contextPath);
	String requestUrl = request.getServletPath()
			+ (request.getQueryString() == null ? "" : "?"
					+ request.getQueryString());
	request.setAttribute("url", requestUrl);
%>
<title>${itpubTitle}-${modTitle}-${pageTitle}</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<script type="text/javascript">
	var contextPath = '${contextPath}';
</script>
<link rel="shortcut icon" type="image/x-icon"
	href="${contextPath}/skin/blue/images/chinanetyellow.ico">
<link href="${contextPath}/skin/blue/css/default.css" rel="stylesheet"
	type="text/css" />
<link href="${contextPath}/skin/blue/css/table.css" rel="stylesheet"
	type="text/css" />
<link href="${contextPath}/common/bss_common_inc.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="<%=contextPath%>/js/jquery/1.8.0/jquery-1.8.0.min.js"></script>

<script type="text/javascript"
	src="${contextPath}/js/jquery/hotkeys/jquery.hotkeys-0.7.8.js"></script>
<script type="text/javascript">
	$(function($) {
		var show = false;
		$(document).bind('keydown', 'Alt+Shift+S', function() {
			if (show == true) {
				$("#debugMessageForDev").hide();
			} else {
				$("#debugMessageForDev").show();
			}
			show = !show;
		});

	});
</script>
