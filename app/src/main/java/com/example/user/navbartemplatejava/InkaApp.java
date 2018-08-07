package com.example.user.navbartemplatejava;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.user.navbartemplatejava.data.prefs.PreferencesHelper;

public class InkaApp extends Application {
    private PreferencesHelper mPrefs;

    @Override
    public void onCreate() {
        super.onCreate();
        mPrefs = new PreferencesHelper(getApplicationContext());
    }

    public PreferencesHelper getPrefs() {
        return mPrefs;
    }
}
