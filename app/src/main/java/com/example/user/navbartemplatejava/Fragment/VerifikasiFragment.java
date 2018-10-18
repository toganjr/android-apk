package com.example.user.navbartemplatejava.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import com.example.user.navbartemplatejava.data.network.ApiClient;
import com.example.user.navbartemplatejava.data.network.InspecVerifInterface;
import com.example.user.navbartemplatejava.data.network.response.VerNcr;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifikasiFragment extends Fragment {
    private Integer idPosition;
    private String Description;
    private String token;

    EditText isiTV32;
    Spinner spinner;
    Button btnSimpan;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.content_verifikasi_ver_ncr_2, container, false);

    //    VerifikasiVerNCRActivity activity3 = (VerifikasiVerNCRActivity) getActivity();

        isiTV32 = (EditText)v.findViewById(R.id.isi32);
        btnSimpan = (Button)v.findViewById(R.id.btnsimpanver);

   //     idPosition = activity3.getIdPosition();
    //    token = activity3.getToken();

        String [] values =
                {"Efektif","Tidak Efektif",};
        spinner = (Spinner) v.findViewById(R.id.isi31);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpanVer();
            }
        });
        Log.d("VER ID POSITION : ", String.valueOf(idPosition));
        Log.d("VER DESCRIPTION : ", isiTV32.getText().toString());
        Log.d("VER SPIN", String.valueOf(spinner.getSelectedItemPosition()+1));
        Log.d("VER TOKEN ", token);

        return v;


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
                    Toast.makeText(getContext(), response.code() + " "+ response.message(), Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<VerNcr> call, @NonNull Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
