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

import com.notrika.regular_trafic.Entitie.OperationResult;
import com.notrika.regular_trafic.Entitie.Res_Violenc;
import com.notrika.regular_trafic.DialogReport;
import com.notrika.regular_trafic.R;
import com.notrika.regular_trafic.application;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReport extends Fragment {

    List<Res_Violenc> list_violenc;
    Spinner sp_pelak_b2;
    EditText et_pelak_b1, et_pelak_b3, et_pelak_b4;
    Button submit_vio;
    public FragmentReport() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_report, container, false);
        init(view);
        fillspiner();
        setlisteners();
        getviolenc();
        return view;
    }

    private void fillspiner() {
        final String[] items = new String[]{"الف", "ب", "پ", "ث", "ج"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);

        sp_pelak_b2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), items[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_pelak_b2.setAdapter(adapter);

    }

    private void setlisteners() {


        submit_vio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isPlakValid())return;

                DialogReport.getInstace().Config(getActivity(), list_violenc).setOnClickListener(new DialogReport.OnDialogClickListener() {
                    @Override
                    public void positiveButtonClicked(ArrayList<Res_Violenc> list) {
                        if (list.size() < 1) {

                            Toast.makeText(getActivity(), "شما هیچ تخلفی را انتخاب نکردید", Toast.LENGTH_SHORT).show();
                        } else {
//                            String pelak = getpelak();
                        }
                    }
                }).build();
            }
        });

    }

    private boolean isPlakValid() {

        if(!et_pelak_b1.getText().toString().matches("\\d{2}")){
            Toast.makeText(getActivity(),"بخش اول پلاک را درست وارد کنید",Toast.LENGTH_SHORT).show();
            et_pelak_b1.requestFocus();
            return false;
        }
        if(!et_pelak_b3.getText().toString().matches("\\d{3}")){
            Toast.makeText(getActivity(),"بخش سوم پلاک را درست وارد کنید",Toast.LENGTH_SHORT).show();
            et_pelak_b3.requestFocus();
            return false;
        }
        if(!et_pelak_b4.getText().toString().matches("\\d{2}")){
            Toast.makeText(getActivity(),"بخش ایران پلاک را درست وارد کنید",Toast.LENGTH_SHORT).show();
            et_pelak_b4.requestFocus();
            return false;
        }
        return true;
    }

    private void init(View view) {
        sp_pelak_b2 = view.findViewById(R.id.sp_pelak_b2);
        submit_vio = view.findViewById(R.id.submit_vio);
        et_pelak_b1 = view.findViewById(R.id.et_pelak_b1);
        et_pelak_b3 = view.findViewById(R.id.et_pelak_b3);
        et_pelak_b4 = view.findViewById(R.id.et_pelak_b4);
    }


    private void getviolenc() {
        retrofit2.Call<OperationResult<List<Res_Violenc>>> call = ((application) getActivity().getApplication()).getClient().getallviolenc();
        call.enqueue(new Callback<OperationResult<List<Res_Violenc>>>() {
            @Override
            public void onResponse(Call<OperationResult<List<Res_Violenc>>> call, Response<OperationResult<List<Res_Violenc>>> response) {
                if (response.isSuccessful()) {

                    list_violenc = response.body().getData();
                    submit_vio.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<OperationResult<List<Res_Violenc>>> call, Throwable t) {

            }
        });
    }


}
