package com.example.androiduitesting;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MovieDeleteDialogFragment extends DialogFragment {
    private MovieDeleteDialogListener listener;

    public static MovieDeleteDialogFragment newInstance(Movie movie){
        Bundle args = new Bundle();
        args.putSerializable("Movie", movie);

        MovieDeleteDialogFragment fragment = new MovieDeleteDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MovieDeleteDialogListener){
            listener = (MovieDeleteDialogListener) context;
        }
        else {
            throw new RuntimeException("Implement listener");
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        Movie movie;

        // Handle data validation
        if (bundle != null)
            movie = (Movie) bundle.getSerializable("Movie");
        else
            throw new RuntimeException("Bundle was not present!");
        if (movie == null)
            throw new RuntimeException("Movie was not in bundle!");

        return new AlertDialog.Builder(requireContext())
                .setMessage("Are you sure you want to delete the movie " + movie.getTitle())
                .setPositiveButton("Delete", (dialog, which) -> {
                    Log.d("ALT", "deletion");
                    listener.deleteMovie(movie);
                })
                .create();
    }
}
