package com.zola.recipe.service.impl;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zola.recipe.domain.Recipe;
import com.zola.recipe.domain.Step;
import com.zola.recipe.service.exception.RecipeServiceException;
import com.zola.recipe.service.facade.RecipeService;

public class RecipeserviceTest {

	private ApplicationContext context;
	
	@Before
	public void setUp() throws Exception {
		
		context = new ClassPathXmlApplicationContext("context-service.xml");
	}

	@Test
	public void testCreateRecipe() {
		
		System.out.println(">>>>>>>>>>>>>");
		RecipeService service = (RecipeService)context.getBean("recipeService");
		
		
		Recipe recipe =  new Recipe();
		recipe.setDateCreated(new Date());
		recipe.setDescription("Test.Recipe");
		recipe.setName("Dummy1");
		
		Step step1 = new Step();
		step1.setName("step1.dummy");
		step1.setDescription("step1.description.dummy");
		step1.setStepNumber(1);
		
		
		Step step2 = new Step();
		step2.setName("step2.dummy");
		step2.setDescription("step2.description.dummy");
		step2.setStepNumber(2);
		
		Step step3 = new Step();
		step3.setName("step3.dummy");
		step3.setDescription("step3.description.dummy");
		step3.setStepNumber(3);//should be step 4
		
		Step step2a = new Step();
		step2a.setName("step2a.xxx");
		step2a.setDescription("step2a.xxx.dummy");
		step2a.setStepNumber(2);//should be styep 3...
		
		Integer recipeid = service.addOrUpdateRecipe(recipe, "eduzol");
		System.out.println("Recipe added with ID " + recipeid);
	
		
		try {
		
			int step1id = service.addStep(recipeid, step1);
			int step2id = service.addStep(recipeid, step2);
			int step3id = service.addStep(recipeid, step3);
			int step2aid = service.addStep(recipeid, step2a);
			System.out.println("steps added " + step1id + " , " + step2id  + " , " + step3id + " , " + step2aid)  ;

			
		} catch (RecipeServiceException e) {

			System.out.println( e.getMessage() );
			Assert.fail();
		}


		//update step1
		
		step1.setName("updated1");
		int stepid = service.updateStep(recipeid, step1);
		System.out.println("stepid " + stepid);
		
		
		
		//update Recipe
		Recipe recipenew = new Recipe();
		recipenew.setRecipeId(recipeid);
		recipenew.setDescription("Shakiras");
		recipenew.setName("SSS");
		
		
		Integer recipeid2 = service.addOrUpdateRecipe(recipenew,"eduzol" );
		System.out.println("Recipe updated with id " + recipeid2);
		Assert.assertEquals(recipeid, recipeid2);
		
		
		//delete step
		service.removeStep(recipeid, step1);

	}

}
