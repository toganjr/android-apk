package com.example.user.navbartemplatejava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ViewItemNcrReg extends AppCompatActivity {
    private ArrayList<exampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private AdapterNcrRegistration mAdapterNcrRegistration;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_content_regncr);
    }


}
