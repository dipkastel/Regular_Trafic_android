package com.notrika.regular_trafic.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.notrika.regular_trafic.Entitie.OperationResult;
import com.notrika.regular_trafic.Entitie.Req_report;
import com.notrika.regular_trafic.Entitie.Res_Violenc;
import com.notrika.regular_trafic.DialogReport;
import com.notrika.regular_trafic.Entitie.Res_report;
import com.notrika.regular_trafic.R;
import com.notrika.regular_trafic.Util.func;
import com.notrika.regular_trafic.Util.sharedPreference;
import com.notrika.regular_trafic.application;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReport extends Fragment {

    List<Res_Violenc> list_violenc;
    String horuf = "الف";
    Spinner sp_pelak_b2;
    EditText et_pelak_b1, et_pelak_b3, et_pelak_b4;
    Button submit_vio;
    boolean isFirst = true;
    boolean isSectedViolence = false;

    public FragmentReport() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        final View view = inflater.inflate(R.layout.fragment_report, container, false);
        init(view);

        fillspiner();
        setlisteners();
        getviolenc();
        return view;
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

    private void setlisteners() {


        submit_vio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isPlakValid()) return;

                DialogReport.getInstace().Config(getActivity(), list_violenc).setOnClickListener(new DialogReport.OnDialogClickListener() {
                    @Override
                    public void positiveButtonClicked(ArrayList<Res_Violenc> list) {
                        if (list.size() < 1) {

                            Toast.makeText(getActivity(), "شما هیچ تخلفی را انتخاب نکردید", Toast.LENGTH_SHORT).show();
                        } else {
                            sendreporttoserver(list);

                            isSectedViolence = true;
                        }
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
            public void afterTextChanged(Editable editable) {

                if (editable.toString().length() > 1) {
                    final Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.focuse_animation);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (!isSectedViolence)
                                submit_vio.startAnimation(anim);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    submit_vio.startAnimation(anim);
                    isSectedViolence = false;
                    hideKeyboard();

                }
            }
        });

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

    private void showKeyboard() {
        View view = getActivity().getCurrentFocus();
        InputMethodManager methodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        assert methodManager != null && view != null;
        methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    private boolean isPlakValid() {

        if (!et_pelak_b1.getText().toString().matches("\\d{2}")) {
            Toast.makeText(getActivity(), "بخش اول پلاک را درست وارد کنید", Toast.LENGTH_SHORT).show();
            et_pelak_b1.requestFocus();
            return false;
        }
        if (!et_pelak_b3.getText().toString().matches("\\d{3}")) {
            Toast.makeText(getActivity(), "بخش سوم پلاک را درست وارد کنید", Toast.LENGTH_SHORT).show();
            et_pelak_b3.requestFocus();
            return false;
        }
        if (!et_pelak_b4.getText().toString().matches("\\d{2}")) {
            Toast.makeText(getActivity(), "بخش ایران پلاک را درست وارد کنید", Toast.LENGTH_SHORT).show();
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

    private void sendreporttoserver(ArrayList<Res_Violenc> list) {
        List<Integer> viollist = new ArrayList<>();
        for (Res_Violenc viol : list) {
            viollist.add(viol.getId());
        }
        Req_report req_report = new Req_report();
        req_report.setLat("");
        req_report.setLng("");
        String plak = new func().pelaqFormater(
                et_pelak_b3.getText().toString(),
                et_pelak_b4.getText().toString(),
                horuf,
                et_pelak_b1.getText().toString()

        );
        req_report.setLicensePlate(plak);
        req_report.setViolations(viollist);
        retrofit2.Call<OperationResult<Res_report>> call = ((application) getActivity().getApplication()).getClient().submitReport(req_report);
        call.enqueue(new Callback<OperationResult<Res_report>>() {
            @Override
            public void onResponse(Call<OperationResult<Res_report>> call, Response<OperationResult<Res_report>> response) {
                new sharedPreference(getActivity()).setScore(response.body().getData().getScore());
                et_pelak_b1.setText("");
                sp_pelak_b2.setSelection(0);
                et_pelak_b3.setText("");
                et_pelak_b4.setText("");
                isFirst = true;
                et_pelak_b1.requestFocus();
                Toast.makeText(getActivity(), response.body().getData().getScore() + "  امتیاز شما  ", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<OperationResult<Res_report>> call, Throwable t) {
                Toast.makeText(getActivity(), "دوباره تلاش کنید", Toast.LENGTH_LONG).show();
            }
        });
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
