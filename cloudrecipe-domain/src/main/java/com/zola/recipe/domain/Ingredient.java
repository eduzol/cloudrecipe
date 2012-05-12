package com.zola.recipe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
