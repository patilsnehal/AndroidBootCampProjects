package com.codepath.apps.basictwitter.models;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Tweet implements Serializable {
	private static final long serialVersionUID = 5758707162546258507L;
	private String uid;
	private String body;
	private String createAt;
	private User user;
 
	public static Tweet fromJson(JSONObject jsonObject) {
		Tweet tweet = new Tweet();
		// Extract value from JSON to populate tweet.
		try {
			tweet.body = jsonObject.getString("text");
			tweet.uid = jsonObject.getString("id");
			tweet.createAt = jsonObject.getString("created_at");
			tweet.user = User.fromJson(jsonObject.getJSONObject("user"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweet;
	}

	public String getUid() {
		return uid;
	}

	public String getBody() {
		return body;
	}

	public String getCreateAt() {
		return createAt;
	}

	public User getUser() {
		return user;
	}

	public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject tweetJson = null;
			try {
				tweetJson = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			Tweet tweet = Tweet.fromJson(tweetJson);
			if (tweet != null) {
				tweets.add(tweet);
			}
		}
		return tweets;
	}
	
	public static long lowestTweetIdfromJsonArray(JSONArray arr) {
		long uid = 0;
		try {
			JSONObject tweetJsonObj = arr.getJSONObject(arr.length() -1);
			uid = tweetJsonObj.getLong("id");
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return uid;
	}


	@Override
	public String toString() {
		return getBody() + getUser().getScreenName();
	}

	

}
