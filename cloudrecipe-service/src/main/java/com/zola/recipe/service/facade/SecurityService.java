package com.zola.recipe.service.facade;

import java.util.List;

import com.zola.recipe.domain.Ingredient;
import com.zola.recipe.domain.Recipe;
import com.zola.recipe.domain.Step;

public interface SecurityService {

	
	
	//Recipes
	void addOrUpdateRecipe( Recipe recipe, String username);
	List<Recipe> getRecipes( String username, int offset, int total);
	void deleteRecipe( Recipe recipe);
	
	
	
	
	//steps
	void addStep( Recipe recipe, Step step );
	void addIngredientToRecipe( Recipe recipe, Ingredient ingredient);
	void addIngredientToStep( Step step, Ingredient ingredient );
	
	
}
