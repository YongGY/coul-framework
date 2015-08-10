$package('Base');

/**
 * 一些基础的工具类类的炒作
 */
var Base={
	/*Json 工具类*/
	isJson:function(str){
		var obj = null; 
		try{
			obj = Base.paserJson(str);
		}catch(e){
			return false;
		}
		var result = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length; 
		return result;
	},
	paserJson:function(str){
		return eval("("+str+")");
	},
	/*弹出框*/
	alert:function(title, msg, icon, callback){
		//$.messager.alert(title,msg,icon,callback);
	},
	/*弹出框*/
	confirm:function(title, msg,callback){
		//$.messager.confirm(title,msg,callback);
	},
	progress:function(title,msg){
//		 var win = $.messager.progress({  
//            title: title ||'Please waiting',  
//            msg: msg ||'Loading data...'  
//         }); 
	},
	closeProgress:function(){
		//$.messager.progress('close');
	},
	
	/**
	 * 表单提交操作～～～
	 * @param form
	 * @param option
	 */
	ajaxSubmit:function(form,option){
		form.ajaxSubmit(option);
	},
	
	/**
	 * 提交数据处理～～
	 * @param url
	 * @param option
	 * @param callback
	 */
	ajaxJson: function(url,option,callback){
		$.ajax(url,{
			type:'post',
			 	dataType:'json',
			 	data:option,
			 	success:function(data){	 		
			 		if($.isFunction(callback)){
			 			callback(data);
			 		}
			 	},
			 	error:function(response, textStatus, errorThrown){
			 		try{
			 			Base.closeProgress();			 			
			 		}catch(e){
			 			alert(e);
			 			Base.alert('提示',"请求出现异常,请联系管理员1",'error');
			 		}
			 	},
			 	complete:function(){
			 	}
		});
	},
	/**
	 * 提交表单数据以MAP的数据格式进行请求操作
	 * @param form
	 * @param callback
	 * @param dataType
	 */
	submitFormMap:function(form , callback , dataType){
		//1、表单数据进行转化
		var  fromData = form.serializeJson();
		var  url = form.attr('action');
		var  requestData = {};
		requestData["requestData"] = fromData;   //构造MAP的数据类型和JSON的数据类型
		Base.ajaxJson(url , requestData);
		
	},
	
	/**
	 * 表单提交数据
	 * @param form
	 * @param callback
	 * @param dataType
	 */
	submitForm:function(form,callback,dataType){
			var option =
			{
			 	type:'post',
			 	dataType: dataType||'json',
			 	success:function(data){
			 		if($.isFunction(callback)){
			 			callback(data);
			 		}
			 	},
			 	error:function(response, textStatus, errorThrown){
			 		try{
			 			Base.closeProgress();
			 			//var data = $.parseJSON(response.responseText);
				 		
			 		}catch(e){
			 			alert(e);
			 			Base.alert('提示',"请求出现异常,请联系管理员1",'error');
			 		}
			 	},
			 	complete:function(){
			 	
			 	}
			 }
			 Base.ajaxSubmit(form,option);
	},
	
	
	saveForm:function(form,callback){
		if(form.form('validate')){
			//Base.progress('请等待','Save ing...');
			//ajax提交form
			Base.submitForm(form,function(data){
				//Base.closeProgress();
			 	if(data.success){
			 		if(callback){
				       	callback(data);
				    }else{
			       		Base.alert('提示','保存成功.','info');
			        } 
		        }else{
		       	   Base.alert('提示',data.msg,'error');  
		        }
			});
		 }
	},
	/**
	 * 
	 * @param {} url
	 * @param {} option {id:''} 
	 */
	getById:function(url,option,callback){
		Base.progress();
		Base.ajaxJson(url,option,function(data){
			Base.closeProgress();
			if(data.success){
				if(callback){
			       	callback(data);
			    }
			}else{
				Base.alert('提示',data.msg,'error');  
			}
		});
	},
	deleteForm:function(url,option,callback){
		Base.progress();
		Base.ajaxJson(url,option,function(data){
				Base.closeProgress();
				if(data.success){
					if(callback){
				       	callback(data);
				    }
				}else{
					Base.alert('提示',result.msg,'error');  
				}
		});
	}
}

/**
 * 部分提交数据转化为MAP的数据格式
 */ 

function  commitDataTurnMap(data){
	//接收是多个或者一个数据处理
	if(data instanceof  Array){
		var  map = new  Map();
		for(var index = 0 ; index <data.length ; index++){
			map.put(data[index] , $("#"+data[index].val()));
		}
	    return map;
	}
}



/*
 设有form中有username,password两个input,看效果
$("form").serializeArray()
[{"name":"username","value":""},{"name":"password","value":""}]
$("form").serializeObject()
{"username":"","password":""}*/
$.fn.serializeMap = function(){
	//var map = new  Map();
	var data = this.serializeArray();
	$.each(data , function(){
		//map.put(this.name+'' , this.value||'');
	});
	//return map;
}

/**
 * 查询数据请求对象
 */
function   RequestData(requestData){
	this.requestData = requestData
	return this;
}

/*转化为JSON的数据*/
$.fn.serializeJson = function(){
	var data = this.serializeArray();
	//构造JSON的字符清单
	var jsonData = "{";
	$.each(data , function(){
		if(this.value != null && this.value != undefined) {
		   jsonData += "\""+ this.name +"\""+ ":" + "\"" + this.value + "\"" +","; 
		}
	});
	//去掉多余的一个“，”
	jsonData = jsonData.substr(0,jsonData.length-1);
	jsonData += "}";
	return jsonData;
	
}


$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}



  

