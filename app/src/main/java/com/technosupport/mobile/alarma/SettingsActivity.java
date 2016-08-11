package com.technosupport.mobile.alarma;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String NUMBER_TO_SEND = "editText_number";
    public static final String FIND_CODE = "editText_find";
    public static final String ON_CODE = "editText_on";
    public static final String OFF_CODE = "editText_off";
    public static final String OPEN_CODE = "editText_open";
    public static final String CLOSE_CODE = "editText_close";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(NUMBER_TO_SEND)){
            Preference number = findPreference(key);
            number.setSummary(sharedPreferences.getString(key, ""));
        }
    }
}
