package com.zola.recipe.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="ingredient")
public class Ingredient {

	@Id @GeneratedValue
	@Column(name ="id_ingredient")
	private int ingredientId;

	@Column(name="name")
	private String name;
	
	@Column(name ="description")
	private String description;
	
	
	@ManyToMany(mappedBy="ingredients", cascade = CascadeType.ALL)
	private Set<Recipe> recipes = new HashSet<Recipe>();
	
	@ManyToMany( mappedBy="stepIngredients", cascade = CascadeType.ALL)
	private Set<Step> steps = new HashSet<Step>();
	
	
	public Set<Step> getSteps() {
		return steps;
	}

	public Set<Recipe> getRecipes() {
		return recipes;
	}
	
	public void addRecipe(Recipe recipe){
		if ( recipe != null){
			
			recipe.addIngredient(this);
		}
		
	}
	
	
	public int getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
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
	@Override
	public String toString() {
		return "Ingredient [ingredientId=" + ingredientId + ", name=" + name
				+ ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
