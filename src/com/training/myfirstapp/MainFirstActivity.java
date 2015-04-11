package com.training.myfirstapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainFirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("In On create");
//        setContentView(R.layout.activity_main_first);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_first, menu);
        System.out.println("In Create Menu Options");
        return true;
    }
    
    
    @SuppressLint("NewApi") 
    public void openFriends(View view){    	
    	System.out.println("In Open Friends");
    	Intent intent = new Intent(this, DisplayActivity.class);
/*    	String msg = ((EditText)(findViewById(R.id.edit_message))).getText().toString();
    	intent.putExtra("MESSAGE", msg);*/
    	startActivity(intent);
    }
}
