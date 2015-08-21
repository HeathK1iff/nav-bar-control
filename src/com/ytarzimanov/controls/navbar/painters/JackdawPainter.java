package com.ytarzimanov.controls.navbar.painters;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ytarzimanov.controls.basic.graphics.GraphicObject;
import com.ytarzimanov.controls.basic.painters.Painter;
import com.ytarzimanov.controls.navbar.graphics.Jackdaw;
import com.ytarzimanov.controls.navbar.graphics.Jackdaw.JackState;

public class JackdawPainter extends Painter{
    private JackState previusState = JackState.gsCollapse;
	
	protected Boolean ValidateOnChange(GraphicObject AGraphicObj){
		Jackdaw Obj =	(Jackdaw)AGraphicObj;
		Boolean Result = super.ValidateOnChange(AGraphicObj) || (previusState != Obj.getState());
		previusState = Obj.getState();
		return Result;	
	}
	
	protected void DrawJackdaw(Graphics2D Graphic, JackState AState, int AWidth, int AHeight)
	{
		Graphic.setPaint(new Color(0xA7, 0xBA, 0xC5)); 
		final int Height = AHeight;//6;
		final int Width = AWidth;//9;
		int iYHeight = 1;
		int iInvert = 1;

		  if (AState == JackState.gsExpand) {
		    iYHeight = Height - 2;
		    iInvert = -1;
		  };

		  int iTop = 0;
		  int iX = 0;
		  int iExTop = 0;
		  
		  int iYShift = 0;
		  while (iX < Width) {
		     iTop = Height - iYHeight;
		     iExTop = iTop;
		     if (iInvert == -1){
		       iExTop = iTop - 1;
		     }

		     if ((iX == 0) || (iX == Width-1)){
		    	 Graphic.drawLine(iX, iExTop, iX, iExTop - 2);
		     }
		     
		     if ((iX > 0) && (iX < Width-1)){
		    	 Graphic.drawLine(iX, iTop - (iYShift * iInvert), iX, ((iTop - (iYShift * iInvert)) - 3));
		       if (iX < 4){
		         iYShift++;
		       }
		       else {
		         iYShift--;
		       };
		     };

		    iX++;
		  };
	}

	@Override
	protected void drawFocused(Graphics2D Graphic, GraphicObject AGraphicObj) {
		draw(Graphic, AGraphicObj);		
	}

	@Override
	protected void draw(Graphics2D Graphic, GraphicObject AGraphicObj) {
		Jackdaw Obj =	(Jackdaw)AGraphicObj;
		DrawJackdaw(Graphic, Obj.getState(), Obj.getWidth(), Obj.getHeight());
	}

	@Override
	protected void drawSelected(Graphics2D arg0, GraphicObject arg1) {
		drawFocused(arg0, arg1);	
	}
}
