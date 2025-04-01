package com.example.yiru15_mymoviewishlist;


import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements
        AddMovieFragment.AddMovieDialogListener {
    private ArrayList<Movie> dataList;
    private ListView movieList;
    private MovieArrayAdapter movieAdapter;
    @Override
    public void addMovie(Movie movie) {
        boolean Found = false;
        for (int i=0;i<dataList.size();i++) {
            if (dataList.get(i).getName().equals(movie.getName())) {
                dataList.set(i, movie);
                Found = true;
                break;
            }
        }
        if (!Found) {
            dataList.add(movie);
        }
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] movieNames = { "Inception", "The Matrix", "Interstellar" };
        String[] directors = { "Christopher Nolan", "Wachowski", "Christopher Nolan" };
        String[] genres = { "Science Fiction", "Action", "Science Fiction" };
        int[] years = { 2010, 1999, 2014 };
        Boolean[] status={true,true,false,false};
        dataList = new ArrayList<>();
        for (int i = 0; i < movieNames.length; i++) {
            dataList.add(new Movie(movieNames[i], directors[i],genres[i],years[i],status[i]));
        }
        movieList = findViewById(R.id.list);
        movieAdapter = new MovieArrayAdapter(this, dataList);
        movieList.setAdapter(movieAdapter);
        FloatingActionButton fab = findViewById(R.id.add);
        fab.setOnClickListener(v -> {
            new AddMovieFragment().show(getSupportFragmentManager(), "Add Movie");
        });
        movieList.setOnItemClickListener((parent, view, position, id) -> {
            Movie selectedMovie = dataList.get(position);
            EditFragment editFragment = EditFragment.newInstance(selectedMovie);
            editFragment.show(getSupportFragmentManager(), "Edit Movie");
        });
    }
}