package com.notrika.regular_trafic;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.notrika.regular_trafic.fragments.FragmentInquiry;
import com.notrika.regular_trafic.fragments.FragmentProduct;
import com.notrika.regular_trafic.fragments.FragmentReport;

import java.util.ArrayList;
import java.util.List;

import eu.long1.spacetablayout.SpaceTabLayout;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    ViewPager viewPager;

    SpaceTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        fragmentlist(savedInstanceState);
    }

       private void fragmentlist(Bundle savedInstanceState) {
        //add the fragments you want to display in a List
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentProduct());
        fragmentList.add(new FragmentReport());
        fragmentList.add(new FragmentInquiry());


        //we need the savedInstanceState to get the position
        tabLayout.initialize(viewPager, getSupportFragmentManager(),
                fragmentList, savedInstanceState);

    }

    //we need the outState to save the position
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        tabLayout.saveState(outState);
        super.onSaveInstanceState(outState);
    }


    private void init() {
        tabLayout = (SpaceTabLayout) findViewById(R.id.spaceTabLayout);
        txt = findViewById(R.id.txt);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

    }


    //
//// Fetch a list of the Github repositories.
//        Call<List<String>> call =
//                ((application)getApplication()).getClient().reposForUser("fs-opensource");
//
//// Execute the call asynchronously. Get a positive or negative callback.
//        call.enqueue(new Callback<List<String>>() {
//            @Override
//            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
//                // The network call was a success and we got a response
//                // TODO: use the repository list and display it
//            }
//
//            @Override
//            public void onFailure(Call<List<String>> call, Throwable t) {
//                // the network call was a failure
//                // TODO: handle error
//            }
//        });
}

