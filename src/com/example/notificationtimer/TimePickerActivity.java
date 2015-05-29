package com.example.notificationtimer;

import java.util.ArrayList;
import java.util.Random;

import android.support.v7.app.ActionBarActivity;
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

public class TimePickerActivity extends ActionBarActivity {
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

	private ArrayList<CharSequence> strings = new ArrayList<CharSequence>();
	private static final Random rand = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/*
		 * Just like the constructor of the activity
		 * (Illustrated by me).
		 */
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		strings.add("I am proud of you.");
//		strings.add("Honey, you are soooo pretty.");
//		strings.add("You make me happy every day!");
//		strings.add("I miss you soooo much!");
//		strings.add("I hope to see you now!");
//		strings.add("Good good study, day day up.");
//		strings.add("I have been dreaming of you a lot!");
//		strings.add("See, you are smart!");
		strings.add("Tu es merde.");
		strings.add("S**t! Such a stupid person!");
		strings.add("What makes you so shitty?");
		strings.add("Do you eat poo every day??");
		strings.add("You should die on a bean curd.");
		strings.add("Go to hell!");
		strings.add("Go skydiving! And you will be smashed!");
		strings.add("See, you are smart! Oh no, I am joking.");
		strings.add("Please do not ruin the poo, it is more lovely than you.");
		strings.add("Failed again, huh? You deserve it!");
		// My own code here.
		appTitle = (TextView) findViewById(R.id.textView1);
		requestTime = (TextView) findViewById(R.id.textView2);
		executeButton = (Button) findViewById(R.id.button1);
		timePicker = (TimePicker) findViewById(R.id.timePicker1);

		/* Annonymous Class */
		executeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Context ctxt = getApplicationContext();
				CharSequence text = strings.get(rand.nextInt(10));
				int duration = Toast.LENGTH_LONG;
				final Toast toast = Toast.makeText(ctxt, text, duration);
				toast.show();
				Intent intent = new Intent();
				intent.setAction("com.example.notificationtimer.PickAppActivity");
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
