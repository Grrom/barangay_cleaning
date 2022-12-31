package com.example.barangay_cleaning.models;

import java.io.Serializable;

public class Resident implements Serializable {
    int image;
    String firstName;
    String lastName;
    int age;
    String address;

    public Resident(int image, String firstName, String lastName, int age, String address){
        this.image = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }

    public int getImage() {
        return image;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName+" "+lastName;
    }
}
