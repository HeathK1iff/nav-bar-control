package com.ytarzimanov.controls.navbar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.ytarzimanov.controls.basic.graphics.*;
import com.ytarzimanov.controls.navbar.graphics.*;
import com.ytarzimanov.controls.navbar.graphics.Jackdaw.JackState;
import com.ytarzimanov.controls.navbar.interfaces.*;
import com.ytarzimanov.controls.navbar.painters.*;


/**
 * JNavigator component for SWING application
 * 
 * @author Tarzimanov Yuri
 * @since 31.01.2013
 */

@SuppressWarnings("serial")
public class NavBar extends com.ytarzimanov.controls.basic.BasicComponent implements OnChangeGroupCheckBox, OnChangeGroupState, OnClickListener{
	private ArrayList<OnChangeGroupCheckBox> onChangeGroupCheckBoxListeners =  new ArrayList<OnChangeGroupCheckBox>();
	private ArrayList<OnChangeGroupState> onChangeGroupStateListeners =  new ArrayList<OnChangeGroupState>();
	
	private ArrayList<OnClickListener> onClickListeners;
	private GraphicObjectHelper border;
	private Painters painters  = new Painters();
	private ArrayList<Group> groups = new ArrayList<Group>();
    private DataModel model;
    private Boolean enabledCheckBox = false;
	private OnCustomDrawItem itemDrawHandler = null;
	
	public NavBar() {
		super();
		painters = new Painters();
		border = new GraphicObjectHelper(this, 0);
		onClickListeners = new ArrayList<OnClickListener>();
	}
	
	public void setDrawHandler(OnCustomDrawItem ItemDrawHandler){
		this.itemDrawHandler = ItemDrawHandler;
	}
	
	public OnCustomDrawItem getItemDrawHandler(){
		return itemDrawHandler;
	}
	
	public void setCheckBoxEnabled(Boolean enabled){
		this.enabledCheckBox = enabled;
	}

	public Boolean getCheckBoxEnabled(){
		return enabledCheckBox;
	}
	
	
	public void update(){
		int i = 0;
		int j = 0;
			
		while (i < groups.size()){
			groups.get(i).ItemsClear();
			i++;
		}
		GroupClear();
		
		i = 0;
		j = 0;
		int height = 0;
		while (i < model.getGroupCount()){
			Group Group = addGroup(model.getGroup(i));
			Group.setCheckState(model.getGroupChecked(i));
			Group.setGroupState(JackState.gsCollapse);
			if (model.getGroupExpanded(i)){
			  Group.setGroupState(JackState.gsExpand);};

			j = 0;		
			while (j < model.getItemsCount(i)){
				Group.addItem(model.getItem(i, j));
				j++;
			}
			i++;
			height += Group.getHeight();
		}
		
		
		setComponentHeight(height);	
        repaint();
	}
	
	protected void updateHeight(){
		int Height = 0;
		int i = 0;
		while (i < groups.size()){
			Height += groups.get(i).getHeight();
			i++;
		}
		setComponentHeight(Height);
	}
	
	private Group addGroup(Object obj){
		Group item = new Group(this.painters, this, obj);
		item.addOnChangeGroupCheckListener(this);
		item.addOnChangeGroupStateListener(this);		
		item.addOnClickListener(this);
		groups.add(item);
		return item;
	}
	
	public void GroupClear(){
		if (groups.size() > 0)
		for (int i = groups.size() - 1; i >= 0 ; i--){
			removeGroup(groups.get(i));
		}
	}
	
	private void removeGroup(Group group){
		group.dispose();
		group.removeOnChangeGroupCheckListener(this);
		group.removeOnChangeGroupStateListener(this);
		group.removeOnClickListener(this);
		
		groups.remove(group);
	}
	
	
	 @Override
	 protected void paintComponent(Graphics g) {
	   Graphics2D g2d = (Graphics2D) g;
	   g2d.setPaint(Color.WHITE);
	   g2d.fillRect(0, 0, getWidth(), getHeight());
	   g2d.setPaint(Color.BLACK);
	   
	   border.setHeight(getHeight()-1);
	   border.setWidth(getWidth()-1);
	   g2d.drawImage(painters.getBorderPainter().drawObj(border), border.getLeft(), border.getTop(), this);
	   int i = 0;
	   int h = 0;
	   
	   while (i < groups.size()){
		   groups.get(i).setWidth(border.getWidth());
		   groups.get(i).setTop(h);
		   groups.get(i).draw(g2d);
		   h += groups.get(i).getHeight();	   
		   i++;
	   } 
	   setComponentHeight(h);
	 }

	protected void notifyOnClick(Item Item){
		int i = 0;
		while (i < onClickListeners.size()) {
			onClickListeners.get(i).onClickItem(Item);
			i++;
		}
	}
	

	protected void notifyOnChangeGroupCheckBox(Group group){
		int i = 0;
		while (i < onChangeGroupCheckBoxListeners.size()) {
			onChangeGroupCheckBoxListeners.get(i).onChangeGroupCheckBox(group);
			i++;
		}
	}
	

	public void setModel(DataModel model){
		this.model = model;
	}
	
	
	protected void notifyOnChangeGroupState(Group Group){
		int i = 0;
		while (i < onChangeGroupStateListeners.size()) {
			onChangeGroupStateListeners.get(i).onChangeGroupState(Group);
			i++;
		}
	}
	
	
	public void addOnChangeGroupStateListener(OnChangeGroupState listener){
		onChangeGroupStateListeners.add(listener);
	}
	
	public void removeOnChangeGroupStateListener(OnChangeGroupState listener){
		onChangeGroupStateListeners.remove(listener);
	}
	
	public void addOnChangeGroupCheckBoxListener(OnChangeGroupCheckBox listener){
		onChangeGroupCheckBoxListeners.add(listener);
	}
	
	public void removOnChangeGroupCheckBoxListener(OnChangeGroupCheckBox listener){
		onChangeGroupCheckBoxListeners.remove(listener);
	}
	
	public void addOnClickListener(OnClickListener listener){
		onClickListeners.add(listener);
	}
	
	public void removeOnClickListener(OnClickListener listener){
		onClickListeners.remove(listener);
	}
	

	@Override
	public void onChangeGroupCheckBox(Group group) {
		notifyOnChangeGroupCheckBox(group);
	}

	@Override
	public void onChangeGroupState(Group group) {
		updateHeight();
		notifyOnChangeGroupState(group);
	}

	@Override
	public void onClickMouseLeftButton(GraphicObject Object) {
		if (Object instanceof Item) {
		  onClickItem((Item)Object); 
		}
	}

	@Override
	public void onClickMouseRightButton(GraphicObject Object) {
        ;
	}

	@Override
	public void onClickItem(Item Item) {
		notifyOnClick(Item);
		
	}
}
