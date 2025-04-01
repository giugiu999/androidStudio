package com.example.androiduitesting;

public interface MovieDialogListener {
    void updateMovie(Movie movie, String title, String genre, String year);
    void addMovie(Movie movie);
}
