package com.yahoo.snehalpatil.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class ImageResult {
	public String fullURL;
	public String thumbURL;
	public String title;

	// new imageResult (raw item json)
	public ImageResult(JSONObject json){
		try {
			this.fullURL = json.getString("url");
			this.thumbURL = json.getString("tburl");
			this.title = json.getString("title");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Take an array of json result and return arraylist of imageResults
	//ImageResult.fromJSONArray([....])
	public static ArrayList<ImageResult> fromJsonArray(JSONArray array){
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		for (int i = 0; i < array.length(); i++) {
			try {
				results.add(new ImageResult(array.getJSONObject(i)));
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return results;
	}
}
