package com.example.user.navbartemplatejava.service;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

public class LocationService extends Service implements LocationListener {
    private static final String TAG = LocationService.class.getSimpleName();

    private static final long INTERVAL_TIME_CHANGE_FOR_UPDATES = 1000 * 60;
    private static final long INTERVAL_DISTANCE_CHANGE_FOR_UPDATES = 10;
    public static final Integer ACTIVITY_RESULT = 1001;
    public static final String[] PERMISSION_REQUEST_LOCATION = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private Boolean isGpsEnable = false;
    private Boolean isNetworkEnable = false;
    private Boolean isAvailableLocation = false;
    private Double latitude;
    private Double longitude;
    private Location location;
    private final IBinder binder = new ServiceBinder();

    public LocationService() {
        // require default constructor
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager != null) {
            Log.d(TAG, "onCreate -> locationManager not null");
            isGpsEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }

        if (!isGpsEnable && !isNetworkEnable) {
            Log.d(TAG, "onCreate -> Gps and Network are disabled");
        } else {
            isAvailableLocation = true;
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) getApplicationContext(), PERMISSION_REQUEST_LOCATION, ACTIVITY_RESULT);
            }

            if (isNetworkEnable) {
                if (locationManager != null) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            INTERVAL_TIME_CHANGE_FOR_UPDATES, INTERVAL_DISTANCE_CHANGE_FOR_UPDATES, this);
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    }
                }
            }

            if (isGpsEnable) {
                if (location == null) {
                    if (locationManager != null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                                INTERVAL_TIME_CHANGE_FOR_UPDATES, INTERVAL_DISTANCE_CHANGE_FOR_UPDATES, this);
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
            }
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        Log.d(TAG, "onBind");
        Toast.makeText(this, "Feature asset service is start", Toast.LENGTH_SHORT).show();
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        Toast.makeText(this, "Feature asset service is start", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        Toast.makeText(this, "Feature asset service is done", Toast.LENGTH_SHORT).show();
        stopSelf();
        super.onDestroy();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged");
        this.location = location;
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, "onProvideDisabled: " + provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, "onProviderEnabled: " + provider);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG, "onStatusChanged: " + provider);
    }

    public double getLatitude() {
        if (location != null) latitude = location.getLatitude();
        Log.d(TAG, "getLatitude : " + latitude);
        return latitude;
    }

    public double getLongitude() {
        if (location != null) longitude = location.getLongitude();
        Log.d(TAG, "getLongitude : " + longitude);
        return longitude;
    }

    public boolean isAvailableLocation() {
        return isAvailableLocation;
    }

    public class ServiceBinder extends Binder {
        public LocationService getService() {
            return LocationService.this;
        }
    }
}
