package com.onebridge.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onebridge.model.ProductCategory;

public class CategoryManager {
	
	private static CategoryManager catManager = null;
	private Map<String, List<ProductCategory>> catMap = null;
	
	public List<ProductCategory> LoadCategories(){
		
		List<ProductCategory> catsList = new ArrayList<ProductCategory>();
		catsList = DataManager.getDataManager().fetchProductCategories();
		addRoot(catsList,0);
		for (ProductCategory productCategory : catsList) {
			addRoot(catsList, productCategory.getNumber("productCategoryId").intValue());
		}
		
		
		return catsList;
	}
	
	
	public List<ProductCategory> getCategory(String catName){
		
		if(catMap == null){
			catMap = new HashMap<String, List<ProductCategory>>();
			LoadCategories();
		}
		
		return catMap.get(catName);
	}
	
	private void addRoot(List<ProductCategory> cats, int parent){
		
		String keyName = "";
		List<ProductCategory> childCats = new ArrayList<ProductCategory>();
		for (ProductCategory productCategory : cats) {
		
/*			if(productCategory.getNumber("parentCategoryId").intValue() == 0){
				keyName = "root-categories";
			}
			else */if(productCategory.getNumber("productCategoryId").intValue() == parent){
				keyName = productCategory.getString("productCategoryName");
			}
			
			if(productCategory.getNumber("parentCategoryId").intValue() == parent){
				childCats.add(productCategory);
/*				addRoot(cats, productCategory.getNumber("productCategoryId").intValue());*/
			}
		}
		
		if(parent == 0){
			keyName = "root-categories";
		}
		
		if(childCats != null && childCats.size() > 0){
			catMap.put(keyName, childCats);
		}
	}
	
	
	public static CategoryManager getCatManager(){
		if(catManager == null){
			catManager = new CategoryManager();
		}
		return catManager;
	}

}
