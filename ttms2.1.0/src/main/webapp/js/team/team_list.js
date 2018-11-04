$(document).ready(function(){	
	$("#queryFormId").on('click',".btn-search",doGetObjects);
	
	$("#queryFormId").on('click',".btn-add,.btn-update",showEditDialog);
	
	 //页面加载完成执行此方法
	 //1.发起ajax请求(findObjects.do)
	 //2.将返回的结果填充到content位置	
	doGetObjects();
});

function showEditDialog(){
	var tittle;
//	if($(this).hasClass(".btn-add")){报错，多了.点
	if($(this).hasClass("btn-add")){
		title="添加团信息";
	}
	if($(this).hasClass("btn-update")){
		title="修改团信息";
		$("#modal-dialog").data("id",$(this).parent().parent().data("id"));
	}
	var url="team/editUI.do";
//	$.post(url,function(){
//		if(result.state==1){
//			$("#modal-title").html(title);
//			$("#modal-dialog").modal("show");
//			
//		}else{
//			alert(result.message);
//		}
//	});
	$("#modal-dialog .modal-body").load(url,function(){
		$("#modal-title").html(title);
		$("#modal-dialog").modal("show");
	});
	 
}
//获取项目信息
function doGetObjects(){
	debugger
	//1,url	
	var url="team/doFindPageObjects.do";	
	//2,params
	var params=getQueryFormatData();
	
	var pageCurrent=$("#pageId").data("pageCurrent");
	
	if(pageCurrent){
		//params,动态的给para添加参数pageCurrent
		params.pageCurrent=pageCurrent;
	}else{
		params.pageCurrent=1;
	}
	console.log(params.projectName+"/"+params.valid+"/"+params.pageCurrent);
	//console.log(params.name+"/")
	//console.log("123:"+JSON.stringify(params.name=name));
	//3,ajax(post)
	$.post(url,params,function(result){
		
		console.log("123"+JSON.stringify(result));
		//3.1填充表格
		if(result.state==1){
			
			setTableRows(result.data.list);
		//3.2设置分页
			setPagination(result.data.pageObject);
		}else{
			alert(result.message);
		}
	});
}
//查询，启用禁用
function getQueryFormatData(){
	console.log("getQueryFormatData");
	var params={
			//对象中的名字要与Controller中的相同
	   "projectName":$("#searchNameId").val(),
	   "valid":$("#searchValidId").val()	   
	}
	console.log(JSON.stringify(params));
	return params;
}
function setTableRows(list){
	console.log("setTableRows(list)");	
	var tds='<td><input type="checkbox" name="checkedItem" value="[id]"></td>'						
		+'<td>[name]</td>'
		+'<td>[projectName]</td>'
		+'<td>[valid]</td>'		
		+'<td><input type="button" class="btn btn-success btn-update" value="修改"></td>';
	//获得tbody对应dom节点对象
	var tBody=$("#tbodyId");
	//清空假数据
	tBody.empty();
	//追加数据
	for(var i in list){//循环一次获取一行数据		
		var tr=$('<tr></tr>');	
		//迭代list(此集合现在存放多个map
		tr.append(tds					
				.replace('[id]',list[i].id)	
				.replace('[name]',list[i].name)
				.replace('[projectName]',list[i].projectName)
				.replace('[valid]',list[i].valid?'启用':'禁用')
				);
		//将tr添加到tBody中
		tBody.append(tr);	 
	}				
}

function dovalidById(){
	console.log("team/dovalidById()");
	//1.判定触发的是启用还是禁用按钮(根据类选择器)
	var state;//定义一个状态值,表示状态
	if($(this).hasClass('btn-valid')){
		console.log("hasClass");
		//启用(将选中的记录的valid修改为1)
		state=1;
	}else{
		state=0;
	}
	 //2.获得选中的checkbox对应的id值(id是记录的唯一标识)
	 var checkedIds=getCheckedIds();
	 if(checkedIds==''){
		 alert("至少选择一个");
		 return;
	  }
	 console.log("checkedIds="+checkedIds);
	 var params={"checkedIds":checkedIds,"valid":state};
	//3.获得的数据通过ajax发送异步请求到服务器然后
	//执行更新操作
	var url="team/dovalidById.do";
	$.post(url,params,function(result){
		console.log("dovalidById.$.post");
		if(result.state==1){
			doGetObjects();//重新查询
		}else{
			alert(result.message);//显示错误信息
		}
		
	})
}
function getCheckedIds(){
	console.log("getCheckedIds");
	var checkedIds='';
	//获得tbody中名为checkedItem的input对象
	$('tbody input[name="checkedItem"]').	
	each(function(){//迭代input对象
		//判断是否是选中的input
		if($(this).is(":checked")){//$(this).prop("checked")
			//将选中的checkbox的值拼接为字符串
			if(checkectIds==''){
				checkedIds+=$(this).val();				
			}else{
				checkedIds+=","+$(this).val();
			}//"1,2,3,4"		
		};
	})
	return checkedIds;
}