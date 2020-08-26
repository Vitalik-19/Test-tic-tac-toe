package com.example.testtic_tac_toe.api;

import com.example.testtic_tac_toe.api.model.FlagDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataApi {
    @GET("/")
    Call<FlagDTO> getFlag();
}
