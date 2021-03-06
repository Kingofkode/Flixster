# Project 2 - Flixster

Flixster shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: 15 hours spent in total

<img src="https://raw.githubusercontent.com/Kingofkode/Flixster/master/HomeScreenshot.jpg" width="300"> <img src="https://raw.githubusercontent.com/Kingofkode/Flixster/master/DetailsScreenshot.jpg" width="300">


## User Stories

The following **required** functionality is completed:

* [x] User can **scroll through current movies** from the Movie Database API
* [x] Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
* [x] For each movie displayed, user can see the following details:
  * [x] Title, Poster Image, Overview (Portrait mode)
  * [x] Title, Backdrop Image, Overview (Landscape mode)
* [x] Allow user to view details of the movie including ratings and popularity within a separate activity

The following **stretch** features are implemented:

* [x] Improved the user interface by experimenting with styling and coloring.
* [x] Apply rounded corners for the poster or background images using [Glide transformations](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#transformations)
* [x] Apply the popular [View Binding annotation library](http://guides.codepath.org/android/Reducing-View-Boilerplate-with-ViewBinding) to reduce boilerplate code.
* [x] Allow video trailers to be played in full-screen using the YouTubePlayerView from the details screen.

The following **additional** features are implemented:

* [x] Display applicable genres in the movie details view (using the [get-movie-list endpoint](https://developers.themoviedb.org/3/genres/get-movie-list)).
* [x] Display number of ratings in the movie details view.
* [x] Display the release date in the movie details view.
* [x] Embedded the YouTubePlayerView directly into the details screen (similar to the actual YouTube App)
  * [x] Movie details can be seen side by side with the video (Portrait Mode)  
  * [x] Full screen playback (Landscape Mode)


## Video Walkthrough

Here's a walkthrough of implemented user stories:

![](https://github.com/Kingofkode/Flixster/blob/master/FlixsterDemo.gif)

## Notes

The most difficult part of the project was changing to the data binding UI framework. However, after implementing the framework, I gained a deeper understanding of how Android manages layouts in activities.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
