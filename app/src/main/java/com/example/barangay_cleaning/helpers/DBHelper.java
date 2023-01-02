package com.example.barangay_cleaning.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;


import com.example.barangay_cleaning.models.Constants;
import com.example.barangay_cleaning.models.Report;
import com.example.barangay_cleaning.models.Resident;

import java.util.ArrayList;

final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}



    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "reports";
        public static final String COLUMN_NAME_TITLE1 = "image_uri";
        public static final String COLUMN_NAME_TITLE2 = "offender_id";
        public static final String COLUMN_NAME_TITLE3 = "violation_name";
        public static final String COLUMN_NAME_TITLE4 = "status";
    }
}

class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE1 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE2 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE3 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

public class DBHelper{

    public static long insertReport(Context context,SerializedReport report){
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE1, report.imageUri);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE2, report.offenderId);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE3, report.violationName);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE4, report.status);

        return db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
    }

    public static ArrayList<Report> getReportByResident(Context context, int residentId ){
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(context);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE1,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE2,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE3,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE4,
        };

        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE2 + " = ?";
        String[] selectionArgs = { String.valueOf(residentId) };

// How you want the results sorted in the resulting Cursor

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        ArrayList<Report> reports = new ArrayList<>();

        while(cursor.moveToNext()) {
            int offenderId = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE2));
            String imageUri = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE1));
            String violationName = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE3));
            String status = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE4));

            reports.add(new SerializedReport(offenderId, imageUri,violationName, status).toReport());
        }

        cursor.close();

        return reports;
    }

    public static ArrayList<Report> getReports(Context context){
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE1,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE2,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE3,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE4,
        };

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        ArrayList<Report> reports = new ArrayList<>();

        while(cursor.moveToNext()) {
            int offenderId = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE2));
            String imageUri = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE1));
            String violationName = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE3));
            String status = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE4));

            reports.add(new SerializedReport(offenderId, imageUri,violationName, status).toReport());
        }

        cursor.close();

        return reports;
    }

    public static class SerializedReport {
        int offenderId;
        String imageUri;
        String violationName;
        String status;

        public SerializedReport(int offenderId, String imageUri, String violationName, String status) {
            this.offenderId = offenderId;
            this.imageUri = imageUri;
            this.violationName = violationName;
            this.status = status;
        }

        public Report toReport(){
            ArrayList<Resident> residents = new ArrayList<>();
            residents.addAll(Constants.getResidents());
            Resident resident = residents.get(0);

            for (int i = 0; i < residents.size(); i++) {

                if(residents.get(i).getId() == offenderId){
                    resident = residents.get(i);
                    break;
                }
            }

            return new Report(imageUri, violationName, status, resident);
        }
    }
}
