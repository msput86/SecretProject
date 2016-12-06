package com.secretproject.secretproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.view.View;
import android.widget.EditText;
import android.preference.EditTextPreference;
/**
 * Created by andrew on 03.12.16.
 */

public class SettingsActivity extends PreferenceActivity /*implements SharedPreferences.OnSharedPreferenceChangeListener*/ {

    private View nickNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //addPreferencesFromResource(R.xml.settings);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();

        SecretProgjectApp myApp = (SecretProgjectApp) getApplication();
        myApp.reloadParams();

        //Preference pref = findPreference("server_nickname");
        //EditTextPreference listPref = (EditTextPreference) pref;
        //listPref.setSummary("sdfsdfasdfsd");
    }


//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//        Preference pref = findPreference(key);
//
//        if (pref instanceof EditTextPreference) {
//            ListPreference listPref = (ListPreference) pref;
//            pref.setSummary(listPref.getEntry());
//        }
//    }

/*---------------------------------------*/
    public static class MyPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);
        }
    }
}
