package com.dehaat.dehaatassignment.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dehaat.dehaatassignment.converters.Converters;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "authors")
public class Author {

    public Author(@NonNull String author_name, String author_bio, List<Book> books) {
        this.author_name = author_name;
        this.author_bio = author_bio;
        this.books = books;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "author_name")
    @SerializedName("author_name")
    private String author_name;

    @ColumnInfo(name = "author_bio")
    @SerializedName("author_bio")
    private String author_bio;

    @SerializedName("books")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

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




}
