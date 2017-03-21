package com.test.Dao;

import java.util.List;

import com.test.Model.Item;

public interface IitemDao {
	public void add(Item i);
	public List<Item> listAll(String uip);
	public void update(Item i);
	public void delete(int i,String uip);
}
