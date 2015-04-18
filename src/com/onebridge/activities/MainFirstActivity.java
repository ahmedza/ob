package com.onebridge.activities;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import com.onebridge.manager.MenuLoader;

public class MainFirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("In On create");
        setContentView(R.layout.activity_main_first);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        
        MenuItem mItem = menu.findItem(R.id.item_Categories);
        SubMenu subCats = mItem.getSubMenu();
        new MenuLoader().fetchMenuItems(subCats);
        System.out.println("In Create Menu Options");
        
        return true;
    }
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	// TODO Auto-generated method stub
    	boolean val = super.onMenuItemSelected(featureId, item);
    	return val; /*super.onMenuItemSelected(featureId, item);*/
    }
        
    public void loadCategoryScreen(View view){
    	Intent intent = new Intent(this, CategoryActivity.class);
    	/*String msg = ((EditText)(findViewById(R.id.edit_message))).getText().toString();
    	intent.putExtra("MESSAGE", msg);*/
    	startActivity(intent);
    }
    
    @SuppressLint("NewApi") 
    public void openFriends(View view){    	
    	System.out.println("In Open Friends");
    	/*Intent intent = new Intent(this, DisplayActivity.class);
    	String msg = ((EditText)(findViewById(R.id.edit_message))).getText().toString();
    	intent.putExtra("MESSAGE", msg);
    	startActivity(intent);*/
    }
}
