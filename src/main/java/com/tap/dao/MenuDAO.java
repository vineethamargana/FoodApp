package com.tap.dao;

import java.util.ArrayList;

import com.tap.model.Menu;

public interface MenuDAO
{
	int addMenu(Menu m);
	ArrayList<Menu> getAllMenus();
	Menu getMenu(int itemId);
	int updateMenu(Menu m);
	int deleteMenu(String itemname);
	ArrayList<Menu> getAllMenusOnResID(int restaurantId);
	
}
