package com.alexandre.esilvmobileapp;

import com.google.gson.annotations.SerializedName;

public class Store {
    @SerializedName("title")
    private String title;


    public Store(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
