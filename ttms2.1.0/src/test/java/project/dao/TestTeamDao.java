package project.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.team.dao.TeamDao;
import cn.tedu.ttms.team.entity.Teams;
import common.dao.TestBaseDao;



public class TestTeamDao extends TestBaseDao{
	@Test
	public void testFindPageObject(){
		TeamDao dao=(TeamDao)act.getBean("teamDao");
		String ProjectName="kunming";
		Integer valid=1;
		int startIndex=1;
		int pageSize=1;
		List<Map<String,Object>> list=dao.findPageObjects(ProjectName, valid, startIndex, pageSize);
		System.out.println("list:"+list);
		Assert.assertNotEquals(null, list);
		System.out.println("list.size"+list.size());
		
//		//计算总页数
		int rowCount=dao.getRowCount(ProjectName, valid);
		PageObject pageObject=new PageObject();
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		Assert.assertEquals(1, pageObject.getPageCount());
		System.out.println("pageCount:"+pageObject.getPageCount());		
	}
		

	@Test
	public void testInsertObject(){
		TeamDao dao=(TeamDao)act.getBean("teamDao");
		Teams t1=new Teams();		
		t1.setName("one day");
		t1.setProjectId(13);
		t1.setValid(1);
		t1.setNote("aa");		
		t1.setCreatedUser("bb");
		t1.setModifiedUser("mm");
		int rows=dao.insertObject(t1);
		Assert.assertEquals(1, rows);
	}
	
	
	

}
