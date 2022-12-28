package com.example.barangay_cleaning.models;

import java.io.Serializable;

public class Resident implements Serializable {
    int image;
    String name;
    int age;
    String address;

    public Resident(int image, String name, int age, String address){
        this.image = image;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}
