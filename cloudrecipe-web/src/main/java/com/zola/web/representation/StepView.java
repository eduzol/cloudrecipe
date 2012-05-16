package com.zola.web.representation;

import com.zola.recipe.domain.Step;

public class StepView extends Step {

	public StepView( Step step){
		super.setDescription(step.getDescription());
		super.setName(step.getName());
		super.setStepId(step.getStepId());
		super.setStepNumber(step.getStepNumber());
		
	}
}
