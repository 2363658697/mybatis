package cn.et.lesson3.resultMap;

import java.util.ArrayList;
import java.util.List;



public class Grade {
	private int gid;
	private String gnamed;
	private List<Student> students=new ArrayList<>();
	
	public int getGid() {
		return gid;
	}
	public List<Student> getList() {
		return students;
	}
	public void setList(List<Student> students) {
		this.students = students;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGnamed() {
		return gnamed;
	}
	public void setGnamed(String gnamed) {
		this.gnamed = gnamed;
	}
	
	
}
