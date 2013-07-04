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
		// Ĭ�ϵ�FIRST_SYSTEM_WINDOW��ֵ����2000��
		// 2003��2002�����������2003���͵�View��2002���͵Ļ�Ҫtop������ʾ��ϵͳ����״̬��֮�ϣ�
		this.wmParams.type = 2003;
		addView();
	}

	@Override
	public void onDestroy() {
		removeView();
		super.onDestroy();
	}

	/**
	 * ��Ӳ���ʾ�������硢ȥ�����
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
	 * �Ƴ��������硢ȥ�����
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
