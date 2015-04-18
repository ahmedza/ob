package com.onebridge.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.onebridge.manager.CategoryManager;
import com.onebridge.model.ProductCategory;
import com.onebridge.utils.enums.EnumCategoryLevel;

public class CategoryActivity extends Activity{
	
	
	CategoryManager catManager = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_main);
		
		inflateCategories(EnumCategoryLevel.ROOT);
	}
	
	
	private void inflateCategories(EnumCategoryLevel catLevel){
		ListView listVw = (ListView)findViewById(R.id.catsList);
		
		final List<ProductCategory> catList = getCatManager().getCategory("root-categories");
		final ArrayAdapter<ProductCategory> adapter = new ArrayAdapter<ProductCategory>(this, R.layout.category_item, R.id.categoryTextVw, catList);
		listVw.setAdapter(adapter);
		
		listVw.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				 final ProductCategory item = (ProductCategory) parent.getItemAtPosition(position);
			        view.animate().setDuration(2000).alpha(0)
			            .withEndAction(new Runnable() {
			              @Override
			              public void run() {
			            	 catList.clear();
			            	 List<ProductCategory> newCatList = getCatManager().getCategory(item.toString());
			            	 if(newCatList != null && newCatList.size() > 0){
			            		 catList.addAll(newCatList);
			            		 adapter.notifyDataSetChanged();
			            	 }
			                view.setAlpha(1);
			              }
			            });				
			}
			
			
			
		});
	}
	
	

	
	private CategoryManager getCatManager(){
		
		if(catManager == null){
			catManager = new CategoryManager();
		}
		return catManager;
	}

}

