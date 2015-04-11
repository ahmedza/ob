package com.onebridge.activities;


import com.onebridge.manager.MenuLoader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

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
    
/*    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    	// TODO Auto-generated method stub
    	new MenuLoader().fetchMenuItems(menu, R.id.item_Categories, R.id.group_cats);
    	return super.onPrepareOptionsMenu(menu);
    }*/
    
    @SuppressLint("NewApi") 
    public void openFriends(View view){    	
    	System.out.println("In Open Friends");
    	/*Intent intent = new Intent(this, DisplayActivity.class);
    	String msg = ((EditText)(findViewById(R.id.edit_message))).getText().toString();
    	intent.putExtra("MESSAGE", msg);
    	startActivity(intent);*/
    }
}
