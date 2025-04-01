# CMPUT 301 W25 - Assignment 1: Movie Wishlist Android App

Consider the situation of someone who wants to create and manage a wishlist of movies they want to watch. Develop a simple, attractive, and intuitive Android mobile app to assist users in organizing their movie-watching preferences. Let’s name this app: `YOURCCID-MyMovieWishlist` (e.g., `hktang-MyMovieWishlist`).

## Functional Requirements

Using this app, users can maintain a list of movies they wish to watch. Each movie entry in the wishlist has the following editable fields:

- **Movie title** (textual, up to 50 characters)
- **Director name** (textual, up to 30 characters)
- **Genre** (e.g., Action, Drama, Comedy)
- **Release year** (four-digit integer)
- **Status** (Watched/Unwatched)

The app must allow the user to:

- Show a list of movies in their wishlist, displaying the title, director, genre, and watch status.
- Add a new movie to the wishlist (appends to the bottom of the list).
- View and edit the details of an existing movie entry, including title, director, genre, and watch status.
- Delete a movie from the wishlist.
- See the total number of movies in the wishlist and the count of movies marked as watched.

The list need not display all the information for an item if screen space is limited. Minimally, each item in the list should show its title, director, genre, and watch status.

## Usability Requirements

The app must assist the user in proper data entry. For example, use appropriate user interface controls to enforce particular data types and avoid illegal values.

## Data Persistence

For this assignment, the data need not be persistent across runs of the app.

## Naming Requirements

Encode your campus computing ID in the app name. Specifically, the app name must show up as `YOURCCID-MyMovieWishlist` (e.g., `hktang-MyMovieWishlist`).

## Demonstration Actions
1. Open the app from the launcher.
2. Show the list of movies in the wishlist, with none so far. (This could be the initial screen.)
3. Add a movie to the wishlist with the title "Inception," director "Christopher Nolan," genre "Science Fiction," release year 2010, and status "Unwatched."
4. Show the list and the total count, with this item.
5. View/edit this item to update the title to "Inception: Director's Cut" and mark it as "Watched."
6. Show the list and the updated counts, with this modified item.
7. Add another movie to the wishlist with the title "The Matrix," director "Wachowski," genre "Science Fiction," release year 1999, and status "Unwatched."
8. Show the list and the updated counts, with the two movies.
9. Add one more movie to the wishlist with your CCID as the director and your student number as the title of the movie, status "Watched," release year 2025.
10. Show the list and the updated counts, with the three movies.
11. View/edit the details of one movie in the wishlist.
12. Delete one movie from the wishlist.
13. Show the list, with the remaining movies.
14. View the details of one movie in the wishlist.
15. Delete the remaining movies from the wishlist.

## Hints
This is a description of the core functionality. Often, problem statements from users lack details. As you are prototyping a design, you may uncover other behaviors that have not been specified, but make sense in the context and intent of the application. For example, think about how someone might effectively use your application. It is up to you to decide what functions your design will need, based on the given problem description and valid assumptions, in discussion with your users (the TAs and instructor). You should consider asking the customer (the instructor) what they want to see.

While you may discuss your design with other students, the code and documentation must be your own work. Code from publicly available sources may be used within reason and only if their licenses permit so. Always fully cite to give proper credit to the original developers in the source code and in the system documentation. For example, in citing a work, at least state: from whom, the date of publication, license, and URL. Do what is required by its license.

The TAs will be inspecting your code, so "major" commented-out experiments should be cleaned up so that the code is readable.

Do not skimp on the UML class diagrams in the system documentation. For neatness and readability, diagrams should be created or drawn using a vector graphics editing tool and exported in a common, non-lossy graphics format (such as .png). You may not include Android system classes in your UML.

Besides addressing the problem correctly, your software design will be evaluated on its proper use of object-oriented design concepts, such as separation of concerns and information hiding.

Your application will also be evaluated for user interface. We suggest you to use dropdowns for predefined choices, date pickers for dates, and checkboxes/radio buttons for binary or multiple options.

## Losing Marks
You may lose marks for any of the following:
- not validating user input and allowing invalid data.
- files not in properly named subdirectories, missing compiled binary APK file for the app, cannot run the app after installation, cannot distinguish CCID from the app name, cannot view files without specialized tools lossy compression used in image file(s) for UML (e.g., JPEG). inadequate or improper citations These are brown M&M rules.
- The app name not showing up as YOURCCID-MyMovieWishlist.


## Submission Procedure and Deliverables
We ask you to submit code, a UML diagram, and a video.txt file in a directory named YOURCCID-MyMovieWishlist (e.g., hktang-MyMovieWishlist). This directory should contain:

- A **code** subdirectory with your Android Studio **project** and the **APK** file (app/build/intermediates/apk/debug/app-debug.apk or app/build/outputs/apk/debug/app-debug.apk).
- A **video.txt** file containing the **link** to your video.
- A **UML.png** file for the diagram.

**Code Base:**
Include your complete source code and a working APK. Ensure all files needed to build and run the app are present. Each class must have comments describing its purpose, design rationale, and any unresolved issues.

**Video:**
The video **(max. 3 minutes)** must demonstrate that your app works. Focus only on the app screen. No audio is required. Avoid using handheld cameras for clarity.

**System Documentation:**
Provide **UML** class diagram(s) describing your app's object-oriented design. Include only the most important classes, with notes explaining their primary responsibilities. Use non-lossy image formats.

Ensure the app's name appears as YOURCCID-MyMovieWishlist (e.g., hktang-MyMovieWishlist) for easy identification. The TA will inspect and test your app using your submitted files.

## Rubric for Android Movie App Assignment - MyMovieWishlist

This is how the assignment will be marked:

**Excellent (8):**
- The app is fully functional, meeting all the specified requirements.
- The user interface is intuitive, attractive, and responsive.
- The app has data validation checks for all fields.
- The UML documentation is comprehensive and accurately represents the object-oriented design.
- The video demonstration clearly showcases all required actions, and the app performs flawlessly in the demo.
- Must have a Video
- Must have UML
- Must Have Codebase

**Very Good (7):**
- The app runs and performs the expected functionalities.
- Minor issues may be present, such as a small bug or a slight deviation from the requirements.
- The user interface is intuitive, attractive and responsive.
- The app has most data validation checks.
- The UML documentation is mostly complete but may have minor omissions.
- Must have a Video
- Must have UML
- Must Have Codebase

**Good (6):**
- The app runs and performs the expected functionalities.
- Minor issues may be present, such as a small bug or a slight deviation from the requirements.
- The user interface is satisfactory but may have some room for improvement.
- The UML documentation is mostly complete but may have minor omissions.
- Must have a Video
- Must have UML
- Must Have Codebase

**Satisfactory (5):**
- The app runs, but there are noticeable issues affecting its stability or functionality.
- Some requirements may be missing or not fully implemented.
- The user interface is functional but may lack polish or suffer from usability issues.
- The UML documentation is present but may be incomplete or inaccurate.
- Must have a Video
- Must have UML
- Must Have Codebase

**Unsatisfactory (4):**
- Effort has been put into the assignment, but the app may not run well or may have significant functionality issues.
- Several requirements are missing or not implemented.
- The user interface may be confusing or challenging to use.
- The app may not follow the brown M&M rules.
- The UML documentation lacks essential components, incomplete, inaccuate.
- Must have a Video
- Must have UML
- Must Have Codebase

**Failure (0):**
- The assignment is incomplete, lacking essential components such as UML documentation, video demonstration, or code.
- No submission is provided.
- Could be missing any one of these: Video, UML, Codebase

Note that there are no inbetween marks and no part marks.
