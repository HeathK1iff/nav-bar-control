package com.ytarzimanov.controls.navbar.painters;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ytarzimanov.controls.basic.graphics.GraphicObject;
import com.ytarzimanov.controls.basic.painters.Painter;
import com.ytarzimanov.controls.navbar.graphics.CheckBox;
import com.ytarzimanov.controls.navbar.graphics.CheckBox.CheckBoxState;

public class CheckBoxPainter extends Painter{
	private CheckBoxState previusState = CheckBoxState.bsGrayed;
	
	protected Boolean ValidateOnChange(GraphicObject AGraphicObj){
		CheckBox Obj =	(CheckBox)AGraphicObj;
		Boolean Result = super.ValidateOnChange(AGraphicObj) || (previusState != Obj.getState());
		previusState = Obj.getState();
		return Result;	
	}
	
	protected void DrawCheckBox(Graphics2D Graphic, CheckBoxState AState, int Width, int Height)
	{
		final int xPos = 0;
	    final int yPos = 0;
	    final int lnSize = 3;
	    int x,y = 0;
	        
	    Graphic.setPaint(Color.white); 
	    Graphic.fillRect(xPos, yPos, xPos + Width - 1, yPos + Height);
	    Graphic.setPaint(Color.black); 
	    Graphic.drawRect(xPos, yPos, xPos + Width - 1, yPos + Height);
	    
	    if (AState == CheckBoxState.bsChecked) {
	      if (AState == CheckBoxState.bsGrayed) {
	    	  Graphic.setColor(Color.gray);};
	      x = 2;
	      y = 4;
	      while (x < 9){
	    	  Graphic.drawLine(xPos+x, yPos+y, xPos+x, yPos+lnSize+y);
	        x++;
	        if(x < 5){
	          y++;}
	        else {
	          y--;};
	      };
	    };    
	}

	@Override
	protected void drawFocused(Graphics2D Graphic, GraphicObject AGraphicObj) {
		draw(Graphic, AGraphicObj);
	}

	@Override
	protected void draw(Graphics2D Graphic, GraphicObject AGraphicObj) {
		CheckBox Obj =	(CheckBox)AGraphicObj;
		DrawCheckBox(Graphic, Obj.getState(), Obj.getWidth(), Obj.getHeight());	
	}

	@Override
	protected void drawSelected(Graphics2D arg0, GraphicObject arg1) {
		drawFocused(arg0, arg1);	
	}

}
