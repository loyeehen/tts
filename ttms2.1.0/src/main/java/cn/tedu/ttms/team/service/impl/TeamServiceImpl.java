package cn.tedu.ttms.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.SaveRuntimeException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.dao.ProjectDAO;
import cn.tedu.ttms.team.dao.TeamDao;
import cn.tedu.ttms.team.entity.Teams;
import cn.tedu.ttms.team.service.TeamService;
@Service
public class TeamServiceImpl implements TeamService{
	@Resource
	private TeamDao teamDao;	
	@Resource
	private ProjectDAO projectDAO;
	
	public Map<String, Object> findPageObjects(//点击页面时发送点击时页面的值，即当前页
			String projectName, Integer valid, Integer pageCurrent) {
		//public Map<Project,Object> findPageObjects(Project project,PageObject)
		//数据多时可以把数据存入对象中		
		//自己new的
		PageObject pageObject=new PageObject();
		//pageObject.setPageSize(3); 不写默认为2，在这里取对象定义的值	
//		pageObject.setStartIndex(pageCurrent);
		pageObject.setPageCurrent(pageCurrent);
		/**PageObject:1、pageCurrent,2、rowCount,3、pageSize,4、pageCount，5、startIndex*/
		/**
		 * 由   pageCurrent,
		 *  public int getStartIndex(){return (pageCurrent-1)*pageSize;} 得到startIndex
		   由
		   public int getPageCount(){		
			int pages=rowCount/pageSize;if(rowCount%pageSize!=0){pages+=1;}return pages;
			}得到pageCount
		*/
		//根据startIndex及参数获得当前页数据
		List<Map<String,Object>> list=
				teamDao.findPageObjects(
						projectName, valid, pageObject.getStartIndex(), pageObject.getPageSize());
		//获得总页数
		int rowCount=teamDao.getRowCount(projectName, valid);
		pageObject.setRowCount(rowCount);
		//得到rowCount,则得到PageObject对象
		/*
		 * 从页面传过来，在页面取到的值只有pageCurrent,不能取到startIndex,pageSize，不能再控制层处理
		 *控制层只是起到数据的流转和交互，不做逻辑处理
		 */		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);	
		return map;
	}
	
	public void saveObject(Teams team){
		if(team==null)
			throw new SaveRuntimeException("信息不能为空!");
		int rows=teamDao.insertObject(team);
		if(rows==-1){
			throw new SaveRuntimeException("保存失败!");
		}		
	}
	
	public List<Map<String, Object>> findProjectIdAndName(){		
		return projectDAO.findIdAndName();
	}

}
