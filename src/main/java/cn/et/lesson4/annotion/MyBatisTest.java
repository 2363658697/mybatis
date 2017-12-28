package cn.et.lesson4.annotion;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.et.lesson4.Student;


public class MyBatisTest {
	private  SqlSession getSession() throws IOException {
		// mybatis核心配置文件路径
		String resource = "cn/et/lesson4/annotion/mybatis.xml";

		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 工厂类
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		// session操作的是指向sql语句的一个唯一标识符
		SqlSession openSession = sqlSessionFactory.openSession();
		return openSession;
	}

	@Test
	public  void selectStudent() throws IOException {	
		SqlSession openSession = getSession();
		
		StudentMapper mapper = openSession.getMapper(StudentMapper.class);
		Student student=new Student();
		
		student.setGid(2);
		student.setSname("飞");
		List<Student> stList = mapper.queryStudent(student);
		
		for(Student stu:stList){
			System.out.println(stu.getGid()+"---"+stu.getSname());
		}
	}
	@Test
	public  void selectStudentBySex() throws IOException {	
		SqlSession openSession = getSession();
		StudentMapper mapper = openSession.getMapper(StudentMapper.class);
		
		List<Student> stList = mapper.queryBySex(1);
		
		for(Student stu:stList){
			System.out.println(stu.getSname());
		}	
	}
	
	@Test
	public  void updateStudent() throws IOException {	
		SqlSession openSession = getSession();
		
		StudentMapper mapper = openSession.getMapper(StudentMapper.class);
		Student student=new Student();
		
		student.setSname("gou飞");
		student.setGid(1);
		student.setSex(1);
		student.setSid(1);
		mapper.updateStudent(student);
		
		openSession.commit();
	}

	@Test
	public  void queryStudentbyAnyGrade() throws IOException {	
		SqlSession openSession = getSession();
		
		StudentMapper mapper = openSession.getMapper(StudentMapper.class);
		
		List<Integer> list=new ArrayList<>();
		list.add(1);
		list.add(3);
		
		List<Student> sList = mapper.queryStudentbyAnyGrade(list);
		
		for(Student stu:sList){
			System.out.println(stu.getSname());
		}	
	}
}
