package com.dehaat.dehaatassignment.model;

import com.google.gson.annotations.SerializedName;

public class Author {

    public Author(String author_name, String author_bio) {
        this.author_name = author_name;
        this.author_bio = author_bio;
    }

    @SerializedName("author_name")
    private String author_name;

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_bio() {
        return author_bio;
    }

    public void setAuthor_bio(String author_bio) {
        this.author_bio = author_bio;
    }

    @SerializedName("author_name")
    private String author_bio;


}
