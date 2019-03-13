package com.notrika.regular_trafic.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.notrika.regular_trafic.Entitie.OperationResult;
import com.notrika.regular_trafic.Entitie.Req_Orderproduct;
import com.notrika.regular_trafic.Entitie.Res_product;
import com.notrika.regular_trafic.R;
import com.notrika.regular_trafic.Util.Dialog_Product;
import com.notrika.regular_trafic.adapters.Adapter_Grid_Product;
import com.notrika.regular_trafic.application;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProduct extends Fragment {

    public FragmentProduct() {
        // Required empty public constructor
    }

    private GridView gridview_product;
    ProgressBar progressbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        init(view);
        getdata();

        return view;
    }

    private void getdata() {

        retrofit2.Call<OperationResult<List<Res_product>>> call = ((application) getActivity().getApplication()).getClient().getallproduct();
        call.enqueue(new Callback<OperationResult<List<Res_product>>>() {
            @Override
            public void onResponse(Call<OperationResult<List<Res_product>>> call, Response<OperationResult<List<Res_product>>> response) {

                progressbar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    Adapter_Grid_Product adapter_grid_product = new Adapter_Grid_Product(getActivity(),response.body().getData());
                    adapter_grid_product.setOnclicklistner(new Adapter_Grid_Product.onclicklistner() {
                        @Override
                        public void onclick(Res_product item) {
                            Dialog_Product dialog_product=new Dialog_Product();
                            dialog_product.Config(getActivity(),item).setOnClickListener(new Dialog_Product.OnDialogClickListener() {
                                @Override
                                public void click(Res_product item) {
                                orderProduct(item);
                                }
                            }).build();
                        }
                    });
                    gridview_product.setAdapter(adapter_grid_product);
                }
                else{
                    Toast.makeText(getActivity(),"خطا در برقراری ارتباط با سرور",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<OperationResult<List<Res_product>>> call, Throwable t) {
                Toast.makeText(getActivity(),"خطا در برقراری ارتباط با سرور",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void orderProduct(Res_product item) {
        Req_Orderproduct req_orderproduct=new Req_Orderproduct();
        req_orderproduct.setProductId(item.getId());
        retrofit2.Call<OperationResult<Boolean>> call = ((application) getActivity().getApplication()).getClient().orderproduct(req_orderproduct);
        call.enqueue(new Callback<OperationResult<Boolean>>() {
            @Override
            public void onResponse(Call<OperationResult<Boolean>> call, Response<OperationResult<Boolean>> response) {
                Toast.makeText(getActivity(),"آفرین",Toast.LENGTH_SHORT).show();
                getdata();


            }

            @Override
            public void onFailure(Call<OperationResult<Boolean>> call, Throwable t) {
                Toast.makeText(getActivity(),"خطا در برقراری ارتباط با سرور",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void init(View view) {
        gridview_product = view.findViewById(R.id.gridview_product);
        progressbar = view.findViewById(R.id.progressbar);
    }

    private Object getApplication() {
        return getApplication();
    }

}
