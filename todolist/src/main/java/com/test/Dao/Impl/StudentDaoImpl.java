package com.test.Dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.test.Dao.IStudentDao;
import com.test.Model.Student;

@Repository("studentDaoImpl")
public class StudentDaoImpl implements IStudentDao {

	
	private JdbcTemplate jdbcTemplate;

	@Resource
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Student> findAllList() {
		String sql = "SELECT * FROM stud where 1=1";
		List<Student> studs = jdbcTemplate.query(sql, new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student s = new Student();
				s.setId(Integer.parseInt(rs.getString("st_id")));
				s.setStName(rs.getString("st_name"));
				s.setStNo(rs.getString("st_no"));
				s.setStSex(rs.getString("st_sex"));
				s.setStMajor(rs.getString("st_major"));
				return s;
			}
		});
		return studs;
	}

	public Student findById(int id){
		String sql = "SELECT * FROM stud where st_id=?";
		RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class); 
		Student stu = jdbcTemplate.queryForObject(sql, rowMapper,id);
		System.out.println("----"+stu+"----");
		return stu;
    } 
      

	
     

	public int studAdd(Student stud) {
		String sql = "INSERT INTO stud(st_no,st_name,st_sex,st_major)VALUES(?,?,?,?)";
		return jdbcTemplate.update(sql, stud.getStNo(), stud.getStName(),
				stud.getStSex(), stud.getStMajor());
	}

	public int studUpdate(Student stud) {
		String sql = "UPDATE stud SET st_no=?,st_name=?,st_sex=?,st_major=? WHERE st_id=?";
		return jdbcTemplate.update(sql, stud.getStNo(), stud.getStName(),
				stud.getStSex(), stud.getStMajor(), stud.getId());
	}

	public int studDelete(int id) {
		String sql = "DELETE FROM stud WHERE st_id = ?";
		return jdbcTemplate.update(sql,id);
	}

}
