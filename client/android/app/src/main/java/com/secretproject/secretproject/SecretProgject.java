package com.secretproject.secretproject;

import android.app.Application;

import java.util.Map;

/**
 * Created by andrew on 05.12.16.
 */

public class SecretProgject extends Application {
    private Map Params = null;

    public Map getParams() {
        return this.Params;
    }

    public void setParam(String name, String value) {

    }
}
