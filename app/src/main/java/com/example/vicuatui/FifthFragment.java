package com.example.vicuatui;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FifthFragment extends Fragment {

    TextView txtLogin;
    TextView textViewDangNhap;

    public FifthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_fifth, container, false);
        textViewDangNhap = (TextView)view.findViewById(R.id.textViewDangNhap);
        textViewDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLogin();
            }
        });

        //return inflater.inflate(R.layout.fragment_fifth, container, false);
        return view;
    }




    private void DialogLogin(){

        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_login);

        // anh xa
        final EditText editTextUsEditText = (EditText)dialog.findViewById(R.id.editTextUserName);
        final EditText edtPassword = (EditText) dialog.findViewById(R.id.editTextPassword);
        Button btnDongY = (Button) dialog.findViewById(R.id.buttonDangNhap);
        Button btnHuy = (Button) dialog.findViewById(R.id.buttonHuyBo);

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsEditText.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                dialog.dismiss();
            }
        });

        dialog.show();


    }
}
