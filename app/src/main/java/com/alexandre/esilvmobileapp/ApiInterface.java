package com.alexandre.esilvmobileapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("users/mygame/reviews?steam=true&googlePlay=true")
    Call<List<Review>> getReviews();

    @GET("games")
    Call<List<Game>> getGames();
}