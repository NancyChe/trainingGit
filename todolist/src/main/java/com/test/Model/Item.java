package com.test.Model;

//����ʵ����
public class Item {
	
	public Item() {
	}

	public Item(int id, int done, String title) {
		this.id = id;
		this.done = done;
		this.title = title;
	}

	private int id;
	
	//0:todo,1:done
	private int done;
	private String title;
	private String uip;
	
	public String getUip() {
		return uip;
	}

	public void setUip(String uip) {
		this.uip = uip;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getDone() {
		return done;
	}


	public void setDone(int done) {
		this.done = done;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", done=" + done + ", title=" + title
				+ ", uip=" + uip + "]";
	}


	
	

}
