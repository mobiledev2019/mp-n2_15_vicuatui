package com.example.vicuatui;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME = "VicuatuiBDv1.db";
    private static final int DB_VER = 1;
    public Database(Context context) {
        super(context, DB_NAME , null , DB_VER);
    }

    // function get all db khoan chi
    public List<KhoanChi> getKhoanChi(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"ID" , "SoTien", "HangMuc" , "DienGiai" , "NgayThang"};

        // make sure all is column name in your table
        String tableName = "khoanchi";

        Cursor cursor = qb.query(db , sqlSelect , null , null , null , null , null);
        List<KhoanChi> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                KhoanChi khoanChi = new KhoanChi();
                khoanChi.setID(cursor.getInt(cursor.getColumnIndex("ID")));
                khoanChi.setID(cursor.getInt(cursor.getColumnIndex("SoTien")));
                khoanChi.setDienGiai(cursor.getString(cursor.getColumnIndex("DienGiai")));
                khoanChi.setHangMuc(cursor.getString(cursor.getColumnIndex("HangMuc")));
                khoanChi.setHangMuc(cursor.getString(cursor.getColumnIndex("NgayThang")));

                result.add(khoanChi);
            }
            while (cursor.moveToNext());
        }
        return result;
    }
    // funtion get all khoan chi name
    public List<String> getName(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"HangMuc"};

        // make sure all is column name in your table
        String tableName = "khoanchi";

        Cursor cursor = qb.query(db , sqlSelect , null , null , null , null , null);
        List<String> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                result.add(cursor.getString(cursor.getColumnIndex("HangMuc")));
            }
            while (cursor.moveToNext());
        }
        return result;
    }

    // functio get khoan chi ny hang muc
    public List<KhoanChi> getKhoanChiByHangMuc(String name){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"ID" , "SoTien", "HangMuc" , "DienGiai" , "NgayThang"};

        // make sure all is column name in your table
        String tableName = "khoanchi";

        Cursor cursor = qb.query(db,sqlSelect,"HangMuc LIKE ?" , new String[]{"%" + name + "%"}, null , null , null);
        List<KhoanChi> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                KhoanChi khoanChi = new KhoanChi();
                khoanChi.setID(cursor.getInt(cursor.getColumnIndex("ID")));
                khoanChi.setID(cursor.getInt(cursor.getColumnIndex("SoTien")));
                khoanChi.setDienGiai(cursor.getString(cursor.getColumnIndex("DienGiai")));
                khoanChi.setHangMuc(cursor.getString(cursor.getColumnIndex("HangMuc")));
                khoanChi.setHangMuc(cursor.getString(cursor.getColumnIndex("NgayThang")));

                result.add(khoanChi);
            }
            while (cursor.moveToNext());
        }
        return result;
    }







    public static SQLiteDatabase initDatabase(Activity activity, String databaseName){
        try {
            String outFileName = activity.getApplicationInfo().dataDir + "/databases/" + databaseName;
            File f = new File(outFileName);
            if(!f.exists()) {
                InputStream e = activity.getAssets().open(databaseName);
                File folder = new File(activity.getApplicationInfo().dataDir + "/databases/");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                FileOutputStream myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];

                int length;
                while ((length = e.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                e.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return activity.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
    }
}
