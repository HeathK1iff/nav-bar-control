package com.ytarzimanov.controls.navbar.interfaces;

public interface DataModel {
	public int getGroupCount();
	public Object getGroup(int index);
	public Boolean getGroupChecked(int index);
	public Boolean getGroupExpanded(int index);
	
	
	public int getItemsCount(int groupIndex);
	public Object getItem(int groupIndex, int itemIndex);
}
