package com.jason.ui;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jason.contact.R;
import com.jason.service.CallService;

/**
 * @author jason
 */
public class OutCallView extends SuperCallView {

	Context context;

	public OutCallView(Context context, String number) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.outcallview, this);
		this.context = context;
		TextView numbertxt = (TextView) findViewById(R.id.number);
		numbertxt.setText("ÄãÕýÔÚ²¦´ò:" + number);
		Button cancelcall = (Button) this.findViewById(R.id.cancelcall);
		cancelcall.setTag("cancelcall");
		cancelcall.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		getTelephony();
		if (v.getTag().toString().equals("cancelcall")) {
			try {
				OutCallView.iTelephony.endCall();
				this.context.stopService(new Intent(this.context,
						CallService.class));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
}
