package com.userlocationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*@Override
    protected User initLocationTracker(User USER) {
        USER.setUID("001");
        USER.setUserName("ABCD");
        USER.setLocation("DELHI");
        return USER;
    }

    public void stopService(View view) {
        stopTracker();
    }*/
}
