package com.dehaat.dehaatassignment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dehaat.dehaatassignment.adapter.BookAdapter;
import com.dehaat.dehaatassignment.model.Author;
import com.dehaat.dehaatassignment.model.Book;
import com.dehaat.dehaatassignment.viewmodels.AuthorViewModel;

import java.util.ArrayList;
import java.util.List;


public class BooksListFragment extends Fragment {
    private String authorName;

    public BooksListFragment() {
    }

    public static BooksListFragment newInstance(int columnCount) {
        BooksListFragment fragment = new BooksListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authorName = getArguments().getString("AuthorName");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            AuthorViewModel authorViewModel=new AuthorViewModel(getActivity().getApplication());
            //TODO:Only get books from room query instead of Author
            authorViewModel.getBooksByAuthor(authorName).observe(this, new Observer<Author>() {
                @Override
                public void onChanged(Author author) {
                    recyclerView.setAdapter(new BookAdapter(author.getBooks()));
                }
            });

        }
        return view;
    }
}