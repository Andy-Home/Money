package com.andy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.contant.MyApplication;
import com.andy.contant.NetworkClient;
import com.andy.money.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by andy on 16-3-11.
 */
public class LoginActivity extends Activity implements View.OnClickListener{
    private EditText name, password;
    private TextView forget_pw, register;
    private Button login;
    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        setListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void init() {
        myApplication = (MyApplication) this.getApplication();

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        forget_pw = (TextView) findViewById(R.id.forgetpw);
        register = (TextView) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);
    }

    private void setListener() {
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                login();
                break;
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login() {
        RequestParams params = new RequestParams();
        params.add("username", name.getText().toString().trim());
        params.add("password", password.getText().toString().trim());
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(NetworkClient.getURL("login"), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {
                    String result = new String(responseBody);
                    try {
                        JSONObject object = new JSONObject(result);
                        if (object.getBoolean("result")) {
                            myApplication.setUserID(object.getString("userID"));
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            String msg = object.getString("msg");
                            Toast toast = Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG);
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
    }
}
