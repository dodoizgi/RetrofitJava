package com.dodo.retrofitjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dodo.retrofitjava.R;
import com.dodo.retrofitjava.adapter.RecyclerViewAdapter;
import com.dodo.retrofitjava.model.CyrptoModel;
import com.dodo.retrofitjava.service.CyrptoApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CyrptoModel> cyrptoModels;
    private  String BASE_URL = "https://api.nomics.com/v1/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView2);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();

    }
    //https://api.nomics.com/v1/prices?key=856c0339851abb980e32c9985f96866e


    private  void loadData(){
        CyrptoApi cyrptoApi = retrofit.create(CyrptoApi.class);

        Call<List<CyrptoModel>> call = cyrptoApi.getData();
        call.enqueue(new Callback<List<CyrptoModel>>() {
            @Override
            public void onResponse(Call<List<CyrptoModel>> call, Response<List<CyrptoModel>> response) {

                if (response.isSuccessful()){
                    List<CyrptoModel> responseList = response.body();
                    cyrptoModels = new ArrayList<>(responseList);

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(cyrptoModels);
                    recyclerView.setAdapter(recyclerViewAdapter);


                }
            }

            @Override
            public void onFailure(Call<List<CyrptoModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
