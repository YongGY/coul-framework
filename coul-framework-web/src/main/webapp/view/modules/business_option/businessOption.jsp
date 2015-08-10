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
 </i>&nbsp;&nbsp;自动化受理-》业务受理事件(测试的每一个案例涉及相关的模块、构件、信息项信息视图)</p>

<div class="row">
	<!-- 业务受理树信息展现 -->
	<div class="col-xs-3">
		<div class="accordion-style1 panel-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a href="#collapseOne" data-parent="#accordion"
							data-toggle="collapse" class="accordion-toggle"> <i
							data-icon-show="icon-angle-right"
							data-icon-hide="icon-angle-down"
							class="bigger-110 icon-angle-down"></i> &nbsp;集团4G业务受理案例
						</a>
					</h4>
				</div>

				<div id="collapseOne" class="panel-collapse in"
					style="height: auto;">
					<div class="panel-body">
						<a class="btn btn-default btn-app radius-4 btn-sm" href="#"> <i
							class="icon-cog bigger-160"></i> 4G新装 
						</a>
						
						<a class="btn btn-default btn-app radius-4 btn-sm" href="#"> <i
							class="icon-cog bigger-160"></i> 4G变更 
						</a>
						<a class="btn btn-default btn-app radius-4 btn-sm" href="#"> <i
							class="icon-cog bigger-160"></i> 4G新装 
						</a>
						
						<a class="btn btn-default btn-app radius-4 btn-sm" href="#"> <i
							class="icon-cog bigger-160"></i> 4G变更 
						</a>
						<a class="btn btn-default btn-app radius-4 btn-sm" href="#"> <i
							class="icon-cog bigger-160"></i> 4G新装 
						</a>
						
						<a class="btn btn-default btn-app radius-4 btn-sm" href="#"> <i
							class="icon-cog bigger-160"></i> 4G变更 
						</a>
						<a class="btn btn-default btn-app radius-4 btn-sm" href="#"> <i
							class="icon-cog bigger-160"></i> 4G新装 
						</a>
						
						<a class="btn btn-default btn-app radius-4 btn-sm" href="#"> <i
							class="icon-cog bigger-160"></i> 4G变更 
						</a>
					</div>
				</div>
			</div>

			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a href="#collapseTwo" data-parent="#accordion"
							data-toggle="collapse" class="accordion-toggle collapsed"> <i
							data-icon-show="icon-angle-right"
							data-icon-hide="icon-angle-down"
							class="bigger-110 icon-angle-right"></i> &nbsp;3G融合套餐业务受理
						</a>
					</h4>
				</div>

				<div id="collapseTwo" class="panel-collapse collapse"
					style="height: 0px;">
					<div class="panel-body"></div>
				</div>
			</div>

			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a href="#collapseThree" data-parent="#accordion"
							data-toggle="collapse" class="accordion-toggle collapsed"> <i
							data-icon-show="icon-angle-right"
							data-icon-hide="icon-angle-down"
							class="bigger-110 icon-angle-right"></i> &nbsp;宽带业务受理
						</a>
					</h4>
				</div>

				<div id="collapseThree" class="panel-collapse collapse"
					style="height: 0px;">
					<div class="panel-body"> </div>
				</div>
			</div>
		</div>


	</div>

	<!-- 表格的业务数据展现 -->
	<div class="col-xs-9">

		<!-- 关联信息操作数据表格 start -->
		<div class="widget-box" style="opacity: 1;">
			<div class="widget-header header-color-grey">
				<h5>关联模板的执行列表顺序</h5>
				<div class="widget-toolbar no-border">
					<button class="btn btn-xs btn-light bigger">关联关系</button>
					<button data-toggle="dropdown"
						class="btn btn-xs bigger btn-yellow dropdown-toggle">删除关系</button>
					<a class="btn btn-purple btn-sm" id="id-btn-dialog1" href="#">弹出窗口</a>
				</div>
			</div>

			<div class="widget-body">
				<div class="widget-body-inner" style="display: block;">
					加载模板数据中.....
					<table id="grid-table-relation"></table>
					<div id="grid-pager-relation"></div>
				</div>
			</div>
		</div>

		<div class="hr hr-double hr-dotted hr18"></div>

		<!-- 关联信息操作数据表格 start -->
		<div class="widget-box" style="opacity: 1;">
			<div class="widget-header header-color-grey">
				<h5>模板关联构件的执行顺序</h5>
				<div class="widget-toolbar no-border">
					<button class="btn btn-xs btn-light bigger">关联关系</button>
					<button data-toggle="dropdown"
						class="btn btn-xs bigger btn-yellow dropdown-toggle">删除关系</button>
					<a class="btn btn-purple btn-sm" id="id-btn-dialog1" href="#">执行顺序流图展现</a>
				</div>
			</div>

			<div class="widget-body">
				<div class="widget-body-inner" style="display: block;">

					加载构件数据中.....
					<table id="grid-table-relation"></table>
					<div id="grid-pager-relation"></div>
				</div>
			</div>
		</div>

		<div class="hr hr-double hr-dotted hr18"></div>

		<!-- 关联信息操作数据表格 start -->
		<div class="widget-box" style="opacity: 1;">
			<div class="widget-header header-color-grey">
				<h5>关键关联的信息项的内容</h5>
				<div class="widget-toolbar no-border">
					<button class="btn btn-xs btn-light bigger">关联关系</button>
					<button data-toggle="dropdown"
						class="btn btn-xs bigger btn-yellow dropdown-toggle">删除关系</button>
					<a class="btn btn-purple btn-sm" id="id-btn-dialog1" href="#">弹出窗口</a>
				</div>
			</div>

			<div class="widget-body">
				<div class="widget-body-inner" style="display: block;">
					<!-- 加载信息 -->
					加载信息项的数据中....
					<table id="grid-table-relation"></table>
					<div id="grid-pager-relation"></div>
				</div>
			</div>
		</div>

	</div>
</div>







<!-- /.row -->

<script type="text/javascript"
	src="<%=contextPath%>/view/modules/business_option/js/businessOption.js"></script>
</body>
</html>

