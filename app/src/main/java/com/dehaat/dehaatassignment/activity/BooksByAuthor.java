package com.dehaat.dehaatassignment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dehaat.dehaatassignment.BooksListFragment;
import com.dehaat.dehaatassignment.R;

public class BooksByAuthor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_by_author);
        Intent intent = getIntent();
        String authorName=intent.getStringExtra("AuthorName");
        Bundle arguments = new Bundle();
        arguments.putString("AuthorName",authorName);
        BooksListFragment booksListFragment=new BooksListFragment();
        booksListFragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_author_books, booksListFragment)
                .commit();

    }
}