package com.example.user.navbartemplatejava;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    NcrRegInterface mNcrRegInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static regNCRActivity regNCRActivity;
    PreferencesHelper mPreferencesHelper;
    private ArrayList <exampleItem> mExampleList;
    private Button btnTambahNcr;
    private AdapterNcrRegistration mAdapterNcrRegistration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ncr_registration);

        mRecyclerView = findViewById(R.id.recyclerViewDisplayNcr);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mNcrRegInterface = ApiClient.getRetrofit().create(NcrRegInterface.class);
        mPreferencesHelper = ((InkaApp) getApplication()).getPrefs();
        btnTambahNcr = findViewById(R.id.btn_tambah_ncr);
        createExampleList();


    }
    //method for put item in RecycleView for example
    public void createExampleList(){
        mExampleList = new ArrayList<>();
        mExampleList.add(new exampleItem("XASD-123-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA 1"));
        mExampleList.add(new exampleItem("XASD-124-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA 2"));
        mExampleList.add(new exampleItem("XASD-125-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA 3"));
        mExampleList.add(new exampleItem("XASD-126-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA 4"));
        mExampleList.add(new exampleItem("XASD-127-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA 5"));
        mExampleList.add(new exampleItem("XASD-128-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA 6"));
        mExampleList.add(new exampleItem("XASD-129-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA 7"));
    }


    @Override
    protected void onStart() {
        super.onStart();

        //for test Example RecycleView
//        mAdapter = new AdapterNcrRegistration(mExampleList);
//        mRecyclerView.setAdapter(mAdapter);

//        For get data from server
       Call<BrowseNcrResponse> call = mNcrRegInterface.browseNcr(mPreferencesHelper.getUserSignInToken());

        call.enqueue(new Callback<BrowseNcrResponse>() {
            @Override
            public void onResponse(Call<BrowseNcrResponse> call, Response<BrowseNcrResponse> response) {
                List<NcrRegistration> ncrRegistrations = response.body().getNcr();
                mAdapter = new AdapterNcrRegistration(ncrRegistrations);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<BrowseNcrResponse> call, Throwable t) {
                Log.e("Retrofit Get",t.toString());
            }
        });



    }


    public void onClickBtnTambahNcr(View view) {
        Intent intent = new Intent(this,AddNcrActivity.class);
        startActivity(intent);
        finish();
    }
}
