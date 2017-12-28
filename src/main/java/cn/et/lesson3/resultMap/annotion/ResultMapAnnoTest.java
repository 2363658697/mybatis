package cn.et.lesson3.resultMap.annotion;

import java.io.IOException;
import java.io.InputStream;



import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.et.lesson3.resultMap.Grade;
import cn.et.lesson3.resultMap.Student;







public class ResultMapAnnoTest {
	private  SqlSession getSession() throws IOException {
		// mybatis���������ļ�·��
		String resource = "cn/et/lesson3/resultMap/annotion/mybatis.xml";

		InputStream inputStream = Resources.getResourceAsStream(resource);
		// ������
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		// session��������ָ��sql����һ��Ψһ��ʶ��
		SqlSession openSession = sqlSessionFactory.openSession();
		return openSession;
	}



	@Test
	public  void testManytoOne() throws IOException {
		
		SqlSession openSession = getSession();
		
		StudentMapper mapper = openSession.getMapper(StudentMapper.class);
			
		 Student queryStudent = mapper.queryStudent(1);
		 
		 System.out.println(queryStudent.getSname()+"----"+queryStudent.getGrade().getGid());
		 
	}
	
	@Test
	public  void testOnetoMany() throws IOException {
		
		SqlSession openSession = getSession();
		
		GradeMapper mapper = openSession.getMapper(GradeMapper.class);
		
		Grade queryGrade = mapper.queryGrade("2");
		 		 
		 System.out.println(queryGrade.getGid()+"---"+queryGrade.getGnamed());
		 
		 for(Student tmp: queryGrade.getList()){
			 System.out.println(tmp.getSname());
		 }
	}
}
