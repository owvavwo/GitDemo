package com.hui.service; 

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hui.model.User;

public class UserServiceTest {
	
	@Test
	public void testAOP() {
		ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("beans.xml");
		UserService service = (UserService) cp.getBean("UserService");
		System.out.println(service.getClass());
		service.add(new User());
		
	}
}
    
	