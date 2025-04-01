package com.example.androidcicd.movie;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class MovieProvider {
    private static MovieProvider movieProvider;
    private final ArrayList<Movie> movies;
    private final CollectionReference movieCollection;

    private MovieProvider(FirebaseFirestore firestore) {
        movies = new ArrayList<>();
        movieCollection = firestore.collection("movies");
    }

    public interface DataStatus {
        void onDataUpdated();
        void onError(String error);
    }

    public void listenForUpdates(final DataStatus dataStatus) {
        movieCollection.addSnapshotListener((snapshot, error) -> {
            if (error != null) {
                dataStatus.onError(error.getMessage());
                return;
            }
            movies.clear();
            if (snapshot != null) {
                for (QueryDocumentSnapshot item : snapshot) {
                    movies.add(item.toObject(Movie.class));
                }
                dataStatus.onDataUpdated();
            }
        });
    }

    public static MovieProvider getInstance(FirebaseFirestore firestore) {
        if (movieProvider == null)
            movieProvider = new MovieProvider(firestore);
        return movieProvider;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public boolean unique(String title){
        for (Movie movie:movies){
            if(movie.getTitle().equals(title)){
                return false;
            }
        }
        return true;
    }

    public void updateMovie(Movie movie, String title, String genre, int year, Context context) {
        if(unique(title)){
            movie.setTitle(title);
            movie.setGenre(genre);
            movie.setYear(year);
            DocumentReference docRef = movieCollection.document(movie.getId());
            if (validMovie(movie, docRef)) {
                docRef.set(movie);
            } else {
                throw new IllegalArgumentException("Invalid Movie!");
            }
        }
        else{
            Toast.makeText(context, "Title must be unique!", Toast.LENGTH_SHORT).show();
        }
    }

    public void addMovie(Movie movie, Context context) {
        if(unique(movie.getTitle())){
            DocumentReference docRef = movieCollection.document();
            movie.setId(docRef.getId());
            if (validMovie(movie, docRef)) {
                docRef.set(movie);
            } else {
                throw new IllegalArgumentException("Invalid Movie!");
            }
        }else{
            Toast.makeText(context, "Title must be unique!", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteMovie(Movie movie) {
        DocumentReference docRef = movieCollection.document(movie.getId());
        docRef.delete();
    }

    public boolean validMovie(Movie movie, DocumentReference docRef) {
        return movie.getId().equals(docRef.getId()) && !movie.getTitle().isEmpty() && !movie.getGenre().isEmpty() && movie.getYear() > 0;
    }
    public static void setInstance(FirebaseFirestore firebaseFirestore){
        MovieProvider movie=new MovieProvider(firebaseFirestore);
    }
}
