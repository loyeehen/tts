var zTree;
//定义树的基本配置
var setting = {
		data : {
			    simpleData : {
				enable : true,
				idKey : "id",  //节点数据中保存唯一标识的属性名称
				pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
				rootPId : null  //根节点id
			}
		}
	}

$(document).ready(function(){
	$("#editTypeForm").on("click",".load-product-type",loadTypeTree);
	$("#typeLayer").on("click",".btn-cancle",hideTypeTree);
	$("#typeLayer").on("click",".btn-confirm",setSelectedTypeNode);//设置选中的节点
	/*$("#editTypeForm").on("click",".btn-save",doSaveOrUpdate);*/
	$("#btn-save").click(doSaveOrUpdate);
	//首先判定当前页面中有没有id值，
	//假如有则根据id查询产品分类
	//并将产品分类的信息填充到表单中
	var typeId=$("#container").data("typeId");//$("#container").data("typeId",id);
	//$("#container").data("typeId",id);
	console.log("edit.type="+typeId);
//	if(typeId)doGetObjectById(); 报错
	if(typeId)doGetObjectById(typeId);
});

//根据id去查找ProductType对象
function doGetObjectById(typeId){
	var url="productType/doFindObjectById.do";
	var params={"id":typeId};
	console.log("params="+JSON.stringify(params));
	$.post(url,params,function(result){
		console.log("result="+JSON.stringify(result.data));
		if(result.state==1){
		   setEditFormData(result.data);
		}else{
		   alert(result.message);
		}
	});
}

//填充表单数据
function setEditFormData(obj){
	//这个id需要写到数据库，需要绑定
	//$("#editTypeForm").data("parentId",obj.parentId);
	//写到页面
//	$("#typeNameId").val(obj.name);
//	$("#parentNameId").val(obj.parentName);//显示
//	$("#typeSortId").val(obj.sort);
////	$("#typeNoteId").data(obj.note);建议写成html
//	$("#typeNoteId").html(obj.note);
	
	//绑定到整个form上的parentid,当执行修改后，form上的id要被移除，解绑
	$("#editTypeForm").data("parentId",obj.parentId);
	
	$("#typeNameId").val(obj.name);
	$("#parentNameId").val(obj.parentName);//显示
	$("#typeSortId").val(obj.sort);
	$("#typeNoteId").html(obj.note);
}

//执行保存或更新的动作
function doSaveOrUpdate(){//需要绑定，需要解绑
	//1，获得表单数据（修改时获得一个id值）
	var params=getEditFormDate();
	//2，提交表单数据
	//2,1检查当前页面是否有绑定的typeId值
	var typeId=$("#container").data("typeId");	
	//如果是修改，还要传值id过去
	//动态的给params传值typeId,即动态的给表单值typeId
	//typeId，有值表示修改，没值表示保存
	//2,2如果是修改，则在表单中添加一个typeId
	if(typeId)params.id=typeId;
	//2,3根据typeId是否有值确定是添加，还是添加操作
	var saveUrl="productType/doSaveObject.do";
	var updateUrl="productType/doUpdateObject.do";
	//id有值则updateUrl，没值，则saveUrl
	var url=typeId?updateUrl:saveUrl;//console.log("params===="+JSON.stringify())
	//2,4提交数据到数据库
	$.post(url,params,function(result){
//		if(result.data==1){因为写成data,所以之后的方法不能调用
		if(result.state==1){
		alert("save ok!")
		//提交成功,获得表单中数据，存入数据库后，
		//需要重新加载页面，在回到页面前，清空表单数据。
		//因为共用一个页面，而且页面上绑定id值，这时表单中会有数据，所以需要清除。
		doClearFormData();
		/*doGetObjects();*/
		//回到列表页面
		$("#container").load("productType/ListUI.do?t="+Math.random(1000));
		}else{
			alert(result.message);
		}
	});	
}

//清空表单数据
function doClearFormData(){
	debugger
	//技巧型应用(给每个表单中需要清空数据的地方添加一个dynamicClear选择)
	$(".dynamicClear").val('');	
	//移除绑定数据
	$("#container").removeData("typeId");	
	$("#editTypeForm").removeData("parentId");		
}

//获得表单数据
function getEditFormDate(){
	//如果是修改，则还有id，则动态的给params值id
	params={		
		"name":$("#typeNameId").val(),
		"parentId":$("#editTypeForm").data("parentId"),
		"sort":$("#typeSortId").val(),
		"note":$("#typeNoteId").val()
	};
	return params;
}

//隐藏typetree
function hideTypeTree(){
	$("#typeLayer").css("display","none");
}
//设置选中的type节点
function setSelectedTypeNode(){
	//获得zTree中选定的节点
	var nodes=zTree.getSelectedNodes();
	//获得选中的第一个节点
	var node=nodes[0];	
	//给parentNameId的赋值
	$("#parentNameId").val(node.name);	//显示的是名字	
	//将id的值绑定到editTypeForm对象上
	$("#editTypeForm").data("parentId",node.id);//保存到数据库的是id的值
	//隐藏zTree树对象
	$("#typeLayer").css("display","none");
}
//加载产品分类信息，然后以zTree形式显示
function loadTypeTree(){	
	//显示xTree树
	$("#typeLayer").css("display","block")//默认none，点击时显示
	//初始化zTree
	var url="productType/doFindTreeNodes.do";
	$.getJSON(url,function(result){
		if(result.state==1){
			//初始化zTree
			zTree=$.fn.zTree.init(
					$("#typeTree"),//显示树的位置
					setting,//树的基本配置
					result.data);//树上要显示的数据
		}else{
			alert(result.mesage);
		}
	});
}