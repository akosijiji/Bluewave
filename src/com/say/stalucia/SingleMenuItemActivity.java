package com.say.stalucia;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.say.stalucia.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleMenuItemActivity  extends Activity {
	
	// Declare Variables 
    String name;
    String cinema;
    String schedule;
    String url;
    ProgressDialog mProgressDialog;
    Bitmap bmImg = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        // Execute loadSingleView AsyncTask
        new loadSingleView().execute();
        
    }
	
	public class loadSingleView extends AsyncTask<String, String, String> {
		 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(SingleMenuItemActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Sta. Lucia Cinema List");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
 
        }
 
        @Override
        protected String doInBackground(String... args) {
            try {
                // Retrieve data from ListViewAdapter on click event
                Intent i = getIntent();
                // Get the result of rank
                name = i.getStringExtra("movie_name");
                // Get the result of country
                cinema = i.getStringExtra("movie_cinema_number");
                // Get the result of population
                schedule = i.getStringExtra("movie_schedules");
                // Get the result of flag
                url = i.getStringExtra("movie_image_url");

                // Download the Image from the result URL given by url
                URL newUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) newUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream();
                bmImg = BitmapFactory.decodeStream(is);
            } catch (IOException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
 
            return null;
        }
 
        @Override
        protected void onPostExecute(String args) {
            // Locate the TextViews in singleitemview.xml
            TextView txtrank = (TextView) findViewById(R.id.name_label);
            TextView txtcountry = (TextView) findViewById(R.id.cinema_label);
            TextView txtpopulation = (TextView) findViewById(R.id.schedules_label);
            // Locate the ImageView in singleitemview.xml
            ImageView imgflag = (ImageView) findViewById(R.id.image_);
 
            // Set results to the TextViews
            txtrank.setText(name);
            txtcountry.setText(cinema);
            txtpopulation.setText(schedule);
 
            // Set results to the ImageView
            imgflag.setImageBitmap(bmImg);
 
            // Close the progressdialog
            mProgressDialog.dismiss();
 
        }
    }
}
