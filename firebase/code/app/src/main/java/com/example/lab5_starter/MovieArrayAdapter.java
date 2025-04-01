package com.example.lab5_starter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    private ArrayList<Movie> movies;
    private Context context;
    private MovieDeleteListener deleteListener;

    public interface MovieDeleteListener {
        void onDeleteMovie(Movie movie);  // Define the delete method
    }

    public MovieArrayAdapter(Context context, ArrayList<Movie> movies, MovieDeleteListener deleteListener) {
        super(context, 0, movies);
        this.movies = movies;
        this.context = context;
        this.deleteListener = deleteListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // If convertView is null, create a new view
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_movie, parent, false);
        }

        // Get the movie object
        Movie movie = movies.get(position);

        // Get the layout views
        TextView movieName = view.findViewById(R.id.textMovieName);
        TextView movieGenre = view.findViewById(R.id.textMovieGenre);
        TextView movieYear = view.findViewById(R.id.textMovieYear);
        Button deleteButton = view.findViewById(R.id.buttonDelete);

        // Set the texts
        movieName.setText(movie.getTitle());
        movieGenre.setText(movie.getGenre());
        movieYear.setText(movie.getYear());

        // Set delete button listener
        deleteButton.setOnClickListener(v -> {
            deleteListener.onDeleteMovie(movie);
        });

        return view;
    }
}
