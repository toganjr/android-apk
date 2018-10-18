package com.example.user.navbartemplatejava.Tab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.example.user.navbartemplatejava.Fragment.DataNCRFragment;
import com.example.user.navbartemplatejava.Fragment.DataTindakLanjutFragment;
import com.example.user.navbartemplatejava.Fragment.VerifikasiFragment;
import com.example.user.navbartemplatejava.R;


public class MyAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] titles ={"A","B","C"};
    int[] icon = new int[]{R.drawable.ic_assignment_black_24dp,R.drawable.ic_assignment_turned_in_black_24dp,R.drawable.ic_assignment_late_black_24dp};
    private int heightIcon;

    public MyAdapter(FragmentManager fm, Context c){
        super(fm);
        mContext = c;
        double scale = c.getResources().getDisplayMetrics().density;
        heightIcon=(int)(50*scale+0.5f);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag= null;

        if(position ==0){
            frag = new DataNCRFragment();
        }else if(position == 1){
            frag = new DataTindakLanjutFragment();
        }else if(position == 2){
            frag = new VerifikasiFragment();
        }

        Bundle b = new Bundle();
        b.putInt("position", position);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    public CharSequence getPageTitle(int position){
        Drawable d = mContext.getResources().getDrawable(icon[position]);
        d.setBounds(30,20,heightIcon,heightIcon);
        ImageSpan is = new ImageSpan(d);

        SpannableString sp = new SpannableString(" ");
        sp.setSpan(is,0,sp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sp;
    }

}