package com.hui.ssh.service.impl;


import com.hui.ssh.dao.UserDao;
import com.hui.ssh.dao.impl.UserDaoImpl;
import com.hui.ssh.model.User;
import com.hui.ssh.service.UserManager;

public class UserManagerImpl implements UserManager {
	private UserDao userDao= new UserDaoImpl();
	 	
	 
	@Override
	public boolean exists(User u ) throws Exception {	 
		 return userDao.checkUserExistsWithName(u.getUsername());
	}
	 
	@Override
	public void add(User u ) throws Exception {
		userDao.save(u);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
