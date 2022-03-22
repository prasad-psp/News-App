package com.psp.news_app.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.psp.news_app.model.News;
import com.psp.news_app.repo.NewsRepo;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    // News list
    private final MutableLiveData<List<News>> newsList;

    // Progressbar
    private final MutableLiveData<Integer> progressbar;

    // Repo object
    private final NewsRepo repo;

    public NewsViewModel() {
        newsList = new MutableLiveData<>();
        progressbar = new MutableLiveData<>();
        repo = new NewsRepo();

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

        repo.requestNewsData(new NewsRepo.OnNewsCallback() {
            @Override
            public void onResponse(ArrayList<News> newsArrayList) {
                // Hide progressbar
                progressbar.setValue(View.INVISIBLE);
                newsList.setValue(newsArrayList);
            }

            @Override
            public void onFailure() {
                // Hide progressbar
                progressbar.setValue(View.INVISIBLE);
                newsList.setValue(null);
            }
        });
    }
}
