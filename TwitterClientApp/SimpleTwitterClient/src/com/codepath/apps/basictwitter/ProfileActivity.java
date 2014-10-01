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

		tvName = (TextView) findViewById(R.id.tvName);
		tvTagline = (TextView) findViewById(R.id.tvTagline);
		tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		tvFollowing = (TextView) findViewById(R.id.tvFollowing);
		ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);

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
						String userTagLine = json.getString("description");
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
						if (userTagLine != null)
							tvTagline.setText(userTagLine);

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
			client.getUserTimeline(new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(int arg0, JSONObject json) {
					String userProfileImageUrl = "";
					System.out.println("USERJSON: " + json.toString());

					String userName;
					try {
						userName = json.getString("name");
						String userScreenName = json.getString("screen_name");
						String userTagLine = json.getString("description");
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
						if (userTagLine != null)
							tvTagline.setText(userTagLine);

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
				public void onFailure(Throwable arg0, String arg1) {
					System.out.println("USERJSON: " +  arg1);
				}
			});
		}

	}

}
