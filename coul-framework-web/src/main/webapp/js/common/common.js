var path_prefix = "";
var current_filename = "";
var current_fileprefix = "";
var current_menuId = "";
var onService = false;
var glb_loginName = "";
var DWRLoadButtonProc = false;//dwr的加载按钮致灰控制
var pathname = location.pathname.substring(1);
var DEAULT_SYSTEM = "200";//默认为易销售web端
var webroot="esale/";
if(pathname.indexOf(webroot) == -1){
   pathname='/'+pathname;
}
if(pathname.indexOf('/') > -1) {
	onService = true;
	pathname = pathname.substring(pathname.indexOf('/') + 1);
	var path_array = pathname.split("/");
	if(pathname.indexOf('/') > -1){
		for (var i = 0; i < path_array.length - 1; i++){
			path_prefix += "../";
		}
	}else{
      path_prefix="./";
	}
}




document.write('<meta HTTP-EQUIV="Pragma" CONTENT="no-cache"/>');
document.write('<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache"/>');
document.write('<meta HTTP-EQUIV="Expires" CONTENT="0"/>');


 /** 公共资源CSS,JS  */
document.write('<link type="text/css" rel="stylesheet" href="' + path_prefix + 'ffcs-atte-web/js/bootstrap/css/bootstrap.min.css" />');
document.write('<link type="text/css" rel="stylesheet" href="' + path_prefix + 'ffcs-atte-web/js/bootstrap/css/bootstrap-theme.min.css" />');
document.write('<link type="text/css" rel="stylesheet" href="' + path_prefix + 'ffcs-atte-web/css/atteCss.css" />');
document.write('<link type="text/css" rel="stylesheet" href="' + path_prefix + 'ffcs-atte-web/css/docs.css" />');
/***
document.write('<link type="text/css" rel="stylesheet" href="' + path_prefix + 'ffcs-atte-web/css/style-metronic.css" />');
document.write('<link type="text/css" rel="stylesheet" href="' + path_prefix + 'ffcs-atte-web/css/style.css" />');
*/
/**
 *JS对数据请求的控制
 */
document.write('<script language="JavaScript" src="' + path_prefix + 'ffcs-atte-web/js/jquery/jquery-1.11.2.min.js"></script>');
document.write('<script language="JavaScript" src="' + path_prefix + 'ffcs-atte-web/js/bootstrap/js/bootstrap.min.js"></script>');
document.write('<script language="JavaScript" src="' + path_prefix + 'ffcs-atte-web/js/bootstrap/js/jquery.bootstrap.teninedialog.v3.js"></script>');

document.write('<!--[if lt IE 11]>');
document.write('<script language="JavaScript" src="' + path_prefix + 'ffcs-atte-web/js/bootstrap/js/html5shiv.js"></script>');
document.write('<script language="JavaScript" src="' + path_prefix + 'ffcs-atte-web/js/bootstrap/js/respond.min.js"></script>');
document.write('<![endif]-->');

