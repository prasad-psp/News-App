package com.psp.news_app.network;

import com.psp.news_app.response.NewsList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET ("news?category=sports")
    Call<NewsList> getNewsList();

}
