package com.jason.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @author jason
 */
public class PhoneStatReceiver extends BroadcastReceiver {

	private static final String TAG = "PhoneStatReceiver";
	private static boolean inFlag = false;
	private static String incoming_number = null;

	@Override
	public void onReceive(Context context, Intent intent) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

		if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
			inFlag = false;
			String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
			Boolean isout = sp.getBoolean("cb2", false);
			if (isout) {
				CallService.number = phoneNumber;
				CallService.isout = true;
				context.startService(new Intent(context, CallService.class));
			}
		} else {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Service.TELEPHONY_SERVICE);
			switch (tm.getCallState()) {
			/* 电话进来时 */
			case TelephonyManager.CALL_STATE_RINGING:
				inFlag = true;
				incoming_number = intent.getStringExtra("incoming_number");
				Boolean iscom = sp.getBoolean("cb1", false);
				if (iscom) {
					// 启动CallService 展示界面
					CallService.number = incoming_number;
					CallService.isout = false;
					context.startService(new Intent(context, CallService.class));
				}
				break;
			/* 接通电话时 */
			case TelephonyManager.CALL_STATE_OFFHOOK:
				if (inFlag) {
					Log.i(TAG, "接通电话" + incoming_number);
					// 可以设置一个接通电话的界面显示
				}
				break;
			/* 无任何状态时 */
			case TelephonyManager.CALL_STATE_IDLE:
				if (inFlag) {
					Log.i(TAG, "无任何状态时");
					context.stopService(new Intent(context, CallService.class));
				}
				break;
			}
		}
	}

}
