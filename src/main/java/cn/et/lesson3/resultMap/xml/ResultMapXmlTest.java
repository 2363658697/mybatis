package cn.et.lesson3.resultMap.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;



import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.et.lesson3.resultMap.Grade;
import cn.et.lesson3.resultMap.Student;




public class ResultMapXmlTest {
	private  SqlSession getSession() throws IOException {
		// mybatis核心配置文件路径
		String resource = "cn/et/lesson3/resultMap/xml/mybatis.xml";

		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 工厂类
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		// session操作的是指向sql语句的一个唯一标识符
		SqlSession openSession = sqlSessionFactory.openSession();
		return openSession;
	}

	@Test
	public  void selectFood() throws IOException {
		
		SqlSession openSession = getSession();
		
		GradeMapper mapper = openSession.getMapper(GradeMapper.class);
		
		List selectAllGrade = mapper.queryAllGrade();
		System.out.println(selectAllGrade.size());
		
	}

	@Test
	public  void testManytoOne() throws IOException {
		
		SqlSession openSession = getSession();
		
		 StudentMapper mapper = openSession.getMapper(StudentMapper.class);
		
		 Student queryStudent = mapper.queryStudent(1);
		 
		// System.out.println(queryStudent.getSname()+"----"+queryStudent.getGrade().getGid());
		 
	}
	@Test
	public  void testOnetoMany() throws IOException {
		
		SqlSession openSession = getSession();
		
		 GradeMapper mapper = openSession.getMapper(GradeMapper.class);
		
		 Grade queryGrade = mapper.queryGrade(2);
		 
		 System.out.println(queryGrade.getGid()+"---"+queryGrade.getGnamed());
		 
		 for(Student tmp: queryGrade.getList()){
			 System.out.println(tmp.getSname());
		 }		 
	}

}
