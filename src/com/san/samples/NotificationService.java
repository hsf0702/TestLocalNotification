package com.san.samples;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NotificationService extends Service {

	private NotificationManager mManager;

	@Override
	public IBinder onBind(Intent arg0) {
		System.out.println("yzj service onBind");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLowMemory() {
		System.out.println("yzj onLowMemory() service");
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onTaskRemoved(Intent rootIntent) {
		System.out.println("yzj onTaskRemoved() service11");
		// TODO Auto-generated method stub
		super.onTaskRemoved(rootIntent);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("yzj onUnbind() service");
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}

	@Override
	public void onCreate() {
		System.out.println("yzj service oncreate");
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		if (intent == null) {
			System.out.println("yzj fufufufuffufufufufuf!!");
			return;
		}
		super.onStart(intent, startId);
		System.out.println("yzj NotificationService onstart" + startId);

		// 不需要用this.getApplicationContext()。getSystemService
		mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		// 点击后回到首页
		Intent intent1 = new Intent(this.getApplicationContext(), MyView.class);

		// 通知的具体内容
		Notification notification = new Notification(0x7f020000, "新消息请查收",
				System.currentTimeMillis());

		intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);

		PendingIntent pendingNotificationIntent = PendingIntent.getActivity(
				this.getApplicationContext(), 0, intent1,
				PendingIntent.FLAG_UPDATE_CURRENT);

		// 点击后自动消失
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		
		// 添加声音和震动  
		notification.defaults |= Notification.DEFAULT_SOUND;  
		notification.defaults |= Notification.DEFAULT_VIBRATE;  
		
		notification.setLatestEventInfo(this.getApplicationContext(), "中文标题",
				intent.getStringExtra("msg"),
				pendingNotificationIntent);
		mManager.notify(0, notification);
	}

	@Override
	public void onDestroy() {
		System.out.println("yzj onDestory() service");
		super.onDestroy();
	}
	
	/*
 16  双倍
 81  全力一击
 103
 167
 189
 254
 
 
	 * */

}