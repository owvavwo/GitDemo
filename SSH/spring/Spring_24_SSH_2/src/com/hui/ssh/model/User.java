package com.hui.ssh.model;

import com.hui.ssh.service.UserManager;

//ƶѪģ�ͣ���Ѫģ��
public class User {
	private String username;
	private int id;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	/*public boolean exists() throws Exception {   //��Ѫģ��
		return new UserManager().exists(this);
	}*/
}
