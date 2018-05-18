package com.userlocationtracker;

/**
 * Created by Amal on 12/02/2017.
 */

public class Person {

    private String fullName;
    private double phoneNumber;

    public Person(){

    }

    public Person(String fullName, double phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
