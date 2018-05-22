package com.userlocationtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import static android.content.Context.MODE_PRIVATE;
import static com.userlocationtracker.LocationTracker.USEROBJECT;

/**
 * Created by Localadmin on 5/21/2018.
 */

public class LocationReceiver extends BroadcastReceiver {
    private DatabaseReference LocationReference;
    private SharedPreferences mPrefs;
    private Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        Location location = (Location) intent.getExtras().get(LocationUpdateService.KEY_LOCATION_CHANGED);
        this.context=context;
        if(location!=null)
        {
            LocationModel locationModel = new LocationModel();
            locationModel.setLat(String.valueOf(location.getLatitude()));
            locationModel.setLong(String.valueOf(location.getLongitude()));
            Log.e("LocationReceiver","location: "+location.getLatitude()+" long: "+location.getLongitude()+" code: "+getResultCode());
            addLocation(locationModel);
        }

    }

    private void addLocation(LocationModel locationModel){
        LocationReference = FirebaseDatabase.getInstance().getReference("Location");
        LocationReference.child(String.valueOf(getUser().getUID())).setValue(locationModel);
    }

    private User getUser()
    {
        Gson gson = new Gson();
        String json = AppConfig.getPrefs().getString(USEROBJECT, "");
        return gson.fromJson(json, User.class);
    }

}
