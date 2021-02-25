package com.dehaat.dehaatassignment.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServerResponse {

    @SerializedName("data")
    List<AuthorResponse> authors;

    public List<AuthorResponse> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorResponse> authors) {
        this.authors = authors;
    }
}
