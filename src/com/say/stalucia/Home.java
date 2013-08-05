package com.say.stalucia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class Home extends Activity {
	
	GridView gridview;
	
	Integer imageIDs[] = {
			R.drawable.logo,
			R.drawable.ic_launcher
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home);
		
		initControls();
		
	}

	private void initControls() {
		// TODO Auto-generated method stub
		gridview = (GridView) findViewById (R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this));
		
		gridview.setOnItemClickListener(new OnItemClickListener()
	       {
	           public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	           {
	       			switch(position){
	       				case 0:
	       					Intent view = new Intent(Home.this, MainActivity.class);
	       					startActivity(view);
	       					break;
	       				case 1:
	       					Intent add = new Intent(Home.this, Map.class);
	       					startActivity(add);
	       					break;
	       			} 
	           }
	       });
		
	}
   
	
	public class ImageAdapter extends BaseAdapter
	{
	    private Context context;
	    
	    public ImageAdapter(Context c)
	    {
	        context = c;
	    }
	    
	    
	   // Returns the number of images
	    public int getCount(){
	     return imageIDs.length;
	    }
	    
	    // Returns the ID of an item
	    public Object getItem(int position) {
	        return position;
	    }
	    
	    // Returns the ID of an item
	    public long getItemId(int position){
	        return position;
	    }
	    
	    // Returns an ImageView View
	    public View getView(int position, View convertView, ViewGroup parent){
	        ImageView imageView;
	        if(convertView == null){
	            imageView = new ImageView(context);
	            imageView.setLayoutParams(new GridView.LayoutParams(100,100));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(5, 5, 5, 5);
	        } 
	        else{
	            imageView = (ImageView) convertView;
	        }
	        
	        imageView.setImageResource(imageIDs[position]);
	        return imageView;
	    }
	}
}