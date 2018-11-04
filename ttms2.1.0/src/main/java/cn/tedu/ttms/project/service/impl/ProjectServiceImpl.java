package cn.tedu.ttms.project.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.UpdateRuntimeException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.dao.ProjectDAO;
import cn.tedu.ttms.project.entity.Project;
import cn.tedu.ttms.project.service.ProjectService;
/**
 * 项目管理service对象
 * 项目中所有与业务相关的事情一般都要放在service中,例如
 * 1)判定参数是否符合业务要求
 * 2)判定dao返回的数据是否是我们需要的结果
 * 3)执行一些日志的记录
 * 4)执行一些事务的处理
   5)....
 */
@Service//放在实现类，而不是接口
public class ProjectServiceImpl implements ProjectService{
	@Resource//注入
	private ProjectDAO projectDAO;
			
	public List<Project> findObjects() {
		
		return projectDAO.findObjects();
	}
	/**
	 * @param pageObject 用于接收控制层传递过来的分页信息
	 * 1)此参数中应包含startIndex
	 * 2)此参数中应包含pageSize
	 */
	public Map<String,Object> findPageObjects(Project project,PageObject pageObject){
//		public Map<String, Object> findPageObject(String startIndex,) {
		//1,获得页面表格中要显示的数据
		List<Project> list=projectDAO.findPageObjects(project,pageObject);
		//2,获得表中记录数并计算页数
		int rowCount=projectDAO.getRowCount(project);
		pageObject.setRowCount(rowCount);
		//3,构建map对象封装从dao层获得的数据
		Map<String,Object> map
			=new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject",pageObject);					
		return map;
		/**
		 * {"list":[{},{},{}],"pageObject":{"pageSize":2,}}
		 */	
	}
	
	public void validById(String checkedIds, Integer valid) {
		//解析字符串(1,2,...
		//判定参数checkedIds
		if(checkedIds==null || checkedIds.length()==0){
			throw new NullPointerException("必须有选中的id");
		}
		if(valid!=1 && valid!=0){
			throw new IllegalArgumentException("无效的valid值");	
		}
		//解析字符串(1,2,3,4,5);
		String ids[]=checkedIds.split(",");
		//访问dao层方法执行启用禁用的更新操作
		int rows=projectDAO.validById(ids,valid);
					
		if(rows==-1){
			throw new RuntimeException("更新失败!");
		}	
	}
	
	public void saveObject(Project project) {
		int rows=projectDAO.insertObject(project);
		if(rows==-1){
			throw new RuntimeException("save error!");
		}		
	} 
	
	public Project findObjectById(Integer id){
		if(id==null)
			throw new NullPointerException("id can not find！");
			Project project=projectDAO.findObjectById(id);
		if(project==null)
			throw new RuntimeException("project does not exist");
			return project;					
		}
		
	
	public void updateObject(Project project) {
		int rows=projectDAO.updateObject(project);
		if(rows==-1)//正常情况下应该是1
		//说明在service中写的自定义异常一般都继承RuntimeException
		throw new UpdateRuntimeException("update error");			
	}	
	
}
