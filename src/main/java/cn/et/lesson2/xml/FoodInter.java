package cn.et.lesson2.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.et.lesson2.Food;

public interface FoodInter {
	public abstract List selectFood(String foodName,String price);
	
	
	public abstract List selectFoodByFoodName(@Param("a") String foodName);
	
	public abstract void saveFood(Food food);
}
