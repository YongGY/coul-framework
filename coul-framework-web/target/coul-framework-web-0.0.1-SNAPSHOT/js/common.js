/**
 * JQgrid的样式的重新定义操作-符合bootstrap
 */

/**
 * editoptions
 * 
 * 
 * array
 * 编辑的一系列选项。{name:’__department_id’,index:’__department_id’,width:200,editable:true,edittype:’select’,editoptions:
 * {dataUrl:”${ctx}/admin/deplistforstu.action”}},这个是演示动态从服务器端获取数据。
 * empty
 * 
 * editrules
 * array
 * 
 * 编辑的规则{name:’age’,index:’age’, width:90,editable:true,editrules:
 * {edithidden:true,required:true,number:true,minValue:10,maxValue:100}},设定
 * 年龄的最大值为100，最小值为10，而且为数字类型，并且为必输字段。
 * empty
 * 
 * edittype
 * string 
 * 可以编辑的类型。可选值：text, textarea, select, checkbox, password, button, image and
 * file.
 * text
 */

/*
 * 设计新增表单风格的设计
 */
function style_edit_form(form) {
	// enable datepicker on "sdate" field and switches for "stock" field
	form.find('input[name=sdate]').datepicker({
		format : 'yyyy-mm-dd',
		autoclose : true
	}).end().find('input[name=stock]').addClass('ace ace-switch ace-switch-5')
			.wrap('<label class="inline" />')
			.after('<span class="lbl"></span>');

	// update buttons classes
	var buttons = form.next().find('.EditButton .fm-button');
	buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();// ui-icon,
	// s-icon
	buttons.eq(0).addClass('btn-primary').prepend('<i class="icon-ok"></i>');
	buttons.eq(1).prepend('<i class="icon-remove"></i>')
	buttons = form.next().find('.navButton a');
	buttons.find('.ui-icon').remove();
	buttons.eq(0).append('<i class="icon-chevron-left"></i>');
	buttons.eq(1).append('<i class="icon-chevron-right"></i>');
}

/*
 */
function style_delete_form(form) {
	var buttons = form.next().find('.EditButton .fm-button');
	buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();// ui-icon,
	// s-icon
	buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
	buttons.eq(1).prepend('<i class="icon-remove"></i>')
}

function style_search_filters(form) {
	form.find('.delete-rule').val('X');
	form.find('.add-rule').addClass('btn btn-xs btn-primary');
	form.find('.add-group').addClass('btn btn-xs btn-success');
	form.find('.delete-group').addClass('btn btn-xs btn-danger');
}
function style_search_form(form) {
	var dialog = form.closest('.ui-jqdialog');
	var buttons = dialog.find('.EditTable')
	buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info')
			.find('.ui-icon').attr('class', 'icon-retweet');
	buttons.find('.EditButton a[id*="_query"]').addClass(
			'btn btn-sm btn-inverse').find('.ui-icon').attr('class',
			'icon-comment-alt');
	buttons.find('.EditButton a[id*="_search"]').addClass(
			'btn btn-sm btn-purple').find('.ui-icon').attr('class',
			'icon-search');
}

function beforeDeleteCallback(e) {
	var form = $(e[0]);
	if (form.data('styled'))
		return false;

	form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner(
			'<div class="widget-header" />')
	style_delete_form(form);

	form.data('styled', true);
}

function beforeEditCallback(e) {
	var form = $(e[0]);
	form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner(
			'<div class="widget-header" />')
	style_edit_form(form);
}

// replace icons with FontAwesome icons like above
function updatePagerIcons(table) {
	var replacement = {
		'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
		'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
		'ui-icon-seek-next' : 'icon-angle-right bigger-140',
		'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
			.each(function() {
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				if ($class in replacement)
					icon.attr('class', 'ui-icon ' + replacement[$class]);
			})
}

function enableTooltips(table) {
	$('.navtable .ui-pg-button').tooltip({
		container : 'body'
	});
	$(table).find('.ui-pg-div').tooltip({
		container : 'body'
	});
}

/**
 * jQgrid工具栏的设置，包括分页的数据信息
 * 各个工具按钮的个性化定制
 * 
 * 
 */
function jQgridToolContailPage(grid_selector, pager_selector,config) {
     
	// 按钮组清单 --公共的模型信息
	jQuery(grid_selector).jqGrid(
			'navGrid',
			pager_selector,
			{ // navbar options
				edit : true,
				editicon : 'icon-pencil blue',
				add : true,
				addicon : 'icon-plus-sign purple',
				// addtext : '', 添加边界名称
				del : true,
				delicon : 'icon-trash red',
				search : true,
				searchicon : 'icon-search orange',
				refresh : true,
				refreshicon : 'icon-refresh green',
				view : true,
				viewicon : 'icon-zoom-in grey',
			},
			{
				recreateForm : true,
				beforeShowForm : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
							.wrapInner('<div class="widget-header" />')
					style_edit_form(form);
				}
			},
			{
				// new record form
				closeAfterAdd : true,
				recreateForm : true,
				viewPagerButtons : false,
				beforeShowForm : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
							.wrapInner('<div class="widget-header" />')
					style_edit_form(form);
				}
			},
			{
				// delete record form
				recreateForm : true,
				beforeShowForm : function(e) {
					var form = $(e[0]);
					if (form.data('styled'))
						return false;
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
							.wrapInner('<div class="widget-header" />')
					style_delete_form(form);
					form.data('styled', true);
				}
			},
			{
				// search form
				recreateForm : true,
				afterShowSearch : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-title')
							.wrap('<div class="widget-header" />')
					style_search_form(form);
				},
				afterRedraw : function() {
					style_search_filters($(this));
				},
				multipleSearch : true,
			},
			{
				// view record form
				recreateForm : true,
				beforeShowForm : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-title')
							.wrap('<div class="widget-header" />')
				}
			});
}



/**
 * 刷新表格的数据写法
 * 
 * $("#search_btn").click(function(){  
    //此处可以添加对查询数据的合法验证  
    var orderId = $("#orderId").val();  
    $("#list4").jqGrid('setGridParam',{  
        datatype:'json',  
        postData:{'orderId':orderId}, //发送数据  
        page:1  
    }).trigger("reloadGrid"); //重新载入  
});
   ① setGridParam用于设置jqGrid的options选项。返回jqGrid对象
   ② datatype为指定发送数据的格式；
   ③ postData为发送请求的数据，以key:value的形式发送，多个参数可以以逗号”,”间隔；
   ④ page为指定查询结果跳转到第一页；
   ⑤ trigger(“reloadGrid”);为重新载入jqGrid表格
 */

// /*
// * 前端各类数据请求的格式
// *
// */
//
//
// //1、MAP数据请求的定义
//
// var maprequest = new Map();
// maprequest.put("name" , 'zengshl');
// maprequest.put("password" , "ddddd");
//
// //2、定义对象的请求数据格式 ---在请求解析是JSON的数据格式
//
// var obj = {};
// obj.name = 'zengshl';
// obj.password = 'dddddd';
//
// //3、json的定义请求数据格式
//
// var jsonData = { name:"中国",
//
// province:[
//
// {name:"黑龙江", cities:{city:["哈尔滨","大庆"] } },
//
// {name:"广东", cities:{city:["广州","深圳","珠海"] }},
//
// {name:"台湾", cities:{city:["台北","高雄"]} },
//
// {name:"新疆", cities:{city:["乌鲁木齐"]} }
//
// ]
//
// }
//
// //定义个对象数据格式
// function Obj(user,pwd){ //use constructor
// this.user=user;
// this.pwd=pwd;
// this.get=get;
// return this;
// }
//
// var obj=new Object();
// obj.user="ssss";
// obj.pwd="sdsd";
// obj.get="sddddd";



/*
 * js经常用到Callback的写法---一定要记住了
 * --回调的方式一般有很多中，比如是函数，数据类型
 * */

/*函数A--作为被调用的函数*/
function  call(name , password , callback){
	
	//函数的回调方式-目前一般都是使用函数的调用方式
	if($.isFunction(callback)){
		    var  data = '122212';
			callback(data);
	}
	
	//参数的调用的方式-
	//callback = '121212';
}



/*函数B--作为调用者的写法*/
function  callA(){
	
	//这就是调用A反馈回来的数据，进行处理一番
	call('11212', '1212' , function(data){
		alert(data);
	});
	
	
}
