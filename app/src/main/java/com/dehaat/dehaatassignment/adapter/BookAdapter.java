package com.dehaat.dehaatassignment.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
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
                .inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.mItem = books.get(position);
        holder.name.setText(book.getTitle());
        holder.mContentView.setText(book.getDescription());
        holder.publisher.setText(book.getPublisher());
        holder.publishedDate.setText(book.getPublished_date());
        holder.price.setText("\u20B9" + book.getPrice().toString());
        /* This listens and sets max lines based on initial line count and sets the expandable boolean
         * which is then used to modify behaviour of btnReadMoreOrCollapse.
         * */
        holder.mContentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                holder.btnReadMoreOrCollapse.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (holder.mContentView.getLineCount() <= 3) {
                    holder.expandable = false;
                    holder.btnReadMoreOrCollapse.setVisibility(View.GONE);
                } else {
                    holder.expandable = true;
                    holder.mContentView.setLines(3);
                }
            }
        });
        //Set up Onclick for ReadMore and Collapse button.
        holder.btnReadMoreOrCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.expandable) {
                    holder.expandable = false;
                    ObjectAnimator animation = ObjectAnimator.ofInt(holder.mContentView, "maxLines", 40);
                    animation.setDuration(100).start();
                    holder.btnReadMoreOrCollapse.setText(R.string.collapse);
                } else {
                    holder.expandable = true;
                    ObjectAnimator animation = ObjectAnimator.ofInt(holder.mContentView, "maxLines", 3);
                    animation.setDuration(100).start();
                    holder.btnReadMoreOrCollapse.setText(R.string.read_more);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name;
        public final TextView mContentView;
        public final TextView publisher;
        public final TextView publishedDate;
        public final TextView price;
        public final Button btnReadMoreOrCollapse;
        public Book mItem;
        private Boolean expandable;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            name = view.findViewById(R.id.book_title);
            mContentView = view.findViewById(R.id.content);
            btnReadMoreOrCollapse = view.findViewById(R.id.read_more);
            price = view.findViewById(R.id.tv_price);
            publishedDate = view.findViewById(R.id.tv_published_date);
            publisher = view.findViewById(R.id.tv_publisher);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}