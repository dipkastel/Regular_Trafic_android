package com.notrika.regular_trafic.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.notrika.regular_trafic.DialogReport;
import com.notrika.regular_trafic.Entitie.Res_Violenc;
import com.notrika.regular_trafic.Entitie.Res_product;
import com.notrika.regular_trafic.R;
import com.notrika.regular_trafic.Util.Dialog_Product;
import com.notrika.regular_trafic.Util.Dialog_inquiry;
import com.notrika.regular_trafic.Util.func;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */


public class FragmentInquiry extends Fragment {
    Spinner sp_pelak_b2;
    EditText et_pelak_b1, et_pelak_b3, et_pelak_b4;
    Button inquiry;
    String horuf="الف";

    public FragmentInquiry() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_inquiry, container, false);
        init(view);
        fillspiner();
        setlisteners();
        return view;

    }

    private void setlisteners() {
        inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_inquiry dialog_inquiry = new Dialog_inquiry();
                final String plak;
                if (et_pelak_b1.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "پلاک را درست وارد نمائید", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (et_pelak_b3.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "پلاک را درست وارد نمائید", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (et_pelak_b4.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "پلاک را درست وارد نمائید", Toast.LENGTH_SHORT).show();
                    return;
                }


                plak = new func().pelaqFormater(
                        et_pelak_b3.getText().toString(),
                        et_pelak_b4.getText().toString(),
                        horuf,
                        et_pelak_b1.getText().toString()

                );

                String matn = "آیا از ثبت پلاک " + plak + " مطمئن هستید ؟";
                dialog_inquiry.Config(getActivity(), matn).setOnClickListener(new Dialog_inquiry.OnDialogClickListener() {
                    @Override
                    public void click() {
                        reqGetmyreports(plak);
                    }
                }).build();
            }
        });
    }


    private void reqGetmyreports(String plak) {
        Toast.makeText(getActivity(),"دریافت اطلاعات پلاک"+ plak , Toast.LENGTH_SHORT).show();
    }

    private void fillspiner() {
        final String[] items = new String[]{"الف", "ب", "پ", "ث", "ج"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);

        sp_pelak_b2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), items[position], Toast.LENGTH_SHORT).show();
                horuf=items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_pelak_b2.setAdapter(adapter);

    }

    private void init(View view) {
        sp_pelak_b2 = view.findViewById(R.id.sp_pelak_b2);
        inquiry = view.findViewById(R.id.inquiry);
        et_pelak_b1 = view.findViewById(R.id.et_pelak_b1);
        et_pelak_b3 = view.findViewById(R.id.et_pelak_b3);
        et_pelak_b4 = view.findViewById(R.id.et_pelak_b4);
    }

}
