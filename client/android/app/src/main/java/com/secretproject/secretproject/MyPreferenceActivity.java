package com.secretproject.secretproject;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.TextView;

/**
 * Created by andrew on 03.12.16.
 */

public class MyPreferenceActivity extends PreferenceActivity {

    //private TextView nickNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        //Intent myIntent = getIntent();
        //nickNameTextView = (TextView) findViewById(R.id.nickNameTextView);
        //nickNameTextView.setText(myIntent.getStringExtra("TestString"));
    }
}
