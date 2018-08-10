package com.example.user.navbartemplatejava;

import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@NonReusable
@Layout(R.layout.drawer_navigation_main)
public class DrawerItemActivity {
    @View(R.id.profileImageView)
    private ImageView profileImage;

    @View(R.id.nameTxt)
    private TextView nameTxt;

    @View(R.id.nipTxt)
    private TextView nipTxt;

    @Resolve
    private void onResolved() {
        nameTxt.setText("Name");
        nipTxt.setText("NIP");
    }
}
