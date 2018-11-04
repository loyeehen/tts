package project.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.dao.ProjectDAO;
import cn.tedu.ttms.project.entity.Project;

public class TestProjectDao {
	private ClassPathXmlApplicationContext act;
	@Before
	public void init(){
		 act=new ClassPathXmlApplicationContext("spring-mvc.xml","spring-mybatis.xml","spring-pool.xml");
	}	
	@Test
	public void testinsertObject() throws ParseException{
		//1,获得ApplicationContext对象		
		//2,获得dao
		ProjectDAO projectDao=(ProjectDAO)act.getBean("projectDAO");
		//2,构建entity
		Project entity=new Project();
		entity.setId(18);
		entity.setName("云南春城");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		entity.setBeginDate(sdf.parse("2013-11-12"));
		entity.setCreatedTime(sdf.parse("2016-11-12"));
		entity.setEndDate(sdf.parse("2014-11-12"));
		entity.setModifiedTime(sdf.parse("2016-11-12"));
		entity.setCode("abcd12123");
		entity.setCreatedUser("bb");
		entity.setNote("ttms");
		entity.setValid(5);	
		entity.setModifiedUser("mm");
		//4,将对象写入到数据库
		int n=projectDao.insertObject(entity);	
		Assert.assertEquals(1,n);		
	}
	@Test
	public void testfindObjects(){		
		ProjectDAO projectDAO=(ProjectDAO)act.getBean("projectDAO");
		List<Project> projects=projectDAO.findObjects();
		System.out.println(projects);
		Assert.assertNotEquals(null, projects);
	}
	
	@Test
	public void testfindPageObjects(){		
		ProjectDAO dao=(ProjectDAO)act.getBean("projectDAO");
		PageObject pageObject=new PageObject();
		Project project=new Project();
		//总记录数
		int rowCount=dao.getRowCount(project);
		//获得总页数（根据总记录数，pageSizez计算总页数）
		pageObject.setRowCount(rowCount);
		int pageCount=pageObject.getPageCount();
		Assert.assertEquals(4, pageCount);
		System.out.println("pageCount"+pageCount);
		//获得当前页的记录（为1）
		List<Project> list=dao.findPageObjects(project,pageObject);
		System.out.println(list);
		Assert.assertEquals(2,list.size());
	}
	
	@Test
	public void testValidById(){
		ProjectDAO dao=(ProjectDAO)act.getBean("projectDAO");
		String[] ids={"1","2"};
		int rows=dao.validById(ids,2);
		System.out.println("rows="+rows);
		Assert.assertEquals(0, rows);
	}
	
	@Test
	public void testupdateObject() throws ParseException{
		ProjectDAO dao=(ProjectDAO)act.getBean("projectDAO");
		Project pro=dao.findObjectById(8);
		pro.setCode("abc12123");
		pro.setName("13579");		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		pro.setBeginDate(sdf.parse("2013-11-12"));		
		pro.setEndDate(sdf.parse("2014-11-12"));
		pro.setValid(5);
		pro.setNote("ttsc");
		pro.setModifiedTime(sdf.parse("2016-11-12"));
		pro.setModifiedUser("aa");			
		//将对象内容持久化到数据库
		int rows=dao.updateObject(pro);		
		Assert.assertEquals(1, rows);		
	}
		
	@After
	public void delete(){
		act.close();
	}	
	//DB-->ProjectMapper.xml-->ProjectDao-->ProjectService--->ProjectController-->页面
}
