package com.onebridge.manager;

import java.util.List;

import com.onebridge.model.ProductCategory;
import com.parse.ParseException;
import com.parse.ParseQuery;



public class DataManager {

	private static DataManager dataManager= null;
	
	protected List<ProductCategory> fetchProductCategories(){
		/*List<MenuItem> menuItems = new  ArrayList<MenuItem>();*/
		
		ParseQuery<ProductCategory> categories= ProductCategory.getQuery();
				
		
		try {
			return categories.find();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	protected static DataManager getDataManager(){
		
		if(dataManager == null){
			dataManager = new DataManager();
		}
		
		return dataManager;
	}
}
