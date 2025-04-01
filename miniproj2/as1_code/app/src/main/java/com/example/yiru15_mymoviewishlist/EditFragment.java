package com.example.yiru15_mymoviewishlist;

import androidx.fragment.app.DialogFragment;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;

import android.app.Dialog;

public class EditFragment extends DialogFragment {

    private Movie movie;
    private AddMovieFragment.AddMovieDialogListener listener;

    public static EditFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putSerializable("movie", movie);
        EditFragment fragment = new EditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddMovieFragment.AddMovieDialogListener) {
            listener = (AddMovieFragment.AddMovieDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddMovieDialogListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movie = (Movie) getArguments().getSerializable("movie");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_fragment, null);
        EditText name = view.findViewById(R.id.name);
        EditText dname = view.findViewById(R.id.dname);
        EditText g = view.findViewById(R.id.g);
        EditText time = view.findViewById(R.id.time);
        EditText status = view.findViewById(R.id.status);

        // Populate fields with current movie data
        name.setText(movie.getName());
        dname.setText(movie.getDname());
        g.setText(movie.getG());
        time.setText(String.valueOf(movie.getTime()));  // Ensure time is shown as a string
        status.setText(String.valueOf(movie.getStatus()));  // Ensure status is shown as a string ("true" or "false")

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view)
                .setTitle("Edit Movie")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save", (dialog, which) -> {
                    // Get user input
                    String user_name = name.getText().toString();
                    String user_dname = dname.getText().toString();
                    String user_g = g.getText().toString();

                    // Handle status: convert string to boolean
                    String statusInput = status.getText().toString().toLowerCase();
                    boolean user_status = statusInput.equals("true");

                    // Handle time: convert string to int
                    String timeInput = time.getText().toString();
                    int user_time = 0;
                    try {
                        user_time = Integer.parseInt(timeInput);
                    } catch (NumberFormatException e) {
                        // Handle invalid input (could log it or keep default value of 0)
                    }

                    // Update movie object with new values
                    movie.setName(user_name);
                    movie.setDname(user_dname);
                    movie.setTime(user_time);
                    movie.setG(user_g);
                    movie.setStatus(user_status);

                    // Pass the updated movie back to the listener
                    listener.addMovie(movie);
                });

        return builder.create();
    }
}
