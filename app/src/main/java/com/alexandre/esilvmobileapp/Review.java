package com.alexandre.esilvmobileapp;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("title")
    private String title;

    @SerializedName("platform")
    private String platform;

    @SerializedName("description")
    private String description;

    public Review(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlatform() {
        return platform;
    }
}