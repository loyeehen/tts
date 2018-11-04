$(document).ready(function(){	
    $("#queryFormId").on('click','.btn-search',doGetObjects);
	$("#queryFormId").on('click','.btn-invalid,.btn-valid',dovalidById);
//	$("#queryFormId").on('click','.btn-add,btn-update',showEditDialog); 报错，btn-update前少了.号
	$("#queryFormId").on('click','.btn-add,.btn-update',showEditDialog); 
	//页面加载完成执行此方法
	 //1.发起ajax请求(findObjects.do)
	 //2.将返回的结果填充到content位置	
	doGetObjects();
});

//显示编辑模态框
function showEditDialog(){
	var url="project/editUI.do";
	var title;
	if($(this).hasClass("btn-add")){
		title="添加项目"
	}
	if($(this).hasClass("btn-update")){
		title="修改项目"
		console.log("title-up"+title);
		//将要修改的记录的id值绑定到模态框上
		//目的时通过一个模块实现添加或更新操作
		$("#modal-dialog").data("id",$(this).parent().parent().data("id"));
				
	}
	//在模态框的.moday-body位置异步加载url
	$("#modal-dialog .modal-body").load(url,function(){//加载完成执行此

		 $("#modal-title").html(title);
		 $("#modal-dialog").modal("show");
	});
//	var url="project/editUI.do";	
//	$("#modal-dialog .modal-body").load(url,function(){					
//		console.log("dialog.url+"+url);
//		$("#modal-title").html("添加项目");
//		$("#modal-dialog").modal("show");
//	})	
}

//获取项目信息
function doGetObjects(){	
	var uri="project/findPageObjects.do";	
	//key值，"pageCourrent"必须定是这个
//	var params={};分页
	var params=getQueryFormatData();//查询
	var pageCurrent=$("#pageId").data("pageCurrent");	
	if(pageCurrent){
		params.pageCurrent=pageCurrent
//		this.pageCurrent=pageCurrent
		};
	console.log("name"+params.name+"/valid"+params.valid+"/pageCurrent"+pageCurrent);
	$.post(uri,params,function(result){	
		console.log("function(result)");		
		//map中list(根据key取值list)
		/*var list=result.list;*/
		 //设置表格tabody中的内容
		if(result.state==1){
		console.log("result.state");
		setTableRows(result.data.list);
		//设置分页		
		setPagination(result.data.pageObject);	
		// console.log("pageObject"+pageObject);
		}else{
			alert(result.message);
		}
	});
}

//获得查询表单中的数据
function getQueryFormatData(){	
	var params={
	   "name":$("#searchNameId").val(),
	   "valid":$("#searchValidId").val()	   
	}
	return params;
}

function setTableRows(list){
	console.log("setTableRows(list)");
	//获得tbody对应dom节点对象
	var tBody=$("#tbodyId");
	//清空假数据
	tBody.empty();
	
	//将从服务端获得的列表数据填充的表格中
	var temp='<td><input type="checkbox" name="checkedItem" value="[id]"></td>'						
		+'<td>[code]</td>'
		+'<td>[name]</td>'
		+'<td>[beginDate]</td>'
		+'<td>[endDate]</td>'
		+'<td>[valid]</td>'		              
		+'<td><input type="button" class="btn btn-success btn-update" value="修改"></td>';
	//追加数据
	for(var i in list){//循环一次取一行数据(对应一对tr对象)		
		var tr=$('<tr></tr>');//创建一堆tr对象	
//		tr.append("id",list[1].id);
		tr.data("id",list[i].id);//绑定数据,便于后续获得此数据进行修改等操作
		tr.append(temp					
				.replace('[id]',list[i].id)
				.replace('[code]',list[i].code)
				.replace('[name]',list[i].name)
				.replace('[beginDate]',new Date(list[i].beginDate).toLocaleDateString())
				.replace('[endDate]',new Date(list[i].endDate).toLocaleDateString())
				.replace('[valid]',list[i].valid?'启用':'禁用')
				);
		//将tr添加到tBody中
		tBody.append(tr);	 
	}				
}

function dovalidById(){	
	
	//1.判定触发的是启用还是禁用按钮(根据类选择器)
	var state;//定义一个状态值,表示状态
	if($(this).hasClass('btn-valid')){		
		//启用(将选中的记录的valid修改为1)
		state=1;
	}else{
		state=0;
	}
	 //2.获得选中的checkbox对应的id值(id是记录的唯一标识)
	 var checkedIds=getCheckedIds();
	 if(checkedIds==' '){
		 alert("至少选择一个");
		 return;
	  }
	 console.log("checkedIds="+checkedIds);
	 var params={"checkedIds":checkedIds,
			 	 "valid":state};
	//3.获得的数据通过ajax发送异步请求到服务器然后
	//执行更新操作
//	var url="dovalidById.do";错
	 var url="project/doValidById.do";
	$.post(url,params,function(result){
		if(result.state==1){//表示更新成功
			doGetObjects();//重新查询
		}else{
			alert(result.message);//显示错误信息
		}
		
	})
}
function getCheckedIds(){	
	debugger
	var checkedIds='';
	//获得tbody对象中名为checkedItem的input对象
	$('tbody input[name="checkedItem"]').each(function(){//each:迭代input对象
		//判定这个input对象是否是选中的input
		
		if($(this).is(":checked")){//$(this).prop("checked")
			//将选中的checkbox的值拼接为字符串
			if(checkedIds==' '){
//				if(checkectIds==''){ 错误
				checkedIds+=$(this).val();	
				console.log("$(this).val"+$(this).val);
			}else{
				console.log("checkedIds"+checkedIds);
				checkedIds+=","+$(this).val();
			}//"1,2,3,4"		
		};
	})
	return checkedIds;
}