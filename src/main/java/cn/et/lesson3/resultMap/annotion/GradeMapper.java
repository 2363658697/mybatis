package cn.et.lesson3.resultMap.annotion;


import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


import cn.et.lesson3.resultMap.Grade;



public interface GradeMapper {
	//”≥…‰“ª∂‘∂‡
	@Results(
			{
		@Result(id=true,property="gid",column="gid"),
		@Result(property="gnamed",column="gname"),
		@Result(property="students",column="gid",javaType=List.class,many=@Many(select="cn.et.lesson3.resultMap.annotion.StudentMapper.queryStudentByGid"))
		
	})
	@Select("select * from grade where gid=#{0}")
	public abstract Grade queryGrade(String gid);
	
	
	
	@Results({
		@Result(property="gnamed",column="gname")	
		
	})
	@Select("select * from grade where gid=#{0}")
	public abstract Grade queryGradeByGid(int gid);
}
