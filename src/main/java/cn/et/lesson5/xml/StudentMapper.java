package cn.et.lesson5.xml;

import java.util.List;


import cn.et.lesson4.Student;



public interface StudentMapper {


	public List<Student> queryStudentBySid(Integer sid);

}
