package com.zola.recipe.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloudrecipe.data.dao.RecipeDAO;
import com.zola.recipe.domain.Ingredient;
import com.zola.recipe.domain.Recipe;
import com.zola.recipe.domain.Step;
import com.zola.recipe.service.exception.RecipeServiceException;
import com.zola.recipe.service.facade.RecipeService;


@Service("recipeService")
public class RecipeServiceImpl implements RecipeService {

	
	
	public RecipeDAO recipeDAO;
	
	
	@Autowired
	public RecipeServiceImpl(RecipeDAO recipeDAO){
		this.recipeDAO = recipeDAO;
		
		
	}
	
	@Transactional(propagation =Propagation.REQUIRED , readOnly = false ) 
	public Integer addOrUpdateRecipe(Recipe recipe, String username) {
		
		int id  = recipeDAO.addRecipe(recipe, username);
		return id;
		
	}

	@Transactional(propagation =Propagation.REQUIRED , readOnly = true ) 
	public List<Recipe> getRecipes(String username) throws RecipeServiceException {
	
		List<Recipe> results = recipeDAO.getRecipes(username);
		
		return results;
		
	}

	@Transactional(propagation =Propagation.REQUIRED , readOnly = false ) 
	public void deleteRecipe(Integer recipeid , String username) {
		
		recipeDAO.deleteRecipe(recipeid, username);
		
	}

	/**
	 * validate if a step with that number already exists... if so, add Step after it
	 * 
	 */
	@Transactional(propagation =Propagation.REQUIRED , readOnly = false ) 
	public Integer addStep(Integer recipeId, Step step) throws RecipeServiceException {
		
		//fixed
		Recipe recipe = recipeDAO.getRecipe(recipeId);
		
		//check if already exists!
		if( recipe.getSteps().contains(step)){
			System.out.println("alreayd exists!!!");
			throw new RecipeServiceException("step already exists use updateStep");
		}
	
		
		
		
		//----------->
		int stepNumber = step.getStepNumber();
		
		
		//here is getting another instances again!!!
		List<Step> steps = recipeDAO.getSteps(recipe);
		//Set<Step> steps = recipe.getSteps();
		
		System.out.println("steps before adding");
		//DEBUG 
		for( Step s : steps){
			System.out.println(s);
		}
		
		System.out.println("********* step size " +  steps.size() + "  step number " + stepNumber);
		if( steps.size() > stepNumber){
			
			System.out.println(">>>>> INSIDE ALGORITHM");
			
			for( int i = 1 ; i <= steps.size() ; i++){
				System.out.println("i " + i + " stepNumber "  + stepNumber);
				if ( i < stepNumber ){
					
					continue;
					
				}else if( i == stepNumber){
					
					step.setStepNumber(stepNumber+1);
				
				}else if ( i> stepNumber){
					
					Step current =steps.get(i-1);
					current.setStepNumber(current.getStepNumber()+1);
					
				}
				
			}
			
		}
		
		//<----
		
		
		//fixed
		int id =recipeDAO.addStep(recipe, step);
		
		
		//---->***---***
		List<Step> steps1 = recipeDAO.getSteps(recipe);
		//Set<Step> steps = recipe.getSteps();
		
		System.out.println("steps after adding");
		//DEBUG 
		for( Step s : steps1){
			System.out.println(s);
		}
		//fixed
		return id;
		
	}

	public Integer updateStep(Integer recipeId, Step step) {
		
		//fixed
		Recipe recipe = recipeDAO.getRecipe(recipeId);
			
		//fixed
		int id =recipeDAO.addStep(recipe, step);
		
		//fixed
		return id;
		
	}

	
	
	@Transactional(propagation =Propagation.REQUIRED , readOnly = false ) 
	public void addIngredientToRecipe(Integer recipeId, Ingredient ingredient) throws RecipeServiceException{
		
		Recipe recipe = recipeDAO.getRecipe(recipeId);
		if (recipe == null)
				throw new RecipeServiceException("recipe not found");
		
		
		recipe.addIngredient(ingredient);
		recipeDAO.addRecipe(recipe, recipe.getCreator().getUsername());


	}

	@Transactional(propagation =Propagation.REQUIRED , readOnly = false ) 
	public void addIngredientToStep(Integer recipeId ,Integer stepNumber, Ingredient ingredient) {
	
		Recipe recipe = recipeDAO.getRecipe(recipeId);
		Step step =  recipeDAO.getStep(recipe, stepNumber);
		step.addIngredient(ingredient);
		recipeDAO.addStep(recipe, step);

	}

	/**
	 * 
	 * It should update the rest of the step numbers so always the enumeration
	 * is congruent all the time
	 */
	@Transactional(propagation =Propagation.REQUIRED , readOnly = false ) 
	public void removeStep(Integer recipeId, Step step) {
		
		Recipe recipe = recipeDAO.getRecipe(recipeId);
		
		List<Step> steps = recipeDAO.getSteps(recipe);
		
		//DEBUG
		for( Step s : steps){
			System.out.println(s);
		}
		
		int stepNumber = step.getStepNumber();
		Step toRemove = null ;
		for ( int i = 1 ; i <= steps.size() ; i++){
			
			System.out.println("i => " + i );
			System.out.println("stepNumber => " + stepNumber);
			
			
			if ( i < stepNumber){
				
				continue;
		
			}else if( i == stepNumber ){
				
				toRemove = steps.get(i-1);
				System.out.println("step to remove" + toRemove);
				System.out.println("step received in method " + step );
				
			}else if( i > stepNumber){
				
				Step currentstep = steps.get(i-1);
				currentstep.setStepNumber(currentstep.getStepNumber()-1);
				
			}
		}
		//DEBUG
		for( Step s : steps){
			System.out.println(s);
		}
		
		//remove step
		if ( toRemove != null){
			recipeDAO.deleteStep( recipe, toRemove);
		
		}
		
		
	}

	

}
