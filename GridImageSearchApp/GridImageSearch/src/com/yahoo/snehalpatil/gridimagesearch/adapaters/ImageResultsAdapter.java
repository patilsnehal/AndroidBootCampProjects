package com.yahoo.snehalpatil.gridimagesearch.adapaters;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yahoo.snehalpatil.gridimagesearch.ImageResult;
import com.yahoo.snehalpatil.gridimagesearch.R;

public class ImageResultsAdapter extends ArrayAdapter<ImageResult> {

	public ImageResultsAdapter(Context context, List<ImageResult> images) {
		super(context, R.layout.item_image_result, images);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResult  imageInfo = getItem(position);
		if (convertView == null){
	          convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, 
	        		  parent, false);
		}
	    // Lookup view for data population
		ImageView ivImage = (ImageView)convertView.findViewById(R.id.ivImage);
	    TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

	    // clear out image from last time
	    ivImage.setImageResource(0);
	    tvTitle.setText(Html.fromHtml(imageInfo.title));
	    
	    //remotely download image data in the background (with Picaso)
	    Picasso.with(getContext()).load(imageInfo.thumbURL).into(ivImage);
	    
	    // return the completed view to be displayed
	    return convertView;
	}

}
