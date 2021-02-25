package com.dehaat.dehaatassignment.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private final List<Book> books;

    public BookAdapter(List<Book> books) {
        this.books = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_books_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = books.get(position);
        holder.name.setText(books.get(position).getTitle());
        holder.mContentView.setText(books.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name;
        public final TextView mContentView;
        public Book mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name = (TextView) view.findViewById(R.id.name);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}