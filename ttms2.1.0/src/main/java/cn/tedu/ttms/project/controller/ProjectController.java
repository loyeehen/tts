package cn.tedu.ttms.project.controller;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.entity.Project;
import cn.tedu.ttms.project.service.ProjectService;
/**
 * 产品项目管理控制器对象
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
	@Resource
	private ProjectService projectService;
	/**
	 * 此方法用于返回项目管理的列表页面
	 * http://localhost:8080/ttms2.0/project/listUI.do
	 * @return
	 */
	@RequestMapping("/listUI")
	public String listUI(){
		//查看spring-mvc.xml (查看视图解析器)
		return "project/project_list";
	}//? return 语句返回的字符串对应一个jsp文件(	//在哪,名字是什么)			
	//[{"id":1,"code":"TT-20170711-CHN-BJ-001","name":"环球游"},....]
	
	@RequestMapping("/editUI")
	public String editUI(){
		//查看spring-mvc.xml (查看视图解析器)
		return "project/project_edit";
	}
		
	/*
	@RequestMapping("/projectList")
	@ResponseBody
	public List<Project> projectList(){
		return projectService.findObjects();
	}
	 */
	
	@RequestMapping("/findPageObjects")
	@ResponseBody
	public JsonResult finPageObject(Project project,PageObject pageObject){
		/**
		 * 1，
		 * project_list.js中var params={"pageCourrent":currentPage};
		 * 传过来的"pageCourrent"，调用PageObject对象的pageCourrent的setPageCourrent
		 * 方法，注入到到其中
		 * 2，
		 * 可以
		 * public Map<String,Object> finPageObject(String pageCourrent){
		 * 传过来的值"pageCourrent"则注入到pageCourrent中
		 * 3，
		 * 可以
		 * public Map<String,Object> finPageObject(HttpServletRequest req){
		 * String pageCourrent=req.getInitParameter("pageCourrent");
		 * 
		 */
		/**name,valid,pageCurrent*/
		 Map<String,Object> map=projectService.findPageObjects(project,pageObject);
		//用于封装页面上传递的数据
		return new JsonResult(map);//state=1,message=ok,data=map
		/**
		 * {"list":[{},{},{}],"pageObject":{"pageSize":2,}}
		 */
		/**
		 * getJSON(url,function(result){
		 * var list=result.list;
		 * var pageObject=result.pageObject;
		 * ..
		 * for(var i in list){
		 * list[i].name;
		 * 
		 * request.setAttribute("",);
		 * ${list.name} getName()
		 */
	}	
	
	@RequestMapping("/doValidById")
	@ResponseBody
	public JsonResult doValidById(String checkedIds,Integer valid){
		System.out.println("checkedIds="+checkedIds);
		System.out.println("valid="+valid);
		projectService.validById(checkedIds,valid);
		return new JsonResult();	
	}
	
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveProject(Project project){
		projectService.saveObject(project);
		System.out.println(new JsonResult());
		return new JsonResult();
	}
	
	@RequestMapping("/doFindById")
	@ResponseBody
	public JsonResult doFindById(Integer id){
		Project pro=projectService.findObjectById(id);	
		return new JsonResult(pro);	
	}
	
	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Project project){		
		System.out.println("upObject-id"+project.getId());
		projectService.updateObject(project);
		return new JsonResult();//state=1,message="ok"		
	}
}




