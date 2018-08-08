package com.example.user.navbartemplatejava;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.navbartemplatejava.data.network.ApiClient;
import com.example.user.navbartemplatejava.data.network.AuthInterface;
import com.example.user.navbartemplatejava.data.network.LoginResponse;
import com.example.user.navbartemplatejava.data.prefs.PreferencesHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = LoginActivity.class.getSimpleName();
    Button btnLogin;
    EditText mUsername;
    EditText mPassword;
    PreferencesHelper mPrefs;

    public static Intent startIntent(Context context){
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnLogin = findViewById(R.id.login);
        mUsername = findViewById(R.id.text_nip);
        mPassword = findViewById(R.id.text_password);
        mPrefs = ((InkaApp) getApplication()).getPrefs();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login(){
        Log.d(TAG, "login");
        if (mUsername.getText().toString().isEmpty() || mPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Please complete the form", Toast.LENGTH_SHORT).show();
        }
        Call<LoginResponse> call = ApiClient.getRetrofit()
                .create(AuthInterface.class)
                .signIn(mUsername.getText().toString(), mPassword.getText().toString());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                Log.d(TAG, "login : onResponse");
                if (response.isSuccessful()){
                    mPrefs.setUserIsSignIn(true);
                    mPrefs.setUserSignIn(response.body().getUser());
                    mPrefs.setUserSignInToken(response.body().getToken());
                    onNextActivity();
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "login : onFailure");
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onNextActivity(){
        startActivity(MenuActivity.startIntent(this));
        finish();
    }
}