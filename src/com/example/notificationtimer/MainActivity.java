package com.example.notificationtimer;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	/**
	 * The title of the App, shown on top of the page.
	 */
	private EditText appTitle;
	/**
	 * The instruction sentence that asks user to input time.
	 */
	private EditText requestTime;
	/**
	 * The execute button which takes user to another interface.
	 */
	private Button executeButton;
	/**
	 * The time picker.
	 */
	private TimePicker timePicker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/*
		 * Just like the constructor of the activity
		 * (Illustrated by me).
		 */
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// My own code here.
		appTitle = (EditText) findViewById(R.id.textView1);
		requestTime = (EditText) findViewById(R.id.textView2);
		executeButton = (Button) findViewById(R.id.button1);
		timePicker = (TimePicker) findViewById(R.id.timePicker1);

		/* Annonymous Class */
		executeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Context ctxt = getApplicationContext();
				CharSequence text = "You too stupid!";
				int duration = Toast.LENGTH_LONG;
				final Toast toast = Toast.makeText(ctxt, text, duration);
				toast.show();
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
