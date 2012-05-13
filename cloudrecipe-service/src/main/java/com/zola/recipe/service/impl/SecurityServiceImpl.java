package com.zola.recipe.service.impl;

import java.util.List;

import com.zola.recipe.domain.Ingredient;
import com.zola.recipe.domain.Recipe;
import com.zola.recipe.domain.Step;
import com.zola.recipe.service.facade.SecurityService;


//TODO finish facade
public class SecurityServiceImpl implements SecurityService {

	public void addOrUpdateRecipe(Recipe recipe, String username) {
		// TODO Auto-generated method stub
		
	}

	public List<Recipe> getRecipes(String username, int offset, int total) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		
	}

	public void addStep(Recipe recipe, Step step) {
		// TODO Auto-generated method stub
		
	}

	public void addIngredientToRecipe(Recipe recipe, Ingredient ingredient) {
		// TODO Auto-generated method stub
		
	}

	public void addIngredientToStep(Step step, Ingredient ingredient) {
		// TODO Auto-generated method stub
		
	}

}
