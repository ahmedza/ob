package com.onebridge.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.onebridge.manager.CategoryManager;
import com.onebridge.model.ProductCategory;
import com.onebridge.utils.enums.EnumCategoryLevel;
import com.parse.codec.binary.StringUtils;

public class CategoryActivity extends Activity{
	
	
	CategoryManager catManager = null;
	final List<ProductCategory> catList = new ArrayList<ProductCategory>();	
	String currentItem = "root-categories";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_main);
		
		inflateCategories(EnumCategoryLevel.ROOT);
		
		setSearchListener();
	}
	
	
	private void inflateCategories(EnumCategoryLevel catLevel){
		ListView listVw = (ListView)findViewById(R.id.catsList);
		
		catList.addAll(CategoryManager.getCatManager().getCategory("root-categories"));
		final ArrayAdapter<ProductCategory> adapter = new ArrayAdapter<ProductCategory>(this, R.layout.category_item, R.id.categoryTextVw, catList);
		listVw.setAdapter(adapter);
		
		listVw.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				 final ProductCategory item = (ProductCategory) parent.getItemAtPosition(position);
				 setCurrentItem(item.toString());
			        /*view.animate().setDuration(2000).alpha(0)
			            .withEndAction(new Runnable() {
			              @Override
			              public void run() {*/
			            	 catList.clear();
			            	 List<ProductCategory> newCatList = CategoryManager.getCatManager().getCategory(item.toString());
			            	 if(newCatList != null && newCatList.size() > 0){
			            		 catList.addAll(newCatList);
			            		 adapter.notifyDataSetChanged();
			            	 }else{
			            		 startProductActivity();
			            	 }
			               /* view.setAlpha(1);
			              }
			            });*/				
			}
			
			
			
		});
	}

	private void setCurrentItem(String currItem){
		this.currentItem = currItem;
	}
	
	public void setSearchListener(){
		final SearchView sView = (SearchView)findViewById(R.id.txSearchVw);
		/*sView.setOnSearchClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				search(sView);
			}
		});
		*/
		sView.setOnQueryTextListener(new OnQueryTextListener() {
			
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				search(query);
				return true;
			}
			
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
				search(newText);
				return true;
			}
		});
	}
	
	private void search(String queryStr){

		List<ProductCategory> resultList = new ArrayList<ProductCategory>();
		for (ProductCategory productCat : CategoryManager.getCatManager().getCategory(currentItem)) {
			if(productCat.toString().toLowerCase().contains(queryStr.toLowerCase())){
				resultList.add(productCat);
			}
		}
		
		catList.clear();
		catList.addAll(resultList);
		ListView listVw = (ListView)findViewById(R.id.catsList);
		((ArrayAdapter<ProductCategory>)(listVw.getAdapter())).notifyDataSetChanged();
	}
	
	private void startProductActivity(){
		
		Intent intent = new Intent(this, ProductActivity.class);
		startActivity(intent);
	}
}

