package com.example.fragmentplay;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class HomeActivity extends FragmentActivity {
	OneFragment fragmentOne;
	TwoFragment fragmentTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    
    public void onFragmentOne(View v){
    	
    	if (fragmentOne == null){
    		fragmentOne = new OneFragment();
    	}
//    	How fragment works
//    	0.Fragment manager
    	FragmentManager manager = getSupportFragmentManager();
//    	1.Create a fragment transcation 
    	FragmentTransaction ft = manager.beginTransaction();
//    	2.Adjust the frame layout
    	ft.replace(R.id.flContainer, fragmentOne);
//    	3.Execute the fragment transaction
    	ft.commit();
    }
    public void onFragmentTwo(View v){
    	
    	if (fragmentTwo == null){
    		fragmentTwo = new TwoFragment();
    	}
//    	How fragment works
    	
//    	0.Fragment manager
    	FragmentManager manager = getSupportFragmentManager();
//    	1.Create a fragment transcation 
    	FragmentTransaction ft = manager.beginTransaction();
//    	2.Adjust the frame layout
    	ft.replace(R.id.flContainer, fragmentTwo);
//    	3.Execute the fragment transaction
    	ft.commit();
   	
    }
}
