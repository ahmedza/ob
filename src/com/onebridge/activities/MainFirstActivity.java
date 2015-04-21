package com.onebridge.activities;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.onebridge.activities.account.AccountInfo;
import com.onebridge.manager.MenuLoader;

public class MainFirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("In On create");
        /*setContentView(R.layout.activity_main_first);*/
        setContentView(R.layout.main_activity_ob);
        setAdapter();
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
    	if(item.getItemId() == R.id.item_account_info){
    		Intent intent = new Intent(this,AccountInfo.class);
    		startActivity(intent);
    	}
    	return val; /*super.onMenuItemSelected(featureId, item);*/
    }
        
    public void loadCategoryScreen(View view){
    	Intent intent = new Intent(this, CategoryActivity.class);
    	/*String msg = ((EditText)(findViewById(R.id.edit_message))).getText().toString();
    	intent.putExtra("MESSAGE", msg);*/
    	startActivity(intent);
    }
    
    public void setAdapter(){
    	
    	ListView listVw = (ListView) findViewById(R.id.listViewOptions);
    	List<String> strings = new ArrayList<String>();
    	strings.add("Wish List");
    	strings.add("Categories");
    	strings.add("Messages");
    	listVw.setAdapter(new ArrayAdapter<String>(this, R.layout.main_screen_list_item, R.id.textView1, strings));
    	listVw.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String selected = (String)parent.getItemAtPosition(position);
				if(selected.equals("Categories")){
					loadCategoryScreen(view);
				}else if(selected.equals("Wish List")){
					//loadCategoryScreen(view);
				}else if (selected.equals("Messages")){
					//loadCategoryScreen(view);
				}
			}
		});
    	
    }
}
