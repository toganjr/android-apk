package com.example.user.navbartemplatejava.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.navbartemplatejava.R;
import com.example.user.navbartemplatejava.VerifikasiVerNCRActivity;

public class DataTindakLanjutFragment extends Fragment{

    TextView isi21;
    TextView isi22;
    TextView isi23;
    TextView isi24;
    TextView isi25;
    TextView isi26;

    private String NoNCR;
    private String noRegIns;
    private String UraianAkarM;
    private String TindakanPerb;
    private String TindakanPenc;
    private String DisposisiUnit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_datatindaklanjutncr, null);

    /*    VerifikasiVerNCRActivity activity2 = (VerifikasiVerNCRActivity) getActivity();

        Intent intent = getActivity().getIntent();


        NoNCR = ": "+activity2.getNoNCR();
        noRegIns = ": "+activity2.getNoRegIns();
        //UraianAkarM
        TindakanPerb = ": "+activity2.getTindakanPerb();
        TindakanPenc = ": "+activity2.getTindakanPenc();
        DisposisiUnit = ": "+activity2.getDisposisiUnit(); */

        isi21 = (TextView)view.findViewById(R.id.isiTV21);
        isi22 = (TextView)view.findViewById(R.id.isiTV22);
        isi23 = (TextView)view.findViewById(R.id.isiTV23);
        isi24 = (TextView)view.findViewById(R.id.isiTV24);
        isi25 = (TextView)view.findViewById(R.id.isiTV25);
        isi26 = (TextView)view.findViewById(R.id.isiTV26);

        isi21.setText(NoNCR);
        isi22.setText(noRegIns);
        isi24.setText(TindakanPerb);
        isi25.setText(TindakanPenc);
        isi26.setText(DisposisiUnit);

        return view;
    }

}
