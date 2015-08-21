package com.ytarzimanov.controls.navbar.graphics;

import java.util.ArrayList;

import com.ytarzimanov.controls.basic.BasicComponent;
import com.ytarzimanov.controls.basic.graphics.GraphicObjectHelper;
import com.ytarzimanov.controls.navbar.interfaces.OnChangeGroupCheckBox;
import com.ytarzimanov.controls.navbar.interfaces.OnChangeGroupState;

public class Row extends GraphicObjectHelper{
    private ArrayList<OnChangeGroupState> onChangeGroupStateListeners = new ArrayList<OnChangeGroupState>();
    private ArrayList<OnChangeGroupCheckBox> onChangeGroupCheckListeners = new ArrayList<OnChangeGroupCheckBox>();
    
    public Row(BasicComponent Component, Object obj) {
		super(Component, obj);
	}
    
	protected void NotifyOnChangeGroupCheckBox(Group Group){
		int i = 0;
		while (i < onChangeGroupCheckListeners.size()) {
			onChangeGroupCheckListeners.get(i).onChangeGroupCheckBox(Group);
			i++;
		}
	}
	
	protected void NotifyOnChangeGroupState(Group Group){
		int i = 0;
		while (i < onChangeGroupStateListeners.size()) {
			onChangeGroupStateListeners.get(i).onChangeGroupState(Group);
			i++;
		}
	}
	
	public void addOnChangeGroupCheckListener(OnChangeGroupCheckBox Listener){
		onChangeGroupCheckListeners.add(Listener);
	}
	
	public void removeOnChangeGroupCheckListener(OnChangeGroupCheckBox Listener){
		onChangeGroupCheckListeners.remove(Listener);
	}
	
	public void addOnChangeGroupStateListener(OnChangeGroupState Listener){
		onChangeGroupStateListeners.add(Listener);
	}
	
	public void removeOnChangeGroupStateListener(OnChangeGroupState Listener){
		onChangeGroupStateListeners.remove(Listener);
	}
	

}
