package com.example.notificationtimer;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;

public class SendSMSService extends IntentService {

	public SendSMSService() {
		super("SendSMSService");
	}

	public SendSMSService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		CountDownTimer timer = new CountDownTimer(20000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
				builder.setSmallIcon(R.drawable.ic_launcher);
				builder.setContentTitle("Countdown of Action");
				builder.setContentText("The service you ordered is about to happen!");
				Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
				TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
				stackBuilder.addParentStack(MainActivity.class);
				stackBuilder.addNextIntent(intent2);
				PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
				builder.setContentIntent(pendingIntent);
				NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				nm.notify(0, builder.build());

			}

			@Override
			public void onFinish() {
				NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
				builder.setSmallIcon(R.drawable.ic_launcher);
				builder.setContentTitle("Countdown of Action");
				builder.setContentText("The service you ordered is about to happen!");
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
				stackBuilder.addParentStack(MainActivity.class);
				stackBuilder.addNextIntent(intent);
				PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
				builder.setContentIntent(pendingIntent);
				NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				nm.notify(0, builder.build());
			}
		};

		timer.start();
	}

}
