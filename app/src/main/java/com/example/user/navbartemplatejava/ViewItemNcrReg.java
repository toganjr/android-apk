package com.example.user.navbartemplatejava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewItemNcrReg extends AppCompatActivity {
    private ArrayList<exampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private AdapterNcrRegistration mAdapterNcrRegistration;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView mNoRegNcrTxt;
    private TextView mNamaProsesTxt;
    private TextView mNamaInspector;
    private TextView mUnit;
    private TextView mKodeProjek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_content_regncr);
        mNoRegNcrTxt = findViewById(R.id.getNoNcr);
        mNamaProsesTxt = findViewById(R.id.getNamaProses);
        mNamaInspector = findViewById(R.id.getNamaInspector);
        mUnit = findViewById(R.id.getUnit);
        mKodeProjek = findViewById(R.id.getKodeProjek);


        mNoRegNcrTxt.setText(getIntent().getStringExtra("No Registration NCR"));
        mNamaProsesTxt.setText(getIntent().getStringExtra("Nama Proses"));



    }


}
