package com.example.user.navbartemplatejava;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.navbartemplatejava.Tab.SlidingTabLayout;
import com.example.user.navbartemplatejava.data.network.ApiClient;
import com.example.user.navbartemplatejava.data.network.InspecVerifInterface;
import com.example.user.navbartemplatejava.data.network.response.NCRVerification;
import com.example.user.navbartemplatejava.data.prefs.PreferencesHelper;


import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifikasiVerNCRActivity extends AppCompatActivity {

    public static final String TAG = VerifikasiVerNCRActivity.class.getSimpleName();
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    PreferencesHelper mPreferencesHelper;

    InspecVerifInterface mNcrVerInterface;

    TextView isiTV11;
    TextView isiTV12;
    TextView isiTV13;
    TextView isiTV14;
    TextView isiTV15;
    TextView isiTV16;
    TextView isiTV17;
    TextView isiTV18;
    TextView isiTV19;
    TextView isiTV110;

    TextView isi21;
    TextView isi22;
    TextView isi23;
    TextView isi24;
    TextView isi25;
    TextView isi26;

    Button Button1;

    private Integer idPosition;
    private String NoNCR;
    private String noRegIns;
    private String TanggalTerbit;
    private String NamaProses;
    private String NamaProyek;
    private String KodeProyek;
    private String LokasiKet;
    private String AcuanPem;
    private String UraianKet;
    private String DisposisiIns;
    private String TargetPeny;
    private String UraianAkarM;
    private String TindakanPerb;
    private String TindakanPenc;
    private String DisposisiUnit;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_verifikasi_ver_ncr);

        idPosition = getIntent().getIntExtra("position",0);
        mNcrVerInterface = ApiClient.getRetrofit().create(InspecVerifInterface.class);

        mPreferencesHelper = ((InkaApp) getApplication()).getPrefs();
        final SimpleDateFormat Formatter = new SimpleDateFormat("dd-MM-yyyy");

        isiTV11 = (TextView)findViewById(R.id.isiTV11);
        isiTV12 = (TextView)findViewById(R.id.isiTV12);
        isiTV13 = (TextView)findViewById(R.id.isiTV13);
        isiTV14 = (TextView)findViewById(R.id.isiTV14);
        isiTV15 = (TextView)findViewById(R.id.isiTV15);
        isiTV16 = (TextView)findViewById(R.id.isiTV16);
        isiTV17 = (TextView)findViewById(R.id.isiTV17);
        isiTV18 = (TextView)findViewById(R.id.isiTV18);
        isiTV19 = (TextView)findViewById(R.id.isiTV19);
        isiTV110 = (TextView)findViewById(R.id.isiTV110);

        isi21 = (TextView)findViewById(R.id.isiTV21);
        isi22 = (TextView)findViewById(R.id.isiTV22);
        isi23 = (TextView)findViewById(R.id.isiTV23);
        isi24 = (TextView)findViewById(R.id.isiTV24);
        isi25 = (TextView)findViewById(R.id.isiTV25);
        isi26 = (TextView)findViewById(R.id.isiTV26);

        Button1 = (Button)findViewById(R.id.button1);


        Call<NCRVerification> call = mNcrVerInterface.browseSelectedIns(idPosition,mPreferencesHelper.getUserSignInToken());
        call.enqueue(new Callback<NCRVerification>() {
            @Override
            public void onResponse(Call<NCRVerification> call, Response<NCRVerification> response) {
                Log.d(TAG, "onStart : onResponse : "+response.code()+" "+response.message());
                if (response.isSuccessful()){
                    Log.d(TAG, "onStart : onResponse : successful");
                    NoNCR = response.body().getNcrNo();
                    noRegIns = response.body().getUserinspector().getInspectorNumber();
                    TanggalTerbit = Formatter.format(response.body().getNcrData().getPublishDate());
                    NamaProses = response.body().getNcrData().getProcessName();
                    NamaProyek = response.body().getNcrData().getProject().getProjectDescription();
                    KodeProyek = response.body().getNcrData().getProject().getProjectCode();
                    LokasiKet = response.body().getNcrData().getDivision().getDivisionName();
                    AcuanPem = response.body().getNcrData().getReferenceInspection();
                    UraianKet = response.body().getNcrData().getDescriptionIncompatibility();
                    DisposisiIns = response.body().getNcrData().getDispositionInspector().getDisposisiDescription();
                    TargetPeny = Formatter.format(response.body().getNcrData().getCompletionTarget());
                    // UraianAkarM
                    TindakanPerb = response.body().getRespData().getCorrectiveAct();
                    TindakanPenc = response.body().getRespData().getPreventiveAct();
                    DisposisiUnit = response.body().getRespData().getUnitDisposition().getDescription();

                    isiTV11.setText(": "+NoNCR);
                    isiTV12.setText(": "+noRegIns);
                    isiTV13.setText(": "+TanggalTerbit);
                    isiTV14.setText(": "+NamaProses);
                    isiTV15.setText(": "+KodeProyek+"/"+NamaProyek);
                    isiTV16.setText(": "+LokasiKet);
                    isiTV17.setText(": "+AcuanPem);
                    isiTV18.setText(": "+UraianKet);
                    isiTV19.setText(": "+DisposisiIns);
                    isiTV110.setText(": "+TargetPeny);

                    isi21.setText(": "+NoNCR);
                    isi22.setText(": "+noRegIns);
                    isi24.setText(": "+TindakanPerb);
                    isi25.setText(": "+TindakanPenc);
                    isi26.setText(": "+DisposisiUnit);
                }
            }

            @Override
            public void onFailure(Call<NCRVerification> call, Throwable t) {
                Log.d(TAG,"onStart : onFailure : " + t.toString());
                Toast.makeText(VerifikasiVerNCRActivity.this, "Internet failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onClickBtn1(View view) {


        Intent intent = new Intent(VerifikasiVerNCRActivity.this,VerifikasiVerNCR2Activity.class);
        intent.putExtra("position",idPosition);
        startActivity(intent);
    }

}
