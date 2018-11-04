$(document).ready(function(){
	$("#modal-dialog").on("click",".ok",doSaveOrUpdate);
	doInitProjectIdAndNames();
	
});
//初始化项目id和名字列表
function doInitProjectIdAndNames(){
	var url="team/doFindProjectIdAndName.do";
	$.getJSON(url,function(result){
		if(result.state==1){
			setProjectSelectOptions(result.data);
		}else{
			alert(result.message);
		}
	});
}
function setProjectSelectOptions(list){
	var selectObj=$("#selectId");
	var optionObj="<option value=[id]>[name]</option>";
	for(var i in list){//List<Map<String,Object>>
		selectObj.append(optionObj
				.replace("[id]",list[i].id)
				.replace("[name]",list[i].name));		
	}
}
function doSaveOrUpdate(){
	var url="team/doSaveObject.do";
	var params=getEditFormData();
	$.post(url,params,function(result){
		if(result.state==1){
			alert("");
			$("#modal-dialog").modal("hide");
			doGetObjects();
		}else{
			alert(result.message);
		}
		});
	}
function getEditFormData(){
	var params={
		"name":$("#nameId").val(),
		"projectName":$("#selectId").val(),
		"valid":$('input[name="valid":checked]').val(),
		"note":$("#noteId").val()
	}
	console.log(JSON.stringify(params));
	return params;
}

