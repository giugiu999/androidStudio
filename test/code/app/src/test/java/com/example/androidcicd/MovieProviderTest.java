package com.example.androidcicd;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.widget.Toast;

import com.example.androidcicd.movie.Movie;
import com.example.androidcicd.movie.MovieProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MovieProviderTest {
    @Mock
    private FirebaseFirestore mockFirestore;
    @Mock
    private CollectionReference mockCollection;
    @Mock
    private DocumentReference mockDocRef;
    private MovieProvider movieProvider;
    private Context context;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);//start
        when (mockFirestore.collection("movies")).thenReturn(mockCollection);
        when (mockCollection.document()).thenReturn(mockDocRef);
        when(mockCollection.document(anyString())).thenReturn(mockDocRef);
        MovieProvider.setInstance(mockFirestore);
        movieProvider = MovieProvider.getInstance(mockFirestore);
        context = mock(Context.class);
    }

    @Test
    public void testAddMovieSetsId() {
        Movie movie = new Movie("Oppenheimer", "Thriller/Historical Drama", 2023);
        when(mockDocRef.getId()).thenReturn("123");
        movieProvider.addMovie(movie,context);
        assertEquals("Movie was not updated with correct id.", "123", movie.getId());
        verify(mockDocRef).set(movie);
    }

    @Test
    public void testDeleteMovie() {
        // Create movie and set our id
        Movie movie = new Movie("Oppenheimer", "Thriller/Historical Drama", 2023);
        movie.setId("123");

        // Call the delete movie and verify the firebase delete method was called.
        movieProvider.deleteMovie(movie);
        verify(mockDocRef).delete();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMovieShouldThrowErrorForDifferentIds() {
        Movie movie = new Movie("Oppenheimer", "Thriller/Historical Drama", 2023);
        // Set our ID to 1
        movie.setId("1");

        // Make sure the doc ref has a different ID
        when(mockDocRef.getId()).thenReturn("123");

        // Call update movie, which should throw an error
        movieProvider.updateMovie(movie, "Another Title", "Another Genre", 2026,context);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMovieShouldThrowErrorForEmptyName() {
        Movie movie = new Movie("Oppenheimer", "Thriller/Historical Drama", 2023);
        movie.setId("123");
        when(mockDocRef.getId()).thenReturn("123");

        // Call update movie, which should throw an error due to having an empty name
        movieProvider.updateMovie(movie, "", "Another Genre", 2026,context);
    }

    @Test
    public void UniqueAdd() {
        Movie movie = new Movie("Oppenheimer", "Thriller/Historical Drama", 2023);
        movie.setTitle("Oppenheimer");
        when(movieProvider.unique(movie.getTitle())).thenReturn(false);
        //check toast
        Context context1 = mock(Context.class);
        Toast mockToast = mock(Toast.class);
        when(Toast.makeText(context1, "Title must be unique!", Toast.LENGTH_SHORT)).thenReturn(mockToast);

        // Call addMovie, shold show toasts
        movieProvider.addMovie(movie,context);
        verify(mockToast).show();

    }

    @Test
    public void UniqueUpdate(){
        Movie movie = new Movie("Oppenheimer", "Thriller/Historical Drama", 2023);
        movie.setTitle("Oppenheimer");
        when(movieProvider.unique(movie.getTitle())).thenReturn(false);
        //check toast
        Context context = mock(Context.class);
        Toast mockToast = mock(Toast.class);
        when(Toast.makeText(context, "Title must be unique!", Toast.LENGTH_SHORT)).thenReturn(mockToast);
        // Call addMovie, shold show toasts
        movieProvider.updateMovie(movie, "Oppenheimer", "Another Genre", 2026,context);
        verify(mockToast).show();
    }


}
