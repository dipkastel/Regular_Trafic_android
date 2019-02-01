package com.notrika.regular_trafic.network;

import com.notrika.regular_trafic.Entitie.OperationResult;
import com.notrika.regular_trafic.Entitie.Req_Update;
import com.notrika.regular_trafic.Entitie.Req_register;
import com.notrika.regular_trafic.Entitie.Req_report;
import com.notrika.regular_trafic.Entitie.Res_Update;
import com.notrika.regular_trafic.Entitie.Res_Violenc;
import com.notrika.regular_trafic.Entitie.Res_product;
import com.notrika.regular_trafic.Entitie.Res_register;
import com.notrika.regular_trafic.Entitie.Res_report;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface clientApi {

    @POST("Device/Register")
    Call<OperationResult<Res_register>> deviceRegister(@Body Req_register req_register);

    @POST("device/update")
    Call<OperationResult<Res_Update>> deviceUpdate(@Body Req_Update req_update);

    @GET("report/getViolences")
    Call<OperationResult<List<Res_Violenc>>> getallviolenc();

    @POST("report/submitreports")
    Call<OperationResult<Res_report>> submitReport(@Body Req_report req_report);

    @GET("product/getall")
    Call<OperationResult<List<Res_product>>> getallproduct();
}
