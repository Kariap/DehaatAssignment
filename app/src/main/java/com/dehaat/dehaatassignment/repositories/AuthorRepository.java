package com.dehaat.dehaatassignment.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dehaat.dehaatassignment.dao.AuthorDao;
import com.dehaat.dehaatassignment.database.AppDatabase;
import com.dehaat.dehaatassignment.model.Author;
import com.dehaat.dehaatassignment.model.Book;

import java.util.ArrayList;
import java.util.List;

public class AuthorRepository {
    private AuthorDao authorDao;
    private LiveData<List<Author>> authors;

    public AuthorRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        authorDao = db.authorDao();
        authors = authorDao.getAllAuthors();
    }
    public LiveData<List<Author>> getAllAuthors() {
        return authors;
    }
    public LiveData<Author> getBooksByAuthor(String name){
        LiveData<Author>authorDetails=authorDao.getBooksByAuthor(name);
        return authorDetails;
    }
    public void insertAll(final List<Author> authors) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            authorDao.insertAuthors(authors);
        });
    }
}
