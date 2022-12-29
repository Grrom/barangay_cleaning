package com.example.barangay_cleaning.models;

public class Area {
    int image;
    String name;
    String status;

    public Area(int image, String name,  String status) {
        this.image = image;
        this.name = name;
        this.status = status;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
