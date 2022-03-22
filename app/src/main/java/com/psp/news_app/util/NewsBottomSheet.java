package com.psp.news_app.util;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.psp.news_app.databinding.BottomsheetNewsBinding;
import com.psp.news_app.model.News;

public class NewsBottomSheet extends BottomSheetDialogFragment {


    private final News news;

    private BottomsheetNewsBinding binding;

    private boolean initView = true;

    public NewsBottomSheet(News news) {
        this.news = news;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomsheetNewsBinding.inflate(inflater,container,false);
        initView = true;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView = true;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(initView) {
            initView = false;

            binding.txtTitleNewsBottom.setText(news.getTitle());
            binding.txtDescNewsBottom.setText(news.getContent());

            Glide.with(this)
                    .load(news.getImageUrl())
                    .apply(RequestOptions.centerCropTransform())
                    .into(binding.imgNewsBottom);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        dismiss();
    }
}
