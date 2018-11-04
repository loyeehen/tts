package cn.tedu.ttms.team.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.project.entity.Project;
import cn.tedu.ttms.team.entity.Teams;
public interface TeamDao extends BaseDao<Teams>{
	
	//继承自BaseDao，BaseDao中已经有int  insertObject(T t);
	//不用再写，以后调用TeamDao时直接调用public int insertObject(Teams entity)
	//实现在mapper中
	//int rows=teamDao.insertObject(Teams team);
	//teamDao->BaseDao->mapper
//	public int insertObject(Teams entity);
	//一条记录对应一个Map(key为列的名字，value为列的值
	//多条记录对应一个集合
	public List<Map<String,Object>> findPageObjects(		
			@Param("projectName")String projectName,
			@Param("valid")Integer valid,
			@Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize
			
			);
	public int getRowCount(
			@Param("projectName")String projectName,
			@Param("valid")Integer valid
			);
	
	public List<Map<String,Object>> findIdAndName();
	

}
