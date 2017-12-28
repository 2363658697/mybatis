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
		// mybatis���������ļ�·��
		String resource = "cn/et/lesson5/xml/mybatis.xml";

		InputStream inputStream = Resources.getResourceAsStream(resource);
		// ������
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		// session��������ָ��sql����һ��Ψһ��ʶ��
		SqlSession openSession = sqlSessionFactory.openSession();
		return openSession;
	}

	private SqlSessionFactory getSessionFactory() throws IOException {
		// mybatis���������ļ�·��
		String resource = "cn/et/lesson5/xml/mybatis.xml";

		InputStream inputStream = Resources.getResourceAsStream(resource);
		// ������
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		return sqlSessionFactory;
	}

	/*
	 * һ�����棺ͬһ��session�������ͬһ�����ݵĲ�ѯ �����Ļ��� ��һ�β�ѯʱ�� ��ѯ���ݿ� ��ȡ���ݺ�ͨ��session���õ�һ��������
	 * �ڶ��β�ѯʱ ͨ��sessionһ�������ж��Ƿ������ͬ����������ֵ ������� ֱ�ӷ������� �����ѯ���ݿ�
	 */
	@Test
	public void selectStudent() throws IOException {

		SqlSession openSession = getSession();

		StudentMapper mapper = openSession.getMapper(StudentMapper.class);

		List<Student> stu = mapper.queryStudentBySid(1);
		List<Student> stu1 = mapper.queryStudentBySid(2);

		System.out.println(stu == stu1);
	}

	// �������棺ͬһ��sessionFactory�²����Ĳ�ͬsession���Թ�������
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
