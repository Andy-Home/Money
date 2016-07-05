//记帐界面，最先显示的是PayFragment,点击Income按钮后显示的IncomeFragment
package com.andy.activity;

import com.andy.fragment.IncomeFragment;
import com.andy.fragment.PayFragment;
import com.andy.money.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class AccountActivity extends FragmentActivity {
    private FragmentTabHost tabHost;
    private LayoutInflater layoutInflater;

    private Class fragment[] = {PayFragment.class, IncomeFragment.class};
    private String text[] = {"支出", "收入"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        init();
    }

    //初始化界面
    private void init() {
        layoutInflater = LayoutInflater.from(this);

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (int i = 0; i < fragment.length; i++) {
            tabHost.addTab(tabHost.newTabSpec(text[i]).setIndicator(getView(i)), fragment[i], null);
        }
    }

    private View getView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_account, null);
        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        textView.setText(text[index]);
        return view;
    }
}