package com.userlocationtracker;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Localadmin on 5/14/2018.
 */

 public abstract class LocationTracker extends AppCompatActivity
        {

    private static final String TAG = "LocationTracker";
    private DatabaseReference UsersReference;
    public static String USEROBJECT="USEROBJECT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppConfig.getEventBus().register(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        UsersReference = FirebaseDatabase.getInstance().getReference("Users");

        if (isGooglePlayServicesAvailable())
            startTracker(initLocationTracker(new User()));
    }

    private void addUser(){
        User user = getUser();
        UsersReference.child(String.valueOf(user.getUID())).setValue(user);
    }

    private void startTracker(User USER)
    {
        if (saveUser(USER)!=null);
        {
            addUser();
            startService(new Intent(this, LocationUpdateService.class));
        }
    }

    private User saveUser(User USER)
    {
        SharedPreferences.Editor prefsEditor = AppConfig.getPrefs().edit();
        Gson gson = new Gson();
        String json = gson.toJson(USER);
        prefsEditor.putString(USEROBJECT, json);
        prefsEditor.commit();
        return getUser();
    }

    private User getUser()
    {
        Gson gson = new Gson();
        String json = AppConfig.getPrefs().getString(USEROBJECT, "");
        return gson.fromJson(json, User.class);
    }

    protected abstract User initLocationTracker(User USER);

    @Override
    public void onStart() {
        super.onStart();
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

}


