package com.say.stalucia;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends Activity {

	// Declare Variables
    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    static String TAG_TYPE = "movie_type";
    static String TAG_NAME = "movie_name";
    static String TAG_LENGTH = "movie_length";
    static final String TAG_SCHEDULES = "movie_schedules";
    static final String TAG_CINEMA = "movie_cinema_number";
    static String URL = "movie_image_url";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        // DownloadJSON execute
        new DownloadJSON().execute();
    }
	
	// DownloadJSON AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void> {
 
    @Override
    protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Sta. Lucia Cinema List");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
    }
        
    @Override
    protected Void doInBackground(Void... params) {
            // Create the array 
            arraylist = new ArrayList<HashMap<String, String>>();
            // Retrive JSON Objects from the given website URL in JSONfunctions.class
            jsonobject = JSONfunctions.getJSONfromURL("http://alyssayango.x10.mx/results.json");
 
            try {
                // Locate the array name
                jsonarray = jsonobject.getJSONArray("posts");
 
                for (int i = 0; i < jsonarray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject = jsonarray.getJSONObject(i);
                    //Log.i(MainActivity.class.getName(), jsonobject.getString("movie_name"));
                    // Retrive JSON Objects
                    map.put(TAG_NAME, jsonobject.getString("movie_name"));
                    map.put(TAG_CINEMA, jsonobject.getString("movie_cinema_number"));
                    map.put(TAG_SCHEDULES, jsonobject.getString("movie_schedules"));
                    map.put(URL, jsonobject.getString("movie_image_url"));
                    // Set the JSON Objects into the array
                    arraylist.add(map);
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
    }
 
    @Override
    protected void onPostExecute(Void args) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listview);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(MainActivity.this, arraylist);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }

}
