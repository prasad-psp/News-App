package com.psp.news_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    public NewsAdapter(Context context) {
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtTitle;
        private final ImageView image;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtNewsTitle);
            image = itemView.findViewById(R.id.imgNews);
        }
    }
}
