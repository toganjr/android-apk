package com.example.user.navbartemplatejava;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.navbartemplatejava.R;
import com.example.user.navbartemplatejava.data.DispositionInspector;
import com.example.user.navbartemplatejava.data.DocRefDivision;
import com.example.user.navbartemplatejava.data.IncompatibilityCategory;
import com.example.user.navbartemplatejava.data.MasterProject;
import com.example.user.navbartemplatejava.data.VerificationResult;
import com.example.user.navbartemplatejava.data.network.ApiClient;
import com.example.user.navbartemplatejava.data.network.InspecVerifInterface;
import com.example.user.navbartemplatejava.data.network.NcrRegInterface;
import com.example.user.navbartemplatejava.data.network.response.AddFormResponse;
import com.example.user.navbartemplatejava.data.network.response.AddInspecFormResponse;
import com.example.user.navbartemplatejava.data.network.response.AddNcrResponse;
import com.example.user.navbartemplatejava.data.network.response.BrowseNcrResponse;
import com.example.user.navbartemplatejava.data.network.response.VerNcr;
import com.example.user.navbartemplatejava.data.prefs.PreferencesHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    public class VerifikasiVerNCR2Activity extends AppCompatActivity {
        private Integer idPosition;
        private String Description;
        private String token;

        AddInspecFormResponse formData;
        Context mContext;

        PreferencesHelper mPreferencesHelper;

        EditText isiTV32;
        Spinner spinner;
        Button btnSimpan;

        InspecVerifInterface mNcrVerInterface;

        @Override
        protected void onCreate(Bundle SavedInstance) {

            mPreferencesHelper = ((InkaApp) getApplication()).getPrefs();
            mNcrVerInterface = ApiClient.getRetrofit().create(InspecVerifInterface.class);
            mContext = this;
            idPosition = getIntent().getIntExtra("position",0);

            super.onCreate(SavedInstance);
            setContentView(R.layout.content_verifikasi_ver_ncr_2);
            isiTV32 = (EditText) findViewById(R.id.isi32);
            btnSimpan = (Button) findViewById(R.id.btnsimpanver);
            spinner = (Spinner) findViewById(R.id.isi31);
            token = mPreferencesHelper.getUserSignInToken();
            formData = (AddInspecFormResponse) getIntent().getSerializableExtra("data");

            Log.d("TOKEN : ", token);

            setSpinners();
            btnSimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SimpanVer();
                }
            });
        }

        public void SimpanVer(){
            Call<VerNcr> call = ApiClient.getRetrofit().create(InspecVerifInterface.class).addInspec(
                    idPosition,
                    isiTV32.getText().toString(),
                    spinner.getSelectedItemPosition()+1,
                    token
            );
            call.enqueue(new Callback<VerNcr>() {
                @Override
                public void onResponse(@NonNull Call<VerNcr> call, @NonNull Response<VerNcr> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(VerifikasiVerNCR2Activity.this, "Verification Succeed", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<VerNcr> call, @NonNull Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(VerifikasiVerNCR2Activity.this, "Internet failed", Toast.LENGTH_SHORT).show();
                }
            });
    }

        private void setSpinners(){
            Call<AddInspecFormResponse> call = mNcrVerInterface.addInspecForm(mPreferencesHelper.getUserSignInToken());
            call.enqueue(new Callback<AddInspecFormResponse>() {
                @Override
                public void onResponse(Call<AddInspecFormResponse> call, Response<AddInspecFormResponse> response) {
                    if (response.isSuccessful()){
                        List <VerificationResult> verifItems = response.body().getVerificationresult();
                        List<String> listSpinner = new ArrayList<>();
                        for (int i = 0; i < verifItems.size(); i++){
                            listSpinner.add((verifItems.get(i).getDescription()));
                            listSpinner.get(i);
                            Log.d("CONNECT 3 : ", listSpinner.get(i));
                        }
                        // Set hasil result json ke dalam adapter spinner
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                                android.R.layout.simple_spinner_item, listSpinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<AddInspecFormResponse> call, Throwable t) {
                    Toast.makeText(VerifikasiVerNCR2Activity.this, "Internet failed", Toast.LENGTH_SHORT).show();
                }
            });
}}
