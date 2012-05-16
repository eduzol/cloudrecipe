package com.cloudrecipe.data.dao;

import java.util.List;

import com.zola.recipe.domain.Ingredient;
import com.zola.recipe.domain.Recipe;
import com.zola.recipe.domain.Step;

public interface RecipeDAO {

	
	int addRecipe( Recipe recipe, String username);
	
	public int updateRecipe(Recipe recipe);
	
	int  addStep ( Recipe recipe, Step step);
	
	List<Step> getSteps( Recipe recipe);
	
	void addIngredient( Step step, Ingredient ingredient);
	
	void addIngredient( Ingredient ingredient);
	
	public List<Recipe> getRecipes( String username );
	
	public Recipe getRecipe( int recipeId);
	
	public void deleteRecipe( int recipeId , String username );
	
	Step getStep( Recipe recipe, int stepNumber);
	
	void deleteStep(Recipe recipe, Step step);

}
