package com.example.user.navbartemplatejava;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.navbartemplatejava.data.DispositionInspector;
import com.example.user.navbartemplatejava.data.DocRefDivision;
import com.example.user.navbartemplatejava.data.IncompatibilityCategory;
import com.example.user.navbartemplatejava.data.MasterProject;
import com.example.user.navbartemplatejava.data.NcrRegistration;
import com.example.user.navbartemplatejava.data.network.ApiClient;
import com.example.user.navbartemplatejava.data.network.NcrRegInterface;
import com.example.user.navbartemplatejava.data.network.response.AddFormResponse;
import com.example.user.navbartemplatejava.data.network.response.AddNcrResponse;
import com.example.user.navbartemplatejava.data.network.response.UpdateNcrResponse;
import com.example.user.navbartemplatejava.data.prefs.PreferencesHelper;
import com.example.user.navbartemplatejava.util.NetworkUtils;
import com.example.user.navbartemplatejava.util.RealPathUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditNcrINCSUBActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int RESULT_LOAD_IMAGE = 1;
    public static final String TAG = EditNcrINCSUBActivity.class.getSimpleName();
    Spinner spinner1;
    ArrayAdapter<CharSequence> adapter;

    //seterror fitur
    private EditText inputnoPO2;
    private EditText inputPIC;
    private EditText inputvendor;
    private EditText inputproduk;
    private EditText inputtanggal_penyelesaian;
    private EditText inputreport;
    private Spinner mProjectIdSpinner;
    private Spinner mNoPoSpinner;
    private Spinner mIncomSpinner;
    private Spinner mDisposSpinner;
    private Calendar dateTime = Calendar.getInstance();
    private DatePickerDialog mTanggalPenyelesaianDialog;
    private String tanggalPenyelesaian;
    private Uri tempPath;
    private String filePath;
    private Integer idPosition;

    private String NamaProses;
    private String NamaVendor;
    private String UraianKet;
    private String PIC;
    //register
    private Button editncr;
    AddFormResponse formData;
    PreferencesHelper mPrefs;
    Double latitude;
    Double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.edit_ncr_content_inc_sub);

        editncr = findViewById(R.id.editncr);
        editncr.setOnClickListener(new register());

        inputnoPO2 = findViewById(R.id.inputnoPO2);
        inputPIC = findViewById(R.id.inputPIC);
        inputvendor = findViewById(R.id.inputvendor);
        inputproduk = findViewById(R.id.inputproduk);
        mProjectIdSpinner = findViewById(R.id.proyek);
        mNoPoSpinner = findViewById(R.id.noPO1);
        mIncomSpinner = findViewById(R.id.kat_ketidaksuaian);
        mDisposSpinner = findViewById(R.id.dis_inspektor);
        inputtanggal_penyelesaian = findViewById(R.id.inputtanggal_penyelesaian);
        inputtanggal_penyelesaian.setInputType(InputType.TYPE_NULL);
        mPrefs = ((InkaApp) getApplication()).getPrefs();
        inputreport = findViewById(R.id.inputreport);
        inputtanggal_penyelesaian.setOnClickListener(this);
        formData = (AddFormResponse) getIntent().getSerializableExtra("data");
        initDateTimePickerDialog();
        if (!NetworkUtils.isGpsAvailable(this) || !NetworkUtils.isNetworkConnected(this)) {
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setMessage("Gps is disabled & No connection or connection missing")
                    .setPositiveButton("Setting", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .create()
                    .show();
        }

        idPosition = getIntent().getIntExtra("position",0);
        NamaProses = getIntent().getStringExtra("Nama Proses");
        NamaVendor = getIntent().getStringExtra("Nama Vendor");
        UraianKet = getIntent().getStringExtra("Uraian Ket");
        PIC = getIntent().getStringExtra("PIC");
        latitude = getIntent().getDoubleExtra("Latitude",0);
        longitude = getIntent().getDoubleExtra("Longitude",0);

        inputproduk.setText(NamaProses);
        inputvendor.setText(NamaVendor);
        inputreport.setText(UraianKet);
        inputPIC.setText(PIC);

    }

    protected void initDateTimePickerDialog(){
        Log.d(TAG, "initDateTimePickerDialog");
        final SimpleDateFormat dateFormatterText = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        final SimpleDateFormat jsonFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        mTanggalPenyelesaianDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.d(TAG, "onDateSet");
                dateTime.set(year, monthOfYear, dayOfMonth);
                tanggalPenyelesaian = jsonFormat.format(dateTime.getTime());
                inputtanggal_penyelesaian.setText(dateFormatterText.format(dateTime.getTime()));
            }

        },  dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        setSpinners();
    }

    protected void setSpinners(){
        Log.d(TAG, "setSpinners");
        List<MasterProject> projectIds = new ArrayList<>();
        List<DocRefDivision> noPo = new ArrayList<>();
        List<IncompatibilityCategory> categories = new ArrayList<>();
        List<DispositionInspector> inspectors = new ArrayList<>();

        for(MasterProject m : formData.getMasterProjects()){
            projectIds.add(m);
        }
        for (DocRefDivision d : formData.getDocDivisions()){
            noPo.add(d);
        }
        for (IncompatibilityCategory i : formData.getCategories()){
            categories.add(i);
        }
        for (DispositionInspector d : formData.getDispositions()){
            inspectors.add(d);
        }

        ArrayAdapter<MasterProject> mProjectIdSpinnerAdapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_spinner_item,
                projectIds);
        ArrayAdapter<DocRefDivision> mNoPoSpinnerAdapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_spinner_item,
                noPo);
        ArrayAdapter<IncompatibilityCategory> mIncomSpinnerAdapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_spinner_item,
                categories);
        ArrayAdapter<DispositionInspector> mDisposSpinnerAdapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_spinner_item,
                inspectors);

        mProjectIdSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mNoPoSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mIncomSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDisposSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mProjectIdSpinner.setAdapter(mProjectIdSpinnerAdapter);
        mNoPoSpinner.setAdapter(mNoPoSpinnerAdapter);
        mIncomSpinner.setAdapter(mIncomSpinnerAdapter);
        mDisposSpinner.setAdapter(mDisposSpinnerAdapter);
    }

    private boolean validatenoPO() {
        String noPO = inputnoPO2.getText().toString().trim();

        if (noPO.isEmpty()) {
            inputnoPO2.setError("tidak boleh kosong");
            Log.d(TAG, "validatenoPO : false");
            return false;
        } else if (noPO.length() != 8) {
            inputnoPO2.setError("harus 8 digit");
            Log.d(TAG, "validatenoPO : false");
            return false;
        } else {
            inputnoPO2.setError(null);
            Log.d(TAG, "validatenoPO : true");
            return true;
        }
    }

    private boolean validatePIC() {
        String PIC = inputPIC.getText().toString().trim();

        if (PIC.isEmpty()) {
            inputPIC.setError("tidak boleh kosong");
            Log.d(TAG, "validatePIC : false");
            return false;
        } else {
            inputPIC.setError(null);
            Log.d(TAG, "validatePIC : true");
            return true;
        }
    }

    private boolean validatevendor() {
        String vendor = inputvendor.getText().toString().trim();

        if (vendor.isEmpty()) {
            inputvendor.setError("tidak boleh kosong");
            Log.d(TAG, "validatevendor : false");
            return false;
        } else {
            inputvendor.setError(null);
            Log.d(TAG, "validatevendor : true");
            return true;
        }
    }

    private boolean validateproduk() {
        String produk = inputproduk.getText().toString().trim();

        if (produk.isEmpty()) {
            inputproduk.setError("tidak boleh kosong");
            Log.d(TAG, "validateproduk : false");
            return false;
        } else {
            inputproduk.setError(null);
            Log.d(TAG, "validateproduk : true");
            return true;
        }
    }

    private boolean validatetanggal_penyelesaian() {
        String tanggal_penyelesaian = inputtanggal_penyelesaian.getText().toString().trim();

        if (tanggal_penyelesaian.isEmpty()) {
            inputtanggal_penyelesaian.setError("tidak boleh kosong");
            Log.d(TAG, "validatetanggal_penyelesaian : false");
            return false;
        } else {
            inputtanggal_penyelesaian.setError(null);
            Log.d(TAG, "validatetanggal_penyelesaian : true");
            return true;
        }
    }

    private boolean validatereport() {
        String report = inputreport.getText().toString().trim();

        if (report.isEmpty()) {
            inputreport.setError("tidak boleh kosong");
            Log.d(TAG, "validatereport : false");
            return false;
        } else {
            inputreport.setError(null);
            Log.d(TAG, "validatereport : true");
            return true;
        }
    }


    class register implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "register : onClick");
            if (validatenoPO() & validatePIC() & validateproduk() &
                    validatereport() & validatetanggal_penyelesaian() &
                    validatevendor()) {
                postNcrRegister();
            }
        }
    }

    public void postNcrRegister(){
        Log.d(TAG, "editNcrRegister");
        Call<UpdateNcrResponse> call = ApiClient.getRetrofit().create(NcrRegInterface.class).updateNcr(
                inputproduk.getText().toString(),
                ((MasterProject) mProjectIdSpinner.getSelectedItem()).getIdProject(),
                inputvendor.getText().toString(),
                ((DocRefDivision) mNoPoSpinner.getSelectedItem()).getId(),
                inputnoPO2.getText().toString(),
                inputreport.getText().toString(),
                ((IncompatibilityCategory) mIncomSpinner.getSelectedItem()).getId(),
                inputPIC.getText().toString(),
                ((DispositionInspector) mDisposSpinner.getSelectedItem()).getId(),
                tanggalPenyelesaian,
                latitude,
                longitude,
                idPosition,
                mPrefs.getUserSignInToken()
        );
        call.enqueue(new Callback<UpdateNcrResponse>() {
            @Override
            public void onResponse(@NonNull Call<UpdateNcrResponse> call, @NonNull Response<UpdateNcrResponse> response) {
                Log.d(TAG, "editNcrRegister : onResponse : " + response.code() + " " + response.message());
                Toast.makeText(EditNcrINCSUBActivity.this, response.code() + " "+ response.message(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){
                    Log.d(TAG, "editNcrRegister : onResponse : successful");
                    Toast.makeText(EditNcrINCSUBActivity.this, response.code() + " "+ response.message(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UpdateNcrResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "editNcrRegister : onFailure : " + t.getMessage());
                t.printStackTrace();
                Toast.makeText(EditNcrINCSUBActivity.this, "Internet failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.inputtanggal_penyelesaian:
                mTanggalPenyelesaianDialog.show();
                break;
        }
    }

}

