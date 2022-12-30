package com.example.barangay_cleaning.models;

public class Report {
    int image;
    String name;
    String status;
    Resident offender;

    public Report(int image, String name, String status, Resident offender) {
        this.image = image;
        this.name = name;
        this.status = status;
        this.offender = offender;
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

    public Resident getOffender() {
        return offender;
    }
}
