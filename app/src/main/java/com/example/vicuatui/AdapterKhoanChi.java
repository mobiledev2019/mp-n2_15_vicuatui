package com.example.vicuatui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterKhoanChi extends BaseAdapter {
    Activity context;
    ArrayList list;

    public AdapterKhoanChi(Activity context, ArrayList list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview_row, null);
        ImageView img_hang_muc = (ImageView) row.findViewById(R.id.img_hang_muc);
        TextView txt_hang_muc = (TextView) row.findViewById(R.id.txt_hang_muc);
        TextView txt_dien_giai = (TextView) row.findViewById(R.id.txt_dien_giai);
        TextView txt_ngay_thang = (TextView) row.findViewById(R.id.txt_ngay_thang);
        TextView txt_so_tien = (TextView) row.findViewById(R.id.txt_so_tien);
        Button btn_sua = (Button) row.findViewById(R.id.btn_sua);
        Button btn_xoa = (Button) row.findViewById(R.id.btn_xoa);

        final KhoanChi khoanChi = (KhoanChi) list.get(position);
        txt_hang_muc.setText(khoanChi.hangMuc);
        txt_dien_giai.setText(khoanChi.dienGiai);
        txt_ngay_thang.setText(khoanChi.ngayThang);
        txt_so_tien.setText(khoanChi.soTien + "");

        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(khoanChi.anhHangMuc, 0 , khoanChi.anhHangMuc.length);
        img_hang_muc.setImageBitmap(bmHinhDaiDien);

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdFragment thirdFragment = new ThirdFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("ID", khoanChi.ID);
                thirdFragment.setArguments(bundle);
            }
        });
        return row;
    }
}
