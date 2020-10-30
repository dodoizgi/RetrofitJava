package com.dodo.retrofitjava.service;

import com.dodo.retrofitjava.model.CyrptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CyrptoApi {

    @GET("prices?key=856c0339851abb980e32c9985f96866e")
    Call<List<CyrptoModel>> getData();
}
