package cn.et.lesson2.preduce;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;



import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;




public class MyBatisTest {
	private  SqlSession getSession() throws IOException {
		// mybatis���������ļ�·��
		String resource = "cn/et/lesson2/preduce/mybatis.xml";

		InputStream inputStream = Resources.getResourceAsStream(resource);
		// ������
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		// session��������ָ��sql����һ��Ψһ��ʶ��
		SqlSession openSession = sqlSessionFactory.openSession();
		return openSession;
	}

	@Test
	public void testProcInterface() throws IOException{
		SqlSession session =getSession();
		Map map=new HashMap();
		map.put("p1", 1111);
		map.put("p2", 1212);
		map.put("result", 0);
		session.selectOne("proc.call_pro_add",map);
		System.out.println(map.get("result"));
	}
	
	
	@Test
	public void testFunInterface() throws IOException{
		SqlSession session =getSession();
		Map map=new HashMap<String, Integer>();
		map.put("p1", 1111);
		map.put("p2", 1212);
		map.put("result", 0);
		session.selectOne("proc.call_fun_add",map);
		System.out.println(map.get("result"));
	}

	
}
