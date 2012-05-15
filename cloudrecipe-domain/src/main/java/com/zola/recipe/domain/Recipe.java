package com.zola.recipe.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="recipe")
public class Recipe {

	@Id @GeneratedValue
	@Column(name="id_recipe")
	private int recipeId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="date_created")
	private Date dateCreated;
	
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy="recipe", orphanRemoval=true)
	private Set<Step> steps = new HashSet<Step>();
	
	
	
	public Set<Step> getSteps() {
		return steps;
	}

	public void addStep(Step step){
		
		if ( step != null ){
			
			steps.add(step);
			System.out.println("added");
			step.setRecipe(this);
			System.out.println("setrecipe...");
		}
	}
	
	public void removeStep(Step step){
		
		if ( step != null ){
			steps.remove(step);
			step.setRecipe(null);
			
		}
		
	}
	
	@ManyToOne
	@JoinColumn(name="created_by")
	private User creator;

	@ManyToMany( cascade = CascadeType.ALL)
	@JoinTable(name ="prepared_with",
				joinColumns={@JoinColumn(name="id_recipe")},
				inverseJoinColumns={@JoinColumn(name="id_ingredient")}	)
	private Set<Ingredient> ingredients = new HashSet<Ingredient>();
	
	
	
	
	public void addIngredient( Ingredient ingredient){
		
		if ( ingredient != null){
			
			ingredients.add(ingredient);
			//add recipe to ingredients too!!!
			ingredient.getRecipes().add(this);
		}
		
	}
	
	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "Recipe [recipeId=" + recipeId + ", name=" + name
				+ ", description=" + description + ", dateCreated="
				+ dateCreated + ", creator=" + creator + "]";
	}
	

}
