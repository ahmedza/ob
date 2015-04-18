package com.onebridge.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("ProductCategory")
public class ProductCategory extends ParseObject {
	
	  public static ParseQuery<ProductCategory> getQuery() {
		    return ParseQuery.getQuery(ProductCategory.class);
		  }

	  
	  @Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getString("productCategoryName");
	}
}
