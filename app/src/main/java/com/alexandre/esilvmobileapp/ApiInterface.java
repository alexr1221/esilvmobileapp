package com.alexandre.esilvmobileapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET
    Call<List<Review>> getReviews(@Url String url);

    @GET("games")
    Call<List<Game>> getGames();

    @GET
    Call<List<Store>> getStores(@Url String url);
}