package com.example.user.navbartemplatejava;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.navbartemplatejava.data.NcrRegistration;
import com.example.user.navbartemplatejava.data.network.ApiClient;
import com.example.user.navbartemplatejava.data.network.NcrRegInterface;
import com.example.user.navbartemplatejava.data.network.response.AddFormResponse;
import com.example.user.navbartemplatejava.data.network.response.AddNcrResponse;
import com.example.user.navbartemplatejava.data.network.response.BrowseNcrResponse;
import com.example.user.navbartemplatejava.data.prefs.PreferencesHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class regNCRActivity extends AppCompatActivity {
    public static final String TAG = regNCRActivity.class.getSimpleName();
    NcrRegInterface mNcrRegInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    PreferencesHelper mPreferencesHelper;
    private Button btnTambahNcr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_display_ncr_registration);

        mRecyclerView = findViewById(R.id.recyclerViewDisplayNcr);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mNcrRegInterface = ApiClient.getRetrofit().create(NcrRegInterface.class);
        mPreferencesHelper = ((InkaApp) getApplication()).getPrefs();
        btnTambahNcr = findViewById(R.id.btn_tambah_ncr);




    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        Call<BrowseNcrResponse> call = mNcrRegInterface.browseNcr(mPreferencesHelper.getUserSignInToken());
        call.enqueue(new Callback<BrowseNcrResponse>() {
            @Override
            public void onResponse(Call<BrowseNcrResponse> call, Response<BrowseNcrResponse> response) {
                Log.d(TAG, "onStart : onResponse : "+response.code()+" "+response.message());
                Toast.makeText(regNCRActivity.this, response.code() +" "+ response.message(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){
                    Log.d(TAG, "onStart : onResponse : successful");
                    Toast.makeText(regNCRActivity.this, response.code() +" "+ response.message(), Toast.LENGTH_SHORT).show();
                    mAdapter = new AdapterNcrRegistration(response.body().getNcr());
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<BrowseNcrResponse> call, Throwable t) {
                Log.d(TAG,"onStart : onFailure : " + t.toString());
                Toast.makeText(regNCRActivity.this, "Internet failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onClickBtnTambahNcr(View view) {
        Log.d(TAG, "onClickBtnTambahNcr");
        Call<AddFormResponse> call = ApiClient.getRetrofit()
                .create(NcrRegInterface.class)
                .addNcrForm(mPreferencesHelper.getUserSignInToken());
        call.enqueue(new Callback<AddFormResponse>() {
            @Override
            public void onResponse(@NonNull Call<AddFormResponse> call, @NonNull Response<AddFormResponse> response) {
                Log.d(TAG, "onClickBtnTambahNcr : onResponse : "+response.code() + " "+response.message());
                Toast.makeText(regNCRActivity.this, response.code() +" "+ response.message(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){
                    Log.d(TAG, "onClickBtnTambahNcr : onResponse : successful");
                    Toast.makeText(regNCRActivity.this, response.code() +" "+ response.message(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(regNCRActivity.this, AddNcrActivity.class);
                    intent.putExtra("data", response.body());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AddFormResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "onClickBtnTambahNcr : onFailure");
                Toast.makeText(regNCRActivity.this, "Internet failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
