package com.yahoo.snehalpatil.gridimagesearch;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class ImageResult implements Serializable{
	private static final long serialVersionUID = -7642656320812422532L;
	public String fullURL;
	public String thumbURL;
	public String title;

	// new imageResult (raw item json)
	public ImageResult(JSONObject json){
		try {
			this.fullURL = json.getString("url");
			this.thumbURL = json.getString("tbUrl");
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
