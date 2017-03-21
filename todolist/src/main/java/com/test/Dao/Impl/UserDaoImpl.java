package com.test.Dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.test.Dao.IUserDao;
import com.test.Model.Student;
import com.test.Model.User;
@Repository("userDaoImpl")
public class UserDaoImpl implements IUserDao{
	
	private JdbcTemplate jdbcTemplate;
	@Resource
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<User> getAllList() {
		String sql = "SELECT * FROM user where 1=1";
		List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u = new User();
				u.setId(Integer.parseInt( rs.getString("id")));
				u.setName(rs.getString("name"));
				u.setUserCode(rs.getString("usercode"));
				u.setPassWord(rs.getString("password"));
				return u;
			}
		});
		return users;
		
	}

	@Override
	public User getById(int id) {
		
		return null;
	}

	@Override
	public int addUser(User user) {
		String sql = "INSERT INTO user(name,usercode,password) VALUES (?,?,?);";
		return jdbcTemplate.update(sql,user.getName(),user.getUserCode(),user.getPassWord());
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delUser(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public User Checklogin(String userCode, String passWord) {
		String sql="select * from user where 1=1 AND usercode=?";
		System.out.println(userCode+"....."+passWord);
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class); 
		User us= jdbcTemplate.queryForObject(sql, rowMapper, userCode);
		System.out.println(us.getUserCode()+"-----"+us.getPassWord());
		
		if(us!=null && us.getPassWord().equals(passWord)){
			System.out.println(us.getUserCode()+",,,,,,,"+us.getPassWord());
			System.out.println("登錄成功！");
			return us;
		}
		System.out.println("账号或密码错误，登录失败！");
		System.out.println(us);
		return null;
	}

	@Override
	public User Register(String userCode, String passWord) {
		String sql="select * from user where 1=1 AND usercode=?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		
			try {
				User us= (User) jdbcTemplate.queryForList(sql, rowMapper, userCode);
				
				if(us!=null && us.getUserCode().equals(userCode)){
						System.out.println("用户名已存在");	
						return us;
				}
			} catch (DataAccessException e) {
				e.printStackTrace();
			} 
		System.out.println("成功");
		return null;
		
	}

	
}
