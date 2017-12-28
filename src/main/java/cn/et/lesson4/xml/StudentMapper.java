package cn.et.lesson4.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.et.lesson4.Student;



public interface StudentMapper {

	/**
	 * 通过编号查询学生
	 * @param sid
	 * @return
	 */
	public List<Student> queryStudent(Student student);
	/**
	 * 根据性别来查所有学生
	 * 参数中 传入sex 就根据条件查  没有传值  查所有女生
	 * @param sex
	 * @return
	 */
	public List<Student> queryBySex(@Param("sex")Integer sex);
	/**
	 * 更新学生信息
	 * @param sid
	 * @return
	 */
	public void updateStudent(Student student);
	/**
	 * 通过传入的班级查询所有学生
	 * 0,1,2
	 * 1,2
	 * 2,3,4
	 * 
	 * @param gradeList
	 */
	public List<Student> queryStudentbyAnyGrade(@Param("gradeList")List<Integer> gradeList);
}
