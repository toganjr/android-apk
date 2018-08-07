package com.example.user.navbartemplatejava;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class addNCRActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE = 1;

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

    //register
    private Button registerncr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ncr_content_inc_sub);

        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new takePhoto());

        registerncr = (Button) findViewById(R.id.registerncr);
        registerncr.setOnClickListener(new register());

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        inputnoPO2 = findViewById(R.id.inputnoPO2);
        inputPIC = findViewById(R.id.inputPIC);
        inputvendor = findViewById(R.id.inputvendor);
        inputproduk = findViewById(R.id.inputproduk);
        inputtanggal_penyelesaian = findViewById(R.id.inputtanggal_penyelesaian);
        inputreport = findViewById(R.id.inputreport);

    }

    private boolean validatenoPO(){
        String noPO = inputnoPO2.getText().toString().trim();

        if (noPO.isEmpty()){
            inputnoPO2.setError("tidak boleh kosong");
            return false;
        } else if (noPO.length() != 8){
            inputnoPO2.setError("harus 8 digit");
            return false;
        } else {
            inputnoPO2.setError(null);
            return true;
        }
    }

    private boolean validatePIC(){
        String PIC = inputPIC.getText().toString().trim();

        if (PIC.isEmpty()) {
            inputPIC.setError("tidak boleh kosong");
            return false;
        } else {
            inputPIC.setError(null);
            return true;
        }
    }

    private boolean validatevendor(){
        String vendor = inputvendor.getText().toString().trim();

        if (vendor.isEmpty()){
            inputvendor.setError("tidak boleh kosong");
            return false;
        } else {
            inputvendor.setError(null);
            return true;
        }
    }

    private boolean validateproduk(){
        String produk = inputproduk.getText().toString().trim();

        if (produk.isEmpty()){
            inputproduk.setError("tidak boleh kosong");
            return false;
        } else {
            inputproduk.setError(null);
            return true;
        }
    }

    private boolean validatetanggal_penyelesaian(){
        String tanggal_penyelesaian = inputtanggal_penyelesaian.getText().toString().trim();

        if (tanggal_penyelesaian.isEmpty()){
            inputtanggal_penyelesaian.setError("tidak boleh kosong");
            return false;
        } else {
            inputtanggal_penyelesaian.setError(null);
            return true;
        }
    }

    private boolean validatereport(){
        String report = inputreport.getText().toString().trim();

        if (report.isEmpty()){
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
            if (!validatenoPO() | !validatePIC() | !validateproduk() | !validatereport() | !validatetanggal_penyelesaian() | !validatevendor()){
                return;
            }
        }
    }

    void getLocation(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);
        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null){
                double latti = location.getLatitude();
                double longi = location.getLongitude();
            }
        }
    }

    @Override
    public void onClick(View v){

    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_LOCATION:
                getLocation();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAN_REQUEST){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView2.setImageBitmap(bitmap);
            getLocation();
        }
    }

    class takePhoto implements  Button.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,CAN_REQUEST);
        }
    }

}
