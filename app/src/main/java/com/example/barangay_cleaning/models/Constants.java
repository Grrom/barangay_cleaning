package com.example.barangay_cleaning.models;

import android.content.Context;

import com.example.barangay_cleaning.R;
import com.example.barangay_cleaning.helpers.DBHelper;

import java.util.ArrayList;
import java.util.Collections;

public class Constants {

    private static Resident kyle=new Resident(1,R.drawable.vic, "Vic", "Hernandez", 22, "Purok 6 Calingatan");
    private static Resident temp_profile=new Resident(2,R.drawable.gab, "Gab", "Manlapas", 24, "Purok 6 Calingatan");
    private static Resident rizal=new Resident(3,R.drawable.jose, "Jose", "Ricardo", 22, "Purok 5 Calingatan");
    private static Resident mabini=new Resident(4,R.drawable.paulino, "Paul", "Mabinag", 32, "Purok 5 Calingatan");
    private static Resident antonio=new Resident(5,R.drawable.anton_lunar, "Anton", "Lunar", 42, "Purok 5 Calingatan");
    private static Resident gregorio=new Resident(6,R.drawable.goryo, "Goryo", "Del Pilario", 45, "Purok 6 Calingatan");
    private static Resident josefa=new Resident(7,R.drawable.josephine, "Josephine", "Escobar", 32, "Purok 6 Calingatan");

    public static ArrayList<Resident> getResidents(){
        ArrayList<Resident> residents= new ArrayList<>();

        residents.add(kyle);
        residents.add(temp_profile);
        residents.add(rizal);
        residents.add(mabini);
        residents.add(antonio);
        residents.add(gregorio);
        residents.add(josefa);

        Collections.sort(residents, (r1, r2) -> r1.getFirstName().compareToIgnoreCase(r2.getFirstName()));

        return residents;
    }

    public static ArrayList<Report> getReports(Context context){
        ArrayList<Report> reports= DBHelper.getReports(context);


        Collections.sort(reports, (r1, r2) -> r1.getOffender().getFirstName().compareToIgnoreCase(r2.getOffender().getFirstName()));

        return  reports;
    }

    public static ArrayList<Area> getAreas(){
        ArrayList<Area> areas= new ArrayList<>();

        areas.add(new Area(R.drawable.a, "Purok 6", "clean"));
        areas.add(new Area(R.drawable.health_center, "Purok 6", "unclean"));
        areas.add(new Area(R.drawable.d, "Purok 5", "clean"));
        areas.add(new Area(R.drawable.b, "Purok 5", "unclean"));
        areas.add(new Area(R.drawable.c, "Purok 6", "unclean"));

        Collections.sort(areas, (r1, r2) -> r1.getName().compareToIgnoreCase(r2.getName()));

        return  areas;
    }

}
