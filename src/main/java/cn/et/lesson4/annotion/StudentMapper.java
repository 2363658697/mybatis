package cn.et.lesson4.annotion;

import java.util.List;
import java.util.Map;

import oracle.net.aso.l;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import cn.et.lesson4.Student;



public interface StudentMapper {
	
	static class StudentProvier{
		/**
		 * ${}在注解情况 失效
		 * 对象的属性 取值 别名.属性名
		 * 提供sql方法的参数 是 Map 键值对
		 * 提供sql语句方法
		 * @param map
		 * @return
		 */
		public String queryStudentSql(Map map){
			Student student=(Student)map.get("stu");
			String sql=" select * from student where 1=1 ";
			if(student.getSname()!=null &&!"".equals(student.getSname())){
				student.setSname("%"+student.getSname()+"%");
				sql+=" and sname like #{stu.sname}";
			}
			if(student.getGid()!=null &&!"".equals(student.getGid())){
				
				sql+="  and gid = #{stu.gid} ";
			}
			return sql;
		}
		
		
		public String queryStudentSql1(Map map){
			Student student=(Student)map.get("stu");
			SQL sql=new SQL();
			sql.SELECT("*").FROM("student");
			if(student.getSname()!=null &&!"".equals(student.getSname())){
				student.setSname("%"+student.getSname()+"%");
				sql.WHERE(" sname like #{stu.sname}");
			}
			if(student.getGid()!=null &&!"".equals(student.getGid())){
				sql.AND();
				sql.WHERE(" gid = #{stu.gid}");
			}
			return sql.toString();
		}
		
		public String updateStudentSql(Map map){
			Student student=(Student)map.get("stu");
			SQL sql=new SQL();
			sql.UPDATE("student");
			if(student.getSname()!=null &&!"".equals(student.getSname())){
				sql.SET("sname=#{stu.sname}");
			}
			if(student.getGid()!=null &&!"".equals(student.getGid())){
				sql.SET("gid=#{stu.gid}");
			}
			if(student.getSex()!=null &&!"".equals(student.getSex())){
				sql.SET("sex=#{stu.sex}");
			}
			sql.WHERE("sid =#{stu.sid}");
			return sql.toString();
		}
		
		public String queryStudentbyAnyGradeSql(Map map){
			List<Integer> list=(List<Integer>) map.get("gradeList");
			
			
			String sql="select * from student where gid in(";

				
			StringBuilder builder = new StringBuilder();
			for (Integer in : list) {
				builder.append(in + ",");
			}
			
			String s = builder.substring(0, builder.length()-1);
			
			s=s+")";
			
			return sql+s;
		}
	}
	
	
	@SelectProvider(type=StudentProvier.class,method="queryStudentSql1")
	public List<Student> queryStudent(@Param("stu") Student student);

	
	
	//这种方式不支持使用
	@Select("<script>select * from student where 1=1 " +
			"<choose>"+
      	"<when test=\"sex!=null\">"+
      	 "   and sex=#{sex}"+
      	"</when>"+
      	" <otherwise>"+
      	"   and sex=1 "+
      	"</otherwise>"+
      "</choose></script>")
	public List<Student> queryBySex(@Param("sex")Integer sex);
	/**
	 * 更新学生信息
	 * @param sid
	 * @return
	 */
	@UpdateProvider(type=StudentProvier.class,method="updateStudentSql")
	public void updateStudent(@Param("stu") Student student);
	/**
	 * 通过传入的班级查询所有学生
	 * 0,1,2
	 * 1,2
	 * 2,3,4
	 * 
	 * @param gradeList
	 */
	@SelectProvider(type=StudentProvier.class,method="queryStudentbyAnyGradeSql")
	public List<Student> queryStudentbyAnyGrade(@Param("gradeList")List<Integer> gradeList);
}
