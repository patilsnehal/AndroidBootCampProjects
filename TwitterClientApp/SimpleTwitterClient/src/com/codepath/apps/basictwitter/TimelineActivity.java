package com.codepath.apps.basictwitter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.basictwitter.Listeners.FragmentTabListener;
import com.codepath.apps.basictwitter.fragments.HomeTimelineFragment;
import com.codepath.apps.basictwitter.fragments.MentionsTimelineFragments;
import com.codepath.apps.basictwitter.models.Tweet;
 
public class TimelineActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		setupTabs();
	}

	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab tab1 = actionBar
			.newTab()
			.setText("Home")
			.setIcon(R.drawable.ic_home)
			.setTag("HomeTimelineFragment")
			.setTabListener(
				new FragmentTabListener<HomeTimelineFragment>(R.id.flContainer, this, "home",
						HomeTimelineFragment.class));

		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar
			.newTab()
			.setText("Mentions")
			.setIcon(R.drawable.ic_mentions)
			.setTag("MentionsTimelineFragment")
			.setTabListener(
			    new FragmentTabListener<MentionsTimelineFragments>(R.id.flContainer, this, "mentions",
			    		MentionsTimelineFragments.class));

		actionBar.addTab(tab2);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		getMenuInflater().inflate(R.menu.tweets, menu);

		return true;
	}
	public void onProfileClicked(MenuItem mi) {
		// construct intent
    	Intent i = new Intent(this, ProfileActivity.class);
    	// Pass arguments
    	//i.putExtra("settings", settings);
    	//Execute Intent StartACtivutyForResult
    	startActivityForResult(i, 5);
	}
	
	
	public void onComposeClicked(MenuItem mi) {
    	Intent i = new Intent(this, ComposeActivity.class);
    	startActivityForResult(i, 5);
	}
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if(requestCode == 5) {
    		if(resultCode == RESULT_OK) {
    			// insert Tweet object to the Timeline
    			Tweet tweet = (Tweet) data.getSerializableExtra("tweet");
//    			aTweets.insert(tweet, 0);
    		}
    	}
    }
    

}
