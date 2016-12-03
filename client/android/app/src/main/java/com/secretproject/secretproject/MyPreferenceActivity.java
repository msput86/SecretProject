package com.secretproject.secretproject;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by andrew on 03.12.16.
 */

public class MyPreferenceActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        //setContentView(R.xml.settings);


    }
}
