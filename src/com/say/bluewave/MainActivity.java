package com.say.bluewave;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	// url to make request
    private static String url = "http://alyssayango.x10.mx/";
    
    private static final String TAG_TYPE = "movie_type";
    private static final String TAG_NAME = "movie_name";
    private static final String TAG_LENGTH = "movie_length";
    private static final String TAG_SCHEDULES = "movie_schedules";
    private static final String TAG_CINEMA = "movie_cinema_number";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        
        String readMovieSchedules = readMovieSchedules();
        
        // Hashmap for ListView
        ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
        
        try {
          JSONArray jsonArray = new JSONArray(readMovieSchedules);
          Log.i(MainActivity.class.getName(),
              "Number of entries " + jsonArray.length());
          for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Log.i(MainActivity.class.getName(), jsonObject.getString("movie_name"));
            
            // Storing each json item in variable
            String name = jsonObject.getString(TAG_NAME);
            String type = jsonObject.getString(TAG_TYPE);
            String length = jsonObject.getString(TAG_LENGTH);
            String cinema = jsonObject.getString(TAG_CINEMA);
            String schedules = jsonObject.getString(TAG_SCHEDULES);
             
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
             
            // adding each child node to HashMap key => value
            map.put(TAG_NAME, name);
            map.put(TAG_TYPE, type);
            map.put(TAG_LENGTH, length);
            map.put(TAG_CINEMA, cinema);
            map.put(TAG_SCHEDULES, schedules);

            // adding HashList to ArrayList
            contactList.add(map);
            
            
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(this, contactList,
                    R.layout.list_item,
                    new String[] { TAG_NAME, TAG_CINEMA, TAG_SCHEDULES }, new int[] {
                            R.id.name, R.id.cinema, R.id.schedules });
            
            setListAdapter(adapter);
            // selecting single ListView item
            ListView lv = getListView();
            
            // Launching new screen on Selecting Single ListItem
            lv.setOnItemClickListener(new OnItemClickListener() {
     
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                        int position, long id) {
                    // getting values from selected ListItem
                    String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
                    String cost = ((TextView) view.findViewById(R.id.cinema)).getText().toString();
                    String description = ((TextView) view.findViewById(R.id.schedules)).getText().toString();
                     
                    // Starting new intent
                    Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
                    in.putExtra(TAG_NAME, name);
                    in.putExtra(TAG_CINEMA, cost);
                    in.putExtra(TAG_SCHEDULES, description);
                    startActivity(in);
                }

				

            });
            
            
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
 
    }
 
	
	public String readMovieSchedules() {
	    StringBuilder builder = new StringBuilder();
	    HttpClient client = new DefaultHttpClient();
	    HttpGet httpGet = new HttpGet(url);
	    try {
	      HttpResponse response = client.execute(httpGet);
	      StatusLine statusLine = response.getStatusLine();
	      int statusCode = statusLine.getStatusCode();
	      if (statusCode == 200) {
	        HttpEntity entity = response.getEntity();
	        InputStream content = entity.getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
	        String line;
	        while ((line = reader.readLine()) != null) {
	          builder.append(line);
	        }
	      } else {
	        Log.e(MainActivity.class.toString(), "Failed to download file");
	      }
	    } catch (ClientProtocolException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return builder.toString();
	  }

	
	
}
