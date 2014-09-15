package com.yahoo.snehalpatil.gridimagesearch;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class SearchActivity extends Activity {
	private EditText etQuery;
	private GridView gvResults;
//	private ArrayList<ImageResult> imageResults;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupViews();
        
      
    }
    
    private void setupViews() {
		// TODO Auto-generated method stub
    	etQuery = (EditText) findViewById(R.id.etQuery);
    	gvResults = (GridView) findViewById(R.id.gvResults);

	}
 
    // Fired whenever the button is pressed 
    public void onImageSearch(View v){
    	String query = etQuery.getText().toString();
    	Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
    	  // URL : https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=android&rez=8
        AsyncHttpClient client = new AsyncHttpClient();
//        String searchURL = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + query + "&rsz=8";
        String searchURL = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=android&rez=8";
        System.out.println("searchURL:" + searchURL);
        
        client.get(searchURL, new JsonHttpResponseHandler(){
        	@Override
        	public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        		super.onSuccess(statusCode, headers, response);
                System.out.println("response:" + response);

        		Log.d("DEBUG", response.toString());
        	}
        	@Override
        	public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                System.out.println("statusCode:" + statusCode);
                System.out.println("errorResponse:" + errorResponse);
        	};
        });
        
    }
}
