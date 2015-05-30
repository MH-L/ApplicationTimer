package com.example.notificationtimer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class PickAppActivity extends Activity {

	private Spinner appPicker;
	private Button goButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pick_app);
		appPicker = (Spinner) findViewById(R.id.spinner1);
		goButton = (Button) findViewById(R.id.button1);
		goButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String choice = appPicker.getSelectedItem().toString();
				int duration = Toast.LENGTH_LONG;
				if (choice.equalsIgnoreCase("SMS")) {
					Toast toast = Toast.makeText(getApplicationContext(), "SMS Opened", duration);
					toast.show();
					Intent intent = new Intent(getApplicationContext(), TimePickerActivity.class);
					intent.putExtra("Application", "SMS");
					startActivity(intent);
				} else if (choice.equalsIgnoreCase("Music")) {
					Toast toast = Toast.makeText(getApplicationContext(), "MusicGonnaPlay", duration);
					toast.show();
					Intent intent = new Intent(getApplicationContext(), TimePickerActivity.class);
					intent.putExtra("Application", "Music");
					startActivity(intent);
				} else {
					Toast toast = Toast.makeText(getApplicationContext(), "Alarm Opened", duration);
					toast.show();
					Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
					startActivity(i);
					finish();
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0) {
			Bitmap imageGot = (Bitmap) data.getExtras().get("data");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pick_app, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
