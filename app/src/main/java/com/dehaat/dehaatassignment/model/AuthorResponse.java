package com.dehaat.dehaatassignment.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AuthorResponse {
    @SerializedName("author_name")
    String authorName;
    @SerializedName("author_bio")
    String authorBio;
    @SerializedName("books")
    List<Book> books;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorBio() {
        return authorBio;
    }

    public void setAuthorBio(String authorBio) {
        this.authorBio = authorBio;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
