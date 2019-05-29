package com.example.buildings.api;

import com.example.buildings.model.*;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;


public interface Api {

    @GET("api/banner")
    Call<BannerResponse> getFeaturedEvents();

    @GET("api/building")
    Call<BuildingResponse> getBuildings();

    @GET("api/news")
    Call<NewsResponse> getNews();

    @GET("api/partner")
    Call<PartnerResponse> getPartner();
}
