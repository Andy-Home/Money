//进入应用程序时显示图片
package com.andy.activity;

import java.util.Timer;
import java.util.TimerTask;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.andy.money.R;

public class StartActivity extends Activity {

	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_start);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				intent = new Intent(StartActivity.this, LoginActivity.class);
				startActivity(intent);
			}
		};
		timer.schedule(task, 2500);
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

}
