package com.example.user.navbartemplatejava;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Layout(R.layout.drawer_item_navigation)
public class DrawerMenuActivity {

    public static final int DRAWER_MENU_ITEM_PROFILE = 1;
    public static final int DRAWER_MENU_ITEM_NCR_REGISTRATION = 2;
    public static final int DRAWER_MENU_ITEM_NCR_VERIFICATION = 3;

    private int mMenuPosition;
    private Context mContext;
    private DrawerCallBack mCallBack;

    @View(R.id.itemNameTxt)
    private TextView itemNameTxt;

    @View(R.id.itemIcon)
    private ImageView itemIcon;

    public DrawerMenuActivity(Context context, int menuPosition) {
        mContext = context;
        mMenuPosition = menuPosition;
    }

    @Resolve
    private void onResolved() {
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_PROFILE:
                itemNameTxt.setText("Profile");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_item_menu));
                break;
            case DRAWER_MENU_ITEM_NCR_REGISTRATION:
                itemNameTxt.setText("NCR REGISTRATION");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_item_menu));
                break;
            case DRAWER_MENU_ITEM_NCR_VERIFICATION:
                itemNameTxt.setText("NCR VERIFICATION");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_item_menu));
                break;
        }
    }

    @Click(R.id.mainView)
    private void onMenuItemClick(){
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_PROFILE:
                Toast.makeText(mContext, "Profile", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onProfileMenuSelected();
                break;
            case DRAWER_MENU_ITEM_NCR_REGISTRATION:
                Toast.makeText(mContext, "NCR REGISTRATION", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onNcrRegisMenuSelected();
                break;
            case DRAWER_MENU_ITEM_NCR_VERIFICATION:
                Toast.makeText(mContext, "NCR VERIFICATION", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onNcrVerifMenuSelected();
                break;

        }
    }

    public void setDrawerCallBack(DrawerCallBack callBack) {
        mCallBack = callBack;
    }

    public interface DrawerCallBack{
        void onProfileMenuSelected();
        void onNcrRegisMenuSelected();
        void onNcrVerifMenuSelected();
    }
}
