package com.dehaat.dehaatassignment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dehaat.dehaatassignment.fragments.BooksFragment;
import com.dehaat.dehaatassignment.R;

public class BooksByAuthor extends AppCompatActivity {

    public static final String KEY_AUTHOR_NAME = "AuthorName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_by_author);
        /*
        * Passing on the author name to fragment so that fragment can get books
        * by that author from db.
        */
        Bundle arguments = new Bundle();
        arguments.putString(KEY_AUTHOR_NAME, getIntent().getStringExtra(KEY_AUTHOR_NAME));
        BooksFragment booksFragment = new BooksFragment();
        booksFragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_author_books, booksFragment)
                .commit();
    }
}