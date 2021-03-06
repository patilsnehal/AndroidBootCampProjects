package com.codepath.apps.basictwitter;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.codepath.apps.basictwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class ComposeActivity extends Activity {
	private EditText etCompose;
	private TwitterClient client;
	private Tweet t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		client = TwitterApplication.getRestClient();
	}

	public void onComposeClicked(View v) {
		etCompose = (EditText) findViewById(R.id.etCompose);
		String composedTweet = etCompose.getText().toString();
		// Create Intent
		  client.postHomeTimeline(composedTweet, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				
				System.out.println("tweet submitted:" + json.toString());
				Intent i = new Intent();

				// create new Tweet and user object and add it to to array list adapter
				t = Tweet.fromJson(json);
				i.putExtra("tweet", t);
				
				// submit my result to parent activity
				setResult(RESULT_OK, i);
				finish();
			}

			@Override
			public void onFailure(Throwable e, String s) {
				System.out.println("tweet failed");
				Log.d("debug", e.toString());
				Log.d("debug", s.toString());
			}
		});

	}
}
