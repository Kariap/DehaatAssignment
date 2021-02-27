package com.dehaat.dehaatassignment.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dehaat.dehaatassignment.dao.AuthorDao;
import com.dehaat.dehaatassignment.database.AppDatabase;
import com.dehaat.dehaatassignment.model.Author;

import java.util.List;

//This is used as database repository.
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

    public LiveData<Author> getBooksByAuthor(String name) {
        return authorDao.getBooksByAuthor(name);
    }

    public void insertAll(final List<Author> authors) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            authorDao.insertAuthors(authors);
        });
    }
}
