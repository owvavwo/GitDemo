package com.bjsxt.dao.impl;
 
import com.bjsxt.model.User;

//�̳�
public class UserDAOImpl3 extends UserDAOImpl {
    
	@Override
	public void save(User user) {
		System.out.println("save user...");
		super.save(user);
		 
	}

}
