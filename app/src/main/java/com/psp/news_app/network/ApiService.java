package com.psp.news_app.network;

import com.psp.news_app.model.NewsList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET ("news?category=sports")
    Call<NewsList> getNewsList();

}
