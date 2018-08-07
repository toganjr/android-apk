package com.example.user.navbartemplatejava;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class qqq extends AppCompatActivity {
    private ArrayList<exampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Button btnAdd;


    public void changeItem(int position, String text){
        mExampleList.get(position).gonext(text);
        mAdapter.notifyItemChanged(position);
    }

    public void gonext(int position){
        Intent intent =  new Intent(this, viewregNCRActivity.class);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regncr);
        createExampleList();
        buildRecyclerView();

      //  btnAdd = (Button) findViewById(R.id.button);
     //   btnAdd.setOnClickListener(new regNCRActivity.Addncr());
    }


    class Addncr implements  Button.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent2 =  new Intent(v.getContext(), addNCRActivity.class);
            startActivityForResult(intent2,0);
        }
    }


    public void createExampleList(){
        mExampleList = new ArrayList<>();
        mExampleList.add(new exampleItem("XASD-123-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA"));
        mExampleList.add(new exampleItem("XASD-123-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA"));
        mExampleList.add(new exampleItem("XASD-123-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA"));
        mExampleList.add(new exampleItem("XASD-123-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA"));
        mExampleList.add(new exampleItem("XASD-123-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA"));
        mExampleList.add(new exampleItem("XASD-123-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA"));
        mExampleList.add(new exampleItem("XASD-123-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA"));
        mExampleList.add(new exampleItem("XASD-123-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA"));
        mExampleList.add(new exampleItem("XASD-123-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA"));
        mExampleList.add(new exampleItem("XASD-123-12/XY/2018","PERUBAHAN PADA JADWAL HALUAN TUNDA"));
    }



    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Adapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //changeItem(position,"Clicked");
                gonext(position);
            }
        });
    }
}
