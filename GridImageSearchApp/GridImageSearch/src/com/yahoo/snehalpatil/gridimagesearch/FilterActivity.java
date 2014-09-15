package com.yahoo.snehalpatil.gridimagesearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class FilterActivity extends Activity {
	Settings settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter);
		// Pull out the arguments from intent
		settings = (Settings) getIntent().getSerializableExtra("settings");
	}
	
	public void onSave(View v){
		// serialize from data
		EditText etIameSize = (EditText)findViewById(R.id.etImageSize);
		EditText etColorFilter = (EditText)findViewById(R.id.etColorFilter);
		EditText etImageType = (EditText)findViewById(R.id.etImageType);
		EditText etSiteFilter = (EditText)findViewById(R.id.etSiteFilter);
		
		// create results
		Intent i = new Intent();
		settings.imageSize = etIameSize.getText().toString();
		settings.colorFilter = etColorFilter.getText().toString();
		settings.imageType = etImageType.getText().toString();
		settings.siteFilter = etSiteFilter.getText().toString();
		i.putExtra("settings", settings);

		// submit my result to parent activity
		setResult(RESULT_OK,i);
		finish();
	}
}
