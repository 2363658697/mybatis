package cn.et.lesson5.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.et.lesson4.Student;

public class MyBatisTest {
	private SqlSession getSession() throws IOException {
		// mybatis核心配置文件路径
		String resource = "cn/et/lesson5/xml/mybatis.xml";

		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 工厂类
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		// session操作的是指向sql语句的一个唯一标识符
		SqlSession openSession = sqlSessionFactory.openSession();
		return openSession;
	}

	private SqlSessionFactory getSessionFactory() throws IOException {
		// mybatis核心配置文件路径
		String resource = "cn/et/lesson5/xml/mybatis.xml";

		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 工厂类
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		return sqlSessionFactory;
	}

	/*
	 * 一级缓存：同一个session对象针对同一份数据的查询 产生的缓存 第一次查询时， 查询数据库 获取数据后通过session设置到一级缓存中
	 * 第二次查询时 通过session一级缓存判断是否存在相同主键的数据值 如果存在 直接返回引用 否则查询数据库
	 */
	@Test
	public void selectStudent() throws IOException {

		SqlSession openSession = getSession();

		StudentMapper mapper = openSession.getMapper(StudentMapper.class);

		List<Student> stu = mapper.queryStudentBySid(1);
		List<Student> stu1 = mapper.queryStudentBySid(2);

		System.out.println(stu == stu1);
	}

	// 二级缓存：同一个sessionFactory下产生的不同session可以共享数据
	@Test
	public void selectStudent1() throws IOException {

		SqlSessionFactory factory = getSessionFactory();

		SqlSession openSession = factory.openSession();
		SqlSession openSession1 = factory.openSession();

		StudentMapper mapper = openSession.getMapper(StudentMapper.class);
		mapper.queryStudentBySid(1);
		openSession.close();

		StudentMapper mapper1 = openSession1.getMapper(StudentMapper.class);
		mapper1.queryStudentBySid(1);
	}
}
