package com.zola.recipe.domain;

import java.util.Date;

public class Recipe {

	private int recipeId;
	private String name;
	private String description;
	private Date dateCreated;
	
	private User creator;

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
