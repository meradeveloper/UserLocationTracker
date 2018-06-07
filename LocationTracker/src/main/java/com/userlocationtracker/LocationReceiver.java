package com.userlocationtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import static com.userlocationtracker.LocationTracker.USEROBJECT;

/**
 * Created by Localadmin on 5/21/2018.
 */

public class LocationReceiver extends BroadcastReceiver {
    private DatabaseReference LocationReference;

    @Override
    public void onReceive(Context context, Intent intent) {
        Location location = (Location) intent.getExtras().get(LocationUpdateService.KEY_LOCATION_CHANGED);
        if(LocationReference==null)
            LocationReference = FirebaseDatabase.getInstance().getReference("Location");
        if(intent.getStringExtra(LocationUpdateService.STOP)!=null && intent.getStringExtra(LocationUpdateService.STOP).equalsIgnoreCase(LocationUpdateService.STOP))
            deleteLocation();
        if(location!=null)
        {
            LocationModel locationModel = new LocationModel();
            locationModel.setLat(String.valueOf(location.getLatitude()));
            locationModel.setLong(String.valueOf(location.getLongitude()));
            Log.e("LocationReceiver","location: "+location.getLatitude()+" long: "+location.getLongitude()+" code: "+getResultCode());
            addLocation(locationModel);
        }
    }

    private void deleteLocation() {
        Log.e("LocationReceiver","deleteLocation: "+getUser().getUID());
        final Query locationQuery = LocationReference.child(getUser().getUID());
        locationQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getKey().equalsIgnoreCase(getUser().getUID()))
                    dataSnapshot.getRef().removeValue();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void addLocation(LocationModel locationModel){
        LocationReference.child(String.valueOf(getUser().getUID())).setValue(locationModel);
    }

    private User getUser()
    {
        Gson gson = new Gson();
        String json = LocationTrackerApp.getPrefs().getString(USEROBJECT, "");
        return gson.fromJson(json, User.class);
    }

}
