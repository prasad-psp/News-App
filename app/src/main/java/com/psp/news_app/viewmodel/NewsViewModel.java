package com.psp.news_app.viewmodel;

import android.view.View;

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

    private final MutableLiveData<Integer> progressbar;

    public NewsViewModel() {
        newsList = new MutableLiveData<>();
        progressbar = new MutableLiveData<>();

        // Hide progressbar
        progressbar.setValue(View.INVISIBLE);
    }

    public MutableLiveData<List<News>> getNewListObserver() {
        return newsList;
    }

    public MutableLiveData<Integer> getProgressbarObserver() {
        return progressbar;
    }

    public void requestNewsData() {
        // show progressbar
        progressbar.setValue(View.VISIBLE);

        Api api = RetrofitInstance.getInstance().create(Api.class);
        Call<NewsList> call = api.getNewsList();

        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, @NonNull Response<NewsList> response) {
                // Hide progressbar
                progressbar.setValue(View.INVISIBLE);

                NewsList list = response.body();

                if(list != null) {
                    newsList.setValue(list.getData());
                } else {
                    newsList.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                // Hide progressbar
                progressbar.setValue(View.INVISIBLE);
            }
        });
    }
}
