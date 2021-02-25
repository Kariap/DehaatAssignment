package com.dehaat.dehaatassignment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.adapter.AuthorAdapter;
import com.dehaat.dehaatassignment.model.Author;
import com.dehaat.dehaatassignment.model.AuthorResponse;
import com.dehaat.dehaatassignment.model.Book;
import com.dehaat.dehaatassignment.model.ServerResponse;
import com.dehaat.dehaatassignment.rest.AppRestClient;
import com.dehaat.dehaatassignment.rest.AppRestClientService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private boolean isTwoPane=false;
    private RecyclerView rvAuthors;
    private AuthorAdapter authorAdapter;
    private AppRestClientService appRestClientService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvAuthors=findViewById(R.id.rv_authors);
        authorAdapter=new AuthorAdapter();
        rvAuthors.setLayoutManager(new LinearLayoutManager(this));
        rvAuthors.setAdapter(authorAdapter);
        appRestClientService= AppRestClient.getInstance().getService();

        if(findViewById(R.id.container_author_books)!=null){
            isTwoPane=true;
        }

        fetchAndSaveDataFromServer();

    }

    private void fetchAndSaveDataFromServer() {
        appRestClientService.getListOfAuthors().enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                List<AuthorResponse> authorResponseList=response.body().getAuthors();
                parseAndSaveData(authorResponseList);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }

    private void parseAndSaveData(List<AuthorResponse> authorResponseList) {
        for(AuthorResponse authorResponse:authorResponseList){
            String authorName=authorResponse.getAuthorName();
            String authorBio=authorResponse.getAuthorBio();
            List<Book> books=authorResponse.getBooks();
            Author author=new Author(authorName,authorBio);
            //insert author
            for(Book book:books){
                //insert Book
            }
        }
    }

}
