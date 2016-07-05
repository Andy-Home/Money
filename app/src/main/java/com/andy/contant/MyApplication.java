package com.andy.contant;

import android.app.Application;

/**
 * Created by andy on 16-3-17.
 */
public class MyApplication extends Application {
    private String userID = "0";

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
