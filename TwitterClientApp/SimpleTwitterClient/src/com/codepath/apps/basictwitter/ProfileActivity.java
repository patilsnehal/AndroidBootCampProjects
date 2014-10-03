package com.codepath.apps.basictwitter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.basictwitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends FragmentActivity {
	TwitterClient client;
	String userScreenName;
	TextView tvName;
	TextView tvFollowers;
	ImageView ivProfileImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		userScreenName = "";
		userScreenName = (String) getIntent().getStringExtra("UserScreenName");
		loadProfileInfo();

	}
	 
	public void loadProfileInfo() {
		TwitterClient client = TwitterApplication.getRestClient();
		tvName = (TextView) findViewById(R.id.tvName);
		tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		ivProfileImage = (ImageView) findViewById(R.id.ivImageView);

		if (userScreenName.equalsIgnoreCase("") == true) {
			client.setUserNameToLookup("");

			client.getMyinfo(new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(int arg0, JSONObject json) {
					String userProfileImageUrl = "";
					System.out.println("USERJSON: " + json.toString());
					String userName;
					try {
						userName = json.getString("name");
						String userScreenName = json.getString("screen_name");
						String uProfileIgUrl = json
								.getString("profile_image_url_https");
						int iFollower = json.getInt("followers_count");
						if (userName != null)
							tvName.setText(userName);
						if (userScreenName != null)
							getActionBar().setTitle(userScreenName);
						else
							getActionBar().setTitle(userName);

						if (uProfileIgUrl != null)
							userProfileImageUrl = uProfileIgUrl;
						tvFollowers.setText(iFollower + " Followers");

						ImageLoader.getInstance().displayImage(uProfileIgUrl,
								ivProfileImage);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				@Override
				public void onFailure(Throwable arg0, String arg1) {
					System.out.println("USERJSON: " +  arg1);
				}
			});
		} else {
			client.setUserNameToLookup(userScreenName);

			client.getUserLookup(new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(JSONArray arg1) {
					String userProfileImageUrl = "";
					JSONObject json;
					try {
						json = arg1.getJSONObject(0);
					String userName;
						userName = json.getString("name");
						String userScreenName = json.getString("screen_name");
						String uProfileIgUrl = json
								.getString("profile_image_url_https");
						int iFollower = json.getInt("followers_count");
						if (userName != null)
							tvName.setText(userName);
						if (userScreenName != null)
							getActionBar().setTitle(userScreenName);
						else
							getActionBar().setTitle(userName);

						if (uProfileIgUrl != null)
							userProfileImageUrl = uProfileIgUrl;
						tvFollowers.setText(iFollower + " Followers");

						ImageLoader.getInstance().displayImage(uProfileIgUrl,
								ivProfileImage);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
		}

	}

}
