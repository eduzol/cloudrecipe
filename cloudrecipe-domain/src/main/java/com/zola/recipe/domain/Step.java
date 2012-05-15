package com.zola.recipe.domain;

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
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="part_of")
	private Recipe recipe;

	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(	name="ingredient_step",
				joinColumns={@JoinColumn(name="id_step")},
				inverseJoinColumns={@JoinColumn(name="id_ingredient")}	)
	private Set<Ingredient> stepIngredients = new HashSet<Ingredient>();
	
	
	public Set<Ingredient> getStepIngredients() {
		return stepIngredients;
	}

	
	public void addRecipe(Recipe recipe){
		
		if ( recipe != null){
			
			recipe.addStep(this);
		}
	}
	
	public void addIngredient( Ingredient ingredient){
		
		if ( ingredient != null){
			
			stepIngredients.add(ingredient);
			ingredient.getSteps().add(this);
			
		}
		
	}
	
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
		//recipe.addStep(this);
		//boolean added = recipe.getSteps().add(this);
		//System.out.println("*******-> "  + added);
		
	}

	@Override
	public String toString() {
		return "Step [stepId=" + stepId + ", name=" + name + ", stepNumber="
				+ stepNumber + ", description=" + description + ", recipe="
				+ recipe + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stepId;
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
		Step other = (Step) obj;
		if (stepId != other.stepId)
			return false;
		return true;
	}
	
	
	
	
	
}
