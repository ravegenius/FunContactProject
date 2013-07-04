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
public class InCallView extends SuperCallView {

	Context context;

	public InCallView(Context context, String number) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.incallview, this);
		this.context = context;

		TextView numbertxt = (TextView) findViewById(R.id.number);
		numbertxt.setText(number + "À´µç");

		Button yesbtn = (Button) this.findViewById(R.id.yescall);
		yesbtn.setTag("yescall");
		yesbtn.setOnClickListener(this);
		Button nobtn = (Button) this.findViewById(R.id.nocall);
		nobtn.setTag("nocall");
		nobtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		getTelephony();
		if (v.getTag().toString().equals("yescall")) {
			try {
				InCallView.iTelephony.answerRingingCall();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} else {
			try {
				InCallView.iTelephony.endCall();
				this.context.stopService(new Intent(this.context, CallService.class));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
}
