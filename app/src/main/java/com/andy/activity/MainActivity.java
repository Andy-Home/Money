//主界面
package com.andy.activity;

import java.util.Calendar;

import com.andy.contant.MyApplication;
import com.andy.contant.NetworkClient;
import com.andy.type.Date;
import com.andy.money.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends Activity implements OnClickListener {
	private int year_int, month_int;
	private Button person, together, account;
	private TextView person_txt, together_txt;
	private TextView person_pay_tv, person_income_tv;
	private TextView together_pay_tv, together_income_tv;
	private double person_pay, person_income, person_sum;
	private double together_pay, together_income, together_sum;
	private String week_start, week_end;
	private MyApplication myApplication;
	Intent intent;
	Calendar calendar = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
		getWeekData();
		setListener();
	}

	private void init() {
		//根据系统获当天的日期,获取本周的日期范围
		Date date = new Date();
		week_start = date.getStart();
		week_end = date.getEnd();

		myApplication = (MyApplication) this.getApplication();

		person = (Button) findViewById(R.id.detail_person);
		together = (Button) findViewById(R.id.detail_together);
		account = (Button) findViewById(R.id.account);
		person_txt = (TextView) findViewById(R.id.main_person_statistics);
		together_txt = (TextView) findViewById(R.id.main_together_statistics);
		person_pay_tv = (TextView) findViewById(R.id.main_person_pay);
		person_income_tv = (TextView) findViewById(R.id.main_person_income);
		together_pay_tv = (TextView) findViewById(R.id.main_together_pay);
		together_income_tv = (TextView) findViewById(R.id.main_together_income);
	}

	private void setListener() {
		person.setOnClickListener(this);
		together.setOnClickListener(this);
		account.setOnClickListener(this);
	}

	private void getWeekData() {
		RequestParams params = new RequestParams();
		params.add("user_id", myApplication.getUserID());
		params.add("week_start", week_start);
		params.add("week_end", week_end);
		AsyncHttpClient client = new AsyncHttpClient();
		client.post(NetworkClient.getURL("findWeekAccount"), params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				if (statusCode == 200) {
					String result = new String(responseBody);
					try {
						JSONObject object = new JSONObject(result);
						together_income = object.getDouble("public_income");
						together_pay = object.getDouble("public_pay");
						together_sum = together_income - together_pay;
						person_income = object.getDouble("person_income");
						person_pay = object.getDouble("person_pay");
						person_sum = person_income - person_pay;
						initTextView();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	private void initTextView() {
		person_txt.setText("个人周统计：" + person_sum);
		person_pay_tv.setText("个人支出 ：" + person_pay);
		person_income_tv.setText("个人收入 ：" + person_income);

		together_txt.setText("公共周统计：" + together_sum);
		together_pay_tv.setText("公共支出 ：" + together_pay);
		together_income_tv.setText("公共收入 ：" + together_income);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.account:
			Log.d("MainActivity", "Click button of account");
			intent = new Intent(MainActivity.this, AccountActivity.class);
			startActivity(intent);
			break;

		case R.id.detail_person:
			Log.d("MainActivity", "Click button of detail person");
			intent = new Intent(MainActivity.this, DetailActivity.class);
			year_int = calendar.get(Calendar.YEAR);
			month_int = calendar.get(Calendar.MONTH) + 1;
			intent.putExtra("year", year_int);
			intent.putExtra("month", month_int);
			intent.putExtra("choose", 1);
			startActivity(intent);
			break;

		case R.id.detail_together:
			Log.d("MainActivity", "Click button of detail together");
			intent = new Intent(MainActivity.this, DetailActivity.class);
			year_int = calendar.get(Calendar.YEAR);
			month_int = calendar.get(Calendar.MONTH) + 1;
			intent.putExtra("year", year_int);
			intent.putExtra("month", month_int);
			intent.putExtra("choose", 0);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

}
