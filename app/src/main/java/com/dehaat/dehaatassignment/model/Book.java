package com.dehaat.dehaatassignment.model;

import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("published_date")
    private String published_date;
    @SerializedName("price")
    private Float price;

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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
