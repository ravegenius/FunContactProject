package com.jason.ui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

/**
 * @author jason
 */
public class SuperCallView extends RelativeLayout implements OnClickListener {

	Context context;

	public SuperCallView(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public void onClick(View v) {
	}

	public static ITelephony iTelephony = null;

	public void getTelephony() {
		TelephonyManager tManager = (TelephonyManager) this.context.getSystemService(Context.TELEPHONY_SERVICE);
		// ≥ı ºªØiTelephony
		Class<TelephonyManager> c = TelephonyManager.class;
		Method getITelephonyMethod = null;
		try {
			getITelephonyMethod = c.getDeclaredMethod("getITelephony", (Class[]) null);
			getITelephonyMethod.setAccessible(true);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		try {
			iTelephony = (ITelephony) getITelephonyMethod.invoke(tManager, (Object[]) null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
