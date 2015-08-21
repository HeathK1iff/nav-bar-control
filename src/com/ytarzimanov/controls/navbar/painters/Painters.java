package com.ytarzimanov.controls.navbar.painters;

import com.ytarzimanov.controls.basic.painters.BorderPainter;

public class Painters extends com.ytarzimanov.controls.basic.painters.Painters {
	private CheckBoxPainter checkboxpainter;
	private GroupHeaderPainter headerpainter;
	private ItemPainter itempainter;
	private BorderPainter borderpainter;
	private JackdawPainter jackdawpainter;
	
	public Painters() {

	}
	
	public BorderPainter getBorderPainter(){	
		if (borderpainter == null){
			borderpainter = new BorderPainter();
		}
	    return borderpainter;
	}
	
	public CheckBoxPainter getCheckBoxPainter(){	
		if (checkboxpainter == null){
			checkboxpainter = new CheckBoxPainter();
		}
	    return checkboxpainter;
	}
	
	public GroupHeaderPainter getHeaderPainter(){
		if (headerpainter == null){
			headerpainter = new GroupHeaderPainter();
		}
		return headerpainter;
	}
	
	public ItemPainter getItemPainter(){
		if (itempainter == null){
			itempainter = new ItemPainter();
		}
		return itempainter;
	}
	
	public JackdawPainter getJackdawPainter(){
		if (jackdawpainter == null){
			jackdawpainter = new JackdawPainter();
		}
		return jackdawpainter;	
	}

}
