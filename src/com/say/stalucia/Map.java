package com.say.stalucia;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Map extends Activity implements OnClickListener {
	
	EditText etSearch;
	Button btSearch;
	ImageButton iv1, iv2, iv3;
	Toast t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map1);
		
		initControls();
	}

	private void initControls() {
		// TODO Auto-generated method stub
		etSearch = (EditText) findViewById (R.id.etSearch);
		btSearch = (Button) findViewById (R.id.btSearch);
		
		iv1 = (ImageButton) findViewById (R.id.iv1);
		iv2 = (ImageButton) findViewById (R.id.iv2);
		iv3 = (ImageButton) findViewById (R.id.iv3);
		
		btSearch.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btSearch:
		String place = etSearch.getText().toString();
		
		if(place.equalsIgnoreCase("Department Store")){
			t = Toast.makeText(Map.this, "Department Store", Toast.LENGTH_SHORT);
			t.setGravity(Gravity.CENTER, 0, 0);
			t.show();
			iv1.setImageDrawable(getResources().getDrawable(
                    R.drawable.first_focused));
			
			// Handler
            new Handler().postDelayed(new Runnable() {

                public void run() {
                    // Revert back to original image
                    iv1.setImageDrawable(getResources().getDrawable(
                                                R.drawable.first));            
                }
            }, 3500L);    // 3500 milliseconds(3.5 seconds) delay
			
		} else if (place.equalsIgnoreCase("Pacifica Drive")){
			t = Toast.makeText(Map.this, "Pacifica Drive", Toast.LENGTH_SHORT);
			t.setGravity(Gravity.CENTER, 0, 0);
			t.show();
			iv2.setImageDrawable(getResources().getDrawable(
                    R.drawable.second_focused));
			
			// Handler
            new Handler().postDelayed(new Runnable() {

                public void run() {
                    // Revert back to original image
                    iv2.setImageDrawable(getResources().getDrawable(
                                                R.drawable.second));            
                }
            }, 3500L);    // 3500 milliseconds(3.5 seconds) delay
			
			
		} else if (place.equalsIgnoreCase("North Parking")){
			t = Toast.makeText(Map.this, "North Parking", Toast.LENGTH_SHORT);
			t.setGravity(Gravity.CENTER, 0, 0);
			t.show();
			iv3.setImageDrawable(getResources().getDrawable(
                    R.drawable.third_focused));

			// Handler
            new Handler().postDelayed(new Runnable() {

                public void run() {
                    // Revert back to original image
                    iv3.setImageDrawable(getResources().getDrawable(
                                                R.drawable.third));            
                }
            }, 3500L);    // 3500 milliseconds(3.5 seconds) delay
			
		}
		break;
		case R.id.iv1:
			t = Toast.makeText(this, "Department Store", Toast.LENGTH_SHORT);
			t.setGravity(Gravity.CENTER, 0, 0);
			t.show();
			break;
		case R.id.iv2:
			t = Toast.makeText(this, "Department Store", Toast.LENGTH_SHORT);
			t.setGravity(Gravity.CENTER, 0, 0);
			t.show();
			break;
		case R.id.iv3:
			t = Toast.makeText(this, "Department Store", Toast.LENGTH_SHORT);
			t.setGravity(Gravity.CENTER, 0, 0);
			t.show();
			break;
		}
		
	}

}