package com.notrika.regular_trafic.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.notrika.regular_trafic.Entitie.OperationResult;
import com.notrika.regular_trafic.Entitie.ReqMyReports;
import com.notrika.regular_trafic.Entitie.Res_InQuery;
import com.notrika.regular_trafic.Entitie.Res_product;
import com.notrika.regular_trafic.R;
import com.notrika.regular_trafic.Util.Dialog_Product;
import com.notrika.regular_trafic.adapters.Adapter_Grid_Product;
import com.notrika.regular_trafic.adapters.Adapter_Recycle_List_Inquiry;
import com.notrika.regular_trafic.application;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInquerylist extends Fragment {

    RecyclerView recyclerView;
    public FragmentInquerylist() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__inquery_list, container, false);
        init(view);
        getdata();
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    private void getdata() {
        ReqMyReports reqMyReports  = new ReqMyReports();
        reqMyReports.setLicensePlate("11 ایران   334 ط 37");
        reqMyReports.setNotifyToMe(false);
        retrofit2.Call<OperationResult<List<Res_InQuery>>> call = ((application) getActivity().getApplication()).getClient().getInquiries(reqMyReports);
        call.enqueue(new Callback<OperationResult<List<Res_InQuery>>>() {
            @Override
            public void onResponse(Call<OperationResult<List<Res_InQuery>>> call, Response<OperationResult<List<Res_InQuery>>> response) {
                if(response.body()!=null)
                setDataToRecycler(response.body().getData());
                else{

                    Toast.makeText(getActivity(), "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OperationResult<List<Res_InQuery>>> call, Throwable t) {

                Toast.makeText(getActivity(), "امتیاز شما کم است", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDataToRecycler(final List<Res_InQuery> data) {

        Adapter_Recycle_List_Inquiry adapter;

            // set up the RecyclerView
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new Adapter_Recycle_List_Inquiry(getActivity(), data);
            adapter.setClickListener(new Adapter_Recycle_List_Inquiry.ItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(getActivity(), "You clicked " + data.get(position).getViolenceDesc() + " on row number " + position, Toast.LENGTH_SHORT).show();


                }
            });
            recyclerView.setAdapter(adapter);
        }




}
