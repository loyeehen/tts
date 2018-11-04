package project.dao;

import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.ProductType;
import common.dao.TestBaseDao;

public class TestProductTypeDao extends TestBaseDao{
	@Test
	public void testInsertObject(){
		ProductTypeDao Dao
			=(ProductTypeDao)act.getBean("productTypeDao");
		ProductType t1=new ProductType();
		t1.setName("快乐旅游1");
		t1.setId(51);
		t1.setSort(2);
//		t1.setParentId(54);
		t1.setNote("快乐旅游1");
		t1.setCreatedUser("ad");
		t1.setModifiedUser("ad");	
		int rows=Dao.insertObject(t1);	
		Assert.assertEquals(1, rows);
	}
	
	@Test
	public void testFindObject(){
		ProductTypeDao Dao
			=(ProductTypeDao)act.getBean("productTypeDao");
		List<Map<String,Object>> map=Dao.findObjects();
		System.out.println("map"+map);
		Assert.assertNotEquals(null, map);
	}
	
	@Test
	public void testFindTreeNodes(){
		ProductTypeDao dao
			=(ProductTypeDao)act.getBean("productTypeDao");
		List<Map<String,Object>> map=dao.findTreeNodes();
		System.out.println("map"+map);
		Assert.assertNotEquals(null, map);
	}
	
	@Test
	public void testUpdateObject(){
		ProductTypeDao dao
			=(ProductTypeDao)act.getBean("productTypeDao");
		Map<String,Object> map
			=dao.findObjectById(253);//-->json对象
		System.out.println("map+"+map);
//		ProductType type=new ProductType();
//		//不需要修改，则去map中取值
////		type.setId((Integer)map.get("id"));
//		type.setModifiedUser("lbw");
//		type.setName("l12");
//		type.setSort((Integer)map.get("sort"));
//		type.setParentId(235);
////		System.out.println("type+"+type);
//		int row=dao.updateObject(type);
//		Assert.assertNotEquals(null, row);
	}
	@Test
	public void testdeleteObject(){
		ProductTypeDao dao
			=(ProductTypeDao)act.getBean("productTypeDao");
		int rows=dao.deleteObjectById(11126);
		Assert.assertEquals(1, rows);
	}
	
	@Test
	public void testHasChilds(){
		ProductTypeDao dao
		=(ProductTypeDao)act.getBean("productTypeDao");
		int rows=dao.hasChilds(111);
		Assert.assertEquals(2, rows);
	}

}
