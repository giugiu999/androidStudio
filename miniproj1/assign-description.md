# CMPUT 301 W25 - Assignment 0: Getting Started [<img src="https://i.ibb.co/N1c9YKQ/1203148854653100042.png" width="50" height="40" style="vertical-align: bottom"/>](https://i.ibb.co/N1c9YKQ/1203148854653100042.png)

## Learning Objectives

- Learn how to use Android Studio effectively for Android development
- Learn to create, build, and run a simple Android app
- Understand basic Android app architecture and components

## Problem Description

1. Install Android Studio on your computer if you haven't already.

2. Create and run a simple hello world app by following [this detailed tutorial](https://docs.google.com/document/d/1xFcRrNIeQ6Y5HUTQg7nbna9SAwBA1G-D4TIFICZ2NCo).

> [!IMPORTANT]
> In Section 6 step 10 where it says change the text of the `TextView` to 0, type in your **7-digit student number** instead. So the count button will increment your student number. Do not prefix your student number with zeros.

## Deliverables

### Screenshots Required:

1. First screenshot: Your running app before pressing the count button, showing your **7-digit student number**
2. Second screenshot: Your running app after tapping the Count button 7 times (should show student number + 7)

> [!NOTE]
>
> - Screenshots should only show the device display with the app interface
> - Capturing the emulator window frame is acceptable
> - Do not include the development environment in screenshots
> - Screenshots must be clear and readable
> - Ensure your student number is clearly visible in both screenshots

### Project Structure Required:

1. Android project files must be placed in a `/code` folder in the root of your repository
2. Ensure all necessary project files are included
3. Do not include build files or other generated content (hint: use `.gitignore`)
4. Update the `README.md` file with your full name and **CCID**
5. Update the `LICENSE.md` file with your full name

## Submission Procedure

1. Use a non-lossy image format (`PNG`, not `JPEG`)
2. Do not use phone camera photos
3. Name files with your **CCID** (e.g., nandan-1.png, nandan-2.png)
4. Place the 2 images in a `/doc` folder in the root of your repository
5. Verify all files are committed **and** pushed before the deadline

## Implementation Notes

### For build.gradle.ktx files:

Instead of:

```gradle
def nav_version = "2.3.0-alpha04"
classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
```

Use in build.gradle.ktx (Project: My First App):

```gradle
buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.7.6"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}
```

Instead of:

```gradle
apply plugin: 'androidx.navigation.safeargs'
```

Use in build.gradle.ktx (module: app):

```gradle
plugins {
    id("androidx.navigation.safeargs")
}
```

> [!WARNING]
> If you get an error about unresolved import `androidx.navigation.fragment.navArgs`, you can remove that import as it's not needed for Java code.
