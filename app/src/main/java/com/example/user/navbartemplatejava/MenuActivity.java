package com.example.user.navbartemplatejava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.navbartemplatejava.data.prefs.PreferencesHelper;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
 public static final String TAG = MenuActivity.class.getSimpleName();
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;

    private TextView mNama;
    private TextView mNip;

    private static String menufrom;

    PreferencesHelper mPrefs;

    public static Intent startIntent(Context context){
        return new Intent(context, MenuActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (menufrom == null){
            setContentView(R.layout.activity_main);
        } else if (menufrom == "registration"){
            setContentView(R.layout.activity_display_ncr_registration);
        } else if (menufrom == "profile") {
            setContentView(R.layout.activity_prof);
        } else if(menufrom == "verification"){
            setContentView(R.layout.activity_verifikasi);
        }

        mPrefs = ((InkaApp)  getApplication()).getPrefs();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        mNama = header.findViewById(R.id.nama);
        mNip = header.findViewById(R.id.nipHeader);

        mNama.setText(mPrefs.getUserSignIn().getName());
        mNip.setText(mPrefs.getUserSignIn().getNip());


    }




    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
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
        Log.d(TAG, "onNavigationItemSelected");
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            menufrom = "profile";
            Intent intent =  new Intent(this, MenuActivity.class);
            startActivityForResult(intent,0);
        } else if (id == R.id.nav_regNCR) {
            menufrom = "registration";
            Intent intent =  new Intent(this, regNCRActivity.class);
            startActivityForResult(intent,0);
        }  else if (id == R.id.nav_verNCR) {
            menufrom = "verification";
            Intent intent =  new Intent(this, MenuActivity.class);
            startActivityForResult(intent,0);


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
