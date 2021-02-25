package com.dehaat.dehaatassignment.converters;

import androidx.room.TypeConverter;

import com.dehaat.dehaatassignment.model.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
    @TypeConverter
    public static List<Book> fromString(String value) {
        Type listType = new TypeToken<List<Book>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String fromBook(List<Book> books) {
        Gson gson = new Gson();
        String json = gson.toJson(books);
        return json;
    }
}