package com.ytarzimanov.controls.navbar.graphics;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;





import com.ytarzimanov.controls.basic.BasicComponent;
import com.ytarzimanov.controls.navbar.NavBar;
import com.ytarzimanov.controls.navbar.graphics.CheckBox.CheckBoxState;
import com.ytarzimanov.controls.navbar.graphics.Jackdaw.JackState;
import com.ytarzimanov.controls.navbar.painters.Painters;

public class Group extends Row {
	protected final int HEADER_HEIGHT = 25;
	private Painters painters;
	protected Header header;
	protected CheckBox checkBox = new CheckBox(component, HEADER_HEIGHT);
	protected Jackdaw jackdaw = new Jackdaw(component, HEADER_HEIGHT);
	private ArrayList<Item> items = new ArrayList<Item>();
	private String caption;
	protected int itemsHeight;
	
	public Group(Painters Painters, BasicComponent Component, Object obj){
		super(Component, obj);
		setCaption(obj.toString());
		this.painters = Painters;
		header = new Header(component, obj);
		header.setHeight(HEADER_HEIGHT);
		itemsHeight = 0;
		checkBox.setLeft(5);
		checkBox.setTop(5);
		
		jackdaw.setLeft(5);
		jackdaw.setTop(10);
	}

	
	public void setGroupState(JackState State){
		jackdaw.setState(State);
	}
	
	public JackState getGroupState(){
		return jackdaw.getState();
	}
	
	public void ItemsClear(){
		if (items.size() > 0)
		for (int i = items.size() - 1; i >= 0 ; i--){
	        removeItem(items.get(i));
		}
	}
	
	public void setCheckState(Boolean State){
		if (State == true){
			checkBox.setState(CheckBoxState.bsChecked);
		}
		else
		{
			checkBox.setState(CheckBoxState.bsUnchecked);
		}
	}
	
	
	public Boolean getCheckState(){
		if (checkBox != null){
		  return (checkBox.getState() == CheckBoxState.bsChecked);
		}
		else
		{
			return false;
		}
	}
	
    public int getHeight(){
    	if (getGroupState() == JackState.gsExpand){
    		setHeight(itemsHeight + header.getHeight());	 
    	}else{
    		setHeight(header.getHeight());	
    	}
    			
    	return super.getHeight();
    }
	
    public void setWidth(int Width){
    	header.setWidth(Width);
    	jackdaw.setLeft(Width - 20);
    	super.setWidth(Width);
    }
    
   public Item addItem(Object obj){
		Item item = new Item(this.painters, component, this, obj);
		item.setCaption(obj.toString());		
		item.addOnClickListener((NavBar)component);
		item.addOnChangeGroupStateListener((NavBar)component);	
		item.addOnChangeGroupCheckListener((NavBar)component);
		
		items.add(item);
		return item;   
   }
   
   
  public void setTop(int Top){
	  super.setTop(Top);
	  header.setTop(Top);
  }
   
   
  public void removeItem(Item item){
		item.dispose();
		item.removeOnClickListener((NavBar)component);
		item.removeOnChangeGroupStateListener((NavBar)component);	
		item.removeOnChangeGroupCheckListener((NavBar)component);	
		
		items.remove(item);
  }
   
    
	public int CountItems(){
		return items.size();
	}
     
	
	public Item getItem(int Index){
		return items.get(Index);
	}
	
   public void draw(Graphics2D g2d) {
	   
	   if (caption != null && !caption.isEmpty())
		   header.setHeight(DrawWordWrapText(g2d, getLeft()+20, getTop(), getWidth() - 45, caption, true)+3);
	   
	   g2d.drawImage(painters.getHeaderPainter().drawObj(header), getLeft(),  getTop(), null);
	   g2d.drawImage(painters.getJackdawPainter().drawObj(jackdaw), getLeft() + jackdaw.getLeft(),  getTop()+jackdaw.getTop(), null);
		
	   if (((NavBar) component).getCheckBoxEnabled()) { 
	   g2d.drawImage(painters.getCheckBoxPainter().drawObj(checkBox), getLeft()+checkBox.getLeft(),  getTop()+checkBox.getTop(), null);};
		
	   g2d.setColor(painters.getHeaderPainter().getColorFont());
		
	   if (caption != null && !caption.isEmpty())
	     DrawWordWrapText(g2d, getLeft()+20, getTop() + 4, getWidth() - 45, caption);
	   
	   if (getGroupState() == JackState.gsExpand){
			int i = 0;
			itemsHeight = 0;
		   
			while (i < items.size()){
			   items.get(i).setWidth(getWidth()-1);
			   items.get(i).setTop(getTop() + header.getHeight() + itemsHeight);
			   items.get(i).Draw(g2d);
			   if (items.size() > i)
			     itemsHeight += items.get(i).getHeight();	      
			   i++;
		} 			
		}
	    
	} 
	
	

	public void setCaption(String Caption){
	  this.caption = Caption;	
	}

	public String getCaption(){
		return caption;
	}
	
	protected void onMouseRightClicked(MouseEvent e){
	   ;
	}
	
	protected void onMouseLeftClicked(MouseEvent e){
	  super.onMouseLeftClicked(e);
	  if (header.PointBelongIt(null, e.getX(), e.getY()) == true){
	  if (checkBox.PointBelongIt(this, e.getX(), e.getY()) == true){
		if (checkBox.getState() == CheckBoxState.bsUnchecked) {
			checkBox.setState(CheckBoxState.bsChecked);
		} else {
			checkBox.setState(CheckBoxState.bsUnchecked);}
			  NotifyOnChangeGroupCheckBox(this);
		     }else{
			   if (getGroupState() == JackState.gsExpand) {
				setGroupState(JackState.gsCollapse);
			   } else {
				setGroupState(JackState.gsExpand);
			   }
			   NotifyOnChangeGroupState(this);
		     }
	  }
		component.repaint();
	}
		
}






