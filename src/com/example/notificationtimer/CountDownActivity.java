package com.example.notificationtimer;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class CountDownActivity extends Activity {

	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count_down);
		tv = (TextView) findViewById(R.id.textView3);

		Intent classIntent = getIntent();
		int hour = 0;
		int minute = 0;
		if (classIntent != null && classIntent.getExtras() != null) {
			hour = classIntent.getExtras().getInt("hoursToGo");
			minute = classIntent.getExtras().getInt("minutesToGo");
		}

		String initialDispStr = String.format("%02d:%02d:00", hour, minute);
		tv.setTextSize(40);
		tv.setText(initialDispStr);
		long millisToFin = TimeUnit.MINUTES.toMillis(minute) +
				TimeUnit.HOURS.toMillis(hour);
		final CountTimer cdn = new CountTimer(millisToFin, 1000);
		cdn.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.count_down, menu);
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

	@Override
	public void onBackPressed() {
		Intent backKeyIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(backKeyIntent);
	}

	public class CountTimer extends CountDownTimer {

		public CountTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onTick(long millisUntilFinished) {
			long millis = millisUntilFinished;
			String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
					TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
					TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
			tv.setText(hms);
		}

		@Override
		public void onFinish() {
			tv.setText("Time up! Action completed!");

		}

	}

}
