package com.psp.news_app.repo;

import androidx.annotation.NonNull;

import com.psp.news_app.model.News;
import com.psp.news_app.model.NewsList;
import com.psp.news_app.network.ApiService;
import com.psp.news_app.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepo {

    private final ApiService apiService;

    public NewsRepo() {
        apiService = RetrofitInstance.getInstance().create(ApiService.class);
    }

    public void requestNewsData(OnNewsCallback newsCallback) {

        Call<NewsList> call = apiService.getNewsList();
        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, @NonNull Response<NewsList> response) {

                NewsList list = response.body();
                if(list != null) {
                    newsCallback.onResponse(list.getData());
                } else {
                    newsCallback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                newsCallback.onFailure();
            }
        });
    }

    public interface OnNewsCallback {
        void onResponse(ArrayList<News> newsArrayList);
        void onFailure();
    }
}

