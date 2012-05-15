package com.zola.recipe.service.facade;

import java.util.List;

import com.zola.recipe.domain.Ingredient;
import com.zola.recipe.domain.Recipe;
import com.zola.recipe.domain.Step;
import com.zola.recipe.service.exception.RecipeServiceException;

public interface RecipeService {

	//Recipes
	Integer addOrUpdateRecipe( Recipe recipe, String username);
	List<Recipe> getRecipes( String username) throws RecipeServiceException;
	void deleteRecipe( Integer recipeId , String username);
	
	
	//steps
	Integer addStep(Integer recipeId, Step step) throws RecipeServiceException;
	Integer updateStep(Integer recipeId, Step step); 
	void removeStep( Integer recipeId , Step step);
	
	//ingredients
	void addIngredientToRecipe( Integer recipeid, Ingredient ingredient) throws RecipeServiceException;
	void addIngredientToStep(Integer recipeId,  Integer stepId, Ingredient ingredient );
	
	
}
