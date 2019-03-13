package com.notrika.regular_trafic.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.notrika.regular_trafic.R;
import com.notrika.regular_trafic.Util.Dialog_inquiry;
import com.notrika.regular_trafic.Util.func;

/**
 * A simple {@link Fragment} subclass.
 */


public class FragmentInquiryGetPlak extends Fragment {
    Spinner sp_pelak_b2;
    EditText et_pelak_b1, et_pelak_b3, et_pelak_b4;
    Button inquiry;
    String horuf="الف";
    boolean isFirst = true;
    View view;

    public FragmentInquiryGetPlak() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
           view = inflater.inflate(R.layout.fragment_inquiry_get_plak, container, false);
          init();
          fillspiner();
          setlisteners();
          return view;

    }

    private void setlisteners() {
        inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Dialog_inquiry dialog_inquiry = new Dialog_inquiry();
                final String plak;
                plak = new func().pelaqFormater(
                        et_pelak_b3.getText().toString(),
                        et_pelak_b4.getText().toString(),
                        horuf,
                        et_pelak_b1.getText().toString()

                );

                String matn = "آیا از ثبت پلاک " + plak + " مطمئن هستید ؟";
                dialog_inquiry.Config(getActivity(), matn).setOnClickListener(new Dialog_inquiry.OnDialogClickListener() {
                    @SuppressLint("ResourceType")
                    @Override
                    public void click() {
                        FragmentManager fm = getFragmentManager();
                        Fragment newFragment =new FragmentInquerylist(); // whatever you do to get a fragment instance

                        FragmentTransaction ft = fm.beginTransaction();


                        ft.replace(R.id.container_inquiry, newFragment, "Stats");
                        ft.addToBackStack("FragmentInquerylist");
                        ft.commit();
                    }
                }).build();
            }
        });

        et_pelak_b1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 1) {
                    sp_pelak_b2.performClick();
                    hideKeyboard();
                }
            }
        });
        et_pelak_b3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 2) {
                    et_pelak_b4.requestFocus();
                    showKeyboard();
                }
            }
        });
        et_pelak_b4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
    }


    private void fillspiner() {
        final String[] items = new String[]{"الف", "ب", "پ", "ط","ث", "ج"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);

        sp_pelak_b2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                horuf = items[position];
                if (isFirst) {
                    isFirst = false;
                    return;
                }
                et_pelak_b3.requestFocus();
                showKeyboard();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                et_pelak_b3.requestFocus();
                showKeyboard();

            }
        });
        sp_pelak_b2.setAdapter(adapter);

    }
    private void init() {
        sp_pelak_b2 = view.findViewById(R.id.sp_pelak_b2);
        inquiry = view.findViewById(R.id.inquiry);
        et_pelak_b1 = view.findViewById(R.id.et_pelak_b1);
        et_pelak_b3 = view.findViewById(R.id.et_pelak_b3);
        et_pelak_b4 = view.findViewById(R.id.et_pelak_b4);
    }
    private void showKeyboard() {
        View view = getActivity().getCurrentFocus();
        InputMethodManager methodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        assert methodManager != null && view != null;
        methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getActivity().getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(getActivity());
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
