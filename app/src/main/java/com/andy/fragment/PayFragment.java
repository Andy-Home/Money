//依附AccountActivity
package com.andy.fragment;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.andy.activity.MainActivity;
import com.andy.contant.MyApplication;
import com.andy.contant.NetworkClient;
import com.andy.money.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class PayFragment extends android.support.v4.app.Fragment implements OnClickListener {
	private EditText number, explain;
	private TextView date;
	private RadioButton person, together;

	private Button save;
	private String amount;
	private String introduction;
	private Date day;
	private String day_string;
	private String choose = "1", type = "1";
	private MyApplication myApplication;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("PayFragment", "test");
		View view = inflater.inflate(R.layout.fragment_account_pay, container,
				false);
		init(view);
		setListener();
		return view;
	}

	private void init(View view) {
		myApplication = (MyApplication) getActivity().getApplication();

		number = (EditText) view.findViewById(R.id.pay_number);
		explain = (EditText) view.findViewById(R.id.pay_explain);
		date = (TextView) view.findViewById(R.id.pay_date);
		person = (RadioButton) view.findViewById(R.id.pay_person);
		together = (RadioButton) view.findViewById(R.id.pay_together);
		save = (Button) view.findViewById(R.id.pay_save);

		Calendar calendar = Calendar.getInstance();
		day = new Date(calendar.get(Calendar.YEAR) - 1900,
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		date.setText(calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DAY_OF_MONTH));
	}

	private void setListener() {
		save.setOnClickListener(this);
		date.setOnClickListener(this);
		person.setOnClickListener(this);
		together.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.pay_save:
			RequestParams params = new RequestParams();
			Log.d("IncomeActivity", "day = " + day);
			amount = number.getText().toString();
			day_string = new SimpleDateFormat("yyyy-MM-dd").format(day);
			introduction = explain.getText().toString();

			params.add("user_id", myApplication.getUserID());
			params.add("date", day_string);
			params.add("account_desc", introduction);
			params.add("number", amount);
			params.add("choose", choose);
			params.add("type", type);
			AsyncHttpClient client = new AsyncHttpClient();
			client.post(NetworkClient.getURL("insertAccount"), params, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
					if (statusCode == 200) {
						String result = new String(responseBody);
						try {
							JSONObject object = new JSONObject(result);
							if (object.getBoolean("result")) {
								Intent intent = new Intent(getActivity(), MainActivity.class);
								startActivity(intent);
							} else {
								String msg = object.getString("msg");
								Toast toast = Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG);
								toast.show();
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}

				@Override
				public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

				}
			});
			break;
		// Set date that user pay for money
		case R.id.pay_date:
			Calendar calendar = Calendar.getInstance();
			new DatePickerDialog(getActivity(), Listener,
					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH)).show();
			break;

		case R.id.pay_person:
			choose = "1";
			Log.d("PayActivity", "person");
			break;

		case R.id.pay_together:
			choose = "0";
			Log.d("PayActivity", "together");
			break;

		default:
			break;
		}
	}

	// when you click date textview,the datepickerdialog will show,this is the
	// setlistener of date textview
	DatePickerDialog.OnDateSetListener Listener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			day = new Date(year - 1900, monthOfYear, dayOfMonth);
			date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
		}
	};
}
