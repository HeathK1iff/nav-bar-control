package com.ytarzimanov.controls.navbar.graphics;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.ytarzimanov.controls.basic.BasicComponent;
import com.ytarzimanov.controls.navbar.NavBar;
import com.ytarzimanov.controls.navbar.graphics.Jackdaw.JackState;
import com.ytarzimanov.controls.navbar.painters.Painters;

public class Item extends Row{
	private String caption = "";
	private Painters painters;
	private Group group;
	
	
	public Item(Painters Painters, BasicComponent Component, Group Group, Object obj) {
		super(Component, obj);		
		this.painters = Painters;
		this.group = Group;
		setHeight(22);
	}
	
	
	 protected void onMouseLeftClicked(MouseEvent e) {
		  if (this.group.getGroupState() == JackState.gsExpand){
		    super.onMouseLeftClicked(e);
		  }
	}
	
	 public void Draw(Graphics2D g2d) {
		 if (caption != null && !caption.isEmpty())
			 this.setHeight(DrawWordWrapText(g2d, getLeft()+30, getTop(), getWidth()- 50, caption, true));
		 
		 g2d.drawImage(painters.getItemPainter().drawObj(this), getLeft(),  getTop(), null);	
		 
		 
		 if (((NavBar)component).getItemDrawHandler() != null){
			 ((NavBar)component).getItemDrawHandler().onCustomDrawItem(this, g2d, painters.getItemPainter());
		 }
		 else
		 {
		   g2d.setColor(painters.getItemPainter().getColorFont());	  
		 }
		 if (caption != null && !caption.isEmpty())
			 DrawWordWrapText(g2d, getLeft()+30, getTop() + 3, getWidth()- 50, caption);
	 }

	public void setCaption(String Caption) {
		this.caption = Caption;		
	}
	
	public String getCaption(){
		return caption;
	}
	
}
