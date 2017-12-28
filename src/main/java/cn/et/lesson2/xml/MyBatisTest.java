package cn.et.lesson2.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;



import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.et.lesson2.Food;


public class MyBatisTest {
	private  SqlSession getSession() throws IOException {
		// mybatis核心配置文件路径
		String resource = "cn/et/lesson2/xml/mybatis.xml";

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
		
		FoodInter mapper = openSession.getMapper(FoodInter.class);
		
		/*List selectFood = mapper.selectFood("dd", "55");
		System.out.println(selectFood);*/
		
		
		List selectFoodByFoodName = mapper.selectFoodByFoodName("c");
		
		System.out.println(selectFoodByFoodName);
	}

	
	@Test
	public  void saveFood() throws IOException {
		
		SqlSession openSession = getSession();
		
		FoodInter mapper = openSession.getMapper(FoodInter.class);
		
		Food food=new Food();
		
		food.setFoodName("kkk");
		food.setPrice("55");
		
		mapper.saveFood(food);
		
		openSession.commit();
		
		System.out.println(food.getFoodId());
	}

}
