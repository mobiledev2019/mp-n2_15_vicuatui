package com.example.vicuatui;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseTest extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "VicuatuiBDv1.db"; // DATABASE NAME
    public static final int DATABASE_VERSION = 1; //DATABASE VERSION
    public static final String TABLE_USERS = "users"; //TABLE NAME

    //SQL for creating users table
    public static final String QUERY_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + "  ( id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, email TEXT, password TEXT ) ";

    public DatabaseTest(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
    }
}
