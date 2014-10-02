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
	static String userScreenName;
	TextView tvName;
	TextView tvTagline;
	TextView tvFollowers;
	TextView tvFollowing;
	ImageView ivProfileImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		userScreenName = "";
		userScreenName = (String) getIntent().getStringExtra("UserScreenName");
		client = TwitterApplication.getRestClient();
		loadProfileInfo();

	}
	
	public static String getUserScreenName (){
		return userScreenName;
	}

	public void loadProfileInfo() {
		TwitterClient client = TwitterApplication.getRestClient();
		client.setUserNameToLookup(userScreenName);
		tvName = (TextView) findViewById(R.id.tvName);
		tvTagline = (TextView) findViewById(R.id.tvTagline);
		tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		tvFollowing = (TextView) findViewById(R.id.tvFollowing);
		ivProfileImage = (ImageView) findViewById(R.id.ivImageView);

		if (userScreenName.equalsIgnoreCase("") == true) {
			client.getMyinfo(new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(int arg0, JSONObject json) {
					String userProfileImageUrl = "";
					System.out.println("USERJSON: " + json.toString());
					String userName;
					try {
						userName = json.getString("name");
						String userScreenName = json.getString("screen_name");
//						String userTagLine = json.getString("description");
						String uProfileIgUrl = json
								.getString("profile_image_url_https");
						int iFollower = json.getInt("followers_count");
						int iFollowing = json.getInt("friends_count");
						if (userName != null)
							tvName.setText(userName);
						if (userScreenName != null)
							getActionBar().setTitle(userScreenName);
						else
							getActionBar().setTitle(userName);
//						if (userTagLine != null)
//							tvTagline.setText(userTagLine);

						if (uProfileIgUrl != null)
							userProfileImageUrl = uProfileIgUrl;
						tvFollowers.setText(iFollower + " Followers");
						tvFollowing.setText(iFollowing + " Following");

						ImageLoader.getInstance().displayImage(uProfileIgUrl,
								ivProfileImage);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				@Override
				public void onFailure(Throwable arg0, String arg1) {
					System.out.println("USERJSON: " +  arg1);
				}
			});
		} else {
			client.getUserLookup(new JsonHttpResponseHandler() {
				@Override
//				public void onSuccess(int arg0, JSONArray arg1) {
				public void onSuccess(JSONArray arg1) {
//					// TODO Auto-generated method stub
//					super.onSuccess(arg0);
//				}
//				@Override
//				public void onSuccess(int arg0, JSONObject json) {
					String userProfileImageUrl = "";
					JSONObject json;
					try {
						json = arg1.getJSONObject(0);
//					} catch (JSONException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					System.out.println("USERJSON: " + json.toString());
//
					String userName;
//					try {
						userName = json.getString("name");
						String userScreenName = json.getString("screen_name");
//						String userTagLine = json.getString("description");
						String uProfileIgUrl = json
								.getString("profile_image_url_https");
						int iFollower = json.getInt("followers_count");
						int iFollowing = json.getInt("friends_count");
						if (userName != null)
							tvName.setText(userName);
						if (userScreenName != null)
							getActionBar().setTitle(userScreenName);
						else
							getActionBar().setTitle(userName);
//						if (userTagLine != null)
//							tvTagline.setText(userTagLine);

						if (uProfileIgUrl != null)
							userProfileImageUrl = uProfileIgUrl;
						tvFollowers.setText(iFollower + " Followers");
						tvFollowing.setText(iFollowing + " Following");

						ImageLoader.getInstance().displayImage(uProfileIgUrl,
								ivProfileImage);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				@Override
				public void onSuccess(int arg0, JSONObject arg1) {
					// TODO Auto-generated method stub
					super.onSuccess(arg0, arg1);
				}
				@Override
				public void onSuccess(int arg0, String arg1) {
					// TODO Auto-generated method stub
					super.onSuccess(arg0, arg1);
				}
				@Override
				public void onSuccess(String arg0) {
					// TODO Auto-generated method stub
					super.onSuccess(arg0);
				}
				@Override
				public void onFailure(Throwable arg0, JSONArray arg1) {
					// TODO Auto-generated method stub
					super.onFailure(arg0, arg1);
				}
				@Override
				public void onFailure(Throwable arg0, JSONObject arg1) {
					// TODO Auto-generated method stub
					super.onFailure(arg0, arg1);
				}
				@Override
				public void onFailure(Throwable arg1) {
					// TODO Auto-generated method stub
					System.out.println("USERJSON: " +  arg1);
				}
				@Override
				public void onFinish() {
					// TODO Auto-generated method stub
					super.onFinish();
				}
				public void onFailure(Throwable arg0, String arg1) {
					System.out.println("USERJSON: " +  arg1);
				}
			});
		}

	}

}
