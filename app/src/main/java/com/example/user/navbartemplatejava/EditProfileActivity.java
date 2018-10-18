package com.example.user.navbartemplatejava;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.navbartemplatejava.data.network.ApiClient;
import com.example.user.navbartemplatejava.data.network.AuthInterface;
import com.example.user.navbartemplatejava.data.network.response.LoginResponse;
import com.example.user.navbartemplatejava.data.prefs.PreferencesHelper;
import com.example.user.navbartemplatejava.util.RealPathUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.user.navbartemplatejava.AddNcrINCSUBActivity.REQUEST_READ;

public class EditProfileActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    public static final String TAG = EditProfileActivity.class.getSimpleName();

    AuthInterface mUserAuthInterface;
    PreferencesHelper mPreferencesHelper;

    private static final int CAN_REQUEST = 1313;
    private Uri tempPath;
    private String filePath;

    private int idPosition;

    Button btnSelect;
    Button btnSave;
    TextView nameFile;
    EditText isiPassword;
    EditText isiConfrmPassword;

    String password;
    String confirmpassword;

    PreferencesHelper mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_profile);

        mPrefs = ((InkaApp) getApplication()).getPrefs();

        btnSelect = (Button)findViewById(R.id.btnSelectFile);
        btnSave = (Button)findViewById(R.id.btnSaveProfile);
        nameFile = (TextView)findViewById(R.id.isiFile);
        isiPassword = (EditText)findViewById(R.id.isi_password);
        isiConfrmPassword = (EditText)findViewById(R.id.isi_confrmpassword);

        password = isiPassword.getText().toString().trim();
        confirmpassword = isiConfrmPassword.getText().toString().trim();

        idPosition = getIntent().getIntExtra("position",0);
        btnSelect.setOnClickListener(new takePhoto());
        btnSave.setOnClickListener(new upload());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult");
        if (requestCode == CAN_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            tempPath = data.getData();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onActivityResult : asking permission");
                ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, REQUEST_READ);
            }else{
                Log.d(TAG, "onActivityResult : permission granted");
                filePath = RealPathUtils.getRealPathFromURI_API19(this, tempPath);
            }
            Log.d(TAG, "onActivityResult : convert bitmap");
            String filename = getFileName(tempPath);
            nameFile.setText(filename);
        }
    }

    private boolean validateImage(){
        if (filePath == null){
            Toast.makeText(this, "Please choose image", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validatePassword(){
        if (password.equals(confirmpassword)) {
            return true;
        } else {
            Toast.makeText(this, "Password anda tidak sama", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    class upload implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "register : onClick");
            if (validateImage() & validatePassword()) {
                postNcrRegister();
            }
        }
    }

    public void postNcrRegister(){
        Log.d(TAG, "postNcrRegister");
        Log.d(TAG, "filePath String : "+filePath);
        File file = new File(filePath);
        if (file.exists()){
            Log.d(TAG, "postNcrRegister : file exists");
        }else{
            Log.d(TAG, "postNcrRegister : file doesnt exists");
        }
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part image = MultipartBody.Part.createFormData("file_bukti[]", file.getName(), reqFile);
        Call<LoginResponse> call = ApiClient.getRetrofit().create(AuthInterface.class).editProfile(
                password,
                confirmpassword,
                image,
                mPrefs.getUserID(),
                mPrefs.getUserSignInToken()
        );
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                Log.d(TAG, "Edit profile : onResponse : " + response.code() + " " + response.message());
                Toast.makeText(EditProfileActivity.this, response.code() + " "+ response.message(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){
                    Log.d(TAG, "Edit profile : onResponse : successful");
                    Toast.makeText(EditProfileActivity.this, response.code() + " "+ response.message(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "postNcrRegister : onFailure : " + t.getMessage());
                t.printStackTrace();
                Toast.makeText(EditProfileActivity.this, "Internet failed", Toast.LENGTH_SHORT).show();
            }
        });
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

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

}
