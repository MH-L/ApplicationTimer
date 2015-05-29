package com.example.notificationtimer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMSOptionActivity extends Activity {

	Button sendMessageBtn;
	Button addFavoriteBtn;
	EditText numberToSend;
	EditText messageBody;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smsoption);

		sendMessageBtn = (Button) findViewById(R.id.button1);
		addFavoriteBtn = (Button) findViewById(R.id.button2);
		numberToSend = (EditText) findViewById(R.id.editText1);
		messageBody = (EditText) findViewById(R.id.editText2);

		sendMessageBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String number = numberToSend.getText().toString();
				String msgBody = messageBody.getText().toString();
				if (number.length() != 10 && number.length() != 11) {
					int duration = Toast.LENGTH_LONG;
					String alarmMsg = "The number you entered must be 10 digits(possibly US or Canada number) or"
							+ "11 digits (compatible with users in China)";
					Toast toast = Toast.makeText(getApplicationContext(), alarmMsg, duration);
					toast.show();
					return;
				} else if (!isNumeric(number)) {
					int duration = Toast.LENGTH_SHORT;
					String alarmMsg = "You must enter a number in the number field.";
					Toast toast = Toast.makeText(getApplicationContext(), alarmMsg, duration);
					toast.show();
					return;
				}

				int duration2 = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(getApplicationContext(), "Redirecting you to sms!", duration2);
				toast.show();
//				Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//				smsIntent.setType("vnd.android-dir/mms-sms");
//				smsIntent.putExtra("address", number);
//				smsIntent.putExtra("sms_body", msgBody);
//				startActivity(smsIntent);
//				Uri uri = Uri.parse("smsto:" + number);
//				Intent it = new Intent(Intent.ACTION_SENDTO, uri);
//				it.putExtra("sms_body", msgBody);
//				startActivity(it);
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(number, null, msgBody, null, null);
			}

			public boolean isNumeric(String s) {
			    return s.matches("[-+]?\\d*\\.?\\d+");
			}
		});

		addFavoriteBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int duration = Toast.LENGTH_SHORT;
				String alarmMsg = "Favorite saved! Check it in main page under personal info.";
				Toast toast = Toast.makeText(getApplicationContext(), alarmMsg, duration);
				toast.show();
				return;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.smsoption, menu);
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
