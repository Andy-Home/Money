//显示帐务列表
package com.andy.activity;

import com.andy.fragment.Detail_OtherFragment;
import com.andy.money.R;
import com.andy.fragment.DetailFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends FragmentActivity implements OnClickListener {
	private Button back, forward;
	private TextView title;
	public int year, month;
	public String choose;
	private Detail_OtherFragment detail_OtherFragment = new Detail_OtherFragment();
	private DetailFragment detailFragment = new DetailFragment();
	FragmentManager fragmentManager = getSupportFragmentManager();
	FragmentTransaction transaction = fragmentManager.beginTransaction();
	boolean flag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getUpData();
		setContentView(R.layout.activity_detail);
		init();
		setListener();
	}

	private void setListener() {
		back.setOnClickListener(this);
		forward.setOnClickListener(this);
	}

	private void init() {
		View view = (View) findViewById(R.id.include1);
		back = (Button) view.findViewById(R.id.title_back);
		forward = (Button) view.findViewById(R.id.title_forward);
		title = (TextView) view.findViewById(R.id.title_text);
		title.setText(month + "月");
	}

	//获取从上级界面传的日期数据，用于查询目标月的帐务数据
	private void getUpData() {
		year = getIntent().getIntExtra("year", 2015);
		month = getIntent().getIntExtra("month", 9);
		choose = String.valueOf(getIntent().getIntExtra("choose", 0));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_back:
			month -= 1;
			if (month == 0) {
				year -= 1;
				month = 12;
			}
			title.setText(month + "月");

			transaction = getSupportFragmentManager().beginTransaction();
			if (flag) {
				flag = false;
				transaction.replace(R.id.frag_layout, detail_OtherFragment);
			} else {
				flag = true;
				transaction.replace(R.id.frag_layout, detailFragment);
			}
			transaction.addToBackStack(null);
			transaction.commit();
			break;

		case R.id.title_forward:
			month += 1;
			if (month == 13) {
				year += 1;
				month = 1;
			}
			title.setText(month + "月");

			transaction = getSupportFragmentManager().beginTransaction();
			if (flag) {
				flag = false;
				transaction.replace(R.id.frag_layout, detail_OtherFragment);
			} else {
				flag = true;
				transaction.replace(R.id.frag_layout, detailFragment);
			}
			transaction.addToBackStack(null);
			transaction.commit();
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Intent intent = new Intent(DetailActivity.this, MainActivity.class);
			startActivity(intent);
			return true;
		}
		return false;
	}
}
