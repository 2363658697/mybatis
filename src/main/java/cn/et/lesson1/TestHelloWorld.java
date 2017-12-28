package cn.et.lesson1;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

public class TestHelloWorld {

	public static SqlSession getSession() throws IOException{
		String resource = "cn/et/lesson1/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工廠類
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session=sqlSessionFactory.openSession();
		return session;
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		SqlSession session =getSession();
		//session操作的是  指向sql語句的一個唯一標識符
		List selectList = session.selectList("a.selectFoodById");
		//Mapped
		// 鍵 = sql語句
		System.out.println(selectList);
		Map map=(Map)session.selectOne("a.selectFoodById");
		System.out.println(map);
	}
	/*
	 * 自定义log
	 * */
	Logger logger=Logger.getLogger(TestHelloWorld.class);
	@Test
	public void testQueryByParam() throws IOException{
		SqlSession session =getSession();
//		Map map=new HashMap();
//		map.put("myprice", 100);
//		map.put("myfoodname", "青椒肉丝");
		Food food=new Food();
		food.setMyfoodname("青椒肉丝");
		food.setMyprice("100");
		List list=session.selectList("a.selectFoodByParam",food);
		logger.info(list);
	}
	
	@Test
	public void savePrice() throws IOException{
		SqlSession session =getSession();
		Map map=new HashMap();
		map.put("price", 100);
		map.put("foodname", "青椒肉丝1122");
		session.insert("a.saveFood",map);
		session.commit();
	}
	

}
