$(document).ready(function(){
	$("#uploadFormId").
	on("click",".btn-upload",doUpload).
	on("click",".btn-down",dodown);
	dogetObjects();
});
function dodown(){
	var id=$(this).parent().parent().data("id");
	console.log("id:"+id);
	var url="att/dodownObject.do?id="+id;
//	var url="att/dodownObject.do";
//	var params={"id":id};
//	$.post(url,params,function(result){
//		if(result.state==1){
//			alert("ok!");
//		}else{
//			alert(result.message);
//		}
//	});

	document.location.href=url;
}
//上传文件
function doUpload(){
	/**$.post();,$.getJSON();,$.ajax();*/
	//之前拿到数据直接getxxx，postxxx
	//异步提交表单(先确保jquery.form.js已经引入了),局部刷新页面
	var url="att/doSaveObject.do";
	$("#uploadFormId").ajaxSubmit({
		type:'post',
		url:url,
		data:{"athType":1,"belongId":1},
		dateType:'json',
		success:function(result){
			if(result.state==1){
				alert("ok!");
				dogetObjects();
			}else{
				alert(result.message);
			}
			
		}
	});	
}
function dogetObjects(){
	var url="att/dofindObjects.do";
	$.post(url,function(result){
		if(result.state==1){
			dosetCows(result.data);
		}else{
			alert(result.message);
		}
	});
}
function dosetCows(list){
	var tbody=$("#tbodyId");
	tbody.empty();
	//'<td type="checkbox" name="selectItem" value="[id]"></td>'
	var tds='<td><input type="checkbox" name="selectItem" value="[id]"></td>'+
			'<td>[title]</td>'+
			'<td>[fileName]</td>'+		
			'<td>[contentType]</td>'+
			'<td>[filePath]</td>'+
			'<td><input type="button" class="btn btn-default btn-down" value="下载"/></td>';
	for(var i in list){
		var tr=$("<tr></tr>");
		tr.data("id",list[i].id);
		tr.append(
				tds.replace("id",list[i].id).
				replace("title",list[i].title).
				replace("fileName",list[i].fileName).	
				replace("contentType",list[i].contentType).
				replace("filePath",list[i].filePath));
		tbody.append(tr);
	}
	
}