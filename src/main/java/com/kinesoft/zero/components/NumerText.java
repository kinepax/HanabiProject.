package com.kinesoft.zero.components;

public class NumerText extends com.vaadin.flow.component.textfield.NumberField {
	
	public NumerText() {
		this.setValue(0d);
		
	//	this.setHasControls(true);
		this.setStepButtonsVisible(true);
		this.setMin(0);
		this.setMax(10);
		this.getStyle().set("width","100px");
	}

}
