package com.example.user.navbartemplatejava;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.navbartemplatejava.data.prefs.PreferencesHelper;

public class SplashActivity extends AppCompatActivity {
    PreferencesHelper mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mPrefs = ((InkaApp) getApplication()).getPrefs();
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                decideNextActivity();
            }
        }, 1500);
    }

    protected void decideNextActivity(){
        if (mPrefs.isSignIn()){
            startActivity(MenuActivity.startIntent(this));
        }else{
            startActivity(LoginActivity.startIntent(this));
        }
        finish();
    }
}
