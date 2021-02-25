package com.dehaat.dehaatassignment.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dehaat.dehaatassignment.model.Author;
import com.dehaat.dehaatassignment.repositories.AuthorRepository;

import java.util.List;

public class AuthorViewModel extends AndroidViewModel {
    private AuthorRepository authorRepository;
    private final LiveData<List<Author>> authors;

    public AuthorViewModel(Application application) {
        super(application);
        authorRepository = new AuthorRepository(application);
        authors = authorRepository.getAllAuthors();

    }
    public LiveData<List<Author>> getAuthors(){
        return authors;
    }

    public void insertAuthors(List<Author> authors){
        authorRepository.insertAll(authors);
    }

    public LiveData<Author> getBooksByAuthor(String authorName){
        LiveData<Author> books = authorRepository.getBooksByAuthor(authorName);
        return books;
    }
}
