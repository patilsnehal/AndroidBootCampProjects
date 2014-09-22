package com.codepath.apps.basictwitter;

import org.json.JSONArray;

import com.codepath.apps.basictwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ComposeActivity extends Activity {
	private EditText etCompose;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
 
		// Pull out the arguments from intent
//		settings = (Settings) getIntent().getSerializableExtra("settings");
	}
	
	public void onComposeClicked(View v) {
		etCompose = (EditText) findViewById(R.id.etCompose);
		
		Intent i = new Intent();
		// create results
		i.putExtra("composedTweet", etCompose.getText().toString());

		// submit my result to parent activity
		setResult(RESULT_OK,i);
		finish();
		}
}
  
