package com.example.testtic_tac_toe.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppService {
    private static final String BASE_URL = "https://my-json-server.typicode.com/Vitalik-19/myJson/";
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



