package cn.tedu.ttms.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.entity.Project;

/**
 * 此接口对象为一个数据访问对象（关联一个
 * @author Student
 *
 */
public interface ProjectDAO extends BaseDao<Project>{
	/**
	 * @param entity 代表是一个project对象
	 * @return 表示insert记录的行数
	 */
//	public int insertObject(Project entity);
	/**
	 * 查询项目信息
	 */
	public List<Project> findObjects();
	/**
	 * 分页查询项目信息
	 * @param page
	 * @return
	 */
	public List<Project> findPageObjects(
			@Param("project")Project project,
			@Param("pageObject")PageObject pageObject);
	/**
	 * 获得表中的记录数
	 * @return
	 */
	public int getRowCount(Project project);
	/**禁用启用记录数
	 * @return 表示更新的行数,假如返回值为-1表示更新失败
	 * */
	public int validById(
			@Param("ids")String[] ids,
			@Param("valid")Integer valid);
	
	/**根据id修改project对象*/
//	public int updateObject(Project project);
	/**根据id查找project对象*/
	public Project findObjectById(Integer id);
	
	/**查询项目的id和名字*/
	public List<Map<String,Object>> findIdAndName();
	
}
