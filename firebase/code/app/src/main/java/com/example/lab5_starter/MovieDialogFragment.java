package com.example.lab5_starter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MovieDialogFragment extends DialogFragment {
    interface MovieDialogListener {
        void updateMovie(Movie movie, String title, String genre, String year);
        void addMovie(Movie movie);
        void deleteMovie(Movie movie);
    }

    private MovieDialogListener listener;

    public static MovieDialogFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putSerializable("Movie", movie);

        MovieDialogFragment fragment = new MovieDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MovieDialogListener) {
            listener = (MovieDialogListener) context;
        } else {
            throw new RuntimeException("Implement listener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_movie_details, null);
        EditText editMovieName = view.findViewById(R.id.edit_title);
        EditText editMovieGenre = view.findViewById(R.id.edit_genre);
        EditText editMovieYear = view.findViewById(R.id.edit_year);

        String tag = getTag();
        Bundle bundle = getArguments();
        Movie movie = null;

        if (tag != null && tag.equals("Movie Details") && bundle != null) {
            movie = (Movie) bundle.getSerializable("Movie");
            if (movie != null) {
                editMovieName.setText(movie.getTitle());
                editMovieGenre.setText(movie.getGenre());
                editMovieYear.setText(movie.getYear());
            }
        }

        final Movie finalMovie = movie;  // Ensure movie is final or effectively final

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Movie Details")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Continue", (dialog, which) -> {
                    String title = editMovieName.getText().toString();
                    String genre = editMovieGenre.getText().toString();
                    String year = editMovieYear.getText().toString();

                    if (finalMovie != null) {
                        listener.updateMovie(finalMovie, title, genre, year);
                    } else {
                        listener.addMovie(new Movie(title, genre, year));
                    }
                })
                .create();
    }
}
