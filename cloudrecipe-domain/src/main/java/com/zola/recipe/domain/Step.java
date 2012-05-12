package com.zola.recipe.domain;

public class Step {

	private int stepId;
	private String name;
	private int stepNumber;
	private String description;
	
	private Recipe recipe;

	public int getStepId() {
		return stepId;
	}

	public void setStepId(int stepId) {
		this.stepId = stepId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "Step [stepId=" + stepId + ", name=" + name + ", stepNumber="
				+ stepNumber + ", description=" + description + ", recipe="
				+ recipe + "]";
	}
	
	
	
	
	
}
