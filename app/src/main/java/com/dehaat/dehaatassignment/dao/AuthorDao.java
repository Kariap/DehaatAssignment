package com.dehaat.dehaatassignment.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dehaat.dehaatassignment.model.Author;
import com.dehaat.dehaatassignment.model.Book;

import java.util.List;

@Dao
public interface AuthorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAuthors(List<Author> authors);

    @Query("SELECT * FROM authors ORDER BY author_name ASC")
    LiveData<List<Author>> getAllAuthors();

    @Query("SELECT * FROM authors WHERE author_name=:name")
    LiveData<Author> getBooksByAuthor(String name);
}
