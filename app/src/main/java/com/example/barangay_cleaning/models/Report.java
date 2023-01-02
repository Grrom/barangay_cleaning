package com.example.barangay_cleaning.models;

import android.net.Uri;

public class Report {
    String image;
    String name;
    String status;
    Resident offender;

    public Report(String image, String name, String status, Resident offender) {
        this.image = image;
        this.name = name;
        this.status = status;
        this.offender = offender;
    }

    public Uri getImage() {
        return Uri.parse(image);
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
