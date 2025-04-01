package com.example.androiduitesting;

import static android.text.TextUtils.isDigitsOnly;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MovieDialogFragment extends DialogFragment {
    private EditText editMovieName;
    private EditText editMovieGenre;
    private EditText editMovieYear;
    private MovieDialogListener listener;

    public static MovieDialogFragment newInstance(Movie movie){
        Bundle args = new Bundle();
        args.putSerializable("Movie", movie);

        MovieDialogFragment fragment = new MovieDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MovieDialogListener){
            listener = (MovieDialogListener) context;
        }
        else {
            throw new RuntimeException("Implement listener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_movie_details, null);
        editMovieName = view.findViewById(R.id.edit_title);
        editMovieGenre = view.findViewById(R.id.edit_genre);
        editMovieYear = view.findViewById(R.id.edit_year);

        String tag = getTag();
        Bundle bundle = getArguments();
        Movie movie;

        if (tag != null && tag.equals( "Movie Details") && bundle != null){
            movie = (Movie) bundle.getSerializable("Movie");
            editMovieName.setText(movie.getTitle());
            editMovieGenre.setText(movie.getGenre());
            editMovieYear.setText(movie.getYear());
        }
        else {movie = null;}

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // Create the dialog fragment
        AlertDialog dialog = builder
                .setView(view)
                .setTitle("Movie Details")
                .setNegativeButton("Cancel", null)
                // Override this later
                .setPositiveButton("Continue", null)
                .create();

        // Change dialog so it does not automatically dismiss, but only when valid data is entered
        dialog.setOnShowListener(d -> {
            Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(v -> {
                String title = editMovieName.getText().toString().trim();
                String genre = editMovieGenre.getText().toString().trim();
                String year = editMovieYear.getText().toString().trim();
                if (title.isEmpty()) {
                    editMovieName.setError("Move name cannot be empty!");
                } else if (genre.isEmpty()){
                    editMovieGenre.setError("Movie genre cannot be empty!");
                } else if (year.isEmpty()) {
                    editMovieYear.setError("Move year cannot be empty!");
                } else if (!isDigitsOnly(editMovieYear.getText())) {
                    editMovieYear.setError("Move year must be numeric!");
                } else {
                    if (tag != null && tag.equals( "Movie Details")) {
                        listener.updateMovie(movie, title, genre, year);
                    } else {
                        listener.addMovie(new Movie(title, genre, year));
                    }
                    dialog.dismiss();
                }
            });
        });
        return dialog;
    }
}
