package com.kenjoel.edamam.network;

import com.kenjoel.green.SpoonacularResponseData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {
    @GET("search?")
    Call<SpoonacularResponseData> getRecipe(
        @Query("query")String foodType,
        @Query("apiKey") String api
    );
}
