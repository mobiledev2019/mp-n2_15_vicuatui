package com.example.vicuatui;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {
    final String DATABASE_NAME = "VicuatuiBDv1.db";
    int id = -1;

    EditText txt_dien_giai, txt_so_tien;
    TextView txt_ngay_thang;
    Spinner spinner_hang_muc;

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        addControls(view);
//        editData();
        return view;
    }

    //TODO: get data in fragment
    private void editData() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("ID");
            txt_dien_giai.setText(id + "");
        } else {
            Toast.makeText(getActivity(), "khong nhan duoc data", Toast.LENGTH_SHORT).show();
        }
        SQLiteDatabase database = Database.initDatabase(getActivity(), DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM KhoanChi WHERE ID = ?", new String[]{1 + ""});
        cursor.moveToFirst();
        double soTien = cursor.getDouble(1);
        String hangMuc = cursor.getString(2);
        String dienGiai = cursor.getString(3);
        String ngayThang = cursor.getString(4);
        byte[] anhHangMuc = cursor.getBlob(5);
        txt_so_tien.setText(soTien + "");
        spinner_hang_muc.setSelection(findItemSpinner(hangMuc));
        txt_dien_giai.setText(dienGiai);
        txt_ngay_thang.setText(ngayThang);
    }

    private void addControls(View view){
        txt_so_tien = (EditText) view.findViewById(R.id.third_fragment__txt_so_tien);
        spinner_hang_muc = (Spinner) view.findViewById(R.id.spinner_hang_muc);
        txt_dien_giai = (EditText) view.findViewById(R.id.third_fragment__txt_dien_giai);
        txt_ngay_thang = (TextView) view.findViewById(R.id.third_fragment__txt_ngay_thang);
    }

    private int findItemSpinner(String string) {
        int position = 1;
        String[] items = {"Ăn uống",
                        "Dịch vụ sinh hoạt",
                        "Đi lại",
                        "Trang phục",
                        "Nhà cửa",
                        "Hưởng thụ",
                        "Con cái",
                        "Phát triển bản thân",
                        "Sức khỏe"};
        for(int i=0; i<9; i++) {
            if (string == items[i]) {
                position = i;
                break;
            }
        }
        return position;
    }
}
