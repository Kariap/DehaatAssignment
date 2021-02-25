package com.dehaat.dehaatassignment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.adapter.AuthorAdapter;
import com.dehaat.dehaatassignment.rest.AppRestClient;
import com.dehaat.dehaatassignment.rest.AppRestClientService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
        appRestClientService.getListOfAuthors().enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonObject responseObject=response.body().getAsJsonObject();
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }
}
