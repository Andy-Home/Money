package com.andy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andy.money.R;
import com.andy.type.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andy on 16-3-23.
 */
public class DetailAdapter extends BaseAdapter {
    private Context context;
    private List<Account> accounts = new ArrayList<>();
    public DetailAdapter(Context context, List<Account> accounts){
        this.context = context;
        this.accounts = accounts;
    }

    @Override
    public int getCount() {
        int count = accounts.size();
        return accounts.size();
    }

    @Override
    public Object getItem(int i) {
        return accounts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_detail, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.desc = (TextView) view.findViewById(R.id.item_detail_desc);
            viewHolder.num = (TextView) view.findViewById(R.id.item_detail_num);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.num.setText(accounts.get(i).getNumber());
        viewHolder.desc.setText(accounts.get(i).getAccount_desc());
        return view;
    }

    class ViewHolder{
        TextView desc;
        TextView num;
    }


}
