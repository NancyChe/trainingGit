package com.pro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pro.dao.StudentMapper;
import com.pro.pojo.Student;
import com.pro.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentMapper studentMapper;
	
	public List<Student> getAllList() {
		return studentMapper.getAllList();
	}

	public Student findOne(Student stud) {
		return studentMapper.findOne(stud);
	}

	public int insertStud(Student stud) {
		return studentMapper.insertStud(stud);
	}

	public int delStud(Student stud) {
		return studentMapper.delStud(stud);
	}

	public int updateStud(Student stud) {
		return studentMapper.updateStud(stud);
	}

}

