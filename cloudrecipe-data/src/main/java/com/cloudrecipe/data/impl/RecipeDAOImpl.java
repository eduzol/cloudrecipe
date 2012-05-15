package com.cloudrecipe.data.impl;

import java.util.List;

import org.hibernate.Query;
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
	public int addRecipe(Recipe recipe, String username) {
		
		User user = (User)currentSession().get(User.class, username);
		recipe.setCreator(user);
		currentSession().saveOrUpdate(recipe);
		return recipe.getRecipeId();
		
	}

	
	@Transactional( propagation = Propagation.REQUIRED , readOnly = false)
	public int addStep(Recipe recipe, Step step) {
		
		step.setRecipe(recipe);
		currentSession().saveOrUpdate(step);
		return step.getStepId();
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
	
	@Transactional( propagation = Propagation.REQUIRED , readOnly = true)
	public List<Recipe> getRecipes( String username ) {
	
		
		User creator =(User) currentSession().get(User.class, username);
		Query query = currentSession().createQuery("from  Recipe recipe where recipe.creator = :creator order by recipe.recipeId asc");
		query.setEntity("creator", creator);
		
		List<Recipe> recipes = (List<Recipe>)query.list();

		return recipes;
	}
	
	@Transactional( propagation = Propagation.REQUIRED , readOnly = false)
	public void deleteRecipe( int recipeId , String username ) {
		
		User creator =(User) currentSession().get(User.class, username);
		Query query = currentSession().createQuery("from  Recipe recipe where recipe.creator = :creator and recipe.recipeId = :recipeid");
		query.setEntity("creator", creator);
		query.setInteger("recipeid", recipeId);
		
		Recipe recipe = (Recipe)query.uniqueResult();
		if (recipe == null)
			return;
		
		System.out.println("deleting recipe " + recipe);
		
		currentSession().delete(recipe);
		
	}
	@Transactional( propagation = Propagation.REQUIRED , readOnly = true)
	public Recipe getRecipe(int recipeId) {
		Recipe recipe = (Recipe) currentSession().get(Recipe.class, recipeId);
		
		return recipe;
	}

	
	@Transactional( propagation = Propagation.REQUIRED , readOnly = true)
	public List<Step> getSteps(Recipe recipe) {
		Query query = currentSession().createQuery("from Step step where step.recipe = :recipe order by step.stepNumber asc");
		query.setEntity("recipe", recipe );
		
		List<Step> steps = (List<Step>)query.list();
		
		return steps;
	}
	
	@Transactional( propagation = Propagation.REQUIRED , readOnly = true)
	public Step getStep( Recipe recipe, int stepNumber){
		
		Query query = currentSession().createQuery("from Step step where step.recipe = :recipe  and step.stepNumber = :stepNumber");
		query.setEntity("recipe", recipe);
		query.setInteger("stepNumber", stepNumber);
		
		Step step  = (Step)query.uniqueResult();
		return step;
		
	}

	@Transactional( propagation = Propagation.REQUIRED , readOnly = false)
	public void deleteStep(Recipe recipe , Step step){
		
		//to mantain transitivity
		recipe.removeStep(step);
		currentSession().delete(step);
				
	}
}
