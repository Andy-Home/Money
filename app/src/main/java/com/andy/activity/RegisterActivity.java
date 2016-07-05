package com.andy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
public class RegisterActivity extends Activity implements View.OnClickListener{
    private EditText username, pw, pw_again;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        setListener();
    }

    private void init() {
        username = (EditText) findViewById(R.id.register_name);
        pw = (EditText) findViewById(R.id.register_pw);
        pw_again = (EditText) findViewById(R.id.register_pw_again);
        register = (Button) findViewById(R.id.register_bt);
    }

    private void setListener() {
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_bt:
                register();
                break;
        }
    }

    private void register() {
        String password = pw.getText().toString().trim();
        String password_again = pw_again.getText().toString().trim();
        if(password.equals(password_again) && !(password.equals(""))){
            RequestParams params = new RequestParams();
            params.add("username", username.getText().toString().trim());
            params.add("password", pw.getText().toString().trim());
            AsyncHttpClient client = new AsyncHttpClient();
            client.post(NetworkClient.getURL("register"), params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    if (statusCode == 200) {
                        String result = new String(responseBody);
                        try {
                            JSONObject object = new JSONObject(result);
                            if (object.getBoolean("result")) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                String msg = object.getString("msg");
                                Toast toast = Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_LONG);
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
        else{
            Toast toast = Toast.makeText(RegisterActivity.this, "密码不一致", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
