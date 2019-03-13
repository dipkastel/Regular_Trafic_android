package com.notrika.regular_trafic;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.os.strictmode.Violation;

import com.notrika.regular_trafic.Database.Database_app;
import com.notrika.regular_trafic.Database.TBL_Violation_Dao;
import com.notrika.regular_trafic.Util.sharedPreference;
import com.notrika.regular_trafic.network.clientApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class application extends Application {
    private final String DATABASE_NAME = "db_regular";
    public clientApi client;
    String API_BASE_URL = "http://www.adanjir.com/api/";
    public TBL_Violation_Dao violation_dao;
    Database_app db;

    @Override
    public void onCreate() {
        super.onCreate();
        RefreshRetrofit();
        RefreshDatabase();
    }

    private void RefreshDatabase() {
        db = Room.databaseBuilder(getApplicationContext(),
                Database_app.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
        violation_dao = db.tbl_violation_dao();
    }
    public TBL_Violation_Dao dao_violation(){
        return violation_dao;

    }

    public void RefreshRetrofit() {

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );
        Retrofit retrofit =
                builder
                        .client(
                                getRetrofitClient()
                        )
                        .build();
        client = retrofit.create(clientApi.class);
    }


    private OkHttpClient getRetrofitClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .header("AuthToken", new sharedPreference(getApplicationContext()).getToken())
                                .header("Content-Type", "application/json")
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    }
                })
                .readTimeout(40, TimeUnit.SECONDS)
                .connectTimeout(40, TimeUnit.SECONDS)
                .build();
    }

    public clientApi getClient() {
        return client;
    }


}
