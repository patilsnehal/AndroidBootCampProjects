package com.codepath.apps.basictwitter.models;

import java.io.Serializable;

import org.json.JSONObject;

public class User implements Serializable {

	private static final long serialVersionUID = 3107933459929701956L;
	private String name;
	private String uid;
	private String screenName;
	private String profileImageUrl;
	public static User fromJson(JSONObject jsonObject) {
		User user = new User();
		try {
			user.name = jsonObject.getString("name");
			user.uid = jsonObject.getString("id");
			user.screenName = jsonObject.getString("screen_name");
			user.profileImageUrl = jsonObject.getString("profile_image_url");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public String getName() {
		return name;
	}
	public String getUid() {
		return uid;
	}
	public String getScreenName() {
		return screenName;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}

}
