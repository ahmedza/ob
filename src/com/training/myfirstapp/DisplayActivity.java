package com.training.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class DisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("In On Create Display");
		setContentView(R.layout.display_activity_main);
		TextView tv = (TextView) findViewById(R.id.disp_tv);
		tv.setText(getIntent().getStringExtra("MESSAGE"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		System.out.println("In Options Display");
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_first, menu);
		return true;
	}

}
