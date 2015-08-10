<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
</head>
<%@include file="/common/resourceCom.jsp"%>
<body>
    
	<div class="navbar navbar-default navbar-fixed-top" id="navbar">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed');
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"> <small> <i
						class="icon-leaf"></i> 自动化受理系统配置
				</small>
				</a>
				<!-- /.brand -->
			</div>
			<!-- /.navbar-header -->

			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">

					<li class="light-blue"><a data-toggle="dropdown" href="#"
						class="dropdown-toggle"> <span class="user-info"> <small>Welcome,admin</small>
						</span>
					</a></li>
				</ul>
				<!-- /.ace-nav -->
			</div>
			<!-- /.navbar-header -->
		</div>
		<!-- /.container -->
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>

			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'fixed')
					} catch (e) {
					}
				</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="icon-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="icon-pencil"></i>
						</button>

						<button class="btn btn-warning">
							<i class="icon-group"></i>
						</button>

						<button class="btn btn-danger">
							<i class="icon-cogs"></i>
						</button>
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span> <span class="btn btn-info"></span>

						<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
					</div>
				</div>
				<!-- #sidebar-shortcuts -->

				<ul class="nav nav-list">
					<li><a href="#" onclick="changMainMsg('受理事件管理')"> <i class="icon-dashboard"></i> <span
							class="menu-text" > 受理事件管理
						</span>
					</a></li>

					<li><a href="#" onclick="changMainMsg('模块配置管理')"> <i class="icon-text-width"></i> <span
							class="menu-text" > 模块配置管理
						</span>
					</a></li>

					<li><a href="#" class="dropdown-toggle" onclick="changMainMsg('构件库配置管理')"> <i
							class="icon-desktop"></i> <span class="menu-text"> 构件库配置管理
						</span>
					</a></li>

					<li><a href="#" class="dropdown-toggle" onclick="changMainMsg('业务数据配置管理')"> <i
							class="icon-edit"></i> <span class="menu-text"> 业务数据配置管理 </span>
					</a></li>
				</ul>
				<!-- /.nav-list -->
                <div id="sidebar-collapse" class="sidebar-collapse">
						<i data-icon2="icon-double-angle-right" data-icon1="icon-double-angle-left" class="icon-double-angle-right"></i>
				</div>


				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'collapsed')
					} catch (e) {
					}
				</script>
			</div>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>

					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon"> <input type="text"
								placeholder="Search ..." class="nav-search-input"
								id="nav-search-input" autocomplete="off" /> <i
								class="icon-search nav-search-icon"></i>
							</span>
						</form>
					</div>
					<!-- #nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" id="mainContext">
							<!-- PAGE CONTENT BEGINS -->
							<!-- PAGE CONTENT ENDS -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->

		</div>
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->

	<!--[if !IE]> -->



	<script type="text/javascript">
		//初值化菜单内容的定位
		$(function() {

			//初值化菜单的方法
			//默认加载首页的数据信息
			var mainContext = $("#mainContext");
			mainContext.load("modules/dataConfig/NewFile.html");

		});

		//触发点
		function changMainMsg(msg) {
			//if(msg = undefined) return;
			var mainContext = $("#mainContext");
			//1 清空数据内容
			mainContext.html('');
			if (msg == '受理事件管理') {
				mainContext.load("modules/business_option/businessOption.jsp");
			} else if (msg == '模块配置管理') {
				mainContext.load("modules/service_event/eventList.jsp");
			}else if (msg == '业务数据配置管理') {
				mainContext.load("modules/business_datamsg/businessDatamsg.jsp");
			}else if (msg == '构件库配置管理') {
				mainContext.load("modules/component_msg/componetMsg.jsp");
			}

			
		}
	</script>

</body>
</html>
