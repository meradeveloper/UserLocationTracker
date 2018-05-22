package com.userlocationtracker;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by Localadmin on 5/22/2018.
 */

public class AppConfig extends Application {

    private static SharedPreferences mPrefs;
    private String LOCATIONPREFERENCE="LocationPreference";

    @Override
    public void onCreate() {
        super.onCreate();
        mPrefs = getSharedPreferences(LOCATIONPREFERENCE,MODE_PRIVATE);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public static SharedPreferences getPrefs() {
        return mPrefs;
    }
}
