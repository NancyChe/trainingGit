package com.test.Dao.Impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.test.Dao.IitemDao;
import com.test.Model.Item;

@Repository("itemDao")
public class ItemDao implements IitemDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void add(Item i) {
		jdbcTemplate.update("insert into items(done,title,uip) values(?,?,?)",i.getDone(),i.getTitle(),i.getUip());
	}
	
	public List<Item> listAll(String uip){
		List<Item> items = jdbcTemplate.query("select * from items where uip = '"+uip+"'", new RowMapper<Item>(){
			
			public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Item i = new Item();
				i.setId(Integer.parseInt(rs.getString("id")));
				i.setTitle(rs.getString("title"));
				i.setDone(rs.getInt("done"));
				return i;
			}
		});
		return items;
	}
	public void update(Item i) {
		jdbcTemplate.update("update items set done=?,title=? where id=? and uip=?",i.getDone(),i.getTitle(),i.getId(),i.getUip());
		
	}
	public void delete(int i,String uip) {
		// TODO Auto-generated method stub
		String sql = "delete from items where id = "+i+" and uip='"+uip+"'";
		System.out.println(sql);
		jdbcTemplate.update(sql);
	}

}
