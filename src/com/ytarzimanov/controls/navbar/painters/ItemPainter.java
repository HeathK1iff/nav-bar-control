package com.ytarzimanov.controls.navbar.painters;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ytarzimanov.controls.basic.graphics.GraphicObject;
import com.ytarzimanov.controls.basic.painters.Painter;

public class ItemPainter extends Painter{
	private Color color = Color.black;
	
	public Color getColorFont(){
		return color;
	}
	
	public void setColorFont(Color color){
	   this.color = color;
	}

	@Override
	protected void drawFocused(Graphics2D Graphic, GraphicObject AGraphicObj) {
	  Graphic.setColor(new Color(0x33, 0x99, 0xFF));
	  Graphic.drawRect(1, 0, AGraphicObj.getWidth()-2, AGraphicObj.getHeight());
	  Graphic.setColor(new Color(0xC2, 0xE1, 0xFF));
	  Graphic.fillRect(2, 1, AGraphicObj.getWidth()-3, AGraphicObj.getHeight()-1);
	}

	@Override
	protected void draw(Graphics2D Graphic, GraphicObject AGraphicObj) {
      ;  	
	}

	@Override
	protected void drawSelected(Graphics2D arg0, GraphicObject arg1) {
		drawFocused(arg0, arg1);	
	}
	
}

