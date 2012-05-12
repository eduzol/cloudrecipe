package com.zola.recipe.domain;

public class Ingredient {

	private int ingredientId;
	private String name;
	private String description;
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
	
	
	
}
