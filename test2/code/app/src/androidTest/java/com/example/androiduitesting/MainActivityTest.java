package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;

import android.util.Log;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new
            ActivityScenarioRule<MainActivity>(MainActivity.class);
    @BeforeClass
    public static void setup(){
        //dummy address
        String host="10.0.2.2";
        int port=8080;
        FirebaseFirestore.getInstance().useEmulator(host, port);
    }
    @After
    public void tearDown() {
        String projectId = "androiduitesting-57fb2";
        URL url = null;
        try {
            url = new URL("http://10.0.2.2:8080/emulator/v1/projects/" + projectId + "/databases/(default)/documents");
        } catch (MalformedURLException exception) {
            Log.e("URL Error", Objects.requireNonNull(exception.getMessage()));
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("DELETE");
            int response = urlConnection.getResponseCode();
            Log.i("Response Code", "Response Code: " + response);
        } catch (IOException exception) {
            Log.e("IO Error", Objects.requireNonNull(exception.getMessage()));
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
    @Before
    public void seedDatabase() throws ExecutionException, InterruptedException {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference moviesRef = db.collection("movies");
        Movie[] movies = {
                new Movie("Oppenheimer", "Thriller/Historical Drama", "2023"),
                new Movie("Barbie", "Comedy/Fantasy", "2023")
        };
        for (Movie movie : movies) {
            Tasks.await(moviesRef.document().set(movie));
        }
        Thread.sleep(5000);
    }

    @Test
    public void addMovieShouldAddValidMovieToMovieList() {
        //opven view
        onView(withId(R.id.buttonAddMovie)).perform(click());
        //dummy input
        onView(withId(R.id.edit_title)).perform(typeText("Interstellar"));
        onView(withId(R.id.edit_genre)).perform(typeText("Science Fiction"));
        onView(withId(R.id.edit_year)).perform(typeText("2014"));
        //submit
        onView(withId(android.R.id.button1)).perform(click());
        //check
        onView(withText("Interstellar")).check(matches(isDisplayed()));
    }
    @Test
    public void addMovieShouldShowErrorForInvalidMovieName(){
        //open view
        onView(withId(R.id.buttonAddMovie)).perform(click());
        //input without name
        onView(withId(R.id.edit_genre)).perform(typeText("Science Fiction"));
        onView(withId(R.id.edit_year)).perform(typeText("2014"));
        //submit
        onView(withId(android.R.id.button1)).perform(click());
        //check
        onView(withId(R.id.edit_title)).check(matches(hasErrorText("Move name cannot be empty!")));
    }
    @Test
    public void editValidMovie() {
        //open view
        onView(withText("Oppenheimer")).perform(click());
        //edit
        onView(withId(R.id.edit_title)).perform(clearText(), typeText("changed1"));
        onView(withId(R.id.edit_genre)).perform(clearText(), typeText("changed2"));
        onView(withId(R.id.edit_year)).perform(clearText(), typeText("2021"));
        onView(withId(android.R.id.button1)).perform(click());//submit
        onView(withText("changed1")).check(matches(isDisplayed()));
//        onView(withId(R.id.edit_title)).check(matches(withText("changed1")));
//        onView(withId(R.id.edit_genre)).check(matches(withText("changed2")));
//        onView(withId(R.id.edit_year)).check(matches(withText("2021")));
    }
    @Test
    public void invalidEdit() {
        // open view
        onView(withText("Oppenheimer")).perform(click());
        //invalid empty, name
        onView(withId(R.id.edit_title)).perform(clearText());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.edit_title)).check(matches(hasErrorText("Move name cannot be empty!")));
        //genre
        onView(withId(R.id.edit_title)).perform(typeText("Oppenheimer"));
        onView(withId(R.id.edit_genre)).perform(clearText());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.edit_genre)).check(matches(hasErrorText("Movie genre cannot be empty!")));
        //year
        onView(withId(R.id.edit_genre)).perform(typeText("Thriller/Historical Drama"));
        onView(withId(R.id.edit_year)).perform(clearText());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.edit_year)).check(matches(hasErrorText("Move year cannot be empty!")));
    }
    @Test
    public void editShow() {
        // click movie to check if it's show the changed details
        onView(withText("Oppenheimer")).perform(click());
        onView(withId(R.id.edit_title)).perform(ViewActions.clearText());
        onView(withId(R.id.edit_title)).perform(ViewActions.typeText("Oppenheimer2"));
//        onView(withId(R.id.edit_genre)).perform(ViewActions.clearText());
//        onView(withId(R.id.edit_genre)).perform(ViewActions.typeText("Oppenheimer2"));
//        onView(withId(R.id.edit_year)).perform(ViewActions.clearText());
//        onView(withId(R.id.edit_year)).perform(ViewActions.typeText("2111"));
        onView(withId(android.R.id.button1)).perform(click());
        //check
        onView(withText("Oppenheimer2")).check(matches(isDisplayed()));
        onView(withText("Oppenheimer2")).perform(click());
        onView(withId(R.id.edit_title)).check(matches(withText("Oppenheimer2")));
//        onView(withId(R.id.edit_genre)).check(matches(withText("Oppenheimer2")));
//        onView(withId(R.id.edit_year)).check(matches(withText("2111")));
    }
    @Test
    public void delete() throws InterruptedException {
        onView(withText("Oppenheimer")).perform(longClick());
        // submit
        onView(withText("Are you sure you want to delete the movie Oppenheimer")).check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).inRoot(isDialog()).perform(click());
        Thread.sleep(5000);
//        onView(withId(android.R.id.button1)).perform(click());
        //check
        onView(withText("Oppenheimer")).check(doesNotExist());
    }

    @Test
    public void appShouldDisplayExistingMoviesOnLaunch() {
        //check data loaded
        onView(withText("Oppenheimer")).check(matches(isDisplayed()));
        onView(withText("Barbie")).check(matches(isDisplayed()));
        //check details
        onView(withText("Oppenheimer")).perform(click());
        onView(withId(R.id.edit_title)).check(matches(withText("Oppenheimer")));
        onView(withId(R.id.edit_genre)).check(matches(withText("Thriller/Historical Drama")));
        onView(withId(R.id.edit_year)).check(matches(withText("2023")));
    }
}
