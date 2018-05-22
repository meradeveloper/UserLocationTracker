package com.userlocationtracker;

import android.app.Application;
import android.content.SharedPreferences;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Localadmin on 5/22/2018.
 */

public class AppConfig extends Application {

    private static EventBus mEventBus;
    private static SharedPreferences mPrefs;
    private String LOCATIONPREFERENCE="LocationPreference";

    @Override
    public void onCreate() {
        super.onCreate();

        mEventBus=EventBus.getDefault();
        mPrefs = getSharedPreferences(LOCATIONPREFERENCE,MODE_PRIVATE);
    }

    public static EventBus getEventBus() {
        return mEventBus;
    }

    public static SharedPreferences getPrefs() {
        return mPrefs;
    }
}
