package project.dao;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

import cn.tedu.ttms.attachement.dao.AttachementDao;
import cn.tedu.ttms.attachement.entity.Attachement;
import common.dao.TestBaseDao;


public class TestAttachements extends TestBaseDao{
	@Test
	public void testInsertObject(){
		AttachementDao dao=(AttachementDao)act.getBean("attachementDao");
		Attachement att=new Attachement();
		att.setTitle("tv-c");
		att.setFileName("2.png");
		att.setFilePath("/update/2017/3/21/hjhj.png");
		att.setContentType("images/png");
		String fileContent="hello world";//文件内容
		//加密
		String digest=DigestUtils.md5Hex(fileContent.getBytes());
		//写到数据库
		att.setFileDisgest(digest);//加密信息
		att.setCreatedUser("demo");
		att.setModifiedUser("demo");
		int rows=dao.insertObject(att);
		Assert.assertEquals(1,rows);
	}
	@Test
	public void testFindObjects(){
		AttachementDao dao=(AttachementDao)act.getBean("attachementDao");
		List<Attachement> list=dao.findObjects();
		System.out.println(list);
	}

}
