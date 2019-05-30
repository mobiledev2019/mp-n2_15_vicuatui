package com.example.vicuatui;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.constraint.Constraints;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FifthFragment extends Fragment {
    TextView textViewDangNhap , textViewDangXuat , textViewRegister;
    ImageView imageView3;
    static int checkView = 0;
    String userName;
    Cursor cursor; //Declaration Cursor
    EditText username, password; //Declaration Edit
    TextInputLayout txtUsername, txtPassword; //Declaration TextInputLayout
    Button buttonLogin; //Declaration Button
    SharedPreferences pref,sharedpreferences;    //Declaration SharedPreferences
    DatabaseTest dataHelper; //Declaration SqliteHelper
    TextView txtRegister;
    public static int checkUser = 0;

    public FifthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_fifth, container, false);

        textViewDangNhap = (TextView)view.findViewById(R.id.textViewDangNhap);
        textViewDangXuat = (TextView)view.findViewById(R.id.textViewDangXuat);
        imageView3 = (ImageView)view.findViewById(R.id.imageView3);


        if (checkView == 0){
            textViewDangXuat.setVisibility(View.INVISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
        }
        else {
            textViewDangXuat.setVisibility(View.VISIBLE);
            imageView3.setVisibility(View.VISIBLE);
        }

        dataHelper = new DatabaseTest(getContext());
        if (checkView == 1){
            textViewDangNhap.setText("Xin chào :" + userName);
        }
        else {
            textViewDangNhap.setText("Đăng Nhập Hệ Thống");
        }

        Log.v(TAG, "cjeck " + checkView);
        textViewDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //User Logged in Successfully Launch You home screen activity

//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.detach(FifthFragment.this).attach(FifthFragment.this).commit();
                if (getCheckUser() == 1){
                    Toast.makeText(getActivity() , "Đăng xuất thành công " , Toast.LENGTH_SHORT).show();
                    checkView = 0;
                    setCheckUser(0);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.detach(FifthFragment.this).attach(FifthFragment.this).commit();
                }
                else {
                    Toast.makeText(getActivity() , "Xin vui lòng đăng nhập " , Toast.LENGTH_SHORT).show();
                }


            }
        });

        if (checkView == 0 ){
            textViewDangNhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //register();
                    DialogLogin();
                }
            });
        }


//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.detach(FifthFragment.this).attach(FifthFragment.this).commit();
        //return inflater.inflate(R.layout.fragment_fifth, container, false);
        return view;
    }

    private void DialogLogin(){
        Log.v(Constraints.TAG , "dfds" + this.getCheckUser());
        dataHelper = new DatabaseTest(getContext());
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_login);


        username = (EditText) dialog.findViewById(R.id.username);
        textViewRegister = (TextView) dialog.findViewById(R.id.txtRegister);

        password = (EditText) dialog.findViewById(R.id.password);
        txtUsername = (TextInputLayout) dialog.findViewById(R.id.txtUsername);
        txtPassword = (TextInputLayout) dialog.findViewById(R.id.txtPassword);
        buttonLogin = (Button) dialog.findViewById(R.id.buttonLogin);
        txtRegister = (TextView) dialog.findViewById(R.id.txtRegister);

        dataHelper = new DatabaseTest(getContext());

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //set click event of login button

                //Check user input is correct or not
                if (validate()) {

                    //Get values from EditText fields
                    String Username = username.getText().toString();
                    String Password = password.getText().toString();
                    // Query check email dan password
                    SQLiteDatabase db = dataHelper.getReadableDatabase();
                    cursor = db.rawQuery("SELECT id FROM users WHERE username = '" + Username + "' AND password ='"+Password+"'",null);
                    cursor.moveToFirst();
                    if (cursor.getCount()>0) {
                        Log.v(TAG , "la : " + Username);
                        checkView = 1;
                        userName = Username;
                        Toast.makeText(getActivity(), "Successfully Logged in",
                                Toast.LENGTH_LONG).show();
                        setCheckUser(1);
                        //User Logged in Successfully Launch You home screen activity

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(FifthFragment.this).attach(FifthFragment.this).commit();
                        dialog.cancel();
//                        finish();
                    }else{
                        Toast.makeText(getActivity(), "Failed to log in , please try again",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        dialog.show();


    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String Username = username.getText().toString();
        String Password = password.getText().toString();

        //Handling validation for Password field
        if(Username.isEmpty()) {
            valid = false;
            txtUsername.setError("Please enter valid Username!");
        }else {
            valid = true;
            txtUsername.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            txtPassword.setError("Please enter valid Password!");
        } else if (Password.length() < 4) {
            valid = false;
            txtPassword.setError("Password is to short!");
        } else {
            valid = true;
        }
        return valid;
    }

    public int getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(int checkUser) {
        this.checkUser = checkUser;
    }
    public void register(){
        // register de sau lam
        textViewDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (validate()) {
                    String UserName = "huyhuy";
                    //String Email = "quangquang@gmail.com";
                    String Password = "123456";

                    // Query check email
                    SQLiteDatabase db = dataHelper.getReadableDatabase();
                    cursor = db.rawQuery("SELECT id FROM users WHERE username = '" + UserName + "'",null);
                    cursor.moveToFirst();
                    if (cursor.getCount()>0) {
                        //Email exists with email input provided so show error user already exist
                        Toast.makeText(getActivity(), "Username already exists",
                                Toast.LENGTH_LONG).show();
                    }else{

                        SQLiteDatabase query = dataHelper.getWritableDatabase();
                        query.execSQL("insert into users(username, password) values('" +
                                UserName + "','" +

                                Password + "')");
                        Toast.makeText(getActivity(), "User created successfully! Please Login",
                                Toast.LENGTH_LONG).show();

                        //User Logged in Successfully Launch You home screen activity
//                        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
//                        startActivity(intent);
//                        finish();
                    }
                }
  //          }
        });
    }
}
