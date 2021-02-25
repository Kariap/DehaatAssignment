package com.dehaat.dehaatassignment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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

    private boolean isTwoPane=false;
    private RecyclerView rvAuthors;
    private AuthorAdapter authorAdapter;
    private AppRestClientService appRestClientService;
    private AuthorViewModel authorViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvAuthors=findViewById(R.id.rv_authors);
        authorAdapter=new AuthorAdapter(this);
        rvAuthors.setLayoutManager(new LinearLayoutManager(this));
        rvAuthors.setAdapter(authorAdapter);
        appRestClientService= AppRestClient.getInstance().getService();

        authorViewModel=new AuthorViewModel(getApplication());
        authorViewModel.getAuthors().observe(this, new Observer<List<Author>>() {
            @Override
            public void onChanged(List<Author> authors) {
                authorAdapter.setmAuthors(authors);
            }
        });
        if(findViewById(R.id.container_author_books)!=null){
            isTwoPane=true;
        }

        fetchAndSaveDataFromServer();

    }

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

    private void saveData(List<Author> authors) {
        authorViewModel.insertAuthors(authors);
    }

}
