package cn.et.lesson3.resultMap.xml;

import java.util.List;


import cn.et.lesson3.resultMap.Grade;


public interface GradeMapper {
	
	public abstract List queryAllGrade();
	
	public abstract Grade queryGrade(int gid);
}
