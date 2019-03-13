package com.notrika.regular_trafic.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.notrika.regular_trafic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInquiryMain extends Fragment {

    FrameLayout container_inquiry;
    public FragmentInquiryMain() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fm = getFragmentManager();
        Fragment newFragment =new FragmentInquiryGetPlak(); // whatever you do to get a fragment instance

        FragmentTransaction ft = fm.beginTransaction();


        ft.replace(R.id.container_inquiry, newFragment, "Stats");
        ft.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inquiry_main, container, false);
    }

}
