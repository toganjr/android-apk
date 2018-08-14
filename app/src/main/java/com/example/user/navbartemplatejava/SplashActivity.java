package com.example.user.navbartemplatejava;

import android.Manifest;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.navbartemplatejava.data.prefs.PreferencesHelper;

public class SplashActivity extends AppCompatActivity {
    PreferencesHelper mPrefs;
    public static final String[] PERMISSION_REQUEST = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static final int PERMISSION_REQUEST_CODE_SUCCESS = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mPrefs = ((InkaApp) getApplication()).getPrefs();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityCompat.requestPermissions(this, PERMISSION_REQUEST, PERMISSION_REQUEST_CODE_SUCCESS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE_SUCCESS){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    decideNextActivity();
                }
            }, 1500);
        }
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
