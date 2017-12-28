package cn.et.lesson3.sql;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface FoodInter {
	
	
	public abstract List selectFoodByFoodName(@Param("a") String foodName);
	

}
