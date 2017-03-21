package com.test.Dao;

import java.util.List;

import com.test.Model.Student;

public interface IStudentDao {

	public List<Student> findAllList();
	public Student findById(int id);
	public int studAdd(Student stud);
	public int studUpdate(Student stud);
	public int studDelete(int id);
}
