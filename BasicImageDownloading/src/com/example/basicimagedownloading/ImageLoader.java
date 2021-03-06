package com.example.basicimagedownloading;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ImageLoader {
	public static void load(String url, ImageView imageView) {
		// cue magic networking
		new ImageAsyncLoader(imageView).execute(url);
	}

	private static class ImageAsyncLoader extends
			AsyncTask<String, Void, Bitmap> {
		private ImageView imageView;

		public ImageAsyncLoader(ImageView imageView) {
			this.imageView = imageView;
		}

		@Override
		protected Bitmap doInBackground(String... addresses) {
			// Convert string to URL
			URL url = getUrlFromString(addresses[0]);
			// Get input stream
			InputStream in = getInputStream(url);
			// Decode bitmap
			Bitmap bitmap = decodeBitmap(in);
			// Return bitmap result
			return bitmap;
		}

		// Returns the URL object based on the address given
		private URL getUrlFromString(String address) {
			URL url;
			try {
				url = new URL(address);
			} catch (MalformedURLException e1) {
				url = null;
			}
			return url;
		}

		// Returns an input stream by connecting to the given URL
		private InputStream getInputStream(URL url) {
			InputStream in;
			// Open connection
			URLConnection conn;
			try {
				conn = url.openConnection();
				conn.connect();
				in = conn.getInputStream();
			} catch (IOException e) {
				in = null;
			}
			return in;
		}

		// Convert the input stream into a Bitmap object using BitmapFactory
		private Bitmap decodeBitmap(InputStream in) {
			Bitmap bitmap;
			try {
				// Turn response into Bitmap
				bitmap = BitmapFactory.decodeStream(in);
				// Close the input stream
				in.close();
			} catch (IOException e) {
				in = null;
				bitmap = null;
			}
			return bitmap;
		}

		// Fires after the task is completed, displaying the bitmap into the
		// ImageView
		@Override
		protected void onPostExecute(Bitmap result) {
			// Set bitmap image for the result
			imageView.setImageBitmap(result);
		}

	}
}
