package com.say.stalucia;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
 
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
 
    public ListViewAdapter(Context context,
            ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
 
    }
 
    @Override
    public int getCount() {
        return data.size();
    }
 
    @Override
    public Object getItem(int position) {
        return null;
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView name;
        TextView cinema;
        TextView schedule;
        ImageView url;
 
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        // Get the position from the results
        HashMap<String, String> resultp = new HashMap<String, String>();
        resultp = data.get(position);
 
        // Locate the TextViews in list_item.xml
        name = (TextView) itemView.findViewById(R.id.name); 
        cinema = (TextView) itemView.findViewById(R.id.cinema); 
        schedule = (TextView) itemView.findViewById(R.id.schedules); 
        // Locate the ImageView in list_item.xml
        url = (ImageView) itemView.findViewById(R.id.image); 
 
        // Capture position and set results to the TextViews
        name.setText(resultp.get(MainActivity.TAG_NAME));
        cinema.setText(resultp.get(MainActivity.TAG_CINEMA));
        schedule.setText(resultp.get(MainActivity.TAG_SCHEDULES));
        // Capture position and set results to the ImageView
        // Passes movie images URL into ImageLoader.class to download and cache
        // images
        imageLoader.DisplayImage(resultp.get(MainActivity.URL), url);
        // Capture button clicks on ListView items
        itemView.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // Get the position from the results
                HashMap<String, String> resultp = new HashMap<String, String>();
                resultp = data.get(position);
                // Send single item click data to SingleMenuItemView Class
                Intent intent = new Intent(context, SingleMenuItemActivity.class);
                // Pass all data rank
                intent.putExtra("movie_name", resultp.get(MainActivity.TAG_NAME));
                // Pass all data country
                intent.putExtra("movie_cinema_number", resultp.get(MainActivity.TAG_CINEMA));
                // Pass all data population
                intent.putExtra("movie_schedules",
                        resultp.get(MainActivity.TAG_SCHEDULES));
                // Pass all data url
                intent.putExtra("movie_image_url", resultp.get(MainActivity.URL));
                // Start SingleItemView Class
                context.startActivity(intent);
 
            }
        });
 
        return itemView;
    }
}
