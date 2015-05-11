package com.san.samples;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

	private NotificationManager mManager;
	
	@Override
	public void onReceive(Context context, Intent intent) {

//		Intent myIntent = new Intent(context, NotificationService.class);
//		myIntent.putExtra("msg", intent.getStringExtra("msg"));
//		context.startService(myIntent);
		if (intent == null) System.out.println("yzj ffffffffffffffffffffff");
		test(context, intent);

	}

	private void test(Context context, Intent intent) {
		// 不需要用this.getApplicationContext()。getSystemService
		mManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		// 点击后回到首页
		Intent intent1 = new Intent(context.getApplicationContext(), MyView.class);

		int id = context.getResources().getIdentifier("ic_launcher", "drawable", context.getPackageName());
		// 通知的具体内容
		Notification notification = new Notification(id, "新消息请查收",
				System.currentTimeMillis());

		intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);

		PendingIntent pendingNotificationIntent = PendingIntent.getActivity(
				context.getApplicationContext(), 0, intent1,
				PendingIntent.FLAG_UPDATE_CURRENT);

		// 点击后自动消失
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		// 添加声音和震动
		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.defaults |= Notification.DEFAULT_VIBRATE;

		notification.setLatestEventInfo(context.getApplicationContext(), "中文标题",
				intent.getStringExtra("msg"), pendingNotificationIntent);
		mManager.notify(intent.getIntExtra("id", 0), notification);
	}

}