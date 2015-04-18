package com.onebridge.manager;

import java.util.ArrayList;
import java.util.List;

import android.view.MenuItem;
import android.view.SubMenu;

import com.onebridge.model.ProductCategory;

public class MenuLoader {
	
	public List<MenuItem> fetchMenuItems(SubMenu menu){
		List<MenuItem> menuItems = new  ArrayList<MenuItem>();
		
		List<ProductCategory> cats= DataManager.getDataManager().fetchProductCategories();
		
		addRoot(menu, cats, 0);
		
		return menuItems;
	}
	
	private void addRoot(SubMenu menu, List<ProductCategory> cats, int parent){
		
		for (ProductCategory productCategory : cats) {
			if(productCategory.getNumber("parentCategoryId").intValue() == parent){
				SubMenu subMenu = menu.addSubMenu(productCategory.getString("productCategoryName"));
				addRoot(subMenu, cats, productCategory.getNumber("productCategoryId").intValue());	
				
			}
		}
		
	}
	
	

}
