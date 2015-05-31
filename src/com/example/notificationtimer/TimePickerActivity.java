package com.example.notificationtimer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class TimePickerActivity extends Activity {
	/**
	 * The title of the App, shown on top of the page.
	 */
	private TextView appTitle;
	/**
	 * The instruction sentence that asks user to input time.
	 */
	private TextView requestTime;
	/**
	 * The execute button which takes user to another interface.
	 */
	private Button executeButton;
	/**
	 * The time picker.
	 */
	private TimePicker timePicker;
	private static final int HOURS_IN_A_DAY = 24;
	private static final int MINS_IN_AN_HOUR = 60;
	private ArrayList<CharSequence> strings = new ArrayList<CharSequence>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/*
		 * Just like the constructor of the activity
		 * (Illustrated by me).
		 */
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// My own code here.
		appTitle = (TextView) findViewById(R.id.textView1);
		requestTime = (TextView) findViewById(R.id.textView2);
		executeButton = (Button) findViewById(R.id.button1);
		timePicker = (TimePicker) findViewById(R.id.timePicker1);

		strings.add("Directing you to the application setting page...");
		strings.add("Done... now directing you to application page...");

		/* Annonymous Class */
		executeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int setHour = timePicker.getCurrentHour();
				int setMinute = timePicker.getCurrentMinute();
				Calendar c = Calendar.getInstance();
				int curHour = c.get(Calendar.HOUR_OF_DAY);
				int curMinute = c.get(Calendar.MINUTE);

				// calculate the time it takes;
				int hoursToGo;
				int minsToGo;
				boolean offset = curMinute > setMinute;
				if (setHour > curHour) {
					if (offset)
						hoursToGo = setHour - curHour - 1;
					else
						hoursToGo = setHour - curHour;
				} else if (setHour == curHour) {
					if (!offset)
						hoursToGo = 0;
					else
						hoursToGo = setHour + HOURS_IN_A_DAY - 1 - curHour;
				} else {
					if (offset)
						hoursToGo = setHour + HOURS_IN_A_DAY - 1 - curHour;
					else
						hoursToGo = setHour + HOURS_IN_A_DAY - curHour;
				}
				if (offset) {
					minsToGo = setMinute + MINS_IN_AN_HOUR - curMinute;
				} else {
					minsToGo = setMinute - curMinute;
				}

				// Tell user how much time left before the action starts
				Context ctxt = getApplicationContext();
				CharSequence text = "The activity is set " + hoursToGo + "hours, " + minsToGo + "mins from now";
				int duration = Toast.LENGTH_LONG;
				final Toast toast = Toast.makeText(ctxt, text, duration);
				toast.show();

				Intent classIntent = getIntent();
				String value = "";
				if (classIntent != null && classIntent.getExtras() != null) {
				    value = classIntent.getExtras().getString("Application");
				}

				Intent intent = new Intent();
				if (value.equals("SMS")) {
					// start sms service now
					Intent countDownService = new Intent(getApplicationContext(), SendSMSService.class);
					countDownService.putExtra("setHour", setHour);
					countDownService.putExtra("setMinute", setMinute);
					getApplicationContext().startService(countDownService);
					intent.setAction("com.example.notificationtimer.CountDownActivity");
				} else {
					intent.setAction("com.example.notificationtimer.CountDownActivity");
				}

				intent.putExtra("setHour", setHour);
				intent.putExtra("setMinute", setMinute);
				intent.putExtra("hoursToGo", hoursToGo);
				intent.putExtra("minutesToGo", minsToGo);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
