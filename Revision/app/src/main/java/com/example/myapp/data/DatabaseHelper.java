package com.example.myapp.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "myApp";
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_SINH_VIEN = "SinhVien";

    //SinhVien Table columns
    public static final String COLUMN_SINH_VIEN_ID = "id";
    public static final String COLUMN_SINH_VIEN_NAME = "name";

    // Create SinhVien Table SQL
    private static final String CREATE_SINH_VIEN_TABLE =
            "CREATE TABLE " + TABLE_SINH_VIEN + "(" +
            COLUMN_SINH_VIEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_SINH_VIEN_NAME + " TEXT NOT NULL);";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SINH_VIEN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SINH_VIEN);
        onCreate(db);
    }
}
