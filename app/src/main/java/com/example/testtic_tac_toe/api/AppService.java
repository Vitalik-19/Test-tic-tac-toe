package com.example.testtic_tac_toe.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppService {
    private static final String BASE_URL = "http://194.5.157.136:8081/";
    private static AppService mInstance;
    private static Retrofit retrofit;

    private AppService() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static AppService getInstance() {
        if (mInstance == null) {
            mInstance = new AppService();
        }
        return mInstance;
    }

    public DataApi getDataApi() {
        return retrofit.create(DataApi.class);
    }
}



