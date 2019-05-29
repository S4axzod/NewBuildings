package com.example.buildings.api;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "https://newbuilding.uz/";
    public static Retrofit retrofit;

    public OkHttpClient client;

    public RetrofitClient(OkHttpClient client){
        this.client = client;
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getClient() {
        return retrofit;
    }
}
