package com.example.basicimagedownloading;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ImageView ivPhoto = (ImageView)findViewById(R.id.ivPhoto);
		String url = "http://2.gravatar.com/avatar/858dfac47ab8176458c005414d3f0c36?s=256&d=&r=G";

        ImageLoader.load(url, ivPhoto);
    }
    
    
}
