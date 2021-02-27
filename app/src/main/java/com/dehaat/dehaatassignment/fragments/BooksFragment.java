package com.dehaat.dehaatassignment.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.activity.BooksByAuthor;
import com.dehaat.dehaatassignment.adapter.BookAdapter;
import com.dehaat.dehaatassignment.model.Author;
import com.dehaat.dehaatassignment.viewmodels.AuthorViewModel;


public class BooksFragment extends Fragment {
    private String authorName;

    public BooksFragment() {
    }

    public static BooksFragment newInstance(int columnCount) {
        BooksFragment fragment = new BooksFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Getting authorName from arguments.
        authorName = getArguments().getString(BooksByAuthor.KEY_AUTHOR_NAME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            AuthorViewModel authorViewModel = new AuthorViewModel(getActivity().getApplication());
            /*
             * This is done to remove instruction message when user first
             * opens the app directly in two pane mode.As layout file for two pane has an instruction text View
             * for displaying instruction.Check activity_main.xml(w400dp) for the instruction text view.The textview
             * is not present in normal mode xml file so this check is necessary.
             */
            if (container != null && container.findViewById(R.id.instruction) != null) {
                container.findViewById(R.id.instruction).setVisibility(View.GONE);
            }
            //Observing books data from db and setting it for recyclerView.
            authorViewModel.getBooksByAuthor(authorName).observe(this, new Observer<Author>() {
                @Override
                public void onChanged(Author author) {
                    recyclerView.setAdapter(new BookAdapter(author.getBooks()));
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                            DividerItemDecoration.VERTICAL);
                    recyclerView.addItemDecoration(dividerItemDecoration);
                }
            });

        }
        return view;
    }
}