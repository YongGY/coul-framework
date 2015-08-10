<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ page isErrorPage="true"%>
<%@page import="java.io.PrintWriter"%>

<html>
<head>
<%@include file="bss_common_inc.jsp"%>
</head>
<body class="message_page_body">
	<div class="message_table_container">
		<div class="message">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr style="display: none;">
					<td class="messageTitle" colspan="2">系统提示信息</td>
				</tr>
				<tr>
					<td class="messageBody" colspan="2"><img
						src="${contextPath}/skin/blue/images/common/err.gif" /> <span>系统运行出错，请联系管理员！</span>
					</td>
				</tr>
				<tr id="debugMessageForDev" style="display: none;">
					<td colspan="2" class="debugMessageForDev"><span>调试辅助信息</span>
						<textarea rows="20" cols="100">请求URL：${url}<%
							exception.printStackTrace(new PrintWriter(out));
						%>
						</textarea></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>


