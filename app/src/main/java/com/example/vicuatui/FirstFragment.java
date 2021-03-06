package com.example.vicuatui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    EditText edt_number_of_limit;
    Button btn_save;
    TextView textview_gioihanchi , txt_gioihansochi;

    TextView txt_number_of_limit;
    FifthFragment fifthFragment;

    private SharedPreferences mPreferences;

    private String sharedPrefFile =
            "limit_number";

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fifthFragment = new FifthFragment();
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        edt_number_of_limit = view.findViewById(R.id.edt_number_of_limit);
        btn_save = view.findViewById(R.id.first_fragment__btn_save);
        textview_gioihanchi = view.findViewById(R.id.textview_gioihanchi);
        txt_gioihansochi = view.findViewById(R.id.txt_gioihansochi);
        txt_number_of_limit = view.findViewById(R.id.txt_number_of_limit);
        if (fifthFragment.getCheckUser() == 1 ){
            mPreferences = this.getActivity().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
            addControls();
        }
        else {
            edt_number_of_limit.setVisibility(View.GONE);
            btn_save.setVisibility(View.GONE);
            txt_number_of_limit.setVisibility(View.GONE);
            txt_gioihansochi.setVisibility(View.GONE);
            textview_gioihanchi.setText("Xin Vui Lòng Đăng Nhập");

        }



        return view;
    }

    private void addControls() {
        int limit = mPreferences.getInt("NUMBER_OF_LIMIT", 0);
        txt_number_of_limit.setText(String.valueOf(limit));
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                int limit = Integer.parseInt(edt_number_of_limit.getText() + "");
                preferencesEditor.putInt("NUMBER_OF_LIMIT", limit);
                edt_number_of_limit.setText("");
                edt_number_of_limit.setFocusable(false);
                txt_number_of_limit.setText(String.valueOf(limit));
                preferencesEditor.apply();
                Toast.makeText(getContext(), "Lưu hạn mức thành công!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
