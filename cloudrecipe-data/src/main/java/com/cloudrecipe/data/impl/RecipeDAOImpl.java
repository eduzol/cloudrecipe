package com.cloudrecipe.data.impl;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloudrecipe.data.dao.RecipeDAO;
import com.zola.recipe.domain.Ingredient;
import com.zola.recipe.domain.Recipe;
import com.zola.recipe.domain.Step;
import com.zola.recipe.domain.User;

@Repository("recipeDAO")
public class RecipeDAOImpl implements RecipeDAO {

	
	private SessionFactory sessionFactory;
	
	@Autowired
	public RecipeDAOImpl( SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
		
	}
	
	private Session currentSession(){
		
		return sessionFactory.getCurrentSession();
	}
	
	
	@Transactional( propagation = Propagation.REQUIRED , readOnly = false)
	public void addRecipe(Recipe recipe, String username) {
		
		User user = (User)currentSession().get(User.class, username);
		recipe.setCreator(user);
	
		currentSession().saveOrUpdate(recipe);
		
		
	}

	
	@Transactional( propagation = Propagation.REQUIRED , readOnly = false)
	public void addStep(Recipe recipe, Step step) {
		
		step.setRecipe(recipe);
		currentSession().saveOrUpdate(step);
		
	}

	@Transactional( propagation = Propagation.REQUIRED , readOnly = false)
	public void addIngredient( Ingredient ingredient){
		currentSession().saveOrUpdate(ingredient);
	}
	
	@Transactional( propagation = Propagation.REQUIRED , readOnly = false)
	public void addIngredient(Step step, Ingredient ingredient) {
	
		step.addIngredient(ingredient);
		currentSession().saveOrUpdate(step);
		
	}

}
