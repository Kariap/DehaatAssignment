package com.dehaat.dehaatassignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dehaat.dehaatassignment.BooksListFragment;
import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.activity.BooksByAuthor;
import com.dehaat.dehaatassignment.activity.MainActivity;
import com.dehaat.dehaatassignment.model.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder>{

    private List<Author> mAuthors=new ArrayList<>();
    private MainActivity mainActivityReference;
    private boolean isTwoPane=false;
    public AuthorAdapter(MainActivity context, boolean isTwoPane) {
        this.mainActivityReference =context;
        this.isTwoPane=isTwoPane;
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_author, parent, false);
        return new AuthorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder holder, int position) {
        Author author=mAuthors.get(position);
        String authorName=author.getAuthor_name();
        holder.authorName.setText(author.getAuthor_name());
        holder.authorBio.setText(author.getAuthor_bio());
        holder.clickAbleLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTwoPane) {
                    Context context = holder.clickAbleLinearLayout.getContext();
                    Intent intent = new Intent(context, BooksByAuthor.class);
                    intent.putExtra("AuthorName", author.getAuthor_name());
                    context.startActivity(intent);
                }else{
                    Bundle arguments = new Bundle();
                    arguments.putString("AuthorName",authorName);
                    BooksListFragment booksListFragment=new BooksListFragment();
                    booksListFragment.setArguments(arguments);
                    mainActivityReference.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_author_books, booksListFragment)
                            .commit();
                }
            }
        });
    }

    public void setmAuthors(List<Author> mAuthors) {
        this.mAuthors = mAuthors;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mAuthors.size();
    }

    class AuthorViewHolder extends RecyclerView.ViewHolder{
        final View clickAbleLinearLayout;
        final TextView authorName;
        final TextView authorBio;
        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            clickAbleLinearLayout=itemView;
            authorName=itemView.findViewById(R.id.author_name);
            authorBio=itemView.findViewById(R.id.author_bio);
        }
    }
}
