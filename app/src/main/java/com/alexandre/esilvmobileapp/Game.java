package com.alexandre.esilvmobileapp;

import com.google.gson.annotations.SerializedName;

public class Game {
    @SerializedName("title")
    private String title;


    public Game(String title, String description) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
