package com.example.user.navbartemplatejava;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.navbartemplatejava.data.network.ApiClient;
import com.example.user.navbartemplatejava.data.network.NcrRegInterface;
import com.example.user.navbartemplatejava.data.network.response.AddFormResponse;
import com.example.user.navbartemplatejava.data.prefs.PreferencesHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewItemNcrVer extends AppCompatActivity {

    public static final String TAG = ViewItemNcrVer.class.getSimpleName();
    private ArrayList<exampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private AdapterNcrRegistration mAdapterNcrRegistration;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView mNoRegNcrTxt;
    private TextView mNamaProsesTxt;
    private TextView mNamaInspector;
    private TextView mTanggalTerbit;
    private TextView mUnit;
    private TextView mKodeProjek;
    private Button btnverifikasi;

    PreferencesHelper mPreferencesHelper;

    String ProjekID;
    String ProjekKode;

    String NoNCR;
    String NoRegIns;
    String TanggalTerbit;
    String NamaProses;
    String NamaProyek;
    String KodeProyek;
    String LokasiKet;
    String AcuanPem;
    String UraianKet;
    String DisposisiIns;
    String TargetPeny;
    Integer idPosition;
    String NamaVendor;
    String PIC;
    Double Latitude;
    Double Longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_content_verncr);
        mNoRegNcrTxt = findViewById(R.id.getNoNcr);
        mNamaProsesTxt = findViewById(R.id.getNamaProses);
        mNamaInspector = findViewById(R.id.getNamaInspector);
        mTanggalTerbit = findViewById(R.id.getTanggal);
        mUnit = findViewById(R.id.getUnit);
        mKodeProjek = findViewById(R.id.getKodeProjek);


        ProjekID = getIntent().getStringExtra("Projek ID");
        ProjekKode = getIntent().getStringExtra("Projek Kode");

        idPosition = getIntent().getIntExtra("position",0);
        NoNCR = getIntent().getStringExtra("No Registration NCR");
        TanggalTerbit = getIntent().getStringExtra("Tanggal Terbit");
        NamaProses = getIntent().getStringExtra("Nama Proses");
        KodeProyek = ProjekID+"-"+ProjekKode;
        NamaProyek = getIntent().getStringExtra("Nama Projek");
        NamaVendor = getIntent().getStringExtra("Nama Vendor");
        UraianKet = getIntent().getStringExtra("Uraian Ket");
        PIC = getIntent().getStringExtra("PIC");
        idPosition = getIntent().getIntExtra("position",0);
        Latitude = getIntent().getDoubleExtra("Latitude",0);
        Longitude = getIntent().getDoubleExtra("Longitude",0);


        mNoRegNcrTxt.setText(getIntent().getStringExtra("No Registration NCR"));
        mNamaProsesTxt.setText(getIntent().getStringExtra("Nama Proses"));
        mNamaInspector.setText(getIntent().getStringExtra("Nama Tester"));
        mTanggalTerbit.setText(getIntent().getStringExtra("Tanggal Terbit"));
        mUnit.setText(getIntent().getStringExtra("Unit"));
        mKodeProjek.setText(ProjekID+"-"+ProjekKode);

        btnverifikasi = findViewById(R.id.btnverver);
        mPreferencesHelper = ((InkaApp) getApplication()).getPrefs();
    }

    public void onClickBtnVer(View view) {

        Intent intent = new Intent(view.getContext(),VerifikasiVerNCRActivity.class);
        intent.putExtra("position",idPosition);
        view.getContext().startActivity(intent);
                }

    public void onClickBtnEdit2(View view) {
        Log.d(TAG, "onClickBtnUpload");
        Call<AddFormResponse> call = ApiClient.getRetrofit()
                .create(NcrRegInterface.class)
                .addNcrForm(mPreferencesHelper.getUserSignInToken());
        call.enqueue(new Callback<AddFormResponse>() {
            @Override
            public void onResponse(@NonNull Call<AddFormResponse> call, @NonNull Response<AddFormResponse> response) {
                Log.d(TAG, "onClickBtnUpload : onResponse : "+response.code() + " "+response.message());
                if (response.isSuccessful()){
                    Log.d(TAG, "onClickBtnUpload : onResponse : successful");
                    String ui_code = response.body().getUi_codes().getUi_code();
                    if ((ui_code.equals("INC")) || (ui_code.equals("SUB"))){
                        Intent intent = new Intent(ViewItemNcrVer.this, EditNcrINCSUBActivity.class);
                        intent.putExtra("data", response.body());
                        intent.putExtra("Nama Proses", NamaProses);
                        intent.putExtra("Nama Vendor",NamaVendor);
                        intent.putExtra("Uraian Ket",UraianKet);
                        intent.putExtra("PIC",PIC);
                        intent.putExtra("Latitude",Latitude);
                        intent.putExtra("Longitude",Longitude);
                        intent.putExtra("position", idPosition);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(ViewItemNcrVer.this, EditNcrOtherActivity.class);
                        intent.putExtra("data", response.body());
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AddFormResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "onClickBtnUpload : onFailure");
                Toast.makeText(ViewItemNcrVer.this, "Internet failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
