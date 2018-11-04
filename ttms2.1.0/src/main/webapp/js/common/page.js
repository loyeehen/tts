$(document).ready(function(){	
	$("#pageId").on('click','.pre,.next,.f,.l',jumpToPage);	
	//on(),动态绑定
});
//设置分页
function setPagination(pageObject){
	//绑定总页数
	$("#pageId").data("pageCount",pageObject.pageCount);
	//绑定当前页面
	$("#pageId").data("pageCurrent",pageObject.pageCurrent);
}

function jumpToPage(){
	//点击对象上的class属性对应的值
	//this，是点击的对象
	debugger
	var cli=$(this).attr("class");
	console.log("cli:"+cli);
	//获得pageId对象上绑定的pageCurrent对应的值
	var pageCurrent=$("#pageId").data("pageCurrent");
	//获得pageId对象上绑定的pageCount对应的值
	var pageCount=$("#pageId").data("pageCount");
	//判断点击的是否是上一页
	if(cli=='f'){
		console.log("首页");
		pageCurrent=1;
		
	}
	if(cli=='pre' && pageCurrent>1){
		console.log("上一页");
		pageCurrent--;		
	}
	if(cli=='next' && pageCurrent<pageCount){
		console.log("下一页");
		pageCurrent++;
	}
	if(cli=='l'){
		console.log("尾页");
		pageCurrent=pageCount;		
	}
	
	//重写绑定pageCurrent的值
//	$("#pageId").data("pageCount",pageCurrent);
	$("#pageId").data("pageCurrent",pageCurrent);
	//重新执行查询操作
	doGetObjects();		 
}