package com.psp.news_app.model;

import java.util.ArrayList;

public class NewsList {

    public String category;
    public ArrayList<News> data;
    public boolean success;

    public NewsList(String category, ArrayList<News> data, boolean success) {
        this.category = category;
        this.data = data;
        this.success = success;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<News> getData() {
        return data;
    }

    public void setData(ArrayList<News> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
