package com.codepath.apps.basictwitter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.net.ParseException;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.basictwitter.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {
	public TweetArrayAdapter(Context context, List<Tweet> tweets) {
		super(context, 0, tweets);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		// Get the data item for this position
		Tweet tweet = getItem(position);
		View v;
		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			v = inflator.inflate(R.layout.tweet_item, parent, false);
		} else {
			v = convertView;
		}

		// Lookup view for data population
		ImageView ivProfileImage = (ImageView) v
				.findViewById(R.id.ivProfileImage);
		TextView tvUserName = (TextView) v.findViewById(R.id.tvUserName);
		TextView tvBody = (TextView) v.findViewById(R.id.tvBody);
		// Populate the data into the template view using the data object
		ivProfileImage.setImageResource(android.R.color.transparent);
		TextView tvDate = (TextView) v.findViewById(R.id.tvDate);

		ImageLoader imageLoader = ImageLoader.getInstance();
		if (tweet.getUser().getProfileImageUrl() != null) {
			imageLoader.displayImage(tweet.getUser().getProfileImageUrl(),
					ivProfileImage);

		}
		tvUserName.setText(tweet.getUser().getScreenName());
		tvBody.setText(tweet.getBody());
		String date = getRelativeTimeAgo(tweet.getCreateAt());
		if (date != null) {
			tvDate.setText(date);
		}
		// Return the completed view to render on screen
		return v;
	}

	// getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
	public String getRelativeTimeAgo(String rawJsonDate) {
		String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
		SimpleDateFormat sf = new SimpleDateFormat(twitterFormat,
				Locale.ENGLISH);
		sf.setLenient(true);

		String relativeDate = "";
		try {
			long dateMillis = 0;
			try {
				dateMillis = sf.parse(rawJsonDate).getTime();
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
					System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS)
					.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return relativeDate;
	}
}
