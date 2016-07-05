package com.andy.contant;

/**
 * Created by andy on 16-3-12.
 * 网络地址
 */
public class NetworkClient {
    private static final String URL = "http://121.42.9.123/Login_Example/";

    public static String getURL(String string){
        return URL + string;
    }
}
