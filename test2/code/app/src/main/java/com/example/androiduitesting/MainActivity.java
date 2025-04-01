package com.example.androiduitesting;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieDialogListener, MovieDeleteDialogListener {
    private Button addMovieButton;
    private ListView movieListView;
    private FirebaseFirestore db;
    private CollectionReference moviesRef;

    private ArrayList<Movie> movieArrayList;
    private ArrayAdapter<Movie> movieArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = FirebaseFirestore.getInstance();
        moviesRef = db.collection("movies");

        moviesRef.addSnapshotListener((value, error) -> {
            if (error != null){
                Log.e("Firestore", error.toString());
            }
            if (value != null){
                movieArrayList.clear();
                if (!value.isEmpty()) {
                    for (QueryDocumentSnapshot snapshot : value) {
                        snapshot.toObject(Movie.class);
                        movieArrayList.add(snapshot.toObject(Movie.class));
                    }
                }
                movieArrayAdapter.notifyDataSetChanged();
            }
        });

        // Set views
        addMovieButton = findViewById(R.id.buttonAddMovie);
        movieListView = findViewById(R.id.listviewMovies);

        // create movie array
        movieArrayList = new ArrayList<>();
        movieArrayAdapter = new MovieArrayAdapter(this, movieArrayList);
        movieListView.setAdapter(movieArrayAdapter);

        // set listeners
        addMovieButton.setOnClickListener(view -> {
            MovieDialogFragment movieDialogFragment = new MovieDialogFragment();
            movieDialogFragment.show(getSupportFragmentManager(),"Add Movie");
        });

        movieListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Movie movie = movieArrayAdapter.getItem(i);
            MovieDialogFragment movieDialogFragment = MovieDialogFragment.newInstance(movie);
            movieDialogFragment.show(getSupportFragmentManager(),"Movie Details");
        });

        movieListView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Movie movie = movieArrayAdapter.getItem(i);
            MovieDeleteDialogFragment movieDeleteDialogFragment = MovieDeleteDialogFragment.newInstance(movie);
            movieDeleteDialogFragment.show(getSupportFragmentManager(),"Movie Delete");
            return true;
        });
    }

    @Override
    public void updateMovie(Movie movie, String title, String genre, String year) {
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setYear(year);
        DocumentReference docRef = moviesRef.document(movie.getTitle());
        docRef.set(movie);
    }

    @Override
    public void addMovie(Movie movie){
        DocumentReference docRef = moviesRef.document(movie.getTitle());
        docRef.set(movie);
    }

    @Override
    public void deleteMovie(Movie movie) {
        DocumentReference docRef = moviesRef.document(movie.getTitle());
        docRef.delete();
    }
}
