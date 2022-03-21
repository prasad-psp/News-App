package com.psp.news_app.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.psp.news_app.model.News;
import com.psp.news_app.model.NewsList;
import com.psp.news_app.network.Api;
import com.psp.news_app.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> newsList;

    public NewsViewModel() {
        newsList = new MutableLiveData<>();
    }

    public MutableLiveData<List<News>> getNewListObserver() {
        return newsList;
    }

    public void requestNewsData() {
        Api api = RetrofitInstance.getInstance().create(Api.class);
        Call<NewsList> call = api.getNewsList();

        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, @NonNull Response<NewsList> response) {
                NewsList list = response.body();

                if(list != null) {
                    newsList.setValue(list.getData());
                } else {
                    newsList.setValue(null);
                }

                Log.d("Response ",""+response);

                Log.d("Response ",""+response.body());
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Log.d("Response ",""+t.getMessage());
            }
        });
    }
}
