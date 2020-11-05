package com.kenjoel.edamam.network;


import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FoodClient {

    private static final String TAG = "usifi" ;
    public static Retrofit retrofit = null;

    public static String api = "32ab879467874a569b37ad3a513db984";
    public static FoodApi getClient(){
        if(retrofit == null){
            OkHttpClient OkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor(){
                        private static final String TAG = "ulafi";

                        @Override
                        public Response intercept(Chain chain) throws IOException{
                            Request newRequest = chain.request().newBuilder()
                                    .build();
                            Log.i(TAG, "intercept" + newRequest);
                            return chain.proceed(newRequest);
                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.spoonacular.com/food/products/")
                    .client(OkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Log.i(TAG, retrofit.toString());
        }
        return retrofit.create(FoodApi.class);
    }
}
