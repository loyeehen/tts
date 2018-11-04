var columns = [
{
field : 'selectItem',
radio : true
},
{
title : '分类id',
field : 'id',
visible : false,
align : 'center',
valign : 'middle',
width : '80px'
},
{
title : '分类名称',
field : 'name',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '上级分类',
field : 'parentName',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '排序号',
field : 'sort',
align : 'center',
valign : 'middle',
sortable : true,
width : '100px'
}];

$(document).ready(function(){
	doGetObjects();
	$("#formHead").on("click",".btn-add,.btn-update",loadEditPage);
	/*.on("click",".load-product-type",loadTypeTree);*/	
	
//	$("formHead").on("click",".btn-delete",doDeleteObject); 查#,报错
	$("#formHead").on("click",".btn-delete",doDeleteObject);
	
});


function doDeleteObject(){
	//获得的id
	var typeId=getSelectedId();
	console.log("deId"+typeId);
	//判断有没有选中删除项
	if(typeId==-1){
		alert("请选中要删除的选项");
		return false;
		}
	var url="productType/doDeleteObjectById.do";
	var params={"id":typeId};
	$.post(url,params,function(result){
		if(result.state==1){
			alert("delete ok!");
			doGetObjects();
		}else{
			alert("result.message");
		}
	});
}

//加载编辑页面
function loadEditPage(){
	var url="productType/editUI.do";
	var title;
	//检测是否是更新操作
	if($(this).hasClass("btn-update")){
		title="更新分类信息";
		//首先获得选中的记录id（在修改页面要根据id查询）
		var id=getSelectedId();//console.log("id11="+id);
		//假若没有选中则提示首先应该选中1个
		if(id==-1){alert("请先选中");return false;}
//		console.log("typeId12="+id);
		//假若id有值，则将id绑定到当前页面的container对象中
		$("#container").data("typeId",id);
		console.log("typeId13="+$("#container").data("typeId"));
	}
	if($(this).hasClass("btn-add")){
		title="添加分类信息";
	}
	//在container位置异步加载url,其中container为index.jsp中div的一个id
//	$("#container").load(url);
	$("#container").load(url,function(){
		$(".panel-heading").html(title);
		//panel-heading,_edit.jsp中
	});
}

//获得选中的id值
function getSelectedId(){
	//获得table中选中的id值
	var selections=$("#typeTable").bootstrapTreeTable("getSelections");
	if(selections.length==0){return -1;}
	return selections[0].id;
	//{"id":123,"name":	 
}

/*function loadTypeTree(){
	var url="";	
}*/
function doGetObjects(){//加载数据（以树形式进行展开）
	var url="productType/doFindObjects.do";
//	$.getJSON(url,function(result){
//		console.log(JSON.stringify(result.data));
//		if(result.state==1){
//			$("#typeTable").html(JSON.stringify(result.data));
//		}else{
//			alert(result.message);
//		}
//	});
	var tableId="typeTable";
//	var tableColumns=columns;//表头
	/*构建treetable对象并进行初始化（tree.table.js）*/
	var table=new TreeTable(tableId,url,columns);
	table.setIdField("id");//设置返回值
	table.setCodeField("id");//设置父子关系
	table.setParentCodeField("parentId");//设置父子关系
	table.setExpandColumn(2);//设置点击第二列展开
	table.setExpandAll(false);//设置初始化时是否全展开
	table.init();
}