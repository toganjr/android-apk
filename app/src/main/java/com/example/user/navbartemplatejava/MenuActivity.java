package com.example.user.navbartemplatejava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.Placeholder;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.user.navbartemplatejava.data.prefs.PreferencesHelper;
import com.mindorks.placeholderview.PlaceHolderView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private PlaceHolderView mDrawerView;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private PlaceHolderView mGalleryView;

    private static String menufrom;
    PreferencesHelper mPrefs;

    public static Intent startIntent(Context context){
        return new Intent(context, MenuActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_main);

        //PlaceHolder
//        mDrawer = findViewById(R.id.drawer_layout);
//        mDrawerView = findViewById(R.id.drawerView);
//        mToolbar = findViewById(R.id.toolbar);
//        mGalleryView = findViewById(R.id.galleryView);
//        setupDrawer();

        if (menufrom == null){
            setContentView(R.layout.activity_main);
        } else if (menufrom == "regNCR"){
            Intent in = new Intent (MenuActivity.this, regNCRActivity.class);
            startActivity(in);
        } else if (menufrom == "profile") {
            setContentView(R.layout.activity_prof);
        }
        mPrefs = ((InkaApp)  getApplication()).getPrefs();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

//    //PlaceHolder
//    private void setupDrawer(){
//        mDrawerView
//                .addView(new DrawerItemActivity())
//                .addView(new DrawerMenuActivity(this.getApplicationContext(), DrawerMenuActivity.DRAWER_MENU_ITEM_PROFILE))
//                .addView(new DrawerMenuActivity(this.getApplicationContext(), DrawerMenuActivity.DRAWER_MENU_ITEM_NCR_REGISTRATION))
//                .addView(new DrawerMenuActivity(this.getApplicationContext(), DrawerMenuActivity.DRAWER_MENU_ITEM_NCR_VERIFICATION));
//
//        ActionBarDrawerToggle  drawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open_drawer, R.string.close_drawer){
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//            }
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//            }
//        };
//
//        mDrawer.addDrawerListener(drawerToggle);
//        drawerToggle.syncState();
//    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btn_logout) {
            mPrefs.setUserSignInToken("");
            mPrefs.setUserSignIn(null);
            mPrefs.setUserIsSignIn(false);
            openLoginActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            menufrom = "profile";
            Intent intent =  new Intent(this, MenuActivity.class);
            startActivityForResult(intent,0);
        } else if (id == R.id.nav_regNCR) {
            menufrom = "regNCR";
            Intent intent =  new Intent(this, regNCRActivity.class);
            startActivityForResult(intent,0);
        }  else if (id == R.id.nav_verNCR) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void openLoginActivity(){
        startActivity(LoginActivity.startIntent(this));
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
