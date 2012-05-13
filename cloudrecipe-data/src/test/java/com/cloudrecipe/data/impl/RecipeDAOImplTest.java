package com.cloudrecipe.data.impl;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloudrecipe.data.dao.RecipeDAO;
import com.cloudrecipe.data.dao.UserDAO;
import com.zola.recipe.domain.Ingredient;
import com.zola.recipe.domain.Recipe;
import com.zola.recipe.domain.Step;
import com.zola.recipe.domain.User;

public class RecipeDAOImplTest {

	private ApplicationContext context;
	

	
	@Before
	public void setUp() throws Exception {
		context =new ClassPathXmlApplicationContext("context-data.xml");
	}
	
	@Ignore
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
	
	@Ignore
	@Test
	public void testRecipeDAO(){
		
		RecipeDAO recipeDAO = (RecipeDAO)context.getBean("recipeDAO");
		
		Recipe recipe = new Recipe();
		recipe.setName("hamburguer");
		recipe.setDescription("Delicious!");
		recipe.setDateCreated(new Date());
			
		
		//create Recipe 
		recipeDAO.addRecipe(recipe, "eduzol");
		
		System.out.println(recipe);
		
		
		//add step to recipe
		Step step = new Step();
		step.setStepNumber(1);
		step.setName("fry meat");
		step.setDescription("fry the meat until is ready to eat");
		
		
		recipeDAO.addStep(recipe, step);
		
		System.out.println(step);
		
	}
	
	
	@Test
	public void testRecipeDAOIngredient(){
		
		RecipeDAO recipeDAO = (RecipeDAO)context.getBean("recipeDAO");
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("meat");
		ingredient.setDescription("beef meat");
		
	
		
		Recipe recipe = new Recipe();
		recipe.setName("lasagna");
		recipe.setDescription("italian delicatessen");
		recipe.setDateCreated(new Date());
		recipe.addIngredient(ingredient);
		
		
		
		
		recipeDAO.addRecipe(recipe, "eduzol");
		//recipeDAO.addIngredient(ingredient);
		
		Step step = new Step();
		step.setName("Fry meat");
		step.setDescription("Fry meat until its ready");
		step.addIngredient(ingredient);
		
		recipeDAO.addStep(recipe, step);
		
		
		System.out.println(recipe);
		System.out.println(step);
		System.out.println(ingredient);
		

		//delete them!
		
		
	}

}
