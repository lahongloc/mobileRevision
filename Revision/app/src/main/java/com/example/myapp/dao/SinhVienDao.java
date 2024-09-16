package com.example.myapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapp.data.DatabaseHelper;
import com.example.myapp.entity.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class SinhVienDao {
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    public SinhVienDao(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
        this.database = this.databaseHelper.getWritableDatabase();
    }

    public SinhVien getFirstSinhVien() {
        SinhVien sinhVien = null;
        Cursor cursor = this.getAllSinhViens();
        if(cursor.moveToLast()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SINH_VIEN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SINH_VIEN_NAME));

            sinhVien = new SinhVien(id, name);
        }
        cursor.close();
        this.close();
        return sinhVien;
    }

    public List<SinhVien> getSinhViens() {
        List<SinhVien> sinhViens = new ArrayList<>();
        Cursor cursor = this.getAllSinhViens();

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SINH_VIEN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SINH_VIEN_NAME));

            SinhVien sinhVien = new SinhVien(id, name);
            sinhViens.add(sinhVien);
        }
        cursor.close();
        this.close();
        return sinhViens;
    }

    public long insertSinhVien(String name) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_SINH_VIEN_NAME, name);
        return database.insert(DatabaseHelper.TABLE_SINH_VIEN, null, values);
    }

    public Cursor getAllSinhViens() {
        String[] columns = {
                DatabaseHelper.COLUMN_SINH_VIEN_ID,
                DatabaseHelper.COLUMN_SINH_VIEN_NAME
        };
        return this.database.query(DatabaseHelper.TABLE_SINH_VIEN, columns, null, null, null, null, null);
    }

    public void close() {
        this.databaseHelper.close();
    }
}
