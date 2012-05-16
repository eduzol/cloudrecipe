package com.zola.web.representation;

import java.util.HashSet;
import java.util.Set;

import com.zola.recipe.domain.Recipe;
import com.zola.recipe.domain.Step;

public class RecipeView extends Recipe {

	
	
	
	public RecipeView( Recipe recipe ){
		
		super.setCreator(recipe.getCreator());
		super.setDateCreated(recipe.getDateCreated());
		super.setDescription(recipe.getDescription());
		super.setName(recipe.getName());
		super.setRecipeId(recipe.getRecipeId());
		/*
		super.setSteps(recipe.getSteps());
		
		for( Step s : super.getSteps() ){
			
			s.setRecipe(new Recipe());
			
		}
		*/
		
	}
}
