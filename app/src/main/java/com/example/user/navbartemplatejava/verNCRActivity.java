package com.example.user.navbartemplatejava;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.user.navbartemplatejava.data.network.ApiClient;
import com.example.user.navbartemplatejava.data.network.InspecVerifInterface;
import com.example.user.navbartemplatejava.data.network.NcrRegInterface;
import com.example.user.navbartemplatejava.data.network.response.AddFormResponse;
import com.example.user.navbartemplatejava.data.network.response.BrowseNcrResponse;
import com.example.user.navbartemplatejava.data.prefs.PreferencesHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class verNCRActivity extends AppCompatActivity {
    public static final String TAG = verNCRActivity.class.getSimpleName();
    InspecVerifInterface mNcrRegInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    PreferencesHelper mPreferencesHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_display_ncr_verifikasi);

        mRecyclerView = findViewById(R.id.recyclerViewDisplayNcrver);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mNcrRegInterface = ApiClient.getRetrofit().create(InspecVerifInterface.class);
        mPreferencesHelper = ((InkaApp) getApplication()).getPrefs();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        Call<BrowseNcrResponse> call = mNcrRegInterface.browseInspec(mPreferencesHelper.getUserSignInToken());
        call.enqueue(new Callback<BrowseNcrResponse>() {
            @Override
            public void onResponse(Call<BrowseNcrResponse> call, Response<BrowseNcrResponse> response) {
                Log.d(TAG, "onStart : onResponse : "+response.code()+" "+response.message());
                if (response.isSuccessful()){
                    Log.d(TAG, "onStart : onResponse : successful");
                    mAdapter = new AdapterNcrVerifikasi(response.body().getNcr());
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<BrowseNcrResponse> call, Throwable t) {
                Log.d(TAG,"onStart : onFailure : " + t.toString());
                Toast.makeText(verNCRActivity.this, "Internet failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
