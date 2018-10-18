package com.example.user.navbartemplatejava.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.navbartemplatejava.InkaApp;
import com.example.user.navbartemplatejava.R;
import com.example.user.navbartemplatejava.VerifikasiVerNCRActivity;
import com.example.user.navbartemplatejava.data.NcrRegistration;
import com.example.user.navbartemplatejava.data.network.ApiClient;
import com.example.user.navbartemplatejava.data.network.InspecVerifInterface;
import com.example.user.navbartemplatejava.data.network.response.NCRVerification;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;

public class DataNCRFragment extends Fragment {

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

    String NoNCR;
    String noRegIns;
    String TanggalTerbit;
    String NamaProses;
    String NamaKodeProyek;
    String LokasiKet;
    String AcuanPem;
    String UraianKet;
    String DisposisiIns;
    String TargetPeny;
    String TindakanPerb;
    private List<NcrRegistration> mListNcrDisplay;

    //Constructor for NcrRegistration


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_data_ncr, null);

      //  VerifikasiVerNCRActivity activity = (VerifikasiVerNCRActivity) getActivity();

   /*     NoNCR = ": "+activity.getNoNCR();
        noRegIns = ": "+activity.getNoRegIns();
        TanggalTerbit = ": "+activity.getTanggalTerbit();
        NamaProses = ": "+activity.getNamaProses();
        NamaKodeProyek = ": "+activity.getKodeProyek()+"/"+activity.getNamaProyek();
        LokasiKet = ": "+activity.getLokasiKet();
        AcuanPem = ": "+activity.getAcuanPem();
        UraianKet = ": "+activity.getUraianKet();
        DisposisiIns = ": "+activity.getDisposisiIns();
        TargetPeny = ": "+activity.getTargetPeny();
*/
        DataTindakLanjutFragment dataTindakLanjutFragment = new DataTindakLanjutFragment ();
        Bundle args = new Bundle();
        getFragmentManager().beginTransaction()
                .replace(R.id.LayoutFragment2, dataTindakLanjutFragment )
                .commit();


        isiTV11 = (TextView)view.findViewById(R.id.isiTV11);
        isiTV12 = (TextView)view.findViewById(R.id.isiTV12);
        isiTV13 = (TextView)view.findViewById(R.id.isiTV13);
        isiTV14 = (TextView)view.findViewById(R.id.isiTV14);
        isiTV15 = (TextView)view.findViewById(R.id.isiTV15);
        isiTV16 = (TextView)view.findViewById(R.id.isiTV16);
        isiTV17 = (TextView)view.findViewById(R.id.isiTV17);
        isiTV18 = (TextView)view.findViewById(R.id.isiTV18);
        isiTV19 = (TextView)view.findViewById(R.id.isiTV19);
        isiTV110 = (TextView)view.findViewById(R.id.isiTV110);

        isiTV11.setText(NoNCR);
        isiTV12.setText(noRegIns);
        isiTV13.setText(TanggalTerbit);
        isiTV14.setText(NamaProses);
        isiTV15.setText(NamaKodeProyek);
        isiTV16.setText(LokasiKet);
        isiTV17.setText(AcuanPem);
        isiTV18.setText(UraianKet);
        isiTV19.setText(DisposisiIns);
        isiTV110.setText(TargetPeny);


        return view;
    }

}
