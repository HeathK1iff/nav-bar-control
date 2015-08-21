package com.ytarzimanov.controls.navbar.interfaces;

import java.awt.Graphics2D;

import com.ytarzimanov.controls.navbar.graphics.Item;
import com.ytarzimanov.controls.navbar.painters.ItemPainter;

public interface OnCustomDrawItem {
	 public void onCustomDrawItem(Item Item, Graphics2D g2d, ItemPainter painter);
}
