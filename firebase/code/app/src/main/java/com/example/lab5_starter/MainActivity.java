package com.example.lab5_starter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class MainActivity extends AppCompatActivity implements MovieDialogFragment.MovieDialogListener, MovieArrayAdapter.MovieDeleteListener {

    private Button addMovieButton;
    private ListView movieListView;
    private FirebaseFirestore db;
    private CollectionReference moviesRef;
    private ArrayList<Movie> movieArrayList;
    private MovieArrayAdapter movieArrayAdapter;

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

        // Set views
        addMovieButton = findViewById(R.id.buttonAddMovie);
        movieListView = findViewById(R.id.listviewMovies);

        // Create movie array
        movieArrayList = new ArrayList<>();
        movieArrayAdapter = new MovieArrayAdapter(this, movieArrayList, this); // Pass `this` as MovieDeleteListener
        movieListView.setAdapter(movieArrayAdapter);

        addDummyData();

        db = FirebaseFirestore.getInstance();
        moviesRef = db.collection("movies");

        // Set listeners
        addMovieButton.setOnClickListener(view -> {
            MovieDialogFragment movieDialogFragment = new MovieDialogFragment();
            movieDialogFragment.show(getSupportFragmentManager(), "Add Movie");
        });
        moviesRef.addSnapshotListener((value, error) -> {
            if (error != null){
                Log.e("Firestore", error.toString());
            }
            if (value != null && !value.isEmpty()){
                movieArrayList.clear();
                for (QueryDocumentSnapshot snapshot : value){
                    String title = snapshot.getString("title");
                    String genre = snapshot.getString("genre");
                    String year = snapshot.getString("year");

                    movieArrayList.add(new Movie(title, genre, year));
                }
                movieArrayAdapter.notifyDataSetChanged();
            }
        });

        movieListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Movie movie = movieArrayAdapter.getItem(i);
            if (movie != null) {
                // Show MovieDialogFragment to edit or view movie details
                MovieDialogFragment movieDialogFragment = MovieDialogFragment.newInstance(movie);
                movieDialogFragment.show(getSupportFragmentManager(), "Movie Details");
            }
        });
    }

    @Override
    public void updateMovie(Movie movie, String title, String genre, String year) {
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setYear(year);
        movieArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void addMovie(Movie movie) {
        movieArrayList.add(movie);
        movieArrayAdapter.notifyDataSetChanged();
        DocumentReference docRef = moviesRef.document(movie.getTitle());
        docRef.set(movie);
    }

    @Override
    public void deleteMovie(Movie movie) {
        // Delete from Firestore first, then remove from the list
        DocumentReference docRef = moviesRef.document(movie.getTitle());
        docRef.delete()
                .addOnSuccessListener(aVoid -> {
                    // Remove from the list after successful deletion
                    movieArrayList.remove(movie);
                    movieArrayAdapter.notifyDataSetChanged();
                    Log.d("Firestore", "Movie deleted successfully!");
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error deleting movie", e));
    }

    @Override
    public void onDeleteMovie(Movie movie) {
        // 获取 Firestore 文档引用
        DocumentReference docRef = moviesRef.document(movie.getTitle());

        // 尝试删除该文档
        docRef.delete()
                .addOnSuccessListener(aVoid -> {
                    // 删除成功后，从列表中删除该项
                    movieArrayList.remove(movie);
                    movieArrayAdapter.notifyDataSetChanged();
                    Log.d("Firestore", "Movie deleted successfully!");
                })
                .addOnFailureListener(e -> {
                    // 删除失败，打印错误日志
                    Log.e("Firestore", "Error deleting movie", e);
                });
    }


    public void addDummyData() {
        Movie m1 = new Movie("Interstellar", "Scifi", "No idea");
        Movie m2 = new Movie("Inception", "Action", "2012");
        movieArrayList.add(m1);
        movieArrayList.add(m2);
        movieArrayAdapter.notifyDataSetChanged();
    }
}
