package com.yahoo.snehalpatil.gridimagesearch;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yahoo.snehalpatil.gridimagesearch.adapaters.ImageResultsAdapter;

public class SearchActivity extends Activity {
	private EditText etQuery;
	private GridView gvResults;
	private ArrayList<ImageResult> imageResults;
	private ImageResultsAdapter aImageResults;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupViews();
        
        // Creates the data source
        imageResults = new ArrayList<ImageResult>();
        
        // Attaches data source to the adapter
        aImageResults = new ImageResultsAdapter(this, imageResults);
        
        // Link the adapter to the adapter view i.e grid view
        gvResults.setAdapter(aImageResults);
    }
    
    private void setupViews() {
		// TODO Auto-generated method stub
    	etQuery = (EditText) findViewById(R.id.etQuery);
    	gvResults = (GridView) findViewById(R.id.gvResults);
    	gvResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// launch image display activity
				// Create an intend
				Intent i = new Intent(SearchActivity.this, ImageDisplayActivity.class);
				// get the image result to display
				ImageResult result = imageResults.get(position);
				// pass image result into the intent
				i.putExtra("result", result);
				// launch the new activity
				startActivity(i);
			}
		});
	}
 
    // Fired whenever the button is pressed 
    public void onImageSearch(View v){
    	String query = etQuery.getText().toString();
    	Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
    	  // URL : https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=android&rez=8
        AsyncHttpClient client = new AsyncHttpClient();
        String searchURL = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + query + "&rsz=8";
         
        client.get(searchURL, new JsonHttpResponseHandler(){
        	@Override
        	public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        		super.onSuccess(statusCode, headers, response);
        		Log.d("DEBUG", response.toString());
        		JSONArray imageResultJson = null;
        		try {
					imageResultJson = response.getJSONObject("responseData").getJSONArray("results");
					imageResults.clear(); // clear existing images from array (in case if its new search)
					
					// make changes to the adapter, it does modify underline data for you automatically.
					aImageResults.addAll(ImageResult.fromJsonArray(imageResultJson));
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
        		Log.i("INFO", imageResults.toString());
                System.out.println("imageResults:" + imageResults.toString());
        	}
        	
        	@Override
        	public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println("statusCode:" + statusCode + "\nerrorResponse:" + errorResponse);
        	};
        });
        
    }
}
