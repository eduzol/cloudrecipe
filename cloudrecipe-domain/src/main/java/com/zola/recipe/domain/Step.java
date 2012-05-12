package com.zola.recipe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name ="step")
public class Step {

	
	@Id @GeneratedValue
	@Column(name ="id_step")
	private int stepId;
	
	
	@Column(name ="name")
	private String name;
	
	@Column(name = "number")
	private int stepNumber;
	
	@Column(name ="description")
	private String description;
	
	
	@ManyToOne
	@JoinColumn(name ="part_of")
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
