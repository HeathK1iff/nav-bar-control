package com.ytarzimanov.controls.navbar.painters;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;

import com.ytarzimanov.controls.basic.graphics.GraphicObject;
import com.ytarzimanov.controls.basic.painters.Painter;

public class GroupHeaderPainter extends Painter{
	private Color color = Color.black;
	
	public Color getColorFont(){
		return color;
	}
	
	public void setColorFont(Color color){
	   this.color = color;
	}

	@Override
	protected void drawFocused(Graphics2D Graphic, GraphicObject AGraphicObj) {
		Composite orgComposite = Graphic.getComposite();
		Graphic.setComposite(makeTransparent(0.6F));
		Graphic.setPaint(new Color(0xDF, 0xF2, 0xFC));
		Graphic.fillRect(1, 1, AGraphicObj.getWidth()-1, 7);
		Graphic.setPaint(new Color(0xBC, 0xE5, 0xFC));
		Graphic.fillRect(1, 7, AGraphicObj.getWidth()-1, AGraphicObj.getHeight()-7);
		Graphic.setPaint(new Color(0x3C, 0x7F, 0xB1));
		Graphic.drawLine(1, AGraphicObj.getHeight(), AGraphicObj.getWidth()-1, AGraphicObj.getHeight());
		Graphic.setComposite(orgComposite);
	}

	@Override
	protected void draw(Graphics2D Graphic, GraphicObject AGraphicObj) {
		Graphic.setColor(new Color(0xE9, 0xE9, 0xE9));
		Graphic.drawLine(1, AGraphicObj.getHeight(), AGraphicObj.getWidth()-1, AGraphicObj.getHeight());
	}

	@Override
	protected void drawSelected(Graphics2D arg0, GraphicObject arg1) {
		drawFocused(arg0, arg1);
	}
}
