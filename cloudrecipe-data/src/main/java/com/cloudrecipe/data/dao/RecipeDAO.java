package com.cloudrecipe.data.dao;

import com.zola.recipe.domain.Ingredient;
import com.zola.recipe.domain.Recipe;
import com.zola.recipe.domain.Step;

public interface RecipeDAO {

	
	void addRecipe( Recipe recipe, String username);
	
	void addStep ( Recipe recipe, Step step);
	
	void addIngredient( Step step, Ingredient ingredient);
	
	void addIngredient( Ingredient ingredient);

}
