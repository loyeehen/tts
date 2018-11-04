package cn.tedu.ttms.team.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.team.entity.Teams;
import cn.tedu.ttms.team.service.TeamService;
@Controller
@RequestMapping("/team")
public class TeamController {
	@Resource
	private TeamService teamService;
	
	@RequestMapping("/listUI")
	/**
	 *此处不能用@ResponseBody，如果用，则@ResponseBody会把"team/team_list"当做资格字符串，而不是地址
	 * */
	public String listUI(){
		//查看spring-mvc.xml (查看视图解析器)
		return "team/team_list";
		//WEN-INF/team/team_list
	}
	
	@RequestMapping("/editUI")
	public String editUI(){
		return "team/team_edit";
	}
	@RequestMapping("/doFindPageObjects")
	@ResponseBody	
	public JsonResult doFindPageObjects(String projectName,
			Integer valid,Integer pageCurrent){
		System.out.println("projectName:"+projectName+"valid:"+valid+"pageCurrent"+pageCurrent);
		Map<String,Object> map
			=teamService.findPageObjects(projectName, valid, pageCurrent);
		return new JsonResult(map);		
	}
	
	//保存团信息
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Teams team){
		teamService.saveObject(team);
		return new JsonResult();
	}
	//查询项目id和名字
	@RequestMapping("/doFindProjectIdAndName")
	@ResponseBody
	public JsonResult doFindProjectIdAndName(){
		List<Map<String,Object>> map
			=teamService.findProjectIdAndName();		
		return new JsonResult(map);							
	};
	
}
	

