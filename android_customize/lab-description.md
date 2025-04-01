# Lab 3: More Android - Adapters & Fragments [<img src="https://i.imgur.com/GjOB7DB.png" width="40" height="40" style="vertical-align: bottom"/>](https://i.imgur.com/GjOB7DB.png)

## 1. Getting Started

1. Clone this repository and open the `/code` folder in Android Studio.
2. You'll find a basic `ListyCity` app that displays a static list of cities.

## 2. Demo Instructions

During the lab demo, we'll implement "Add City" functionality:

1. Review [Lab 3 Slides](https://eclass.srv.ualberta.ca/pluginfile.php/11688733/mod_label/intro/lab03-slides.pdf)
2. Follow along with [Lab 3 Instructions](https://eclass.srv.ualberta.ca/pluginfile.php/11688733/mod_label/intro/lab03-instructions.pdf)
3. By the end, you'll have implemented the ability to add new cities to the list.

## 3. Lab 3 Participation Exercise

### Task

- Add functionality to `ListyCity` to allow **editing** existing cities. The design implementation is flexible and up to your creativity.
- Update the `README.md` and the `LICENSE.md` with your details.

### Example Implementation

<div style="display: flex; flex-wrap: wrap; justify-content: center;">
    <img src="assets/img1.png" width="300" style="margin: 20px;">
    <img src="assets/img2.png" width="300" style="margin: 20px;">
</div>
<div style="display: flex; flex-wrap: wrap; justify-content: center;">
    <img src="assets/img3.png" width="300" style="margin: 20px;">
    <img src="assets/img4.png" width="300" style="margin: 20px;">
</div>

## 4. Implementation Tips

### 1. City Class Updates

- Add setter methods for city properties
- Example: `setName()`, `setProvince()`

### 2. Choose Your Implementation

Option 1 - Basic Approach:

- Create Fragment constructor with `City` parameter
- Store `City` as Fragment instance variable
- Include empty constructor for new additions

Option 2 - Recommended Approach:

- Implement `newInstance()` pattern
- Use Fragment Bundle for data
- Make `City` implement `Serializable`
- Access data via `getArguments()` or `requireArguments()`

#### Example Code

```java
public static EditFragment newInstance(City city) {
   Bundle args = new Bundle();
   args.putSerializable("city", city);
   EditFragment fragment = new EditFragment();
   fragment.setArguments(args);
   return fragment;
}
```

> [!CAUTION]
> Make sure to commit **and** push your code to the GitHub repository before the deadline!
