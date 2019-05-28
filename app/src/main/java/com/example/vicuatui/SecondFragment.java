package com.example.vicuatui;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.graphics.Color.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    final String DATABASE_NAME = "VicuatuiBDv1.db";
    SQLiteDatabase database;

    ListView listView;
    TextView txvTongTien;
    ArrayList<KhoanChi> list;
    AdapterKhoanChi adapter;
    public TextView txtDienGiai , txtNgayThang , txtSoTien , txtHangMuc;

    public SecondFragment() {
        // Required empty public constructor
    }

    public void Second(View view){
        txtHangMuc = (TextView)view.findViewById(R.id.txt_hang_muc);
        txtDienGiai = (TextView)view.findViewById(R.id.txt_dien_giai);
        txtNgayThang = (TextView)view.findViewById(R.id.txt_ngay_thang);
        txtSoTien = (TextView)view.findViewById(R.id.txt_so_tien);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        addControl(view);
        readData();
        return view;
    }

    public void addControl(View view) {
        listView = view.findViewById(R.id.listview);
        list = new ArrayList<>();
        adapter = new AdapterKhoanChi(getActivity(), list);
        listView.setAdapter(adapter);
        txvTongTien = view.findViewById(R.id.second_fragment_txt_tongtien);
        txvTongTien.setText(sum());
    }

    private void readData() {
        database = (SQLiteDatabase) Database.initDatabase(getActivity(), DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM KhoanChi  WHERE ID = " + 1 , null);
        list.clear();
        for(int i=0; i<cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            double soTien = cursor.getDouble(1);
            String hangMuc = cursor.getString(2);
            String dienGiai = cursor.getString(3);
            String ngayThang = cursor.getString(4);
            byte[] anhHangMuc = cursor.getBlob(5);
            list.add(new KhoanChi(id, soTien, hangMuc, dienGiai, ngayThang, anhHangMuc));
        }
        adapter.notifyDataSetChanged();
    }

    private String sum(){
        double tongTien = 0;
        SQLiteDatabase database = Database.initDatabase(getActivity(), "VicuatuiBDv1.db");
        Cursor cursor = database.rawQuery("SELECT * FROM KhoanChi", null);
        while (cursor.moveToNext()) {
            double soTien = cursor.getDouble(1);
            tongTien += soTien;
        }
        return tongTien + "";
    }
}

////    public TextView txtDienGiai, txtNgayThang, txtSoTien, txtHangMuc;
////txtHangMuc =(TextView)view.findViewById(R.id.txt_hang_muc);
////        txtDienGiai =(TextView)view.findViewById(R.id.txt_dien_giai);
////        txtNgayThang =(TextView)view.findViewById(R.id.txt_ngay_thang);
////        txtSoTien =(TextView)view.findViewById(R.id.txt_so_tien);




