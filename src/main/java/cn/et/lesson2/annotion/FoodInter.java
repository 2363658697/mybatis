package cn.et.lesson2.annotion;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import cn.et.lesson2.Food;

public interface FoodInter {
	
	//指定参数名获取参数：不再通过索引
	@Select("select * from foods where foodname=#{foodName} and price=#{price}")
	public abstract List<Map> selectFood(@Param("foodName") String foodName,@Param("price") String price);
	
	@Select("select * from foods where foodname like '%${foodName}%'")
	public abstract List<Food> selectFoodByFoodName(@Param("foodName") String foodName);
	
	
	@SelectKey(before=true,keyProperty="foodId",statement="select foods_scr.nextval from dual", resultType = int.class)
	@Insert("insert into foods values(#{foodId},#{foodName},#{price})")
	public abstract void saveFood(Food food);
	
}
