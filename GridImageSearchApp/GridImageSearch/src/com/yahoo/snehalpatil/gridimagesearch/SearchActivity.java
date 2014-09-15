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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yahoo.snehalpatil.gridimagesearch.adapaters.EndlessScrollListener;
import com.yahoo.snehalpatil.gridimagesearch.adapaters.ImageResultsAdapter;

public class SearchActivity extends Activity {
	private EditText etQuery;
	private GridView gvResults;
	private ArrayList<ImageResult> imageResults;
	private ImageResultsAdapter aImageResults;
	private Settings settings;
	private int pageOffset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupViews();
        setUpSettings();
        pageOffset = 0;
        // Creates the data source
        imageResults = new ArrayList<ImageResult>();
        
        // Attaches data source to the adapter
        aImageResults = new ImageResultsAdapter(this, imageResults);
        
        // Link the adapter to the adapter view i.e grid view
        gvResults.setAdapter(aImageResults);
        
        // Attach the listener to the AdapterView onCreate
        gvResults.setOnScrollListener(new EndlessScrollListener() {
	    @Override
	    public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
	        customLoadMoreDataFromApi(page); 
                // or customLoadMoreDataFromApi(totalItemsCount); 
	    }
        });
    }
    
 // Append more data into the adapter
    public void customLoadMoreDataFromApi(int offset) {
      // This method probably sends out a network request and appends new data items to your adapter. 
      // Use the offset value and add it as a parameter to your API request to retrieve paginated data.
      // Deserialize API response and then construct new objects to append to the adapter
    	pageOffset = pageOffset + 8;
    	getMoreData();
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
    
    private void setUpSettings(){
    	settings = new Settings();
        settings.imageSize = "small";
        settings.colorFilter = "red";
        settings.imageType = "faces";
        settings.siteFilter = "yahoo.com";
    }
 
    // Fired whenever the button is pressed 
    public void onImageSearch(View v){
  	  // URL : https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=android&rez=8
    	getMoreData();
    }

	private void getMoreData() {

    	String query = etQuery.getText().toString();
    	// build URL
        String url = buildURL(query); 
        
        AsyncHttpClient client = new AsyncHttpClient();        
        client.get(url, new JsonHttpResponseHandler() {
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
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.image_display, menu);
        return true;
    }
        
    public void onSettingsClicked(MenuItem mi){
    	// construct intent
    	Intent i = new Intent(this, FilterActivity.class);
    	// Pass arguments
    	i.putExtra("settings", settings);
    	//Execute Intent StartACtivutyForResult
    	startActivityForResult(i, 5);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if(requestCode == 5) {
    		if(resultCode == RESULT_OK) {
    			Settings settings = (Settings) data.getSerializableExtra("settings");
    			this.settings = settings;
    	    	Toast.makeText(this, settings.siteFilter, Toast.LENGTH_SHORT).show();
    	    	getMoreData();
    		}
    	}
    }
    
    private String buildURL(String query){
    	// Build URL using settings parameter
        String searchURL = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&rsz=8&q=" + query + "&as_sitesearch=" + settings.siteFilter + "&imgcolor="
        + settings.colorFilter + "&imgsz=" + settings.imageSize + "&imgtype=" + settings.imageType + "&start=" + pageOffset;
        System.out.println("searchURL:" + searchURL);
        return searchURL;
    }
}
