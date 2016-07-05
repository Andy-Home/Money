//DetailActivity中的Fragment，这是最先显示的界面
package com.andy.fragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.andy.activity.DetailActivity;
import com.andy.adapter.ListAdapter;
import com.andy.contant.MyApplication;
import com.andy.contant.NetworkClient;
import com.andy.type.Account;
import com.andy.money.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import cz.msebera.android.httpclient.Header;

public class DetailFragment extends Fragment {
    private ListView listView;
    private List<Account> dataList = new ArrayList<>();
    private ListAdapter adapter;
    private int year, month;
    private String choose;

    private MyApplication myApplication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        //在界面初始化前获取数据
        Log.d("DetailFragment", "onCreateView");
        getUpData();
        init(view);
        getDatailData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("DetailFragment","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("DetailFragment", "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("DetailFragment", "onDestroyView");
    }

    private void getUpData() {
        dataList.clear();
        DetailActivity detailActivity = (DetailActivity) getActivity();
        year = detailActivity.year;
        month = detailActivity.month;
        choose = detailActivity.choose;
    }

    private void init(View view) {
        myApplication = (MyApplication) getActivity().getApplication();

        listView = (ListView) view.findViewById(R.id.list);
    }

    private void getDatailData() {
        RequestParams params = new RequestParams();
        if (month < 10) {
            params.add("date_month", year + "-0" + month);
        } else {
            params.add("date_month", year + "-" + month);
        }
        params.add("choose", choose);
        params.add("user_id", myApplication.getUserID());
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(NetworkClient.getURL("findMonthAccount"), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    Gson gson = new Gson();
                    String result = new String(responseBody);

                    JsonParser parser = new JsonParser();
                    JsonElement el = parser.parse(result);

                    JsonArray jsonArray = null;
                    if(el.isJsonArray()){
                        jsonArray = el.getAsJsonArray();
                    }
                    Account account = null;
                    Iterator it = jsonArray.iterator();
                    while(it.hasNext()){
                        JsonElement e = (JsonElement)it.next();
                        account = gson.fromJson(e, Account.class);
                        dataList.add(account);
                    }
                    setAdapter();
                    //Message msg = new Message();
                    //myHandler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void setAdapter() {
        adapter = new ListAdapter(getActivity(), dataList);
        listView.setAdapter(adapter);
    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            //刷新listview
            adapter.notifyDataSetChanged();
            super.handleMessage(msg);
        }

    }

}
