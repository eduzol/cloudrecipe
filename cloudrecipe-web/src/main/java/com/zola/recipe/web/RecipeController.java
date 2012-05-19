package com.zola.recipe.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.zola.recipe.domain.Recipe;
import com.zola.recipe.domain.Step;
import com.zola.recipe.service.facade.RecipeService;
import com.zola.web.representation.RecipeView;
import com.zola.web.representation.StepView;

@Controller
@RequestMapping("/user/{userId}/recipes")
public class RecipeController {

	
	
	@Autowired
	RecipeService recipeService;
	
	@RequestMapping( method=RequestMethod.GET , headers ={"Accept=application/json"} )	
	public @ResponseBody List<RecipeView> getRecipes(@PathVariable("userId") String userId){
		
		try{
			System.out.println("getRecipes UserId " + userId  );
			List<Recipe> recipes = recipeService.getRecipes(userId); 
			List<RecipeView> results = new ArrayList<RecipeView>();
			for( Recipe recipe : recipes){
				
				results.add(new RecipeView(recipe));
			}
			
			return results;
			
			
		}catch(Exception ex){
			System.out.println("Exception " + ex.getMessage() );
			ex.printStackTrace();
			//http status code
			return null;
		}
		

	}
	
	
	
	@RequestMapping( method=RequestMethod.POST , headers ={"Accept=application/json"})
	public @ResponseBody RecipeView postRecipes(@PathVariable("userId") String userId  , WebRequest recipeData){
		
		try{
			
			System.out.println("postRecipes UserId " + userId );
			System.out.println("name " + recipeData.getParameter("name"));
			System.out.println("description " + recipeData.getParameter("description"));
					
			Recipe recipe = new Recipe();
			recipe.setName(recipeData.getParameter("name"));
			recipe.setDescription( recipeData.getParameter("description"));
			recipe.setDateCreated(new Date() );
			
			
			recipeService.addOrUpdateRecipe(recipe, userId);
			
			System.out.println("CREATED "+recipe) ;
			RecipeView view = new RecipeView(recipe);
			return view;
		
		}catch(Exception ex ){
			
			System.out.println("Exception ex " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		
	} 
	
	
	@RequestMapping(value="/{recipeId}"  , method=RequestMethod.GET , headers ={"Accept=application/json"})
	public @ResponseBody RecipeView getRecipe(@PathVariable("userId") String userId , @PathVariable("recipeId") Integer recipeId){
		
		System.out.println("userId >>> " + userId + "recipeId >>> " + recipeId);
		
		try{
					
			List<Recipe> recipes = recipeService.getRecipes(userId);
		
			Recipe recipe = null;
			for( Recipe r: recipes){
				
				if ( r.getRecipeId() == recipeId){
					
					recipe = r;
					break;
				}
			}
			
			if ( recipe == null){
				
				System.out.println("not found!!! ");
				return null;
			}
			System.out.println(recipe);
			//lets look at the steps
			/*
			for( Step s : recipe.getSteps()){
				System.out.println(s);
			}
			*/
			RecipeView view = new RecipeView(recipe);
			return view;
			
		
		}catch(Exception ex ){
			
			
			System.out.println("Exception ex " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		
	}

	
	@RequestMapping( value="/{recipeId}" , method=RequestMethod.PUT , headers ={"Accept=application/json"})
	public @ResponseBody RecipeView putRecipe(@PathVariable("userId") String userId,@PathVariable("recipeId")Integer recipeId , WebRequest recipeData )
	{
		
		System.out.println("putRecipe userId "+ userId + "  recipeId " + recipeId);
		System.out.println("name " + recipeData.getParameter("name"));
		System.out.println("description " + recipeData.getParameter("description"));
		try{
			
			Recipe recipe = new Recipe();
			
			if (recipeData.getParameter("name") != null )
				recipe.setName(recipeData.getParameter("name"));
			
			if(recipeData.getParameter("description")!= null )
				recipe.setDescription( recipeData.getParameter("description"));
			
		
			recipe.setRecipeId(recipeId);
			
			//create Update  method 
			recipeService.updateRecipe(recipe);
			List<Recipe> results = recipeService.getRecipes(userId);
			for( Recipe r: results){
				
				if( r.getRecipeId() == recipeId){
					
					recipe = r;
					break;
				}
			}
			
			return new RecipeView(recipe);
		
		}catch(Exception ex ){
	
			System.out.println("Exception ex " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping( value="/{recipeId}/steps" , method = RequestMethod.GET ,headers ={"Accept=application/json"})	
	public @ResponseBody List<StepView> getSteps( @PathVariable("userId") String userId, @PathVariable("recipeId") Integer recipeId)
	{
		
		System.out.println("getSteps  userId "  + userId + "  recipeId " + recipeId );
		try{
			
			List<Recipe> recipes =recipeService.getRecipes(userId);
			Recipe recipe = null;
			for( Recipe r : recipes){
				if ( r.getRecipeId() == recipeId ){
					recipe = r;
					break;
				}
				
			}
			
			if (recipe == null){
				
				System.out.println("not found!!");
				return null;
			}
			
			List<StepView> steps = new ArrayList<StepView>();
			for( Step step : recipe.getSteps()){
				System.out.println(step);
				steps.add(new StepView(step));
		
			}
			
			return steps;
			
		}catch(Exception ex){

			System.out.println("Exception ex " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	
		
	}
	
	
	@RequestMapping( value="/{recipeId}/steps" , method = RequestMethod.POST ,headers ={"Accept=application/json"})	
	public @ResponseBody StepView postSteps( @PathVariable("userId") String userId,  @PathVariable("recipeId")Integer recipeId, WebRequest recipeStepData ){
		
		System.out.println("postSteps  userId "  + userId + "  recipeId " + recipeId );
		
		try{
			String stepName = recipeStepData.getParameter("name");
			String stepNumber = recipeStepData.getParameter("number");
			String description = recipeStepData.getParameter("description");
			
			
			Step s = new Step() ;
			s.setDescription(description);
			s.setName(stepName);
			s.setStepNumber(Integer.parseInt(stepNumber));
			
			recipeService.addStep(recipeId, s);
			
			
			StepView view = new StepView(s);
			return view;
			
		}catch(Exception ex){
			System.out.println("Exception ex " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping(value="/{recipeId}/steps/{stepNumber}", method=RequestMethod.GET ,headers ={"Accept=application/json"} )
	public @ResponseBody StepView getStep( @PathVariable("userId")String userId, @PathVariable("recipeId")Integer recipeId,@PathVariable("stepNumber") Integer stepNumber)	{
		
		
		System.out.println("getstep  " );

		try{
			
			List<Recipe> recipes =recipeService.getRecipes(userId);
			Recipe recipe = null;
			for( Recipe r : recipes){
				if ( r.getRecipeId() == recipeId ){
					recipe = r;
					break;
				}
				
			}
			
			if (recipe == null){
				
				System.out.println("not found!!");
				return null;
			}
			
			Step step = null;
			for( Step s : recipe.getSteps()){
				
				if( s.getStepNumber() == stepNumber){
					
					step  = s;
				}

			}
			if( step == null){
				System.out.println("step not found! ");
				return null;
				
			}
			return new StepView(step);
		}catch(Exception ex){
			System.out.println("Exception ex " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		
		
	}
	
	//TODO UPDATE STEP IS BROKEN 
	@RequestMapping(value="/{recipeId}/steps/{stepNumber}", method=RequestMethod.PUT ,headers ={"Accept=application/json"} )
	public @ResponseBody StepView putStep( @PathVariable("userId")String userId, @PathVariable("recipeId")Integer recipeId,@PathVariable("stepNumber") Integer stepNumber , WebRequest recipeStepData ){
		
		System.out.println("putstep ");
		try{
			
			List<Recipe> recipes =recipeService.getRecipes(userId);
			Recipe recipe = null;
			for( Recipe r : recipes){
				if ( r.getRecipeId() == recipeId ){
					recipe = r;
					break;
				}
				
			}
			
			if (recipe == null){
				
				System.out.println("not found!!");
				return null;
			}
			
			Step step = null;
			
			for( Step s : recipe.getSteps()){
				
				if( s.getStepNumber() == stepNumber){
					
					step  = s;
				}

			}
			
			if( step == null){
				System.out.println("step not found! ");
				return null;
				
			}
			
			recipeService.updateStep(recipeId, step);
			return new StepView(step);
			
			
			
		}catch(Exception ex){
			
			System.out.println("Exception ex " + ex.getMessage());
			ex.printStackTrace();
			return null;
			
		}	
	}
		
}
