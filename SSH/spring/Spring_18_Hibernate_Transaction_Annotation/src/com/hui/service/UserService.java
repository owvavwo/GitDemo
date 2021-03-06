package com.hui.service;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hui.dao.LogDAO;
import com.hui.dao.UserDAO;
import com.hui.model.Log;
import com.hui.model.User;

@Component("UserService")
@Service
public class UserService {
	private UserDAO userDAO;
	private LogDAO logDAO;
	
	@Transactional
	public void add(User user) {
		userDAO.save(user);
		Log log = new Log();
		log.setMsg("a user saved!");
		logDAO.save(log);
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}
	@Resource
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public LogDAO getLogDAO() {
		return logDAO;
	}
	@Resource
	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}
}
