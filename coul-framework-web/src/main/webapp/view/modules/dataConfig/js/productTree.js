/**
 * $package('product.main');
 * 初值化界面的中属性和方法
 * @return {TypeName} 
 */

var ProductTree = $('#product-tree');
var subProductTree = $('#supProductId');
var ProdProvComGrid=$('#productProviderId');

var rightMenu = $('#right-menu');
var Form = {
	catalogEdit : $("#editCatalogForm"),
	productEdit : $("#editProdForm")
}
//Win 窗口
var Win = {
	catalog : $("#editCatalogWin"),
	product : $("#editProdWin")
}

var Events = {
	//刷新Grid 数据
	refreshTree : function(callback) {
		var param = ProductTree.tree('getSelected');
		if (!param) {
			param = ProductTree.tree('getRoot');
		}
		ProductTree.tree('reload', param.target);
		//回调函数
		if (jQuery.isFunction(callback)) {
			callback();
		}
	},
	closeCatalog : function() {
		YiYa.confirm('提示', '确定关闭窗口?', function(r) {
			if (r) {
				Win.catalog.dialog('close');
				Events.refreshTree();
			}
		});
	},
	closeProduct : function() {
		YiYa.confirm('提示', '确定关闭窗口?', function(r) {
			if (r) {
				Win.product.dialog('close');
				Events.refreshTree();
			}
		});
	}
}
Win.catalog.dialog( {
	buttons : [ {
		text : '保存',
		handler : saveCatalogItem
	}, {
		text : '关闭',
		handler : Events.closeCatalog
	} ]
});



function addTab(_title,_url){
			var boxId = '#right-tab-box';
			if($(boxId).tabs('exists',_title) ){
				var tab = $(boxId).tabs('getTab',_title);
				var index = $(boxId).tabs('getTabIndex',tab);
				$(boxId).tabs('select',index);
				if(tab && tab.find('iframe').length > 0){  
					 var _refresh_ifram = tab.find('iframe')[0];  
				     _refresh_ifram.contentWindow.location.href=_url;  
			    } 
			}else{		
				var _content ="<iframe scrolling='auto' frameborder='0' src='"+_url+"' style='width:100%; height:100%'></iframe>";
				$(boxId).tabs('add',{
					    title:_title,
					    content:_content,
					    closable:true});
				
			}
		}


Win.product.dialog( {
	buttons : [ {
		text : '保存',
		handler : saveCatalogItem
	}, {
		text : '关闭',
		handler : Events.closeProduct
	} ]
});

//该方法用于初始化页面,需要加载的内容
$(function() {
	//加载产品树
	ProductTree.tree( {
		url : 'queryProdTree.do',
		lines : true,
		onContextMenu : function(e, node) {
			console.log("true on");
			e.preventDefault();
			$(this).tree('select', node.target);
			rightMenu.menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		},
		onDblClick:function(node){
			console.log(node);
			if($(this).tree("isLeaf",node.target)){
				addTab("产品服务","../prodService/main.do?productId="+node.id);
			}else{
				alert("no");
			}
//			if (node.id==="800000002") {
//				addTab("产品服务","../prodService/main.do");
//			}else{
//				console.log("isLeaf false");
//			}
		}

	});
	//所属产品加载下拉框
	subProductTree.combotree({
		url : 'queryProdTree.do',
		panelWidth:200,
		panelHeight:300,
		lines : true
	});
	//服务提供商
 	ProdProvComGrid.combogrid({
 		title:'查询条件',
 		panelWidth:550,
 		panelHeight:350,
		idField : 'productProviderId',
		textField:'productProviderName',
		pagination : true, //分页控件
		singleSelect : true, //是否单选 
		url : 'getProductProviderDataList.do',
		toolbar : "#tab_search",
		columns : [ [ {
			field : 'productProviderName',
			title : '产品供应商名称',
			align : 'left',
			width : 100
		}, {
			field : 'productProviderDesc',
			title : '产品供应商描述',
			align : 'left',
			width : 120
		}, {
			field : 'statusCd',
			title : '状态',
			align : 'left',
			width : 60
		}] ]
	});
});

/**
 * 统一初始化,新增产品对话框中,所有下拉框属性
 * 下拉框放在打开窗口前加载,就可以显示默认值
 */
function initProductCombobox() {
	$('#manageGrade').combobox( { //管理级别
				url : 'getComboboxValue.do?comBoxType=manageGrade',
				valueField : 'id',
				textField : 'text'
			});
	$('#objectType').combobox( { //产品对象类型
				url : 'getComboboxValue.do?comBoxType=objectType',
				valueField : 'attrValue',
				textField : 'attrValueName',
				onLoadSuccess : function() {
					var data = $(this).combobox("getData");
					if (data) {//设置默认值展示与上面方法却别
					$(this).combobox("setValue", data[1].attrValue);
				}
			}
			});
	$('#productType').combobox( { //产品类型
				url : 'getComboboxValue.do?comBoxType=productType',
				valueField : 'attrValue',
				textField : 'attrValueName',
				onLoadSuccess : function() {
					var data = $(this).combobox("getData");
					if (data) {//设置默认值展示与上面方法却别
					$(this).combobox("setValue", data[0].attrValue);
				}
			}
			});
	$('#statusCd').combobox( { //产品状态
				url : 'getComboboxValue.do?comBoxType=statusCd',
				valueField : 'attrValue',
				textField : 'attrValueName',
				onLoadSuccess : function() {
					var data = $(this).combobox("getData");
					if (data) {//设置默认值展示与上面方法却别
					$(this).combobox("setValue", data[0].attrValue);
				}
			}
			});
	$('#prodFuncType').combobox( { //产品功能分类
				url : 'getComboboxValue.do?comBoxType=prodFuncType',
				valueField : 'attrValue',
				textField : 'attrValueName',
				onLoadSuccess : function() {
					var data = $(this).combobox("getData");
					if (data) {//设置默认值展示与上面方法却别
					$(this).combobox("setValue", data[0].attrValue);
				}
			}
			});
	$('#intfType').combobox( { //产品接口分类
				url : 'getComboboxValue.do?comBoxType=intfType',
				valueField : 'attrValue',
				textField : 'attrValueName'
			});
	$('#ibsProdType').combobox( { //计费产品类型
				url : 'getComboboxValue.do?comBoxType=ibsProdType',
				valueField : 'attrValue',
				textField : 'attrValueName'
			});
};
/**
 * 判断是否产品节点
 * @param {Object} data
 */
function judgeProdNode(dataNode, callback) {
	var url = 'judgeProdNode.do';
	var data = {};
	data['id'] = dataNode.id;
	//判断是否非目录节点,如果是可以添加,非不能添加
	YiYa.getById(url, data, function(result) {
		if (result.success) {
			//回调函数
			if (jQuery.isFunction(callback)) {
				callback(result);
			}
		}
	});
}
function appendProduct() {
	var node = ProductTree.tree('getSelected');
	if (!node){
		YiYa.alert('提示', '请选择一个目录节点,新增产品', 'error')
	}
	YiYa.confirm('产品配置', '确定新增产品?', function(r) {
		if (r) {
			judgeProdNode(node, function(data) {
				var dataii = data;
				if (dataii.msg == 'success') {
					YiYa.alert('操作提示', '该节点为产品节点，不能新增产品', 'error');
				} else {
					//打开对话框
					initProductCombobox();
					Win.product.dialog('open');
					Form.productEdit.resetForm();
				}
			});
		};

	});
};

function appendCatalogue() {
	var node = ProductTree.tree('getSelected');
	YiYa.confirm('产品配置', '确定新增目录节点?', function(r) {
		if (r) {
			judgeProdNode(node, function(data) {
				var dataii = data;
				if (dataii.msg == 'success') {
					YiYa.alert('操作提示', '该节点为产品节点，不能新增目录', 'error');
				} else {
					//打开对话框
					Win.catalog.dialog('open');
					Form.catalogEdit.resetForm();
				}
			});
		};
	});
};

function removeCatalogue() {
	var isProdNode = {};
	var node = ProductTree.tree('getSelected');
	var parentNode = ProductTree.tree("getParent", node.target);
	var chlidNode = ProductTree.tree("getChildren", node.target);
	var url = 'removeCatalog.do';
	var dataNode = {};
	dataNode['id'] = node.id;
	
	YiYa.confirm('产品配置', '确定删除该节点?', function(r) {
		if (r) {
			judgeProdNode(node, function(data) {
				this.isProdNode = data;
			});
			if (chlidNode.length !=0 || this.isProdNode.msg == 'success') {
				YiYa.alert('操作提示', '非空节点或产品节点,不能删除', 'info');
			} else {
				YiYa.progress();
				YiYa.ajaxJson(url, dataNode, function(data) {
					YiYa.closeProgress();
					if (data.success) {
						YiYa.alert('操作提示', '删除成功', 'info');
						ProductTree.tree('reload', parentNode.target);
						if (callback) {
							callback(data);
						}
					} else {
						YiYa.alert('提示', data.msg, 'error');
					}
				});
			}
		};
	});

};

function saveCatalogItem() {
	var node = ProductTree.tree('getSelected');
	var formMap = Form.catalogEdit;
	var upid = node.id
	$('#upCatalogItemId').val(upid);
	if (Form.catalogEdit.form('validate')) {
		Form.catalogEdit.attr('action', 'saveCatalog.do');
		YiYa.saveForm(Form.catalogEdit, function(data) {
			Win.catalog.dialog('close');
			Form.catalogEdit.resetForm();
			YiYa.alert('操作提示', '保存成功', 'info');
			Events.refreshTree();
			//回调函数
				if (jQuery.isFunction(callback)) {
					callback(data);
				}
			});
	}

};

function doSearchProdProvider() {
	var div_search = $("#productProviderName");
	var record = div_search[0].value;
	var idKey = 'productProviderName';
	var param = {};
	param[idKey] = (record);
	var g = ProdProvComGrid.combogrid('grid');
	g.datagrid('reload', param);
};
