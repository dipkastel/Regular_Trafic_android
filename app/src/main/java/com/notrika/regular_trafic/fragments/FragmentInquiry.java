package com.notrika.regular_trafic.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.notrika.regular_trafic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInquiry extends Fragment {


    public FragmentInquiry() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inquiry, container, false);
    }

}
