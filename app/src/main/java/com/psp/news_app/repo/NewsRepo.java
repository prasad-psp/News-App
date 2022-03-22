package com.psp.news_app.repo;

import androidx.annotation.NonNull;

import com.psp.news_app.model.News;
import com.psp.news_app.model.NewsList;
import com.psp.news_app.network.Api;
import com.psp.news_app.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepo {

    private final Api api;

    public NewsRepo() {
        api = RetrofitInstance.getInstance().create(Api.class);
    }

    public void requestNewsData(OnNewsCallback newsCallback) {

        Call<NewsList> call = api.getNewsList();
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

