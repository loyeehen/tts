package cn.tedu.ttms.project.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.entity.Project;

public interface ProjectService {
	public List<Project> findObjects();
	/**
	 * 返回分页记录、
	 * 记录信息，分页信息
	 * */
	public Map<String,Object> findPageObjects(Project project,PageObject pageObject);
	
	public void validById(String checkedIds,Integer valid);
	
	public void saveObject(Project project);
	
	public Project findObjectById(Integer id);
	
	public void updateObject(Project project);

}
