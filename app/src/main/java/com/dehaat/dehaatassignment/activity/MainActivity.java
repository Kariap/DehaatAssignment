package com.dehaat.dehaatassignment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dehaat.dehaatassignment.R;

public class MainActivity extends AppCompatActivity {

    private boolean isTwoPane=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.container_author_books)!=null){
            isTwoPane=true;
        }
    }
}
