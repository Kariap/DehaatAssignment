package com.dehaat.dehaatassignment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.adapter.AuthorAdapter;
import com.dehaat.dehaatassignment.model.Author;
import com.dehaat.dehaatassignment.model.ServerResponse;
import com.dehaat.dehaatassignment.rest.AppRestClient;
import com.dehaat.dehaatassignment.rest.AppRestClientService;
import com.dehaat.dehaatassignment.viewmodels.AuthorViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private boolean isTwoPane = false;
    private AuthorAdapter authorAdapter;
    private AppRestClientService appRestClientService;
    private AuthorViewModel authorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         *Check if there is container_author_books which will only be present
         *if the width of device is > 600dp if true set as twoPane mode isTwoPane is used for
         * authorsAdapter.
         */
        if (findViewById(R.id.container_author_books) != null) {
            isTwoPane = true;
        }

        setUpAuthorsRecycleView();

        appRestClientService = AppRestClient.getInstance().getService();
        authorViewModel = new AuthorViewModel(getApplication());
        //Observe data from db and if changed set author data in AuthorAdapter.
        authorViewModel.getAuthors().observe(this, authors -> authorAdapter.setAuthors(authors));

        fetchAndSaveDataFromServer();

    }

    //Set up recycleView with divider ,Adapter and layout manager.
    private void setUpAuthorsRecycleView() {
        RecyclerView rvAuthors = findViewById(R.id.rv_authors);
        authorAdapter = new AuthorAdapter(this, isTwoPane);
        rvAuthors.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvAuthors.getContext(),
                DividerItemDecoration.VERTICAL);
        rvAuthors.addItemDecoration(dividerItemDecoration);
        rvAuthors.setAdapter(authorAdapter);
    }

    //This function fetches data from server and saves it in local database.
    private void fetchAndSaveDataFromServer() {
        appRestClientService.getListOfAuthors().enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                saveData(response.body().getAuthors());
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }

    //This function inserts data into db.
    private void saveData(List<Author> authors) {
        authorViewModel.insertAuthors(authors);
    }

}
