package com.psp.news_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.psp.news_app.R;
import com.psp.news_app.adapter.NewsAdapter;
import com.psp.news_app.databinding.ActivityMainBinding;
import com.psp.news_app.model.News;
import com.psp.news_app.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private NewsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,
                false));
        NewsAdapter adapter = new NewsAdapter(this);
        binding.recycleView.setAdapter(adapter);

        // view model initialize
        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        viewModel.getNewListObserver().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                if(news != null) {
                    adapter.setList(news);
                } else {
                    Toast.makeText(MainActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        requestData();
    }

    private void requestData() {
        viewModel.requestNewsData();
    }
}