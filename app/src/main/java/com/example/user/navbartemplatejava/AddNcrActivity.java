package com.example.user.navbartemplatejava;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.user.navbartemplatejava.data.DispositionInspector;
import com.example.user.navbartemplatejava.data.DocRefDivision;
import com.example.user.navbartemplatejava.data.IncompatibilityCategory;
import com.example.user.navbartemplatejava.data.MasterProject;
import com.example.user.navbartemplatejava.data.network.response.AddFormResponse;
import com.example.user.navbartemplatejava.data.prefs.PreferencesHelper;
import com.example.user.navbartemplatejava.service.LocationService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddNcrActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int RESULT_LOAD_IMAGE = 1;
    public static final String TAG = AddNcrActivity.class.getSimpleName();
    Spinner spinner1;
    ArrayAdapter<CharSequence> adapter;

    //access location
    static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;

    //access camera
    ImageView imageView2;
    private static final int CAN_REQUEST = 1313;

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
    private Date tanggalPenyelesaian;
    private Uri filePath;
    //register
    private Button registerncr;
    AddFormResponse formData;
    private LocationService mLocationService;
    private boolean mLocationServiceBound = false;
    private Intent mLocationServiceIntent;
    PreferencesHelper mPrefs;
    private ServiceConnection mLocationServiceConnection = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected");
            LocationService.ServiceBinder binder = (LocationService.ServiceBinder) iBinder;
            mLocationService = binder.getService();
            mLocationServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected");
            mLocationServiceBound = false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.add_ncr_content_inc_sub);

        imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new takePhoto());

        registerncr = findViewById(R.id.registerncr);
        registerncr.setOnClickListener(new register());

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

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
        mLocationServiceIntent = new Intent(this, LocationService.class);
        formData = (AddFormResponse) getIntent().getSerializableExtra("data");
        initDateTimePickerDialog();
    }

    protected void initDateTimePickerDialog(){
        Log.d(TAG, "initDateTimePickerDialog");
        final SimpleDateFormat dateFormatterText = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());;
        mTanggalPenyelesaianDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.d(TAG, "onDateSet");
                dateTime.set(year, monthOfYear, dayOfMonth);
                tanggalPenyelesaian = dateTime.getTime();
                inputtanggal_penyelesaian.setText(dateFormatterText.format(dateTime.getTime()));
            }

        },  dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        setSpinners();
        if (!mLocationServiceBound){
            bindService(mLocationServiceIntent, mLocationServiceConnection, Context.BIND_AUTO_CREATE);
        }
        // Checking if service of feature asset has bound or not
        if (mLocationServiceBound && mLocationService.isAvailableLocation()) {
            Log.d(TAG, "Latitude: " + mLocationService.getLatitude());
            Log.d(TAG, "Longitude: " + mLocationService.getLongitude());
        }
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
        Log.d(TAG, "validatenoPO");
        String noPO = inputnoPO2.getText().toString().trim();

        if (noPO.isEmpty()) {
            inputnoPO2.setError("tidak boleh kosong");
            return false;
        } else if (noPO.length() != 8) {
            inputnoPO2.setError("harus 8 digit");
            return false;
        } else {
            inputnoPO2.setError(null);
            return true;
        }
    }

    private boolean validatePIC() {
        Log.d(TAG, "validatePIC");
        String PIC = inputPIC.getText().toString().trim();

        if (PIC.isEmpty()) {
            inputPIC.setError("tidak boleh kosong");
            return false;
        } else {
            inputPIC.setError(null);
            return true;
        }
    }

    private boolean validatevendor() {
        Log.d(TAG, "validatevendor");
        String vendor = inputvendor.getText().toString().trim();

        if (vendor.isEmpty()) {
            inputvendor.setError("tidak boleh kosong");
            return false;
        } else {
            inputvendor.setError(null);
            return true;
        }
    }

    private boolean validateproduk() {
        Log.d(TAG, "validateproduk");
        String produk = inputproduk.getText().toString().trim();

        if (produk.isEmpty()) {
            inputproduk.setError("tidak boleh kosong");
            return false;
        } else {
            inputproduk.setError(null);
            return true;
        }
    }

    private boolean validatetanggal_penyelesaian() {
        Log.d(TAG, "validatetanggal_penyelesaian");
        String tanggal_penyelesaian = inputtanggal_penyelesaian.getText().toString().trim();

        if (tanggal_penyelesaian.isEmpty()) {
            inputtanggal_penyelesaian.setError("tidak boleh kosong");
            return false;
        } else {
            inputtanggal_penyelesaian.setError(null);
            return true;
        }
    }

    private boolean validatereport() {
        Log.d(TAG, "validatereport");
        String report = inputreport.getText().toString().trim();

        if (report.isEmpty()) {
            inputreport.setError("tidak boleh kosong");
            return false;
        } else {
            inputreport.setError(null);
            return true;
        }
    }

    class register implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "register : onClick");
            if (!validatenoPO() | !validatePIC() | !validateproduk() | !validatereport() | !validatetanggal_penyelesaian() | !validatevendor()) {
//                postNcrRegister();
            }
        }
    }

//    public void postNcrRegister(){
//        Call<AddNcrResponse> call = ApiClient.getRetrofit().create(NcrRegInterface.class).addNcr(
//                inputproduk.getText().toString(),
//                ((MasterProject) mProjectIdSpinner.getSelectedItem()).getIdProject(),
//                inputvendor.getText().toString(),
//                ((DocRefDivision) mNoPoSpinner.getSelectedItem()).getId(),
//                inputnoPO2.getText().toString(),
//                inputreport.getText().toString(),
//                ((IncompatibilityCategory) mIncomSpinner.getSelectedItem()).getId(),
//                inputPIC.getText().toString(),
//                //foto
//                ((DispositionInspector) mDisposSpinner.getSelectedItem()).getId(),
//                tanggalPenyelesaian,
//                mLocationService.getLatitude(),
//                mLocationService.getLongitude(),
//                mPrefs.getUserSignInToken()
//        );
//        call.enqueue(new Callback<AddNcrResponse>() {
//            @Override
//            public void onResponse(Call<AddNcrResponse> call, Response<AddNcrResponse> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<AddNcrResponse> call, Throwable t) {
//
//            }
//        });
//    }

    public void getLocation() {
        Log.d(TAG, "getLocation");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "getLocation : requestPermissions");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Log.d(TAG, "getLocation : permitted");
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.inputtanggal_penyelesaian:
                mTanggalPenyelesaianDialog.show();
                break;
        }
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionResult");
        switch (requestCode) {
            case REQUEST_LOCATION:
                getLocation();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAN_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView2.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class takePhoto implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), CAN_REQUEST);
        }
    }
}
