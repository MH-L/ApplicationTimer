package com.example.notificationtimer;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	Button addNewTaskBtn;
	Button pickPhotoBtn;
	ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);

		pickPhotoBtn = (Button) findViewById(R.id.button2);
		addNewTaskBtn = (Button) findViewById(R.id.button1);
		iv = (ImageView) findViewById(R.id.imageView1);
		addNewTaskBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction("com.example.notificationtimer.TimePickerActivity");
				startActivity(intent);
			}
		});
		pickPhotoBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent pickPhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
				pickPhotoIntent.setType("image/*");
				startActivityForResult(pickPhotoIntent, 1);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 1) {
	        Bitmap imageChosen = (Bitmap) data.getExtras().get("data");
	        iv.setImageBitmap(imageChosen);
	    }
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
