package com.example.flixster;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.databinding.ActivityMovieDetailsBinding;
import com.example.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.Headers;

public class MovieDetailsActivity extends YouTubeBaseActivity {

    public static final String TAG = "MovieDetailsActivity";

    // Movie to display
    Movie movie;

    ActivityMovieDetailsBinding binding;

    Map<Integer, String> genreDictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        inflateGenreDictionary();
        // Unwrap the movie passed via intent, using its simple name as a the key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        loadVideoForMovie();
        // Set title and overview
        binding.tvTitle.setText(movie.getTitle());
        binding.tvOverview.setText(movie.getOverview());
        // Vote average is 0 - 10, converted to 0 - 5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        binding.rbVoteAverage.setRating(voteAverage > 0 ? voteAverage / 2.0f : voteAverage);
        // Vote count left of rating bar
        // To insert commas where necessary. i.e. 1000 -> 1,000
        String countString = NumberFormat.getNumberInstance(Locale.US).format(movie.getVoteCount());
        binding.tvVoteCount.setText(String.format("(%s)", countString));
        // Release date of movie
        binding.tvReleaseDate.setText("Released: " + movie.getReleaseDate());
        // Genres
        StringBuilder genreString = new StringBuilder();
        List<Integer> genreIds = movie.getGenresIds();
        for (int i = 0; i < genreIds.size(); i++) {
            genreString.append(genreDictionary.get(genreIds.get(i)));
            if (i != genreIds.size() - 1) {
                genreString.append(" • ");
            }
        }

        binding.tvGenres.setText(genreString.toString());
    }

    private void inflateGenreDictionary() {
        // Mapping genre ids to their name
        // API ref:
        genreDictionary = new HashMap<>();
        genreDictionary.put(28, "Action");
        genreDictionary.put(12, "Adventure");
        genreDictionary.put(16, "Animation");
        genreDictionary.put(35, "Comedy");
        genreDictionary.put(80, "Crime");
        genreDictionary.put(99, "Documentary");
        genreDictionary.put(18, "Drama");
        genreDictionary.put(10751, "Family");
        genreDictionary.put(14, "Fantasy");
        genreDictionary.put(36, "History");
        genreDictionary.put(27, "Horror");
        genreDictionary.put(10402, "Music");
        genreDictionary.put(9648, "Mystery");
        genreDictionary.put(10749, "Romance");
        genreDictionary.put(878, "Science Fiction");
        genreDictionary.put(10770, "TV Movie");
        genreDictionary.put(53, "Thriller");
        genreDictionary.put(10752, "War");
        genreDictionary.put(37, "Western");
    }

    private void loadVideoForMovie() {
        String videosUrl = "https://api.themoviedb.org/3/movie/" + movie.getId() + "/videos" + "?api_key=" + getString(R.string.tmdb_api_key);

        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(videosUrl, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                JSONObject responseObject = json.jsonObject;
                Log.d(TAG, "onSuccess");
                try {
                    JSONArray results = responseObject.getJSONArray("results");
                    if (results.getJSONObject(0) != null) {
                        String videoID = results.getJSONObject(0).getString("key");
                        launchPlayer(videoID);
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "JSON Exception", e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });

    }

    private void launchPlayer(final String videoID) {
        // Resolve player view from layout
        binding.player.initialize(getString(R.string.youtube_api_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(videoID);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.e(TAG, "onInitializationFailure");
            }
        });
    }


}