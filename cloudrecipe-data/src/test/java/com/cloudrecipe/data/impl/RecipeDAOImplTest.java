package com.cloudrecipe.data.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloudrecipe.data.dao.UserDAO;
import com.zola.recipe.domain.User;

public class RecipeDAOImplTest {

	private ApplicationContext context;
	

	
	@Before
	public void setUp() throws Exception {
		context =new ClassPathXmlApplicationContext("context-data.xml");
	}
	
	@Test
	public void testUserDAO() {
		
		
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		
		User user = new User();
		user.setEmail("eduzol@gmail.com");
		user.setName("Eduardo Zola");
		user.setPassword("xanadu");
		user.setUsername("dummytest");
		
		//add user
		userDAO.addUser(user);
		
		//retrieve user
		
		User user2 =  userDAO.getUser("dummytest");
		System.out.println(user2);
		
		Assert.assertEquals(user.getUsername(), user2.getUsername());
		
		//finally delete user
		userDAO.deleteUser("dummytest");
		
		
	}

}
