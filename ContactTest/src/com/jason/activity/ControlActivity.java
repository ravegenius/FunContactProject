package com.jason.activity;

import com.jason.contact.R;
import com.jason.service.CallService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * @author jason
 */
public class ControlActivity extends Activity {

	private Context mContext;
	private SharedPreferences localSharedPreferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mContext = this;
		localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		/** 获取通讯录信息 **/
		Button botton0 = (Button) findViewById(R.id.button0);
		botton0.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext, ContactsActivity.class);
				startActivity(intent);
			}
		});

		CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox1);
		cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (!isChecked) {
					ControlActivity.this.stopService(new Intent(ControlActivity.this,
							CallService.class));
				}
				localSharedPreferences.edit().putBoolean("cb1", isChecked)
						.commit();
			}
		});
		cb1.setChecked(localSharedPreferences.getBoolean("cb1", false));

		CheckBox cb2 = (CheckBox) findViewById(R.id.checkBox2);
		cb2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				localSharedPreferences.edit().putBoolean("cb2", isChecked)
						.commit();
			}
		});
		cb2.setChecked(localSharedPreferences.getBoolean("cb2", false));

	}
}