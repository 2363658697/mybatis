package cn.et.lesson3.resultMap.annotion;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.et.lesson3.resultMap.Student;



public interface StudentMapper {
	
	//”≥…‰∂‡∂‘“ª
	@Results({
		@Result(property="grade",column="gid",one=@One(select="cn.et.lesson3.resultMap.annotion.GradeMapper.queryGradeByGid"))	
	})
	@Select("select * from student where sid=#{0}")
	public Student queryStudent(int sid);
	
	
	@Select("select * from student where gid=#{0}")
	public List<Student> queryStudentByGid(int sid);
	
}
