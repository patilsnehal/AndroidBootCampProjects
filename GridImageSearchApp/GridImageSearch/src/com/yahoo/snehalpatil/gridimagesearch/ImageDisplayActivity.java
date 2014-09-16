package com.yahoo.snehalpatil.gridimagesearch;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ImageDisplayActivity extends Activity {
	ImageResult result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_display);
		// remove action bar on the image display activity
		getActionBar().hide();
		// pull out the url from the content 
		result = (ImageResult) getIntent().getSerializableExtra("result");
		// find the image view
		ImageView ivImageResult = (ImageView) findViewById(R.id.ivImageResult);
		// load the image url into the imageview using picasso
		Picasso.with(this).load(result.fullURL).into(ivImageResult);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onSendEmailClicked(View v) {
		System.out.println("email button clicked");
	      Log.i("Send email", "");

	      String[] TO = {"amrood.admin@gmail.com"};
	      String[] CC = {"mcmohd@gmail.com"};
	      Intent emailIntent = new Intent(Intent.ACTION_SEND);
	      emailIntent.setData(Uri.parse("mailto:"));
	      emailIntent.setType("text/plain");

	      emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
	      emailIntent.putExtra(Intent.EXTRA_CC, CC);
	      emailIntent.putExtra(Intent.EXTRA_SUBJECT,result.title);
	      emailIntent.putExtra(Intent.EXTRA_TEXT, "This is really great image. Please have a look. Image");
	      emailIntent .putExtra(Intent.EXTRA_STREAM, Uri.parse(result.fullURL));

//	      emailIntent.setType("image/jpeg");
//	      File downloadedPic =  new File(
//	          Environment.getExternalStoragePublicDirectory(
//	          Environment.DIRECTORY_DOWNLOADS), 
//	          result.fullURL);
//	      

	      
	      try {
	         startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	         finish();
	         Log.i("Finished sending email...", "");
	      } catch (android.content.ActivityNotFoundException ex) {
	         Toast.makeText(this, 
	         "There is no email client installed.", Toast.LENGTH_SHORT).show();
	      }
	   }
}
