package com.dehaat.dehaatassignment.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//This class is used to easily parse and get data from server into java object.
public class ServerResponse {

    @SerializedName("data")
    List<Author> authors;

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
