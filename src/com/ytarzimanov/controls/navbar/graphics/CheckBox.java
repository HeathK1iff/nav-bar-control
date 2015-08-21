package com.ytarzimanov.controls.navbar.graphics;

import com.ytarzimanov.controls.basic.BasicComponent;
import com.ytarzimanov.controls.basic.graphics.GraphicObjectHelper;


public class CheckBox extends GraphicObjectHelper{
    public enum CheckBoxState {bsUnchecked, bsChecked, bsGrayed};
	private CheckBoxState state = CheckBoxState.bsUnchecked;
	
	public CheckBox(BasicComponent Component, int Hashcode)
	{
		super(Component, Hashcode);
		super.setHeight(11);
		super.setWidth(11);
	}
	
	public void setState(CheckBoxState State){
	  this.state = State;	
	}
	
	public CheckBoxState getState(){
		return state;
	}

}