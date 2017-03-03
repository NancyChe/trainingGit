package com.pro.dao;

import java.util.List;

import com.pro.pojo.Student;

public interface StudentMapper {

	List<Student> getAllList();
	Student findOne(Student stud);
	int insertStud(Student stud);
	int delStud(Student stud);
	int updateStud(Student stud);
}
