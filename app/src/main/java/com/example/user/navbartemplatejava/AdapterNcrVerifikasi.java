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

import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterNcrVerifikasi extends RecyclerView.Adapter<AdapterNcrVerifikasi.MyViewHolder>  {
    public static final String TAG = AdapterNcrVerifikasi.class.getSimpleName();

    private List <NcrRegistration> mListNcrDisplay;

    //Constructor for NcrRegistration
    public AdapterNcrVerifikasi(List<NcrRegistration> ListNcrDisplay){
        mListNcrDisplay = ListNcrDisplay;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ncr_registration, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final SimpleDateFormat Formatter = new SimpleDateFormat("dd-MM-yyyy");
        holder.mNoNcr.setText("No. NCR : "+mListNcrDisplay.get(position).getNoRegNcr());
        holder.mNamaProses.setText("Nama Proses : "+mListNcrDisplay.get(position).getProcessName());
        holder.mNoNcr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ViewItemNcrVer.class);
                intent.putExtra("No Registration NCR",mListNcrDisplay.get(position).getNoRegNcr());
                intent.putExtra("Nama Proses",mListNcrDisplay.get(position).getProcessName());
                intent.putExtra("Nama Tester",mListNcrDisplay.get(position).getUser().getName());
                intent.putExtra("Nama Vendor",mListNcrDisplay.get(position).getVendorName());
                intent.putExtra("Uraian Ket",mListNcrDisplay.get(position).getDescriptionIncompatibility());
                intent.putExtra("Kategori Ket",mListNcrDisplay.get(position).getIncompatibilityCategoryId());
                intent.putExtra("PIC",mListNcrDisplay.get(position).getPersonInCharge());
                intent.putExtra("Tanggal Terbit",Formatter.format(mListNcrDisplay.get(position).getPublishDate()));
                intent.putExtra("Unit",mListNcrDisplay.get(position).getDivision().getDivisionName());
                intent.putExtra("Nama Projek",mListNcrDisplay.get(position).getProject().getProjectDescription());
                intent.putExtra("Projek ID",String.valueOf(mListNcrDisplay.get(position).getProject().getId()));
                intent.putExtra("Projek Kode",mListNcrDisplay.get(position).getProject().getProjectCode());
                intent.putExtra("position",mListNcrDisplay.get(position).getId());
                intent.putExtra("Latitude",mListNcrDisplay.get(position).getLatitude());
                intent.putExtra("Longitude",mListNcrDisplay.get(position).getLongitude());
                v.getContext().startActivity(intent);
            }
        });
        holder.mNamaProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ViewItemNcrVer.class);
                intent.putExtra("No Registration NCR",mListNcrDisplay.get(position).getNoRegNcr());
                intent.putExtra("Nama Proses",mListNcrDisplay.get(position).getProcessName());
                intent.putExtra("Nama Tester",mListNcrDisplay.get(position).getUser().getName());
                intent.putExtra("Nama Vendor",mListNcrDisplay.get(position).getVendorName());
                intent.putExtra("Uraian Ket",mListNcrDisplay.get(position).getDescriptionIncompatibility());
                intent.putExtra("Kategori Ket",mListNcrDisplay.get(position).getIncompatibilityCategoryId());
                intent.putExtra("PIC",mListNcrDisplay.get(position).getPersonInCharge());
                intent.putExtra("Tanggal Terbit",Formatter.format(mListNcrDisplay.get(position).getPublishDate()));
                intent.putExtra("Unit",mListNcrDisplay.get(position).getDivision().getDivisionName());
                intent.putExtra("Nama Projek",mListNcrDisplay.get(position).getProject().getProjectDescription());
                intent.putExtra("Projek ID",String.valueOf(mListNcrDisplay.get(position).getProject().getId()));
                intent.putExtra("Projek Kode",mListNcrDisplay.get(position).getProject().getProjectCode());
                intent.putExtra("position",mListNcrDisplay.get(position).getId());
                intent.putExtra("Latitude",mListNcrDisplay.get(position).getLatitude());
                intent.putExtra("Longitude",mListNcrDisplay.get(position).getLongitude());
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        Log.d(TAG,"Size Recycle : " );
        return mListNcrDisplay.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mNoNcr;
        public TextView mNamaProses;
        public TextView mNamaInspector;
        public TextView mUnit;
        public TextView mKodeProjek;



        public MyViewHolder(View itemView) {
            super(itemView);
            mNoNcr = itemView.findViewById(R.id.displayNoNcr);
            mNamaProses = itemView.findViewById(R.id.displayNamaProses);
            mNamaInspector = itemView.findViewById(R.id.getNamaInspector);
            mUnit = itemView.findViewById(R.id.getUnit);
            mKodeProjek = itemView.findViewById(R.id.getKodeProjek);
        }
    }








}
