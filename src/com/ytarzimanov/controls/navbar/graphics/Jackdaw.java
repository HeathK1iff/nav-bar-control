package com.ytarzimanov.controls.navbar.graphics;

import com.ytarzimanov.controls.basic.BasicComponent;
import com.ytarzimanov.controls.basic.graphics.GraphicObjectHelper;

public class Jackdaw extends GraphicObjectHelper{
	public enum JackState {gsCollapse, gsExpand};
	
	private JackState state = JackState.gsExpand;
	
	public Jackdaw(BasicComponent Component, int Hashcode)
	{
		super(Component, Hashcode);
		super.setHeight(6);
		super.setWidth(9);
	}
	
	public void setState(JackState State){
	  this.state = State;	
	}
	
	public JackState getState(){
		return state;
	}
}
