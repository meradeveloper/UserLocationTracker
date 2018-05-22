package com.userlocationtracker;

import java.io.Serializable;

/**
 * Created by Amal on 12/02/2017.
 */

public class User implements Serializable {

    private String UserName;
    private String UID;
    private String Location;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
