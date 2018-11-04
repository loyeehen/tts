package common.dao;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBaseDao {
	public ClassPathXmlApplicationContext act;
	@Before
	public void init(){
		act=new ClassPathXmlApplicationContext(
		"spring-mvc.xml",
		"spring-pool.xml",
		"spring-mybatis.xml");
	}
	//销毁context(也可以先不写)
	@After
	public void destory(){
		act.close();
	}
}


