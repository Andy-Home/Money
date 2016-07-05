//设置ListView的显示情况
package com.andy.adapter;

import java.util.List;

import com.andy.contant.Constat;
import com.andy.type.Account;
import com.andy.money.R;
import com.andy.type.Statistics;
import com.andy.util.DayAccount;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
	private String choose;
	private Context context;
	private String data_compare = "0000-00-00";
	private double income = 0.0, pay = 0.0;
	private List<Account> accounts;
	private DayAccount dayAccount;
	int time = 0;

	public ListAdapter(Context context, List<Account> accounts) {
		this.context = context;
		this.accounts = accounts;
		dayAccount = new DayAccount(accounts);
	}

	@Override
	public int getCount() {
		return accounts.size();
	}

	@Override
	public Object getItem(int position) {
		return accounts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_list_detail,parent, false);
			viewHolder = new ViewHolder();

			viewHolder.info_layout = (LinearLayout) convertView
					.findViewById(R.id.info_layout);
			viewHolder.pay_layout = (LinearLayout) convertView
					.findViewById(R.id.pay_layout);
			viewHolder.income_layout = (LinearLayout) convertView
					.findViewById(R.id.income_layout);
			viewHolder.info_date = (TextView) convertView.findViewById(R.id.info_date);
			viewHolder.info_pay = (TextView) convertView.findViewById(R.id.info_pay);
			viewHolder.info_income = (TextView) convertView
					.findViewById(R.id.info_income);
			viewHolder.pay_amount = (TextView) convertView
					.findViewById(R.id.pay_amount);
			viewHolder.pay_introduction = (TextView) convertView
					.findViewById(R.id.pay_introduction);
			viewHolder.income_amount = (TextView) convertView
					.findViewById(R.id.income_amount);
			viewHolder.income_introduction = (TextView) convertView
					.findViewById(R.id.income_introduction);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// 是否显示日期等信息
		if (!accounts.get(position).getDate().equals(data_compare)) {
			data_compare = accounts.get(position).getDate();
			Log.d("ListAdapter", "is different");
			pay = dayAccount.getPay(data_compare, position);
			income = dayAccount.getIncome(data_compare, position);
			viewHolder.info_layout.setVisibility(View.VISIBLE);
			viewHolder.info_date.setText(accounts.get(position).getDate());
			viewHolder.info_pay.setText("支出 : " + pay);
			viewHolder.info_income.setText(" 收入 : " + income);
		} else {
			Log.d("ListAdapter", "is same");
			viewHolder.info_layout.setVisibility(View.GONE);
		}
		// judgment is an input or output
		// 显示的是支出项还是收入项
		if (accounts.get(position).getType().equals(Constat.TYPE_INCOME)) {
			viewHolder.income_layout.setVisibility(View.VISIBLE);
			viewHolder.pay_layout.setVisibility(View.GONE);
			viewHolder.income_amount.setText("" + accounts.get(position).getNumber());
			viewHolder.income_introduction.setText(accounts.get(position).getAccount_desc());
		} else if (accounts.get(position).getType().equals(Constat.TYPE_PAY)) {
			viewHolder.pay_layout.setVisibility(View.VISIBLE);
			viewHolder.income_layout.setVisibility(View.GONE);
			viewHolder.pay_amount.setText("" + accounts.get(position).getNumber());
			viewHolder.pay_introduction.setText(accounts.get(position).getAccount_desc());
		}
		return convertView;
	}

	// 保存显示过的数据，减少占用的系统资源
	class ViewHolder {
		LinearLayout pay_layout;
		LinearLayout income_layout;
		LinearLayout info_layout;
		TextView info_date;
		TextView info_pay;
		TextView info_income;
		TextView pay_amount;
		TextView pay_introduction;
		TextView income_amount;
		TextView income_introduction;
	}
}
