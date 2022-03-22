package com.psp.news_app.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.psp.news_app.R;
import com.psp.news_app.adapter.NewsAdapter;
import com.psp.news_app.databinding.ActivityMainBinding;
import com.psp.news_app.model.News;
import com.psp.news_app.util.NewsBottomSheet;
import com.psp.news_app.viewmodel.NewsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsAdapter.OnItemClicked{

    private ActivityMainBinding binding;

    private NewsViewModel viewModel;

    private NewsAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,
                false));
        adapter = new NewsAdapter(this,this);
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

        viewModel.getProgressbarObserver().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.progressIndicator.setVisibility(integer);
            }
        });

        requestData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menuRefresh) {
            // refresh menu clicked
            adapter.clearAll();
            viewModel.requestNewsData();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }


    private void requestData() {
        viewModel.requestNewsData();
    }

    @Override
    public void onClicked(News news) {
        if(news == null) {
            return;
        }

        NewsBottomSheet bottomSheet = new NewsBottomSheet(news);
        bottomSheet.show(getSupportFragmentManager(),getString(R.string.news_bottom_tag));
    }
}