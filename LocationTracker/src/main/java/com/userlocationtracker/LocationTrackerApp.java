package com.userlocationtracker;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;

import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by Localadmin on 5/22/2018.
 */

public class LocationTrackerApp extends Application {

    private static SharedPreferences mPrefs;
    private String LOCATIONPREFERENCE="LocationPreference";

    @Override
    public void onCreate() {
        super.onCreate();
        mPrefs = getSharedPreferences(LOCATIONPREFERENCE,MODE_PRIVATE);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static SharedPreferences getPrefs() {
        return mPrefs;
    }
}
