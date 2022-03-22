package com.psp.news_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.psp.news_app.R;
import com.psp.news_app.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> list;

    private final Context context;

    private final OnItemClicked itemClicked;

    public NewsAdapter(Context context,OnItemClicked itemClicked) {
        this.context = context;
        this.itemClicked = itemClicked;
    }

    public void setList(List<News> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_news,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = list.get(position);

        holder.txtTitle.setText(news.getTitle());

        Glide.with(context)
                .load(news.getImageUrl())
                .apply(RequestOptions.centerInsideTransform())
                .into(holder.image);

        holder.rootLayout.setOnClickListener(view -> {
            itemClicked.onClicked(news);
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public interface OnItemClicked {
        void onClicked(News news);
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtTitle;
        private final ImageView image;
        private final ConstraintLayout rootLayout;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtNewsTitle);
            image = itemView.findViewById(R.id.imgNews);
            rootLayout = itemView.findViewById(R.id.rootLayoutNewsItem);
        }
    }
}
