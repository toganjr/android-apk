package com.example.user.navbartemplatejava;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.navbartemplatejava.data.NcrRegistration;

import java.util.ArrayList;
import java.util.List;

public class AdapterNcrRegistration extends RecyclerView.Adapter<AdapterNcrRegistration.MyViewHolder>  {
    public static final String TAG = AdapterNcrRegistration.class.getSimpleName();

    private List <NcrRegistration> mListNcrDisplay;
    private ArrayList<exampleItem> mExampleItems;

    //Constructor for NcrRegistration
    public AdapterNcrRegistration( List<NcrRegistration> ListNcrDisplay){
        mListNcrDisplay = ListNcrDisplay;
    }

    //constructor for Example Item RecycleView
    public AdapterNcrRegistration(ArrayList<exampleItem> ExampleItem){
        mExampleItems = ExampleItem;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ncr_registration, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

//for Test Example RecycleView
        holder.mNoNcr.setText(mExampleItems.get(position).getText1());
        holder.mNamaProses.setText(mExampleItems.get(position).getText2());

        holder.mNoNcr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ViewItemNcrReg.class);

                v.getContext().startActivity(intent);
            }
        });

//for Get No NCR and Name Process
//
//        holder.mNoNcr.setText(mListNcrDisplay.get(position).getNoRegNcr());
//        holder.mNamaProses.setText(mListNcrDisplay.get(position).getProcessName());
//        holder.mNoNcr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(),ViewItemNcrReg.class);
//                v.getContext().startActivity(intent);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        Log.d(TAG,"Size Recycle : " );
        //return mListNcrDisplay.size();
        return mExampleItems.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mNoNcr;
        public TextView mNamaProses;



        public MyViewHolder(View itemView) {
            super(itemView);
            mNoNcr = itemView.findViewById(R.id.displayNoNcr);
            mNamaProses = itemView.findViewById(R.id.displayNamaProses);




        }
    }








}
