package com.onebridge.manager;

import java.util.ArrayList;
import java.util.List;

import android.view.MenuItem;

import com.onebridge.exception.DataException;
import com.onebridge.model.ProductCategory;
import com.parse.ParseException;
import com.parse.ParseQuery;



public class DataManager {

	private static DataManager dataManager= null;
	
	public List<ProductCategory> fetchMenuItems(){
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
	
	
	static DataManager getDataManager(){
		
		if(dataManager == null){
			dataManager = new DataManager();
		}
		
		return dataManager;
	}
}
