package com.jason.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;

import com.jason.ui.InCallView;
import com.jason.ui.OutCallView;

/**
 * @author jason
 */
public class CallService extends Service {
	private static final String TAG = "CallService";
	WindowManager localWindowManager;
	WindowManager.LayoutParams wmParams;
	InCallView icView;
	OutCallView ocView;
	static String number;
	static Boolean isout;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		this.localWindowManager = ((WindowManager) getSystemService("window"));
		this.wmParams = new WindowManager.LayoutParams();
		// 默认的FIRST_SYSTEM_WINDOW的值就是2000。
		// 2003和2002的区别就在于2003类型的View比2002类型的还要top，能显示在系统下拉状态栏之上！
		this.wmParams.type = 2003;
		addView();
	}

	@Override
	public void onDestroy() {
		removeView();
		super.onDestroy();
	}

	/**
	 * 添加并显示覆盖来电、去电界面
	 */
	public void addView() {
		Log.i(TAG, ".addView");
		removeView();
		if (isout) {
			this.ocView = new OutCallView(this, number);
			this.localWindowManager.addView(this.ocView, this.wmParams);
		} else {
			this.icView = new InCallView(this, number);
			this.localWindowManager.addView(this.icView, this.wmParams);
		}
	}

	/**
	 * 移除覆盖来电、去电界面
	 */
	public void removeView() {
		Log.i(TAG, ".removeView");
		if (isout) {
			if (this.ocView != null) {
				this.localWindowManager.removeView(this.ocView);
				this.ocView = null;
			}
		} else {
			if (this.icView != null) {
				this.localWindowManager.removeView(this.icView);
				this.icView = null;
			}
		}
	}
}
