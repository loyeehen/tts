$(document).ready(function(){
	$("#queryFormId").on("click",".btn-attachment",loadAttachement)
});
//加载产品附件信息页面
function loadAttachement(){
	//var url="pproduct/ListUI"; 报错
	//四个地方，写错ppproduct,多p,少.do，地址写错
	var url="att/uploadUI.do";
	//归属类型对象(产品附件,分销商附件,...)
	$("#container").data("athTypy",1);
	//假设归属产品1对象(是选择的checkbox的value值)
	$("#container").data("belongId",1);
	//加载附件页面
	$("#container").load(url);
}
