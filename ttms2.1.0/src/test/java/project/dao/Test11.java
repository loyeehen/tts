package project.dao;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

import cn.tedu.ttms.attachement.dao.AttachementDao;
import cn.tedu.ttms.attachement.entity.Attachement;
import common.dao.TestBaseDao;



public class Test11 extends TestBaseDao{
	
	@Test
	public void testInsertObject(){
		AttachementDao dao
		=(AttachementDao)act.getBean("attachementDao");
	Attachement aa=new Attachement();
	aa.setTitle("t-cc");
//	aa.setFileName("b4.png");
//	aa.setFilePath("/update/2017/02/11/1234.png");
//	aa.setContentType("images/png");
//	String ac="hello world！";
//	String digest=DigestUtils.md5Hex(ac.getBytes());
//	aa.setFileDisgest(digest);
//	aa.setCreatedUser("hs");
//	aa.setModifiedUser("hp");
	int rows=dao.insertObject(aa);
	Assert.assertEquals(1, rows);
	
	}
	
	

}
