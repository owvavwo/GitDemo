package com.hui.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hui.dao.UserDAO;
import com.hui.model.User;



public class UserService {
	private UserDAO userDAO;  
	
	 
	public void add(User user) { 
		userDAO.save(user);
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}
	@Autowired                      //�Զ�װ�䣬Ĭ����byType,����ж��bean�����ʹ��@Qualifierָ��
	public void setUserDAO(@Qualifier("u")UserDAO userDAO) {   
		this.userDAO = userDAO;
	}
	 
}
