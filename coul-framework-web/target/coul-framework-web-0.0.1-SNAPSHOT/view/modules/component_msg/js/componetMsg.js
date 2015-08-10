/*******************************************************************************
 * 最基础的增加、修改、删除、的操作
 * 
   author  zengshl
 * data    2015/6/5
 *******************************************************************************/

/*
 * （×××）本人比较喜欢采用常规的JS写法的三步曲操作
 * 
 */

// 全局变量的定制这边
// 1、界面请求的URL地址
var QUERYTEMPLATE_PATH = "../template/dataList.html";

/* 初学者可以理解为JAVA的mian函数 */
$(function() {
	
	// 0、界面需要应用的全局变量和属性信息

	// 1、初值化的方法-界面加载需要实现的方法有
	initInterface();

	// 2、事件触发的方法-界面点击事件产生的方法
	$("#id-btn-dialog1").bind('click', onClickRelationTemplate);
	
	// 3、辅助的一些函数和方法实现-个性化定制
	//3.1窗口变化值表格数据的变化
	$(window).resize(function() {
		$("#grid-table").setGridWidth($("#template-widget").width());
	});
	//3.2  ???????????????????不能够写死，寻找好办法
	$("#sidebar-collapse").bind('click' ,function(){
		var width1 = $("#template-widget").width();
		var width2 = $("#sidebar-shortcuts").width(); 
		if(width2 > 100){
			$("#grid-table").setGridWidth($("#template-widget").width()+147);
		}else{
			$("#grid-table").setGridWidth($("#template-widget").width()-147);
		}
		
	});
	
	

});

/**
 * 1、初值化界面函数
 */

function initInterface() {
	// 1.1 初值界面的表格的数据信息，默认选择第一行的数据
	initTemplate();
}

// 1.1 初值界面的表格的数据信息，默认选择第一行的数据
function initTemplate() {
	// 模板的数据表格
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";

	jQuery(grid_selector).jqGrid({
		url : '../template/dataList.html',
		datatype : "json",
		height : 250,
		colNames : [ ' ', '标识', '模板名称', '版本' ],
		colModel : [ {name :  '',index : '',width : 80,fixed : true,sortable : false,resize : false,formatter : 'actions'  },  //--jQgrid自带的的
		             {name : 'id',index : 'id',width : 60,sorttype : "int",editable : true,hidedlg:false}, 
		             {name : 'name',index : 'name',width : 150,editable : true,editoptions : {size : "20",maxlength : "30"}}, 
		             {name : 'version',index : 'version',width : 150,sortable : false,editable : true,edittype : "textarea",
		            	 editoptions : {rows : "2",cols : "10"}} ],
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : pager_selector,
		altRows : true,
		multiselect : true,
		multiboxonly : true,
		editurl : "../template/baseOpt.html", // 关键的URL字段负责数据的添加修改删除等操作
		loadComplete : function() {
			var table = this;
			setTimeout(function() {
				updatePagerIcons(table);
				enableTooltips(table);
			}, 0);
		},
		autowidth : true,
		onSelectRow:function(rowid){
			//加载表格的数据
		    $("#grid-table-relation").jqGrid('setGridParam',{  
		        datatype:'json',  
		        postData:{'orderId':rowid}, //发送数据  
		        page:1  
		    }).trigger("reloadGrid"); //重新载入  
		}

	});
	// 初值化表格的分页信息，和工具栏信息
	jQgridToolContailPage(grid_selector, pager_selector);

}

// 1.1.1 表格的辅助函数，字段匹配
/* 数据匹配过程 */
function aceSwitch(cellvalue, options, cell) {
	setTimeout(function() {
		$(cell).find('input[type=checkbox]').wrap('<label class="inline" />')
				.addClass('ace ace-switch ace-switch-5').after(
						'<span class="lbl"></span>');
	}, 0);
}
// enable datepicker
function pickDate(cellvalue, options, cell) {
	setTimeout(function() {
		$(cell).find('input[type=text]').datepicker({
			format : 'yyyy-mm-dd',
			autoclose : true
		});
	}, 0);
}


