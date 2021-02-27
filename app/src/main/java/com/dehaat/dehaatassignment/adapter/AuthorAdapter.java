package com.dehaat.dehaatassignment.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dehaat.dehaatassignment.fragments.BooksFragment;
import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.activity.BooksByAuthor;
import com.dehaat.dehaatassignment.activity.MainActivity;
import com.dehaat.dehaatassignment.model.Author;

import java.util.ArrayList;
import java.util.List;


public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder> {

    private List<Author> mAuthors = new ArrayList<>();
    private MainActivity mainActivityReference;
    private boolean isTwoPane;
    private int selectedItemPosition = -1;

    public AuthorAdapter(MainActivity context, boolean isTwoPane) {
        this.mainActivityReference = context;
        this.isTwoPane = isTwoPane;
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

        Author author = mAuthors.get(position);
        String authorName = author.getAuthor_name();

        holder.authorName.setText(author.getAuthor_name());
        holder.authorBio.setText(author.getAuthor_bio());
        /*
         * Set up clickListener to handle Author clicks.
         * if isTwoPane mode we populate the books fragment in the container else we
         * start a new activity.
         */
        holder.clickAbleLinearLayout.setOnClickListener(v -> {
            if (!isTwoPane) {
                //starting the activity.
                Context context = holder.clickAbleLinearLayout.getContext();
                Intent intent = new Intent(context, BooksByAuthor.class);
                intent.putExtra(BooksByAuthor.KEY_AUTHOR_NAME, author.getAuthor_name());
                context.startActivity(intent);
            } else {
                //This variable is used to change colour of selected author view.
                selectedItemPosition = position;
                //This is done to change color of previously selected author view back to normal.
                notifyDataSetChanged();
                //Creating the Fragment in the container if two pane.
                Bundle arguments = new Bundle();
                arguments.putString(BooksByAuthor.KEY_AUTHOR_NAME, authorName);
                BooksFragment booksFragment = new BooksFragment();
                booksFragment.setArguments(arguments);
                mainActivityReference.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_author_books, booksFragment)
                        .commit();
            }
        });
        //Changing colour for selected Author view.
        //Note:This works because notifyDataSetChanged() is called in onClick.
        if (selectedItemPosition == position)
            holder.clickAbleLinearLayout.setBackgroundColor(Color.parseColor("#f0f0f0"));
        else
            holder.clickAbleLinearLayout.setBackgroundColor(Color.parseColor("#ffffff"));

        //Set up Onclick for ReadMore and Collapse button.
        holder.btnReadMoreOrCollapse.setOnClickListener(v -> {
            if (holder.expandable) {
                holder.expandable = false;
                ObjectAnimator animation = ObjectAnimator.ofInt(holder.authorBio, "maxLines", 40);
                animation.setDuration(100).start();
                holder.btnReadMoreOrCollapse.setText(R.string.collapse);
            } else {
                holder.expandable = true;
                ObjectAnimator animation = ObjectAnimator.ofInt(holder.authorBio, "maxLines", 3);
                animation.setDuration(100).start();
                holder.btnReadMoreOrCollapse.setText(R.string.read_more);
            }
        });
        /* This listens and sets max lines based on initial line count and sets the expandable boolean
         * which is then used to modify behaviour of btnReadMoreOrCollapse.
         * */
        holder.authorBio.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                holder.btnReadMoreOrCollapse.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (holder.authorBio.getLineCount() <= 3) {
                    holder.expandable = false;
                    holder.btnReadMoreOrCollapse.setVisibility(View.GONE);
                } else {
                    holder.expandable = true;
                    holder.authorBio.setLines(3);
                }
            }
        });

    }

    public void setAuthors(List<Author> mAuthors) {
        this.mAuthors = mAuthors;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mAuthors.size();
    }

    class AuthorViewHolder extends RecyclerView.ViewHolder {
        final View clickAbleLinearLayout;
        final TextView authorName;
        final TextView authorBio;
        final Button btnReadMoreOrCollapse;
        private boolean expandable;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            clickAbleLinearLayout = itemView;
            authorName = itemView.findViewById(R.id.author_name);
            authorBio = itemView.findViewById(R.id.author_bio);
            btnReadMoreOrCollapse = itemView.findViewById(R.id.read_more);
        }
    }
}
