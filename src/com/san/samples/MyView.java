package com.san.samples;


import java.util.Calendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MyView extends Activity {

	public static Activity mContext = null;
	public static int count = 0;
	protected AlarmReceiver myReceiver;  
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mContext = this;
		
//		myReceiver = new AlarmReceiver();
//		mContext.registerReceiver(myReceiver, null); 
		
		System.out.println("yzj onCreate count = " + count);
		count = 0;
		unregisterAllLocalNotifications();
		for(int i = 1;i<=10; i++){
			registerLocalNotification(i*5000,"aa"+i);
		}
	}
	
	public static void registerLocalNotification(int time,String msg){
		AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);
		
		Intent myIntent = new Intent(mContext, AlarmReceiver.class);
		myIntent.putExtra("msg", msg);
		myIntent.putExtra("id", count);
		// 第二个参数一定不一样 否则重复只会让最后一个起作用
		System.out.println("yzj id = " + count);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, count++, myIntent, 0);
 
		alarmManager.set(AlarmManager.RTC, Calendar.getInstance().getTimeInMillis() + time, pendingIntent);
	}
	
	public static void unregisterAllLocalNotifications(){
		((NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();
	}
}