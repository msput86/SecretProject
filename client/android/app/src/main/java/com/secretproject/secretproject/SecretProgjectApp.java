package com.secretproject.secretproject;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrew on 05.12.16.
 */

public class SecretProgjectApp extends Application {

    public final String[] paramKeys = {
            "server_address",
            "server_port",
            "server_api",
            "server_login",
            "server_nickname"
    };

    private Map<String, String> params = new HashMap<String, String>();

    public Map<String, String> getParams() {
        return this.params;
    }
    public String getParam(String key) {
        return this.params.get(key);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.reloadParams();
    }

    public void setParam(String key, String value) {
        this.params.put(key, value);
    }

    public void reloadParams() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        for (String key: this.paramKeys) {
            this.params.put(key, prefs.getString(key, null));
        }
        Log.i("APP", this.params.toString());
    }
}
