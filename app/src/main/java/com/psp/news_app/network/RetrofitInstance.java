package com.psp.news_app.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    // Base URL
    private final static String BASE_URL = "https://inshortsapi.vercel.app/"; // news?category=sports

    // Retrofit object
    private static Retrofit retrofit;

    public static synchronized Retrofit getInstance() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
