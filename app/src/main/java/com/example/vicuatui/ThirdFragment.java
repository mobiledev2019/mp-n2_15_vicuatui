package com.example.vicuatui;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {
    final String DATABASE_NAME = "VicuatuiBDv1.db";
    final int REQUEST_TAKE_PHOTO = 123;
    int id = -1;
    Bundle bundle;
    int mDay, mMonth, mYear;

    EditText txt_dien_giai, txt_so_tien;
    EditText txt_ngay_thang;
    TextView txvAddBill;
    ImageView imgBill;
    Spinner spinner_hang_muc;
    Button btnGhi, btnDatePicker;
    private TextInputLayout inputLayoutDienDai, inputLayoutTien, inputLayoutNgayThang;
    AwesomeValidation awesomeValidation;


    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        addControls(view);
        bundle = this.getArguments();
        editData();
        return view;
    }

    private void saveData() {
        double soTien = (double) Double.parseDouble(txt_so_tien.getText() + "");
        String hangMuc = spinner_hang_muc.getSelectedItem().toString();
        String dienGiai = txt_dien_giai.getText().toString();
        String ngayThang = txt_ngay_thang.getText().toString();
        byte[] anh = getbyteArrayFromImageView(imgBill);

        ContentValues contentValues = new ContentValues();
        contentValues.put("SoTien", soTien);
        contentValues.put("HangMuc", hangMuc);
        contentValues.put("DienGiai", dienGiai);
        contentValues.put("NgayThang", ngayThang);
        //contentValues.put("Anh", anh);
        if (anh.length > 0 || anh != null){
            Log.d(TAG , "LOG HERE2");
            contentValues.put("Anh", anh);

        }
        else {
            Log.d(TAG , "LOG HERE");
        }

        SQLiteDatabase database = Database.initDatabase(getActivity(), "VicuatuiBDv1.db");
        database.insert("KhoanChi", null, contentValues);
        Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
        txt_dien_giai.setText("");
        txt_ngay_thang.setText("");
        txt_so_tien.setText("");
        imgBill.setImageBitmap(null);
    }

    //TODO: get  and update data in fragment
    private void editData() {
        if (bundle != null) {
            id = bundle.getInt("ID");
            txt_dien_giai.setText(id + "");
            SQLiteDatabase database = Database.initDatabase(getActivity(), DATABASE_NAME);
            Cursor cursor = database.rawQuery("SELECT * FROM KhoanChi WHERE ID = ?", new String[]{id + ""});
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
            Bitmap bitmap = BitmapFactory.decodeByteArray(anhHangMuc, 0, anhHangMuc.length);
            imgBill.setImageBitmap(bitmap);
        }
    }

    private void updateData() {
        Log.d(TAG , "LOG HERE");
        double soTien = (double) Double.parseDouble(txt_so_tien.getText() + "");
        String hangMuc = spinner_hang_muc.getSelectedItem().toString();
        String dienGiai = txt_dien_giai.getText().toString();
        String ngayThang = txt_ngay_thang.getText().toString();
        byte[] anh = getbyteArrayFromImageView(imgBill);

        ContentValues contentValues = new ContentValues();
        contentValues.put("SoTien", soTien);
        contentValues.put("HangMuc", hangMuc);
        //contentValues.put("DienGiai", dienGiai);
        contentValues.put("NgayThang", ngayThang);
        if (anh.length > 0 || anh != null){
            Log.d(TAG , "LOG HERE2" + anh.length);
            contentValues.put("Anh", anh);

        }
        else {
            Log.d(TAG , "LOG HERE");
        }


        SQLiteDatabase database = Database.initDatabase(getActivity(), "VicuatuiBDv1.db");
        database.update("KhoanChi", contentValues, "id = ?", new String[] { id + ""});
    }

    private void addControls(View view){
        txt_so_tien = (EditText) view.findViewById(R.id.third_fragment__txt_so_tien);
        spinner_hang_muc = (Spinner) view.findViewById(R.id.spinner_hang_muc);
        txt_dien_giai = (EditText) view.findViewById(R.id.third_fragment__txt_dien_giai);
        txt_ngay_thang = (EditText) view.findViewById(R.id.third_fragment__txt_ngay_thang);
        btnGhi = (Button) view.findViewById(R.id.third_fragment__btn_ghi);
        btnDatePicker = (Button) view.findViewById(R.id.third_fragment__btn_date_picker);
        txvAddBill = (TextView) view.findViewById(R.id.third_fragment__txv_add_bill);
        imgBill = (ImageView) view.findViewById(R.id.third_fragment__img_bill);
        inputLayoutNgayThang = (TextInputLayout) view.findViewById(R.id.inputLayoutNgayThang);
        inputLayoutTien = (TextInputLayout) view.findViewById(R.id.inputLayoutTien);
        inputLayoutDienDai = (TextInputLayout) view.findViewById(R.id.inputLayoutDienGiai);


//        txt_so_tien.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
////                if(txt_so_tien.getText().length() == 0){
////                    txt_so_tien.setError("Không được để trống !");
////                }
//                if (txt_so_tien.getText().length() < 3 || txt_so_tien.getText().length() > 11){
//                    txt_so_tien.setError("Số tiền không hợp lệ");
//                }
//            }
//        });
//
//        txt_dien_giai.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(txt_dien_giai.getText().length() == 0){
//                    txt_dien_giai.setError("Không được để trống !");
//                }
//            }
//        });




        Calendar c = Calendar.getInstance();
        mDay = c.get(Calendar.DATE);
        mMonth = c.get(Calendar.MONTH);
        mYear = c.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txt_ngay_thang.setText(sdf.format(c.getTime()));

        btnGhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (validate()){
                        if (bundle !=  null) {
                            if (bundle.getString("Edit") == "true") {
                                updateData();
                                Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            saveData();
                        }
                            Toast.makeText(getActivity() , "Data receove success" , Toast.LENGTH_SHORT).show();
                        }
                }
        });

        txvAddBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
    }

    private boolean validate() {

        boolean isValid = true;

        if (txt_dien_giai.getText().toString().isEmpty()) {
            inputLayoutDienDai.setError("Không được để trống");
            isValid = false;
        } else {
            inputLayoutDienDai.setErrorEnabled(false);
        }

        if (txt_so_tien.getText().toString().isEmpty() && txt_so_tien.getText().toString().trim().length() < 3 && txt_so_tien.getText().toString().trim().length() <12) {
            inputLayoutTien.setError("Số tiền không hợp lệ");
            isValid = false;
        } else {
            inputLayoutTien.setErrorEnabled(false);
        }

        if (txt_ngay_thang.getText().toString().trim().isEmpty() ) {
            inputLayoutNgayThang.setError("Ngày tháng không hợp lệ");
            isValid = false;
        } else {
            inputLayoutNgayThang.setErrorEnabled(false);
        }

        if (isValid) {
            Toast.makeText(getActivity(), R.string.success, Toast.LENGTH_SHORT).show();
        }
        return isValid;
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

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgBill.setImageBitmap(bitmap);
            }
        }
    }

    private byte[] getbyteArrayFromImageView(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
